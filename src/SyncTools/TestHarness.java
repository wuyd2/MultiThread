/**
 * 
 */
package SyncTools;

import java.util.concurrent.CountDownLatch;

/**
 * @author Administrator
 * 同步工具类:闭锁，相当于是一个门，未到达终止状态前此门是关着的，线程不能通过
 * 1、确保所有计算资源都被初始化后才继续进行
 * 2、确保依赖的其它服务都已启动才能启动此服务
 * 3、等待操作参与者就绪，例如所有游戏玩家都就绪
 * 本例使用闭锁工具类CountDownLatch计算n个线程的执行时间 
 */
public class TestHarness {
   //使用CountDownLatch计算N个线程的执行时间总和
   public long timeTasks(int nthreads,final Runnable task){
	   final CountDownLatch startGate = new CountDownLatch(1);
	   final CountDownLatch endGate = new CountDownLatch(nthreads);
	   for(int i=0;i<nthreads;i++){
		   Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					startGate.await();//闭锁,阻塞等待startGate降为0才能继续执行
					task.run();
				   } catch (InterruptedException e) {
					   e.printStackTrace();
				   } finally {
					   endGate.countDown();
				   }
			}
		   });
		  thread.start(); 
	  }
	  long start = System.nanoTime();
	  startGate.countDown();//打开闭锁,允许线程执行
	  try {
		endGate.await();//阻塞直到endGate等于0
		long end = System.nanoTime();
		return end - start;
	} catch (InterruptedException e) {
		e.printStackTrace();
		return 0L;
	}
   }
   public static void main(String []args){
	  Runnable runnable = new Runnable() {
		public void run() {
		    System.out.println(Thread.currentThread().getName() + " running.");	
		}
	};
	System.out.println(new TestHarness().timeTasks(3, runnable));
   }
}
