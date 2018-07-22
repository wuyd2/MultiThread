package VolatileTest;

import java.util.Random;
import java.util.logging.Logger;

public class RecorderExample extends Thread{
	private static final Logger logger = Logger.getLogger(RecorderExample.class.getName());
    int a = 0;
    boolean flag = false;
	@Override
	public void run() {
		long seed = 10;
		Random random = new Random(seed);
		try {
			Thread.currentThread().sleep(random.nextLong());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = true;
		if(flag){
			a  = 1;
			logger.info(String.valueOf(a));	
		}
	}
	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			new RecorderExample().start();
		}

	}

	

}
