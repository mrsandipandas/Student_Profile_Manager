<%--
    Document   : AddCard
    Created on : Jan 10, 2010, 10:50:15 PM
    Author     : Sandi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add E-Card Image</title>
        <base target="_self"/>
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
            .inputFileStyle {
                width: auto;
                border: 1px solid #CA6F75;
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 11px;
                color: #666666;
                padding-top:2px;
            }
        </style>
    </head>
    <body onunload="returnFileNotUploaded();"  style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 11px;color:#C66A6C; font-weight: normal;">
        <form action="./AddCardServlet" id="uploadForm" method="post" enctype="multipart/form-data">
            <input  type="hidden" id="subcategoryId" name="subcategoryId"/>
            <table width="100%" cellpadding="1" cellspacing="1">
                <tr>
                    <td>Enter Card Name :</td>
                    <td><input  type="text" class="inputTextStyle" id="ecardName"  name="ecardName" maxlength="30"/></td>
                </tr>
                <tr>
                    <td>Enter Card Description :</td>
                    <td><input  type="text" class="inputTextStyle" id="ecardDescription"  name="ecardDescription" maxlength="100"/></td>
                </tr>
                <tr>
                    <td>Enter Card Contributor Name :</td>
                    <td><input  type="text" class="inputTextStyle" id="ecardContributorName"  name="ecardContributorName" maxlength="30"/></td>
                </tr>
                <tr>
                    <td>Select Card Image :</td>
                    <td><input type="file" class="inputFileStyle" id="ecardImageFile"  name="ecardImageFile" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="left">
                        <b style="font-family: Arial,Helvetica,sans-serif; font-size: 11px; color: red;">
                            &nbsp;Tip:
                        </b>
                        <b style="font-family: Arial,Helvetica,sans-serif; font-size: 11px; color: blue;">
                            It is recommened to upload JPEG image of size 550&#215;400 pixel
                        </b>
                    </td>
                </tr>
                <tr><td colspan="2">&nbsp;<br>&nbsp;<br>&nbsp;</td></tr>
                <tr>
                    <td align="right">
                        <div id="cancelUploadButton">
                            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image18','','images/b_cancel_o.gif',1)">
                                <img src="images/b_cancel_u.gif" alt="Cancel" name="Image18" width="81" height="24" border="0" id="Image18" onclick="javascript:window.close();"/>
                            </a>
                        </div>
                    </td>
                    <td align="left">
                        <div id="uploadButton">
                            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image20','','images/b_upload_pic_o.gif',1)">
                                <img src="images/b_upload_pic_u.gif" alt="Upload E-card Image" name="Image20" width="121" height="24" border="0" id="Image20" onclick="sumbitForm();" />
                            </a>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <table width="100%" cellpadding="1" cellspacing="1" align="center">
            <tr>
                <td align="center">
                    <div id="showStatusMessagePicture" style="display: none;">
                        <img alt="" src="images/uploading_picture.gif"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <div id="showStatusMessage" style="display: none;">
                        Uploading picture.Please wait......
                    </div>
                </td>
            </tr>
        </table>

        <script type="text/javascript">
                function returnFileNotUploaded(){
                    var obj = window.dialogArguments;
                    obj.returnvalue = '0';
                }

                function isValueEmpty(value) {
                    return value=="";
                }

                function validateFormSubmit(){
                    //var cardName = encodeURI(document.getElementById('ecardName').value);
                    var cardName = document.getElementById('ecardName').value;
                    var cardDescription = document.getElementById('ecardDescription').value;
                    var cardContributorName = document.getElementById('ecardContributorName').value;

                    if(isValueEmpty(trimField(cardName))){
                        document.getElementById('ecardName').value = "";
                        alert("Please enter a Card Name");
                        return false;
                    }
                    else if(isValueEmpty(trimField(cardDescription))){
                        document.getElementById('ecardDescription').value = "";
                        alert("Please enter a Card Description ");
                        return false;
                    }
                    else if(isValueEmpty(trimField(cardContributorName))){
                        document.getElementById('ecardContributorName').value = "";
                        alert("Please enter Card Contributor Name");
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

                function trimField(str){
                    return str.replace(/^\s+|\s+$/g, '');
                }

                function sumbitForm(){
                    if(validateFormSubmit()){
                        document.getElementById("cancelUploadButton").style.display ="none";
                        document.getElementById("uploadButton").style.display ="none";
                        document.getElementById("showStatusMessagePicture").style.display ="block";
                        document.getElementById("showStatusMessage").style.display ="block";
                        var obj = window.dialogArguments;
                        document.getElementById('subcategoryId').value = obj.subcategoryId;
                        document.getElementById('uploadForm').submit();
                    }
                }

                function checkPhoto()
                {

                    var photoFileItem = document.getElementById("ecardImageFile");
                    var parent = photoFileItem.parentNode;
                    var photoFilePath = photoFileItem.value;
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


                preloadImages('images/b_cancel_o.gif','images/b_upload_pic_o.gif');

        </script> </body>
</html>
