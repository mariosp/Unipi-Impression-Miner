package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class InsertKeywords {
	private BufferedReader readSearch = new BufferedReader(new InputStreamReader(System.in));
	private int readNum;
	private List<SearchKeyword> searchKeywords = new ArrayList<SearchKeyword>();

	
	public void showMenu() {
		System.out.println("--- Insert search keywords---");
		System.out.println("Type the number of search keywords :");

		readNum  = readNumber();

		if (readNum != 0)
			System.out.println("Enter " + readNum + " search keywords :");


		String s = null;
		for(int i=0;i<readNum;i++) {
			try {
				s = readSearch.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			searchKeywords.add(new SearchKeyword(s));

		}
		
		
	}

	private int readNumber() {
		// Declare the variables
		int number=0;
		try
		{
			// Create Scanner object
			Scanner s = new Scanner(System.in);

			// Read the next integer from the screen
			number = s.nextInt();

		}
		catch(InputMismatchException e)
		{
			// accept integer only.
			System.out.println("--- Invalid Input. Please Input a number ---");
			showMenu();
		}

		return number;
	}
	
	
	private void printList() {
	
		System.out.println(searchKeywords);
		
	}
	
	public List<SearchKeyword> getSearchKeywords() {
		return searchKeywords;
	}
	

}
