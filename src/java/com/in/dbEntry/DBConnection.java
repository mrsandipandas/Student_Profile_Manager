/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

/**
 *
 * @author sougata das
 */
import java.sql.*;

public class DBConnection {
    static Connection conn = null;

    public static Connection getConnection() {
        String DRIVER = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/studentreg?user=root&amp;password=root";
        
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(url, "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(){
        if(null!=conn){
            try{
                conn.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Connection already closed");
        }
    }
    
}
