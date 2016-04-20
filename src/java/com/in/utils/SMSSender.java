package com.in.utils;

import com.in.servlet.PropertyConfiguratorServlet;
import java.io.IOException;

import com.pwc.sms.Way2SMSSender;

public class SMSSender {

	public static void sendSMSMessage(String mobileNo, String message) {
        try {
            Way2SMSSender sender = new Way2SMSSender();
            System.out.println("About to send SMS to " + mobileNo);
            String reg_mob_no = "9433453859";//PropertyConfiguratorServlet.getProperty(ConstantUtil.SMS_REG_MOB_NO);
            String reg_mob_password = "1192"; //PropertyConfiguratorServlet.getProperty(ConstantUtil.SMS_REG_MOB_PWD);
            //String sms_gateway_ip = PropertyConfiguratorServlet.getProperty(ConstantUtil.SMS_PROXY_GATEWAY_IP);
            //String sms_gateway_port = PropertyConfiguratorServlet.getProperty(ConstantUtil.SMS_PROXY_GATEWAY_PORT);
            int sms_gateway_port_int = 0;
            try{
             //   sms_gateway_port_int = Integer.parseInt(sms_gateway_port);
            }
            catch(NumberFormatException ex){
                ex.printStackTrace();
                sms_gateway_port_int = 0;
            }
            sender.send(reg_mob_no,reg_mob_password,mobileNo,message,"",sms_gateway_port_int);
            System.out.println("SMS sent Succesfully to" + mobileNo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SMSSender.sendSMSMessage("9433453859", "ebar ami jake ichche sms korte pari");
    }

}
