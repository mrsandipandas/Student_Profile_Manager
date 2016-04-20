function GetXmlHttpObject()
{
    var objXMLHttp=null;
    if (window.XMLHttpRequest)
    {
        objXMLHttp=new XMLHttpRequest();
    }
    else if (window.ActiveXObject)
    {
        objXMLHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    return objXMLHttp;
}

function ajaxRequest(url,divid)
{
    if(document.getElementById("errorSpan"))
        document.getElementById("errorSpan").style.display = "none";
	
    var timestamp = new Date();
    url = url+ (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
    var xmlHttp=GetXmlHttpObject();
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request")
        return;
    }
	
	
    if(document.getElementById(divid) != null)
        document.getElementById(divid).innerHTML = "<img src=\"images/loading.gif\">";
    else
    {
        var mainContentId = document.getElementById("mainContentId");
        if(mainContentId != null)
        {
            mainContentId.innerHTML = "<div id=\"" + divid + "\"></div>";
            document.getElementById(divid).innerHTML = "<img src=\"Image/loading.gif\">";
        }
    }

    xmlHttp.open("GET",url,true)
    xmlHttp.onreadystatechange=function(){
        if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        {
            document.getElementById(divid).innerHTML=xmlHttp.responseText;
			
            //hideAll();
        }
    }
    xmlHttp.send(null);
}

function ajaxRequestToPostForSave(url,divid,postparams)
{
    var timestamp = new Date();
    url = url+ (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
    var xmlHttp=GetXmlHttpObject();
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request")
        return;
    }
	
    if(document.getElementById(divid) != null)
        document.getElementById(divid).innerHTML = "<img src=\"resources/images/loading.gif\">";
    else
    {
        var mainContentId = document.getElementById("mainContentId");
        if(mainContentId != null)
        {
            mainContentId.innerHTML = "<div id=\"" + divid + "\"></div>";
            document.getElementById(divid).innerHTML = "<img src=\"resources/images/loading.gif\">";
        }
    }
	
    xmlHttp.open("POST",url,true)
    xmlHttp.onreadystatechange=function(){
        if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        {
            document.getElementById(divid).innerHTML=xmlHttp.responseText;
        }
    }
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Content-length", postparams.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.setRequestHeader("If-Modified-Since", "Sat, 1 Jan 2000 00:00:00 GMT");
    xmlHttp.send(postparams);
}

//this function will take url and object reference of a function
//to be called after the ajax request is completed
function ajaxRequestWithReturnFunction(url,returnFunc)
{
    var xmlHttp = GetXmlHttpObject();
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request")
        return;
    }
    var timestamp = new Date();
    url = url+ (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
    xmlHttp.open("GET",url,false);
    xmlHttp.onreadystatechange = function(){

    if (xmlHttp.readyState==4 && xmlHttp.status==200)
        {
            returnFunc(xmlHttp.responseText);
        }
    }
    xmlHttp.send(null);
}

//this function will take url and object reference of the dropdown
//to be populated after the ajax request is completed
//colSeperator : seperator between text and value 
//(value comes before text in response)
//rowSeperator : seperator between 2 rows of ajax response
function ajaxRequestWithDropDown(url,dropdown,colSeperator,rowSeperator)
{
    var xmlHttp = GetXmlHttpObject();
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request")
        return;
    }
    var timestamp = new Date();
    url = url+ (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
    xmlHttp.open("GET",url,false);
    xmlHttp.onreadystatechange = function(){
        if (xmlHttp.readyState==4 && xmlHttp.status==200)
        {
            for(var i = dropdown.options.length - 1; i >= 1; i--)
            {
                dropdown.remove(i);
            }
            var optn, response = xmlHttp.responseText;
            var responseRows = response.split(rowSeperator);
            for(var i=0;i<responseRows.length;i++) {
                var sp = responseRows[i].split(colSeperator);
                if(sp.length==2) {
                    optn = document.createElement("OPTION");
                    optn.text = sp[0];
                    optn.value = sp[1];
                    dropdown.options.add(optn);
                }
            }
        }
    }
    xmlHttp.send(null);
}

function ajaxRequestWithoutImage(url,divid)
{
    if(document.getElementById("errorSpan"))
        document.getElementById("errorSpan").style.display = "none";
	
    var timestamp = new Date();
    url = url+ (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
    var xmlHttp=GetXmlHttpObject();
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request")
        return;
    }
	
	
    xmlHttp.open("GET",url,true)
    xmlHttp.onreadystatechange=function(){
        if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        {
            document.getElementById(divid).innerHTML=xmlHttp.responseText;
			
            //hideAll();
        }
    }
    xmlHttp.send(null);
}

function ajaxRequestWithNoReturn(url)
{
    if(document.getElementById("errorSpan"))
        document.getElementById("errorSpan").style.display = "none";

    var timestamp = new Date();
    url = url+ (url.indexOf("?") > 0 ? "&" : "?")+ "timestamp="+ timestamp.getTime();
    var xmlHttp=GetXmlHttpObject();
    if (xmlHttp==null)
    {
        alert ("Browser does not support HTTP Request")
        return;
    }


    xmlHttp.open("GET",url,true)
    xmlHttp.onreadystatechange=function(){
        if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
        {
            
        }
    }
    xmlHttp.send(null);
}
