/**
 * 
 */
package SyncTools.EffectiveCache;

import java.util.HashMap;
import java.util.Map;

/**
 * 第一种缓存实现
 * 使用HashMap做缓存，由于HashMap不是线程安全的容器，必须对compute方法使用synchronized进行
 * 同步，但这样做就导致所有的计算任务都被串行化，同一时刻只允许一个线程访问compute方法，其它线
 * 程被阻塞。性能很低
 * 
 */
public class Memoizer1<A, V> implements Computable<A, V> {
   private final Map<A, V> cache = new HashMap<A,V>(); //使用HashMap缓存计算结果 
   private final Computable<A, V> c;//待计算任务
   public Memoizer1(Computable<A, V> c) {
	   this.c = c;
   }
	@Override
	public synchronized V compute(A arg)throws Exception  {
		V value = cache.get(arg);
		if (value == null) {
			value = c.compute(arg);
			cache.put(arg, value);
		}
		return value;
	}
}
