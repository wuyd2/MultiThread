/**
 * 
 */
package VolatileTest;

/**
 * @author Administrator
 * 线程池接口
 * 
 */
public interface ThreadPool<Job extends Runnable>{
	//execute a job which implement Runnable
	void execute(Job job);
	//关闭线程池
	void shutdown();
	//增加工作者线程
	void addWorkers(int num);
	//减少工作者线程
	void removeWorker(int num);
	//获取队列中任务个数
	int getJobSize();
}
