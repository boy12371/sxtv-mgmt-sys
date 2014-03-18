var myDataTable;
function initAccuracyTable() {
	var formatNum = function(elCell, oRecord, oColumn, sData) {
		if (sData == -1) {
			elCell.innerHTML = "无评分影带";
		} else {
			var _val = sData.toString();
			elCell.innerHTML = (_val.length > 5) ? _val.substring(0,5) : _val;
		}
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key : "employeeName",
		label : "姓名",
		sortable : true
	}, {
		key : "userName",
		label : "用户名",
		sortable : true
	}, {
		key : "accuracy",
		label : "准确度(%)",
		formatter : formatNum,
		sortable : false
	} ];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource(
			"/tv/accuracy/getAccuracy.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : [ "employeeName", "userName", "accuracy" ],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
		}
	};

	var requestBuilder = function(oState, oSelf) {
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();
		if (startDate.length == 0 || endDate.length == 0) {
			alert("Please select date");
			return;
		}
		startDate = startDate.substring(0, startDate.indexOf("T"));
		endDate = endDate.substring(0, endDate.indexOf("T"));
		return "&results=100&startIndex=0&sort=accuracy&dir=desc&startDate="
				+ startDate + "&endDate=" + endDate;
	};
	// DataTable configuration
	var myConfigs = {
		initialLoad : false,
		initialRequest : "sort=accuracy&dir=desc", // Initial
		generateRequest : requestBuilder,
		sortedBy : {
			key : "accuracy",
			dir : YAHOO.widget.DataTable.CLASS_DESC
		}, // Sets UI initial sort arrow
		dynamicData : true
	// ,
	// paginator :new YAHOO.widget.Paginator({
	// rowsPerPage :25,
	// firstPageLinkLabel :"首页",
	// lastPageLinkLabel :" 尾页",
	// previousPageLinkLabel :" 上一页",
	// nextPageLinkLabel :" 下一页",
	// template
	// :"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
	// pageReportTemplate :"Showing items {startIndex} - {endIndex} of
	// {totalRecords}",
	// rowsPerPageOptions : [25, 50,100 ]}
	// )
	};
	// DataTable instance
	myDataTable = new YAHOO.widget.DataTable("accuracyTableDiv", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	};
	myDataTable.subscribe("renderEvent", function() {
		$.unblockUI();
		parent.resizeIframe();
		// var rs = myDataTable.getRecordSet();
		// alert(rs);
	});
	myDataSource.subscribe("requestEvent", function() {
		$.blockUI({
			message : "<h1>数据加载中......</h1>"
		});
	});
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

	var fireEvent = function(resetRecordOffset) {
		// var selYear = YAHOO.util.Dom.get("selectYear").value;
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();
		if (startDate.length == 0 || endDate.length == 0) {
			alert("Please select date");
			return;
		}
		startDate = startDate.substring(0, startDate.indexOf("T"));
		endDate = endDate.substring(0, endDate.indexOf("T"));

		var oState = myDataTable.getState();
		//oState.pagination.recordOffset = 0;
		var callback = {
			success : myDataTable.onDataReturnSetRows,
			failure : myDataTable.onDataReturnSetRows,
			argument : oState,
			scope : myDataTable
		};
		request = myDataTable.get("generateRequest")(oState, myDataTable);
		myDataTable.getDataSource().sendRequest(request, callback);
	};

	var searchBtn = new YAHOO.widget.Button({
		label : "查&nbsp;&nbsp;询",
		id : "searchBtn",
		container : "searchBtnDiv"
	});

	searchBtn.on("click", fireEvent);

	var printFun = function() {
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();
		if (startDate.length == 0 || endDate.length == 0) {
			alert("Please select date");
			return;
		}
		startDate = startDate.substring(0, startDate.indexOf("T"));
		endDate = endDate.substring(0, endDate.indexOf("T"));
		var url = "/tv/accuracy/toAccuracyPrint.action?startDate=" + startDate
				+ "&endDate=" + endDate;
		window.open(url, "打印预览");
	}
	var printBtn = new YAHOO.widget.Button({
		label : "打&nbsp;&nbsp;印",
		id : "printBtn",
		container : "printDiv"
	});
	printBtn.on("click", printFun);

	return {
		ds : myDataSource,
		dt : myDataTable
	};
}

function filterFunc() {
	// var selYear = YAHOO.util.Dom.get("selectYear").value;
	var startDate = dojo.widget.byId("startDate").getValue();
	var endDate = dojo.widget.byId("endDate").getValue();
	if (startDate.length == 0 || endDate.length == 0) {
		alert("Please select date");
		return;
	}
	startDate = startDate.substring(0, startDate.indexOf("T"));
	endDate = endDate.substring(0, endDate.indexOf("T"));

	var oState = myDataTable.getState();
	oState.pagination.recordOffset = 0;
	var callback = {
		success : myDataTable.onDataReturnSetRows,
		failure : myDataTable.onDataReturnSetRows,
		argument : oState,
		scope : myDataTable
	};
	request = myDataTable.get("generateRequest")(oState, myDataTable);
	myDataTable.getDataSource().sendRequest(request, callback);
}
