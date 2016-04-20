<%-- 
    Document   : Template
    Created on : Jan 27, 2010, 4:39:54 PM
    Author     : sada3260
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>
            Welcome -:- l NSEC Social Network
        </title>
        <style type="text/css">
            <!--
            body,td,th {
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 11px;
                color: #000000;
            }
            body {
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
                background-image: url(images/body_bg.jpg);
                background-repeat: repeat-x;
            }
            .footer {
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 9px;
                color:#999999
            }
            .userWelcome {
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 10px;
                color:#999999;
                padding-left:10px;
                padding-top:3px;
                white-space:nowrap;
            }
            .contentStyle {
                font-family: Verdana, Arial, Helvetica, sans-serif;
                font-size: 11px;
                color:#666666;
                padding-left:10px;
                padding-top:10px;
            }
            -->
        </style>
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

                function closePopup(){
                    if(newwin && newwin!=null){
                        newwin.close();
                        newwin = null;
                    }
                }
                //-->
        </script>
    </head>

    <body onfocus="closePopup();">
        <table width="792" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
                <td align="left" valign="top"><img alt="" src="images/header.jpg" width="792" height="76" /></td>
            </tr>
            <tr>
                <td align="left" valign="top"><table width="792" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="300" align="left" valign="top" nowrap="nowrap" background="images/02_a.jpg" class="userWelcome">
                                <!--Start loggedin user welcome message-->
		Welcome, ${sessionScope['sessionUserDetails'].firstname} ${sessionScope['sessionUserDetails'].lastname}
                                <!--End loggedin user welcome message-->		</td>
                            <td width="29" align="left" valign="middle"><img alt="" src="images/02_b.jpg" width="29" height="31" /></td>
                            <td align="right" valign="middle" background="images/02_c.jpg"><img alt="" src="images/02_d.jpg" width="412" height="31" /></td>
                        </tr>
                    </table></td>
            </tr>
            <tr>
                <td align="left" valign="top">
                    <table width="792" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="769" align="left" valign="top" bgcolor="#FFFFFF" class="contentStyle">
                                <!--End header section-->
                                
