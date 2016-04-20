
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script language="javascript" type="text/javascript" src="tinyfck/tiny_mce.js"></script>
<script language="javascript" type="text/javascript">
    tinyMCE.init({
        mode : "textareas",
        theme : "advanced",
        theme_advanced_buttons1 : "mybutton,bold,italic,underline,separator,justifyleft,justifycenter,justifyright,justifyfull,bullist,numlist,undo,redo",
        theme_advanced_buttons2 : "separator,insertdate,inserttime,separator,forecolor,backcolor",
        theme_advanced_buttons3 : "",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        plugins :"table,advhr,advimage,insertdatetime,preview,zoom,flash,searchreplace,paste",
        theme_advanced_buttons1_add : "fontselect,fontsizeselect",
        //theme_advanced_buttons2_add : "separator,insertdate,inserttime,separator,forecolor,backcolor",
        theme_advanced_buttons2_add_before: "cut,copy,paste,separator,search,replace,separator",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        plugin_insertdate_dateFormat : "%Y-%m-%d",
        plugin_insertdate_timeFormat : "%H:%M:%S",
        extended_valid_elements : "hr[class|width|size|noshade]",
        file_browser_callback : "fileBrowserCallBack",
        paste_use_dialog : false,
        theme_advanced_resizing : true,
        theme_advanced_resize_horizontal : false,
        theme_advanced_link_targets : "_something=My somthing;_something2=My somthing2;_something3=My somthing3;",
        apply_source_formatting : true
    });

    function fileBrowserCallBack(field_name, url, type, win){
        var connector = "../../filemanager/browser.html?Connector=connectors/jsp/connector";
        var enableAutoTypeSelection = true;

        var cType;
        tinyfck_field = field_name;
        tinyfck = win;

        switch (type) {
            case "image":
                cType = "Image";
                break;
            case "flash":
                cType = "Flash";
                break;
            case "file":
                cType = "File";
                break;
        }

        if (enableAutoTypeSelection && cType) {
            connector += "&Type=" + cType;
        }
        window.open(connector, "tinyfck", "modal,width=600,height=400");
    }
</script>

<%@include file="template_header_home.jsp"%>
<%--The link type is: ${link.linkType}--%>
<form id="form1" action="PreviewGreeting.jsp" method="post">
    <input type="hidden" name="displayCardId" id="displayCardId" value="${param.displayCardId}"/>
    <input type="hidden" id="displayCardDesc" name="displayCardDesc" value="${param.displayCardDesc}"/>
    <input type="hidden" id="displayCardType" name="displaycardType" value="${param.displaycardType}"/>

    <table cellspacing="0" cellpadding="0" border="0" align="center" width="100%">

        <tr>
            <td>
                <br/>
            </td>
        </tr>
        <tr>
            <td>
                <br/>
            </td>
        </tr>
        <c:choose>
            <c:when test="${param.greetingBackGroundColor eq null}">
                <c:set var="backgroundColor" scope="page" value="#EBC4C5"/>
            </c:when>
            <c:otherwise>
                <c:set var="backgroundColor" scope="page" value="${param.greetingBackGroundColor}"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td width="100%" align="center">
                <table id="content_tbl" width="550" border="0" cellspacing="0" cellpadding="10" align="center" bgcolor="${backgroundColor}" >
                    <tr>
                        <td align="center" valign="top">
                            <img alt="${param.displayCardDesc}" width='550' height='400' src="/StudentREG/DisplayCardServlet?ecardId=${param.displayCardId}"/>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="top">
                            <textarea cols="2" rows="2" id="text_content" name="text_content" style="width: 550px; height: 200px">
                                ${param.greetingHiddenMessage}
                            </textarea>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td width="100%" align="center">
                <input type="hidden" name="bg_color_val" id="bg_color_val" value="${backgroundColor}" />
                <input type="button" name="Button" value="Set Background" onclick="show_color_picker(this)" style="font-family:Century Gothic;font-size:11px;font-weight:bold;background-color:#EEAEB0;margin:auto;padding:0px;height: 20px;width:auto;border:1px solid #B1262B;color:#B1262B;" />
            </td>
        </tr>
        <tr>
            <td>
                <br/>
            </td>
        </tr>
        <tr>
            <td align="center">
                <b style="font-family: Arial,Helvetica,sans-serif; font-size: 11px; color: red;">
                    &nbsp;Tip:
                </b>
                <b style="font-family: Arial,Helvetica,sans-serif; font-size: 11px; color: blue;">
                    Please press Shift+Enter to create a single line space
                </b>
            </td>
        </tr>
    </table>
</form>
<br/><br/>
<form id="form2" action="./BackToViewGreetingsCard" method="post">
    <input id="linkTypeHiddenValInCreateGreeting" type="hidden" name="linkTypeHiddenValInCreateGreeting" value="${link.linkType}"/>
</form>
<table  cellspacing="0" cellpadding="0" border="0" align="center" width="88%">
    <tr>
        <td align="right" width="45%">
            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image3','','images/b_back_o.gif',1)" onclick="submitBackForm();">
                <img src="images/b_back_u.gif" alt="Back" name="Image3" width="81" height="24" border="0" id="Image3" />
            </a>
        </td>
        <td align="center" width="10%">

        </td>
        <td align="left" width="45%">
            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image9','','images/b_next_o.gif',1)" onclick="submitForwardForm();">
                <img src="images/b_next_u.gif" alt="Next" name="Image9" width="81" height="24" border="0" id="Image9"/>
            </a>
        </td>
    </tr>
</table>
<!--End main content-->
<%@include file="template_footer.jsp"%>
<script language="javascript" type="text/javascript">
    function submitForwardForm(){
        document.getElementById('form1').submit();
    }

    function submitBackForm(){
        document.getElementById('form2').submit();
    }
    //var myInterval_child = window.setInterval(function (){
    //		var obj_edit = document.getElementById('text_content');
    //		var obj_display = document.getElementById('html_preview');
    //		if((obj_edit!=null || obj_edit!="undefined") && (obj_display!=null || obj_display!="undefined")){obj_display.innerHTML=obj_edit.value;}
    //},100);
    //window.setTimeout(function () {clearInterval(myInterval_child);},6000000);
	
</script>
