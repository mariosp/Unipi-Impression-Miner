package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import util.DbConnect;
import util.UserInputs;

public class Statistics {
	UserInputs inputs = new UserInputs();
	
	public void showOptionMenu() {
		System.out.println("--- Statistics Menu ---");
		System.out.println("Press one option :");
		System.out.println("1. Show Keyword timeline");

		int userInput = inputs.onInputNumber();
		selectedOption(userInput);

	}
	
private void selectedOption(int option) {
		
		switch(option) {
		case 1:
			DbConnect.printAllSearchKeywords();
			List<SearchKeyword> searchKeywordAll = DbConnect.selectAllSearchKeywords();
			if(!searchKeywordAll.isEmpty()) {
				System.out.println("Type the word you want to show search timeline:");
				String s = inputs.onInputString();
				
				List<SearchKeyword> newSk = findKeyword(searchKeywordAll,s);
				if(!newSk.isEmpty()) {
					System.out.println("Timeline for keyword : " + newSk.get(0).keyword);
					for(SearchKeyword sk:newSk) {
						System.out.println(sk.timestamp + " -> search keyword found: " + sk.count);
					}
				} else {
					System.out.println("Search keywords not found. Please try again");
					showOptionMenu();
				}
			} else {
				System.out.println("No search keywords in Database! Please make a new search");
			}
		
			break;
			default:
				System.out.println("Please Try Again. With a correct option.");
				showOptionMenu();
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
	
}
