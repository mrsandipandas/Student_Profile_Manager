/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.dbEntry.TestDataAccessor;
import com.in.VO.Users;
import com.in.VO.CategoryVO;
import com.in.dbEntry.Category;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author sada3260
 */
public class HomePageServlet extends HttpServlet {

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
//            String linkType = request.getParameter("linkTypeHiddenValInCreateGreeting");
//            System.out.println("**********"+linkType);
            HttpSession session = request.getSession();
            Users sessionUserObj = (Users) session.getAttribute("sessionUserDetails");
            if(null != sessionUserObj && null != sessionUserObj.getUserName()){
                Category category = new Category();
                ArrayList<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
                categoryVOs = category.getCategory();
                request.setAttribute("categoryVOs", categoryVOs);
                request.setAttribute("menuId", "-1");
    //            request.setAttribute("linkType", linkType);


    //            String username = request.getParameter("username").toString();
                String username = sessionUserObj.getUserName();

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
            }
            else{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Login.jsp");
                if (dispatcher != null) {
                    dispatcher.include(request, response);
                }
            }

            
        } catch (Exception ex) {
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
