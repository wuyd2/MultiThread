/**
 * 
 */
package design;

/**
 * @author Administrator
 *
 * 
 */
public class PrinterProxy implements Printable {
    private String name;
    private Printer printer;
	/* (non-Javadoc)
	 * @see design.Printable#setName(java.lang.String)
	 */
	public synchronized void  setName(String name) {
		if (printer != null) {
			this.printer.setName(name);
		}else {
			this.name = name;
		}
	}
	

	/* (non-Javadoc)
	 * @see design.Printable#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if (printer!=null) {
			return this.printer.getName();
			
		}
		return this.name;
	}

	/* (non-Javadoc)
	 * @see design.Printable#print()
	 */
	@Override
	public void  print(String name ) {
		realized();
		printer.print(name);
	}
	private synchronized void realized(){
		if (printer == null) {
			printer = new Printer(name);
		}
	}
	public PrinterProxy(String name){
		this.name = name;
	}
	public PrinterProxy( ){
	}

}
