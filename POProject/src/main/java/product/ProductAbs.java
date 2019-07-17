package product;
/**
 * 
 * Abstract class that includes the common attributes of products with their get/set methods
 *
 */
public abstract class ProductAbs {
	private String category;
	private int productCode;
	private String desc;
	private double marketPrice;
	
	public ProductAbs(String category, int productCode, String desc, double marketPrice) {
		this.category = category;
		this.productCode = productCode;
		this.desc = desc;
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
	 * @return the product code of products
	 */
	public int getProductCode() {
		return productCode;
	}


	/**
	 * 
	 * @return desc is the description of products
	 */
	public String getDesc() {
		return desc;
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
	

}
