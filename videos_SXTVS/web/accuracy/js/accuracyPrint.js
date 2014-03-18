var myDataTable;
function initAccuracyTable() {	
	var formatNum = function(elCell, oRecord, oColumn, sData) {
		if (sData == -1) {
			elCell.innerHTML = "无评分影带";
		} else {
			var _val = sData.toString();
			elCell.innerHTML = (_val.length > 5) ? _val.substring(0,5) : _val;
		}
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
	    key :"employeeName",
	    label :"姓名",
	    sortable :true
	}, {
		key :"userName",
		label :"用户名",
		sortable :true
	},  {
		key :"accuracy",
		label :"准确度(%)",
		formatter :formatNum,
		sortable :true
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/accuracy/getAccuracy.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "employeeName", "userName", "accuracy"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=accuracy&dir=desc&startIndex=0&startDate=" + start+"&endDate="+end, // Initial
		sortedBy : {
			key :"accuracy",
			dir :YAHOO.widget.DataTable.CLASS_DESC
		} // Sets UI initial sort arrow
	};
	// DataTable instance
	myDataTable = new YAHOO.widget.DataTable("accuracyTableDiv", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	};
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}
