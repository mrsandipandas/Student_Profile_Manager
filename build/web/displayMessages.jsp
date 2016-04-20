
<%@ page isErrorPage="false" errorPage="error.jsp" import="java.util.Set,java.util.Iterator,java.util.Map,java.util.Date,java.text.DateFormat,com.in.chat.*"%>
<%
            String roonName = null;
            String nickname = (String) session.getAttribute("nickname");
            ChatRoomList roomList = null;
            ChatRoom chatRoom = null;
            Chatter chatter = null;
            Message[] messages = null;

            if (nickname != null) {
                try {
                    roomList = (ChatRoomList) application.getAttribute("chatroomlist");
                    roonName = roomList.getRoomOfChatter(nickname).getName();
                    if (roonName != null && roonName != "") {
                        chatRoom = roomList.getRoom(roonName);
                        chatter = chatRoom.getChatter(nickname);
                        if (chatRoom != null) {
                            long enteredAt = chatter.getEnteredInRoomAt();
                            if (enteredAt != -1) {
                                messages = chatRoom.getMessages(enteredAt);
                            } else {
                                messages = chatRoom.getMessages(chatter.getLoginTime());
                            }

                        } else {
                            out.write("<b>Room " + roonName + " not found</b>");
                            out.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                    throw new ServletException("Unable to get handle to ServletContext");
                }

%>
<HTML>
    <HEAD>
        <!--<meta http-equiv="refresh" content="10">-->

        <link rel="stylesheet" type="text/css" href="chat.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.js"></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/modal/basic.js'></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/common/CommonFunction.js"></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/modal/jquery.simplemodal.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/common/ajax.js'></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jqueryUI/jquery-ui.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/notification/sticky.full.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/hoverIntent.js"></script>

        <script language="JavaScript" type="text/javascript">
            <!--

            function hideUserDetailsOfChatRoom(){
                $('#chatRooUserDetailsDiv').html('');
                return false;
            }
            function dispalyUserDetailsOfChatRoom(username){
                $('#chatRooUserDetailsDiv').html('');
                ajaxRequest('<%=request.getContextPath()%>/ManageFriends?mode=showChatRoomUserDetails&username='+username,'chatRooUserDetailsDiv');
                return false;
            }

            function winopen(path)
            {
                chatterinfo = window.open(path,"chatterwin","scrollbars=no,resizable=yes, width=400, height=300, location=no, toolbar=no, status=no");
                chatterinfo.focus();
            }
            //-->
        </script>
    </HEAD>
    <BODY bgcolor="#FFFFFF">
        <table width="100%" border="0">
            <tr>
                <td width="70%" valign="top">

                    <table>
                        <tr>
                            <td>
                                <h3><i><%=(String) session.getAttribute("nickname")%></i> you are in room <b><%=roonName%></b></h3>
                                <%

                                                if (messages != null && messages.length > 0) {
                                                    for (int i = 0; i < messages.length; i++) {
                                                        Message message = (Message) messages[i];
                                                        String chatterName = message.getChatterName();
                                                        String strmsg = message.getMessage();
                                                        long time = message.getTimeStamp();
                                                        Date date = new Date(time);

                                                        if (chatterName.equalsIgnoreCase((String) session.getAttribute("nickname"))) {
                                                            out.write("<font face=\"Arial\" size=\"2\" color=\"blue\"><b>" + chatterName + " (" + DateFormat.getTimeInstance().format(date) + ")&gt;</b></font> " + strmsg + "<br>\n");
                                                        } else if (chatterName.equalsIgnoreCase("system")) {
                                                            out.write("<span class=\"error\">" + strmsg + "</span><br>\n");
                                                        } else {
                                                            out.write("<font face=\"Arial\" size=\"2\"><b>" + chatterName + " (" + DateFormat.getTimeInstance().format(date) + ")&gt;</b></font> " + strmsg + "<br>\n");
                                                        }
                                                    }
                                                    out.write("<a name=\"current\"></a>");
                                                } else {
                                                    out.write("<font color=\"red\" face=\"Arial\" size=\"2\">There are currently no messages in this room</font>");
                                                }
                                                out.write("<a name=\"current\"></a>");
                                %>
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="30%" valign="top">
                    <table width="100%" border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#2C259C">
                                    <tr>
                                        <td>
                                            <span class="white"><%=chatRoom.getNoOfChatters()%> people in this room</span><br>
                                        </td>
                                    </tr>
                                </table>
                                <%
                                                Chatter[] chatters = chatRoom.getChattersArray();
                                                for (int i = 0; i < chatters.length; i++) {
                                                    if (chatters[i].getName().equals(session.getAttribute("nickname"))) {
                                %>
                                <font face="Arial" size="2" color="blue"><%=chatters[i].getName() + " (" + chatters[i].getSex() + ")<br>"%></font>
                                <%
                                                    } else {
                                                        out.write("<font face=\"Arial\" size=\"2\"><a href=\"#\" onmouseout=\"javascript:hideUserDetailsOfChatRoom()\" onmouseover=\"javascript:dispalyUserDetailsOfChatRoom('" + chatters[i].getName() + "')\" title=\"View information about " + chatters[i].getName() + "\">" + chatters[i].getName() + "</a> (" + chatters[i].getSex() + ")</font><br>");
                                                    }
                                                }

                                            } else {
                                                response.sendRedirect("Login.jsp");
                                            }
                                %>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <div style="float: right; width: 300px;height: 100px;" id="chatRooUserDetailsDiv"></div>
    </BODY>
</HTML>