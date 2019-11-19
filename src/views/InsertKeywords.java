package views;

import util.UserInputs;
import java.util.*;

public class InsertKeywords {
	private List<SearchKeyword> searchKeywords = new ArrayList<SearchKeyword>();
	UserInputs inputs = new UserInputs();


	public void showMenu() {
		System.out.println("--- Insert search keywords---");
		System.out.println("Type the number of search keywords :");

		int userInput = inputs.onInputNumber();
		if (userInput != 0)
			System.out.println("Enter " + userInput + " search keywords :");

		String s = "";
		for(int i=0;i<userInput;i++) {
			s = inputs.onInputString();
			searchKeywords.add(new SearchKeyword(s));
		}
		
		
	}
	
	public List<SearchKeyword> getSearchKeywords() {
		return searchKeywords;
	}
	

}
