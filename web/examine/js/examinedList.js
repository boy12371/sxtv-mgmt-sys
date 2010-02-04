function initDataTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
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
		key :"vedioName",
		label :"影带名称",
		formatter :formatUrl
	}, {
		key :"storyScore",
		label :"情节",
		sortable :true
	}, {
		key :"techScore",
		label :"技术",
		sortable :true
	}, {
		key :"performScore",
		label :"表演",
		sortable :true
	}, {
		key :"innovateScore",
		label :"创新",
		sortable :true
	}, {
		key :"score",
		label :"综合",
		sortable :true
	}, {
		key :"dateExamine",
		label :"打分时间",
		sortable :true,
		formatter :formatDate
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/examine/getExaminedTapes.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "vedioName", "storyScore", "techScore", "performScore", "innovateScore", "score", "dateExamine"],
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

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs, myDataSource, myConfigs);
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