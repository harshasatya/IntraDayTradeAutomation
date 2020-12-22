package com.scb.model;

public class Profit {
	int trans_id;
	String email;
	String date;
	float profit;
	float entry_price;
	float exit_price;
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	public int getTrans_id() {
		return trans_id;
	}

	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public float getEntry_price() {
		return entry_price;
	}

	public void setEntry_price(float entry_price) {
		this.entry_price = entry_price;
	}

	public float getExit_price() {
		return exit_price;
	}

	public void setExit_price(float exit_price) {
		this.exit_price = exit_price;
	}
	
}
