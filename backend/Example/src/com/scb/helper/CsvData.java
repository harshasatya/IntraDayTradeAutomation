package com.scb.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvData {

	public static int i = 99;
	private static final String COMMA_DELIMITER = ",";
	
	static BufferedReader br = null;
	
	public static float getCurrRsi() throws IOException
	{
		
		 float rsi[] = new float[100];
		 br = new BufferedReader(new FileReader("C:/Users/IBM/Downloads/rsi.csv"));
		 br.readLine();
		 String line;
		 int count=0;
		 while ((line = br.readLine()) != null && count<100) 
         {
             String[] employeeDetails = line.split(COMMA_DELIMITER);
             rsi[count] =  Float.parseFloat(employeeDetails[1]);
             //System.out.println(employeeDetails[1]);
             count++;
         }
		 System.out.println("Current rsi - "+ rsi[i]);
	    return rsi[i];
	}
	
	public static float getCurrPrice() throws IOException
	{
		 float rsi[] = new float[100];
		 br = new BufferedReader(new FileReader("C:/Users/IBM/Downloads/price.csv"));
		 br.readLine();
		 String line;
		 int count=0;
		 while ((line = br.readLine()) != null && count<100) 
         {
             String[] employeeDetails = line.split(COMMA_DELIMITER);
             rsi[count] =  Float.parseFloat(employeeDetails[1]);
            // System.out.println(employeeDetails[1]);
             count++;
         }
		 i--;
		 System.out.println("Current price - "+ rsi[i-1]);
	    return rsi[i+1];
	    
	}
	
	public static void main(String a[]) throws IOException
	{
		System.out.println(getCurrRsi());
		
	}
	
}