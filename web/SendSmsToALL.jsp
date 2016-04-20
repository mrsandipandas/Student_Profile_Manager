<%-- 
    Document   : SendSmsToALL
    Created on : Mar 21, 2011, 8:14:05 PM
    Author     : SougataDas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/CommonFunction.js"></script>


<%@include file="template_header_home.jsp"%>
<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
        <h3 style="color: red;" align="center">${fileUploadError}</h3>
        <table width="100%">
            <tr>
                <td width="30%" align="center"><h1 align="center "style="color:black"></h1></td>
                <td width="60%" align="right"></td>
                <td width="10%">
<!--                    <form action="./LoginServlet?">
                        <input type="hidden" name="mode" value="logout"/>
                        <input type="submit" align="center" value="Logout">
                    </form>-->
                </td>
            </tr>
        </table>
        <h1 align="CENTER">SMS SENDING PAGE</h1>
        <form id="smsSenderForm" action="./RegistrationServlet" method="post">
            <input type="hidden" value="${sessionScope['sessionUserDetails'].userName}" name="username"/>
            <input type="hidden" id="editParam" value="" name="editParam"/>
            <table border="0" align="center" width="500px">
                <tr>
                    <td style="border: 0;">MESSAGE</td>
                    <td style="border: 0;">
                        <textarea onkeypress="return imposeMaxLength(this,160)" name="message" cols="50" rows="10" ></textarea>
                    </td>
                <tr>
                    <td align="center" style="border: 0;" colspan="2">
                        <input type="button" value="SendSMS" onclick="submitForm('sendSMSToAll');"/>
                        <input type="button" value="Back" onclick="submitForm('back');"/>
                    </td>
                </tr>
            </table>


        </form>
    </c:when>
    <c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>
<%@include file="template_footer.jsp"%>
<script type="text/javascript">
    function submitForm(param){
        document.getElementById('editParam').value = param;
        document.getElementById('smsSenderForm').submit();
    }
</script>

