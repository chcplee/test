package wemake.codingtest.api;

public class RequestTestDto {
	private String url;
	private String option;
	private int groupCount;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public int getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("url : " + url);
		buffer.append(", option : " + option);
		buffer.append(", groupCount : " + groupCount);
		
		return buffer.toString();
	}
}
