function autoCompleteVideoName() {
	var myDataSource = new YAHOO.util.XHRDataSource(
			"/tv/search/autoCompleteForVideoName.action");
	myDataSource.responseSchema = {
		resultsList : "records",
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
		oDS : myDataSource,
		oAC : myAutoComp
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
		var duration = Math.abs(date - inputDate) / 1000 / 60 / 60 / 24;
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

// YUI data table column formatter common
// functions*****************************************
var formatorComments = function(elCell, oRecord, oColumn, sData) {
	if (sData != null && sData.length > 10) {
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
		for ( var i = 0; i < sData.length; i++) {
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
		for ( var i = 0; i < sData.length; i++) {
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
		for ( var i = 0; i < sData.length; i++) {
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
		for ( var i = 0; i < sData.length; i++) {
			if (sData[i].result == 1) {
				yes += 1;
			} else {
				no += 1;
			}
		}
		elCell.innerHTML = yes + "/" + no;
	}
}
var auRateFormater = function(elCell, oRecord, oColumn, sData) {
	if (sData == "null" || sData == null) {
		elCell.innerHTML = "0";
	} else {
		elCell.innerHTML = sData;
	}
}

var mkShareFormatter = function(elCell, oRecord, oColumn, sData) {
	if (sData == "null" || sData == null) {
		elCell.innerHTML = "0";
	} else {
		elCell.innerHTML = sData;
	}
}

var statusSortor = function(elCell, oRecord, oColumn, sData) {
	if (sData == "null" || sData == null) {
		elCell.innerHTML = "0";
	} else {
		elCell.innerHTML = sData;
	}
}
function fixTableWidthWithScrollBar(divId) {
	// change datatable css for scroll bar
	var elements = YAHOO.util.Dom.getElementsByClassName('yui-dt-bd', 'div',
			divId);
	for ( var i = 0; i < elements.length; i++) {
		YAHOO.util.Dom.setStyle(elements[i], "border", "0px");
		YAHOO.util.Dom.setStyle(elements[i], "background-color", "transparent");
	}

	var elementsh = YAHOO.util.Dom.getElementsByClassName('yui-dt-hd', 'div',
			divId);
	for ( var i = 0; i < elementsh.length; i++) {
		YAHOO.util.Dom.setStyle(elementsh[i], "border", "0px");
		YAHOO.util.Dom
				.setStyle(elementsh[i], "background-color", "transparent");
	}

}

var sortScores = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}

	var _aScores = a.getData("vedioscores");
	var _bScores = b.getData("vedioscores");

	var _aAvgScore = 0;
	var _aTotal = 0;
	if (_aScores.length != 0) {
		for ( var i = 0; i < _aScores.length; i++) {
			_aTotal += _aScores[i].score;
		}
		_aAvgScore = _aTotal / _aScores.length;
	}

	var _bAvgScore = 0;
	var _bTotal = 0;
	if (_bScores.length != 0) {
		for ( var i = 0; i < _bScores.length; i++) {
			_bTotal += _bScores[i].score;
		}
		_bAvgScore = _bTotal / _bScores.length;
	}
	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aAvgScore, _bAvgScore, desc);
	return compState;
}

var sortCompany = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}

	var _aCom = a.getData("companyID");
	var _bCom = b.getData("companyID");

	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aCom.id, _bCom.id, desc);
	return compState;
}

var sortSubject = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}

	var _aCom = a.getData("subject");
	var _bCom = b.getData("subject");

	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aCom.id, _bCom.id, desc);
	return compState;
}

var sortTopic = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}

	var _aCom = a.getData("topic");
	var _bCom = b.getData("topic");

	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aCom.id, _bCom.id, desc);
	return compState;
}

var auRateSortor = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}
	var _aR=0;
	var _bR=0;
	var _aCom = a.getData("audienceRating");
	var _bCom = b.getData("audienceRating");
	
	if (_aCom != null) {
		_aR = _aCom;
	}
	if (_bCom != null) {
		_bR = _bCom;
	}
	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aR, _bR, desc);
	return compState;
}

var marketShareSortor = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}
	var _aR=0;
	var _bR=0;
	var _aCom = a.getData("marketShare");
	var _bCom = b.getData("marketShare");
	
	if (_aCom != null) {
		_aR = _aCom;
	}
	if (_bCom != null) {
		_bR = _bCom;
	}
	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aR, _bR, desc);
	return compState;
}
var statusSortor = function(a, b, desc) {
	if (!YAHOO.lang.isValue(a)) {
		return (!YAHOO.lang.isValue(b)) ? 0 : 1;
	} else if (!YAHOO.lang.isValue(b)) {
		return -1;
	}
	var _aCom = a.getData("status");
	var _bCom = b.getData("status");
	// First compare by state
	var comp = YAHOO.util.Sort.compare;
	var compState = comp(_aCom.id, _bCom.id, desc);
	return compState;
}
function resizeScrollTable(containerId){
	var container = document.getElementById(containerId);
	var tables = container.getElementsByTagName("table");
	if (null != tables && 0 != tables.length) {
		if (typeof(container.originalWidth)=="undefined" || tables[0].offsetWidth < container.originalWidth) {
			var tableWidth;
			if(typeof(container.originalWidth)=="undefined"){
				if(tables[0].offsetWidth > 920) tableWidth=920;
				else tableWidth = tables[0].offsetWidth;
				container.originalWidth = tableWidth;
				container.style.width = tableWidth + "px";
			}else{
				container.style.width = tables[0].offsetWidth + "px";
			}
		}
	}
}