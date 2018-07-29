/**
 * 
 */
package SyncTools.EffectiveCache;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask来封装计算任务，这样如果已经有线程开始了计算，那另外一个线程通过
 * FutureTask.get()获取结果时将被阻塞，而不会重新发起计算
 * 
 */
public class Memoizer<A,V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = 
    		new ConcurrentHashMap<A,Future<V>>();//缓存
    private Computable<A, V> c;//封装一个计算任务
	public Memoizer(Computable<A, V> c) {
		this.c = c;
	}
	@Override
	public V compute(A arg) throws Exception {
		Future<V> f = cache.get(arg);
		if (f == null) {
			Callable<V> eval = new Callable<V>() {
				@Override
				public V call() throws Exception {
					return c.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<>(eval);
			f = cache.putIfAbsent(arg, ft);//如果缓存中没有以arg为Key的键值对，putIfAbsent将返回null
			if (f == null) {
				f = ft;
				ft.run();
			}
		}
		try {
			return f.get();
		} catch (CancellationException e) {
			cache.remove(arg, f);
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
