/**
 * 
 */
package SyncTools;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 使用Semaphore实现有界集合
 * 集合的大小就信号量的大小
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private Semaphore sem;
    public BoundedHashSet(int bound) {
    	set = Collections.synchronizedSet(new HashSet<>(bound));
    	sem = new Semaphore(bound);//bound就是集合的大小
    }
    public boolean add(T o) throws InterruptedException {
    	boolean isAdded = false;
		if(sem.tryAcquire(2000, TimeUnit.MILLISECONDS)){
			try {
				isAdded = set.add(o);
				System.out.println(o.toString());
				return isAdded;
			} finally {
				if (!isAdded) {
					sem.release();
				}
			}
		}
		return isAdded;
	}
    public boolean remove(Object o) {
    	boolean removed = set.remove(o);	
		if (removed) {
			sem.release();
		}
		return removed;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BoundedHashSet<Integer> bh = new BoundedHashSet<>(3);
		for(int i=1;i<5;i++){
			try {
				if (!bh.add(i)) {
					System.out.println(i + " was not added.");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		System.out.println(bh.set.size());
	}

}
