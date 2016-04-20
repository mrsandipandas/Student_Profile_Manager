/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.in.VO.GreetingCardVO;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sada3260
 */
public class GreetingCard {

    private String ADD_GREETINGS_CARD = "insert into greetingcard values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private String NO_OF_GREETINGS_CARD = "select count(*) from greetingcard";
    private String GREETING_CARD_INFORMATION = "select * from greetingcard where greeting_id = ? and greeting_random_num = ?";
    private String ALL_GREETING_CARDS_INFO_FOR_A_DAY = "select * from greetingcard where greeting_send_date = ?";
    private String UPDATE_GREETING_CARD_VISITED_STATUS = "update greetingcard set greeting_is_visited = ? where greeting_id = ?";
    private String PURGE_GREETING_CARD = "delete from greetingcard where (TRUNC(SYSDATE - greeting_send_date))>30";

    public void purgeGreetingCardTransactionalData() {
        System.out.println("Entering purgeGreetingCardTransactionalData......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = jdbcConn.getJDBCConnection();

            System.out.println("purgeGreetingCardTransactionalData JDBC Connection created......");

            stmt = conn.createStatement();
            stmt.executeUpdate(PURGE_GREETING_CARD);

            System.out.println("Available transactional data of last 30 days deleted");

        } catch (Exception ex) {
            System.out.println("Problem encountered while purging transactional data of last 30 days");
            ex.printStackTrace();
        } 
        finally {
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
                System.out.println("Problem in closing purgeGreetingCardTransactionalData jdbc connection....");
                e.printStackTrace();
            }
        }
    }

    public void updateGreetingCardVisitedStatus(String greetingId) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;



        try {
            conn = jdbcConn.getConnection();

            System.out.println("updateGreetingCardVisitedStatus jdbc Connection created......");

            pstmt = conn.prepareStatement(UPDATE_GREETING_CARD_VISITED_STATUS);
            pstmt.setString(1, "1");
            pstmt.setString(2, greetingId);
            pstmt.executeUpdate();

            System.out.println("Status updated successfully....");


        } catch (Exception ex) {

            throw new Exception("Problem encountered while updating status."
                    + " Please try after sometime");


        } finally {
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
                System.out.println("Problem in closing updateGreetingCardVisitedStatus JDBC connection.......");
                e.printStackTrace();


                throw new Exception(e);


            }
        }
    }

    public int getNoOfGreetingsCard() throws Exception {
        System.out.println("Entering getNoOfGreetingsCard......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        int n = -1;


        try {
            conn = jdbcConn.getConnection();

            System.out.println("No of GreetingsCard JDBC Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(NO_OF_GREETINGS_CARD);

            System.out.println("Max GreetingsCard Id found.....");



            if (rs.next()) {
                n = rs.getInt(1);


            } else {
                n = 0;


            }
        } catch (Exception ex) {
            ex.printStackTrace();


            throw new Exception("Problem encountered while searching GreetingsCard Ids."
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
                System.out.println("prob in closing getNoOfGreetingsCard jdbc connection....");
                e.printStackTrace();


                throw new Exception(e);


            }
        }
        return n;


    }

    public String addGreetingsCardInDatabase(String ecard_id, String senderName, String senderEmail,
            String receiverName, String receiverEmail, String message, String isVisited, String randomNo,
            String format, java.util.Date sendDate, java.util.Date createDate, String cardType, String cardBgColor) throws Exception {

        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String newGreetingCardId = null;


        try {
            conn = jdbcConn.getConnection();

            System.out.println("addGreetingsCardInDatabase jdbc Connection created......");

            pstmt = conn.prepareStatement(ADD_GREETINGS_CARD);


            int n = getNoOfGreetingsCard();

            System.out.println("no of greeting cards = " + n);

            java.sql.Date greetingCreateDate = null;

            //If create date field is not filled then set the date to today's date


            if (createDate != null) {
                greetingCreateDate = new java.sql.Date(createDate.getTime());


            } else {
                createDate = new java.util.Date();
                greetingCreateDate = new java.sql.Date(createDate.getTime());


            }

            java.sql.Date greetingSendDate = null;

            //If send date field is not filled then set the date to today's date


            if (sendDate != null) {
                greetingSendDate = new java.sql.Date(sendDate.getTime());


            } else {
                sendDate = new java.util.Date();
                greetingSendDate = new java.sql.Date(sendDate.getTime());


            }

            if (n != -1) {
                n++;
                pstmt.setString(1, "" + n);
                pstmt.setString(2, ecard_id);
                pstmt.setString(3, senderName);
                pstmt.setString(4, senderEmail);
                pstmt.setString(5, receiverName);
                pstmt.setString(6, receiverEmail);
                pstmt.setString(7, message);
                pstmt.setString(8, isVisited);
                pstmt.setString(9, randomNo);
                pstmt.setString(10, format);
                pstmt.setDate(11, greetingSendDate);
                pstmt.setDate(12, greetingCreateDate);
                pstmt.setString(13, cardType);
                pstmt.setString(14, cardBgColor);
                pstmt.executeUpdate();

                newGreetingCardId = "" + n;

                System.out.println("Greeting card Added successfully in database.....");


            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while adding Greeting card."
                    + " Please try after sometime");


        } finally {
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
                System.out.println("problem in closing category addGreetingsCardInDatabase connection");
                e.printStackTrace();


                throw new Exception(e);


            }
        }
        return newGreetingCardId;


    }

    public GreetingCardVO getGreetingCardInformation(String greetingId, String randomNumber) throws Exception {
        System.out.println("greetingId#######****** = " + greetingId);
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        GreetingCardVO greetingCardVO = new GreetingCardVO();



        try {
            conn = jdbcConn.getConnection();

            System.out.println("getGreetingCardInformation jdbc Connection created......");

            pstmt = conn.prepareStatement(GREETING_CARD_INFORMATION);
            pstmt.setString(1, greetingId);
            pstmt.setString(2, randomNumber);
            rs = pstmt.executeQuery();



            if (rs.next()) {

                String ecardId = rs.getString("ecard_id");
                String senderName = rs.getString("greeting_sender_name");
                String senderEmail = rs.getString("greeting_sender_email");
                String receiverName = rs.getString("greeting_receiver_name");
                String receiverEmail = rs.getString("greeting_receiver_email");
                String message = rs.getString("greeting_message");
                String isVisited = rs.getString("greeting_is_visited");
                String randomNo = rs.getString("greeting_random_num");
                String format = rs.getString("greeting_format");
                java.util.Date sendDate = rs.getDate("greeting_send_date");
                java.util.Date createDate = rs.getDate("greeting_create_date");
                String cardType = rs.getString("greeting_card_type");
                String cardBgColor = rs.getString("greeting_background_color");

                greetingCardVO.setGreetingId(greetingId);
                greetingCardVO.setEcardId(ecardId);
                greetingCardVO.setGreetingSenderName(senderName);
                greetingCardVO.setGreetingSenderEmail(senderEmail);
                greetingCardVO.setGreetingReceiverName(receiverName);
                greetingCardVO.setGreetingReceiverEmail(receiverEmail);
                greetingCardVO.setGreetingMessage(message);
                greetingCardVO.setGreeting_isVisited(isVisited);
                greetingCardVO.setGreetingRandomNo(randomNo);
                greetingCardVO.setGreetingFormat(format);
                greetingCardVO.setGreetingSendDate(sendDate);
                greetingCardVO.setGreetingCreateDate(createDate);
                greetingCardVO.setGreetingCardType(cardType);
                greetingCardVO.setGreetingBackgroundColor(cardBgColor);


            }

            System.out.println("GreetingCard Info got successfully for a greeting_id.....");



        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while getting GreetingCard infos."
                    + " Please try after sometime or contact system administrator");


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
                System.out.println("prob in closing getGreetingCardInformation jdbc connection....");
                e.printStackTrace();


                throw new Exception(e);


            }
        }
        return greetingCardVO;


    }

    public ArrayList<GreetingCardVO> getAllGreetingInfoForADay() throws Exception {
        System.out.println("Entering getAllGreetingInfoForADay");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        java.sql.Date todaysDate = new java.sql.Date(new java.util.Date().getTime());

        ArrayList<GreetingCardVO> greetingCardVOs = new ArrayList();


        try {
            conn = jdbcConn.getJDBCConnection();
            System.out.println("getAllGreetingInfoForADay jdbc Connection created......");

            pstmt = conn.prepareStatement(ALL_GREETING_CARDS_INFO_FOR_A_DAY);
            pstmt.setDate(1, todaysDate);
            rs = pstmt.executeQuery();



            if (rs.next()) {
                GreetingCardVO greetingCardVO = new GreetingCardVO();

                String greetingId = rs.getString("greeting_id");
                String ecardId = rs.getString("ecard_id");
                String senderName = rs.getString("greeting_sender_name");
                String senderEmail = rs.getString("greeting_sender_email");
                String receiverName = rs.getString("greeting_receiver_name");
                String receiverEmail = rs.getString("greeting_receiver_email");
                String message = rs.getString("greeting_message");
                String isVisited = rs.getString("greeting_is_visited");
                String randomNo = rs.getString("greeting_random_num");
                String format = rs.getString("greeting_format");
                java.util.Date sendDate = rs.getDate("greeting_send_date");
                java.util.Date createDate = rs.getDate("greeting_create_date");
                String cardType = rs.getString("greeting_card_type");
                String cardBgColor = rs.getString("greeting_background_color");

                greetingCardVO.setGreetingId(greetingId);
                greetingCardVO.setEcardId(ecardId);
                greetingCardVO.setGreetingSenderName(senderName);
                greetingCardVO.setGreetingSenderEmail(senderEmail);
                greetingCardVO.setGreetingReceiverName(receiverName);
                greetingCardVO.setGreetingReceiverEmail(receiverEmail);
                greetingCardVO.setGreetingMessage(message);
                greetingCardVO.setGreeting_isVisited(isVisited);
                greetingCardVO.setGreetingRandomNo(randomNo);
                greetingCardVO.setGreetingFormat(format);
                greetingCardVO.setGreetingSendDate(sendDate);
                greetingCardVO.setGreetingCreateDate(createDate);
                greetingCardVO.setGreetingCardType(cardType);
                greetingCardVO.setGreetingBackgroundColor(cardBgColor);

                greetingCardVOs.add(greetingCardVO);


            }
            System.out.println("GreetingCard Infos for a day got successfully......");



        } catch (Exception ex) {
            throw new Exception("Problem encountered while getting all GreetingCard infos for a day."
                    + " Please try after sometime or contact system administrator");


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
                System.out.println("prob in closing getAllGreetingInfoForADay jdbc connection....");
                e.printStackTrace();


                throw new Exception(e);


            }
        }
        return greetingCardVOs;

    }
}
