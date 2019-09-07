package views;

import java.util.ArrayList;
import java.util.List;

public class TargetUrls {
	String[] urls = {
			"https://medium.com/",
			"https://edition.cnn.com/"
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
