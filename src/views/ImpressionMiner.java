package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import util.DbConnect;;

public class ImpressionMiner {
	List<Url> urlList;
	List<SearchKeyword> searchKeywords;
	List<TargetResult> targetResults;
	List<Integer> sCount = new ArrayList<>();
	int search_id;
	
	public ImpressionMiner(List<SearchKeyword> searchKeywords) {
		this.searchKeywords = searchKeywords;
		TargetUrls targetUrls = new TargetUrls();
		urlList = targetUrls.getList();
	}
	
	
	public void setTasks() throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Set<Callable<TargetResult>> callables = new HashSet<Callable<TargetResult>>();
		
		for(int i=0;i<urlList.size();i++) {
			callables.add(new Task(urlList.get(i), this.searchKeywords));
		}
		
		List<Future<TargetResult>> futures = executorService.invokeAll(callables);
		
		System.out.println("-----------SEARCH FINISHED----------");
		System.out.println("Results : ");
		for(int i=0;i<this.searchKeywords.size();i++){
		System.out.println(this.searchKeywords.get(i).getKeyword() +"  FOUND: " + this.searchKeywords.get(i).getCount());
		}
		
		
		String timestamp = getCurrentTime();
		try {
			insertSearchToDb(timestamp);
			for(SearchKeyword keyword:this.searchKeywords) {
			insertKeywordsToDb(timestamp,keyword);
			}
			
			for(Future<TargetResult> future : futures){
				this.targetResults = (List<TargetResult>) future.get();
				for(TargetResult result:this.targetResults) {
					insertTargetResultToDb(timestamp,result);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		pressAnyKeyToContinue();
	}
	
	private void pressAnyKeyToContinue()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }


	private void insertTargetResultToDb(String timestamp, TargetResult result) throws SQLException {
		String sqlinsert = "insert into target_results (sid,url,keyword,count,timestamp) values(?,?,?,?,?)";
		
		try {
	        Connection conn = DbConnect.getConnection();
	        PreparedStatement statement = conn.prepareStatement(sqlinsert,
	                Statement.RETURN_GENERATED_KEYS);
	        statement.setInt(1, search_id );
	        statement.setString(2, result.getTargetUrl().url);
	        statement.setString(3, result.getSearchKeyword());
	        statement.setInt(4, result.getTimes() );
	        statement.setString(5, timestamp);
	        int i=statement.executeUpdate();
	        }catch(Exception e)
	        { 
	        	System.out.println(e);
	    	} 
		
	}


	private void insertSearchToDb(String timestamp) throws SQLException {
		
		 String sqlinsert = "insert into search_h (timestamp) values(?)";
        
        try {
        Connection conn = DbConnect.getConnection();
        PreparedStatement statement = conn.prepareStatement(sqlinsert,
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, timestamp);
        int i=statement.executeUpdate();
        
        ResultSet generatedKey = statement.getGeneratedKeys();
        if (generatedKey.next()) {
            search_id = generatedKey.getInt(1);
        }
        else {
            throw new SQLException("no ID obtained.");
        }
        
        }catch(Exception e)
        { 
        	System.out.println(e);
    	} 
        
        
	}
	
private void insertKeywordsToDb(String timestamp, SearchKeyword keyword) throws SQLException {
		
        String sqlinsert = "insert into search_keywords (sid,keyword,countall,timestamp) values(?,?,?,?)";
        
        try {
        Connection conn = DbConnect.getConnection();
        PreparedStatement statement = conn.prepareStatement(sqlinsert,
                Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, search_id );
        statement.setString(2, keyword.getKeyword());
        statement.setInt(3, keyword.getCount() );
        statement.setString(4, timestamp);
        int i=statement.executeUpdate();

        }catch(Exception e)
        { 
        	System.out.println(e);
    	} 
        
        
	}
	
	private String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    String ts = sdf.format(timestamp);
	   // System.out.println(ts);
	    return ts;
	}
}
