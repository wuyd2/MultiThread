/**
 * 
 */
package CAS;

/**
 * @author Administrator
 *
 * 
 */
public class CasCounter {
   private SimulatedCAS value;
   public int get(){
	   return value.get();
   }
   public int increment(){
	   /*第一种实现
	   int v;
	   do {
		 v = value.get();
	   } while (v != (value.compareAndSwap(v, v+1)));
	   return v+1;
       * */
	   //第二种实现
	   while(true){
		   int v = value.get();
		   if(value.compareAndSet(v, v+1)){
			   return v+1;
		   }
	   }
   }
   
}
