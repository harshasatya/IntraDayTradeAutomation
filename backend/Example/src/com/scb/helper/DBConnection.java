package com.scb.helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBConnection {
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");       
            //Creating a connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
            System.out.println("Called getConnection method -> Connection created");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println(" no connection created....");
            e.printStackTrace();
        } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static Statement getStatement(){
        Statement statement = null;
        try {
            statement= getConnection().createStatement();
            System.out.println("got statement");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return statement;
    }

    public static boolean getResultStatus(String query){
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(query);
            if(resultSet.next())
                return true;
            else
                return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } 


    }

    public static ResultSet getResultSet(String query){
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(query);
            System.out.println("got result set");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;

    }

    public static int dmlAction(String query){
        int status = 0;
        try {
            status = getStatement().executeUpdate(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return status;

    }

}