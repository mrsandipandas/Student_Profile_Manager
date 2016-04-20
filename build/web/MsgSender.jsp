<%-- 
    Document   : index
    Created on : Mar 22, 2011, 5:08:23 PM
    Author     : SougataDas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@include file="template_header_home.jsp"%>
<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
        <table width="100%">

            <tr>
                <td width="70%" align="center"><h4 align="left">All USER MAIL ID AND PHONE NUMBER</h4></td>
                <td width="20%" align="right"></td>
                <td width="10%">
<!--                    <form action="./LoginServlet?">
                        <input type="hidden" name="mode" value="logout"/>
                        <input type="submit" align="center" value="Logout">
                    </form>-->
                </td>
            </tr>
        </table>
        <h3 style="color: green;" align="center">${msgStatusText}</h3>

        <table align="center" border="0" id="allUserTable">
            <thead style="height: 30px;" bgcolor="#646F5E" align="center">
                <tr>
                    <th width="25" align="left"><font color="white"><input type="checkbox" name="selectAll" onclick="checkBoxMultiSelect('selectAll','selectIndividual');"/></font></th>
                    <th width="150"><font color="white">Username</font></th>
                    <th width="150"><font color="white">Email</font></th>
                    <th width="150"><font color="white">PhoneNumber</font></th>
                    <th width="300"><font color="white">ACTION</font></th>
                </tr>
            </thead>
            <c:forEach items="${allUserList}" var="user">

                <tbody>
                    <tr>
                        <td><input type="checkbox" name="selectIndividual" onclick="checkBoxSync('selectAll',this,'selectIndividual')"/></td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.phonenumber}</td>
                        <td>

                            <c:if test="${sessionScope['sessionUserDetails'].isadmin eq 'Y'}">
                                <input type="button" onclick="sendIndividualSMS('${user.username}')" value="Send SMS" />
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" onclick="sendIndividualMail('${user.username}')" value="Send Mail" />
                            </c:if>

                        </td>
                    </tr>

                </c:forEach>
            </tbody>
            
        </table>
        <table>
            <tr>
                <td align="center" style="border: 0;" colspan="1">
                    <input type="button" value="Group SMS" onclick="sendGroupSMS();"/>
                </td>
                <td align="center" style="border: 0;" colspan="3">
                    <form action="./MsgRegisteredUsersServlet" method="post">
                        <input type="hidden" value="${sessionScope['sessionUserDetails'].userName}" name="username"/>
                        <input type="hidden" value="back" name="mode"/>
                        <input type="submit" value="Back"/>
                    </form>
                </td>
                <td align="center" style="border: 0;" colspan="1">
                    <input type="button" value="Group Email" onclick="sendGroupMail();"/>
                </td>
            </tr>
        </table>
        <table width="100%">
            <tr>
                <td>
                    <div id="basic-modal-content-sms">
                        <h3>Send SMS</h3>
                        <form action="./MsgRegisteredUsersServlet" method="post">
                            <input type="hidden" value="" id="smsusername" name="username"/>
                            <input type="hidden" value="sms" name="mode"/>
                            <table border="0" align="center" width="500px">
                                <tr>
                                    <td style="border: 0;">MESSAGE</td>
                                    <td style="border: 0;">
                                        <textarea onkeypress="return imposeMaxLength(this,160)" name="message" cols="31" rows="4" ></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="border: 0;" colspan="2">
                                        <input type="submit" value="SendSMS"/>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </div>
                </td>
            </tr>
        </table>
        <table width="100%">
            <tr>
                <td>
                    <div id="basic-modal-content-groupsms">
                        <h3>Send Multiple SMS</h3>
                        <form action="./MsgRegisteredUsersServlet" method="post">
                            <input type="hidden" value="" id="smsusername" name="username"/>
                            <input type="hidden" value="groupsms" name="mode"/>
                            <table border="0" align="center" width="500px">
                                <tr>
                                    <td style="border: 0;">MESSAGE</td>
                                    <td style="border: 0;">
                                        <textarea onkeypress="return imposeMaxLength(this,160)" name="message" cols="31" rows="4" ></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="border: 0;" colspan="2">
                                        <input type="submit" value="Send Multiple SMS"/>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </div>
                </td>
            </tr>
        </table>
        <table width="100%">
            <tr>
                <td>
                    <div id="basic-modal-content-email">
                        <h3>Send Email</h3>
                        <form action="./MsgRegisteredUsersServlet" method="post">
                            <input type="hidden" value="" id="emailusername" name="username"/>
                            <input type="hidden" value="email" name="mode"/>
                            <table border="2" align="center" width="500px">
                                <tr>
                                    <td style="border: 0;">
                                        Subject:<input type="text" name="subject"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border: 0;">
                                        <textarea rows="7" cols="50" name="message"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="border: 0;">
                                        <input type="submit" value="SendEmail"/>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </div>
                </td>
            </tr>
        </table>
        <table width="100%">
            <tr>
                <td>
                    <div id="basic-modal-content-groupemail">
                        <h3>Send Group Email</h3>
                        <form action="./MsgRegisteredUsersServlet" method="post">
                            <input type="hidden" value="" id="emailusername" name="username"/>
                            <input type="hidden" value="groupemail" name="mode"/>
                            <table border="2" align="center" width="500px">
                                <tr>
                                    <td style="border: 0;">
                                        Subject:<input type="text" name="subject"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border: 0;">
                                        <textarea rows="7" cols="50" name="message"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="center" style="border: 0;">
                                        <input type="submit" value="Send Group Email"/>
                                    </td>
                                </tr>
                            </table>

                        </form>
                    </div>
                </td>
            </tr>
        </table>
    </c:when>
</c:choose>
<%@include file="template_footer.jsp"%>
