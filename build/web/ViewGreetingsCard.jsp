<%-- 
    Document   : Template
    Created on : Jan 27, 2010, 4:39:54 PM
    Author     : sada3260
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="link" class="com.in.VO.LinkTypeVO" scope="session">
    <jsp:setProperty name="link" property="linkType" value="${requestScope.linkType}"/>
</jsp:useBean>

<jsp:useBean id="sender" class="com.in.VO.SenderVO" scope="session">
    <jsp:setProperty name="sender" property="senderName" value="${requestScope.senderName}"/>
    <jsp:setProperty name="sender" property="senderEmail" value="${requestScope.senderEmail}"/>
</jsp:useBean>

<jsp:useBean id="bdayCardReceiver" class="com.in.VO.BirthdayCardRecipientVO" scope="session">
    <jsp:setProperty name="bdayCardReceiver" property="bdayCardRecipientName" value="${requestScope.birthdayCardReceiverName}"/>
    <jsp:setProperty name="bdayCardReceiver" property="bdayCardRecipientEmail" value="${requestScope.birthdayCardReceiverEmail}"/>
    <jsp:setProperty name="bdayCardReceiver" property="bdayCardRecipientBday" value="${requestScope.birthdayCardReceiverBDate}"/>
</jsp:useBean>
<%@include file="template_header_home.jsp"%>
<table cellspacing="0" cellpadding="0" border="0" align="center" width="100%">
    <tr>
        <td width="100%">
            <jsp:include page="Menu.jsp">
                <jsp:param name="userLinkType" value="${link.linkType}"/>
            </jsp:include>
        </td>
    </tr>
    <tr>
        <td width="100%" bgcolor="#FFFFFF" class="contentStyle">
            <c:choose>
                <c:when test="${requestScope.adminButtonState eq 'admin'}">
                    <jsp:include page="ShowCardsForAdmin.jsp">
                        <jsp:param name="userLinkType" value="${link.linkType}"/>
                    </jsp:include>
                </c:when>
                <c:otherwise>
                    <jsp:include page="ShowCards.jsp">
                        <jsp:param name="userLinkType" value="${link.linkType}"/>
                    </jsp:include>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
<%@include file="template_footer.jsp"%>