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
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=accuracy&dir=desc", // Initial
		sortedBy : {
			key :"accuracy",
			dir :YAHOO.widget.DataTable.CLASS_DESC
		}, // Sets UI initial sort arrow
		paginator :new YAHOO.widget.Paginator({
			rowsPerPage :25,
			firstPageLinkLabel :"第一页",
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
	var startDate = dojo.widget.byId("startDateStr").getValue(); 
	var endDate = dojo.widget.byId("endDateStr").getValue(); 
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
