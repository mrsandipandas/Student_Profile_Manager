<%-- 
    Document   : ValidationMsg
    Created on : Mar 19, 2011, 7:24:56 PM
    Author     : SougataDas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${userNameAvailable eq 'true'}">
        <input type="hidden" id="validUser" value="Y"/>
        <img src="./Image/correct.jpg" alt="" width="32px" height="32px"/><h4 style="color: green;">Username available</h4>
    </c:when>
    <c:otherwise>
        <input type="hidden" id="validUser" value="N"/>
        <img src="./Image/error.gif" alt="" width="32px" height="32px"/><h4 style="color: red;">Username not available</h4>
    </c:otherwise>
</c:choose>
