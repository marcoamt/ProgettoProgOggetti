package product;

public class Metadati {
	
	private String alias;
	private String sourceFields;
	private String type;
	
	public Metadati(String alias, String sourceFields, String type) {
		this.alias = alias;
		this.sourceFields = sourceFields;
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public String getSourceFields() {
		return sourceFields;
	}

	public String getType() {
		return type;
	}
	
	
	
	

}
