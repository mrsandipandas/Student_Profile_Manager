
<%@ page isErrorPage="false" errorPage="error.jsp" import="java.util.Set,java.util.Iterator,java.util.Map,com.in.chat.*"%>
<%

            String nickname = (String) session.getAttribute("nickname");

            if (nickname != null && nickname.length() > 0) {
                ChatRoomList roomList = (ChatRoomList) application.getAttribute("chatroomlist");
                ChatRoom chatRoom = roomList.getRoomOfChatter(nickname);
                if (chatRoom != null) {
                    String msg = request.getParameter("messagebox");

                    if (msg != null && msg.length() > 0) {
                        msg = msg.trim();
                        chatRoom.addMessage(new Message(nickname, msg, new java.util.Date().getTime()));
                    }

%>
<HTML>
    <HEAD>
        <LINK rel="stylesheet" type="text/css" href="chat.css">
        <META http-equiv="pragma" content="no-cache">

        <SCRIPT language="JavaScript" type="text/javascript">
            <!--

            function reload()
            {
                document.getElementById('allMessages').src = document.getElementById('allMessages').src;
            }

            setInterval('reload()', 5000);

            function winopen(path)
            {
                chatterinfo = window.open(path,"chatterwin","scrollbars=no,resizable=yes, width=400, height=300, location=no, toolbar=no, status=no");
                chatterinfo.focus();

            }

            //-->
        </SCRIPT>
    </HEAD>
    <BODY onLoad="document.msg.messagebox.focus();" bgcolor="#FFFFFF">
        <TABLE width="100%" cellpadding="3" cellspacing="0">
            <TR>
                <TD width="40%" align="left" valign="top">
                    <TABLE>
                        <TR>
                        <FORM name="msg" action="chat.jsp" method="post">
                            <TD width="100%">
                                <INPUT type="text" name="messagebox" maxlength="300" size="35">
                                <INPUT type="hidden" name="nickname" value="<%=session.getAttribute("nickname")%>">
                                <INPUT name="submit" type="submit" value="Send">
                            </TD>
                        </FORM>
            </TR>
        </TABLE>
    </TD>
<TD width="60%">
    <TABLE border="1" width="100%" cellpadding="1" cellspacing="0" class="panel">
        <TR align="left" valign="top">
            <TD width="35%" align="center">
                <FORM name="changeRoom" method="post" action="listrooms.jsp">
                    <INPUT type="hidden" name="n" value="<%=nickname%>">
                    <INPUT name="ChangeRoom" type="submit" id="ChangeRoom" value="Change Room">
                </FORM>
            </TD>
            <td width="15%" align="center"><input type="button" value="Refresh" name="refresh" onclick="reload();return false;">
            </td>
            <TD width="15%" align="center">
                <FORM name="find">
                    <INPUT type="button" value="Find" onClick='winopen("find.jsp")'>
                </FORM>
            </TD>
            <td width="35%" align="center">
                <form action="./RegistrationServlet" method="post">
                    <input type="hidden" value="<%=nickname%>" name="username"/>
                    <input type="hidden" value="chatroomLogout" name="editParam"/>
                    <input type="submit" value="Chat Logout">
                </form>
            </td>
        </TR>
    </TABLE>
</TD>
</TR>
</TABLE>
</BODY>
</HTML>
<%
                } else {
                    out.write("<h2 class=\"error\">Your room couldn't be found. You can't send message</h2>");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }
%>
