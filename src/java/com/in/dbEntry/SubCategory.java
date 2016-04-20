/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import com.in.VO.SubCategoryVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author sada3260
 */
public class SubCategory {
    //private String SUBCATEGORY_SEQ = "select subcategory_seq.nextval from dual";
    private String ADD_SUBCATEGORY_NAMES = "insert into subcategory values(?,?,?,?,?)";
    private String SUBCATEGORY_NAMES = "SELECT * FROM subcategory WHERE subcategory_is_deleted = '0'";
    private String NO_OF_SUBCATEGORIES = "select count(*) from subcategory";
    private String DELETE_SUBCATEGORY_NAMES = "UPDATE subcategory SET subcategory_is_deleted = ? WHERE category_id = ?";

    public ArrayList<SubCategoryVO> getSubCategory() throws Exception {
        System.out.println("Entering get subcategory......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<SubCategoryVO> subCategoryVOs = new ArrayList<SubCategoryVO>();
        try {
            conn = jdbcConn.getConnection();

            System.out.println("SubCategory jdbc Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(SUBCATEGORY_NAMES);

            System.out.println("SubCategory names found.....");

            int i = 0;
            while (rs.next()) {
                System.out.println("\n" + "iterarion" + ++i);

                String subCategoryName = rs.getString("subcategory_name");
                String subCategoryId = rs.getString("subcategory_id");
                String subCategoryIsDeleted = rs.getString("subcategory_is_deleted");
                String emailTemplateId = rs.getString("emailtemplate_id");
                String categoryId = rs.getString("catgory_id");
                
                System.out.println("\n" + subCategoryName + "\n" + subCategoryId + "\n" + subCategoryIsDeleted + "\n" + emailTemplateId + "\n" + categoryId);

                SubCategoryVO subCategoryVO = new SubCategoryVO();

                subCategoryVO.setCategoryId(categoryId);
                subCategoryVO.setEmailTemplateId(emailTemplateId);
                subCategoryVO.setSubCategoryId(subCategoryId);
                subCategoryVO.setSubCategoryName(subCategoryName);
                subCategoryVO.setSubCategory_isDeleted(subCategoryIsDeleted);
                
                subCategoryVOs.add(subCategoryVO);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Proble encountered while searching subcategory Names."
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
                System.out.println("Problem in closing subcategory jdbc connection.......");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return subCategoryVOs;
    }

    public int getNoOfSubCategory() throws Exception {
        System.out.println("Entering get no of  subcategory......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int n = -1;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("No. of subcategory JDBC Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(NO_OF_SUBCATEGORIES);

            System.out.println("Max subcategory Id found.....");

            if (rs.next()) {
                n = rs.getInt(1);
            } else {
                n = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Proble encountered while searching subcatgory Ids."
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
                System.out.println("prob in closing no_of_subcategory jdbc connection....");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return n;
    }

    public void addSubCategory(String categoryId, String emailtemplateId, String subcategoryName) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("AddSubCategory jdbc Connection created......");

            pstmt = conn.prepareStatement(ADD_SUBCATEGORY_NAMES);
            int n = getNoOfSubCategory();

            System.out.println("No of subcategories = " + n);

            if (n != -1) {
                n++;
                pstmt.setString(1, "" + n);
                pstmt.setString(2, categoryId);
                pstmt.setString(3, emailtemplateId);
                pstmt.setString(4, subcategoryName);
                pstmt.setString(5, "0");
                pstmt.executeUpdate();

                System.out.println("SubCategory Added successfully.....");
            }
        } catch (Exception ex) {
            throw new Exception("Problem encountered while adding subcategory."
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
                System.out.println("problem in closing subcategory addition connection");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }

    public void deleteSubCategory(String categoryId) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("Delete SubCategory jdbc Connection created......");

            pstmt = conn.prepareStatement(DELETE_SUBCATEGORY_NAMES);
            pstmt.setString(1, "1");
            pstmt.setString(2, categoryId);
            pstmt.executeUpdate();

        } catch (Exception ex) {

            throw new Exception("Problem encountered while deleting subcategory."
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
                System.out.println("Problem in closing delete subcategory JDBC connection.......");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }
}
