package product;

public class Item {
	
	private String field;
	private double avg;
	private double min;
	private double max;
	private double std;
	private double sum;
	private double count;
	
	public Item(String field, double avg, double min, double max, double std, double sum, double count) {
		this.field = field;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.std = std;
		this.sum = sum;
		this.count = count;
	}

	public String getField() {
		return field;
	}

	public double getAvg() {
		return avg;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

	public double getStd() {
		return std;
	}

	public double getSum() {
		return sum;
	}

	public double getCount() {
		return count;
	}
	
	
	
	
}
