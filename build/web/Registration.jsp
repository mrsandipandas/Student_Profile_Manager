<%-- 
    Document   : Registration
    Created on : Mar 10, 2011, 8:53:46 AM
    Author     : sougata das
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/common/ajax.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/common/CommonUtil.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/js/common/CommonFunction.js'></script>
        <script type="text/javascript">

            <%@ include file="./js/CalendarPopup.js"%>
                var cal = new CalendarPopup();
                cal.showNavigationDropdowns();

                var fromDate_obj = document.getElementById("sendingDateObj");
        </script>
        <script type="text/javascript">
            function validateFormSubmit(){
                if(document.getElementById('username').value == ''){
                    alert("Please enter username");
                    return false;
                }
                else if(document.getElementById('validUser').value == 'N' || document.getElementById('validUser').value == ''){
                    alert("Please enter a valid username");
                    return false;
                }
                else if(document.getElementById('firstname').value == ''){
                    alert("Please enter firstname");
                    return false;
                }
                else if(document.getElementById('lastname').value == ''){
                    alert("Please enter lastname");
                    return false;
                }
                else if(document.getElementById('email').value == ''){
                    alert("Plz enter email");
                    return false;
                }
                else if(!checkPhoto()){
                    alert("The only acceptable format for photos is JPEG.");
                    return false;
                }
                else{
                    return true;
                }
            }

            function checkPhoto()
            {
                try{
                    var photoFileItem = document.getElementById("imageFile");
                    var parent = photoFileItem.parentNode;
                    var photoFilePath = photoFileItem.value;
                   // alert(photoFilePath);
                    var array = photoFilePath.split("\\");//find file name
                    var photoFileName = array[array.length-1];
                    array = photoFileName.split(".");//find extension
                    var extension = array[array.length-1];
                    if(extension.toLowerCase()=="jpg" || extension.toLowerCase()=="jpeg"){
                        return true;
                    }
                    else{
                        return false;
                    }
                }
                catch(err){
                    return false;
                }
            }

            function trimField(str){
                return str.replace(/^\s+|\s+$/g, '');
            }

            function validateUserName(){
                var userName = document.getElementById('username').value;
                if(userName != ''){
                    ajaxRequest('./RegistrationServlet?editParam=checkUserName&username='+userName,'usernameCheck');
                }
                else{
                    document.getElementById('usernameCheck').innerHTML = "";
                    alert('Plese enter a username');
                }
            }

        </script>
        <style type="text/css">
            body{
                /*  background-color: green;
                  background-image: url('./Images/Sunset.jpg');
                  background-repeat: no-repeat;
                */


                /*       background-image: url('./Image/lake-tree.jpg') ;
                       background-repeat:no-repeat;
                       background-position:center;
                       margin-right:9px; */



            }
            h1
            {
                color:red;
                text-align:center;
            }


            .newStyle{
                background-color: green;
                background-repeat:repeat-y;
            }
            .mandatory{
                color: red;
            }
        </style>
    </head>
    <title>REGISTRATION PAGE</title>
   <%@include file="template_header_login.jsp"%>
   
        <h1 align="center">Registration</h1>
        <form action="./RegistrationServlet" method="post" enctype="multipart/form-data">
            <table border="2" align="center" width="500px">
                <tr>
                    <td style="border: 0;">User Name:<span class="mandatory">*</span></td>
                    <td style="border: 0;" nowrap>
                        <input type="text" name="username" id="username" onkeypress="return isAlphaNumeric(event);"  align="left" size="40" onchange="validateUserName();"/>
                        <div id="usernameCheck"></div>
                    </td>
                    <td nowrap style="border: 0; color: red;">${usernameMsg}</td>
                </tr>
                <tr>
                    <td style="border: 0;">First Name:<span class="mandatory">*</span></td>
                    <td style="border: 0;"><input type="text" name="firstname" id="firstname" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Last Name:<span class="mandatory">*</span></td>
                    <td style="border: 0;"><input type="text" name="lastname" id="lastname" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">EmailAddress<span class="mandatory">*</span>:</td>
                    <td style="border: 0;"><input type="text" onchange="return emailCheck(this)" name="email" id="email" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Date of Birth:<span class="mandatory">*</span></td>
                    <td style="border: 0;" nowrap align="left">
                        <input type="text" id="dob" name="dob" readonly="true" size="40" value=""/>
                        <a href="#" onclick="cal.select(document.getElementById('dob'),'anchor1','dd/MM/yyyy'); return false;"
                           name="anchor1" id="anchor1">
                            <img alt="Calender" src ="./Image/calendar.jpg" style="border:none;">
                        </a>

                    </td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Address</td>
                    <td style="border: 0;"><textarea onkeypress="return imposeMaxLength(this,100)"name="address" cols="31" rows="4" ></textarea></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Company Name:</td>
                    <td style="border: 0;"><input type="text" name="companyname" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Your Role:</td>
                    <td style="border: 0;"><input type="text" name="yourrole" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Phone number:<span class="mandatory"</span>*</td>
                    <td style="border: 0;"><input type="text" maxlength="10" onkeypress="return isNumberKey(event)" name="phonenumber" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">website:</td>
                    <td style="border: 0;"><input type="text" name="webpage" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Upload Image:<span class="mandatory">*</span></td>
                    <td style="border: 0;"><input type="file" id="imageFile"  name="imageFile" align="left"></td>
                    <td nowrap style="border: 0;">${fileUploadMsg}</td>
                </tr>
                <tr>
                    <td style="border: 0;">Password:<span class="mandatory"</span>*</td>
                    <td style="border: 0;"><input type="password" name="password" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>
                <tr>
                    <td style="border: 0;">Confirm Password:<span class="mandatory"</span>*</td>
                    <td style="border: 0;"><input type="password" name="confirmpassword" align="left" size="40"></td>
                    <td nowrap style="border: 0;"></td>
                </tr>

                <tr>
                    <td align="center" style="border: 0;" >
                        <input type="submit" onclick="return validateFormSubmit();" value="Register">
                    </td>
                    <td style="border: 0;">
                        <input type="reset" value="Reset!">
                    </td>
                    <td nowrap style="border: 0;"></td>
            </table>

        </form>
    <%@include file="template_footer.jsp"%>

