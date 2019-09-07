package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import views.SearchKeyword;

public class DbConnect {
	public static String url = "jdbc:sqlite:data.db";
	DbConnect(){};
	
	public static void connect() {
        Connection conn = null;
        try {
            // db parameters
//            String url = "jdbc:sqlite:data.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            //System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
	
	 public static void createNewDatabase(String fileName) {
	        try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	               // System.out.println("The driver name is " + meta.getDriverName());
	               // System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public static void createNewTable() {
	        // SQLite connection string
	       // String url = "jdbc:sqlite:data.db";
	        // SQL statement for creating a new table
	        String sqlsearch = "CREATE TABLE IF NOT EXISTS search_h (\n"
	                + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
	                + "	timestamp text NOT NULL\n"
	                + ");";
	        
	        String sqltarget_results = "CREATE TABLE IF NOT EXISTS target_results (\n"
	        		+ "	id integer PRIMARY KEY AUTOINCREMENT,\n"
	                + "	sid integer NOT NULL,\n"
	                + "	url text NOT NULL,\n"
	                + "	keyword text NOT NULL,\n"
	                + "	count integer NOT NULL,\n"
	                + "	timestamp text NOT NULL\n"
	                + ");";
	        
	        String sqlskey = "CREATE TABLE IF NOT EXISTS search_keywords (\n"
	        		+ "	id integer PRIMARY KEY AUTOINCREMENT,\n"
	                + "	sid integer NOT NULL,\n"
	                + "	keyword text NOT NULL,\n"
	                + "	countall int NOT NULL,\n"
	                + "	timestamp text NOT NULL\n"
	                + ");";
	        
	        try (Connection conn = DriverManager.getConnection(url);
	             Statement stmt = conn.createStatement()) {
	            // create a new table
	            stmt.execute(sqlsearch);
	            stmt.execute(sqltarget_results);
	            stmt.execute(sqlskey);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	 
	 public static void printAllSearchKeywords(){
	        String sql = "SELECT DISTINCT keyword FROM search_keywords";
	        try (Connection conn = DriverManager.getConnection(url);
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            // loop through the result set
	        	int i =1;
	            while (rs.next()) {
	            	System.out.print(rs.getString("keyword") +" || ");
	            	i++;
	            	if(i==5)
	            		i = 1;
	            	System.out.print("\n");
	            	}
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        System.out.print("\n");
	        
	 }
	 
	    public static List<SearchKeyword> selectAllSearchKeywords(){
	    	List<SearchKeyword> queryResult = new ArrayList<>();
	        String sql = "SELECT * FROM search_keywords";
	        try (Connection conn = DriverManager.getConnection(url);
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            // loop through the result set
	            while (rs.next()) {
//	            	System.out.print(rs.getString("keyword") +" || ");
	            	queryResult.add(new SearchKeyword(rs.getString("keyword"),rs.getInt("countall"),rs.getString("timestamp"),rs.getInt("sid")));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        return queryResult;
	    }
	    
	    public static Connection getConnection() throws SQLException {
	    	String url = "jdbc:sqlite:data.db";
	        Connection conn = null;
	            conn = DriverManager.getConnection(url);
	        //System.out.println("Connected to database");
	        return conn;
	    }
	    
	    

}
