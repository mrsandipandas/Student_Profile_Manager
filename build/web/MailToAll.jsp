<%-- 
    Document   : MailToAll
    Created on : Mar 20, 2011, 8:12:24 PM
    Author     : SougataDas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript">
    tinyMCE.init({
        // General options
        mode : "textareas",
        theme : "advanced",
        plugins : "spellchecker,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template",

        // Theme options
        theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,styleselect,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
        theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
        theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,spellchecker,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,blockquote,pagebreak,|,insertfile,insertimage",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : true,

        // Skin options
        skin : "o2k7",
        skin_variant : "silver"

        // Example content CSS (should be your site CSS)
        //content_css : "css/example.css",

        // Drop lists for link/image/media/template dialogs
        //template_external_list_url : "js/template_list.js",
        //external_link_list_url : "js/link_list.js",
        //external_image_list_url : "js/image_list.js",
        //media_external_list_url : "js/media_list.js"

        // Replace values for the template plugin
        /* template_replace_values : {
            username : "Some User",
            staffid : "991234"
        }*/
    });
</script>
<%@include file="template_header_home.jsp"%>
<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
        <h3 style="color: red;" align="center">${fileUploadError}</h3>
        <table width="100%">
            <tr>
                <td width="30%" align="center"><h1 align="center "style="color:black"></h1></td>
                <td width="60%" align="right"></td>
                <td width="10%">
<!--                    <form action="./LoginServlet?">
                        <input type="hidden" name="mode" value="logout"/>
                        <input type="submit" align="center" value="Logout">
                    </form>-->
                </td>
            </tr>
        </table>
        <h1 align="center">Send E-Mail to all</h1>
        <form id="mailSenderForm" action="./RegistrationServlet">
            <input type="hidden" value="${sessionScope['sessionUserDetails'].userName}" name="username"/>
            <input type="hidden" id="editParam" value="" name="editParam"/>
            <table border="2" align="center" width="500px">
                <tr>
                    <td style="border: 0;">
                        Subject:<input type="text" name="subject"/>
                    </td>
                </tr>
                <tr>
                    <td style="border: 0;">
                        <textarea name="message"></textarea>
                    </td>
                </tr>
                <tr>
                    <td align="center" style="border: 0;" colspan="2">
                        <input type="button" value="SendMail" onclick="submitForm('sendEMailToAll');"/>
                        <input type="button" value="Back" onclick="submitForm('back');"/>
                    </td>
                </tr>
            </table>
        </form>
    </c:when>
    <c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
        <jsp:include page="/Login.jsp"/>
    </c:otherwise>
</c:choose>
<%@include file="template_footer.jsp"%>

<script type="text/javascript">
    function submitForm(param){
        document.getElementById('editParam').value = param;
        document.getElementById('mailSenderForm').submit();
    }
</script>

