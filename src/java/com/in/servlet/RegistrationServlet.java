/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.dbEntry.TestDataAccessor;
import com.in.VO.Users;
import com.in.chat.ChatRoom;
import com.in.chat.ChatRoomList;
import com.in.chat.Message;
import com.in.utils.SendMailClient;
import com.in.utils.SMSSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author sougata das
 */
public class RegistrationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String mode = request.getParameter("editParam");
            HttpSession session = request.getSession();
            if (null != mode && mode.equalsIgnoreCase("edit")) {
                String username = request.getParameter("username").toString();
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                user = tsd.getUserDetails(username);
                Date d = user.getDob();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = null != d ? sdf.format(d) : "";
                user.setDateStr(date);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "");
                    request.setAttribute("user", user); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("back")) {
                String username = request.getParameter("username").toString();
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                user = tsd.getUserDetails(username);
                Date d = user.getDob();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = null != d ? sdf.format(d) : "";
                user.setDateStr(date);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "disabled");
                    request.setAttribute("user", user); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("chatroom")) {

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/chatopen.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "");
                    request.setAttribute("user", ((Users) request.getSession().getAttribute("sessionUserDetails"))); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("chatroomLogout")) {
                String nickname = (String) session.getAttribute("nickname");
                if (nickname != null && nickname.length() > 0) {
                    ChatRoomList roomlist = (ChatRoomList) getServletContext().getAttribute("chatroomlist");
                    ChatRoom chatRoom = roomlist.getRoomOfChatter(nickname);
                    chatRoom.addMessage(new Message("system", nickname + " has logged out.", new java.util.Date().getTime()));
                    if (chatRoom != null) {
                        chatRoom.removeChatter(nickname);
                        session.removeAttribute("nickname");
//                        out.write("<font color=\"blue\">You successfully logged out</font><br>");
//                        out.write("<a href=\"Login.jsp\">Login again</a>");
                    }
                }
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/chatopen.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "");
                    request.setAttribute("user", ((Users) request.getSession().getAttribute("sessionUserDetails"))); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("sendEMailToAll")) {
                String username = request.getParameter("username").toString();
                String mailBody = request.getParameter("message").toString();
                String subject = request.getParameter("subject").toString();
                String msg = "Email sent successfully to all";
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                //write method of send mail to all here
                ArrayList<Users> userList = new ArrayList<Users>();
                userList = tsd.getAllUsers();
                int countOfUsersHavingEmail = 0;
                for (Users users : userList) {
                    if (null != users.getEmail() && !users.getEmail().equalsIgnoreCase("")) {
                        countOfUsersHavingEmail++;
                    }
                }
                String to[] = new String[countOfUsersHavingEmail];
                int i = 0;
                for (Users users : userList) {

                    if (null != users.getEmail() && !users.getEmail().equalsIgnoreCase("")) {
                        to[i] = users.getEmail();
                    }
                    i++;
                }
                SendMailClient.sendEmail(to, subject, mailBody);
                //End email code
                user = tsd.getUserDetails(username);
                Date d = user.getDob();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = null != d ? sdf.format(d) : "";
                user.setDateStr(date);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "disabled");
                    request.setAttribute("user", user); //key value pair
                    request.setAttribute("emailSentToAllMsg", msg);
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("sendSMSToAll")) {
                String username = request.getParameter("username").toString();
                String smsBody = request.getParameter("message").toString();
                String msg = "SMS sent successfully to all";
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                //write method of send sms to all here
                ArrayList<Users> userList = new ArrayList<Users>();
                userList = tsd.getAllUsers();
                for (Users users : userList) {
                    if (null != users.getPhonenumber() && !users.getPhonenumber().equalsIgnoreCase("")) {
                        SMSSender.sendSMSMessage(users.getPhonenumber(), smsBody);
                    }
                }
                //End sms code
                user = tsd.getUserDetails(username);
                Date d = user.getDob();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = null != d ? sdf.format(d) : "";
                user.setDateStr(date);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "disabled");
                    request.setAttribute("user", user); //key value pair
                    request.setAttribute("emailSentToAllMsg", msg);
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("delete")) {
                String username = request.getParameter("username").toString();
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                int row = tsd.deleteUserDetails(username);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/AllRegisteredUsers");
                rd.forward(request, response);
            } else if (null != mode && mode.equalsIgnoreCase("save")) {
                String username = request.getParameter("username").toString();
                String firstname = request.getParameter("firstname").toString();
                String lastname = request.getParameter("lastname").toString();
                String email = request.getParameter("email").toString();
                String dob = request.getParameter("dob").toString();
                String address = request.getParameter("address").toString();
                String companyname = request.getParameter("companyname").toString();
                String yourrole = request.getParameter("yourrole").toString();
                String webpage = request.getParameter("webpage").toString();
                String phonenumber = request.getParameter("phonenumber").toString();

                Users user = new Users();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                user.setDob(null != dob ? sdf.parse(dob) : null);

                user.setUsername(username);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setEmail(email);
                user.setAddress(address);
                user.setCompanyname(companyname);
                user.setYourrole(yourrole);
                user.setWebpage(webpage);
                user.setPhonenumber(phonenumber);

                TestDataAccessor tsd = new TestDataAccessor();
                tsd.updateUserDetails(user);

                user = tsd.getUserDetails(username);
                Date d = sdf.parse(dob);
                String date = sdf.format(d);
                user.setDateStr(date);

                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                if (dispatcher != null) {
                    request.setAttribute("disabled", "disabled");
                    request.setAttribute("user", user); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("checkUserName")) {
                String username = request.getParameter("username").toString();
                TestDataAccessor tsd = new TestDataAccessor();
                boolean flag = tsd.validateUserName(username);
                request.setAttribute("userNameAvailable", flag);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/ValidationMsg.jsp");
                rd.forward(request, response);
            } else {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload servletFileUpload = new ServletFileUpload(factory);

                List items = servletFileUpload.parseRequest(request);

                FileItem usernameItem = (FileItem) items.get(0);
                String username = usernameItem.getString();

                FileItem firstnameItem = (FileItem) items.get(1);
                String firstname = firstnameItem.getString();

                FileItem lastnameItem = (FileItem) items.get(2);
                String lastname = lastnameItem.getString();

                FileItem emailItem = (FileItem) items.get(3);
                String email = emailItem.getString();

                FileItem dobItem = (FileItem) items.get(4);
                String dob = dobItem.getString();

                FileItem addressItem = (FileItem) items.get(5);
                String address = addressItem.getString();

                FileItem companynameItem = (FileItem) items.get(6);
                String companyname = companynameItem.getString();

                FileItem yourroleItem = (FileItem) items.get(7);
                String yourrole = yourroleItem.getString();

                FileItem phonenumberItem = (FileItem) items.get(8);
                String phonenumber = phonenumberItem.getString();

                FileItem webpageItem = (FileItem) items.get(9);
                String webpage = webpageItem.getString();

                FileItem file = (FileItem) items.get(10);

                FileItem passwordItem = (FileItem) items.get(11);
                String password = passwordItem.getString();

                FileItem confirmpasswordItem = (FileItem) items.get(12);
                String confirmpassword = confirmpasswordItem.getString();

                Users user = new Users();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                user.setDob(!"".equalsIgnoreCase(dob) ? sdf.parse(dob) : null);
                Date d = null != user.getDob() ? user.getDob() : null;
                String date = null != d ? sdf.format(d) : "";
                user.setDateStr(date);
                user.setUsername(username);
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setEmail(email);
                user.setAddress(address);
                user.setCompanyname(companyname);
                user.setYourrole(yourrole);
                user.setWebpage(webpage);
                user.setPhonenumber(phonenumber);
                user.setPassword(password);
                user.setConfirmpassword(confirmpassword);
                user.setIsadmin("N");
                TestDataAccessor tsd = new TestDataAccessor();
                boolean flag = validate(user, request);
                boolean fileValidate = validateFile(file, request);
                if (flag && fileValidate) {
                    tsd.insertIntoDB(user);
                    if (file.getSize() > 0 && file.getContentType().matches("image/.+")) {
                        //upload file
                        //use the following command to set maxm file upload limit in mysql
                        //set global max_allowed_packet = 2M
                        tsd.addUserImage(username, file);
                    }
                    String to[] = new String[1];
                    to[0] = email;
                    SendMailClient.sendEmail(to, "New Registration", "Thank you for registering " + "FIRST NAME:" + firstname + "\n " + "LASTNAME:" + lastname + "\n" + "EMAIL:" + email + "\n" + "ADDRESS:" + address + "\n" + "COMPANY NAME" + companyname + "\n" + "YOUR ROLE:" + yourrole + "\n" + "WEBPAGE:" + webpage + "\n" + "PASSWORD" + password);
 //                  SMSSender.sendSMSMessage(phonenumber, "You are successfully registered " + firstname + " " + lastname);
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                    if (dispatcher != null) {
                        request.setAttribute("disabled", "disabled");
                        session.setAttribute("sessionUserDetails", user);
                        request.setAttribute("user", user); //key value pair
                        dispatcher.include(request, response);
                    }
                } else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Registration.jsp");
                    if (dispatcher != null) {
                        dispatcher.include(request, response);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    private boolean validate(Users user, HttpServletRequest request) {
        boolean flag = false;
        if (!user.getUsername().equalsIgnoreCase("")) {
            request.setAttribute("usernameMsg", "Username cannot be left empty");
            flag = true;
        }
        return flag;
    }

    private boolean validateFile(FileItem file, HttpServletRequest request) {
        boolean flag = false;
        if (file.getContentType().matches("image/.+")) {
            flag = true;
            request.setAttribute("fileUploadMsg", "Upload image file only(jpeg or gif)");
        }
        return flag;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
