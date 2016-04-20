/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.utils;

import com.in.servlet.PropertyConfiguratorServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class JdbcConnection {

    //private Connection conn = null;
    private String JNDI_LOOOKUP = PropertyConfiguratorServlet.getProperty((ConstantUtil.DATA_SOURCE));
    private static Logger logger = Logger.getLogger(JdbcConnection.class);

//    public Connection getConnection() throws Exception {
//        Connection conn = null;
//        try {
//                /*
//                 * Get the connection from datasource and return the connection.
//                 */
//                Context initContext = new InitialContext();
//                Context envContext = (Context) initContext.lookup("java:/comp/env");
//               DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
//                 //DataSource ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/myoracle");
//
//                conn = ds.getConnection();
//                    System.out.println("**********Getting connection from connection pool ");
//                if (logger.isDebugEnabled()) {
//                    logger.debug("[JdbcConnection:getConnection] The datasource is " + ds);
//                }
//
//                if (logger.isDebugEnabled()) {
//                    logger.debug("[JdbcConnection:getConnection] The connection is " + conn);
//                }
//                return conn;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("error getting connection");
////            logger.error("[JdbcConnection:getConnection] Problem in getting the connection", e);
//            throw new Exception("Problem in opening connection", e);
//        }
//    }
    public Connection getConnection() throws Exception {
        Connection connection = null;
        String DRIVER = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_DRIVER));
        String url = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_URL));
        String username = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_USERNAME));
        String password = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_PASSWORD));
        try {
            if (connection != null) {
                return connection;
            } else {
                Class.forName(DRIVER).newInstance();
                System.out.println("Driver loaded successfully");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connected......");
                return connection;
                //            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            throw new Exception("Problem in opening connection", e);
        }
    }

    public Connection getJDBCConnection() throws Exception {
        Connection connection = null;
        String DRIVER = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_DRIVER));
        String url = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_URL));
        String username = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_USERNAME));
        String password = PropertyConfiguratorServlet.getProperty((ConstantUtil.JDBC_PASSWORD));
        try {
            if (connection != null) {
                return connection;
            } else {
                Class.forName(DRIVER).newInstance();
                System.out.println("Driver loaded successfully");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connected......");
                return connection;
//            }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
            throw new Exception("Problem in opening connection", e);
        }
    }
    /**
     *This method returns true if the connection is null or successfully
     * closed otherwise return false.
     */
//    public void closeConnection() throws Exception {
//        //Connection conn = null;
//        //if connection is null or successfully closed then return true.
//        //otherwise return false.
//        try {
//            if (conn == null && conn.isClosed()) {
//            } else {
//                conn.close();
//                System.out.println("Connection closing.......");
//            }
//        } catch (Exception e) {
//            throw new Exception("Problem in closing connection", e);
//        }
//    }
}

