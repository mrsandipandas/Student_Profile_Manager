/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import com.in.VO.EcardVO;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

/**
 *
 * @author Sandi
 */
public class Card {

    private String CARD_IMAGE = "select ecard_image_file from ecard where ecard_id = ?";
    private String CATEGORY_CARDS = "select ecard_name, ecard_id, ecard_is_deleted, ecard_desc, ecard_contributor_name"
            + " from ecard where ecard_is_deleted = '0' and subcategory_id = ?";
    private String ADD_CARD = "insert into ecard values(?,?,?,?,null,?,?)";
    private String INSERT_CARD_IMAGE = "update ecard set ecard_image_file = ? where ecard_id = ?";
    private String DELETE_CARD = "update ecard set ecard_is_deleted = ? WHERE ecard_id = ?";
    private String NO_OF_CARDS = "SELECT COUNT(*) FROM ecard";
    private String DELETE_ALL_CARDS_OF_A_SUBCATEGORY = "update ecard set ecard_is_deleted = ? WHERE "
            + "subcategory_id = ?";
    private static Logger logger = Logger.getLogger(Card.class);

    public byte[] getCategoryCardImage(String ecardId) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Entering getCategoryCardImage in Card.java");
        }
        System.out.println("Entering getCategoryCardImage in Card.java");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //Blob ecardImage = null;
        byte[] imgData = null;
        Blob ecardImage = null;
        try {
            conn = jdbcConn.getConnection();

            if (logger.isDebugEnabled()) {
                logger.debug("getCategoryCardImage jdbc Connection created......");
            }
            System.out.println("getCategoryCardImage jdbc Connection created......");

            pstmt = conn.prepareStatement(CARD_IMAGE);
            pstmt.setString(1, ecardId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                ecardImage = rs.getBlob("ecard_image_file");
                imgData = ecardImage.getBytes(1, (int) ecardImage.length());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Card Image got successfully for a ecard_id:::::" + ecardId);
            }
            System.out.println("Card Image got successfully for a ecard_id:::::" + ecardId);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while getting ecard image."
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
                if (logger.isDebugEnabled()) {
                    logger.debug("prob in closing getCategoryCardImage jdbc connection....");
                }
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return imgData;
    }
//    public Blob getCategoryCardImage(String ecardId) throws Exception {
//        if (logger.isDebugEnabled()) {
//            logger.debug("Entering getCategoryCardImage in Card.java");
//        }
//        JdbcConnection jdbcConn = new JdbcConnection();
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        Blob ecardImage = null;
//        try {
//            conn = jdbcConn.getConnection();
//
//            if (logger.isDebugEnabled()) {
//                logger.debug("getCategoryCardImage jdbc Connection created......");
//            }
//
//            pstmt = conn.prepareStatement(CARD_IMAGE);
//            pstmt.setString(1, ecardId);
//            rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                ecardImage = rs.getBlob("ecard_image_file");
//            }
//
//            if (logger.isDebugEnabled()) {
//                logger.debug("Card Image got successfully for a ecard_id:::::" + ecardId);
//            }
//
//        } catch (Exception ex) {
//            throw new Exception("Problem encountered while getting ecard image."
//                    + " Please try after sometime or contact system administrator");
//        } finally {
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//            } catch (Exception ex) {
//            }
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                if (logger.isDebugEnabled()) {
//                    logger.debug("prob in closing getCategoryCardImage jdbc connection....");
//                }
//                e.printStackTrace();
//                throw new Exception(e);
//            }
//        }
//        return ecardImage;
//    }

    public ArrayList<EcardVO> getCategoryCardInformation(String subCategoryId) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("Entering getCategoryCardInformation in Card.java");
        }
//        if(subCategoryId != null && !subCategoryId.trim().equalsIgnoreCase("")){
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<EcardVO> ecardVOs = new ArrayList<EcardVO>();
        //ecardVOs = null;
        try {
            conn = jdbcConn.getConnection();

            if (logger.isDebugEnabled()) {
                logger.debug("getCategoryCardInformation jdbc Connection created......");
            }

            pstmt = conn.prepareStatement(CATEGORY_CARDS);
            pstmt.setString(1, subCategoryId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String ecardName = rs.getString("ecard_name");
                String ecardId = rs.getString("ecard_id");
                String ecardIsDeleted = rs.getString("ecard_is_deleted");
                String ecardDescription = rs.getString("ecard_desc");
                String ecardContributor = rs.getString("ecard_contributor_name");
                //Blob ecardImage = rs.getBlob("ecard_image_file");
                EcardVO ecardVO = new EcardVO();

                ecardVO.setEcardName(ecardName);
                ecardVO.setEcardDescription(ecardDescription);
                ecardVO.setEcardId(ecardId);
                //ecardVO.setEcardImageFile(ecardImage);
                ecardVO.setEcard_isDeleted(ecardIsDeleted);
                ecardVO.setSubCategoryId(subCategoryId);
                ecardVO.setEcardContributorName(ecardContributor);
                ecardVOs.add(ecardVO);
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Card Info got successfully for a subcategory.....");
            }


        } catch (Exception ex) {
            throw new Exception("Problem encountered while getting ecard infos."
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
                if (logger.isDebugEnabled()) {
                    logger.debug("prob in closing getCategoryCardInformation jdbc connection....");
                }

                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return ecardVOs;
    }

    public int addCategoryCards(String subCategoryId, String cardName, String cardDescription, String cardContributor, FileItem cardImage, String card_isDeleted) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Entering addCategoryCards in Card.java");
        }

        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getJDBCConnection();
            //conn.setAutoCommit(false);

            //if (logger.isDebugEnabled()) {
                System.out.println("AddCard jdbc Connection created......");
                System.out.println("subCategoryId:::::::" + subCategoryId);
                System.out.println("cardName:::::::::" + cardName);
                System.out.println("cardDescription:::::::::" + cardDescription);
                System.out.println("cardContributor:::::::::" + cardContributor);
                System.out.println("card content type:::::::" + cardImage.getContentType());
                System.out.println("card content size:::::::" + cardImage.getSize());
            //}

            pstmt = conn.prepareStatement(ADD_CARD);
            int n = getNoOfCards();

            if (logger.isDebugEnabled()) {
                logger.debug("no of cards = " + n);
            }

            System.out.println("n = " + n);
            if (n != -1) {
                n++;
                //ecard_id
                //Blob newBlob = (Blob) oracle.sql.BLOB.createTemporary(conn,false, oracle.sql.BLOB.DURATION_SESSION);
                pstmt.setString(1, "" + n);
                pstmt.setString(2, subCategoryId);
                pstmt.setString(3, cardName);
                pstmt.setString(4, cardDescription);
                // size must be converted to int otherwise it results in error
                //pstmt.setBlob(5, newBlob);
                //pstmt.registerOutParameter(5, java.sql.Types.BLOB);
                //pstmt.setBinaryStream(5, cardImage.getInputStream());
                pstmt.setString(5, card_isDeleted);
                pstmt.setString(6, cardContributor);
                pstmt.executeUpdate();

                if (logger.isDebugEnabled()) {
                    logger.debug("Card Added successfully.....");
                }
                System.out.println("Card Added successfully");
            }
            //conn.commit();
            return n;
        } catch (Exception ex) {
            throw new Exception("Problem encountered while adding card."
                    + " Please try after sometime or contact system administrator");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("problem in closing category addition connection");
                }
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }

    public void addCardImage(int ecardId, FileItem cardImage) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getJDBCConnection();
            //conn.setAutoCommit(false);

            System.out.println("addCardImage jdbc Connection created......");
//            File imageFile = new File(cardImage.getName());
//            System.out.println("imageFile location = " + imageFile.getAbsolutePath());
//            InputStream in = (InputStream) new FileInputStream(imageFile.getAbsolutePath());
//            System.out.println("File size = " + in.available());

            pstmt = conn.prepareStatement(INSERT_CARD_IMAGE);
            
            //pstmt.setBinaryStream(2, cardImage.getInputStream(), (int) cardImage.getSize());
///////////////////////////////////////////////
            //File imageFile = new File(image.getName());
            //System.out.println("imageFile location = " + imageFile.getAbsolutePath());
            //InputStream inputStream = (InputStream) new FileInputStream(imageFile);
            //System.out.println("File size = " + inputStream.available());
            //pstmt = conn.prepareStatement("update photos set photo = ? where id =?");

            //pstmt.setBinaryStream(1, inputStream, (int) imageFile.length());
            //pstmt.setInt(2, Integer.parseInt(id));
            //pstmt.setBinaryStream(2, cardImage.getInputStream(), (int) cardImage.getSize());
            //int rowCount = pstmt.executeUpdate();
            //System.out.println("rowCount = " + rowCount);
            //System.out.println("################card added successfully####################");


///////////////////////////////////////////////////
           //pstmt.setBinaryStream(1, (InputStream) profileImage.getInputStream(), (int) profileImage.getSize());
            pstmt.setBinaryStream(1,(InputStream) cardImage.getInputStream(), (int) cardImage.getSize());
            pstmt.setString(2, "" + ecardId);
            int rowCount = pstmt.executeUpdate();
            System.out.println("rowCount = " + rowCount);

            System.out.println("Card Added successfully.....");

            //conn.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while adding CardImage."
                    + " Please try after sometime or contact system administrator");
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (Exception e) {
                throw new Exception(e);
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println("problem in closing addCardImage connection");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }

    public void deleteCategoryCards(String ecardId) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Entering deleteCategoryCards in Card.java");
        }
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = jdbcConn.getConnection();

            if (logger.isDebugEnabled()) {
                logger.debug("Delete Category cards jdbc Connection created......");
            }
            pstmt = conn.prepareStatement(DELETE_CARD);
            pstmt.setString(1, "1");
            pstmt.setString(2, ecardId);
            pstmt.executeUpdate();

            if (logger.isDebugEnabled()) {
                logger.debug("Card deleted successfully....");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while deleting."
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
                if (logger.isDebugEnabled()) {
                    logger.debug("Problem in closing delete card JDBC connection.......");
                }
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }

    public int getNoOfCards() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Entering getNoOfCards in Card.java");
        }
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int n = -1;
        try {
            conn = jdbcConn.getConnection();

            if (logger.isDebugEnabled()) {
                logger.debug("No of card JDBC Connection created......");
            }

            stmt = conn.createStatement();
            rs = stmt.executeQuery(NO_OF_CARDS);

            if (logger.isDebugEnabled()) {
                logger.debug("Max card Id found.....");
            }

            if (rs.next()) {
                n = rs.getInt(1);
            } else {
                n = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching card Ids."
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
                if (logger.isDebugEnabled()) {
                    logger.debug("prob in closing no_of_card jdbc connection....");
                }
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return n;
    }

    public void deleteAllCategoryCards(String subcategoryId) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("Entering deleteAllCategoryCards in Card.java");
        }
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = jdbcConn.getConnection();

            if (logger.isDebugEnabled()) {
                logger.debug("Delete All Cards in a category jdbc Connection created......");
            }

            pstmt = conn.prepareStatement(DELETE_ALL_CARDS_OF_A_SUBCATEGORY);
            pstmt.setString(1, "1");
            pstmt.setString(2, subcategoryId);
            pstmt.executeUpdate();

            if (logger.isDebugEnabled()) {
                logger.debug("All Cards deleted successfully....");
            }
        } catch (Exception ex) {

            throw new Exception("Problem encountered while deleting all cards."
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
                if (logger.isDebugEnabled()) {
                    logger.debug("Problem in closing delete Allcard JDBC connection.......");
                }
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }
}

