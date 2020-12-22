package com.scb.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scb.dao.UserDao;
import com.scb.dao.UserDao1;
import com.scb.model.Trader;
import com.scb.model.User;

@Path("login")
public class LoginResource {
	
	
	    @GET
	    @Path("/users")
	    @Produces(MediaType.APPLICATION_JSON)
	    public List<User> getAllUsers(){
	        return UserDao.getAllUserDetails();
	    }
	 
	    @GET
	    @Path("/details/{Email}/{Password}")
	    //@Produces(MediaType.APPLICATION_JSON)
	    public int getUserDetails1(@PathParam("Email") String email,@PathParam("Password") String password) throws SQLException{
	    	
	          return UserDao1.getUser1(email,password);
	         
	    
	    	
	    }
}
