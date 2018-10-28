/**
 * 
 */
package design;

/**
 * @author Administrator
 *
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Printable printerProxy = new PrinterProxy("Test");
		printerProxy.print("test");
		printerProxy.setName("Test2");
		printerProxy.print("Bob");
	}

}
