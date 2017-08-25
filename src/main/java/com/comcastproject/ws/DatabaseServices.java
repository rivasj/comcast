package com.comcastproject.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseServices {

	private static DatabaseServices instance = new DatabaseServices();
    public static final String URL = "jdbc:mysql://comcast.c8k8fufzt0ct.us-east-2.rds.amazonaws.com:3306/comcast?useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "mypassword";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver"; 
     
    private DatabaseServices() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     
    private Connection createConnection() {
 
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database." + e.getLocalizedMessage());
        }
        return connection;
    }   
     
    public static Connection getConnection() {
        return instance.createConnection();
    }
}
