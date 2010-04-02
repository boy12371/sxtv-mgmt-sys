function initDataTable() {

	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./search/toVideoDetail.action?vid=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
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
				sortable : true,
				formatter : formatDate
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
				key : "vedioscores",
				label : "综合平均分",
				formatter : formatScroes
			}, {
				key : "purchase",
				label : "购买意见",
				formatter : formatPurchase
			}, {
				key : "awarding",
				label : "获奖备选(是/否)",
				formatter : formatAward
			}, {
				key : "audiencescore",
				label : "观众投票(看/不看)",
				formatter : formatAudienceScore
			}, {
				key : "comments",
				label : "备注"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/search/searchVideos.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "marketShare", "audienceRating",
				"vedioscores", "purchase", "awarding", "audiencescore",
				"comments"],
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

		var vid = YAHOO.util.Dom.get("vid").value;
		var vname = YAHOO.util.Dom.get("vname").value;
		var vcompany = YAHOO.util.Dom.get("vcompany").value;
		var vsubject = YAHOO.util.Dom.get("vsubject").value;
		var vtopic = YAHOO.util.Dom.get("vtopic").value;
		var vstatus = YAHOO.util.Dom.get("vstatus").value;
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();

		startIndex = (oState.pagination) ? oState.pagination.recordOffset : 0;
		results = (oState.pagination) ? oState.pagination.rowsPerPage : null;
		sort = (oState.sortedBy)
				? oState.sortedBy.key
				: oSelf.getColumnSet().keys[0].getKey();
		dir = (oState.sortedBy != null && oState.sortedBy.dir == YAHOO.widget.DataTable.CLASS_DESC)
				? "desc"
				: "asc";
		var queryString = "&results=" + results + "&startIndex=" + startIndex
				+ "&sort=" + sort + "&dir=" + dir;
		queryString += "&sc.id=" + vid + "&sc.name=" + vname
				+ "&sc.company.id=" + vcompany + "&sc.subject.id=" + vsubject
				+ "&sc.topic.id=" + vtopic + "&sc.status.id=" + vstatus
				+ "&sc.startDate="
				+ startDate.substring(0, startDate.indexOf("T"))
				+ "&sc.endDate=" + endDate.substring(0, endDate.indexOf("T"));

		return queryString;
	}
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=dateInput&dir=asc&startIndex=0&results=25",
		initialLoad : false,
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "dateInput",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage : 25,
			firstPageLinkLabel : "第一页",
			lastPageLinkLabel : " 尾页",
			previousPageLinkLabel : " 上一页",
			nextPageLinkLabel : " 下一页",
			template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
			pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
			rowsPerPageOptions : [25, 50, 100]
		}), // Enables pagination
		generateRequest : requestBuilder
	};

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("renderEvent", function() {
				$.unblockUI();
				parent.resizeIframe();
			});

	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataSource.subscribe("requestEvent", function() {
				var path = '${pageContext.request.contextPath}';
				$.blockUI({
							message : "<h1>数据加载中......</h1>"
						});
			});
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
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

		var vid = YAHOO.util.Dom.get("vid").value;
		var vname = YAHOO.util.Dom.get("vname").value;
		var vcompany = YAHOO.util.Dom.get("vcompany").value;
		var vsubject = YAHOO.util.Dom.get("vsubject").value;
		var vtopic = YAHOO.util.Dom.get("vtopic").value;
		var vstatus = YAHOO.util.Dom.get("vstatus").value;
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();
		if (vid.length == 0 && vname.length == 0 && vcompany == 0
				&& vsubject == 0 && vtopic == 0 && vstatus == 0
				&& startDate.length == 0 && endDate.length == 0) {
			jAlert('至少要填写或选择一个选项', '提示');
			return;
		}
		request = myDataTable.get("generateRequest")(oState, myDataTable);
		myDataSource.sendRequest(request, oCallback);

	}
	checkSelectItems = function() {
		var vid = YAHOO.util.Dom.get("vid").value;
		var vname = YAHOO.util.Dom.get("vname").value;
		var vcompany = YAHOO.util.Dom.get("vcompany").value;
		var vsubject = YAHOO.util.Dom.get("vsubject").value;
		var vtopic = YAHOO.util.Dom.get("vtopic").value;
		var vstatus = YAHOO.util.Dom.get("vstatus").value;
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();
		if (vid.length == 0 && vname.length == 0 && vcompany == 0
				&& vsubject == 0 && vtopic == 0 && vstatus == 0
				&& startDate.length == 0 && endDate.length == 0) {
			jAlert('至少要填写或选择一个选项', '提示');
			return "";
		} else {
			var params = "";
			params += "&sc.id=" + vid + "&sc.name=" + vname + "&sc.company.id="
					+ vcompany + "&sc.subject.id=" + vsubject + "&sc.topic.id="
					+ vtopic + "&sc.status.id=" + vstatus + "&sc.startDate="
					+ startDate.substring(0, startDate.indexOf("T"))
					+ "&sc.endDate="
					+ endDate.substring(0, endDate.indexOf("T"));
			return params;
		}
	}
	var generatePrintTable = function(resetRecordOffset) {
		// getDateFromDataTimePicker("fromDate");
		var p = "&startIndex=-1&sort=dateInput&dir=asc";
		var params = checkSelectItems();
		if (params.length != 0) {
			var queryString = "/tv/search/toPrintVideosReport.action?query="
					+ escape(p + params);
		} else {
			return;
		}

		window.open(queryString, "打印预览");
	}

	var printBtn = new YAHOO.widget.Button({
				type : "button",
				id : "topicBtn",
				label : "打印",
				container : "printBtn"
			});
	printBtn.on("click", generatePrintTable);

	var searchBtn = new YAHOO.widget.Button("go");// YAHOO.util.Dom.get("filter");
	searchBtn.on("click", fireEvent);

	var vcompany = YAHOO.util.Dom.get("vcompany");
	vcompany.insertBefore(new Option("请选择", 0), vcompany.options[0]);
	vcompany.selectedIndex = 0;

	var vsubject = YAHOO.util.Dom.get("vsubject");
	vsubject.insertBefore(new Option("请选择", 0), vsubject.options[0]);
	vsubject.selectedIndex = 0;

	var vtopic = YAHOO.util.Dom.get("vtopic");
	vtopic.insertBefore(new Option("请选择", 0), vtopic.options[0]);
	vtopic.selectedIndex = 0;

	var vstatus = YAHOO.util.Dom.get("vstatus");
	vstatus.insertBefore(new Option("请选择", 0), vstatus.options[0]);
	vstatus.selectedIndex = 0;

	return {
		ds : myDataSource,
		dt : myDataTable
	};

}

function initOrderDataTable() {
	var rowIndex = 0;
	var formatOrder = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = (rowIndex += 1);
	}
	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./search/toVideoDetail.action?vid=";
		href += sData;
		href += "' target='blank'>" + sData + "</a>";
		elCell.innerHTML = href;
	}

	// Column definitions
	var myColumnDefs = [{
				key : "order",
				label : "排名",
				formatter : formatOrder
			}, {
				key : "id",
				label : "编号",
				formatter : formatLink
			}, {
				key : "vedioName",
				label : "剧目名称"
			}, {
				key : "topic",
				label : "题材",
				formatter : formatTopic
			}, {
				key : "subject",
				label : "栏目",
				formatter : formatSubject
			}, {
				key : "companyID",
				label : "影视公司",
				formatter : formatCompany
			}, {
				key : "dateInput",
				label : "收带日期",
				formatter : formatDate
			}, {
				key : "status",
				label : "状态",
				formatter : formatStatus
			}, {
				key : "marketShare",
				label : "市场份额"
			}, {
				key : "audienceRating",
				label : "收视率"
			}, {
				key : "vedioscores",
				label : "综合平均分",
				formatter : formatScroes
			}, {
				key : "purchase",
				label : "购买意见",
				formatter : formatPurchase
			}, {
				key : "awarding",
				label : "获奖备选(是/否)",
				formatter : formatAward
			}, {
				key : "audiencescore",
				label : "观众投票(看/不看)",
				formatter : formatAudienceScore
			}, {
				key : "comments",
				label : "备注"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/search/doSearchAndSequenceVideos.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["order", "id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "marketShare", "audienceRating",
				"vedioscores", "purchase", "awarding", "audiencescore",
				"comments"],
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

		var vcompany = YAHOO.util.Dom.get("vcompany").value;
		var vsubject = YAHOO.util.Dom.get("vsubject").value;
		var vtopic = YAHOO.util.Dom.get("vtopic").value;
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();

		startIndex = (oState.pagination) ? oState.pagination.recordOffset : -1;
		// results = (oState.pagination) ? oState.pagination.rowsPerPage : null;
		sort = (oState.sortedBy)
				? oState.sortedBy.key
				: oSelf.getColumnSet().keys[0].getKey();
		dir = (oState.sortedBy != null && oState.sortedBy.dir == YAHOO.widget.DataTable.CLASS_DESC)
				? "desc"
				: "asc";
		var queryString = "&startIndex=" + startIndex + "&sort=" + sort
				+ "&dir=" + dir;
		queryString += "&sc.company.id=" + vcompany + "&sc.subject.id="
				+ vsubject + "&sc.topic.id=" + vtopic
				+ "&sc.status.id=9&sc.startDate="
				+ startDate.substring(0, startDate.indexOf("T"))
				+ "&sc.endDate=" + endDate.substring(0, endDate.indexOf("T"));

		return queryString;
	}
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=audienceRating&dir=asc&startIndex=-1&results=0",
		initialLoad : false,
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "audienceRating",
			dir : YAHOO.widget.DataTable.CLASS_DESC
		},
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25
				}),
		generateRequest : requestBuilder
	};

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("renderEvent", function() {
				rowIndex = 0;
				$.unblockUI();
				parent.resizeIframe();
			});

	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataSource.subscribe("requestEvent", function() {
				var path = '${pageContext.request.contextPath}';
				$.blockUI({
							message : "<h1>数据加载中......</h1>"
						});
			});
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

//	var colDiv = YAHOO.util.Dom.get("colDiv");
//
//	addColumns = function() {
//
//		for (var i = 0, l = myColumnDefs.length; i < l; i++) {
//			var column = myColumnDefs[i];
//			var checkbox = document.createElement("INPUT");
//			checkbox.type = "checkbox";
//			checkbox.name = "colCkbox";
//			checkbox.value = column.key;
//			checkbox.checked = false;
//			checkbox.addListener("click",function(){
//				alert(this.checked);
//			});
//			// checkbox.appendChild(column.label);
//			colDiv.appendChild(checkbox);
//			
//			var label = document.createElement("LABEL");
//			label.innerHTML= column.label;
//			colDiv.appendChild(label);
//		}
//	};

	var fireEvent = function(resetRecordOffset) {
		//addColumns();
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

		var vcompany = YAHOO.util.Dom.get("vcompany").value;
		var vsubject = YAHOO.util.Dom.get("vsubject").value;
		var vtopic = YAHOO.util.Dom.get("vtopic").value;
		// var vstatus = YAHOO.util.Dom.get("vstatus").value;
		var startDate = dojo.widget.byId("startDate").getValue();
		var endDate = dojo.widget.byId("endDate").getValue();
		if (vcompany == 0 && vsubject == 0 && vtopic == 0
				&& startDate.length == 0 && endDate.length == 0) {
			jAlert('至少要填写或选择一个选项', '提示');
			// alert("至少要填写或选择一个选项");
			return;
		}
		request = myDataTable.get("generateRequest")(oState, myDataTable);
		myDataSource.sendRequest(request, oCallback);

	}

	var searchBtn = new YAHOO.widget.Button("go");// YAHOO.util.Dom.get("filter");
	searchBtn.on("click", fireEvent);

	var vcompany = YAHOO.util.Dom.get("vcompany");
	vcompany.insertBefore(new Option("请选择", 0), vcompany.options[0]);
	vcompany.selectedIndex = 0;

	var vsubject = YAHOO.util.Dom.get("vsubject");
	vsubject.insertBefore(new Option("请选择", 0), vsubject.options[0]);
	vsubject.selectedIndex = 0;

	var vtopic = YAHOO.util.Dom.get("vtopic");
	vtopic.insertBefore(new Option("请选择", 0), vtopic.options[0]);
	vtopic.selectedIndex = 0;

	return {
		ds : myDataSource,
		dt : myDataTable
	};

}
