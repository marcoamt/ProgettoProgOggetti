package conteggio;

public class Conteggio {

	private String element;
	private int count;
	
	/**
	 * 
	 * @param element string element that the user wants to count
	 * @param count number of elements in the dataset
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
	 * @return number of an element
	 */
	public int getCount() {
		return count;
	}

}
