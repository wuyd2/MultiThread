/**
 * 
 */
package SyncTools.EffectiveCache;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 使用FutureTask来封装计算任务，这样如果已经有线程开始了计算，那另外一个线程通过
 * FutureTask.get()获取结果时将被阻塞，而不会重新发起计算
 * 
 * 此实现有 先检查后执行 的问题，先检查 f是否为空，然后决定是否插入，不是原子操作，仍然有可能
 * 因为这个非原子操作导致重复计算。
 */
public class Memoizer3<A,V> implements Computable<A, V> {
    private final ConcurrentHashMap<A, Future<V>> cache = 
    		new ConcurrentHashMap<A,Future<V>>();//缓存
    private Computable<A, V> c;//封装一个计算任务
	public Memoizer3(Computable<A, V> c) {
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
			f = ft;
			cache.put(arg, f);//放入缓存中，阻塞其它线程重新计算，避免重复计算
			ft.run();//开始计算
		}
			try {
				return f.get();
			} catch (InterruptedException | ExecutionException e) {
				throw e;
			}
	}

}
