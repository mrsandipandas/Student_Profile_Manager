/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.scheduler;

import com.in.VO.EmailTemplateVO;
import com.in.VO.GreetingCardVO;
import com.in.dbEntry.EmailTemplate;
import com.in.dbEntry.GreetingCard;
import com.in.utils.ConstantUtil;
import com.in.utils.SendMailClient;
import java.util.ArrayList;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * @author sada3260
 */
public class MailSendingJob implements Job {
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        //GreetingCardVO greetingCardVO = new GreetingCardVO();
        EmailTemplate emailTemplate = new EmailTemplate();
        GreetingCard greetingCard = new GreetingCard();
        ConstantUtil constants = new ConstantUtil();
        try {
            System.out.println("*********Started execting Scheduler at "+new Date()+ "**********");
            greetingCard.purgeGreetingCardTransactionalData();
            ArrayList<GreetingCardVO> greetingCardVOs = greetingCard.getAllGreetingInfoForADay();
            Iterator iter = greetingCardVOs.iterator();
            EmailTemplateVO emailTemplateVO = emailTemplate.getEmailTemplateForScheduler();
            String body = null;
            String subject = null;

            if (emailTemplateVO != null) {
                body = emailTemplateVO.getEmailemplateBody();
                subject = emailTemplateVO.getEmailemplateSubject();
            } else {
                subject = "";
                body = "";
            }

            String[] recipientList = new String[1];
            while (iter.hasNext()) {

                GreetingCardVO greetingVO = ((GreetingCardVO) iter.next());
                String greetingId = greetingVO.getGreetingId();
                String ecardId = greetingVO.getEcardId();
                String senderName = greetingVO.getGreetingSenderName();
                String senderEmail = greetingVO.getGreetingSenderEmail();
                String receiverName = greetingVO.getGreetingReceiverName();
                String receiverEmail = greetingVO.getGreetingReceiverEmail();
                String message = greetingVO.getGreetingMessage();
                String isVisited = greetingVO.getGreeting_isVisited();
                String randomNo = greetingVO.getGreetingRandomNo();
                String format = greetingVO.getGreetingFormat();
                java.util.Date sendDate = greetingVO.getGreetingSendDate();
                java.util.Date createDate = greetingVO.getGreetingCreateDate();
                String cardTpye = greetingVO.getGreetingCardType();
                String bgColor =  greetingVO.getGreetingBackgroundColor();

                System.out.println("GreetingId####:" + greetingId);


                subject = subject.replaceAll(constants.CARD_TYPE, cardTpye);
                String mailBody = "<br><p><a href="
                        + ConstantUtil.CARD_LINK + greetingId + "&randomId=" + randomNo + "'"
                        + ">Click here to view your card</a></p>";
                mailBody = body + mailBody;
                mailBody = mailBody.replaceAll(constants.CARD_RECIPIENT, receiverName);
                mailBody = mailBody.replaceAll(constants.CARD_SENDER, senderName);
                recipientList[0] = receiverEmail;
                SendMailClient.sendEmail(recipientList, subject, mailBody);
                
            }
            System.out.println("*********Stopped execting Scheduler at "+new Date()+ "**********");

        } catch (Exception ex) {
            System.out.println("Error in getting greeting card info for today's date");
            ex.printStackTrace();
        }
    }
}
