<%-- 
    Document   : DisplayGreetingCard
    Created on : Jan 14, 2010, 11:58:09 AM
    Author     : sada3260
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.in.dbEntry.GreetingCard"%>
<%@ page import="com.in.VO.GreetingCardVO"%>

<%
            GreetingCard greetingCard = new GreetingCard();
            GreetingCardVO greetingCardVO = new GreetingCardVO();
            greetingCardVO = greetingCard.getGreetingCardInformation((String) request.getParameter("greetingCardId"), (String) request.getParameter("randomId"));
            String greetingMessage = greetingCardVO.getGreetingMessage();
            String receiverName = greetingCardVO.getGreetingReceiverName();
            String greetingBgColor = greetingCardVO.getGreetingBackgroundColor();
            String greetingSenderName = greetingCardVO.getGreetingSenderName();
            String greetingCardType = greetingCardVO.getGreetingCardType();

            if (greetingMessage == null) {
                greetingMessage = "";
            }
            if (receiverName == null) {
                receiverName = "";
            }

            pageContext.setAttribute("ecardId", greetingCardVO.getEcardId());
            request.setAttribute("pageType", "displayGreetingPage");
            request.setAttribute("receiverName", receiverName);
            request.setAttribute("greetingBgColor", greetingBgColor);
            request.setAttribute("greetingSenderName", greetingSenderName);
            request.setAttribute("greetingCardType", greetingCardType);
%>

<%@include file="template_header_display_greeting.jsp"%>
<c:choose>
    <c:when test="${ecardId ne null}">
        <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
            <tr>
                <td width="100%" align="center">
                    <table width="550" border="0" cellspacing="0" cellpadding="10" align="center" bgcolor="${greetingBgColor}">
                        <tr>
                            <td align="center" valign="top"><img alt="${param.displayCardDesc}" width='550' height='400' src="./DisplayCardServlet?ecardId=${ecardId}"/></td>
                        </tr>
                        <tr>
                            <td align="left" valign="top">
                                <div id="textAlignment1" style="width:550px; height:100px; border-right:#F4DEE0 0px solid; border-left:#F4DEE0 0px solid; BORDER-BOTTOM: #F4DEE0 0px solid; BORDER-TOP: #F4DEE0 0px solid; PADDING: 5px; OVERFLOW: auto; OVERFLOW-X: hidden; SCROLLBAR-FACE-COLOR: #F4DEE0; SCROLLBAR-HIGHLIGHT-COLOR: #F4DEE0; SCROLLBAR-3DLIGHT-COLOR: #949292; SCROLLBAR-ARROW-COLOR: #949292; SCROLLBAR-DARKSHADOW-COLOR: #949292; SCROLLBAR-BASE-COLOR: #949292;">
                                    <%=greetingMessage%>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td align="right" valign="bottom">
                                <b>You have a ${greetingCardType} card from ${greetingSenderName}</b>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <%
                    greetingCard.updateGreetingCardVisitedStatus((String) request.getParameter("greetingCardId"));
        %>
    </c:when>
    <c:otherwise>
        <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
        <table width="100%">
            <tr>
                <td width="100%" align="center">
                    <b style="font-family: Arial,Helvetica,sans-serif; font-size: 15px; color: red;">
                        Sorry, your greetings card was not found.
                    </b>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>
<%@include file="template_footer.jsp"%>