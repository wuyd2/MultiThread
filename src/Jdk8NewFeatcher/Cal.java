/**
 * 
 */
package Jdk8NewFeatcher;

/**
 * @author Administrator
 *
 * 
 */
public class Cal implements calculate {

	/* (non-Javadoc)
	 * @see Jdk8NewFeatcher.calculate#calcute()
	 */
	@Override
	public int calcute() {
		int i = 3 + 3;
		return i;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Cal cal = new Cal();
		System.out.println(cal.sqrt(16));
	}

}
