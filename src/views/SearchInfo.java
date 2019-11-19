package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import util.DbConnect;
import util.UserInputs;

public class SearchInfo {
	UserInputs inputs = new UserInputs();
	public void showOptionMenu() {
		System.out.println("--- Search Info Menu ---");
		System.out.println("Press one option :");
		System.out.println("1. Show search history");
		System.out.println("2. Repeat search");
		try {
			int userInput = inputs.onInputNumber();
			selectedOption(userInput);
		} catch (InterruptedException e) {

			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

private void selectedOption(int option) throws InterruptedException, ExecutionException {
	
	switch(option) {
	case 1:
		selectSearchHistoryAndKeywords();
		System.out.println("Press the id of the search to view the results on the search or 0 to exit");
		int inputId = inputs.onInputNumber();
		showResult(inputId);
	break;
	case 2:
		selectSearchHistoryAndKeywords();
		System.out.println("Press the id of the search to repeat the impression miner or 0 to exit");
		int selectedId = inputs.onInputNumber();
		ImpressionMiner im = new ImpressionMiner(searchKeywordsById(selectedId));
		im.setTasks();
	break;
	}
}

private void showResult(int id) {
	
	String sql = "SELECT * FROM target_results WHERE sid='"+id+"'";
    try (Connection conn = DbConnect.getConnection();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        // loop through the result set
        while (rs.next()) {
        	System.out.println("TARGET SOURCE: " + rs.getString("url") +" SEARCH KEYWORD: "+ rs.getString("keyword") +" FOUND: "+ rs.getInt("count") );
        	
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
	
	
}

public void selectSearchHistoryAndKeywords(){
    String sql = "SELECT * FROM search_h";
 
    try (Connection conn = DbConnect.getConnection();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        // loop through the result set
        while (rs.next()) {
        	List<SearchKeyword> searchkeyword = searchKeywordsById(rs.getInt("id"));
	       	System.out.print(rs.getInt("id") +" date: " + rs.getString("timestamp") +" || ");
	       	for(SearchKeyword sh:searchkeyword) {
	       		System.out.print(sh.keyword +" || ");
	       	}	
	       	System.out.println();
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

    
}

private List<SearchKeyword> searchKeywordsById(int id) {
	List<SearchKeyword> queryResult = new ArrayList<>();
	String sql = "SELECT keyword FROM search_keywords WHERE sid='"+id+"'";
    try (Connection conn = DbConnect.getConnection();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        // loop through the result set
        while (rs.next()) {
        	queryResult.add(new SearchKeyword(rs.getString("keyword")));
        	
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
    return queryResult;
	
}

//public List<SearchKeyword> selectHistorySearchKeywords(int id){
//	List<SearchKeyword> queryResult = new ArrayList<>();
//    String sql = "SELECT * FROM search_keywords WHERE sid='"+id+"'";
//    try (Connection conn =  DbConnect.getConnection();
//         Statement stmt  = conn.createStatement();
//         ResultSet rs    = stmt.executeQuery(sql)){
//        // loop through the result set
//        while (rs.next()) {
//        	queryResult.add(new SearchKeyword(rs.getString("keyword")));
//        }
//    } catch (SQLException e) {
//        System.out.println(e.getMessage());
//    }
//    
//    return queryResult;
//}


}