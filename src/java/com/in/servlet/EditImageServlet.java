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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author sougata das
 */
public class EditImageServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Users user = new Users();
        TestDataAccessor tsd = new TestDataAccessor();
        String username = "";
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
            List items = servletFileUpload.parseRequest(request);
            
            FileItem usernameItem = (FileItem) items.get(0);
            username = usernameItem.getString();

            FileItem file = (FileItem) items.get(1);

            if (file.getSize() > 0 && file.getContentType().matches("image/.+")) {
                //upload file
                //use the following command to set maxm file upload limit in mysql
                //set global max_allowed_packet = 2M
                tsd.updateUserImage(username, file);
            }


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
        } catch (Exception ex) {
            //ex.printStackTrace();
            user = tsd.getUserDetails(username);
            Date d = user.getDob();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String date = null != d ? sdf.format(d) : "";
            user.setDateStr(date);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/UserProfile.jsp");
            if (dispatcher != null) {
                request.setAttribute("disabled", "disabled");
                request.setAttribute("user", user); //key value pair
                request.setAttribute("fileUploadError", "File Size exceeded the max limit");
                dispatcher.include(request, response);
            }
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
