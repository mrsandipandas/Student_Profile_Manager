/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author sada3260
 */
public class AdminCheck {

    private String GET_ADMIN_IDS = "select * from users1 where username = ?";

    public boolean isAdmin(String uid) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Boolean flag = false;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("isAdmin jdbc Connection created......");

            pstmt = conn.prepareStatement(GET_ADMIN_IDS);
            pstmt.setString(1, uid.toUpperCase());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                if (null != rs.getString("isadmin") && rs.getString("isadmin").equalsIgnoreCase("Y")) {
                    flag = true;
                }
            }
            System.out.println("Admin privilages checked successfully....");
        } catch (Exception ex) {

            throw new Exception("Problem encountered while checking Admin privilages"
                    + " Please try after sometime");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Problem in closing isAdmin JDBC connection.......");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return flag;
    }
}
