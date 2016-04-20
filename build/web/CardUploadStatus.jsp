<%--
    Document   : ThankYou
    Created on : Jan 11, 2010, 1:19:46 AM
    Author     : Sandi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Status</title>
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
    </head>
    <body onunload="returnFileUploaded();">
        <c:choose>
            <c:when test="${requestScope.uploadStatus eq 'success'}">
                <center>
                    <h3>Your Card has been uploaded successfully</h3>
                </center>
            </c:when>
            <c:when test="${requestScope.uploadStatus eq 'noFileToUpload'}">
                <center>
                    <h3>No image is selected or file corrupted</h3><br>
                    <h4>Please try again later</h4>
                </center>
            </c:when>
            <c:when test="${requestScope.uploadStatus eq 'wrongFileFormat'}">
                <center>
                    <h3>You are allowed to upload images only</h3><br>
                    <h4>Please try again and upload images</h4>
                </center>
            </c:when>
            <c:otherwise>
                <center>
                    <h3>Sorry card uploading failed</h3>
                    <br>
                    <h4>Please try again later</h4>
                </center>
            </c:otherwise>
        </c:choose>
        <br>
        <br>
        <br>
        <center>
            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image16','','images/b_close_o.gif',1)" onclick="javascript:window.close();">
                <img src="images/b_close_u.gif" alt="Close" name="Image16" width="81" height="24" border="0" id="Image16" />
            </a>
        </center>
    </body>
    <script type="text/javascript">
            function returnFileUploaded(){
                var obj = window.dialogArguments;
                if(${requestScope.uploadStatus eq 'success'}){
                    obj.returnvalue = '1';
                }
                else{
                    obj.returnvalue = '0';
                }
            }

            preloadImages('images/b_close_o.gif');
    </script>
</html>
