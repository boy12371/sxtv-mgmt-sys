

function autoCompleteVideoName() {
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/search/autoCompleteForVideoName.action");
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["vname"]
	};

	// Instantiate AutoComplete
	var myAutoComp = new YAHOO.widget.AutoComplete("searchinput",
			"searchcontainer", myDataSource);
	myAutoComp.queryMatchContains = true;
	myAutoComp.queryQuestionMark = false;
	myAutoComp.useShadow = true;
	myAutoComp.generateRequest = function(sQuery) {
		return "?query=" + encodeURI(sQuery);
	}
	// Keeps container centered
	/*
	 * myAutoComp.doBeforeExpandContainer = function(oTextbox, oContainer,
	 * sQuery, aResults) { var pos = YAHOO.util.Dom.getXY(oTextbox); pos[1] +=
	 * YAHOO.util.Dom.get(oTextbox).offsetHeight + 2;
	 * YAHOO.util.Dom.setXY(oContainer, pos); return true; };
	 */

	return {
		oDS : myDataSource,
		oAC : myAutoComp
	}
}

function displayErrorMsg(msg){
	var ul = document.getElementById("errorMsgUL");
	if(null == ul) return;
	var span = document.getElementById("errorMsgSpan");
	if(null == span) return;
	span.innerHTML = msg;
	ul.style.display="block";
}

function clearErrorMsg(){
	var ul = document.getElementById("errorMsgUL");
	if(null == ul) return;
	var span = document.getElementById("errorMsgSpan");
	if(null == span) return;
	span.innerHTML = "";
	ul.style.display="none";
}