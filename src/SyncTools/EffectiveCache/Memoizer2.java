package SyncTools.EffectiveCache;

import java.util.concurrent.ConcurrentHashMap;
/***
 * 
 *  使用ConcurrentHashMap实现缓存，引方法的问题是如果一个线程已经开始了compute计算，而另外一个
 *  线程并不知道此值正在计算中，于是又重新计算一遍，出现了重复计算的问题。
 *
 */
public class Memoizer2<A,V> implements Computable<A,V> {
    private final ConcurrentHashMap<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;
	public Memoizer2(Computable<A, V> c) {
		this.c = c;
	}
	@Override
	public V compute(A arg)throws Exception  {
		V value = cache.get(arg);
		if (value == null) {
			value = c.compute(arg);
			cache.put(arg, value);
		}
		return value;
	}

}
