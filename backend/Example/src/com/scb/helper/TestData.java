package com.scb.helper;

public class TestData {

	static float rsi[] = new float[]{21,22,23,24,25,26};
	static float price[] = new float[]{100,100,105,108,8988,9};
	static int i =0;
	public static float curr_Rsi()
	{
		System.out.println("rsi - "+rsi[i]);
		return rsi[i];
	}
	public static float curr_Price()
	{
		i++;
		System.out.println("price - "+price[i-1]);
		return price[i-1];
	}
}
