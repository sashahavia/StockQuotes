package lesson14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class StockGetPrice {
	public static String printStockPrice(String symbol){
		String csvString;
		URL url = null;
		URLConnection urlConn = null;
		String price = null;
		InputStreamReader inStream = null;
		BufferedReader buff  = null;
		try{
//			url  = new URL("http://quote.yahoo.com/d/quotes.csv?s="
//							+ symbol + "&f=sl1d1t1c1ohgv&e=.csv" ); // Does not work anymore
			url = new URL("https://marketdata.websol.barchart.com/getQuote.csv?apikey=bb2ed98c5122be7a685b47bb0cb56796&symbols="
							+ symbol + "%2CGOOG&fields=fiftyTwoWkHigh%2CfiftyTwoWkHighDate%2CfiftyTwoWkLow%2CfiftyTwoWkLowDate&mode=I&jerq=false");
			urlConn = url.openConnection();
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		try{
			inStream = new InputStreamReader(urlConn.getInputStream());
			buff = new BufferedReader(inStream);
			// get the quote as a csv string
			csvString = buff.readLine(); 
			csvString = buff.readLine(); 
			price = cleanUpString(csvString);
		} catch(MalformedURLException e){
			System.out.println("Please check the spelling of " 
					+ "the URL: " + e.toString() );
		} catch(IOException  e1){
			System.out.println("Can't read from the Internet: " + 
					e1.toString() ); 
		} finally {
			try { if (buff != null) buff.close(); } catch (Exception e) {};
		    try { if (inStream != null) inStream.close(); } catch (Exception e) {};
		}
//		System.out.println("Price " + price);
		return price;
   } 
	
	public static String cleanUpString(String csv){
		// Parse the csv string using Split
		String[] split = csv.split(",");
		String price  = split[6];
		price = price.replaceAll("\"", "");
		return price;
	}
}
