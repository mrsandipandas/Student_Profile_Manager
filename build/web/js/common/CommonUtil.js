function checkIfSelectInOrder(objName,indexToCheck){
	var flag = true;
	var checkBoxArr = document.getElementsByName(objName);
	var checkBoxCountArr = document.getElementsByName('checkBoxCountVal');
	indexToCheck = indexToCheck - checkBoxCountArr[0].value;
	if(checkBoxArr[indexToCheck].checked){
		for(var i=0;i<indexToCheck;i++){
			flag = flag & (checkBoxArr[i].checked);
		}
		if (flag == false){
			alert('Please select Installment payins in order');
			checkBoxArr[indexToCheck].checked = false;
			return false;
		}
		else{
			return true;
		}
	}
	else{
		for(var i=indexToCheck;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
			setCheckPayIn(checkBoxArr[i]);
		}
		return true;
	}
}

function validatePayin()
{
    var msg = "<ul class=\"errorList\">";
    var errorFound = false, payinSelected = false;

    var checkBoxes = document.getElementsByName("primKey");
    var actualAmounts = document.getElementsByName("actualAmount");
    for (var i = 0; i < checkBoxes.length; i++) {
        if (checkBoxes[i].checked) {
            payinSelected = true;
            if (actualAmounts[i].value.length == 0) {
                msg += "<li>Payin amount can not be empty</li>";
                errorFound = true;
                break;
            }
        }
    }
    if (!payinSelected) {
        msg += "<li>No payin is selected</li>";
        errorFound = true;
    }
    if (document.getElementById('paymentModeId').value == '-1') {
        msg += "<li>Please select Payment Mode</li>";
        errorFound = true;
    }
    if (document.getElementById('paymentDateCal'))
	   	{
		   	if (document.getElementById('paymentDateCal').value == '') 
		   	{
		        msg += "<li>Please select Payment Date</li>";
		        errorFound = true;
	    	}
    	}
        
    if (document.getElementById('instrumentClrDateCal'))
    {
    	if (document.getElementById('instrumentClrDateCal').value == '') 
    	{
            msg += "<li>Please select Payment / Intrument Clearance Date</li>";
            errorFound = true;
    	}
    }
    
    var paymentModeVal = document.getElementById("paymentModeId");
    var strValue =paymentModeVal.selectedIndex;
    strValue = paymentModeVal.options[strValue].value;
    
	if(strValue == "-1")
	{
		msg += "<li>Please select a payment mode</li>";
		errorFound = true;
	}
	
	if(strValue=="2" || strValue=="3"){
		
		var instrumentdateVal = document.productPayInForm.instrumentDate.value.trim();	
		if(instrumentdateVal == "")
		{
			msg += "<li>Please provide Instrument Date</li>";
			errorFound = true;
		}
		var draweeBankVal = document.productPayInForm.drawnOnBank.value.trim();	
		if(draweeBankVal == "")
		{
			msg += "<li>Please provide bank information</li>";
			errorFound = true;
		}
		var draweeBranchVal = document.productPayInForm.bankBranch.value.trim();	
		if(draweeBranchVal == "")
		{
			msg += "<li>Please provide branch name</li>";
			errorFound = true;
		}
		var checkNoVal = document.productPayInForm.chequeNo.value.trim();	
		if(checkNoVal == "")
		{
			msg += "<li>Please provide Instrument no</li>";
			errorFound = true;
		}
	}


    msg += "</ul>";
    if (errorFound)
    {
        $("#errorSpan").html(msg);
        $("#errorSpan").show("slow");
        return false;
    }
    else {
    	if((document.getElementById('tempReceiptNo').value!="")&&parseFloat(document.getElementById('totalAmountEnclosed').value) != parseFloat(document.getElementById('tempTotalAmountEnclosedForReceipt').value)){
    		if(!confirm("Total amount differs in payment and receipt\nDo you want to proceed?")) {
    			return false;
    		}
    	}
    	
    	if(!confirm("Record will be saved on submission \nDo you want to proceed?")) {
    		return false;
    	}
    	
        document.forms[0].action = 'productPayin.do?mode=save';
        document.forms[0].submit();
    }
}

function searchTempReceiptNumber(customerNumber,applicationNumber){
	openModalWin('searchExistingCustomerDetails.do?mode=tempReceiptSearch&customerNumber='+customerNumber+'&applicationNumber='+applicationNumber+'&time='+new Date(),500,500,tempReceiptNumberSearchCallBack)
}

function tempReceiptNumberSearchCallBack(data){
//	document.getElementById('tempReceiptNo').value = data.split(',')[1];
//	document.getElementById('tempTotalAmountEnclosedForReceipt').value = data.split(',')[0];
	var dataArr = data.split('!~!');
	
	var amount = dataArr[0];
	document.getElementById('tempTotalAmountEnclosedForReceipt').value = amount;
	
	var amount = dataArr[0];
//	document.getElementById('totalAmount').value = amount;
	document.getElementById('tempTotalAmountEnclosedForReceipt').value = amount;
	
	var paymentModeId = dataArr[4];
	var paymentModeName = dataArr[11];
	
	var paymentModeDrpDwn = document.getElementById("paymentModeId");

	for (i=0; i<paymentModeDrpDwn.options.length; i++) 
	{
		if (paymentModeDrpDwn.options[i].text.toUpperCase() == paymentModeName.toUpperCase())
		{
			paymentModeDrpDwn.selectedIndex = i;
		}
//		if (i == paymentModeId)
//		{
//			paymentModeDrpDwn.selectedIndex = i;
//		}
	}
	
	var tempReceiptNo = dataArr[6];
	document.getElementById('tempReceiptNo').value = tempReceiptNo;
	
	var checkNo = dataArr[7];
	if(checkNo!=null && checkNo!="")
		document.forms[0].chequeNo.value = checkNo;
	
	var chequeDate = dataArr[8];
	var dateStringArr = chequeDate.split(" ")[0].split("-");
	
	if(chequeDate!=null && chequeDate!="")
	{
		document.forms[0].instrumentDateCal.value = dateStringArr[2]+'-'+dateStringArr[1]+'-'+dateStringArr[0];
		document.forms[0].instrumentDate.value = dateStringArr[2]+'-'+dateStringArr[1]+'-'+dateStringArr[0];
	}
	
	var branchName = dataArr[9];
	if(branchName!=null && branchName!="")
	{
		document.forms[0].bankBranch.value = branchName;
	}
	
	var bankName = dataArr[10];
	if(bankName!=null && bankName!="")
	{
		document.forms[0].drawnOnBank.value = bankName;
	}
}

function calculateTotalPayIn(obj)
{
    var unitVal = document.getElementById("unitPrice").value;
    if (!(obj == undefined) && obj.value * 1.0 < unitVal * 1.0) {
        alert("Amount entered should be minimum Rs. " + unitVal);
        obj.value = "";
    }
    if (!(obj == undefined) && obj.value % unitVal != 0) {
        alert("Amount entered should be multiple of Rs. " + unitVal);
        obj.value = "";
    }
    var objArray = document.getElementsByName('primKey');

    var sum = 0,fine,payinAmt;

    for (var i = 0; i < objArray.length; i++)
    {
        fine = objArray[i].parentNode.parentNode.cells[3].getElementsByTagName('INPUT')[0].value;
        payinAmt = objArray[i].parentNode.parentNode.cells[6].getElementsByTagName('INPUT')[0].value;
        if (objArray[i].checked && fine.length > 0 && payinAmt.length > 0)
        {
            sum = parseFloat(sum) + parseFloat(fine) + parseFloat(payinAmt);
        }
        else if(fine.length == 0){
        	sum = parseFloat(sum) + parseFloat(payinAmt);
        }
    }

    if (sum != 0)
    {
        document.productPayInForm.totalAmountEnclosed.value = sum;
    }
    else {
        document.productPayInForm.totalAmountEnclosed.value = "";
    }

}

function setCheckPayIn(obj)
{
    //alert('alert : '+obj.parentNode.parentNode.cells[3].getElementsByTagName('INPUT')[0].value);
    if (obj.checked)
    {
        obj.parentNode.parentNode.cells[3].getElementsByTagName('INPUT')[0].disabled = '';
        obj.parentNode.parentNode.cells[6].getElementsByTagName('INPUT')[0].disabled = '';
    }
    else
    {
        obj.parentNode.parentNode.cells[3].getElementsByTagName('INPUT')[0].disabled = 'true';
        obj.parentNode.parentNode.cells[6].getElementsByTagName('INPUT')[0].disabled = 'true';
    }

    var objArray = document.getElementsByName('primKey');

    //alert(objArray.length);

    var sum = 0;

    for (var i = 0; i < objArray.length; i++)
    {
        if (objArray[i].checked)
        {
            //alert('HMM : '+objArray[i].parentNode.parentNode.cells[2].innerHTML);
            sum = parseFloat(sum) + parseFloat(objArray[i].parentNode.parentNode.cells[6].getElementsByTagName('INPUT')[0].value) + parseFloat(objArray[i].parentNode.parentNode.cells[3].getElementsByTagName('INPUT')[0].value);
        }
    }

    if (sum != '')
    {
        document.productPayInForm.totalAmountEnclosed.value = sum;
    }


}

function addLateFine(obj)
{

    var objArray = document.getElementsByName('primKey');

    //alert(objArray.length);

    var sum = 0;

    for (var i = 0; i < objArray.length; i++)
    {
        if (objArray[i].checked)
        {
            //alert('HMM : '+objArray[i].parentNode.parentNode.cells[2].innerHTML);
            sum = parseFloat(sum) + parseFloat(objArray[i].parentNode.parentNode.cells[2].innerHTML) + parseFloat(objArray[i].parentNode.parentNode.cells[3].getElementsByTagName('INPUT')[0].value);
        }
    }

    if (sum != '')
    {
        document.productPayInForm.totalAmountEnclosed.value = sum;
    }

    //document.productPayInForm.totalAmountEnclosed.value=parseFloat(document.productPayInForm.totalAmountEnclosed.value)+parseFloat(obj.value);
}


function isNumberKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode
    if (charCode > 31 && (charCode < 48 || charCode > 57)){
        alert("Plz enter numbers only");
        return false;
    }
    return true;
}
var i = 0
function isCurrencyKey(evt)
{
    var charCode = (evt.which) ? evt.which : event.keyCode

    if (charCode == 46) {
        i = i + 1;

        if (i == 1)
            return true;
        else
            return false;

    }
    else {
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }
}
function isCurrency(str) {
    isCurrency = /^\d+\.\d{2}$/;
    return isCurrency.test(str);
}

function convertToCurrency(cur)
{
    var amount = cur;
    var i = parseFloat(amount);
    if (isNaN(i)) {
        i = 0.00;
    }
    var minus = '';
    if (i < 0) {
        minus = '-';
    }
    i = Math.abs(i);
    i = parseInt((i + .005) * 100);
    i = i / 100;
    s = new String(i);
    if (s.indexOf('.') < 0) {
        s += '.00';
    }
    if (s.indexOf('.') == (s.length - 2)) {
        s += '0';
    }
    s = minus + s;
    //e.value = s;
    return s;
}


function openModalPrintWin(url, width, height) {
    var modalResponse = null;
    if (window.showModalDialog) {
        modalResponse = window.showModalDialog(url, "",
                "dialogWidth: " + width + "px;dialogHeight:" + height + "px");
    } else {
        modalResponse = window.open(url, '',
                'height=' + height + ',width=' + width + ',toolbar=no,directories=no,status=no, menubar=no,scrollbars=no,resizable=no ,modal=yes');
    }

    return modalResponse;
}

function validateReceipt()
{
    var error = '<ul class="errorList">';


    if (document.getElementById('productType').value == '-1')
    {
        error = error + '<li>Please select Application Type</li>';
    }

//    if (document.getElementById('appNumId').value.trim() == '')
//    {
//        error = error + '<li>Please type in an Application Number</li>';
//    }

    if (error != '<ul class="errorList">')
    {
        error = error + '</ul>';
    }
    else
    {
        error = '';
    }

    if (error == '')
    {
        var frm = document.getElementById('abc1');
		window.name = 'dup_cert_Search';
		//document.forms[0].target= 'admin_Search';
		frm.target = 'dup_cert_Search';
		frm.submit();
		return true;
        //printReceipt();
    }
    else
    {
        $("#errorSpanForPopup").html(error);

        $("#errorSpanForPopup").show("slow");
        return false;
    }
}

function printReceipt()
{
    var url = 'generateReceipt.do?pop=Y&appNum=' + document.getElementById('appNumId').value + '&appType=' + document.getElementById('appTypeId').value;
    //alert('URL : '+url);
    var retval = openModalWin(url, 600, 600, '');
}


//start for generate receipt 

function getInstallmentsForPermReceipt()
{
	 var appType = document.getElementById("appTypeId").value;
	    var appNum = document.getElementById("appNumId").value;
	    var printType = 'printType';
	    var paymentDD = document.getElementById("paymentId");
	    var paymentId = paymentDD.value;
	    if (document.forms[0].appNumber.value != appNum) {
	        var optn;
	        for (var i = paymentDD.options.length - 1; i > 0; i--)
	        {
	            paymentDD.remove(i);
	        }
	        paymentId = "";
	    }
	    var msg = "<ul class=\"errorList\">";
	    var errorFound = false;
	    

	    if (appType == "-1")
	    {
	        msg += "<li>Please select Application Type Name</li>";
	        errorFound = true;
	    }
	    if (appNum == "")
	    {
	        msg += "<li>Application Number is missing</li>";
	        errorFound = true;
	    }
	    if (paymentId == "" && paymentDD.options.length > 1)
	    {
	        msg += "<li>Please select installment numbers</li>";
	        errorFound = true;
	    }
	    msg += "</ul>";
	    if (errorFound)
	    {
	        $("#errorSpan").html(msg);
	        $("#errorSpan").show("slow");
	    }
	    else
	    {
			 if (paymentId == "") {
		         if (appType == '2') {
		             document.forms[0].appNumber.value = appNum;
		             ajaxRequestWithReturnFunction("commonAjaxAction.do?mode=GET_PREF_SHARE_INST&appNum=" + appNum, showPrefShareInstallments);
		         }
		         else 
		        	 alert("This is valid only for Preferential Share");
			 }
	    }
}
function callPrintGenerateReceiptnewOpenModalWin(fromCallback) 
{
	 var appType = document.getElementById("appTypeId").value;
	 var appNum = document.getElementById("appNumId").value;
	 
	if(fromCallback=="true")
	{	   
	    var printType = 'printType';
	    var paymentDD = document.getElementById("paymentId");
	    var paymentId = paymentDD.value;
	    if (document.forms[0].appNumber.value != appNum) {
	        var optn;
	        for (var i = paymentDD.options.length - 1; i > 0; i--)
	        {
	            paymentDD.remove(i);
	        }
	        paymentId = "";
	    }
	    var msg = "<ul class=\"errorList\">";
	    var errorFound = false;
	    
	
	    if (appType == "-1")
	    {
	        msg += "<li>Please select Application Type Name</li>";
	        errorFound = true;
	    }
	    if (appNum == "")
	    {
	        msg += "<li>Application Number is missing</li>";
	        errorFound = true;
	    }
	    if (paymentId == "" && paymentDD.options.length > 1)
	    {
	        msg += "<li>Please select installment numbers</li>";
	        errorFound = true;
	    }
	    msg += "</ul>";
	    if (errorFound)
	    {
	        $("#errorSpan").html(msg);
	        $("#errorSpan").show("slow");
	    }
	    else
	    {
	        if (paymentId == "") 
	        {
	//           openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=&paymentId=", 800, 600);
	        	var documentForm = document.getElementById("abc1");
	    		documentForm.action="generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=";
	    		documentForm.target="_blank";
	    		documentForm.submit();
	        }
	        else 
	        {
	        	var documentForm = document.getElementById("abc1");
	        	documentForm.action="generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=" + paymentId.split("~!~")[1] + "&paymentId=" + paymentId.split("~!~")[0];
	        	documentForm.target="_blank";
	        	documentForm.submit();
	//            openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=" + paymentId.split("~!~")[1] + "&paymentId=" + paymentId.split("~!~")[0], 800, 600);
	        }
	    }
	}
	else
	{
		ajaxRequestWithReturnFunction("commonAjaxAction.do?mode=GET_PREF_SHARE_INST&appNum=" + appNum, showIfApplicationApproved);
	}
		
}

function showIfApplicationApproved(data)
{
	if(data=="Not Approved")
	{
		alert("Please use a valid approved Application Number.");
		return;
	}
	callPrintGenerateReceiptnewOpenModalWin('true');
}

function showPrefShareInstallments(data) 
{	
	if(data=="Not Approved")
	{
		alert("Please use a valid approved Application Number.");
		return;
	}
	
    var appType = document.getElementById("appTypeId").value;
    var appNum = document.getElementById("appNumId").value;
    var dataArr = data.split("!~!");
    if (data.length == 0) {
//        openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=1", 800, 600);
    	if(confirm("This is a single installment payin\nClick Ok to print the receipt for this or cancel to search with other details")) 
    	{
    		var documentForm = document.getElementById("abc1");
    		documentForm.action="generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=";
    		documentForm.target="_blank";
    		documentForm.submit();
//    		 openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=", 800, 600);
		}  
    }
    else if (dataArr.length == 1) {
        if (dataArr[0].split("~!~")[0] == "0") {
        	if(confirm("This is a single installment payin\nClick Ok to print the receipt for this or cancel to search with other details")) 
        	{
        		var documentForm = document.getElementById("abc1");
        		documentForm.action="generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=";
        		documentForm.target="_blank";
        		documentForm.submit();
//        		 openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=", 800, 600);
    		}           
        }
        else {
//            openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=1", 800, 600);
        	if(confirm("This is a single installment payin\nClick Ok to print the receipt for this or cancel to search with other details")) 
        	{
        		var documentForm = document.getElementById("abc1");
        		documentForm.action="generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=";
        		documentForm.target="_blank";
        		documentForm.submit();
//        		 openModalWin("generateReceipt.do?appType=" + appType + "&appNum=" + appNum + "&installments=", 800, 600);
    		}  
        }
    }
    else {
        var paymentDD = document.getElementById("paymentId");
        var optn;
        for (var i = paymentDD.options.length - 1; i > 0; i--)
        {
            paymentDD.remove(i);
        }
        for (var i = 0; i < dataArr.length; i++) {
            optn = document.createElement("OPTION");
            optn.text = dataArr[i].split("~!~")[1];
            optn.value = dataArr[i];
            paymentDD.options.add(optn);
        }
        paymentDD.options[1].selected = "selected";
        document.forms[0].appNumber.value = appNum;
    }
}


function ClearcallPrintGenerateReceiptnewOpenModalWin() {
    var url = "generateReceipt.do";
    ajaxRequest(url, 'adminDashboardMainContent');
}

//end for gererate receipt


function openModalWin(url, width, height, callback) {
    var modalResponse = null;
    if (window.showModalDialog) {
        modalResponse = 
        	window.showModalDialog(url, "",
                "dialogWidth: " + width + "px;dialogHeight:" + height + "px");
    } else {
        modalResponse = window.open(url, '',
                'height=' + height + ',width=' + width + ',toolbar=no,directories=no,status=no, menubar=no,scrollbars=no,resizable=no ,modal=yes');
    }

    if (callback && modalResponse && modalResponse != '')
        callback(modalResponse);

    return modalResponse;
}

function closeWindowModalDialog(saveData)
{
    window.returnValue = saveData;
    window.open('', '_top');
    window.close();
}


function emailCheck(emailObj){
    var email = emailObj.value;
    var atpos=email.indexOf("@");
    var dotpos=email.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
    {
        alert("Not a valid e-mail address");
        emailObj.value = '';
        return false;
    }
    else{
        return true;
    }
}



function nameCompare(applicantNameVal, nameToBeCompared)
{
    if (applicantNameVal == nameToBeCompared)
    {
        return false;
    }
    return true;
}

function isAlphaNumeric(evt) {
    var charCode = (evt.which) ? evt.which : event.keyCode;
    if ((charCode==8)||(charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
        return true;

    return false;
}


function createReport(contextPath, reportName)
{
    $("#AllInputParams").remove();//remove the custom div to hold the hidden input parameters
    var radioButtonName = 'rb_' + reportName;
    var reportType = $("input:radio[@name=" + radioButtonName + "]:checked").val();
    var additionalParams = '';
    $("#id_jasperReportInputForm").find("input").each(function(eachInput) {
        //alert($(this).val());
        //alert($(this).attr('id'));
        if ($(this).val() != undefined && jQuery.trim($(this).val()) != '' && $(this).attr('type') != 'button' && $(this).attr('type') != 'radio' && $(this).attr('type') != 'checkbox') {
            additionalParams = additionalParams + '&&' + $(this).attr('id') + '=' + $(this).val();

        }
    })
    $("#id_jasperReportInputForm").find("select").each(function(eachInput) {
        //alert($(this).val());
        //alert($(this).attr('id'));
        if ($(this).val() != undefined && jQuery.trim($(this).val()) != '' && $(this).attr('type') != 'button' && $(this).attr('type') != 'radio') {
            additionalParams = additionalParams + '&&' + $(this).attr('id') + '=' + $(this).val();
        }
    })    
    var url = contextPath + '/jasperReportAction.do?REPORT_TYPE=' + reportType + '&&REPORT_NAME=' + reportName + additionalParams;//This is for jQuery 1.1.3    
    $("#id_jasperReportInputForm").append("<div id='AllInputParams'></div>");
    $("#AllInputParams").append("<input type='hidden' name='REPORT_TYPE' value='" + reportType + "'/>");
    $("#AllInputParams").append("<input type='hidden' name='REPORT_NAME' value='" + reportName + "'/>");
    jQuery.each(additionalParams.split("&&"), function() {

        if (this.trim()!= '') {
            $("#AllInputParams").append("<input type='hidden' name='" + this.split("=")[0] + "' value='" + this.split("=")[1] + "'/>");
        }
    });
    $("#id_jasperReportInputForm").submit();
}

function printTemporaryReceipt() {
	//alert(1);
	var receiptType = document.getElementById("receiptTypeId").value;
	var customerNumber = document.getElementById("customerNumberId").value;
	var paymentMode = document.getElementById("paymentModeId").value;
	var firstName = document.getElementById("firstNameId").value;
	var lastName = document.getElementById("lastNameId").value;
	var planName = document.getElementById("planNameId").value;
	var productTypeId = document.getElementById("productTypeId").value;
	var introducerNumber = document.getElementById("introducerNumberId").value;
	var introducerFirstName = document.getElementById("introducerFirstNameId").value;
	var introducerLastName = document.getElementById("introducerLastNameId").value;
	var introducerNumberDSA = document.getElementById("introducerNumberIdDSA").value;
	var introducerFirstNameDSA = document.getElementById("introducerFirstNameIdDSA").value;
	var introducerLastNameDSA = document.getElementById("introducerLastNameIdDSA").value;
	//alert("introducerNumberDSA "+introducerNumberDSA);
	//alert("introducerFirstNameDSA "+introducerFirstNameDSA);
	//alert("introducerLastNameDSA "+introducerLastNameDSA);
	var amount = document.getElementById("amountId").value;
	var bankName =document.getElementById("bankNameId").value;
	var chequeNo = document.getElementById("chequeNoId").value;
	var branchName = document.getElementById("branchNameId").value;
	var installmentNo = document.getElementById("installmentNoId").value;
	var chequeDate = document.getElementById("chequeDateId").value;
	

	 var msg = "<ul class=\"errorList\">";
	 var errorFound = false;
	 
	 if ( receiptType =='')
	    {
	        msg += "<li>Please select Receipt Type </li>";
	        errorFound = true;
	    }
	 if(receiptType=='INVESTMENT' && customerNumber == ""){
		 msg += "<li>Please insert Customer Number</li>";
	     errorFound = true;
	 }
	 if(paymentMode == ""){
		 msg += "<li>Please select Mode of Payment</li>";
	        errorFound = true;
	 }
	 if(firstName == ""){
		 msg += "<li>Please insert First Name</li>";
	        errorFound = true;
	 }
	 if(lastName == ""){
		 msg += "<li>Please insert Last Name</li>";
	        errorFound = true;
	 }
	 if(receiptType == "INVESTMENT" && productTypeId == ""){
		 msg += "<li>Please insert/select Product type</li>";
	        errorFound = true;
	 }
	 if(receiptType == "INVESTMENT" && planName == ""){
		 msg += "<li>Please insert/select Plan Name</li>";
	        errorFound = true;
	 }
	 if(amount == "" || amount==0){
		 msg += "<li>Please insert amount</li>";
	        errorFound = true;
	 }
	 else if(receiptType == "INSTALLMENTS" && productTypeId != ""){
		 
		 var installmentamount = document.getElementById("installmentAmountId").value;
		 //alert(paymentMode+" "+installmentamount+" "+amount);
		 var remainder = amount%installmentamount;
		 //alert(remainder);
		 if(remainder != 0){
			 msg += "<li>Amount should be multiple of installment amount</li>"+installmentamount;
		        errorFound = true;
		 }
	 }
	 else if(receiptType == "INSTALLMENTS" && productTypeId == ""){
		 msg += "<li>Please insert/select Product type</li>";
	     errorFound = true;
	 }
/*	if((paymentMode=="DD"||paymentMode=="BD" ||paymentMode=="CHEQUE")&& bankName==""){
		 	msg += "<li>Please insert Bank Name</li>";
	        errorFound = true;
	 }
	 if((paymentMode=="DD"||paymentMode=="BD" ||paymentMode=="CHEQUE")&& chequeNo==""){
		 	msg += "<li>Please insert Cheque No</li>";
	        errorFound = true;
	 }
	 if((paymentMode=="DD"||paymentMode=="BD" ||paymentMode=="CHEQUE")&& branchName==""){
		 	msg += "<li>Please insert Branch Name</li>";
	        errorFound = true;
	 }
	 if((paymentMode=="DD"||paymentMode=="BD" ||paymentMode=="CHEQUE")&& chequeDate==""){
		 	msg += "<li>Please insert Cheque Date</li>";
	        errorFound = true;
	 }*/
	if((paymentMode != "Cash" && paymentMode !="Other" && paymentMode !="Transfer from MIP" && paymentMode !="MIP to Recurring" && paymentMode !="Bank Deposit Cash" && paymentMode !="Bank Transfer" && paymentMode !="ECS" && paymentMode !="NECS" && paymentMode !="RTGS")&& bankName==""){
		 	msg += "<li>Please insert Bank Name</li>";
	        errorFound = true;
	 }
	 if((paymentMode != "Cash" && paymentMode !="Other" && paymentMode !="Transfer from MIP" && paymentMode !="MIP to Recurring" && paymentMode !="Bank Deposit Cash" && paymentMode !="Bank Transfer" && paymentMode !="ECS" && paymentMode !="NECS" && paymentMode !="RTGS")&& chequeNo==""){
		 	msg += "<li>Please insert Cheque No</li>";
	        errorFound = true;
	 }
	 if((paymentMode != "Cash" && paymentMode !="Other" && paymentMode !="Transfer from MIP" && paymentMode !="MIP to Recurring" && paymentMode !="Bank Deposit Cash" && paymentMode !="Bank Transfer" && paymentMode !="ECS" && paymentMode !="NECS" && paymentMode !="RTGS")&& branchName==""){
		 	msg += "<li>Please insert Branch Name</li>";
	        errorFound = true;
	 }
	 if((paymentMode != "Cash" && paymentMode !="Other" && paymentMode !="Transfer from MIP" && paymentMode !="MIP to Recurring" && paymentMode !="Bank Deposit Cash" && paymentMode !="Bank Transfer" && paymentMode !="ECS" && paymentMode !="NECS" && paymentMode !="RTGS")&& chequeDate==""){
		 	msg += "<li>Please insert Cheque Date</li>";
	        errorFound = true;
	 }
	 
	 if((paymentMode =='Cash' || paymentMode =="Transfer from MIP" || paymentMode =="MIP to Recurring") && !(chequeDate ==null ||chequeDate == '')){
		 msg += "<li>Please remove Cheque Date for this payment mode</li>";
	        errorFound = true;
	 }
	 if(receiptType=='INSTALLMENTS' && installmentNo == "" ){
		 msg += "<li>Calculate Installment Number by clicking 'Get Installment' button</li>";
	     errorFound = true;
	 }
	
	 if (errorFound)
    {
        $("#errorSpan").html(msg);
        $("#errorSpan").show("slow");
        return false;
        
    }
    else
    {
	
		/*var urlparam = "receiptType="+receiptType+"&customerNumber="+customerNumber+"&firstName="+firstName+"&lastName="+lastName+"&paymentMode="+paymentMode+"&planName="+planName+"&amount="+amount;
		if(introducerNumber !=null && introducerNumber!=''){
			urlparam = urlparam+"&introducerNumber="+introducerNumber+"&introducerFirstName="+introducerFirstName+"&introducerLastName="+introducerLastName;
		}
		if(bankName != null && bankName!=''){
			urlparam = urlparam+"&bankName="+bankName;
		}
		if(chequeNo != null && chequeNo != ''){
			urlparam = urlparam + "&chequeNo="+chequeNo;
		}
		if(branchName != null && branchName != ''){
			urlparam = urlparam + "&branchName="+branchName;
		}
		if(installmentNo != null && installmentNo != ''){
			urlparam = urlparam + "&installmentNo="+installmentNo;
		}
		if(chequeDate != null && chequeDate != ''){
			urlparam = urlparam + "&chequeDate="+chequeDate;
		}*/
		if(confirm("Are you sure you want to save the data\nIMPORTANT: Please note receipt number before closing print window for future reference")) {
			document.getElementById("printButton").disabled= true;
			
			var form = document.getElementById("abc1");
			form.action = "createTempReceipt.do";
			form.targert = "_blank";
			form.submit();
			//return true;
			//openModalWin("createTempReceipt.do?" +urlparam, 800, 347,tReceiptCallback);
			
		}
    }
	
	
}

function printTempReceipt() {
		
	//alert(urlparam);
	openModalWin("printTempReceipt.do", 800, 600);
	
	
}

function searchCustomerForTReceipt(){
	var receiptType = document.getElementById("receiptTypeId").value;
	var productType = document.getElementById("productTypeId").value;
	if(receiptType == ''){
		alert('Please select Receipt type');
		
	}
	else if(receiptType == 'INSTALLMENTS' && productType == ''){
		alert('Please select Pref Share Product type');
	}
	else if(receiptType == 'INVESTMENT' && productType == ''){
		alert('Please select a Product type');
	}
	else if(receiptType != 'JOINING'){ 
		if(receiptType == 'INSTALLMENTS'){
			openModalWin('searchCustomerCallForInstallmentPayin.do',800,600,populateTReceiptMultiplePayCustomer);
		}else{
			openModalWin("searchCustomerCallForReceipt.do?time="+new Date(), 800, 600,populateTReceiptCustomer);
		}
	}
}

function populateTReceiptCustomer(data){
	//alert(data);
	
	document.getElementById("customerNumberId").value = data.split(';')[0].split('=')[1];
	document.getElementById("firstNameId").value = data.split(';')[1].split('=')[1].split(' ')[0];
	
	var name = data.split(';')[1].split('=')[1].split(' ');
	var lastName = data.split(';')[1].split('=')[1].split(' ')[1];
	if(name.length >2){
		for(var i=2 ;i<name.length;i++){
			lastName = lastName +" "+ name[i];
		}
	}
	
	document.getElementById("lastNameId").value = lastName;
		
	
	
}

function resetCustomerFromTReceipt(){
	document.getElementById("customerNumberId").value = "";
	document.getElementById("firstNameId").value = "";
	document.getElementById("lastNameId").value = "";
}

function searchMemberForTReceipt(){
	var receiptType = document.getElementById("receiptTypeId").value;
	if(receiptType != "INSTALLMENTS"){
		openModalWin("MemberSearch.do", 800, 600,populateTReceiptMember);
	}
}

function populateTReceiptMember(data){
	
	
	document.getElementById("introducerNumberId").value = data.split(';')[1].split('=')[1];
	document.getElementById("introducerFirstNameId").value = data.split(';')[2].split('=')[1].split(' ')[0];
	
	var name = data.split(';')[2].split('=')[1].split(' ');
	var lastName = data.split(';')[2].split('=')[1].split(' ')[1];
	if(name.length >2){
		for(var i=2 ;i<name.length;i++){
			lastName = lastName +" "+ name[i];
		}
	}
	
	document.getElementById("introducerLastNameId").value = lastName;
		
		
}

//Start Added for Temporary Receipt For DSA 

function searchDSAMemberForTReceipt(){
	var receiptType = document.getElementById("receiptTypeId").value;
	if(receiptType != "INSTALLMENTS"){
		openModalWin("dsaMemberSearch.do?mode=dsaSearchViewPage", 800, 600,populateTReceiptMemberForDSA);
	}
}

function populateTReceiptMemberForDSA(data){
	
	//alert("DSA number "+data.split(';')[1].split('=')[1]);
	//alert("First name "+data.split(';')[2].split('=')[1].split(' ')[0]);
	
	document.getElementById("introducerNumberIdDSA").value = data.split(';')[1].split('=')[1];
	document.getElementById("introducerFirstNameIdDSA").value = data.split(';')[2].split('=')[1].split(' ')[0];
	
	var name = data.split(';')[2].split('=')[1].split(' ');
	var lastName = data.split(';')[2].split('=')[1].split(' ')[1];
	if(name.length >2){
		for(var i=2 ;i<name.length;i++){
			lastName = lastName +" "+ name[i];
		}
	}
	//alert("Last name  "+lastName);
	document.getElementById("introducerLastNameIdDSA").value = lastName;
		
		
}

function resetMemberFromTReceiptDSA(){
	document.getElementById("introducerNumberIdDSA").value = "";
	document.getElementById("introducerFirstNameIdDSA").value = "";
	document.getElementById("introducerLastNameIdDSA").value = "";
}

//End  Added for Temporary Receipt For DSA 

function resetMemberFromTReceipt(){
	document.getElementById("introducerNumberId").value = "";
	document.getElementById("introducerFirstNameId").value = "";
	document.getElementById("introducerLastNameId").value = "";
}

function searchTReceipt() {
	//alert(1);
	var receiptNo = document.getElementById("receiptNoId").value;
	
	
	//alert(receiptType);
	 var msg = "<ul class=\"errorList\">";
	 var errorFound = false;
	 
	 if (receiptNo == "")
	    {
	        msg += "<li>Please insert Receipt Number for search</li>";
	        errorFound = true;
	    }
	
	
	 if (errorFound)
    {
        $("#errorSpan").html(msg);
        $("#errorSpan").show("slow");
        return false;
    }
    else
    {
	
		//var urlparam = "receiptNo="+receiptNo;
    	var form = document.getElementById("abc1");
		form.action = "searchTempReceipt.do";
		form.targert = "_blank";
		form.submit();
		return false;
		//openModalWin("searchTempReceipt.do?" +urlparam, 800, 347);
		//ajaxRequest("searchTempReceipt.do?" +urlparam,'adminDashboardMainContent');
    }
	
	
}


function resetTReceiptSearch(){
	document.getElementById("receiptNoId").value = "";
}

function alterTempReceiptDisplay(){
	var receiptType = document.getElementById("receiptTypeId").value;
	document.getElementById("firstNameId").value="";
	document.getElementById("lastNameId").value = "";
	document.getElementById("customerNumberId").value = "";
	//document.getElementById("psaNumberFieldId").value = "";
	document.getElementById("calcInstButton").innerHTML = "";
	//document.getElementById("psanumberId").innerHTML = "";
	document.getElementById("installmentAmountDivId").innerHTML = "";
	document.getElementById("installmentNoId").value = "";
	document.getElementById("installmentNoId").readOnly = false;
	if(receiptType == 'INVESTMENT' || receiptType == 'INSTALLMENTS'){
		document.getElementById("firstNameId").readOnly = true;
		document.getElementById("lastNameId").readOnly = true;
		if(receiptType == 'INSTALLMENTS'){
			document.getElementById("installmentNoId").readOnly = true;
			document.getElementById("lateFineId").readOnly = false;
			document.getElementById("calcInstButton").innerHTML = "<input type=\"button\" value=\"Get Installment\" onclick=\"calculateTempReceiptInstallment()\"/>"; 
		}
		else{
			document.getElementById("lateFineId").value = '0.0';
			document.getElementById("lateFineId").readOnly = true;
		}
		document.getElementById('radioButtonTr').style.display = "";		
	}else{
		document.getElementById("firstNameId").readOnly = false;
		document.getElementById("lastNameId").readOnly = false;
		
		//For Late fine for installments
		document.getElementById("lateFineId").value = '0.0';
		document.getElementById("lateFineId").readOnly = true;
		//For Member Joining Visibility of Radio Button (DSA/Associate )False
		
		document.getElementById('radioButtonTr').style.display = "none";
		document.getElementById('dsaIDtr').style.display = "none";
		document.getElementById('dsaNametr').style.display = "none";
		document.getElementById('associateIDtr').style.display = "";
		document.getElementById('associateNametr').style.display = "";
	}
	//Populate product types view
	ajaxRequestWithoutImage('PlanPopulate.do?receiptType='+receiptType+'&mode=productView','productTypeView');
	//reset the plan view if investment is not selected
	if(receiptType != 'INVESTMENT'){
		ajaxRequestWithoutImage('PlanPopulate.do?receiptType='+receiptType+'&mode=planView&productTypeId=','planView');
	}
}

function populateProductPlans(){
	var receiptType = document.getElementById("receiptTypeId").value;
	var productTypeId = document.getElementById("productTypeId").value;
	ajaxRequestWithoutImage('PlanPopulate.do?receiptType='+receiptType+'&mode=planView&productTypeId='+productTypeId,'planView');
}

function alterTempReceiptPaymentMode(){
	var paymentMode = document.getElementById("paymentModeId").value;
	document.getElementById("bankNameId").value = "";
	document.getElementById("chequeNoId").value = "";
	document.getElementById("branchNameId").value = "";
	document.getElementById("chequeDateId").value = "";
	if(paymentMode == "Cash" || paymentMode =="Transfer from MIP" || paymentMode =="MIP to Recurring"){
		document.getElementById("bankNameId").readOnly = true;
		document.getElementById("chequeNoId").readOnly = true;
		document.getElementById("branchNameId").readOnly = true;
	}else{
		document.getElementById("bankNameId").readOnly = false;
		document.getElementById("chequeNoId").readOnly = false;
		document.getElementById("branchNameId").readOnly = false;
	}
}

function resetTempReceiptChequeDate(){
	document.getElementById("chequeDateId").value = "";
}

function tempReceiptCalender(obj, div_id, field_id){
	var paymentMode = document.getElementById("paymentModeId").value;
	if(paymentMode != "Cash" && paymentMode !="Transfer from MIP" && paymentMode !="MIP to Recurring"){
		calender(obj, div_id, field_id);
	}
}

function tReceiptCallback(data){
	ajaxRequest('tempReceiptNotification.do?receiptNo='+data,'adminDashboardMainContent');
}

function populateTReceiptMultiplePayCustomer(data){
	
	//alert(data);
	var customerNumber = data.split(';')[1].split('=')[1];
	document.getElementById("customerNumberId").value = data.split(';')[1].split('=')[1];
	document.getElementById("firstNameId").value = data.split(';')[2].split('=')[1].split(' ')[0];
	
	var name = data.split(';')[2].split('=')[1].split(' ');
	var lastName = data.split(';')[2].split('=')[1].split(' ')[1];
	if(name.length >2){
		for(var i=2 ;i<name.length;i++){
			lastName = lastName +" "+ name[i];
		}
	}
	var psaNumber = data.split(';')[4].split('=')[1];
	document.getElementById("lastNameId").value = lastName;
	//alert(psaNumber);
	//document.getElementById("psanumberId").innerHTML = "PSA NUMBER : "+psaNumber;
	//document.getElementById("psaNumberFieldId").value = psaNumber;
	ajaxRequestWithoutImage("TemporaryReceiptInstallment.do?applicationNo="+psaNumber,"installmentAmountDivId");
	ajaxRequestWithReturnFunction("commonAjaxAction.do?mode=T_RECEIPT_CUST_DETAILS&customerNumber="+customerNumber+"&psaNumber="+psaNumber, populatePlanAndIntroducer);
}

function calculateTempReceiptInstallment(){
	if(document.getElementById("currentInstallmentNoId") && document.getElementById("installmentAmountId")){
		var installMentNumber = document.getElementById("currentInstallmentNoId").value;
		var installMentAmount = document.getElementById("installmentAmountId").value;
		var amount = document.getElementById("amountId").value;
		var remainingInstallments = document.getElementById("remainingInstallmentsId").value;
		var remainder = amount%installMentAmount;
		
		 if(amount == "" || amount==0){
			 alert("Please put amount");
		 }else if(remainder !=0){
			alert("amount should be multiple of installment amount");
		}else {
			var noOfInStallments = amount/installMentAmount;
			if(noOfInStallments>Number(remainingInstallments)){
			alert("You are trying to pay more than the number of remaining installments");
			//document.getElementById("amountId").value = "";
			}else if(noOfInStallments >1){
				var upperLimit = (Number(installMentNumber)+(noOfInStallments-1));
				installMentNumber = installMentNumber+"-"+upperLimit;
				document.getElementById("installmentNoId").value = installMentNumber;
			}else{
				document.getElementById("installmentNoId").value = installMentNumber;
			}
			document.getElementById("calcInstButton").innerHTML = "<input type=\"button\" value=\"Reset\" onclick=\"resetTempReceiptAmount()\"/>"; 
			document.getElementById("amountId").readOnly=true;
		}
	}else{
		alert("Select customer for multiple pay in");
	}
}

function resetTempReceiptAmount(){
	document.getElementById("installmentNoId").value="";
	document.getElementById("amountId").value="";
	document.getElementById("amountId").readOnly=false;
	document.getElementById("calcInstButton").innerHTML = "<input type=\"button\" value=\"Get Installment\" onclick=\"calculateTempReceiptInstallment()\"/>";
	
}
function populatePlanAndIntroducer(data){
	//alert(data);
	//alert("in populatePlanAndIntroducer()  data.split(';')[2].split('=')[1];; "+data.split(';')[2].split('=')[1]);
	//alert(" in populatePlanAndIntroducer()  data.split(';')[3].split('=')[1];; "+data.split(';')[3].split('=')[1]);
	//DSA Pref Share Change Temporary Receipt 
	
	var roleName = data.split(';')[4].split('=')[1];;
	//alert("roleName "+roleName);
	
	//Check Role Name is DSA or not
	
	//Role is DSA
	if(roleName == 'DSA'){
		
		document.getElementById("introducerNumberIdDSA").value = data.split(';')[0].split('=')[1];
		document.getElementById("introducerFirstNameIdDSA").value = data.split(';')[1].split('=')[1].split(' ')[0];
		
		var name = data.split(';')[1].split('=')[1].split(' ');
		var lastName = data.split(';')[1].split('=')[1].split(' ')[1];
		if(name.length >2){
			for(var i=2 ;i<name.length;i++){
				lastName = lastName +" "+ name[i];
			}
		}
		
		document.getElementById("introducerLastNameIdDSA").value = lastName;
		
		document.getElementById('associateIDtr').style.display = "none";
		document.getElementById('associateNametr').style.display = "none";
		document.getElementById('dsaIDtr').style.display = "";
		document.getElementById('dsaNametr').style.display = "";
		
		var radios = document.getElementsByName("selfIntroducer");
		
		setCheckedValue(radios, "dsa");
		
		
	}	//End Role DSA
	
	//Role is Associate
	else{
		
		document.getElementById("introducerNumberId").value = data.split(';')[0].split('=')[1];
		document.getElementById("introducerFirstNameId").value = data.split(';')[1].split('=')[1].split(' ')[0];
		
		var name = data.split(';')[1].split('=')[1].split(' ');
		var lastName = data.split(';')[1].split('=')[1].split(' ')[1];
		if(name.length >2){
			for(var i=2 ;i<name.length;i++){
				lastName = lastName +" "+ name[i];
			}
		}
		
		document.getElementById("introducerLastNameId").value = lastName;
		
		document.getElementById('dsaIDtr').style.display = "none";
		document.getElementById('dsaNametr').style.display = "none";
		document.getElementById('associateIDtr').style.display = "";
		document.getElementById('associateNametr').style.display = "";
		
		var radios = document.getElementsByName("selfIntroducer");
		
		setCheckedValue(radios, "N");
		
	}	//Role is Associate

	
	
	document.getElementById("planNameId").value = data.split(';')[2].split('=')[1];;
}

//start Added by snigdha For DSA setting Radio button dynamically
function setCheckedValue(radioObj, newValue) {
	//alert("In setCheckedValue()  ");
	if(!radioObj)
		return;
	var radioLength = radioObj.length;
	if(radioLength == undefined) {
		radioObj.checked = (radioObj.value == newValue.toString());
		return;
	}
	for(var i = 0; i < radioLength; i++) {
		radioObj[i].checked = false;
		if(radioObj[i].value == newValue.toString()) {
			radioObj[i].checked = true;
		}
	}
}

//start Added by snigdha For DSA setting Radio button dynamically

