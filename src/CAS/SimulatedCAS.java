/**
 * 
 */
package CAS;

/**
 * @author Administrator
 * 模拟CAS操作
 * 
 */
//@ThreadSafe
public class SimulatedCAS {
	private int value;
	public synchronized int get(){
		return value;
	}
	public synchronized int  compareAndSwap(int expectedValue,int newValue){
		int oldValue = value;
		if (value == expectedValue) {
			value = newValue;
		}
		return oldValue;
	}
	/***
	 * 
	 * @param expectedValue
	 * @param newValue
	 * @return
	 * 当value的值与expectedValue相等时，value被设置为newValue，方法返回true;
	 * 当value的值被其实线程改为非expectedValue时,不更新value的值，方法返回false，线程不会被阻塞
	 */
	public synchronized boolean compareAndSet(int expectedValue,int newValue){
		return (expectedValue==compareAndSwap(expectedValue, newValue));
	}

}
