package VolatileTest;

public class VolatileTest {
    public static volatile int race = 0;
    private static final int THREAD_COUNT = 20;
    private static  void increase(){//synchronized
//    	synchronized (VolatileTest.class) {
    		race++;	
//		}
    }
	public static void main(String[] args) {
	   Thread threads[] = new Thread[THREAD_COUNT];
       for(int i=0;i<THREAD_COUNT;i++){
    	   threads[i] = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int j = 0; j < 1000; j++) {
					increase();					
				}
			}
		  });
       }
       for(int i=0;i<threads.length;i++){
    	   threads[i].start();
       }
       //等待线程结束后再往后执行
       while(Thread.activeCount() >1){
    	   Thread.yield();
       }
       System.out.println(race);
	}
}
