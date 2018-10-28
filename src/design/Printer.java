/**
 * 
 */
package design;

/**
 * @author Administrator
 *
 * 
 */
public class Printer implements Printable{
    private String name;
    
	public Printer(String name) {
		this.name = name;
		heavyJob();
	}
	public Printer() {
		heavyJob();
	}

	/* (non-Javadoc)
	 * @see design.Printable#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see design.Printable#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/* (non-Javadoc)
	 * @see design.Printable#print()
	 */
	@Override
	public void print(String name) {
		// TODO Auto-generated method stub
		System.out.println("my name is " + this.name);;
	}
	private void heavyJob(){
		;
	}

}
