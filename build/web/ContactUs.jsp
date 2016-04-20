<%-- 
    Document   : ContactUs
    Created on : Dec 30, 2011, 9:45:21 AM
    Author     : sougata das
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">


        <%@include file="template_header_home.jsp"%>


        <div style="width: 100%;height: 350px; overflow: hidden;">
            <table width="100%">
                <tr valign="top">
                    <td width="50%">
                        <img src="images/mybest1.jpg"  width='300' height='600' border="0"/>
                    </td>
                    <td width="50%">
                        <table cellpadding="5" border="1" width="80%" >
                            <tr>
                                <td>
                                    Phone Number:
                                </td>
                                <td>
                                    9883878945
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>


            </table>
        </div>
        <%@include file="template_footer.jsp"%>
    </c:when>
    <c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>


