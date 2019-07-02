package product;

public class Prodotti {
	
	private String category;
	private String sectorCode;
	private int productCode;
	private String desc;
	private String briefDesc;
	private String unit;
	private String country;
	private int period;
	private double marketPrice;
	
	public Prodotti(String category, String sectorCode, int productCode, String desc, String briefDesc, String unit,
			String country, int period, double marketPrice) {
		this.category = category;
		this.sectorCode = sectorCode;
		this.productCode = productCode;
		this.desc = desc;
		this.briefDesc = briefDesc;
		this.unit = unit;
		this.country = country;
		this.period = period;
		this.marketPrice = marketPrice;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBriefDesc() {
		return briefDesc;
	}

	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getCategory() {
		return category;
	}

	public String getSectorCode() {
		return sectorCode;
	}

	public int getProductCode() {
		return productCode;
	}

	public String getCountry() {
		return country;
	}

	public int getPeriod() {
		return period;
	}
	

}
