/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;
import com.in.dbEntry.Card;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayCardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OutputStream os = response.getOutputStream();
        Card card = new Card();
//        try {
//            String ecardId = request.getParameter("ecardId");
//            Blob  b = card.getCategoryCardImage(ecardId);
//            response.setContentType("image/jpeg");
//            response.setContentLength((int) b.length());
//            InputStream is = b.getBinaryStream();
//            System.out.println("input Stream length *************  = " + is.available());
//            byte buf[] = new byte[(int) b.length()];
//            is.read(buf);
//            os.write(buf);
//            //is.close();
//        }
//        try {
//            String ecardId = request.getParameter("ecardId");
//            Blob  b = card.getCategoryCardImage(ecardId);
//            response.setContentType("image/jpeg");
//            //response.setContentLength( (int) b.length());
//            InputStream is = b.getBinaryStream();
//            int size = is.available();
//            byte buf[] = new byte[size];
//            is.read(buf);
//            os.write(buf);
//            //is.close();
//        }


//        try {
//            String ecardId = request.getParameter("ecardId");
//            Blob  b = card.getCategoryCardImage(ecardId);
//            response.setContentType("image/jpeg");
//            response.setContentLength(1024);
//            InputStream is = b.getBinaryStream();
//            byte buf[] = new byte[1024];
//            is.read(buf);
//            os.write(buf);
//            //is.close();
//        }
        try {
            String ecardId = request.getParameter("ecardId");
            byte[] imgData = card.getCategoryCardImage(ecardId);
            response.setContentType("image/jpeg");
            os.write(imgData);
            os.flush();
      }
      catch (Exception ex) {
            ex.printStackTrace();
            //System.out.println(ex.printStackTrace());
        } finally {
            os.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
