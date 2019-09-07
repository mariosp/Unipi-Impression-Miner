package views;

import java.util.ArrayList;
import java.util.List;

public class TargetResult {
	Url targetUrl;
	String searchKeyword;
	int times = 0;
	
	public TargetResult(){
		
	}

	public TargetResult(Url targetUrl, String searchKeyword, int times) {
		super();
		this.targetUrl = targetUrl;
		this.searchKeyword = searchKeyword;
		this.times = times;
	}


	public Url getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(Url targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "TargetResult [targetUrl=" + targetUrl + ", searchKeyword=" + searchKeyword + ", times=" + times + "]";
	}
	
	public void insertToDb() {
		
	}
	
	

}
