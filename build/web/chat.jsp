

<%@ page session="true" import="com.in.chat.*" errorPage="error.jsp"%>
<%
	String nickname = (String)session.getAttribute("nickname");
	if (nickname != null && nickname.length() > 0)
	{
		ChatRoomList roomList = (ChatRoomList) application.getAttribute("chatroomlist");
		ChatRoom room = roomList.getRoomOfChatter(nickname);
		String roomname = room.getName();
%>
	
<HTML>
<HEAD>
</HEAD>
<%@include file="template_header_login.jsp"%>
<iframe id="allMessages" style="width: 770px;height: 350px; overflow: auto;"  src="displayMessages.jsp"></iframe>
<jsp:include page="sendMessage.jsp"/>
<%@include file="template_footer.jsp"%>
</HTML>
<%
}
else
{
	response.sendRedirect("Login.jsp");
}
%>