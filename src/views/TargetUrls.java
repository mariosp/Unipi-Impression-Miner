package views;

import java.util.ArrayList;
import java.util.List;

public class TargetUrls {
	String[] urls = {
			"https://www.theverge.com/tech",
			"https://www.bbc.com/news/technology",
			"https://www.cnet.com/news/",
			"https://www.gadgetsnow.com/tech-news",
			"https://www.wired.co.uk/topic/technology",
			"https://gadgets.ndtv.com/news",
			"https://thenextweb.com/",
			"https://gizmodo.com/",
			"https://www.techradar.com/",
			"https://mashable.com/?europe=true"
			
	};
	
	List<Url> urlList;
	
	public TargetUrls(){
		initList();
	}
	
	private void initList(){
		urlList = new ArrayList<Url>();
		for (int i=0; i<urls.length; i++) 
		{ 
			urlList.add(new Url(i,urls[i])); //create object Url and add it to list
		}
	}
	
	public List<Url> getList(){
		
		return urlList;
		
	}
}
