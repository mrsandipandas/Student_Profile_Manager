<%-- 
    Document   : Login
    Created on : Mar 7, 2011, 7:04:47 PM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="template_header_login.jsp"%>
<center><h1>LOGIN PAGE</h1></center>
<!--     <div align="center" style="color:red" id="txt"></div>-->
<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
        <center><h1>WELCOME , ${sessionScope['sessionUserDetails'].userName}</h1></center>
        <form action="./LoginServlet?">
            <input type="hidden" name="mode" value="logout"/>
            <input type="submit" align="center" value="Logout">
        </form>
    </c:when>
    <c:otherwise>
        <hr>
        <form action="./LoginServlet" method="post">
            <table border="2" align="center" width="400px">
                <br>
                <tr>
                    <td style="border: 0;">Username</td>
                    <td style="border: 0;"><input type="text" name="userName"></td>
                    <td nowrap style="border: 0;"><c:if test="${message eq 'invalid'}">
                            <h5 style="color: red;">User name and password do not match</h5>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="border: 0;">Password</td>
                    <td style="border: 0;"><input type="password"  name="password" ></td>
                    <td style="border: 0;">
                        <input type="Submit" align="center" value="login">
                    </td>
                </tr>
                <tr>
                    <td align="left" style="border: 0;">
                        <a href="./ForgotPassword.jsp">Forgot your Password?</a>
                    </td>
                    <td align="right" style=" border: 0;">
                        <a href="./Registration.jsp">new users?sign up</a>
                    </td>
                    <td style="border: 0;"></td>
                </tr>
            </table>
        </form>
         <c:if test="${msg eq 'sent'}">
            <h2 align ="center">Your password has been sent to your respective email id .plz enter to login</h2>

        </c:if>

    </c:otherwise>
</c:choose>
<%@include file="template_footer.jsp"%>
