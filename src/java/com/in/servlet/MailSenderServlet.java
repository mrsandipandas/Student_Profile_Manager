/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import com.in.utils.SendMailClient;
import com.in.utils.ConstantUtil;
import com.in.VO.EmailTemplateVO;
import com.in.VO.GreetingCardReceiverVO;
import com.in.VO.GreetingCardVO;
import com.in.VO.LinkTypeVO;
import com.in.dbEntry.AddressBook;
import com.in.dbEntry.EmailTemplate;
import com.in.dbEntry.GreetingCard;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sada3260
 */
public class MailSenderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = null;
        EmailTemplate emailTemplate = new EmailTemplate();
        GreetingCardVO greetingCardVO = new GreetingCardVO();
        Random randomGenerator = new Random();
        ConstantUtil constants = new ConstantUtil();
        GreetingCard greetingCard = new GreetingCard();
        AddressBook addressBook = new AddressBook();
        String errorStatus = "Error in sending mail to ";
        try {
//            LinkTypeVO linkTypeVO = (LinkTypeVO) request.getSession().getAttribute("link");
//            String linkType = linkTypeVO.getLinkType();
            String receiverMailIds = (String) request.getParameter("allEmailIds");
            String senderMailId = (String) request.getParameter("senderMailId");
            String senderName = (String) request.getParameter("senderName");
            String messageOfCard = (String) request.getParameter("emailMessage");
            String ecardId = (String) request.getParameter("ecardId");
            String greetingFormat = "1";
            String greetingIsVisited = "0";
            String sendingDateString = (String) request.getParameter("sendingDate");
            String cardType = (String) request.getParameter("cardType");
            String greetingBgColor = (String) request.getParameter("ecardBgColor");

            java.util.Date sendingDate = null;
            java.util.Date today = new Date();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            if (sendingDateString != null && !sendingDateString.equalsIgnoreCase("")) {
//                sendingDate = dateFormat.parse(sendingDateString);
//            } else {
//                sendingDateString = dateFormat.format(today);
//            }

//            System.out.println("Date is : " + dateFormat.format(today));
//            System.out.println("sendingDate = " + sendingDateString);
//            System.out.println("senderMailId = " + senderMailId);
//            System.out.println("senderName = " + senderName);
//            System.out.println("messageOfCard = " + messageOfCard);
//            System.out.println("ecardId = " + ecardId);

            EmailTemplateVO emailTemplateVO = emailTemplate.getEmailTemplate();
            String body = null;
            String subject = null;

            if (emailTemplateVO != null) {
                body = emailTemplateVO.getEmailemplateBody();
                subject = emailTemplateVO.getEmailemplateSubject();
            } else {
                subject = "";
                body = "";
            }

            if(greetingBgColor == null) {
                greetingBgColor = "#FFFFFF";
            }

            greetingCardVO.setGreetingCreateDate(null);
            greetingCardVO.setEcardId(ecardId);
            greetingCardVO.setGreetingCardType(cardType);
            greetingCardVO.setGreetingFormat(greetingFormat);
            greetingCardVO.setGreetingMessage(messageOfCard);
            greetingCardVO.setGreetingSenderEmail(senderMailId);
            greetingCardVO.setGreetingSenderName(senderName);
            greetingCardVO.setGreeting_isVisited(greetingIsVisited);
            greetingCardVO.setGreetingBackgroundColor(greetingBgColor);

            String[] recipientList = new String[1];
            String[] emailList;
            String delimiter = ",";
            emailList = receiverMailIds.split(delimiter);

//            SendMailClient sendMailClient = new SendMailClient();

            try {
                for (int i = 0; i < emailList.length; i++) {
                    GreetingCardReceiverVO greetingCardReceiverVO = new GreetingCardReceiverVO();
                    greetingCardReceiverVO = addressBook.LookUpGreetingCardRecipient(emailList[i]);

                    if (sendingDateString != null && sendingDateString.equalsIgnoreCase("SetSendingDateForSendOnBirthday")) {
                        if (greetingCardReceiverVO != null) {
                            sendingDateString = greetingCardReceiverVO.getBirthday();
                            sendingDate = dateFormat.parse(sendingDateString);
                        } else {
//                            sendingDate = null;
                            sendingDateString = dateFormat.format(today);
                            sendingDate = dateFormat.parse(sendingDateString);
                        }
                        sendingDateString = "SetSendingDateForSendOnBirthday";
                    } else if (sendingDateString != null && !sendingDateString.equalsIgnoreCase("") && !sendingDateString.equalsIgnoreCase("SetSendingDateForSendOnBirthday")) {
                        sendingDate = dateFormat.parse(sendingDateString);
                    } else if (sendingDateString == null || sendingDateString.equalsIgnoreCase("")) {
                        sendingDateString = dateFormat.format(today);
                        sendingDate = dateFormat.parse(sendingDateString);
                        sendingDateString = null;
                    }

                    int randomInt = randomGenerator.nextInt(32767);
                    greetingCardVO.setGreetingSendDate(sendingDate);
                    greetingCardVO.setGreetingReceiverEmail(emailList[i]);
                    if (greetingCardReceiverVO.getName() != null) {
                        greetingCardVO.setGreetingReceiverName(greetingCardReceiverVO.getName());
                    } else {
                        greetingCardVO.setGreetingReceiverName("user");
                    }
                    greetingCardVO.setGreetingRandomNo("" + randomInt);
                    greetingCardVO.setGreetingId(null);

                    String greetingId = greetingCard.addGreetingsCardInDatabase(greetingCardVO.getEcardId(),
                            greetingCardVO.getGreetingSenderName(),
                            greetingCardVO.getGreetingSenderEmail(),
                            greetingCardVO.getGreetingReceiverName(),
                            greetingCardVO.getGreetingReceiverEmail(),
                            greetingCardVO.getGreetingMessage(),
                            greetingCardVO.getGreeting_isVisited(),
                            greetingCardVO.getGreetingRandomNo(),
                            greetingCardVO.getGreetingFormat(),
                            greetingCardVO.getGreetingSendDate(),
                            greetingCardVO.getGreetingCreateDate(),
                            greetingCardVO.getGreetingCardType(),
                            greetingCardVO.getGreetingBackgroundColor());

                    subject = subject.replaceAll(constants.CARD_TYPE, cardType);
                    String mailBody = "<br><p><a href="
                            + PropertyConfiguratorServlet.getProperty(ConstantUtil.CARD_LINK) + greetingId + "&randomId=" + randomInt + "'"
                            + ">Click here to view your card</a></p></body></html>";
                    mailBody = body + mailBody;
                    mailBody = mailBody.replaceAll(constants.CARD_RECIPIENT, greetingCardVO.getGreetingReceiverName());
                    mailBody = mailBody.replaceAll(constants.CARD_SENDER, senderName);

                    System.out.println("mailBody = " + mailBody);

                    recipientList[0] = emailList[i];
                    if (sendingDate != null) {
                        if (dateFormat.format(today).equalsIgnoreCase(dateFormat.format(sendingDate))) {
                            SendMailClient.sendEmail(recipientList, subject, mailBody);
                        }
                    } else {
                        errorStatus = errorStatus + "," + emailList[i];
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Mail send error:::::");
            }
            request.setAttribute("error", errorStatus);
            rd = getServletContext().getRequestDispatcher("/ThankYou.jsp");
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
