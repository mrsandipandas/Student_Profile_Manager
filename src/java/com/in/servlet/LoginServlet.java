/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.servlet;

import com.in.dbEntry.TestDataAccessor;
import com.in.VO.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class LoginServlet extends HttpServlet {
   
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
            out.println("<title>Servlet LoginServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath () + "</h1>");
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
        RequestDispatcher dispatcher = null;
        TestDataAccessor tsd = new TestDataAccessor();
        String mode = request.getParameter("mode");
        if (null != mode && mode.equalsIgnoreCase("logout")) {
            String username = ((Users)request.getSession().getAttribute("sessionUserDetails")).getUserName();
            tsd.deleteLoggedInUser(username);
            request.getSession().removeAttribute("sessionUserDetails");
            request.getSession().invalidate();
        }
        
        dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
        if (null == mode) {
            dispatcher = getServletContext().getRequestDispatcher("/HomePageServlet");
        }
        if (dispatcher != null) {
            dispatcher.include(request, response);
        }

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
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        try {
            TestDataAccessor tsd = new TestDataAccessor();
            Users user = new Users();
            boolean flag = tsd.validateUser(userName, password);
            if (flag) {
                user = tsd.getUserDetails(userName);
                tsd.addUserLoggedInTime(userName);
                Date d = user.getDob();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = null != d ? sdf.format(d) : "";
                user.setDateStr(date);
                dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
                if (dispatcher != null) {
                    session.setAttribute("sessionUserDetails", user);
                    request.setAttribute("user", user);
                    request.setAttribute("disabled", "disabled");
                    dispatcher.include(request, response);
                }
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                if (dispatcher != null) {
                    request.setAttribute("message", "invalid");
                    dispatcher.include(request, response);
                }
            }

        } finally {
            // out.close();
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
