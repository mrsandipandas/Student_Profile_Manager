/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.VO.Users;
import com.in.dbEntry.TestDataAccessor;
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
 * @author sougata das
 */
public class ManageFriends extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mode = request.getParameter("mode");
        if (null != mode && mode.equalsIgnoreCase("showAllFriends")) {
            ArrayList<Users> userList = null;
            TestDataAccessor tsd = new TestDataAccessor();
            try {
                String username = ((Users) request.getSession().getAttribute("sessionUserDetails")).getUserName();
                userList = tsd.getAllFriends(username);
                tsd.addUserLoggedInTime(username);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AvailableUsers.jsp");
                if (dispatcher != null) {
                    request.setAttribute("allUserList", userList); //key value pair
                    dispatcher.include(request, response);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (null!=mode && mode.equalsIgnoreCase("showFriend")) {
            TestDataAccessor tsd = new TestDataAccessor();
            String username = request.getParameter("username");
            Users user = tsd.getUserDetails(username);
            Date d = user.getDob();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = null != d ? sdf.format(d) : "";
            user.setDateStr(date);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserOnlyProfile.jsp");
            if (dispatcher != null) {
                request.setAttribute("user", user);
                request.setAttribute("disabled", "disabled");
                dispatcher.include(request, response);
            }
        }
        else if (null!=mode && mode.equalsIgnoreCase("showChatRoomUserDetails")) {
            TestDataAccessor tsd = new TestDataAccessor();
            String username = request.getParameter("username");
            Users user = tsd.getUserDetails(username);
            Date d = user.getDob();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = null != d ? sdf.format(d) : "";
            user.setDateStr(date);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ChatRoomUserDetials.jsp");
            if (dispatcher != null) {
                request.setAttribute("user", user);
                dispatcher.include(request, response);
            }
        }
        else if(null!=mode && mode.equalsIgnoreCase("exitChat")){
            TestDataAccessor tsd = new TestDataAccessor();
            String username = ((Users) request.getSession().getAttribute("sessionUserDetails")).getUserName();
            //tsd.deleteLoggedInUser(username);
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
