package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import views.SearchKeyword;

/**
 * Connect To The SQLite Database
 */
public class DbConnect {
	// Path to the database file
	private static final String JDBC_URL = "jdbc:sqlite:data.db";

	public static void connect() {
        Connection connection = null;
        try {
            // create a connection to the database
			connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
					connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }

	/**
	 * Connect to the database file
	 * @return the Connection object
	 */
	public static Connection getConnection()  {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(JDBC_URL);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	/**
	 * Create new database
	 *
	 * @param fileName the database file name
	 */
	 public static void createNewDatabase(String fileName) {

		 String url = "jdbc:sqlite:" + fileName;

		 try (Connection conn = DriverManager.getConnection(url)) {
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("The driver name is " + meta.getDriverName());
	                System.out.println("A new database has been created.");
	            }

	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	/**
	 * Create a new table in our database
	 *
	 * @param fileName the database file name
	 */
	 public static void createNewTable(String fileName) {
	        // SQLite connection string
			 String url = "jdbc:sqlite:" + fileName;

	        // SQL statement for creating new tables
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
	            // create  new tables
	            stmt.execute(sqlsearch);
	            stmt.execute(sqltarget_results);
	            stmt.execute(sqlskey);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	/**
	 * print all rows in the search_keywords table
	 */
	 public static void printAllSearchKeywords(){
	        String sql = "SELECT DISTINCT keyword FROM search_keywords";

	        try (Connection conn = DriverManager.getConnection(JDBC_URL);
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){

	            // loop through the result set
	        	int i =1;
	            while (rs.next()) {
	            	System.out.print(rs.getString("keyword") +" || ");
	            	i++;
	            	if(i==5) {
	            		i = 1;
	            	System.out.print("\n");
	            	}
	            	}
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        System.out.print("\n");
	 }

	/**
	 * return all search_keywords
	 */
	    public static List<SearchKeyword> selectAllSearchKeywords(){
	    	List<SearchKeyword> queryResult = new ArrayList<>();

	        String sql = "SELECT * FROM search_keywords";

	        try (Connection conn = DriverManager.getConnection(JDBC_URL);
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	            // loop through the result set
	            while (rs.next()) {
					//System.out.print(rs.getString("keyword") +" || ");
	            	queryResult.add(new SearchKeyword(rs.getString("keyword"),rs.getInt("countall"),rs.getString("timestamp"),rs.getInt("sid")));
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        
	        return queryResult;
	    }


}
