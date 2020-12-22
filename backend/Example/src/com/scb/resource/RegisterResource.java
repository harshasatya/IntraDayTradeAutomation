package com.scb.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scb.dao.UserDao;
import com.scb.dao.UserDao1;
import com.scb.model.Trader;

@Path("register")
public class RegisterResource {
	@POST
	 @Path("/users")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public void createUser(Trader user){
	        //logic to add User into database
	        UserDao1.addUser1(user);
	        System.out.println("User created");
	        System.out.println();

}
}
