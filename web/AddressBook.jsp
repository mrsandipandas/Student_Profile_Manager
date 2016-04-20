<%-- 
    Document   : AddressBook
    Created on : Jan 21, 2010, 6:08:23 PM
    Author     : sada3260
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Recipients</title>
        <script type="text/JavaScript">
            <!--
            function swapImgRestore() {
                var i,x,a=document.sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
            }

            function preloadImages() {
                var d=document; if(d.images){ if(!d.p) d.p=new Array();
                    var i,j=d.p.length,a=preloadImages.arguments; for(i=0; i<a.length; i++)
                        if (a[i].indexOf("#")!=0){ d.p[j]=new Image; d.p[j++].src=a[i];}}
                }

                function findObj(n, d) {
                    var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
                        d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
                    if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
                    for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=findObj(n,d.layers[i].document);
                    if(!x && d.getElementById) x=d.getElementById(n); return x;
                }

                function swapImage() {
                    var i,j=0,x,a=swapImage.arguments; document.sr=new Array; for(i=0;i<(a.length-2);i+=3)
                        if ((x=findObj(a[i]))!=null){document.sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
                }
                //-->
        </script>
        <style type="text/css">
            .inputTextStyle {
                height: 15px;
                width: auto;
                border: 1px solid #CA6F75;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 11px;
                color: #666666;
                padding-top:2px;
            }
        </style>
    </head>
    <body>
        <table width="50%" align="center" cellpadding="2" cellspacing="0">
            <%--window.opener.document.getElementById("TextBox1"). value = 'string from popup'|document.getElementById("").value;--%>
            <form id="memberSearchingForm" name="memberSearchingForm" action="./AddressBookServlet" method="post">
                <tr>
                    <td height="24" align="left" valign="middle" nowrap><b style="font-family: Arial,Helvetica,sans-serif; font-size: 13px;">Firstname:</b></td>
                    <td height="24" align="left" valign="middle" nowrap><b style="font-family: Arial,Helvetica,sans-serif; font-size: 13px;">Surname:</b></td>
                    <td height="24" align="left" valign="middle" nowrap style="width:90px">&nbsp;</td>
                </tr>
                <tr>
                    <td width="150px" height="24" align="left" valign="middle" nowrap><input type="text" class="inputTextStyle" id="firstName" name="firstName" value="${firstName}"/>                  </td>
                    <td width="150px" height="24" align="left" valign="middle" nowrap><input type="text" class="inputTextStyle" id="lastName" name="lastName" value="${lastName}"/>                  </td>
                    <td width="100px" height="24" align="left" valign="middle" nowrap style="width:90px; padding-top:5px">
                        <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image19','','images/b_search_o.gif',1)" onclick="searchAddressBookForm();">
                            <img src="images/b_search_u.gif" alt="Search" name="Image19" width="81" height="24" border="0" id="Image19" />                        </a>                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="left" nowrap>
                        <b style="font-family: Arial,Helvetica,sans-serif; font-size: 11px; color: red;">
                            &nbsp;Tip:                        </b>
                        <b style="font-family: Arial,Helvetica,sans-serif; font-size: 11px; color: blue;">
                            Click on check boxes to choose multiple recipients after search                        
                        </b>
                    </td>
                </tr>
            </form>
            <tr>
                <td colspan="3" nowrap>
                    <br>                </td>
            </tr>
            <tr>
                <td colspan="3" nowrap>
                    <br>                </td>
            </tr>
            <tr>
                <td width="100%" colspan="3" nowrap>
                    <c:forEach var="employeeRecord" items="${recipientList}">
                        <input type="checkbox" value="${employeeRecord.emailId}"  onclick="setEmailInTextBox(this.value);">${employeeRecord.firstname}&nbsp;${employeeRecord.lastName}<br>
                    </c:forEach>                </td>
            </tr>
            <tr>
                <td colspan="3" align="center" nowrap><a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image16','','images/b_close_o.gif',1)"><img src="images/b_close_u.gif" alt="Close" name="Image16" width="81" height="24" border="0" id="Image16"  onclick="javascript:window.close();"/></a></td>
            </tr>
        </table>
    </body>
    <script language="JavaScript" type="text/JavaScript">
            function setEmailInTextBox(emailId){
                if(trimField(window.opener.document.getElementById("allEmailOfRecipients").value) == ""){
                    window.opener.document.getElementById("allEmailOfRecipients").value  = emailId;
                }
                else{
                    window.opener.document.getElementById("allEmailOfRecipients").value  =  window.opener.document.getElementById("allEmailOfRecipients").value + ',' +  emailId;
                }
            }

            function validateFormSubmitOfAddressBook(){
                var firstName = document.getElementById('firstName').value;
                var surName = document.getElementById('lastName').value;

                if(isValueEmpty(trimField(firstName)) && isValueEmpty(trimField(surName))){
                    document.getElementById('firstName').value = "";
                    document.getElementById('lastName').value = "";
                    alert("Please enter Firstname or Surname to search");
                    return false;
                }
                else{
                    return true;
                }
            }

            function searchAddressBookForm(){
                if(validateFormSubmitOfAddressBook()){
                    document.getElementById('memberSearchingForm').submit();
                }
            }

            function isValueEmpty(value) {
                return value=="";
            }

            function trimField(str){
                return str.replace(/^\s+|\s+$/g, '');
            }
            preloadImages('images/b_close_o.gif','images/b_search_o.gif');
    </script>
</html>
