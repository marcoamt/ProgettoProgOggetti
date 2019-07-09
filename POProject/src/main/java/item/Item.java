package item;

public class Item {

	private String field;
	private double avg;
	private double min;
	private double max;
	private double std;
	private double sum;
	private int count;
	
	/**
	 * 
	 * @param field is the param of filter
	 * @param avg is the average
	 * @param min is the minimum
	 * @param max is the maximum
	 * @param std 
	 * @param sum is the sum of element
	 * @param count 
	 */
	public Item(String field, double avg, double min, double max, double std, double sum, int count) {
		this.field = field;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.std = std;
		this.sum = sum;
		this.count = count;
	}
	
	/**
	 * 
	 * @return the field 
	 */
	public String getField() {
		return field;
	}

	/**
	 * 
	 * @return the average
	 */
	public double getAvg() {
		return avg;
	}

	/**
	 * 
	 * @return the minimum
	 */
	public double getMin() {
		return min;
	}

	/**
	 * 
	 * @return the maximum
	 */
	public double getMax() {
		return max;
	}

	/**
	 * 
	 * @return the std
	 */
	public double getStd() {
		return std;
	}

	/**
	 * 
	 * @return the std
	 */
	public double getSum() {
		return sum;
	}

	/**
	 * 
	 * @return the count of element
	 */
	public int getCount() {
		return count;
	}
	
}
