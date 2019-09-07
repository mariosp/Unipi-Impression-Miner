package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InputMenu {
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private BufferedReader readSearch = new BufferedReader(new InputStreamReader(System.in));
	private char readNum;
	private List<SearchKeyword> searchKeywords = new ArrayList<SearchKeyword>();
	
	
	public InputMenu() {};
	
	public void showMenu() {
		System.out.println("--- Insert search keywords---");
		System.out.println("Type the number of search keywords :");
		
		readNum  = readNumber();
		
		System.out.println("Enter " + readNum + " search keywords :");
		
		
		String s = null;
		for(int i=0;i<Character.getNumericValue(readNum);i++) {
			try {
				s = readSearch.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			searchKeywords.add(new SearchKeyword(s));
			
		}
		
		
	}
	
	
	
	
	private char readNumber() {
		int read = 0;
		try{
			read = in.read();
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (char) read;
		
	}
	
	
	private void printList() {
	
		System.out.println(searchKeywords);
		
	}
	
	public List<SearchKeyword> getSearchKeywords() {
		return searchKeywords;
	}
	

}
