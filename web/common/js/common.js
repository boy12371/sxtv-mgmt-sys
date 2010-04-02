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


//YUI data table column formatter common functions*****************************************
var formatorComments = function(elCell, oRecord, oColumn, sData) {
	if (sData !=null && sData.length > 10) {
		elCell.innerHTML = sData.substring(0, 10) + "...";
		elCell.title = sData;
	} else {
		elCell.innerHTML = sData;
	}
}
var formatCompany = function(elCell, oRecord, oColumn, sData) {
	elCell.innerHTML = sData.companyName;
}
var formatTopic = function(elCell, oRecord, oColumn, sData) {
	elCell.innerHTML = sData.topicName;
}
var formatSubject = function(elCell, oRecord, oColumn, sData) {
	elCell.innerHTML = sData.subjectName;
}
var formatDate = function(elCell, oRecord, oColumn, sData) {
	var idx = sData.indexOf("T");
	if (idx != -1) {
		elCell.innerHTML = sData.substring(0, idx);
	} else {
		elCell.innerHTML = sData;
	}
}
var formatStatus = function(elCell, oRecord, oColumn, sData) {
	elCell.innerHTML = sData.status;
}
var formatScroes = function(elCell, oRecord, oColumn, sData) {
	if (sData.length == 0) {
		elCell.innerHTML = "0";
	} else {
		var avgScore = 0;
		var total = 0;
		for (var i = 0; i < sData.length; i++) {
			total += sData[i].score;
		}
		var s = (total / sData.length).toString();
		s = s.substring(0, s.indexOf(".") + 3);
		elCell.innerHTML = s;
	}
}

var formatAward = function(elCell, oRecord, oColumn, sData) {
	sData = oRecord.getData("vedioscores");
	if (sData.length == 0) {
		elCell.innerHTML = "0/0";
	} else {
		var yes = 0;
		var no = 0;
		for (var i = 0; i < sData.length; i++) {
			if (sData[i].award == 1) {
				yes += 1;
			} else {
				no += 1;
			}
		}
		elCell.innerHTML = yes + "/" + no;
	}
}

var formatPurchase = function(elCell, oRecord, oColumn, sData) {
	sData = oRecord.getData("vedioscores");
	if (sData.length == 0) {
		elCell.innerHTML = "0/0";
	} else {
		var yes = 0;
		var no = 0;
		for (var i = 0; i < sData.length; i++) {
			if (sData[i].purchase == 1) {
				yes += 1;
			} else {
				no += 1;
			}
		}
		elCell.innerHTML = yes + "买" + no + "否";
	}
}


var formatAudienceScore = function(elCell, oRecord, oColumn, sData) {
	if (sData.length == 0) {
		elCell.innerHTML = "0/0";
	} else {
		var yes = 0;
		var no = 0;
		for (var i = 0; i < sData.length; i++) {
			if (sData[i].result == 1) {
				yes += 1;
			} else {
				no += 1;
			}
		}
		elCell.innerHTML = yes + "/" + no;
	}
}
