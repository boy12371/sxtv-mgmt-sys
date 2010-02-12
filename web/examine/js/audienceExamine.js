var myDataTable;
function initDataTable() {
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	};
	var myRowFormatter = function(elTr, oRecord){
		var xData = oRecord.getData();
		if(typeof(xData.marked)!="undefined" && 1==xData.marked){
			elTr.className = elTr.className + " markedRow";
		}
		return true;
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
	    key :"audience",
	    label :"观众名称",
	    sortable :true,
	}, {
		key :"tapeName",
		label :"影带名称",
	}, {
		key :"result",
		label :"评价结果"		
	}, {
		key :"dateExamine",
		label :"评价日期",  
		sortable :true,
		formatter :formatDate
	} ];

	// DataSource instance
	var vedioID = document.getElementById("vedioID").innerHTML;
	var myDataSource = new YAHOO.util.DataSource("/tv/examine/getAudienceExamine.action?tape.vedioID=" + vedioID + "&");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "audience", "tapeName", "result", "dateExamine"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=dateExamine&dir=asc&startIndex=0&results=10", // Initial
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"dateExamine",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {rowsPerPage :10}),
		// Enables pagination
		formatRow: myRowFormatter
	};

	// DataTable instance

	myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}

	return {
		ds :myDataSource,
		dt :myDataTable
	};
}

function addAction(){
	var tapeID = document.getElementById("vedioID").innerHTML;
	var tapeName = document.getElementById("vedioName").innerHTML;
	var audience = document.getElementById("audienceName").value;
	var result = document.getElementById("look").checked?"看":"不看";
	var xData = {
		tapeID:tapeID,
		tapeName:tapeName,
		audience:audience,
		result:result,
		dateExamine:"",
		//mark this row is new need to be submit.
		marked:1
	};
	var pos = getRecordFormTable(xData);
	if(null != pos){
		myDataTable.deleteRow(pos);  
	}
	myDataTable.addRow(xData,0);
	markRow(0);
}

function submitAction(){
	var newResult = document.getElementById("newResult");
	newResult.value = "xxxxxxxxxxxxx";
	document.forms[0].submit();
}

function getRecordFormTable(xData){
	for(var i=0,record=myDataTable.getRecord(i); null != record; i++,record=myDataTable.getRecord(i)){
		var tData = record.getData();
		if(xData.audience == tData.audience && xData.tapeName == tData.tapeName){
			return i;
		}
	}
	return null;
}
