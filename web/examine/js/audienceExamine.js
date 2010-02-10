function initDataTable() {
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	};
	
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
	    key :"audience",
	    label :"观众名称",
	    sortable :true,
	}, {
		key :"tape",
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
		fields : [ "audience", "tape", "result", "dateExamine"],
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
		paginator :new YAHOO.widget.Paginator( {rowsPerPage :10})
		// Enables pagination
	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
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