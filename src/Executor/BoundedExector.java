/**
 * 
 */
package Executor;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * @author Administrator
 * 使用Semaphere来控制任务的提交速率
 * 
 */
public class BoundedExector {
	private Executor executor;
	private Semaphore semaphore;

	public BoundedExector(Executor executor, int bound) {
		this.executor = executor;
		this.semaphore = new Semaphore(bound);
	}

	public void submitTask(final Runnable command) throws InterruptedException{
	   semaphore.acquire();
	   try{
		   executor.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					command.run();	
				} finally {
   				   semaphore.release();		   
				}
			}
		});
	   }catch(RejectedExecutionException e){
		   semaphore.release();
	   }
	   
   }
}
