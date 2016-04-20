<%--
    Document   : UserProfile
    Created on : Mar 12, 2011, 12:50:46 PM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="border: 1px solid #333; ">
    <table>
        <tr>
            <td width="25%" align="left" valign="middle">
                <img src="./ShowImageServlet?username=${user.username}" alt="${user.firstname} ${user.lastname}" width='70' height='70' border="0"/>
            </td>
            <td width="75%" align="left" valign="top">
                <table align="left">
                    <tr>
                        <td><span style="color: blue;font: 100 italic .8em 'FB Armada', sans-serif;font-weight: bold;">${user.firstname} ${user.lastname} (${user.phonenumber})</span></td>
                    </tr>
                    <tr>
                        <td><span style="color: blue;font: 100 italic .8em 'FB Armada', sans-serif;font-weight: bold;">${user.email}</span></td>
                    </tr>
                    <tr>
                        <td>Date Of Birth:  <span style="color: blue;font: 100 italic .8em 'FB Armada', sans-serif;font-weight: bold;">${user.dateStr}</span></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>