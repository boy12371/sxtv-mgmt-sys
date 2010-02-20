function initDataTable() {

	var formatLink = function(elCell, oRecord, oColumn, sData) {
		if (oRecord.getData("status").id != 4 && oRecord.getData("status").id != 3) {
			var href = "<a href='./audit/findVideoByNameOrID?optionName=auditing&vid=";
			href += sData;
			href += "'>" + sData + "</a>";
			
			elCell.innerHTML = href;
		}else{
			elCell.innerHTML = sData;
		}

	}

	var formatCompany = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.companyName;
	}
	var formatTopic = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.topicName;
	}
	var formatSubject = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.subjectName;
	}
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	}
	var formatStatus = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = sData.status;
	}
	// Column definitions
	var myColumnDefs = [{
				key : "id",
				label : "编号",
				sortable : true,
				formatter : formatLink
			}, {
				key : "vedioName",
				label : "剧目名称"
			}, {
				key : "topic",
				label : "题材",
				sortable : true,
				formatter : formatTopic
			}, {
				key : "subject",
				label : "栏目",
				sortable : true,
				formatter : formatSubject
			}, {
				key : "companyID",
				label : "影视公司",
				sortable : true,
				formatter : formatCompany
			}, {
				key : "dateInput",
				label : "收带日期",
				sortable : true
			}, {
				key : "status",
				label : "状态",
				sortable : true,
				formatter : formatStatus
			}, {
				key : "marketShare",
				label : "市场份额",
				sortable : true				
			}, {
				key : "audienceRating",
				label : "收视率",
				sortable : true
			}, {
				key : "comments",
				label : "备注"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/search/");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "marketShare", "audienceRating", "comments"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};
	requestBuilder = function(oState, oSelf) {
		var startIndex, results, sort, dir;
		oState = oState || {
			pagination : null,
			sortedBy : null
		};
		var filterValue = YAHOO.util.Dom.get("filter").value;
		var vid = YAHOO.util.Dom.get("vid").value;
		var vname = YAHOO.util.Dom.get("vname").value;
		var vcompany = YAHOO.util.Dom.get("vcompany").value;
		var vsubject = YAHOO.util.Dom.get("vsubject").value;
		var vtopic = YAHOO.util.Dom.get("vtopic").value;
		var vstatus = YAHOO.util.Dom.get("vstatus").value;
		var startDate = YAHOO.util.Dom.get("startDate").value;
		var endDate = YAHOO.util.Dom.get("endDate").value;
		startIndex = (oState.pagination) ? oState.pagination.recordOffset : 0;
		results = (oState.pagination) ? oState.pagination.rowsPerPage : null;
		sort = (oState.sortedBy)
				? oState.sortedBy.key
				: oSelf.getColumnSet().keys[0].getKey();
		dir = (oState.sortedBy != null && oState.sortedBy.dir == YAHOO.widget.DataTable.CLASS_DESC)
				? "desc"
				: "asc";
		return "&results=" + results + "&startIndex=" + startIndex + "&sort="
				+ sort + "&dir=" + dir + "&filter=" + filterValue;
	}
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=dateInput&dir=asc&startIndex=0&results=25",
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "dateInput",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25,
					template :YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions : [ 25, 50, 100 ]
				}), // Enables pagination
		generateRequest : requestBuilder
	};

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}

	var fireEvent = function(resetRecordOffset) {
		var oState = myDataTable.getState(), request, oCallback;
		if (resetRecordOffset) {
			oState.pagination.recordOffset = 0;
		}
		oCallback = {
			success : myDataTable.onDataReturnSetRows,
			failure : myDataTable.onDataReturnSetRows,
			argument : oState,
			scope : myDataTable
		};
		request = myDataTable.get("generateRequest")(oState, myDataTable);
		myDataSource.sendRequest(request, oCallback);
	}

	// DataTable instance
	var searchBtn = YAHOO.util.Dom.get("filter");
	YAHOO.util.Event.addListener(searchBtn, "click", fireEvent);
	return {
		ds : myDataSource,
		dt : myDataTable
	};

}