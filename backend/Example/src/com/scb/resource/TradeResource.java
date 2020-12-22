package com.scb.resource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scb.dao.UserDao1;
import com.scb.helper.CrunchifyJSON;
import com.scb.helper.CsvData;
import com.scb.helper.TestData;
import com.scb.model.Input;
import com.scb.model.Profit;

@Path("/trades")
public class TradeResource {
	static String uni;

	static float curr_rsi=0.0f;
	static float curr_price=0.0f;
	static float trade_entry_price=0.0f;
	static float trade_exit_price = 0.0f;
	static float pro_loss = 0.0f;
	static boolean trade =true;
	static boolean at = true;
	static boolean trade_entry = false;
	static boolean trade_exit = false;
	static int time_of_trade = 0;
	static boolean bought= false;
	static boolean sold = false;
	static int no_of_shares=0;
	 @GET
	    @Path("/details/{Email}/{Password}")
	    //@Produces(MediaType.APPLICATION_JSON)
	    public int getUserDetails1(@PathParam("Email") String email,@PathParam("Password") String password) throws SQLException{
	    	  uni=email;
	          return UserDao1.getUser1(email,password);
	         
	    
	    	
	    }
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/rsi/{no_of_shares}/{profit_per}/{loss_per}/{time}")
	 public Profit trade(@PathParam("no_of_shares")int no_shares,@PathParam("profit_per")float profit_per,@PathParam("loss_per")float loss_per,@PathParam("time")int time) throws InterruptedException, IOException
    {
		at = true;
		long startTime = System.currentTimeMillis();
		long endTime = startTime + (60000L*time);
		
		while(System.currentTimeMillis() < endTime)
		{
			
		
			
			try{
			curr_rsi = CrunchifyJSON.getCurrRsi();
			curr_price = CrunchifyJSON.getCurrPrice();
			
        	//curr_rsi = TestData.curr_Rsi();
	    	//curr_price = TestData.curr_Price();
			
		    //curr_rsi =  CsvData.getCurrRsi();
			//curr_price = CsvData.getCurrPrice();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(at == false)
			{
				System.out.println("AT OFF");
				break;
			}
			if(trade == true)
			{
	
				if(trade_entry == false)
				{	
					if(curr_rsi <=30)
					{
						
						trade_entry_price += (-1*no_shares * curr_price);
						no_of_shares += no_shares;
						trade_entry = true;
						bought = true;
						System.out.println("entered the trade with buying as rsi is less than 30");
						System.out.println("no_of_shares - "+no_of_shares);
					}
					
					if(curr_rsi >=70)
					{
						
						trade_entry_price += (float) (no_shares * curr_price);
						no_of_shares -= no_shares;
						trade_entry = true;
						sold =true;
						System.out.println("entered the trade with selling as rsi is greater than 70");
						System.out.println("no_of_shares - "+no_of_shares);
					}
				}
				
				if(trade_entry == true)
				{
					if(bought == true && (curr_price*no_of_shares)>= ((100+profit_per)/100)*trade_entry_price)
					{
						trade_exit_price = (float) (curr_price * no_of_shares);
						pro_loss = trade_exit_price - trade_entry_price;
						trade_exit = true;
						System.out.println("exiting the trade with profit by selling the shares");
						System.out.println("no_of_shares - "+no_of_shares);
					}
					if(bought == true && ((curr_price*no_of_shares) <= ((100-loss_per)/100)*trade_entry_price)) // Stop loss
					{ System.out.println("inside loss");
						trade_exit_price = (curr_price * no_of_shares);
						pro_loss = trade_exit_price - trade_entry_price;
						trade_exit = true;
						System.out.println("exiting the trade with loss by selling the shares");
						System.out.println("no_of_shares - "+no_of_shares);
					}
					
					if(sold == true &&  (curr_price*no_of_shares)<= ((100-profit_per)/100)*trade_entry_price)
					{
						trade_exit_price = (float) (curr_price * no_of_shares);
						pro_loss = trade_entry_price - trade_exit_price;
						trade_exit = true;
						System.out.println("exiting the trade with profit by buying the shares");
						System.out.println("no_of_shares - "+no_of_shares);
					}
					
					if(sold == true && (curr_price*no_of_shares) >= ((100+loss_per)/100)*trade_entry_price) // Stop loss
					{
						trade_exit_price = (float) (curr_price * no_of_shares);
						pro_loss = trade_entry_price - trade_exit_price;
						trade_exit = true;
						System.out.println("exiting the trade with loss by buying the shares");
						System.out.println("no_of_shares - "+no_of_shares);
					}
					
					
				}
				
				
			}
			
			if(trade_entry == true && trade_exit == true)
			{
				trade=false;
				Profit p = new Profit();
				p.setProfit(pro_loss);
				p.setEntry_price(trade_entry_price/no_of_shares);
				p.setExit_price(trade_exit_price/no_of_shares);
				p.setEmail(uni);
				UserDao1.addUser(p);
				return p;
			}
			
			
			
			Thread.sleep(60000L);
			
		}
		
		
		// didnot enter trade
		Profit p = new Profit();
		p.setProfit(pro_loss);
		System.out.println(trade_entry_price/no_of_shares);
		System.out.println(trade_exit_price/no_of_shares);
		if(no_of_shares != 0){
		p.setEntry_price(trade_entry_price/no_of_shares);
		p.setExit_price(trade_exit_price/no_of_shares);}
		else{
			p.setEntry_price(0);
			p.setExit_price(0);}	
		p.setEmail(uni);
		UserDao1.addUser(p);
		return p;
    }
	
	
	
	
	
	
	
	
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/ledger")
	public List<Profit> getLedger()
	{   
		System.out.println(uni);
		return UserDao1.getAllUserDetails(uni);
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/buy/{no_of_shares}")
	public void buy(@PathParam("no_of_shares") int no) throws IOException
	{
		
	    	//curr_price = TestData.curr_Price();
	 	//curr_price = CsvData.getCurrPrice();
	 	curr_price = CrunchifyJSON.getCurrPrice();
	 	no_of_shares += no;
	 	trade_entry_price -= no*curr_price; 
	 	System.out.println(no_of_shares);
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/sell/{no_of_shares}")
	public void sell(@PathParam("no_of_shares") int no) throws IOException
	{
		 //	curr_price = TestData.curr_Price();
	 	//curr_price = CsvData.getCurrPrice();
	 	curr_price = CrunchifyJSON.getCurrPrice();
	 	no_of_shares -= no;
	 	trade_entry_price += no*curr_price; 
		System.out.println(no_of_shares);
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/atoff")
	public void atoff()
	{
	 at = false;
	 System.out.println("Algorithmic trade stopped");
	}
	
}
