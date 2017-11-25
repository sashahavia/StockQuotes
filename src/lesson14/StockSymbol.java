package lesson14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class StockSymbol{

	public static String getSymbolsFromDatabase(String symbol){
		final String DB_URL = "jdbc:derby://localhost:1527/HW3";
		String price = null;
		ResultSetMetaData rsMeta = null;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "SELECT Symbol from Stocks";
		boolean equal = false;
		try { 
			conn = DriverManager.getConnection(DB_URL);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			// Find out the number of columns, their names and display the data
			rsMeta = rs.getMetaData();
			int colCount = rsMeta.getColumnCount();
			while (rs.next()){ 
				for (int i = 1; i <= colCount; i++)  {
					if(symbol.equals(rs.getString(i))){
						equal = true;
					} 
				}
				System.out.print("\n");   // new line character
			}
			if(equal == true){
				price = StockGetPrice.printStockPrice(symbol);
			} else {
				System.out.println("Please enter the correct symbol");
			}
		} catch( SQLException se ) {
			System.out.println ("SQLError: " + se.getMessage ()
								+ " code: " + se.getErrorCode ());
		} catch( Exception e ) { 
			e.printStackTrace(); 
		} finally {
			try { if (stmt != null) stmt.close(); } catch (Exception e) {};
		    try { if (rs != null) rs.close(); } catch (Exception e) {};
		    try { if (conn != null) conn.close(); } catch (Exception e) {};
		}
		return price;
	}
	
}
