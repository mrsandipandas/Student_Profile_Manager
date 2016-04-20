/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.chat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.in.chat.*;

/**
 *
 * @author Admin
 */
public class SaveInfoServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String nickname;
    int age;
    String email;
    String comment;
    HttpSession session;
    String contextPath;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SaveInfoServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaveInfoServlet at " + request.getContextPath () + "</h1>");
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
    protected void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
        nickname = httpservletrequest.getParameter("nickname");
        contextPath = httpservletrequest.getContextPath();
        try {
            Integer integer = Integer.valueOf(httpservletrequest.getParameter("age"));
            age = integer.intValue();
        } catch (NumberFormatException numberformatexception) {
            age = -1;
        }
        email = httpservletrequest.getParameter("email");
        comment = httpservletrequest.getParameter("comment");
        session = httpservletrequest.getSession(true);
        try {
            ChatRoomList chatroomlist = (ChatRoomList) getServletContext().getAttribute("chatroomlist");
            ChatRoom chatroom = chatroomlist.getRoomOfChatter(nickname);
            if (chatroom != null) {
                Chatter chatter = chatroom.getChatter(nickname);
                chatter.setAge(age);
                chatter.setEmail(email);
                chatter.setComment(comment);
                httpservletresponse.setContentType("text/html");
                PrintWriter printwriter = httpservletresponse.getWriter();
                printwriter.write("<html>\n<head>\n<title>Information Saved</title>\n</head>\n<body>\n");
                printwriter.write("<b>Information Saved</b>\n<div align=\"center\">\n<form name=\"closing\">\n");
                printwriter.write("<input type=\"button\" onClick=\"window.close()\" value=\"Close\">\n");
                printwriter.write("</form>\n</div>\n</body>\n</html>");
                printwriter.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Exception: " + exception.getMessage());
            httpservletresponse.sendRedirect(contextPath + "/error.jsp");
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
    protected void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
            throws ServletException, IOException {
        doGet(httpservletrequest, httpservletresponse);
    }

    private final void _mththis() {
        nickname = null;
        age = -1;
        email = null;
        comment = null;
        session = null;
        contextPath = null;
    }

    public SaveInfoServlet() {
        _mththis();
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
