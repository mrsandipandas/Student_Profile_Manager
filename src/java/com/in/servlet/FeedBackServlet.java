/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.VO.Users;
import com.in.dbEntry.TestDataAccessor;
import com.in.utils.SendMailClient;
import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sougata das
 */
public class FeedBackServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


      
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
                try {
            HttpSession session = request.getSession();
            String msg = request.getParameter("feedback").toString();
            Users user = new Users();
            TestDataAccessor tsd = new TestDataAccessor();
            ArrayList<Users> userList = new ArrayList<Users>();
            String username = ((Users) session.getAttribute("sessionUserDetails")).getUserName();
            String firstname = ((Users) session.getAttribute("sessionUserDetails")).getFirstname();
            String lastname = ((Users) session.getAttribute("sessionUserDetails")).getLastname();
            String from = firstname + "" + lastname;
            String email = ((Users) session.getAttribute("sessionUserDetails")).getEmail();
            userList = tsd.getAllAdmins();
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
            SendMailClient.sendEmail(to, email, msg);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Feedback.jsp");
            if (dispatcher != null) {
                request.setAttribute("user", user); //key value pair
                request.setAttribute("note","valid");
                
                dispatcher.include(request, response);
            }



        
    }catch(Exception ex){
                    ex.printStackTrace();

    }
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
