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
		initialRequest :"sort=dateComing&dir=asc", // Initial
		sortedBy : {
			key :"employeeName",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator( {rowsPerPage:10}),
		formatRow: myRowFormatter
	};
	// DataTable instance
	vat table = new YAHOO.widget.DataTable("accuracyTableDiv", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	table.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	
	return {
		ds :myDataSource,
		dt :table
	};
}
