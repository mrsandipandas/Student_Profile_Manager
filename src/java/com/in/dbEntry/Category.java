/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import java.util.ArrayList;
import com.in.VO.CategoryVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Sandi
 */
public class Category {
    //private String CATEGORY_SEQ = "select category_seq.nextval from dual";

    private String ADD_CATEGORY_NAMES = "insert into category values(?,?,?)";
    private String CATEGORY_NAMES = "SELECT *  FROM category WHERE category_is_deleted = '0'";
    private String NO_OF_CATEGORIES = "select count(*) from category";
    private String DELETE_CATEGORY_NAMES = "UPDATE category SET category_is_deleted = ? WHERE category_id = ?";

    SubCategory subcategory = new SubCategory();

    public ArrayList<CategoryVO> getCategory() throws Exception {
        System.out.println("Entering get category......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
        try {
            conn = jdbcConn.getConnection();

            System.out.println("Category jdbc Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(CATEGORY_NAMES);

            System.out.println("Category names found.....");

            int i = 0;
            while (rs.next()) {
                System.out.println("\n" + "iterarion" + ++i);

                String categoryName = rs.getString("category_name");
                String categoryId = rs.getString("category_id");
                String categoryIsDeleted = rs.getString("category_is_deleted");

                System.out.println("\n" + categoryName + "\n" + categoryId + "\n" + categoryIsDeleted);

                CategoryVO categoryVO = new CategoryVO();
                categoryVO.setCategory_name(categoryName);
                categoryVO.setCategory_id(categoryId);
                categoryVO.setCategory_isDeleted(categoryIsDeleted);
                categoryVOs.add(categoryVO);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching category Names."
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
        return categoryVOs;
    }

    public int getNoOfCategory() throws Exception {
        System.out.println("Entering get no of  category......");
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int n = -1;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("No of category JDBC Connection created......");

            stmt = conn.createStatement();
            rs = stmt.executeQuery(NO_OF_CATEGORIES);

            System.out.println("Max category Id found.....");

            if (rs.next()) {
                n = rs.getInt(1);
            } else {
                n = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching catgory Ids."
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
                System.out.println("prob in closing no_of_category jdbc connection....");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return n;
    }

    public String addCategory(String categoryName) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        String newCategoryId = null ;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("AddCategory jdbc Connection created......");

            pstmt = conn.prepareStatement(ADD_CATEGORY_NAMES);
            int n = getNoOfCategory();

            System.out.println("no of categories = " + n);

            if (n != -1) {
                n++;
                pstmt.setString(1, "" + n);
                pstmt.setString(2, categoryName);
                pstmt.setString(3, "0");
                pstmt.executeUpdate();

                subcategory.addSubCategory(""+n, "1", "default");
                newCategoryId = ""+n;

                System.out.println("Category Added successfully.....");
            }
        } catch (Exception ex) {
            throw new Exception("Problem encountered while adding category."
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
                System.out.println("problem in closing category addition connection");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
        return newCategoryId;
    }

    public void deleteCategory(String categoryId) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getConnection();

            System.out.println("Delete Category jdbc Connection created......");

            subcategory.deleteSubCategory(categoryId);

            pstmt = conn.prepareStatement(DELETE_CATEGORY_NAMES);
            pstmt.setString(1, "1");
            pstmt.setString(2, categoryId);
            pstmt.executeUpdate();

        } catch (Exception ex) {

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
                System.out.println("Problem in closing delete category JDBC connection.......");
                e.printStackTrace();
                throw new Exception(e);
            }
        }
    }
}
