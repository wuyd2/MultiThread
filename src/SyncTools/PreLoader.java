/**
 * 
 */
package SyncTools;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask也可以用来做闭锁：只有当Callable计算完成后才能获取计算结果。
 * Callable可以抛出受检查、不受检查的异常，所以在调用FutureTask的get获取返回结果时要有完善的异常
 * 处理机制。
 * 
 * 
 */
public class PreLoader {
  private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
		@Override
		public ProductInfo call() throws Exception {
			return ProductInfo.loadProductInfo();
		}
   });
  private Thread thread = new Thread(future);
  public void start() {
	  thread.start();
  }
  public ProductInfo get() {
     try {
		ProductInfo productInfo = future.get();
		return productInfo;
     } catch (InterruptedException | ExecutionException e) {
		e.printStackTrace();
		return null;
	 }
  }
  public static void main(String []args){
	  new PreLoader().start();
  }
  
}

class ProductInfo{
	private static ProductInfo productInfo;
	private static synchronized ProductInfo newInstance() {
		if (productInfo == null) {
			productInfo = new ProductInfo();			
		}
		return productInfo;
	}
	public static ProductInfo loadProductInfo(){
		System.out.println("load ProductInfo.");
		return newInstance();
	}
}
