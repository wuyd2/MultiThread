/**
 * 
 */
package Jdk8NewFeatcher;

/**
 * @author Administrator
 *
 * 
 */
public interface calculate {
  public int calcute();
  default public double sqrt(int value){
	  return Math.sqrt(value);
  }
}
