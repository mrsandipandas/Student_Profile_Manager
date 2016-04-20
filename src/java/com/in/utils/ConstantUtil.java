/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.utils;

/**
 *
 * @author sada3260
 */
public class ConstantUtil {
    
    //JDBC Database connection
    public static final String DATA_SOURCE = "data.source";
    public static final String JDBC_URL = "JDBC.url";
    public static final String JDBC_DRIVER = "JDBC.driver";
    public static final String JDBC_USERNAME = "JDBC.username";
    public static final String JDBC_PASSWORD = "JDBC.password";

    //Email sending properties
    public static final String SMTP_HOST = "SMTP.host";
    public static final String SMTP_PORT = "SMTP.port";
    public static final String SMTP_USERNAME = "SMTP.username";
    public static final String SMTP_PASSWORD = "SMTP.password";

    //Email template pace holders
    public String CARD_TYPE = "_CARD_TYPE_";
    public String CARD_RECIPIENT = "_CARD_RECIPIENT_";
    public String CARD_SENDER = "_CARD_SENDER_";

    //Emai card link
    public static final String CARD_LINK = "card.link.url";

    //SMS sending properties
    public static final String SMS_REG_MOB_NO = "SMS.reg.mob.no";
    public static final String SMS_REG_MOB_PWD = "SMS.reg.mob.password";
    public static final String SMS_PROXY_GATEWAY_IP = "SMS.host";
    public static final String SMS_PROXY_GATEWAY_PORT = "SMS.port";

    //Emai card link
    public static final String SERVER_URL = "Server.url";
}
