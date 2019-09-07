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

public class SearchInfo {

	public void showOptionMenu() {
		System.out.println("--- Search Info Menu ---");
		System.out.println("Press one option :");
		System.out.println("1. Show search history");
		System.out.println("2. Repeat search");
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
		int lastid = selectSearchHistoryAndKeywords();
		System.out.println("Press the id of the search to view the results on the search or 0 to exit");
		String s = readLine();
		System.out.println(s);
		System.out.println(lastid);
		if(s.equals(String.valueOf(lastid))) {
			System.out.println("mbika");
			showResult(lastid);
		}
	break;
	case '2':
		int lastid2 = selectSearchHistoryAndKeywords();
		System.out.println("Press the id of the search to repeat the impression miner or 0 to exit");
		String t = readLine();
		if(t.equals(String.valueOf(lastid2))) {
			ImpressionMiner im = new ImpressionMiner(searchKeywordsById(lastid2));
			im.setTasks();
		}
		
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

public int selectSearchHistoryAndKeywords(){
	int lastid = 0;
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
	       	
	       	lastid = rs.getInt("id");
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    
    return lastid;
    
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