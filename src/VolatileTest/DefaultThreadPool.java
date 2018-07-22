/**
 * 
 */
package VolatileTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Administrator
 *
 * 
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    private int workNum = DEFAULT_WORKER_NUMBERS;
	private final LinkedList<Job> jobs = new LinkedList<Job>();
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	private  AtomicLong threadNum = new AtomicLong();
	public DefaultThreadPool() {
       initializeWorkers(DEFAULT_WORKER_NUMBERS);
	}
	public DefaultThreadPool(int num){
		workNum = num>MAX_WORKER_NUMBERS?MAX_WORKER_NUMBERS:num<MIN_WORKER_NUMBERS?MIN_WORKER_NUMBERS:num;
		initializeWorkers(workNum);
	}
	private void initializeWorkers(int num){
		for (int i=0;i<num;i++){
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker,"ThreadPool-worker-" + threadNum.incrementAndGet());
			thread.start();
			
		}
	}
	@Override
	public void execute(Job job) {
		if (job != null) {
			synchronized (jobs) {
				jobs.addLast(job);
				jobs.notify();
			}
		}
	}

	/* (non-Javadoc)
	 * @see VolatileTest.ThreadPool#shutdown()
	 */
	@Override
	public void shutdown() {
		for(Worker worker :workers){
			worker.shutdown();
		}
	}

	/* (non-Javadoc)
	 * @see VolatileTest.ThreadPool#addWorkers(int)
	 */
	@Override
	public void addWorkers(int num) {
		synchronized (jobs) {//为什么 要对jobs加锁？
			if (num + workNum > MAX_WORKER_NUMBERS) {
				num = MAX_WORKER_NUMBERS -workNum;
			}
			initializeWorkers(num);
			this.workNum += num;
		}
	}
	@Override
	public void removeWorker(int num) {
		synchronized (jobs) {
			if(workNum-num<MIN_WORKER_NUMBERS){
				throw new IllegalArgumentException("Beyond workNum.");
			}
			int count = 0;
			while(count < num){
				Worker worker = workers.get(count);
				if (workers.remove(worker)) {
					worker.shutdown();
					count++;
				}
			}
			this.workNum -= count;
		}
		
	}
	@Override
	public int getJobSize() { //不对Jobs加锁不会有多线程问题吗？
		// TODO Auto-generated method stub
		return jobs.size();
	}
	//消费者，当jobs为空时，在Jobs等待，不为空时取出一个Job执行
	class Worker implements Runnable {
		private volatile boolean running = true;
		@Override
		public void run() {
			while (running) {
				synchronized (jobs) {
					while (jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (Exception e) {
							Thread.currentThread().interrupt();
							return;
						}
					}
				}
				Job job = jobs.removeFirst();
				if (job != null) {
					try {
						job.run();
					} catch (Exception e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
			}
		}
		public void shutdown(){
			running = false;
		}
	}
}
