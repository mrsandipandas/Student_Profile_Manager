  <%--
    Document   : AllRegisteredUsers
    Created on : Mar 12, 2011, 6:22:36 PM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table align="center" border="0" >
    <c:forEach items="${allUserList}" var="user" varStatus="itr">
        <tr id="div_user_${itr.count}">
            <td valign="top" align="left">
                <a href="#" onclick="$('#userProfileDiv').html('');ajaxRequest('<%=request.getContextPath()%>/ManageFriends?mode=showFriend&username=${user.username}','userProfileDiv');return false;">
                    <img src="./ShowImageServlet?username=${user.username}" alt="" width='50' height='50' border="0"/>
                </a>
            </td>
            <td valign="top" align="left">
                <div id="user_details_dispaly_${user.username}">
                    <table width="100%">
                        <tr>
                            <td align="left" valign="top">
                                ${user.firstname} ${user.lastname}
                                <c:if test="${user.isonline eq 'Y'}">
                                    <img src="img/OnlineIcon.gif" alt="" border="0"/>
                                </c:if>
                            </td>
                            <td align="right" valign="top">
                                <a href="#" onclick="$('#div_user_break_${itr.count}').html(''); $('#div_user_${itr.count}').html('');" style="text-decoration: none;color: blue;">X</a>
                            </td>
                        </tr>
                        <tr>
                            <td align="left" colspan="2">
                                <c:choose>
                                    <c:when test="${user.isonline eq 'Y'}">
                                        <div style="float: left;"><a href="#" onclick="openChatDialog('${user.username}','${user.firstname} ${user.lastname}');return false;" style="text-decoration: none;color: blue;">Chat</a></div>
                                        <div id="chat_message_counter_${user.username}"></div>
                                        <div id="chat_dialog_${user.username}" class="chatDialogClass" style="display: none;">
                                            <table width="100%" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td colspan="2" align="left">
                                                        <div style="height: 100px; width: 100%; overflow: auto; border:1px solid  #808080;" id="chat_display_area_${user.username}"></div><br>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="left">
                                                        <div class="is_typing_div" style="display: none;" id="chat_is_typing_display_area_${user.username}">${user.username} is typing...</div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="80%" align="left">
                                                        <textarea onkeypress="checkEnterPressed('${user.username}',event);" id="chat_msg_area_${user.username}" style="height: 20px; width: 100%; overflow: auto;"></textarea>
                                                    </td>
                                                    <td width="20%" align="right">
                                                        <input type="button" value="Send" onclick="sendAndAppendMessage('${user.username}'); return false;" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <i>Offline</i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
<!--                        <tr>
                            <td align="left" colspan="2">
                                <a href="#" onclick="document.getElementById('birthdayCardForm').submit();return false;">Send Birthday Card</a>
                                <form name="birthdayCardForm" id="birthdayCardForm" action="./MenuNameServlet" method="post">
                                    <input type="hidden" id="senderId" name="senderId" value="${sessionScope['sessionUserDetails'].userName}"/>
                                    <input type="hidden" id="receiverId" name="receiverId" value="${user.username}"/>
                                </form>
                            </td>
                        </tr>-->
                    </table>
                </div>
            </td>
        </tr>
        <tr id="div_user_break_${itr.count}">
            <td colspan="2">
                <hr style="border: 0.2px thin #0063DC;"/>
            </td>
        </tr>
    </c:forEach>
</table>