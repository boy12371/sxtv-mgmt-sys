var historyTable = null;
//arrange table
function initPrintTable() {
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		if(null==sData || ""==sData || sData.indexOf("1000")!=-1){
			elCell.innerHTML = "";
			return;
		}
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
	    key :"id",
	    label :"编号"
	}, {
		key :"vedioName",
		label :"名称"
	}, {
		key :"playDate",
		label :"播放日期",
		formatter :formatDate
	}, {
		key :"subject",
		label :"栏目"		
	}, {
		key :"topic",
		label :"题材"		
	}, {
		key :"dateComing",
		label :"收带日期",
		formatter :formatDate
	}, {
		key :"avgScore",
		label :"平均分"
	}, {
		key :"audiScore",
		label :"观众投票(看/不看)"
	}, {
		key :"companyID",
		label :"公司"
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/arrange/getArrangedHistory.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "playDate", "subject", "topic", "dateComing", "companyID", "marked", "avgScore", "audiScore"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=playDate&dir=asc&month="+selMonth+"&subject="+selSubject, // Initial
		dynamicData :true, // Enables dynamic server-driven data
		sortedBy : {
			key :"playDate",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		}// Sets UI initial sort arrow
	};
	// DataTable instance
	historyTable = new YAHOO.widget.DataTable("historyDiv", myColumnDefs, myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	historyTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	};
	
	return {
		ds :myDataSource,
		dt :historyTable
	};
}