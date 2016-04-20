/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.in.servlet;

import java.io.*;
import java.net.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.servlet.*;
import javax.servlet.http.*;


public class PropertyConfiguratorServlet extends HttpServlet {

    private static Properties propertyMap = new Properties();

    public static Properties getPropertyMap() {
        return propertyMap;
    }

    public static String getProperty(String property) {
        if (propertyMap != null) {
            return propertyMap.getProperty(property);
        } else {
            return "";
        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("***************Initializing Property Configurator***************");
        try {
            //URL url =  this.getClass().getResource("/com/pwc/in/property/GPILCardApp.properties");
            //propertyMap.load(new FileInputStream(new File(url.getFile())));
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/in/property/StudentApp.properties");
            propertyMap.load(inputStream);
            
//            String propValue = propertyMap.getProperty("LDAP.port");
//            System.out.println("propValue = " + propValue);
//            String propValue = propertyMap.getProperty("Server.url");
//            System.out.println("propValue = " + propValue);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
