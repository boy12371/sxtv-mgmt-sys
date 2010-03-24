var historyTable = null;
var selMonth="";

function initSelectElements(){
	var selYear = document.getElementById("selectYear");
	var firstYear = parseInt(firstArrangedDate.split("-")[0],10);
	var endYear = parseInt(nowDate.split("-")[0],10);
	var endMonth = parseInt(nowDate.split("-")[1],10);
	if(endMonth == 1){
		endYear --;
		endMonth = 12;
	}
	for(var i=firstYear;i<=endYear;i++){
		selYear.options.add(new Option(i+"年",i));
	}
	selYear.options[selYear.options.length-1].selected = true;
	selectYearFunc(selYear);
}

function selectYearFunc(self){
	var selMonth = document.getElementById("selectMonth");
	var startMonth = 1;
	var stopMonth = 12;
	var firstYear = parseInt(firstArrangedDate.split("-")[0],10);
	var firstMonth = parseInt(firstArrangedDate.split("-")[1],10);
	var endYear = parseInt(nowDate.split("-")[0],10);
	var endMonth = parseInt(nowDate.split("-")[1],10);
	if(endMonth == 1){
		endYear --;
		endMonth = 12;
	}else{
		endMonth --;
	}
	var year = YAHOO.util.Dom.get("selectYear").value;
	if(firstYear == year) startMonth = firstMonth;
	if(endYear == year) stopMonth = endMonth;
	selMonth.options.length=0;
	if(firstYear == endYear && startMonth > stopMonth){
		displayErrorMsg("暂无历史编排记录。");
		return;
	}
	for(var i=startMonth;i<=stopMonth;i++){
		selMonth.options.add(new Option(i+"月",i));
	}
	selMonth.options[0].selected = true;
	selectMonthFunc(selMonth);
}

function selectMonthFunc(self){
	var year = YAHOO.util.Dom.get("selectYear").value;
	var month = YAHOO.util.Dom.get("selectMonth").value;
	selMonth = year + "-" + month; 	
	refreshTable();
}

function refreshTable(){
	if(null == historyTable){
		initHistoryTable();
	}
	var callback = {
			success:historyTable.onDataReturnInitializeTable,
			failure:historyTable.onDataReturnInitializeTable,
			argument:historyTable.getState(),
			scope:historyTable
			};
	historyTable.getDataSource().sendRequest(
			"sort=playDate&dir=asc&month="+selMonth,
			callback
			);
}
//arrange table
function initHistoryTable() {
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
	    label :"影带编号"
	}, {
		key :"vedioName",
		label :"影带名称"
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
		key :"companyID",
		label :"公司"
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/arrange/getArrangedHistory.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "playDate", "subject", "topic", "dateComing", "companyID", "marked"],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
		}
	};
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=playDate&dir=asc&month="+selMonth, // Initial
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
	
	historyTable.subscribe("initEvent", function() { 
		parent.resizeIframe();
	});
	
	return {
		ds :myDataSource,
		dt :historyTable
	};
}