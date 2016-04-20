/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.servlet;

import com.in.VO.ReceipentVO;
import com.in.dbEntry.AddressBook;
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
 * @author sada3260
 */
public class AddressBookServlet extends HttpServlet {
   
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
        RequestDispatcher rd = null;
        AddressBook addressBook = new AddressBook();

        try {
            String first_name = (String) request.getParameter("firstName");
            System.out.println("The first_name is:#########" + first_name);

            String last_name = (String) request.getParameter("lastName");
            System.out.println("The last_name is:#########" + last_name);

            ArrayList<ReceipentVO> receipentVOs = addressBook.LookUp(first_name, last_name);
            //System.out.println("categoryList = " + categoryList);

            //request.setAttribute("categoryList", categoryList);
            request.setAttribute("firstName", first_name);
            request.setAttribute("lastName", last_name);
            request.setAttribute("recipientList", receipentVOs);
            rd = getServletContext().getRequestDispatcher("/AddressBook.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
