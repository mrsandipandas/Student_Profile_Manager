/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.VO.Users;
import com.in.dbEntry.TestDataAccessor;
import com.in.utils.SendMailClient;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sougata das
 */
public class PasswordSendingServlet extends HttpServlet {

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
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PasswordSendingServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordSendingServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {
            out.close();
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
        try {
            String username = request.getParameter("username").toString();

            String msg = "Email sent successfully to all";
           

            TestDataAccessor tsd = new TestDataAccessor();
            //write method of send mail to all here
            ArrayList<Users> userList = new ArrayList<Users>();
            userList = tsd.getPassword(username);
            Users user = new Users();
            user = tsd.getUserDetails(username);
            String mailBody=user.getPassword();
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
            SendMailClient.sendEmail(to, "UR PASSWORD IS:", mailBody);
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                if (dispatcher != null) {


                    request.setAttribute("msg","sent");
                    dispatcher.include(request, response);
                }
        } catch (Exception ex) {
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
