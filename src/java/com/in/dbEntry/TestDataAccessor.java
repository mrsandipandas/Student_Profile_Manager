/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import com.in.VO.Users;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.sql.*;
import javax.imageio.ImageIO;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author sougata das
 */
public class TestDataAccessor {

    public void insertIntoDB(Users userVO) {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("INSERT INTO users1(username,firstname,lastname,email,dob,address,companyname,yourrole,phonenumber,webpage,password,confirmpassword,image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,null)");
            pstmt.setString(1, null != userVO.getUserName() ? userVO.getUserName() : null);
            pstmt.setString(2, null != userVO.getFirstname() ? userVO.getFirstname() : null);
            pstmt.setString(3, null != userVO.getLastname() ? userVO.getLastname() : null);
            pstmt.setString(4, null != userVO.getEmail() ? userVO.getEmail() : null);
            pstmt.setDate(5, userVO.getDob() != null ? new java.sql.Date(userVO.getDob().getTime()) : null);
            pstmt.setString(6, null != userVO.getAddress() ? userVO.getAddress() : null);
            pstmt.setString(7, null != userVO.getCompanyname() ? userVO.getCompanyname() : null);
            pstmt.setString(8, null != userVO.getYourrole() ? userVO.getYourrole() : null);
            pstmt.setString(9, null != userVO.getPhonenumber() ? userVO.getPhonenumber() : null);
            pstmt.setString(10, null != userVO.getWebpage() ? userVO.getWebpage() : null);
            pstmt.setString(11, null != userVO.getPassword() ? userVO.getPassword() : null);
            pstmt.setString(12, null != userVO.getConfirmpassword() ? userVO.getConfirmpassword() : null);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void addUserImage(String username, FileItem profileImage) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getConnection();
            pstmt = conn.prepareStatement("update users1 set image = ? where username = ?");
            //Code for image compression
            BufferedImage imageBuffer = ImageIO.read(profileImage.getInputStream());
            BufferedImage compressedImageBuffer = resize(imageBuffer, 200, 200);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(compressedImageBuffer, "gif", os);
            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
            //end code for image compression
            pstmt.setBinaryStream(1, inputStream, (int) profileImage.getSize());
//            pstmt.setBinaryStream(1, (InputStream) profileImage.getInputStream(), (int) profileImage.getSize());
            pstmt.setString(2, username);
            int rowCount = pstmt.executeUpdate();

            System.out.println("rowCount = " + rowCount);
            System.out.println("Image Added successfully.....");

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

    public void updateUserImage(String username, FileItem profileImage) throws Exception {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = jdbcConn.getConnection();
            pstmt = conn.prepareStatement("update users1 set image = ? where username = ?");
            pstmt.setNull(1, java.sql.Types.BLOB);
            pstmt.setString(2, username);
            int rowCount = pstmt.executeUpdate();
            //Code for image compression
            BufferedImage imageBuffer = ImageIO.read(profileImage.getInputStream());
            BufferedImage compressedImageBuffer = resize(imageBuffer, 300, 300);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(compressedImageBuffer, "gif", os);
            InputStream inputStream = new ByteArrayInputStream(os.toByteArray());
            //end code for image compression
            pstmt = conn.prepareStatement("update users1 set image = ? where username = ?");
            pstmt.setBinaryStream(1, inputStream, (int) profileImage.getSize());
//            pstmt.setBinaryStream(1, (InputStream) profileImage.getInputStream(), (int) profileImage.getSize());
            pstmt.setString(2, username);
            rowCount = pstmt.executeUpdate();

            System.out.println("rowCount = " + rowCount);
            System.out.println("Image Added successfully.....");

        } catch (Exception ex) {
            //ex.printStackTrace();
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

    public BufferedImage resize(BufferedImage img, int newW, int newH) {

        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }

    public ArrayList<Users> getAllUsers() {
        ArrayList<Users> userList = new ArrayList<Users>();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select * from users1");
            rs = pstmt.executeQuery();

            if (null != rs) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setUserName(rs.getString("username"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setDob(rs.getDate("dob"));
                    user.setAddress(rs.getString("address"));
                    user.setCompanyname(rs.getString("companyname"));
                    user.setYourrole(rs.getString("yourrole"));
                    user.setWebpage(rs.getString("webpage"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setIsadmin(rs.getString("isadmin"));
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return userList;
    }

    public ArrayList<Users> getAllFriends(String username) {
        ArrayList<Users> userList = new ArrayList<Users>();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("SELECT usr.* , actvty.lastaccess FROM users1 usr "
                    + "LEFT OUTER JOIN activity actvty ON usr.username = actvty.username "
                    + "WHERE usr.username <> ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (null != rs) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setUserName(rs.getString("username"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setDob(rs.getDate("dob"));
                    user.setAddress(rs.getString("address"));
                    user.setCompanyname(rs.getString("companyname"));
                    user.setYourrole(rs.getString("yourrole"));
                    user.setWebpage(rs.getString("webpage"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setIsadmin(rs.getString("isadmin"));
                    user.setIsonline(null != rs.getTimestamp("lastaccess") ? "Y" : "N");
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return userList;
    }

    public ArrayList<Users> getMultipleUserDetails(String username[]) {
        ArrayList<Users> userList = new ArrayList<Users>();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            String sql = "select * from users1 where ";
            for (int i = 0; i < username.length; i++) {
                sql += "username = ?";
            }
            pstmt = con.prepareStatement(sql);
            for (int i = 0; i < username.length; i++) {
                pstmt.setString(i + 1, username[i]);
            }
            rs = pstmt.executeQuery();

            if (null != rs) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setUserName(rs.getString("username"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setDob(rs.getDate("dob"));
                    user.setAddress(rs.getString("address"));
                    user.setCompanyname(rs.getString("companyname"));
                    user.setYourrole(rs.getString("yourrole"));
                    user.setWebpage(rs.getString("webpage"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setIsadmin(rs.getString("isadmin"));
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return userList;
    }

    public void updateUserDetails(Users userVO) {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("update users1 set firstname=? ,lastname =? ,email=? ,dob=?,address =?,companyname=?,yourrole=?,phonenumber=?,webpage=? where username = ?");
            int index = 1;
            pstmt.setString(index++, null != userVO.getFirstname() ? userVO.getFirstname() : "");
            pstmt.setString(index++, null != userVO.getLastname() ? userVO.getLastname() : "");
            pstmt.setString(index++, null != userVO.getEmail() ? userVO.getEmail() : "");
            pstmt.setDate(index++, userVO.getDob() != null ? new java.sql.Date(userVO.getDob().getTime()) : null);
            pstmt.setString(index++, null != userVO.getAddress() ? userVO.getAddress() : "");
            pstmt.setString(index++, null != userVO.getCompanyname() ? userVO.getCompanyname() : "");
            pstmt.setString(index++, null != userVO.getYourrole() ? userVO.getYourrole() : "");
            pstmt.setString(index++, null != userVO.getPhonenumber() ? userVO.getPhonenumber() : "");
            pstmt.setString(index++, null != userVO.getWebpage() ? userVO.getWebpage() : "");
            pstmt.setString(index++, userVO.getUserName());
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public Users getUserDetails(String userName) {
        Users user = new Users();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select * from users1 where username = ?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (null != rs) {
                if (rs.next()) {
                    user.setUserName(null != rs.getString("username") ? rs.getString("username") : null);
                    user.setFirstname(null != rs.getString("firstname") ? rs.getString("firstname") : null);
                    user.setLastname(null != rs.getString("lastname") ? rs.getString("lastname") : null);
                    user.setDob(null != rs.getDate("dob") ? rs.getDate("dob") : null);
                    user.setAddress(null != rs.getString("address") ? rs.getString("address") : null);
                    user.setEmail(null != rs.getString("email") ? rs.getString("email") : null);
                    user.setPhonenumber(null != rs.getString("phonenumber") ? rs.getString("phonenumber") : null);
                    user.setCompanyname(null != rs.getString("companyname") ? rs.getString("companyname") : null);
                    user.setYourrole(null != rs.getString("yourrole") ? rs.getString("yourrole") : null);
                    user.setWebpage(null != rs.getString("webpage") ? rs.getString("webpage") : null);
                    user.setPassword(null != rs.getString("password") ? rs.getString("password") : null);

                    user.setIsadmin(rs.getString("isadmin"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return user;
    }

    public void addUserLoggedInTime(String username) {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("delete from activity where username = ?");
            pstmt.setString(1, username);
            pstmt.execute();

            pstmt = con.prepareStatement("insert into activity (username) values(?)");
            pstmt.setString(1, username);
            pstmt.execute();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public void deleteLoggedInUser(String username) {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("delete from activity where username = ?");
            pstmt.setString(1, username);
            pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    public int deleteUserDetails(String userName) {
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("delete from users1 where username = ?");
            pstmt.setString(1, userName);
            rows = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return rows;
    }

    public boolean validateUser(String userName, String password) {
        PreparedStatement pstmt = null;
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select * from users1 where userName= ? and password= ?");
            pstmt.setString(1, null != userName ? userName : "");
            pstmt.setString(2, null != password ? password : "");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                }
            }
        }
        return flag;
    }

    public boolean validateUserName(String userName) {
        PreparedStatement pstmt = null;
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select count(*) from users1 where userName = ? ");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException sqle) {
                }
            }
        }
        return flag;
    }

    public byte[] getUserImage(String username) throws Exception {
        Connection con = null;
        JdbcConnection jdbcConn = new JdbcConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        byte[] imgData = null;
        Blob image = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select image  from users1 where username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                image = rs.getBlob("image");
                if (null != image) {
                    imgData = image.getBytes(1, (int) image.length());
                } else {
                    imgData = null;
                }

            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            throw new Exception("Problem encountered while getting user image."
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
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                // e.printStackTrace();
                //throw new Exception(e);
            }
        }
        return imgData;
    }

    public ArrayList<Users> getAllAdmins() {
        ArrayList<Users> userList = new ArrayList<Users>();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select * from users1 where isadmin='Y'");
            rs = pstmt.executeQuery();

            if (null != rs) {
                while (rs.next()) {
                    Users user = new Users();
                    user.setUserName(rs.getString("username"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setDob(rs.getDate("dob"));
                    user.setAddress(rs.getString("address"));
                    user.setCompanyname(rs.getString("companyname"));
                    user.setYourrole(rs.getString("yourrole"));
                    user.setWebpage(rs.getString("webpage"));
                    user.setPhonenumber(rs.getString("phonenumber"));
                    user.setIsadmin(rs.getString("isadmin"));
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return userList;
    }

    public ArrayList<Users> getPassword(String userName) {


        ArrayList<Users> userList = new ArrayList<Users>();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = jdbcConn.getConnection();
            pstmt = con.prepareStatement("select email from users1 where username=?");
            pstmt.setString(1, userName);
            rs = pstmt.executeQuery();

            if (null != rs) {
                while (rs.next()) {
                    Users user = new Users();
                    
                    user.setEmail(rs.getString("email"));
                    
                    userList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return userList;
    }
}
