<%-- 
    Document   : UserProfile
    Created on : Mar 12, 2011, 12:50:46 PM
    Author     : sougata das
--%>

<%@page import="com.in.utils.ConstantUtil"%>
<%@page import="com.in.servlet.PropertyConfiguratorServlet"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile Page</title>

<script type="text/javascript">

    <%@ include file="./js/CalendarPopup.js"%>
        var cal = new CalendarPopup();
        cal.showNavigationDropdowns();

        var fromDate_obj = document.getElementById("sendingDateObj");
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common/jquery.js"></script>
<script type="text/javascript">

    <%
                String hostURL = PropertyConfiguratorServlet.getProperty((ConstantUtil.SERVER_URL));

    %>
        var baseURL = "<%=hostURL%>";
        var ajaxTimer;
        var receiveUrl = "/StudentREG/resources/chat/incomingMessage?receiver=${sessionScope['sessionUserDetails'].userName}";
        function signInChat(){
            //this is javascript inner function
            //when signs in and requests the server for any msgs it has to get
            ajaxRequest('<%=request.getContextPath()%>/ManageFriends?mode=showAllFriends','allAvailableFriends');
            $('#enterChatButton').attr('disabled', 'disabled');
            $('#exitChatButton').attr('disabled', '');
            ajaxTimer = window.setInterval(
            function() {
                ajaxRequestWithReturnFunction(receiveUrl,processChatReceiveMessage);
            }, 4000);
            return false;
        }

        function signOutChat(){
            ajaxRequestWithNoReturn('<%=request.getContextPath()%>/ManageFriends?mode=exitChat');
            $('#enterChatButton').attr('disabled', '');
            $('.chatDialogClass').dialog('close');
            $('.chatDialogClass').remove();
            $('#allAvailableFriends').html('');
            $('#exitChatButton').attr('disabled', 'disabled');
            clearTimeout(ajaxTimer);
            ajaxTimer = null;
            return false;
        }

        

        

        function processChatReceiveMessage(response) {
            var obj = JSON.parse(response);
            if(obj.chatMessages.constructor !== Array){
                var chatMessage = obj.chatMessages;
                var senderName = chatMessage.senderName;
                var msg = chatMessage.message;
                var isTyping = chatMessage.isTyping;
                if(isTyping == "true"){
                    if($("#chat_dialog_"+senderName).dialog("isOpen") === true){
                        $("#chat_is_typing_display_area_"+senderName).show();
                    }
                }
                else{
                    $("#chat_is_typing_display_area_"+senderName).hide();
                }
                if(msg != ""){
                    playNotification();
                    $("#chat_is_typing_display_area_"+senderName).hide('fast');
                    $('#chat_display_area_'+senderName).append('<b>'+senderName+': </b>' + msg + '<br/>');
                    $('#chat_display_area_'+senderName).attr('scrollTop', $('#chat_display_area_'+senderName).attr('scrollHeight'));
                    if($("#chat_dialog_"+senderName).dialog("isOpen") !== true){
                        $.sticky('<b>'+senderName+': </b>'+msg);
                        $("#user_details_dispaly_"+senderName).addClass('active_incoming_message_div');
                        $("#chat_message_counter_"+senderName).removeClass('active_incoming_message').addClass(
                        function ()
                        {
                            $(this).addClass('active_incoming_message');
                            var newMsgCount = ($.trim($(this).html()) == '') ? 1 : (1 + parseInt($.trim($(this).html())));
                            $(this).html(newMsgCount);
                        }
                    );
                    }
                }
            }
            else{
                for(var i=0; i<obj.chatMessages.length ; i++) {
                    var chatMessage = obj.chatMessages[i];
                    var senderName = chatMessage.senderName;
                    var msg = chatMessage.message;
                    var isTyping = chatMessage.isTyping;
                    if(isTyping == "true"){
                        if($("#chat_dialog_"+senderName).dialog("isOpen") === true){
                            $("#chat_is_typing_display_area_"+senderName).show();
                        }
                    }
                    else{
                        $("#chat_is_typing_display_area_"+senderName).hide();
                    }
                    if(msg != ""){
                        playNotification();
                        $('#chat_display_area_'+senderName).append('<b>'+senderName+': </b>' + msg + '<br/>');
                        $('#chat_display_area_'+senderName).attr('scrollTop', $('#chat_display_area_'+senderName).attr('scrollHeight'));
                        if($("#chat_dialog_"+senderName).dialog("isOpen") !== true){
                            $.sticky('<b>'+senderName+': </b>'+msg);
                            $("#user_details_dispaly_"+senderName).addClass('active_incoming_message_div');
                            $("#chat_message_counter_"+senderName).removeClass('active_incoming_message').addClass(
                            function ()
                            {
                                $(this).addClass('active_incoming_message');
                                var newMsgCount = ($.trim($(this).html()) == '') ? 1 : (1 + parseInt($.trim($(this).html())));
                                $(this).html(newMsgCount);
                            }
                        );
                        }
                    }
                }
            }
        }

        function playNotification() {
            var soundObj = document.getElementById("chat_notify");
            soundObj.volume = .4;
            soundObj.play();
        }

        function checkEnterPressed(receiverId,evt)
        {

            var charCode = (evt.keyCode ? evt.keyCode : evt.which);
            sendIsTyping(receiverId);
            if (charCode == 13) {
                sendAndAppendMessage(receiverId);
                //var e = jQuery.Event("keydown");
                //e.which = 8; // # Some key code value
                //$("#chat_msg_area_"+count).trigger(e);
                return false;
            }
            else{
                return true;
            }
        }

        var sendUrl = "/StudentREG/resources/chat/sendingMessage?";
        function sendAndAppendMessage(receiverId){
            $('#chat_display_area_'+receiverId).append('<b>me: </b>' + $('#chat_msg_area_'+receiverId).val() + '<br/>');
            var message = $('#chat_msg_area_'+receiverId).val();
            var sender = "${sessionScope['sessionUserDetails'].userName}";
            var receiver = receiverId;
            $('#chat_msg_area_'+receiverId).val('');
            $('#chat_display_area_'+receiverId).attr('scrollTop', $('#chat_display_area_'+receiverId).attr('scrollHeight'));
            ajaxRequestWithReturnFunction(sendUrl+"sender="+sender+"&receiver="+receiver+"&message="+message,processChatSendMessage);
        }

        function processChatSendMessage(responseData) {
            //alert(responseData);
        }

        var timeout;
        var sendTypingMsgUrl = "/StudentREG/resources/chat/typingMessage?";
        function sendIsTyping(receiverId){
            //called when enter key is nt pressed
            var sender = "${sessionScope['sessionUserDetails'].userName}";
            var receiver = receiverId;
            if(timeout) {
                clearTimeout(timeout);
                timeout = setTimeout(function(){
                    ajaxRequestWithReturnFunction(sendTypingMsgUrl+"sender="+sender+"&receiver="+receiver+"&isTyping="+"false",processIsTypingSend)
                }, 12000);
            }
            else{
                ajaxRequestWithReturnFunction(sendTypingMsgUrl+"sender="+sender+"&receiver="+receiver+"&isTyping="+"true",processIsTypingSend);
                timeout = setTimeout(function(){
                    ajaxRequestWithReturnFunction(sendTypingMsgUrl+"sender="+sender+"&receiver="+receiver+"&isTyping="+"false",processIsTypingSend)
                }, 12000);
            }
        
        }

        function processIsTypingSend(responseData) {
            if(responseData == "false"){
                clearTimeout(timeout);
                timeout = null;
            }
        }

//        $(document).ready(function() {
//            $(window).scroll(function() {
//                
//                var flag = false;
//                var username = "";
//                $('.chatDialogClass').each(function ()
//                {
//                    username = this.id;
//                    if(!flag){
//                        if($("#chat_dialog_"+username).dialog("isOpen") === true){
//                            $('.chatDialogClass').dialog("close");
//                            flag = true;
//                        }
//                    }
//                });
//
//                $(".chatDialogClass").dialog({
//                    title: '<img src="img/OnlineIcon.gif" alt="" border="0"/> '+username,
//                    resizable: false,
//                    height:205,
//                    modal: false,
//                    position: ['right','bottom']
//                    //            buttons: {
//                    //
//                    //                Send: function() {
//                    //                    $(this).dialog( "close" );
//                    //                },
//                    //                Cancel: function() {
//                    //                    $( this ).dialog( "close" );
//                    //                }
//                    //            }
//                });
//            });
//        });
       
        function openChatDialog(userId , username) {
            $("#chat_message_counter_"+userId).html('');
            $("#chat_message_counter_"+userId).removeClass('active_incoming_message');
            $("#user_details_dispaly_"+userId).removeClass('active_incoming_message_div');
            $('.chatDialogClass').dialog("close");
            $('#chat_dialog_'+userId).dialog({
                title: '<img src="img/OnlineIcon.gif" alt="" border="0"/> '+username,
                resizable: false,
                draggable: false,
                height:205,
                modal: false,
                autoOpen:false,
                position: ['right','bottom']
            });
            $('#chat_dialog_'+userId).parent().css({position:"fixed"}).end().dialog('open');
            $('#chat_display_area_'+userId).attr('scrollTop', $('#chat_display_area_'+userId).attr('scrollHeight'));
        }
        function validateFormSubmit(){
            if(!checkPhoto()){
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
                if(photoFilePath != ''){
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
                else{
                    alert('Please choose an image to upload');
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

</script>
<%
            session.removeAttribute("sender");
            session.removeAttribute("bdayCardReceiver");
            session.removeAttribute("link");
%>
<body onunload="signOutChat();">
<audio id="chat_notify" src="/StudentREG/sound/notify.wav" preload="auto" autobuffer></audio>
<%@include file="template_header_home.jsp"%>
<c:choose>
    <c:when test="${sessionScope['sessionUserDetails'].userName ne null}">
        <h3 style="color: red;" align="center">${fileUploadError}</h3>
        <table width="100%">
            <tr>
                <td width="30%" align="left"><h1 align="center "style="color:black">USER PROFILE</h1></td>
                <td width="60%" align="right"></td>
                <td width="10%">
                    <!--                    <form action="./LoginServlet?">
                                            <input type="hidden" name="mode" value="logout"/>
                                            <input type="submit" align="center" value="Logout">
                                        </form>-->
                </td>
            </tr>
        </table>
        <h3 align="center" style="color: blue;">${emailSentToAllMsg}</h3>
        <table width="100%">
            <tr>
                <td width="70%">
                    <div id="userProfileDiv">
                        <table><tr>
                                <td width="35%" align="right" valign="top">
                                    <img src="./ShowImageServlet?username=${user.username}" alt="${user.firstname} ${user.lastname}" width='200' height='200' border="0"/>
                                    <br/>
                                    <a href="#" id="basic-modal">Change picture</a>
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
                    </div>
                </td>
                <td width="30%" valign="top">
                    <table>
                        <tr>
                            <td>
                                <input type="button" id="enterChatButton" onclick="signInChat();" value="Chat Sign In" alt="Please click here to enter chat"/>
                            </td>
                            <td>
                                <input disabled type="button" id="exitChatButton" onclick="signOutChat();" alt="Please click here to exit chat" value="Chat Sign Out"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div id="allAvailableFriends" style="height: 400px; overflow: auto; "></div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </body>
</c:when>
<c:otherwise><h2 align="center" style="color: red;">Please login first</h2>
    <jsp:include page="/Login.jsp"/>
</c:otherwise>
</c:choose>
<%@include file="template_footer.jsp"%>
