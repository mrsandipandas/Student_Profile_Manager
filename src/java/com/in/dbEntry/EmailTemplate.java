/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import com.in.VO.EmailTemplateVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author sada3260
 */
public class EmailTemplate {
    private String EMAIL_TEMPLATE = "select * from emailtemplate where emailtemplate_id = '1'";

    public EmailTemplateVO getEmailTemplate() throws Exception {
        System.out.println("Entering get emailTemplates......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        EmailTemplateVO emailTemplateVO = new EmailTemplateVO();
        try {
            conn = jdbcConn.getConnection();

            System.out.println("emailTemplates jdbc Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(EMAIL_TEMPLATE);

            System.out.println("emailTemplate name found.....");

            if (rs.next()) {

                String emailtemplate_id = rs.getString("emailtemplate_id");
                String emailtemplate_body = rs.getString("emailtemplate_body");
                String emailtemplate_subject = rs.getString("emailtemplate_subject");

                System.out.println("\n" + emailtemplate_id + "\n" + emailtemplate_body + "\n" + emailtemplate_subject);

                emailTemplateVO.setEmailemplateId(emailtemplate_id);
                emailTemplateVO.setEmailemplateBody(emailtemplate_body);
                emailTemplateVO.setEmailemplateSubject(emailtemplate_subject);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching emailTemplate Names."
                    + " Please try after sometime");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Problem in closing category jdbc connection.......");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return emailTemplateVO;
    }

    public EmailTemplateVO getEmailTemplateForScheduler() throws Exception {
        System.out.println("Entering get getEmailTemplateForScheduler......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        EmailTemplateVO emailTemplateVO = new EmailTemplateVO();
        try {
            conn = jdbcConn.getJDBCConnection();

            System.out.println("getEmailTemplateForScheduler jdbc Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(EMAIL_TEMPLATE);

            System.out.println("getEmailTemplateForScheduler name found.....");

            if (rs.next()) {

                String emailtemplate_id = rs.getString("emailtemplate_id");
                String emailtemplate_body = rs.getString("emailtemplate_body");
                String emailtemplate_subject = rs.getString("emailtemplate_subject");

                System.out.println("\n" + emailtemplate_id + "\n" + emailtemplate_body + "\n" + emailtemplate_subject);

                emailTemplateVO.setEmailemplateId(emailtemplate_id);
                emailTemplateVO.setEmailemplateBody(emailtemplate_body);
                emailTemplateVO.setEmailemplateSubject(emailtemplate_subject);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching emailTemplate Names(getEmailTemplateForScheduler)."
                    + " Please try after sometime");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception ex) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("Problem in closing getEmailTemplateForScheduler jdbc connection.......");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return emailTemplateVO;
    }

}
