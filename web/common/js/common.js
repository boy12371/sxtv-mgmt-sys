function autoCompleteVideoName() {
	var myDataSource = new YAHOO.util.XHRDataSource(
			"/tv/search/autoCompleteForVideoName.action");
	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "vname" ]
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
		oDS :myDataSource,
		oAC :myAutoComp
	}
}

function parseDate(str) {
	if (typeof str == 'string') {
		var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
		if (results && results.length > 3)
			return new Date(parseInt(results[1]), parseInt(results[2]) - 1,
					parseInt(results[3]));
		results = str
				.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
		if (results && results.length > 6)
			return new Date(parseInt(results[1]), parseInt(results[2]) - 1,
					parseInt(results[3]), parseInt(results[4]),
					parseInt(results[5]), parseInt(results[6]));
		results = str
				.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
		if (results && results.length > 7)
			return new Date(parseInt(results[1]), parseInt(results[2]) - 1,
					parseInt(results[3]), parseInt(results[4]),
					parseInt(results[5]), parseInt(results[6]),
					parseInt(results[7]));
	}
	return null;
}
function highLightRow(elTr, oRecord) {
	var xData = oRecord.getData();
	if (typeof (xData.dateInput) != "undefined") {
		// elTr.className = elTr.className +
		// YAHOO.widget.DataTable.CLASS_HIGHLIGHTED;
		var data = xData.dateInput;
		var idx = data.indexOf("T");
		var dateString = data.substring(0, idx);
		var inputDate = parseDate(dateString);
		var date = new Date();
		var duration = Math.abs(date - inputDate)/1000/60/60/24;
		if (duration > 30) {
			elTr.className = elTr.className + " mark2";
		}

	}
	return true;
}

var myPaginator = new YAHOO.widget.Paginator({
			rowsPerPage :25,
			firstPageLinkLabel :"第一页",
			lastPageLinkLabel :" 尾页",
			previousPageLinkLabel :" 上一页",
			nextPageLinkLabel :" 下一页",
			template :"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
			pageReportTemplate :"Showing items {startIndex} - {endIndex} of {totalRecords}",
			rowsPerPageOptions : [25, 50,100 ]
		});

function displayErrorMsg(msg) {
	var ul = document.getElementById("errorMsgUL");
	if (null == ul)
		return;
	var span = document.getElementById("errorMsgSpan");
	if (null == span)
		return;
	span.innerHTML = msg;
	ul.style.display = "block";
}

function clearErrorMsg() {
	var ul = document.getElementById("errorMsgUL");
	if (null == ul)
		return;
	var span = document.getElementById("errorMsgSpan");
	if (null == span)
		return;
	span.innerHTML = "";
	ul.style.display = "none";
}