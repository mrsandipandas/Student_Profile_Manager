/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.dbEntry;

import com.in.utils.JdbcConnection;
import com.in.VO.ReceipentVO;
import com.in.VO.EmployeeVO;
import java.util.*;
import com.in.VO.SenderVO;
import com.in.VO.BirthdayCardRecipientVO;
import com.in.VO.GreetingCardReceiverVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author sada3260
 */
public class AddressBook {

    
    public ArrayList list;

    public AddressBook() {
        list = new ArrayList();
    }

    public ArrayList LookUp(String fname, String lname /*, Hashtable param*/) throws Exception {
        
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcConn.getConnection();

            stmt = conn.prepareStatement("select * from users1 where firstname LIKE ? and lastname LIKE ?");
            stmt.setString(1, "%"+fname.trim()+"%");
            stmt.setString(2, "%"+lname.trim()+"%");
            rs = stmt.executeQuery();

            int i = 0;
            while (rs.next()) {
                ReceipentVO detail = new ReceipentVO();

                detail.setFirstname(rs.getString("firstname"));
                detail.setLastName(rs.getString("lastname"));
                detail.setEmailId(rs.getString("email"));

                list.add(detail);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching employees list."
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
        
        Collections.sort(list, new Comparator<ReceipentVO>(){
            public int compare(ReceipentVO prev, ReceipentVO next) {
              return prev.getFirstname().compareTo(next.getFirstname());
            }
        });

        return list;
    }

    public ArrayList<EmployeeVO> LookUpAllEmployees() throws Exception {
        
        ArrayList<EmployeeVO> employeeList = new ArrayList<EmployeeVO>();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcConn.getConnection();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from users1");

            int i = 0;
            while (rs.next()) {
                System.out.println("\n" + "iterarion" + ++i);
                EmployeeVO detail = new EmployeeVO();
                detail.setEmployeeEmail(rs.getString("email"));
                detail.setEmployeeName(rs.getString("firstname") + " " + rs.getString("lastname"));
                detail.setEmployeeNumber(rs.getString("username"));
                detail.setEmployeeUId(rs.getString("username"));
                employeeList.add(detail);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching employees list."
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
        return employeeList;
    }


    public SenderVO LookUpSender(String uid /*, Hashtable param*/) throws Exception{
        SenderVO senderVO = new SenderVO();
        JdbcConnection jdbcConn = new JdbcConnection();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcConn.getConnection();

            stmt = conn.prepareStatement("select * from users1 where username = ?");
            stmt.setString(1, uid);
            rs = stmt.executeQuery();

            int i = 0;
            if (rs.next()) {
               senderVO.setSenderName(rs.getString("firstname") + " " + rs.getString("lastname"));
               senderVO.setSenderEmail(rs.getString("email"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching employees list."
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
        return senderVO;
    }

    public BirthdayCardRecipientVO LookUpBirthdayCardRecipient(String uid /*, Hashtable param*/) throws Exception {
        BirthdayCardRecipientVO birthdayCardRecipientVO = new BirthdayCardRecipientVO();
        JdbcConnection jdbcConn = new JdbcConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcConn.getConnection();

            stmt = conn.prepareStatement("select * from users1 where username = ?");
            stmt.setString(1, uid);
            rs = stmt.executeQuery();

            int i = 0;
            if (rs.next()) {
                System.out.println("\n" + "iterarion" + ++i);
                birthdayCardRecipientVO.setBdayCardRecipientName(rs.getString("firstname")+" "+rs.getString("lastname"));
                birthdayCardRecipientVO.setBdayCardRecipientEmail(rs.getString("email"));
                birthdayCardRecipientVO.setBdayCardRecipientBday(null!=rs.getDate("dob")?sdf.format(rs.getDate("dob")):"");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching employees list."
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
        return birthdayCardRecipientVO;
    }


    public GreetingCardReceiverVO LookUpGreetingCardRecipient(String emailId /*, Hashtable param*/) throws Exception {
        GreetingCardReceiverVO greetingCardReceiverVO = new GreetingCardReceiverVO();
        JdbcConnection jdbcConn = new JdbcConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcConn.getConnection();

            stmt = conn.prepareStatement("select * from users1 where email = ?");
            stmt.setString(1, emailId);
            rs = stmt.executeQuery();

            int i = 0;
            if (rs.next()) {
                System.out.println("\n" + "iterarion" + ++i);
                greetingCardReceiverVO.setName(rs.getString("firstname") + " " + rs.getString("lastname"));
                greetingCardReceiverVO.setBirthday(null!=rs.getDate("dob")?sdf.format(rs.getDate("dob")):"");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Problem encountered while searching employees list."
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
        return greetingCardReceiverVO;
    }

    public String getBirthdayStringOfCurrentYear(String birthdayString) {
        String bdayString = null;
        if (birthdayString != null || birthdayString.equalsIgnoreCase("")) {
            Calendar today = new GregorianCalendar();
            String year = birthdayString.substring(0, 4);
            String month = birthdayString.substring(4, 6);
            String day = birthdayString.substring(6, 8);
            year = "" + today.get(Calendar.YEAR);
            int mm = Integer.parseInt(month);
            int dd = Integer.parseInt(day);
            int yy = Integer.parseInt(year);
            if ((today.get(Calendar.MONTH) + 1) > mm) {
                yy = yy + 1;
            } else if ((today.get(Calendar.MONTH) + 1) == mm) {
                if (today.get(Calendar.DATE) > dd) {
                    yy = yy + 1;
                }
            }
//        System.out.println("year#####" + yy);
//        System.out.println("Month######" + mm);
//        System.out.println("date######" + dd);
//        System.out.println("Calendar.MONTH#####" + today.get(Calendar.MONTH));
//        System.out.println("Calendar.day_of_month#####" + today.get(Calendar.DATE));
            bdayString = day + "/" + month + "/" + yy;
//        System.out.println("Birthday=====" + bdayString);
        }
        return bdayString;
    }
}
