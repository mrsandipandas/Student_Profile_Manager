/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.dbEntry.TestDataAccessor;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author sougata das
 */
public class ShowImageServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream os = response.getOutputStream();
        TestDataAccessor tsd = new TestDataAccessor();
        try {
            String username = request.getParameter("username");
            byte[] imgData = tsd.getUserImage(username);
            if (null == imgData) {
//                InputStream fis = new FileInputStream("C:\\no_profile_image.gif");
                InputStream fis = this.getClass().getClassLoader().getResourceAsStream("../../Image/no_profile_image.gif");
                imgData = IOUtils.toByteArray(fis);
            }
            response.setContentType("image/jpeg");
            os.write(imgData);
            os.flush();
        } catch (Exception ex) {//no_profile_image.gif
            InputStream fis = this.getClass().getClassLoader().getResourceAsStream("../../Image/no_profile_image.gif");
            //InputStream fis = new FileInputStream("C:\\no_profile_image.gif");
            byte[] imgData = IOUtils.toByteArray(fis);
            response.setContentType("image/jpeg");
            os.write(imgData);
            //ex.printStackTrace();
            //System.out.println(ex.printStackTrace());
        } finally {
            os.close();
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
