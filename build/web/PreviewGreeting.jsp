<%--
    Document   : nextPage
    Created on : Dec 17, 2009, 10:23:28 AM
    Author     : debhu623
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.in.dbEntry.AddressBook"%>
<%@ page import="com.in.VO.ReceipentVO"%>
<script type="text/javascript">
    <%@ include file="/js/CalendarPopup.js"%>
        var cal = new CalendarPopup();
        cal.showNavigationDropdowns();
        function checkCalender(field) {
            var msgAlert = 'Please select date from the calender';
            if (field) {
                field.value = "";
                alert(msgAlert);
                return false;
            }
            return true;
        }
        function isDateValid(dateFromCalender){
            var date = new Date();
            //Here date from calender is checked with current date.
            //compareDates returns 1 if current date is greater than today.
            //compareDates returns 0 if current date is less than or equal to today.
            var flag = compareDates(dateFromCalender,"dd/MM/yyyy",formatDate(date,"dd/MM/yyyy"),"dd/MM/yyyy");
            if(flag == 1){
                return true;
            }
            else{
                return false;
            }
        }

        function echeck(str) {
            var at="@"
            var dot="."
            var lat=str.indexOf(at)
            var lstr=str.length
            var ldot=str.indexOf(dot)
            if (str.indexOf(at)==-1){
                return false
            }
            if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
                return false
            }
            if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
                return false
            }
            if (str.indexOf(at,(lat+1))!=-1){
                return false
            }
            if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
                return false
            }
            if (str.indexOf(dot,(lat+2))==-1){
                return false
            }
            if (str.indexOf(" ")!=-1){
                return false
            }
            return true;
        }

        function isValueEmpty(value) {
            return value=="";
        }

        function trimField(str){
            return str.replace(/^\s+|\s+$/g, '');
        }

        sortitems = 1;
        //==================================================================================================
        function moveAll(fbox_id,tbox_id){
            try{
                fbox = document.getElementById(fbox_id);
                tbox = document.getElementById(tbox_id);
                if((fbox!=null || fbox!="undefined") && (tbox!=null || tbox!="undefined")){
                    for(var i=0; i<fbox.options.length; i++){
                        if( fbox.options[i].value != ""){
                            var no = new Option();
                            no.value = fbox.options[i].value;
                            no.text = fbox.options[i].text;
                            tbox.options[tbox.options.length] = no;
                            fbox.options[i].value = "";
                            fbox.options[i].text = "";
                        }
                    }
                    BumpUp(fbox);
                    if (sortitems) SortD(tbox);
                }
            }catch(e){}
        }
        //==================================================================================================
        function move(fbox_id,tbox_id){
            try{
                fbox = document.getElementById(fbox_id);
                tbox = document.getElementById(tbox_id);
                if((fbox!=null || fbox!="undefined") && (tbox!=null || tbox!="undefined")){
                    for(var i=0; i<fbox.options.length; i++){
                        if(fbox.options[i].selected && fbox.options[i].value != ""){
                            var no = new Option();
                            no.value = fbox.options[i].value;
                            no.text = fbox.options[i].text;
                            tbox.options[tbox.options.length] = no;
                            fbox.options[i].value = "";
                            fbox.options[i].text = "";
                        }
                    }
                    BumpUp(fbox);
                    if (sortitems) SortD(tbox);
                }
            }catch(e){}
        }
        //==================================================================================================
        function BumpUp(box){
            try{
                for(var i=0; i<box.options.length; i++)
                {
                    if(box.options[i].value == "")
                    {
                        for(var j=i; j<box.options.length-1; j++)
                        {
                            box.options[j].value = box.options[j+1].value;
                            box.options[j].text = box.options[j+1].text;
                        }
                        var ln = i;
                        break;
                    }
                }
                if(ln < box.options.length)
                {
                    box.options.length -= 1;
                    BumpUp(box);
                }
            }catch(e){}
        }
        //==================================================================================================
        function SortD(box){
            try{
                var temp_opts = new Array();
                var temp = new Object();
                for(var i=0; i<box.options.length; i++)
                {
                    temp_opts[i] = box.options[i];
                }
                for(var x=0; x<temp_opts.length-1; x++){
                    for(var y=(x+1); y<temp_opts.length; y++){
                        if(temp_opts[x].text > temp_opts[y].text){
                            temp = temp_opts[x].text;
                            temp_opts[x].text = temp_opts[y].text;
                            temp_opts[y].text = temp;
                            temp = temp_opts[x].value;
                            temp_opts[x].value = temp_opts[y].value;
                            temp_opts[y].value = temp;
                        }
                    }
                }
                for(var i=0; i<box.options.length; i++){
                    box.options[i].value = temp_opts[i].value;
                    box.options[i].text = temp_opts[i].text;
                }
            }catch(e){}
        }
        //==================================================================================================
        function selectAll(box){try{for(var i=0; i<box.options.length; i++) {box.options[i].selected = true;}}catch(e){}}
        //==================================================================================================
        function listChange()
        {
            return true;
        }
        //==================================================================================================
        function return_value(tbox_id)
        {
            try{
                tbox = document.getElementById(tbox_id);
                if(tbox!=null || tbox!="undefined"){
                    var val = new Array();
                    var ret_val = "";
                    var ctr = 0;
                    for(var i=0; i<tbox.options.length; i++){if(tbox.options[i].value != ""){val[ctr] = tbox.options[i].value;ctr++;}}
                    if(val.length==0){
                        alert("Please select atleast one recipient");//Error msg, if no value is selected
                        return false;
                    }else{
                        for(var a=0; a<val.length; a++){if(a<(val.length-1)){ret_val = ret_val + val[a] + ",";}else{ret_val = ret_val + val[a];}}
                        //alert(ret_val);
                        return ret_val;
                    }
                }
            }catch(e){}
        }
        //==================================================================================================
</script>
<style type="text/css">
    .TextAreaClass{
        overflow-x: hidden;
        overflow-y: scroll;
        width: 300px;
        height: 30px;
    }
    .MultiSelectBox {
        width: 200px;
        height:150px;
        border: 1px solid #CA6F75;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #666666;
        padding-top:2px;
    }
    .dropDownStyle {
        height: 20px;
        width: auto;
        border: 1px solid #CA6F75;
        font-family: Verdana, Arial, Helvetica, sans-serif;
        font-size: 11px;
        color: #666666;
        padding-top:1px;
    }
</style>
<%@include file="template_header_home.jsp"%>
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
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
    <tr>
        <td>
            <br/>
        </td>
    </tr>
    <tr>
        <td width="100%" align="center">
            <table width="550" border="0" cellspacing="0" cellpadding="10" align="center" bgcolor="${param.bg_color_val}">
                <tr>
                    <td align="center" valign="top"><img alt="${param.displayCardDesc}" width='550' height='400' src="/StudentREG/DisplayCardServlet?ecardId=${param.displayCardId}"/></td>
                </tr>
                <tr>
                    <td align="left" valign="top">
                        <%
                                    String text = new String((String) request.getParameter("text_content"));
                        %>

                        <div id="textAlignment1" style="width:550px; height:100px; border-right:#F4DEE0 0px solid; border-left:#F4DEE0 0px solid; BORDER-BOTTOM: #F4DEE0 0px solid; BORDER-TOP: #F4DEE0 0px solid; PADDING: 5px; OVERFLOW: auto; OVERFLOW-X: hidden; SCROLLBAR-FACE-COLOR: #F4DEE0; SCROLLBAR-HIGHLIGHT-COLOR: #F4DEE0; SCROLLBAR-3DLIGHT-COLOR: #949292; SCROLLBAR-ARROW-COLOR: #949292; SCROLLBAR-DARKSHADOW-COLOR: #949292; SCROLLBAR-BASE-COLOR: #949292;">
                            <%=text%>
                        </div>
                    </td>
                </tr>
            </table>

        </td>
    </tr>
</table>
<br/>&nbsp;
<table width="550px" align="center" cellpadding="0" cellspacing="0">
    <c:if test="${link.linkType ne 'BirthDayLink'}">
        <%
                    AddressBook addressBook = new AddressBook();
                    ArrayList<ReceipentVO> recipientVOs = addressBook.LookUp("", "");
                    request.setAttribute("recipientList", recipientVOs);
        %>
        <tr align="center">
            <td width="200px" align="left">
                <select name="list1" id="list1" multiple class="MultiSelectBox">
                    <c:forEach var="recipientVO" items="${recipientList}">
                        <option value="${recipientVO.emailId}">${recipientVO.firstname}&nbsp;${recipientVO.lastName}</option>
                    </c:forEach>
                </select>
            </td>
            <td width="50px" align="center">
                <img alt="" src="images/button_ctrl_small.jpg" width="50" height="129" border="0" usemap="#MapID" />
                <map name="MapID" id="MapID">
                    <area shape="rect" coords="8,36,42,61" href="javascript:move('list1','list2')" alt="Select" />
                    <area shape="rect" coords="8,65,42,91" href="javascript:move('list2','list1')" alt="Remove" />
                </map>
            </td>
            <td width="200px" align="right">
                <select name="list2" id="list2" multiple class="MultiSelectBox"></select>
            </td>
        </tr>
    </c:if>
</table>
<br/>&nbsp;<br/>
<table width="550px" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td align="left" width="130px" style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 13px;color:#C66A6C; font-weight: normal;">
            Sending Option:
        </td>
        <td align="left">
            &nbsp;<select name="sendingOption" id="sendingOption" class="dropDownStyle" onchange="displayCalender();">
                <option value="0" selected>Select Sending Option</option>
                <option value="1">Send Now</option>
                <c:if test="${link.linkType ne 'BirthDayLink'}">
                    <option value="2">Send Later</option>
                </c:if>
                <c:if test="${param.displaycardType eq 'Birthday' || link.linkType eq 'BirthDayLink'}">
                    <option value="3">Send On Birthday</option>
                </c:if>
            </select>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <br>
        </td>
    </tr>
    <tr>
        <td align="left" width="130px" style="font-family: Verdana,Arial,Helvetica,sans-serif; font-size: 13px;color:#C66A6C; font-weight: normal;">
            <div id="calenderText" style="display: none;">
                Sending Date:
            </div>
        </td>
        <td align="left">
            <div id="calenderSelector" style="display: none;">
                &nbsp;<input type="text" id="sendingDateObj"  readonly="true" value=""/>
                <a href="#" onclick="cal.select(fromDate_obj,'anchor1','dd/MM/yyyy'); return false;"
                   name="anchor1" id="anchor1">
                    <img alt="Calender" src ="img/calendar.jpg" style="border:none;">
                </a>
            </div>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <br>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <br>
        </td>
    </tr>
</table>
<table width="550px" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td align="right" width="45%">
            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image31','','images/b_back_o.gif',1)" onclick="document.getElementById('backToCreateGreetingForm').submit();">
                <img src="images/b_back_u.gif" alt="Back" name="Image31" width="81" height="24" border="0" id="Image31" />
            </a>
        </td>
        <td align="center" width="10%">
        </td>
        <td align="left" width="45%">
            <%--href="javascript:nop"---- this syntax means no operation be performed on clicking and screen remains static
            href="http:///" ----- this means no operation to be performed on click if error but if no error then as usual function occurs--%>
            <a href="#" onmouseout="swapImgRestore()" onmouseover="swapImage('Image12','','images/b_send_o.gif',1)" onclick="sendCard();">
                <img src="images/b_send_u.gif" alt="Send" name="Image12" width="81" height="24" border="0" id="Image12"/>
            </a>
        </td>
    </tr>
</table>
<form id="backToCreateGreetingForm" action="CreateGreeting.jsp" method="post">
    <input type="hidden" name="displayCardId" id="displayCardId" value="${param.displayCardId}"/>
    <input type="hidden" id="displayCardDesc" name="displayCardDesc" value="${param.displayCardDesc}"/>
    <input type="hidden" id="displayCardType" name="displaycardType" value="${param.displaycardType}"/>
    <input type="hidden" id="greetingHiddenMessage" name="greetingHiddenMessage" value='<%=text%>'/>
    <input type="hidden" id="greetingBackGroundColor" name="greetingBackGroundColor" value="${param.bg_color_val}"/>
</form>
<form id="mailSenderForm" name="mailSenderForm" action="./MailSenderServlet" method="post">
    <input type="hidden" id="allEmailIds" name="allEmailIds" value=""/>
    <input type="hidden" id="emailMessage" name="emailMessage" value='<%=text%>'/>
    <input type="hidden" id="senderMailId" name="senderMailId" value="${sender.senderEmail}"/>
    <input type="hidden" id="senderName" name="senderName" value="${sender.senderName}"/>
    <input type="hidden" id="ecardId" name="ecardId" value="${param.displayCardId}"/>
    <input type="hidden" id="sendingDate" name="sendingDate" value=""/>
    <input type="hidden" id="cardType" name="cardType" value="${param.displaycardType}"/>
    <input type="hidden" id="ecardBgColor" name="ecardBgColor" value="${param.bg_color_val}"/>
</form>
<!--End main content-->
<%@include file="template_footer.jsp"%>
<script language="JavaScript" type="text/JavaScript">
    var fromDate_obj = document.getElementById("sendingDateObj");

    var sendingObj = document.getElementById("sendingOption");
    var sendingId =  sendingObj.options[sendingObj.selectedIndex].value;
    var sendingName = sendingObj.options[sendingObj.selectedIndex].text;
    //sendingId = 1 -----send Now
    //sendingId = 2 -----send Later
    //sendingId = 3 -----send on Birthday

    function displayCalender(){
        var sendingObj = document.getElementById("sendingOption");
        var sendingId =  sendingObj.options[sendingObj.selectedIndex].value;
        var sendingName = sendingObj.options[sendingObj.selectedIndex].text;
        //sendingId = 1 -----send Now
        //sendingId = 2 -----send Later
        //sendingId = 3 -----send on Birthday
        if(sendingId == '2'){
            document.getElementById("calenderText").style.display ="block";
            document.getElementById("calenderSelector").style.display ="block";
        }
        else{
            document.getElementById("calenderText").style.display ="none";
            document.getElementById("calenderSelector").style.display ="none";
        }
    }

    function sendCard(){
        var sendingObj = document.getElementById("sendingOption");
        var sendingId =  sendingObj.options[sendingObj.selectedIndex].value;
        var sendingName = sendingObj.options[sendingObj.selectedIndex].text;
        //sendingId = 1 -----send Now
        //sendingId = 2 -----send Later
        //sendingId = 3 -----send on Birthday
        if(${link.linkType eq 'BirthDayLink'}){
            if(sendingId == '1'){
                sendNowForBirthdayLink();
            }
            else if(sendingId == '2'){
                sendLaterForBirthdayLink();
            }
            else if(sendingId == '3'){
                sendOnBirthdayForBirthdayLink();
            }
            else{
                alert("Please choose a Sending Option");
            }
        }
        else{
            if(sendingId == '1'){
                sendNow();
            }
            else if(sendingId == '2'){
                sendLater();
            }
            else if(sendingId == '3'){
                sendOnBirthday();
            }
            else{
                alert("Please choose a Sending Option");
            }
        }
    }

    function sendNowForBirthdayLink(){
        if(validateFormSubmitForBirthdayLink()){
            document.getElementById('allEmailIds').value = "${bdayCardReceiver.bdayCardRecipientEmail}";
            document.getElementById('mailSenderForm').submit();
        }
    }

    function sendLaterForBirthdayLink(){
        if(validateFormSubmitForBirthdayLink()){
            document.getElementById('allEmailIds').value = "${bdayCardReceiver.bdayCardRecipientEmail}";
            document.getElementById('sendingDate').value = document.getElementById("sendingDateObj").value;
            document.getElementById('mailSenderForm').submit();
        }
    }

    function sendOnBirthdayForBirthdayLink(){
        if(validateFormSubmitForBirthdayLink()){
            document.getElementById('allEmailIds').value = "${bdayCardReceiver.bdayCardRecipientEmail}";
            document.getElementById('sendingDate').value = "${bdayCardReceiver.bdayCardRecipientBday}";
            document.getElementById('mailSenderForm').submit();
        }
    }


    function sendNow(){
        if(validateFormSubmit()){
            //alert(return_value("list2"));"sandipan.das@in.pwc.com";
            document.getElementById('allEmailIds').value = return_value("list2");//document.getElementById('allEmailOfRecipients').value;
            document.getElementById('mailSenderForm').submit();
        }
    }

    function sendLater(){
        if(validateFormSubmit()){
            document.getElementById('allEmailIds').value = return_value("list2");//document.getElementById('allEmailOfRecipients').value;
            document.getElementById('sendingDate').value = document.getElementById("sendingDateObj").value;
            document.getElementById('mailSenderForm').submit();
        }
    }

    function sendOnBirthday(){
        if(validateFormSubmit()){
            document.getElementById('allEmailIds').value = return_value("list2");//document.getElementById('allEmailOfRecipients').value;
            document.getElementById('sendingDate').value = "SetSendingDateForSendOnBirthday";
            document.getElementById('mailSenderForm').submit();
        }
    }

    function validateFormSubmit(){
        var sendingObj = document.getElementById("sendingOption");
        var sendingId =  sendingObj.options[sendingObj.selectedIndex].value;
        var sendingName = sendingObj.options[sendingObj.selectedIndex].text;
        //sendingId = 1 -----send Now
        //sendingId = 2 -----send Later
        //sendingId = 3 -----send on Birthday
        if(sendingId == '1'){
            if(!return_value("list2")){
                return false;
            }
            else{
                var email = return_value("list2");//document.getElementById('allEmailOfRecipients').value;
                var emailArray = new Array();

                emailArray = email.split(",");
                for(var i=0; i<(emailArray.length); i++){
                    var echeckVal = echeck(emailArray[i])+"";
                    if(echeckVal=="false"){
                        alert('Please enter a valid Email Id');
                        return false;
                    }
                }
                return true;
            }
        }
        else if(sendingId == '2'){
            if(!return_value("list2")){
                return false;
            }
            else{
                var date = document.getElementById('sendingDateObj').value;
                var email = return_value("list2");// document.getElementById('allEmailOfRecipients').value;
                var emailArray = new Array();
                if(isValueEmpty(date)){
                    alert("Please enter a date from calender");
                    return false;
                }
                else if(!isDateValid(date)){
                    alert("Sending date must be later than today");
                    return false;
                }
                else{
                    emailArray = email.split(",");
                    for(var i=0; i<(emailArray.length); i++){
                        var echeckVal = echeck(emailArray[i])+"";
                        if(echeckVal=="false"){
                            alert('Please enter a valid Email Id'+'\n'+'And seperate multiple emailIds by comma(,)' );
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        else if(sendingId == '3'){
            if(!return_value("list2")){
                return false;
            }
            else{
                var email = return_value("list2");// document.getElementById('allEmailOfRecipients').value;
                var emailArray = new Array();
                emailArray = email.split(",");
                for(var i=0; i<(emailArray.length); i++){
                    var echeckVal = echeck(emailArray[i])+"";
                    if(echeckVal=="false"){
                        alert('Please enter a valid Email Id'+'\n'+'And seperate multiple emailIds by comma(,)' );
                        return false;
                    }
                }
                return true;
            }
        }
    }

    function validateFormSubmitForBirthdayLink(){
        var sendingObj = document.getElementById("sendingOption");
        var sendingId =  sendingObj.options[sendingObj.selectedIndex].value;
        var sendingName = sendingObj.options[sendingObj.selectedIndex].text;
        //sendingId = 1 -----send Now
        //sendingId = 2 -----send Later
        //sendingId = 3 -----send on Birthday
        if(sendingId == '1'){
            var email = "${bdayCardReceiver.bdayCardRecipientEmail}";
            if(isValueEmpty(email)){
                alert("No Email Id of recipient in LDAP database"+"\n"+
                    "Please contact system administrator");
                return false;
            }
            else{
                var echeckVal = echeck(email)+"";
                if(echeckVal=="false"){
                    alert("Email Id of recipient in LDAP database is not valid"+"\n"+
                        "Please contact system administrator");
                    return false;
                }
                return true;
            }
        }
        else if(sendingId == '2'){
            var date = document.getElementById('sendingDateObj').value;
            var email = "${bdayCardReceiver.bdayCardRecipientEmail}";
            if(isValueEmpty(email)){
                alert("No Email Id of recipient in LDAP database"+"\n"+
                    "Please contact system administrator");
                return false;
            }
            else if(isValueEmpty(date)){
                alert("Please enter a date from calender");
                return false;
            }
            else if(!isDateValid(date)){
                alert("Sending date must be later than today");
                return false;
            }
            else{
                var echeckVal = echeck(email)+"";
                if(echeckVal=="false"){
                    alert("Email Id of recipient in LDAP database is not valid"+"\n"+
                        "Please contact system administrator");
                    return false;
                }
                return true;
            }
        }
        else if(sendingId == '3'){
            var date = "${bdayCardReceiver.bdayCardRecipientBday}";
            var email = "${bdayCardReceiver.bdayCardRecipientEmail}";
            if(isValueEmpty(email)){
                alert("No Email Id of recipient in LDAP database"+"\n"+
                    "Please contact system administrator");
                return false;
            }
            else if(isValueEmpty(date)){
                alert("No Birthday date of recipient in LDAP database"+"\n"+
                    "Please contact system administrator");
                return false;
            }
            else{
                var echeckVal = echeck(email)+"";
                if(echeckVal=="false"){
                    alert("Email Id of recipient in LDAP database is not valid"+"\n"+
                        "Please contact system administrator");
                    return false;
                }
                else{
                    if (confirm("Birthday Card will be sent to "+"${bdayCardReceiver.bdayCardRecipientName}"+" on "+
                        "${bdayCardReceiver.bdayCardRecipientBday}"+"\n"+"Click OK to confirm")) {
                        return true;
                    }
                    else
                        return false;
                }
                return true;
            }
        }
    }
</script>
