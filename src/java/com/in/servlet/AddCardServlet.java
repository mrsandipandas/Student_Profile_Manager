/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.dbEntry.Card;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 *
 * @author sada3260
 */
public class AddCardServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(AddCardServlet.class);

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


        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
//            if (!ServletFileUpload.isMultipartContent(request)) {
//                if (logger.isDebugEnabled()) {
//                    logger.error("sorry. No file uploaded");
//                }
//                request.setAttribute("uploadStatus", "failure");
//                rd = getServletContext().getRequestDispatcher("/CardUploadStatus.jsp");
//                rd.forward(request, response);
//            }

            // parse request
            List items = servletFileUpload.parseRequest(request);

            FileItem subcategoryId = (FileItem) items.get(0);
            String ecardSubcategoryId = subcategoryId.getString();

            FileItem name = (FileItem) items.get(1);
            String ecardName = name.getString();

            FileItem description = (FileItem) items.get(2);
            String ecardDescription = description.getString();

            FileItem contributorName = (FileItem) items.get(3);
            String ecardContributorName = contributorName.getString();



            // get uploaded file
            FileItem file = (FileItem) items.get(4);

            if (logger.isDebugEnabled()) {
                logger.debug("ecardSubcategoryId::::::::::" + ecardSubcategoryId);
                logger.debug("ecardName::::::::::" + ecardName);
                logger.debug("ecardDescription::::::::::" + ecardDescription);
                logger.debug("FileContentType::::::::::" + file.getContentType());
                logger.debug("fileSize::::::::::" + file.getSize());
                logger.debug("ecardContributorName::::::::::" + ecardContributorName);
            }

            // Connect to Oracle and upload card
            Card card = new Card();

            if (file.getSize() == 0) {
                request.setAttribute("uploadStatus", "noFileToUpload");
                rd = getServletContext().getRequestDispatcher("/CardUploadStatus.jsp");
                rd.forward(request, response);
            } else if (!file.getContentType().matches("image/.+")) {
                request.setAttribute("uploadStatus", "wrongFileFormat");
                rd = getServletContext().getRequestDispatcher("/CardUploadStatus.jsp");
                rd.forward(request, response);
            } else if (file.getSize() != 0 && file.getContentType().matches("image/.+")) {
                int ecardId = card.addCategoryCards(ecardSubcategoryId, ecardName, ecardDescription, ecardContributorName, file, "0");
                System.out.println("ecard id"+ecardId);
                if (ecardId != -1) {
                    card.addCardImage(ecardId, file);
                    request.setAttribute("uploadStatus", "success");
                    rd = getServletContext().getRequestDispatcher("/CardUploadStatus.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("uploadStatus", "failure");
                    rd = getServletContext().getRequestDispatcher("/CardUploadStatus.jsp");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("uploadStatus", "failure");
                rd = getServletContext().getRequestDispatcher("/CardUploadStatus.jsp");
                rd.forward(request, response);
            }
            //out.println("Photo Added Successfully. <p> <a href='/ImageBlobTest2/ListPhotosServlet'>List Photos </a>");
        } catch (Exception ex) {
            //out.println("Error --> " + ex.getMessage());
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
