<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Add new Room</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/chat.css">
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
                <%@include file="template_header_home.jsp"%>
                <div align="center">
                    <center>
                        <form action="<%=request.getContextPath()%>/ManageChatServlet" method="post">
                            <table width="80%" cellpadding="0" cellspacing="0" border="0">
                                <%
                                            Object e = request.getAttribute("error");
                                            String error = null;
                                            if (e != null) {
                                                error = (String) e;
                                %>
                                <tr>
                                    <td colspan="2"><h3 class="error"><%=error%></h3></td>
                                </tr>
                                <%
                                            }
                                %>
                                <tr>
                                    <td colspan="2"><h2>Add new Room</h2></td>
                                </tr>
                                <tr>
                                    <td><b>Room Name (no spaces)</b></td><td><input type="text" name="rn"></td>
                                </tr>
                                <tr>
                                    <td><b>Description</b></td><td><textarea rows="5" cols="30" name="rd"></textarea></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td><td><input type="submit" value="Submit"></td>
                                </tr>
                            </table>
                        </form>
                    </center>
                </div>
                <%@include file="template_footer.jsp"%>
            </c:when>
            <c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
                <jsp:include page="/Login.jsp"/>
            </c:otherwise>
        </c:choose>
    </body>
</html>