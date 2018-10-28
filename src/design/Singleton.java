/**
 * 
 */
package design;

/**
 * @author Administrator
 * 单例迟加载
 * 
 */
public class Singleton {
  private static volatile Singleton singleton;
  private Singleton (){
	  
  }
  public static Singleton newInstance(){
	  if (singleton == null) {
		  synchronized(Singleton.class){
			  if (singleton != null) {
				  singleton = new Singleton();
			}
		  }
	  }
      return singleton;
  }
}
