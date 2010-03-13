var myDataTable;
function initAccuracyTable() {	
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
		label :"准确度",
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
	
	var startDate = YAHOO.util.Dom.get("startDate").value; 
	var endDate = YAHOO.util.Dom.get("endDate").value;
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=accuracy&dir=asc&startDateStr=" + startDate + "&endDateStr=" + endDate, // Initial
		sortedBy : {
			key :"accuracy",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {rowsPerPage:10})
	};
	// DataTable instance
	myDataTable = new YAHOO.widget.DataTable("accuracyTableDiv", myColumnDefs,
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

function filterFunc(){
	var startDate = YAHOO.util.Dom.get("startDate").value; 
	var endDate = YAHOO.util.Dom.get("endDate").value;
	if((null==startDate || ""==startDate) || (null==endDate || ""==endDate)) return;
	var callback = {
			success:myDataTable.onDataReturnInitializeTable,
			failure:myDataTable.onDataReturnInitializeTable,
			argument:myDataTable.getState(),
			scope:myDataTable
			};
	myDataTable.getDataSource().sendRequest(
			"startDateStr="+startDateStr+"&endDateStr="+endDateStr,
			callback
			);
}
