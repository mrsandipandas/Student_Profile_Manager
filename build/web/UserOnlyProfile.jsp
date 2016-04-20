<%-- 
    Document   : UserProfile
    Created on : Mar 12, 2011, 12:50:46 PM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table><tr>
        <td width="35%" align="right" valign="top">
            <img src="./ShowImageServlet?username=${user.username}" alt="${user.firstname} ${user.lastname}" width='200' height='200' border="0"/>
            <br/>
<!--            <a href="#" id="basic-modal">Change picture</a>-->
        </td>
        <td width="65%" align="left" valign="top">

            <table align="left" style="color:RED" border="1">
                <form action="./RegistrationServlet" method="post">
                    <tr>
                        <td style="color: blue;">UserName:</td>
                        <td><input type="text" name="username" disabled value="${user.username}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;">FirstName:</td>
                        <td><input type="text" name="firstname" ${disabled} value="${user.firstname}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;">LastName</td>
                        <td><input type="text" name="lastname" ${disabled} value="${user.lastname}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;" nowrap>DateOfBirth</td>
                        <td><input type="text" id="dob" name="dob" ${disabled} readonly value="${user.dateStr}"/>
                            <a href="#" onclick="cal.select(document.getElementById('dob'),'anchor1','dd/MM/yyyy'); return false;"
                               name="anchor1" id="anchor1">
                                <img alt="Calender" src ="./Image/calendar.jpg" style="border:none;">
                            </a>
                        </td>
                    </tr>

                    <tr>
                        <td style="color: blue;">EmailId:</td>
                        <td><input type="text" name="email" ${disabled} value="${user.email}"/></td>
                    </tr>

                    <tr>
                        <td style="color: blue;">CompanyName:</td>
                        <td><input type="text" name="companyname" ${disabled} value="${user.companyname}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;">WebPage:</td>
                        <td><input type="text" name="webpage" ${disabled} value="${user.webpage}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;">PhoneNumber:</td>
                        <td><input type="text" name="phonenumber" ${disabled} value="${user.phonenumber}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;">YourRole:</td>
                        <td><input type="text" name="yourrole" ${disabled} value="${user.yourrole}"/></td>
                    </tr>
                    <tr>
                        <td style="color: blue;">Address:</td>
                        <td><input type="text" name="address" ${disabled} value="${user.address}"/></td>
                    </tr>
                    <tr>
                        <td>
                            <input type="hidden" value="${user.username}" name="username"/>
                            <input type="hidden" value="save" name="editParam"/>
                            <c:if test="${sessionScope['sessionUserDetails'].isadmin eq 'Y' or sessionScope['sessionUserDetails'].username eq user.username}">
                                <input ${disabled} type="submit" value="Save"/>
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${sessionScope['sessionUserDetails'].isadmin eq 'Y' or sessionScope['sessionUserDetails'].username eq user.username}">
                                <input type="button" onclick="document.getElementById('editUserForm').submit();" value="Edit"/>
                            </c:if>
                        </td>
                    </tr>
                </form>
            </table>
        </td>
    </tr>
</table>
<table width="100%">
    <tr>
        <td>
            <div id="basic-modal-content">
                <h3>Upload Picture</h3>
                <form action="./EditImageServlet" method="post" enctype="multipart/form-data">
                    <table>
                        <tr>
                            <td>
                                <input type="hidden" value="${user.username}" name="username"/>
                                <input type="file" id="imageFile"  name="imageFile" align="left"/>
                            </td>
                            <td>
                                <input type="submit" onclick="return validateFormSubmit();" value="Upload"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <form id="editUserForm" action="./RegistrationServlet" method="post">
                <input type="hidden" value="${user.username}" name="username"/>
                <input type="hidden" value="edit" name="editParam"/>
            </form>
        </td>
    </tr>
</table>