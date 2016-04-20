<%-- 
    Document   : Mailing
    Created on : Mar 7, 2011, 9:25:14 PM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="template_header_login.jsp"%>
<form action="./PasswordSendingServlet" method="post" >
    <div style="width: 100%;height: 250px; overflow: hidden;">

        <table  width="100%">
            <tr>
                <td width="100%" align="center" valign="top">

                </td>
            </tr>
            <tr align="center">
                <td width="100%" height="250px" align="center" valign="middle">
                    PLEASE PROVIDE YOUR USERNAME: <input type="text" name="username" size="30">
                </td>
            </tr>
        </table>
    </div>

    <div>
        <table  width="100%">
            <tr>
                <td width="100%" align="center"  valign="top">
                    <input type="submit" value="SUBMIT"/>
                </td>
            </tr>

        </table>
       
    </div>
    <%@include file="template_footer.jsp"%>
</form>



