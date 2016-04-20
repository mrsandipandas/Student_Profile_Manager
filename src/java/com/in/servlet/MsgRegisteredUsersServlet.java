/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.dbEntry.TestDataAccessor;
import com.in.VO.Users;
import com.in.utils.SendMailClient;
import com.in.utils.SMSSender;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SougataDas
 */
public class MsgRegisteredUsersServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String mode = request.getParameter("mode");
            if (null != mode && mode.equalsIgnoreCase("back")) {
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
            } else if (null != mode && mode.equalsIgnoreCase("sms")) {
                String msgStatus = "";
                String username = request.getParameter("username").toString();
                String smsBody = request.getParameter("message").toString();
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                user = tsd.getUserDetails(username);
                //Send sms to individual
                if (null != user.getPhonenumber() && !user.getPhonenumber().equalsIgnoreCase("")) {
                    SMSSender.sendSMSMessage(user.getPhonenumber(), smsBody);
                    msgStatus = "Message sent successfully to" + user.getFirstname() + " " + user.getLastname();
                } else {
                    msgStatus = "Message not sent..no such number";
                }
                //End

                ArrayList<Users> userList = null;
                userList = tsd.getAllUsers();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MsgSender.jsp");
                if (dispatcher != null) {
                    request.setAttribute("msgStatusText", msgStatus); //msg of sent sms
                    request.setAttribute("allUserList", userList); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("groupsms")) {
                TestDataAccessor tsd = new TestDataAccessor();
                String msgStatus = "";
                String username[] = request.getParameterValues("username");
                String smsBody = request.getParameter("message").toString();
                if (null != username && username.length > 0) {
                    ArrayList<Users> users = tsd.getMultipleUserDetails(username);
                    //Send sms to multiple
                    for (Users user : users) {
                        if (null != user.getPhonenumber() && !user.getPhonenumber().equalsIgnoreCase("")) {
                            SMSSender.sendSMSMessage(user.getPhonenumber(), smsBody);
                            msgStatus += " Message sent to " + user.getFirstname() + " " + user.getLastname();
                        } else {
                            msgStatus += " Message not sent to " + user.getFirstname() + " " + user.getLastname();
                        }
                    }
                    //End
                } else {
                    msgStatus = "No user selected";
                }
                ArrayList<Users> userList = null;
                userList = tsd.getAllUsers();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MsgSender.jsp");
                if (dispatcher != null) {
                    request.setAttribute("msgStatusText", msgStatus); //msg of sent sms
                    request.setAttribute("allUserList", userList); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("groupemail")) {
                TestDataAccessor tsd = new TestDataAccessor();
                String msgStatus = "";
                String username[] = request.getParameterValues("username");
                String mailBody = request.getParameter("message").toString();
                String subject = request.getParameter("subject").toString();
                ArrayList<Users> users = tsd.getMultipleUserDetails(username);
                String to[] = new String[users.size()];

                //Send email to multiple
                int count = 0;
                for (Users user : users) {
                    if (null != user.getEmail() && !user.getEmail().equalsIgnoreCase("")) {
                        to[count++] = user.getEmail();
                    }
                }
                SendMailClient.sendEmail(to, subject, mailBody);
                msgStatus = "Email sent to all successfully";

                //End
                ArrayList<Users> userList = null;
                userList = tsd.getAllUsers();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MsgSender.jsp");
                if (dispatcher != null) {
                    request.setAttribute("msgStatusText", msgStatus); //msg of sent sms
                    request.setAttribute("allUserList", userList); //key value pair
                    dispatcher.include(request, response);
                }
            } else if (null != mode && mode.equalsIgnoreCase("email")) {
                String msgStatus = "";
                String username = request.getParameter("username").toString();
                String mailBody = request.getParameter("message").toString();
                String subject = request.getParameter("subject").toString();
                Users user = new Users();
                TestDataAccessor tsd = new TestDataAccessor();
                user = tsd.getUserDetails(username);
                //Send sms to individual
                if (null != user.getEmail() && !user.getEmail().equalsIgnoreCase("")) {
                    String to[] = new String[1];
                    to[0] = user.getEmail();
                    SendMailClient.sendEmail(to, subject, mailBody);
                    msgStatus = "Email sent successfully to" + user.getFirstname() + " " + user.getLastname();
                } else {
                    msgStatus = "Message not sent..no such email";
                }
                //End

                ArrayList<Users> userList = null;
                userList = tsd.getAllUsers();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MsgSender.jsp");
                if (dispatcher != null) {
                    request.setAttribute("msgStatusText", msgStatus); //msg of sent sms
                    request.setAttribute("allUserList", userList); //key value pair
                    dispatcher.include(request, response);
                }
            } else {
                ArrayList<Users> userList = null;
                TestDataAccessor tsd = new TestDataAccessor();
                userList = tsd.getAllUsers();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MsgSender.jsp");
                if (dispatcher != null) {
                    request.setAttribute("allUserList", userList); //key value pair
                    dispatcher.include(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();


        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";

    }// </editor-fold>
}
