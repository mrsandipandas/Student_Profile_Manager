/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.chat.servlet;

import java.io.*;


import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.in.chat.*;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Admin
 */
public class ChatServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String contextPath;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChatServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChatServlet at " + request.getContextPath () + "</h1>");
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
        //  contextPath = httpservletrequest.getContextPath();
        // httpservletresponse.sendRedirect("/Login.jsp");
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
        contextPath = httpservletrequest.getContextPath();
        HttpSession session = httpservletrequest.getSession();
        String s = httpservletrequest.getParameter("nickname");
        s = s.trim();
        String s1 = httpservletrequest.getParameter("sex");
        if (s1.length() > 0) {
            s1 = s1.trim().toLowerCase();
        }
        if (s != null && s.length() > 1 && s.indexOf(" ") == -1 && s1 != null && (s1.equals("m") || s1.equals("f"))) {
            try {
                ChatRoomList chatroomlist = (ChatRoomList) getServletContext().getAttribute("chatroomlist");
               

//                boolean flag = chatroomlist.chatterExists(s);
//                if (flag) {
//                    httpservletresponse.sendRedirect(contextPath + "/Login.jsp");
//
//                } else {
                    HttpSession httpsession = httpservletrequest.getSession(true);
                    int i = 1800;
                    String s2 = getServletContext().getInitParameter("sessionTimeout");
                    if (s2 != null) {
                        try {
                            i = Integer.parseInt(s2);
                            i *= 60;
                        } catch (NumberFormatException numberformatexception) {
                        }
                    }
                    httpsession.setMaxInactiveInterval(i);
                    httpsession.setAttribute("nickname", s);
                    ChatRoom chatroom = chatroomlist.getRoom("StartUp");
//                    s = s.toLowerCase();
                    Chatter chatter = null;
                    if ("m".equals(s1)) {
                        s1 = "Male";
                    } else {
                        s1 = "Female";
                    }
                    chatter = new Chatter(s, s1, (new Date()).getTime());
                    chatroom.addChatter(chatter);
                    httpservletresponse.sendRedirect(contextPath + "/listrooms.jsp");
//                }
            } catch (Exception exception) {
                System.out.println("Exception thrown in LoginServlet: " + exception.getMessage());
                exception.printStackTrace();
                httpservletresponse.sendRedirect(contextPath + "/error.jsp");
            }
        } else {
            httpservletresponse.sendRedirect(contextPath + "/Login.jsp?ic=t");
        }
    }

    private final void _mththis() {
        contextPath = "";
    }

    public ChatServlet() {
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
