/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.in.utils;

import com.in.servlet.PropertyConfiguratorServlet;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailClient {

   public static void sendEmail(String to[], String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.host", PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_HOST));
            props.put("mail.smtp.user", PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_USERNAME));
            props.put("mail.smtp.password", PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_PASSWORD));
            props.put("mail.smtp.port", PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_PORT));
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_USERNAME)));
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
            transport.connect(PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_HOST), PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_USERNAME), PropertyConfiguratorServlet.getProperty(ConstantUtil.SMTP_PASSWORD));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
