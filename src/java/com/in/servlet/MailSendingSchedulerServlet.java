/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;
import com.in.scheduler.MailSendingScheduler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
/**
 *
 * @author sada3260
 */
public class MailSendingSchedulerServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            System.out.println("**********Initializing Mail Sending Scheduler************");
            MailSendingScheduler scheduler = new MailSendingScheduler();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

