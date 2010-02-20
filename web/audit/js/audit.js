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
				key : "comments",
				label : "备注"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/audit/filterVideos.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "comments"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};
	requestBuilder = function(oState, oSelf) {
		/*
		 * We aren't initializing sort and dir variables. If you are using
		 * column sorting built into the DataTable, use this set of variable
		 * initializers. var sort, dir, startIndex, results;
		 */
		var startIndex, results, sort, dir;

		oState = oState || {
			pagination : null,
			sortedBy : null
		};
		var filterValue = YAHOO.util.Dom.get("filter").value;
		/*
		 * If using column sorting built into DataTable, these next two lines
		 * will properly set the current _sortedBy_ column and the
		 * _sortDirection_ sort = (oState.sortedBy) ? oState.sortedBy.key :
		 * oSelf.getColumnSet().keys[0].getKey(); dir = (oState.sortedBy &&
		 * oState.sortedBy.dir === DataTable.CLASS_DESC) ? "desc" : "asc";
		 */
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
		initialRequest : "sort=dateInput&dir=asc&startIndex=0&results=25&filter=0",
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

		/*
		 * We don't always want to reset the recordOffset. If we want the
		 * Paginator to be set to the first page, pass in a value of true to
		 * this method. Otherwise, pass in false or anything falsy and the
		 * paginator will remain at the page it was set at before.
		 */
		if (resetRecordOffset) {
			oState.pagination.recordOffset = 0;
		}

		/*
		 * If the column sort direction needs to be updated, that may be done
		 * here. It is beyond the scope of this example, but the
		 * DataTable::sortColumn() method has code that can be used with some
		 * modification.
		 */

		/*
		 * This example uses onDataReturnSetRows because that method will clear
		 * out the old data in the DataTable, making way for the new data.
		 */
		oCallback = {
			success : myDataTable.onDataReturnSetRows,
			failure : myDataTable.onDataReturnSetRows,
			argument : oState,
			scope : myDataTable
		};

		// Generate a query string
		request = myDataTable.get("generateRequest")(oState, myDataTable);

		// Fire off a request for new data.
		myDataSource.sendRequest(request, oCallback);
	}

	// DataTable instance
	var filter = YAHOO.util.Dom.get("filter");
	YAHOO.util.Event.addListener(filter, "change", fireEvent);
	return {
		ds : myDataSource,
		dt : myDataTable
	};

}

function initScoreDataTable(videoID) {	
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
				key : "vedioName",
				label : "剧目名称"
			}, {
				key : "score",
				label : "综合得分",
				sortable : true
			},{
				key : "storyScore",
				label : "故事",
				sortable : true
			}, {
				key : "techScore",
				label : "技术",
				sortable : true
			}, {
				key : "performScore",
				label : "表演",
				sortable : true
			}, {
				key : "innovateScore",
				label : "创新",
				sortable : true
			}, {
				key : "dateExamine",
				label : "评分日期",
				sortable : true
			}, {
				key : "award",
				label : "获奖",
				sortable : true
			}, {
				key : "purchase",
				label : "购买意见",
				sortable : true
			},{
				key:"examiner",
				label:"评分人"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/audit/getVideoScores.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["vedioName", "score", "storyScore", "techScore", "performScore",
				"innovateScore", "dateExamine", "award", "purchase","examiner"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=score&dir=asc&startIndex=0&results=25&videoID="+YAHOO.util.Dom.get("videoID").value,
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "score",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25,
					template :YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions : [ 25, 50, 100 ]
				})

	};
	
	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}

	// DataTable instance

	return {
		ds : myDataSource,
		dt : myDataTable
	};

}