package product;

/**
 * 
 * Class that represents each row of the dataset, this extends from ProductAbs
 *
 */

public class Prodotti extends ProductAbs{

	private String sectorCode;
	private String briefDesc;
	private String unit;
	private String country;
	private int period;
	
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
		super(category, productCode, desc, marketPrice);
		this.sectorCode = sectorCode;
		this.briefDesc = briefDesc;
		this.unit = unit;
		this.country = country;
		this.period = period;
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
	 * @return the sector code of products
	 */
	public String getSectorCode() {
		return sectorCode;
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


}
