package com.scb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.scb.helper.DBConnection;
import com.scb.model.Trader;
import com.scb.model.User;

public class UserDao {
    
    public static User getUserDetails(int uid) {
        String query = "SELECT * FROM TABUSERS WHERE USERID=" + uid;
        ResultSet rs = DBConnection.getResultSet(query);
        User user = new User();
        try {
            if(rs.next()){
                user.setId(uid);
                user.setFirstName(rs.getString("fname"));
                user.setLastName(rs.getString("lname"));
                user.setEmail(rs.getString("email"));                           
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("record found");
        return user;        
    }
    
    public static int deleteUser(int userId) {
        String query = "DELETE FROM TABUSERS WHERE USERID = " + userId;
        int status = DBConnection.dmlAction(query);
        return status;
    }
    
    public static int addUser(User user) {
        String query = "INSERT INTO TABUSERS  VALUES(user_seq.NEXTVAL,'"+user.getFirstName()+"','" + user.getLastName() + "','" + user.getEmail() +"')"  ;
        int status = DBConnection.dmlAction(query);
        return status;
    }
    
    public static int updateUser(User user){
        String query = "UPDATE TABUSERS SET FNAME='" + user.getFirstName() +"',LNAME='"+user.getLastName() +"',EMAIL='" + user.getEmail() +"' WHERE USERID=" + user.getId();
        int status = DBConnection.dmlAction(query);
        return status;      
    }
    
    public static List<User> getAllUserDetails(){
        String query = "SELECT * FROM TABUSERS";
        ResultSet rs = DBConnection.getResultSet(query);
        List<User> users = new ArrayList<>();
        try {
            User user = null;
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("userid"));
                user.setFirstName(rs.getString("fname"));
                user.setLastName(rs.getString("lname"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
            System.out.println("total : "  + users.size());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return users;
    }
    public static int getUser1(String email,String password) throws SQLException{
    	System.out.println(email);  
    	
    	Trader trader=new Trader();
    	boolean c=false;
        String query = "select * from trade where email = '"+email+"' and password = '"+password+"'";
        System.out.println(query);
        ResultSet rs = DBConnection.getResultSet(query);
        System.out.println(rs);
        
        try {
            if(rs.next()){
            	
            	
            	trader.setName(rs.getString("name"));
            	trader.setGender(rs.getString("gender"));
				trader.setEmail(rs.getString("email"));
				System.out.println(trader.getEmail()); 
                trader.setPassword(rs.getString("password"));
                trader.setRepassword(rs.getString("repassword"));
                trader.setPhone(rs.getString("phone"));                           
                c=true;
            }
               
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(c)
        {   
        	System.out.println("record found");
        	return 1;
        }
        else
        {
           return 0;
        }
        
        
    }
    public static int addUser1(Trader user) {
        String query = "INSERT INTO TRADE  VALUES('"+user.getName()+"','"+user.getGender()+"','"+user.getEmail()+"','" + user.getPassword() + "','" + user.getRepassword() +"','" + user.getPhone()+"')"  ;
        int status = DBConnection.dmlAction(query);
        return status;
    }
}
        
    