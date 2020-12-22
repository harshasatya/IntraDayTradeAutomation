package com.scb.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.scb.dao.UserDao;
import com.scb.model.User;

@Path("user")
public class UserResource {
    
    @GET
    @Path("/details/{UserId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserDetails(@PathParam("UserId") int UserId){
        User user = UserDao.getUserDetails(UserId);
        //write logic to get the User details either from some collection or from database
        
        System.out.println("---->>"+user.getId() + " " + user.getFirstName());
        return user;
    }
    
    
    
    @GET
    @Path("/users")
    //@Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        return UserDao.getAllUserDetails();
    }
    
    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public void createUser(User user){
        //logic to add User into database
        UserDao.addUser(user);
        System.out.println("User created");
        
    }
    
    @PUT    
    @Produces(MediaType.APPLICATION_JSON)
    public int updateUser(User user){
        int status = UserDao.updateUser(user);
        System.out.println("User updated");     
        return status;
    }

    
    
    
    
    @DELETE
    @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public void deleteUser(@PathParam("userId") int userId){
        System.out.println("User deleted...." + userId);
        UserDao.deleteUser(userId);
        //return "User Deleted";
    }

    
}
