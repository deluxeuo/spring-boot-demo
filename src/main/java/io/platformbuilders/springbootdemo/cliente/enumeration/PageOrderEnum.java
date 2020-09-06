package io.platformbuilders.springbootdemo.cliente.enumeration;

public enum PageOrderEnum {
	
	ASC("ASC"), DESC("DESC");

	private String value;
	
	private PageOrderEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static PageOrderEnum getSortDirection(String value){
		if(ASC.getValue().equals(value)) {
			return ASC;
		}
		return DESC;
	}
}