
function idSearchCallBack(data)
{
	//alert('data : '+data);
	document.getElementById('memberId').value=data.split(';')[1].split('=')[1];
 	
 	document.getElementById('devMemberId').value=data.split(';')[5].split('=')[1];
}

function binaryEquityCallBack(data)
{
	//alert('data : '+data);
	document.getElementById('newInvestorPositionId').value = data;
}




function searchExistingPerson(url)
{
	var winPersonSearch =window.open(url,'','width=400,height=400,left=100,top=100,menubar=0,toolbar=0,status=no,titlebar=0,scrollbars=1');
    return winPersonSearch;
}

function checkSessionValid(){
	var sessionXMLHttp = null;
	
	if (window.XMLHttpRequest)
	{
		sessionXMLHttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject)
	{
		sessionXMLHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var timestamp = new Date();
	var url = "checkSession.do";
  	url = url + (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
  	
  	sessionXMLHttp.open("GET",url,true);
  	
	sessionXMLHttp.onreadystatechange=function(){
		//alert("sessionXMLHttp.readyState = " + sessionXMLHttp.readyState)
		if (sessionXMLHttp.readyState == 4 || sessionXMLHttp.readyState == "complete")
		{
			  var isSessionValid = sessionXMLHttp.responseText; 
			  //alert("isSessionValid = " + isSessionValid)
			  if(isSessionValid == "0")
			  {
			  	window.location.href = "jsp/login/login.jsp";
			  }
	    }
	};
	sessionXMLHttp.send(null);
}


function backToHome()
{
	var frm = document.createElement("form");
    frm.setAttribute("method", "post");
    frm.setAttribute("action", "navigator.do?page=user.dashboard");	
	
	document.body.appendChild(frm);    
    frm.submit();
}


	function clearFormWithFieldSets()
	{
		var oForm = document.forms[0];
   		
   		var fieldArray = new Array();
   		
   		var selectArray = new Array();
   		
   		fieldArray = oForm.getElementsByTagName('INPUT');
   		
   		if(fieldArray.length>0)
   		{
   			for(var i = 0 ; i < fieldArray.length ; i++)
   			{
   				if(fieldArray[i].type.toLowerCase()!='button' && fieldArray[i].type.toLowerCase()!='submit' && fieldArray[i].name!='agentName' && fieldArray[i].name!='agentId')
   				{
   					fieldArray[i].value='';
   					if(fieldArray[i].id=='applicantNameId')
   					{
   						//alert('congrats');
   						fieldArray[i].readOnly=false;
   					}
   				}
   			}
   		}
   		
   		selectArray = oForm.getElementsByTagName('SELECT');
   		
   		if(selectArray.length>0)
   		{
   			for(var j = 0 ; j < selectArray.length ; j++)
   			{
   				selectArray[j].value='-1';
   			}
   		}
   		
   		var textAreaArray = new Array();
   		
   		textAreaArray = oForm.getElementsByTagName('TEXTAREA');
   		
   		if(textAreaArray.length>0)
   		{
   			for(var k = 0 ; k < textAreaArray.length ; k++)
   			{
   				textAreaArray[k].value='';
   			}
   		}
   		
   		
   		
   		
	}


function clearForm()
{
	// alert('Inside clearForm() ');
   var oForm = document.forms[0];
   
   var frm_elements = oForm.elements; 
   
   for(i=0; i<frm_elements.length; i++) {

	field_type = frm_elements[i].type.toLowerCase();

	switch(field_type) {
			case "text":
			case "password":
			case "textarea":
			case "hidden":		
				frm_elements[i].value = "";
			break;

			case "radio":
			case "checkbox":	
				if (frm_elements[i].checked) {
					frm_elements[i].checked = false;
				}
			
			break;

			case "select":
		//	case "select-one":
		//	case "select-multi":
			frm_elements[i].selectedIndex = -1;
			
			break;

			default:
			break;
		}
	} 
}



function validateDob(dt)
       {
       //    var dt = document.getElementById('nomineeDoBId').value;
       //    alert('nomineeDoBId ' + dt);
           var mySplitResult = dt.split('-');

           var dtday = mySplitResult[0];
           var dtmonth = mySplitResult[1];
           var dtyear = mySplitResult[2];

      //     alert(' dtday ' + dtday + ' dtmonth '+ dtmonth + 'dtyear ' + dtyear);

           var enteredDob = new Date();

           enteredDob.setDate(dtday);
           enteredDob.setMonth(dtmonth-1); 
           enteredDob.setFullYear(dtyear);

           var today = new Date();
           
           var date1_ms = enteredDob.getTime()
		var date2_ms = today.getTime()
		
		var difference_ms = Math.abs(date1_ms - date2_ms)

  var one_day=1000*60*60*24
  
  // Convert back to days and return
  var days = Math.round(difference_ms/one_day);
  
  var years = Math.floor(days/365.25);

       if(years >= 18 )
        {
			return true;
        }
        return false;
       }
 
function isPreviousDate(dt) {
	var mySplitResult = dt.split('-');

    var dtday = mySplitResult[0];
    var dtmonth = mySplitResult[1];
    var dtyear = mySplitResult[2];


    var enteredDt = new Date();

    enteredDt.setDate(dtday);
    enteredDt.setMonth(dtmonth-1); 
    enteredDt.setFullYear(dtyear);
    
    var today = new Date();
    
    if(enteredDt>=today) {
    	return false;
    }
    return true;
}

// returns >0,0,<0 in case
// dt1>dt2, dt1==dt2, dt1<dt2 respectively
function compareDates(dt1,dt2) {
    var date1 = new Date(), date2 = new Date();
	var dateSplit1 = dt1.split('-'), dateSplit2 = dt2.split('-');

    var dt1day = dateSplit1[0];
    var dt1month = dateSplit1[1];
    var dt1year = dateSplit1[2];

    date1.setDate(dt1day);
    date1.setMonth(dt1month-1); 
    date1.setFullYear(dt1year);
    
    var dt2day = dateSplit2[0];
    var dt2month = dateSplit2[1];
    var dt2year = dateSplit2[2];

    date2.setDate(dt2day);
    date2.setMonth(dt2month-1); 
    date2.setFullYear(dt2year);
    
    return date1 - date2;
}


function isFutureDate(dt) {
	var mySplitResult = dt.split('-');

    var dtday = mySplitResult[0];
    var dtmonth = mySplitResult[1];
    var dtyear = mySplitResult[2];


    var enteredDt = new Date();

    enteredDt.setDate(dtday);
    enteredDt.setMonth(dtmonth-1); 
    enteredDt.setFullYear(dtyear);
    
    var today = new Date();
    
    if(enteredDt>today) {
    	return true;
    }
    return false;
}


 numbercheck="0123456789";           
            
 function onlynumbernolimits(t,v){        
         var w = "";
         for (i=0; i<t.value.length; i++) {
         x = t.value.charAt(i);
         if (v.indexOf(x,0) != -1)
         w += x;
         }
         t.value = w;        
     }
     
 
 // Allow Alphabates
function onlyAlphabet(e)
{
	var code = e.keyCode ? event.keyCode : e.which ? e.which : e.charCode;
	//alert(code);
	if ((code >= 65 && code <= 90)||(code >= 97 && code <= 122) || code==46 || code==8 || code==17 || code==127 || code==9 || code==16 || code==37 || code==38 || code==39 || code==40)
	{ 
        checknos = true;
		return (checknos);
	}
	else
	{
		checknos= false;			
	//	alert("Only Alphabates Allowed !");		
		return (checknos);        
	}
}

function onlyAlphabetwithSpace(e)
{
	var code = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
	//alert(code);s
	//if ((code >= 65 && code <= 91)||(code >= 97 && code <= 123) ||(code == 32))
   // if ((code >= 65 && code <= 90)||(code >= 97 && code <= 122) || code==32 || code==46 || code==8 || code==17 || code==127 || code==9 || code==16 || code==37 || code==38 || code==39 || code==40)		
	if ((code >= 65 && code <= 90)||(code >= 97 && code <= 122) || code==32 || code==8 || code==17 || code==9 || code==16)
	{ 
        checknos = true;
		return (checknos);
	}
	else
	{
		checknos= false;			
	//	alert("Only Alphabates Allowed !");		
		return (checknos);        }
}



function onlyAlplhaNumericWithSomeSplChars(e)
{
	var code = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
	//alert(code);
	if ((code >= 65 && code <= 91)||(code >= 97 && code <= 123) || (code >= 48 && code <= 57) || (code==13) || code==32 || code==8 || code==17 || code==127 || code==9 || code==16 || code==38 || code==43 || code==44 || code==47  || code==61 || code==64)
	
	{ 
		checknos = true;
		return (checknos);
	}
	else
	{
		checknos= false;
		// alert("Only Alphanumeric Allowed !");
		return (checknos);
	}
}



function isNotFutureYear(obj)
{
	var enteredYear = obj.value;
	var enteredYearInt = parseInt(enteredYear);
	
	var d = new Date();
	//	 alert(" d " + d);
	var currentYear = d.getFullYear(); 
	// alert('Current Year ' + currentYear + ' enteredYearInt ' + enteredYearInt);
	
	/*
	if(enteredYearInt <= currentYear)
	{
		alert(' enteredYearInt is less than equal to currentYear ');
		return true;
	}	
	else 
	*/
	if(enteredYearInt > currentYear)
	{
	//	alert(' enteredYearInt is greater than currentYear ');
		return false;
	}	
		return true;
}
/*

function submitCertNoForm(contextpath)
	{
	//	openModalWin('printCert.do?',800,700);
		
		var custName = document.certificatePreprintForm.custName.value;
		var totalUnits = document.certificatePreprintForm.totalUnits.value;
		var folioNo = document.certificatePreprintForm.folioNo.value;
		var plantId = document.certificatePreprintForm.plantId.value;
		var productTypeId = document.certificatePreprintForm.productTypeId.value;
		var certificateNo = document.certificatePreprintForm.certificateNo.value;
		var nomineeName = document.certificatePreprintForm.nomineeName.value;
		var fromAllocationUnit = document.certificatePreprintForm.fromAllocationUnit.value;
		var toAllocationUnit = document.certificatePreprintForm.toAllocationUnit.value;
		var applicationDateStr = document.certificatePreprintForm.applicationDateStr.value;
		var productInstancePayinId = document.certificatePreprintForm.productInstancePayinId.value;
		
		var url = contextpath + '/printCert.do?custName=' + encodeURIComponent(custName) + '&totalUnits=' + totalUnits + '&folioNo=' + folioNo + '&certificateNo=' + encodeURIComponent(certificateNo) + '&nomineeName=' + encodeURIComponent(nomineeName) + '&productTypeId=' + productTypeId + '&fromAllocationUnit=' + fromAllocationUnit + '&toAllocationUnit=' + toAllocationUnit + '&applicationDateStr=' + applicationDateStr + '&productInstancePayinId=' + productInstancePayinId ;
		// alert(url);
		openModalWin(url,800,700);
		
	}
	
	
	function confirmPrintSuccess(contextpath)
	{
		var answer = confirm('Are you sure that you have printed the Certificate ? ');
		if(answer)
		{
			var certificateNo = document.certificatePreprintForm.certificateNo.value;
			var productInstancePayinId = document.certificatePreprintForm.productInstancePayinId.value;
			
			var url = contextpath + '/certTrack.do?mode=printsuccess' + '&certificateNo=' + certificateNo + '&productInstancePayinId=' + productInstancePayinId;
		//	alert(url);
			//document.certificatePreprintForm.action = url;
			ajaxRequest(url,'adminDashboardMainContent')
			document.certificatePreprintForm.submit();
		}
		else
		{
			alert('You can print ');
		}
	
	
	}	
	
	function verifyCertificateDispathSave()
	{
		var retVal = false;
		alert('inside verifyCertificateDispathSave ');
	
	var cetificateIds = document.getElementsByName("cetificateId");
		for (i=0; i<cetificateIds.length; i++){
		
			if (cetificateIds[i].checked)
			{
				// alert("Checkbox at index "+i+" is checked!")
				retVal = true;
				// alert(retVal);
			}
		}
		
		if(!retVal)
		alert('You must select one customer to dispatch');
	
		return retVal;
	}
	

                
*/
function isPositiveNumber(evt, value) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	if(value.split(".").length<2 && charCode==46)
		return true;
	else {
		if (charCode > 31 && (charCode < 48 || charCode > 57))
   			return false;
		return true;
	}
}

function isCurrencyKey(evt, value) {
	var charCode = (evt.which) ? evt.which : event.keyCode
	if(value.split(".").length<2 && charCode==46)
		return true;
	else {
		if (charCode > 31 && (charCode < 48 || charCode > 57))
   			return false;
		return true;
	}
}

function isAlphanumericSpecialChar(evt)
{
  
  var charCode = (evt.which) ? evt.which : event.keyCode
         if ((charCode ==42) || (charCode ==33) || (charCode ==34) || (charCode ==35) || (charCode ==36) || (charCode ==37) || (charCode ==38) || (charCode ==42))
          {
               
  		return false;
          } 
	 
          return true;
}

function imposeMaxLength(Object,MaxLen)
{
	if (Object.value.length >= MaxLen)
	{
		alert("You cannot type more than "+MaxLen+" characters");
		Object.value = Object.value.substring(0, MaxLen-1);
		Object.focus();
        return true;
    }  
}

//function is supposed to be called from onchange or onclick event of driver check box
function checkBoxMultiSelect(drivingCheckBoxName, drivenCheckBoxesName) {
	var driverCBox = document.getElementsByName(drivingCheckBoxName)[0];
	var drivenCBoxes = document.getElementsByName(drivenCheckBoxesName);
	var checkStatus = false;
	if(driverCBox.checked) {
		checkStatus = true;
	}
	for ( var i = 0; i < drivenCBoxes.length; i++) {
		drivenCBoxes[i].checked = checkStatus;
	}
}

function checkBoxSync(drivingCheckBoxName, drivenCheckBox, drivenCheckBoxesName) {
	var driverCBox = document.getElementsByName(drivingCheckBoxName)[0];
	var drivenCBoxes = document.getElementsByName(drivenCheckBoxesName);
	if(!drivenCheckBox.checked) {
		driverCBox.checked = false;
	}
	else {
		for ( var i = 0; i < drivenCBoxes.length; i++) {
			if(!drivenCBoxes[i].checked) {
				driverCBox.checked = false;
				return;
			}
		}
		driverCBox.checked = true;
	}
}

function isEnoughLengthComList()
{
    if(document.getElementById('memberNumber').value=='')
    {
       alert('You must enter Member Id to search');
       return false;	    
    }
    else{
		return true;
    }
}

//--------------------------------------FOR CLOCK TO MOVE ON---------------------------------------------------------------------------------
function startTime()
{
var today=new Date();
var h=today.getHours();
var m=today.getMinutes();
var s=today.getSeconds();
// add a zero in front of numbers<10
m=checkTime(m);
s=checkTime(s);
document.getElementById('txt').innerHTML=h+":"+m+":"+s;
t=setTimeout('startTime()',500);
}

function checkTime(i)
{
if (i<10)
  {
  i="0" + i;
  }
return i;
}
