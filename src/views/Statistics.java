package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import util.DbConnect;

public class Statistics {

	
	public void showOptionMenu() {
		System.out.println("--- Statistics Menu ---");
		System.out.println("Press one option :");
		System.out.println("1. Show Keyword timeline");
		try {
			readOption();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
private void selectedOption(char read) throws InterruptedException, ExecutionException {
		
		switch(read) {
		case '1':
			DbConnect.printAllSearchKeywords();
			List<SearchKeyword> searchKeywordAll = DbConnect.selectAllSearchKeywords();
			if(!searchKeywordAll.isEmpty()) {
				System.out.println("Type the word you want to show search timeline:");
				String s = readLine();
				
				List<SearchKeyword> newSk = findKeyword(searchKeywordAll,s);
				if(!newSk.isEmpty()) {
					System.out.println("Timeline for keyword : " + newSk.get(0).keyword);
					for(SearchKeyword sk:newSk) {
						System.out.println(sk.timestamp + " -> search keyword found: " + sk.count);
					}
				}else {
					
				}
				
			
			}else {
				System.out.println("No search keywords in Database! Please make a new search");
			}
		
			break;
		}
		
	}


private List<SearchKeyword> findKeyword(List<SearchKeyword> searchKeywordAll,String keyword) {
	List<SearchKeyword> newSk = new ArrayList<>();
	for(SearchKeyword sk:searchKeywordAll) {
		if(sk.keyword.equalsIgnoreCase(keyword)) {
			newSk.add(sk);
		}
	}
	
	System.out.println(newSk.size());
	Collections.reverse(newSk);
	return newSk;
	
}

private String readLine() {
	String s = null;
	BufferedReader readSearch = new BufferedReader(new InputStreamReader(System.in));
	try {
		s = readSearch.readLine();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return s;
}
	
private void readOption() throws InterruptedException, ExecutionException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try{
			int read = in.read();
			while(read<49 || read>51) {
				System.out.println(read);
				read = in.read();
				
			}
			
			selectedOption((char) read);
            
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
