<%-- 
    Document   : AllRegisteredUsers
    Created on : Mar 12, 2011, 6:22:36 PM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<body onload="startTime();">
    <div align="center" style="color:red" id="txt"></div>
    <%@include file="template_header_home.jsp" %>
    <c:choose>
        <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
            <table width="100%">
                <tr>
                    <td width="70%" align="left"><h2 align="left">ALL REGISTERED USERS</h2></td>

                    <td width="10%">
                        <!--                        <form action="./LoginServlet?">
                                                    <input type="hidden" name="mode" value="logout"/>
                                                    <input type="submit" align="center" value="Logout">
                                                </form>-->
                    </td>
                </tr>
            </table>
            <table id="example" align="left" border="0" >
                <thead>
                <th >Name</th>
                <th >Email</th>
                <th >Address</th>
                <th >Company</th>
                <th >Phone Number</th>
                <th >Action</th>
            </thead>
            <tbody>
                <c:forEach items="${allUserList}" var="user">
                    <tr>
                        <td>${user.firstname} ${user.lastname}</td>
                        <td>${user.email}</td>
                        <td>${user.address}</td>
                        <td>${user.companyname}</td>
                        <td>${user.phonenumber}</td>
                        <td>
                            <table width="100%">
                                <tr>
                                    <td width="50%" align="left">
                                        <c:if test="${sessionScope['sessionUserDetails'].isadmin eq 'Y' or sessionScope['sessionUserDetails'].username eq user.username}">
                                            <form action="./RegistrationServlet" method="post">
                                                <input type="hidden" value="${user.username}" name="username"/>
                                                <input type="hidden" value="edit" name="editParam"/>
                                                <input type="submit" value="Edit"/>
                                            </form>
                                        </c:if>
                                    </td>
                                    <td width="50%" align="right">
                                        <c:if test="${sessionScope['sessionUserDetails'].isadmin eq 'Y' and sessionScope['sessionUserDetails'].username ne user.username}">
                                            <form action="./RegistrationServlet" method="post">
                                                <input type="hidden" value="${user.username}" name="username"/>
                                                <input type="hidden" value="delete" name="editParam"/>
                                                <input type="submit" onclick="if(!(confirm('Are you sure to delete this profile?'))){return false;}" value="Delete"/>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>


                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>
<%@include file="template_footer.jsp"%>
