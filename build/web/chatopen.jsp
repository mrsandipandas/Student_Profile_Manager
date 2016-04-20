<%@ page errorPage="error.jsp" %>
<%@ page import="com.in.VO.Users" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<HTML>
    <HEAD>
        <META http-equiv="pragma" content="no-cache">

        <script language="JavaScript">
            <!--
            if(window.top != window.self)
            {
                window.top.location = window.location;
            }
            //-->
        </script>
        <LINK rel="stylesheet" href="<%=request.getContextPath()%>/chat.css">

    </HEAD>
        <c:choose>
            <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
                <%@include file="template_header_home.jsp"%>
                <div style="width: 100%;height: 350px; overflow: hidden;">
                <TABLE width="40%" border="0" cellspacing="1" cellpadding="1" align="center">
                  
                    <TR>
                        <TD colspan="2" class="panel">
                            <FORM  action="<%=request.getContextPath()%>/ChatServlet" method="post">
                                <TABLE width="100%" border="0">
                                    <TR>
                                        <TD width="30%" class="white">
                                            Nickname
                                        </TD>
                                        <TD width="70%">
                                            <INPUT type="text" name="nickname" readonly="true" value="${user.username}" size="15">
                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD width="30%" class="white">
                                            Sex
                                        </TD>
                                        <TD width="70%">
                                            <SELECT size="1" name="sex">
                                                <OPTION value="m">
                                                    Male
                                                </OPTION>
                                                <OPTION value="f">
                                                    Female
                                                </OPTION>
                                            </SELECT>
                                        </TD>
                                    </TR>
                                    <TR>
                                        <TD>
                                            &nbsp;  
                                        </TD>
                                        <TD>
                                            <INPUT type="submit" name="Submit" value="Submit">
                                        </TD>
                                    </TR>
                                </TABLE>
                            </FORM>
                        </TD>
                    </TR>
                </TABLE>
                </div>
            <%@include file="template_footer.jsp"%>
        </c:when>
        <c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
            <jsp:include page="/Login.jsp"/>
        </c:otherwise>
    </c:choose>
</HTML>