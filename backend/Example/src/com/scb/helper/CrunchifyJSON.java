package com.scb.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 
public class CrunchifyJSON {
 
	public static float getCurrPrice() {
 
		String jsonString = callURL("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=AAPL&interval=1min&apikey=UMDMHR72J6NMHG8O");
		//System.out.println("\n\njsonString: " + jsonString);
	    JSONObject jsonObject = new JSONObject(jsonString);
	    JSONObject mdata = jsonObject.getJSONObject("Meta Data");
	    Object out = mdata.get("3. Last Refreshed");
	    String ss = new String(out.toString());
	  //  System.out.println(out.toString());
	    JSONObject m1data = jsonObject.getJSONObject("Time Series (1min)");
	    JSONObject m2data = m1data.getJSONObject(ss);
	    Object out1 = m2data.get("1. open");
	    String ret = new String(out1.toString());
	    System.out.println(out.toString()+" "+"price "+ret);
	    return Float.parseFloat(ret);
	}
 
	
	public static float getCurrRsi()
	{
		String jsonString = callURL("https://www.alphavantage.co/query?function=RSI&symbol=AAPL&interval=1min&time_period=10&series_type=open&apikey=UMDMHR72J6NMHG8O");
		//System.out.println(jsonString);
		JSONObject jsonObject = new JSONObject(jsonString);
		JSONObject mdata = jsonObject.getJSONObject("Meta Data");
		Object out = mdata.get("3: Last Refreshed");
		//System.out.println(out.toString());
		String ss = new String(out.toString());
		JSONObject m1data = jsonObject.getJSONObject("Technical Analysis: RSI");
	    JSONObject m2data = m1data.getJSONObject(ss.substring(0, ss.length()-3));
	    Object out1 = m2data.get("RSI");
	    String ret = new String(out1.toString());
	    //System.out.println(ret);
	    System.out.println(out.toString()+" "+"rsi "+ret);
	    return Float.parseFloat(ret);
	}
	public static String callURL(String myURL) {
	//	System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		} 
 
		return sb.toString();
	}
 
}