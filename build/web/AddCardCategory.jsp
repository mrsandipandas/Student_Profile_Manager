<%-- 
    Document   : AddCardCategory
    Created on : Jan 3, 2010, 1:14:51 AM
    Author     : Sandi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a new Card Category</title>
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
    <body style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 11px;color:#C66A6C; font-weight: normal;">
        <table cellpadding="1" cellspacing="1" width="100%">
            <tr>
                <td width="50%" align="left">Enter a new Category Name: </td>
                <td width="50%" align="left">
                    <input type="text" class="inputTextStyle" id="newCategoryTextBox" value="" maxlength="30"/>
                </td>
            </tr>
            <tr>
                <td width="50%">
                    <br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>&nbsp;<br>
                </td>
                <td width="50%">&nbsp;</td>
            </tr>
            <tr>
                <td width="50%" align="right">
                    <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image18','','images/b_cancel_o.gif',1)">
                        <img src="images/b_cancel_u.gif" alt="Cancel" name="Image18" width="81" height="24" border="0" id="Image18" onclick="javascript:window.close();"/>
                    </a>
                </td>
                <td width="50%" align="left">
                    <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image17','','images/b_add_o.gif',1)" >
                        <img src="images/b_add_u.gif" alt="Add Category" name="Image17" width="81" height="24" border="0" id="Image17" onclick="closeAndRedirect();"/>
                    </a>
                </td>
            </tr>
        </table>

        <script language="JavaScript" type="text/JavaScript">
                function closeAndRedirect(){
                    var categoryName = document.getElementById('newCategoryTextBox').value;
                    if(validateSubmit()){
                        window.opener.document.getElementById('newCategoryName').value = categoryName;
                        window.opener.redirectAfterAddCategory();
                    }
                }
        
                function isValueEmpty(value) {
                    return value=="";
                }

                function trimField(str){
                    return str.replace(/^\s+|\s+$/g, '');
                }

                function validateSubmit(){
                    var categoryName = encodeURI(document.getElementById('newCategoryTextBox').value);
                    if(isValueEmpty(trimField(categoryName))){
                        document.getElementById('newCategoryTextBox').value = "";
                        alert("Please enter a Category Name");
                        return false;
                    }
                    else{
                        return true;
                    }
                }
                preloadImages('images/b_add_o.gif','images/b_cancel_o.gif');
        </script>
    </body>
</html>
