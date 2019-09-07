package views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Task implements Callable{

	Url url;
	List<SearchKeyword> searchKeywords;
	List<TargetResult> targetResults = new ArrayList<>();
	
	public Task(Url url, List<SearchKeyword> searchKeywords) {
		super();
		this.url = url;
		this.searchKeywords = searchKeywords;
	}
	
	@Override
	public Object call() throws Exception {
//				System.out.println("testStart:");
//				System.out.println(this.url.url);
				
				Document doc = null;
				try {
					doc = Jsoup.connect(this.url.url).get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String text = doc.body().text();
				getWord(text.toLowerCase());
		
		return this.targetResults;
	}
	
	private void getWord(String text) {
		 int count = 0;
		
		for (int i = 0; i < this.searchKeywords.size(); i++) {
			String searchkeyword = this.searchKeywords.get(i).getKeyword();
			Pattern p = Pattern.compile("\\b" + searchkeyword.toLowerCase() + "\\b"); 
			
			Matcher m1 = p.matcher(text.toLowerCase());
			count =0;
			    while (m1.find()) { 
			        count++;
			    }
			    System.out.println("TARGET SOURCE: " + this.url.url +" SEARCH KEYWORD: "+ searchkeyword +" FOUND: "+ count ); 
			    
			    this.targetResults.add(new TargetResult(this.url, searchkeyword, count ));
			    this.searchKeywords.get(i).addCount(count);
		}
		 
	}

}
