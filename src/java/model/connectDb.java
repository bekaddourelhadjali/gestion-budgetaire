/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

public class connectDb {
    static  String url = "jdbc:mysql://localhost:3306/bddlabri";
    static  String user = "root";
    static  String passwd = "1234";
    public static Connection con;
    
    
    public static  Connection connecterDB() throws ClassNotFoundException {
       
        try {
            Class.forName("com.mysql.jdbc.Driver");  
        con=DriverManager.getConnection(  
       "jdbc:mysql://localhost:3306/bddlabri","root","1234"); 
                        
        } catch ( SQLException ex) {}
 
      return con;     
    }
 
    }


