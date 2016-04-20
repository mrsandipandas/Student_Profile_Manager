
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}

String.prototype.startsWith = function(str) 
{
	return (this.match("^"+str)==str)
}


String.prototype.ReplaceAll = function(stringToFind,stringToReplace){

	var temp = this;
	
	var index = temp.indexOf(stringToFind);
	
	    while(index != -1){
	
	        temp = temp.replace(stringToFind,stringToReplace);
	
	        index = temp.indexOf(stringToFind);
	
	    }
	
	    return temp;

}
