package metadati;

public class Metadati {
	
	private String alias;
	private String sourceFields;
	private String type;
	
	/**
	 * 
	 * @param alias is the name of field provided by us
	 * @param sourceFields is the name of field
	 * @param type is the type of field
	 */
	public Metadati(String alias, String sourceFields, String type) {
		this.alias = alias;
		this.sourceFields = sourceFields;
		this.type = type;
	}
	
	/**
	 * 
	 * @return the name of field
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * 
	 * @return the description of field
	 */
	public String getSourceFields() {
		return sourceFields;
	}

	/**
	 * 
	 * @return the type of field
	 */
	public String getType() {
		return type;
	}
	
	
	
	

}
