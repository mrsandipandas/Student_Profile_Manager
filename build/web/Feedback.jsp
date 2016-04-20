<%-- 
    Document   : Feedback
    Created on : Dec 30, 2011, 10:17:59 AM
    Author     : sougata das
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">

        <%@include file="template_header_home.jsp"%>

        <form action="./FeedBackServlet" method="post" >
            <div style="width: 100%;height: 250px; overflow: hidden;">

                <table  width="100%">
                    <tr>
                        <td width="100%" align="center" valign="top">
                            PLEASE PROVIDE YOUR FEEDBACK.
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="100%" align="center" valign="middle">
                            <textarea  name="feedback" rows="10" cols="40"></textarea>
                        </td>
                    </tr>
                </table>
            </div>

            <div style="width: 100%;height: 100px; overflow: hidden;">
                <table  width="100%">
                    <tr>
                        <td width="100%" align="center" valign="top">
                            <input type="submit" value="SUBMIT"/>
                        </td>
                    </tr>

                </table>

            </div>
        </form>

                    <c:if test="${note eq 'valid'}">
                        <h2 align ="center">THANK YOU FOR YOUR NECESSARY FEED BACK</h2>
                    </c:if>
        <%@include file="template_footer.jsp"%>
    </c:when>

    <c:otherwise>

        <h2 align="center" style="color: red;">Please login first</h2>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>


