var myDataTable;
function initAccuracyTable() {	
	var formatNum = function(elCell, oRecord, oColumn, sData) {
		if (sData == -1) {
			elCell.innerHTML = "无评分影带";
		} else {
			elCell.innerHTML = sData + "";
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
	
	var requestBuilder = function (oState, oSelf) {
		var sort, dir, startIndex, results; 
		var startIndex, results;
		oState = oState || {pagination: null, sortedBy: null};
		sort = (oState.sortedBy) ? oState.sortedBy.key : oSelf.getColumnSet().keys[0].getKey();
		dir = (oState.sortedBy && oState.sortedBy.dir === myDataTable.CLASS_DESC) ? "desc" : "asc"; 
		startIndex = (oState.pagination) ? oState.pagination.recordOffset : 0;
		results = (oState.pagination) ? oState.pagination.rowsPerPage : null;
		
		var selYear = YAHOO.util.Dom.get("selectYear").value;
		var selMonth = YAHOO.util.Dom.get("selectMonth").value;
		var selDate = selYear + "-" + selMonth;
		return "&results=" + results + "&startIndex=" + startIndex + "&sort="
		+ sort + "&dir=" + dir + "&selDate=" + selDate;
	};
	// DataTable configuration
	var selYear = YAHOO.util.Dom.get("selectYear").value;
	var selMonth = YAHOO.util.Dom.get("selectMonth").value;
	var selDate = selYear + "-" + selMonth;
	var myConfigs = {
		initialRequest :"sort=accuracy&dir=desc&selDate=" + selDate, // Initial
		generateRequest: requestBuilder,
		sortedBy : {
			key :"accuracy",
			dir :YAHOO.widget.DataTable.CLASS_DESC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator({
			rowsPerPage :25,
			firstPageLinkLabel :"首页",
			lastPageLinkLabel :" 尾页",
			previousPageLinkLabel :" 上一页",
			nextPageLinkLabel :" 下一页",
			template :"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
			pageReportTemplate :"Showing items {startIndex} - {endIndex} of {totalRecords}",
			rowsPerPageOptions : [25, 50,100 ]
		})
	};
	// DataTable instance
	myDataTable = new YAHOO.widget.DataTable("accuracyTableDiv", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse, oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataTable.subscribe("renderEvent", function() {
		$.unblockUI();
		parent.resizeIframe();
	});
	myDataSource.subscribe("requestEvent", function() { 
		$.blockUI({ message: "<h1>数据加载中......</h1>" });
	});
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}

function filterFunc(){
	var oState = myDataTable.getState();
	oState.pagination.recordOffset = 0;
	var callback = {
			success:myDataTable.onDataReturnSetRows,
			failure:myDataTable.onDataReturnSetRows,
			argument:oState,
			scope:myDataTable
			};
	myDataTable.getDataSource().sendRequest(
			myDataTable.get("generateRequest")(myDataTable.getState(), myDataTable),
			callback
			);
}
