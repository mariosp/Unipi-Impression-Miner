package views;

public class SearchKeyword {
	String keyword;
	int count = 0;
	String timestamp;
	int sid;
	
	public SearchKeyword(String keyword) {
		super();
		this.keyword =keyword;
		this.count =0;
	}
	public SearchKeyword(String keyword,int count,String timestamp, int sid) {
		super();
		this.keyword =keyword;
		this.count = count;
		this.timestamp = timestamp;
		this.sid = sid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void addCount(int count) {
		this.count += count;
	}
	
}
