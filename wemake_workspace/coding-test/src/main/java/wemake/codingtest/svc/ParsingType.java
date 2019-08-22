package wemake.codingtest.svc;

public enum ParsingType {
	WITHOUT_TAG("WITHOUT_TAG"),FULL_TEXT("FULLTEXT");
	
	private String type;
	private ParsingType(String type) {
		this.type = type;
	}
	
	public String getTypeString() {
		return type;
	}
}
