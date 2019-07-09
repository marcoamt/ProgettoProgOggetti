package product;

public class Conteggio {

	private String element;
	private int count;
	
	/**
	 * 
	 * @param element
	 * @param count 
	 */
	public Conteggio(String element, int count){
		this.element = element;
		this.count = count;
	}

	/**
	 * 
	 * @return the element
	 */
	public String getElement() {
		return element;
	}

	/**
	 * 
	 * @return count of element
	 */
	public int getCount() {
		return count;
	}

}
