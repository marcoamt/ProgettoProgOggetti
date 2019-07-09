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
	
	/**
	 * 
	 * @param category is the category about products
	 * @param sectorCode is the code about sector of products
	 * @param productCode is the code of products
	 * @param desc is the description of products
	 * @param briefDesc is a brief description of products
	 * @param unit is the unite of measure of products
	 * @param country is the country of products coming
	 * @param period is a field of the dataset 
	 * @param marketPrice is the price of products at market
	 */
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

	/**
	 * 
	 * @return the description of products
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 
	 * @param desc is the description of products
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 
	 * @return a brief description of products
	 */
	public String getBriefDesc() {
		return briefDesc;
	}

	/**
	 * 
	 * @param briefDesc is a brief description of products
	 */
	public void setBriefDesc(String briefDesc) {
		this.briefDesc = briefDesc;
	}

	/**
	 * 
	 * @return the unit of measure of products
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 
	 * @param unit is the unit of measure of products
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * 
	 * @return the price of products at market 
	 */
	public double getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 
	 * @param marketPrice is the price of products at market
	 */
	public void setMarketPrice(double marketPrice) {
		this.marketPrice = marketPrice;
	}

	/**
	 * 
	 * @return the category of products
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 
	 * @return the sector code of products
	 */
	public String getSectorCode() {
		return sectorCode;
	}

	/**
	 * 
	 * @return the product code of products
	 */
	public int getProductCode() {
		return productCode;
	}

	/**
	 * 
	 * @return the country of products 
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @return the expiry period of products
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * 
	 * @param productCode is the code of products
	 */
	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}
	

}
