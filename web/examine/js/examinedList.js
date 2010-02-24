var vid;
var vname;

function initDataTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var vid = encodeURIComponent(oRecord.getData().vedioID);
		var uid = encodeURIComponent(oRecord.getData().userID);
		var href = "<a href='./examine/toExamineTape.action?tapeScore.vedioID=";
		href += vid;
		href += "&uid=" + uid;
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
	    key :"vedioID",
	    label :"影带编号"
	}, {
		key :"vedioName",
		label :"影带名称"
	}, {
		key :"examiner",
		label :"打分人员",
		sortable :true,
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
		key :"award",
		label :"获奖情况",
		sortable :true
	}, {
		key :"purchase",
		label :"购买意见",
		sortable :true
	}, {
		key :"dateExamine",
		label :"打分时间",
		sortable :true,
		formatter :formatDate
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/examine/getExaminedTapes.action?vid="+vid+"&vname="+vname+"&");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "vedioID", "vedioName", "examiner", "storyScore", "techScore", "performScore", "innovateScore", "score", "award", "purchase", "dateExamine", "userID"],
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

function filterFunc(){
	vid = YAHOO.util.Dom.get("vid").value; 
	vname= YAHOO.util.Dom.get("searchinput").value;
	if((null==vid || ""==vid) && (null==vname || ""==vname)) return;
	YAHOO.example.DynamicData = initDataTable();
}
