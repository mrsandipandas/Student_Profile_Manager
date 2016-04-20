/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.VO.BirthdayCardRecipientVO;
import com.in.VO.CategoryVO;
import com.in.VO.SenderVO;
import com.in.dbEntry.AddressBook;
import com.in.dbEntry.AdminCheck;
import com.in.dbEntry.Category;
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
 * @author Sandi
 */
public class MenuNameServlet extends HttpServlet {

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
        SenderVO senderVO = new SenderVO();
        BirthdayCardRecipientVO birthdayCardRecipientVO = new BirthdayCardRecipientVO();
        request.getSession().removeAttribute("sender");
        request.getSession().removeAttribute("bdayCardReceiver");
        request.getSession().removeAttribute("link");
        try {
            String linkType = new String();
            String senderId = (String) request.getParameter("senderId");
            String receiverId = (String) request.getParameter("receiverId");

            if (senderId != null && receiverId != null) {
                linkType = "BirthDayLink";
            } else if (senderId != null && receiverId == null) {
                linkType = "CardApplicationLink";
            }

            if (linkType.equalsIgnoreCase("BirthDayLink")) {
                //String senderIdForBdayLink = (String) request.getParameter("senderIdForBirthdayLink");
                System.out.println("senderIdForBdayLink $$$$$$$$$= " + senderId);
                //String receiverIdForBdayLink = (String) request.getParameter("receiverIdForBirthdayLink");
                System.out.println("receiverIdForBdayLink########= " + receiverId);
                senderVO = addressBook.LookUpSender(senderId);
                birthdayCardRecipientVO = addressBook.LookUpBirthdayCardRecipient(receiverId);
                request.setAttribute("senderName", senderVO.getSenderName());
                request.setAttribute("senderEmail", senderVO.getSenderEmail());
                request.setAttribute("birthdayCardReceiverName", birthdayCardRecipientVO.getBdayCardRecipientName());
                request.setAttribute("birthdayCardReceiverEmail", birthdayCardRecipientVO.getBdayCardRecipientEmail());
                request.setAttribute("birthdayCardReceiverBDate", birthdayCardRecipientVO.getBdayCardRecipientBday());
            } else if (linkType.equalsIgnoreCase("CardApplicationLink")) {

                System.out.println("senderId#########= " + senderId);
                senderVO = addressBook.LookUpSender(senderId);

                AdminCheck adminCheck = new AdminCheck();
                if (adminCheck.isAdmin(senderId)) {
                    linkType = "Administrator";
                } else {
                    linkType = "User";
                }
                request.setAttribute("senderName", senderVO.getSenderName());
                request.setAttribute("senderEmail", senderVO.getSenderEmail());
            }

            //Check user authenticity
            if (senderVO.getSenderEmail() == null || senderVO.getSenderEmail().equalsIgnoreCase("")) {
                rd = getServletContext().getRequestDispatcher("/ErrorPage.jsp");
                rd.forward(request, response);
            } else {
                Category category = new Category();
                ArrayList<CategoryVO> categoryVOs = new ArrayList<CategoryVO>();
                categoryVOs = category.getCategory();
                System.out.println("LinkType is############" + linkType);
//            System.out.println("senderName#### = " + senderName);
//            System.out.println("senderEmail = " + senderEmail);
//

                request.setAttribute("categoryVOs", categoryVOs);
                request.setAttribute("linkType", linkType);
                request.setAttribute("menuId", "-1");
                rd = getServletContext().getRequestDispatcher("/ViewGreetingsCard.jsp");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Cannot connect database.......................");
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
