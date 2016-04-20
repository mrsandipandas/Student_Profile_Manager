/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.utils;

import com.in.constants.ConstantUtil;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author SougataDas
 */
public class EmailSender {

    public static void sendEmail(String to[], String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.host", ConstantUtil.SMTP_HOST);
            props.put("mail.smtp.user", ConstantUtil.SMTP_USERNAME);
            props.put("mail.smtp.password", ConstantUtil.SMTP_PASSWORD);
            props.put("mail.smtp.port", ConstantUtil.SMTP_PORT);
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(ConstantUtil.SMTP_USERNAME));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            System.out.println(Message.RecipientType.TO);
            
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(ConstantUtil.SMTP_HOST, ConstantUtil.SMTP_USERNAME, ConstantUtil.SMTP_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
        }
    }
}
