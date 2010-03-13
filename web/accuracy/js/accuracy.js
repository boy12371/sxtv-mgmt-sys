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
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=accuracy&dir=desc", // Initial
		sortedBy : {
			key :"accuracy",
			dir :YAHOO.widget.DataTable.CLASS_DESC
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
	var startDate = document.getElementsByName("startDateStr")[0].value; 
	var endDate = document.getElementsByName("endDateStr")[0].value;
	if((null==startDate || ""==startDate) || (null==endDate || ""==endDate)) return;
	var callback = {
			success:myDataTable.onDataReturnInitializeTable,
			failure:myDataTable.onDataReturnInitializeTable,
			argument:myDataTable.getState(),
			scope:myDataTable
			};
	myDataTable.getDataSource().sendRequest(
			"startDateStr="+startDate+"&endDateStr="+endDate,
			callback
			);
}
