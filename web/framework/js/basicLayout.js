var layout = null;

function initPageElements() {
	initLayout();
	initMenuBar();
	initDataTable();
	autoCompleteVideoName();
}

function beforeSearchSubmit(form){
	//var sform = YAHOO.util.Dom.get("searchForm");
	var iframe = YAHOO.util.Dom.get("mainContentFrame");
	iframe.style.display = "";
	var queryDiv = YAHOO.util.Dom.get("queryDiv");
	queryDiv.style.display = "none";
	form.target="mainContentFrame";
	form.submit();
}
function initLayout() {
	layout = new YAHOO.widget.Layout({
		units : [ {
			position : 'top',
			height : 28,
			body : 'top1',
			scroll : null,
			zIndex : 2
		}, {
			position : 'right',
			header : '查询',
			width : 310,
			resize : true,
			collapse : true,
			footer : 'Footer',
			scroll : true,
			body : 'right1',
			animate : true,
			gutter : '5'
		}, {
			position : 'bottom',
			height : 30,
			body : 'bottom1'
		}, {
			position : 'center',
			body : 'center1',
			gutter : '5 0',
			height : 0,
			width : 0
		} ]
	});
	layout.render();

}

function initMenuBar() {

	var oMenuBar = new YAHOO.widget.MenuBar("productsandservices", {
		autosubmenudisplay : false,
		hidedelay : 750,
		lazyload : true,
		effect : {
			effect : YAHOO.widget.ContainerEffect.FADE,
			duration : 0.25
		}
	});

	oMenuBar.render();
}
function initDataTable() {
	var formatLink = function(elCell, oRecord, oColumn, sData) {
		elCell.innerHTML = "<a href='#'>" + sData + "</a>";
	}

	// Column definitions
	var myColumnDefs = [ {
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
		key : "dateStore",
		label : "入库日期",
		sortable : true,
		formatter : function(elCell, oRecord, oColumn, sData) {
			if (sData != null) {
				var idx = sData.indexOf("T");
				if (idx != -1) {
					elCell.innerHTML = sData.substring(0, idx);
				} else {
					elCell.innerHTML = sData;
				}
			} else {
				elCell.innerHTML = "-";
			}

		}
	}, {
		key : "playDate",
		label : "播出日期",
		sortable : true,
		formatter : function(elCell, oRecord, oColumn, sData) {
			if (sData != null) {
				var idx = sData.indexOf("T");
				if (idx != -1) {
					elCell.innerHTML = sData.substring(0, idx);
				} else {
					elCell.innerHTML = sData;
				}
			} else {
				elCell.innerHTML = "-";
			}

		}
	}, {
		key : "marketShare",
		label : "市场份额",
		sortable : true
	}, {
		key : "audienceRating",
		label : "收视率",
		sortable : true
	}, {
		key : "avgScore",
		label : "综合平均分"// ,formatter : formatScroes
	}, {
		key : "purchase",
		label : "购买意见"
	// ,formatter : formatPurchase
	}, {
		key : "award",
		label : "获奖备选"// , formatter : formatAward
	}, {
		key : "audiScore",
		label : "观众投票"// , formatter : formatAudienceScore
	}, {
		key : "comments",
		label : "备注",
		formatter : formatorComments
	} ];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource(
			"/tv/search/searchVideos.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : [ "id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "dateStore", "playDate", "marketShare",
				"audienceRating", "avgScore", "purchase", "award", "audiScore",
				"comments" ],
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
		sort = (oState.sortedBy) ? oState.sortedBy.key
				: oSelf.getColumnSet().keys[0].getKey();
		dir = (oState.sortedBy != null && oState.sortedBy.dir == YAHOO.widget.DataTable.CLASS_DESC) ? "desc"
				: "asc";
		var queryString = "&results=" + results + "&startIndex=" + startIndex
				+ "&sort=" + sort + "&dir=" + dir;
		queryString += "&sc.id=" + vid + "&sc.name=" + vname
				+ "&sc.company.id=" + vcompany + "&sc.subject.id=" + vsubject
				+ "&sc.topic.id=" + vtopic + "&sc.status.id=" + vstatus
				+ "&sc.startDate="
				+ startDate.substring(0, startDate.indexOf("T"))
				+ "&sc.endDate=" + endDate.substring(0, endDate.indexOf("T"));

		return encodeURI(encodeURI(queryString));
	}
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=dateInput&dir=asc&startIndex=0&results=20",
		initialLoad : false,
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "dateInput",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort
		// arrow
		paginator : new YAHOO.widget.Paginator(
				{
					rowsPerPage : 20,
					firstPageLinkLabel : "首页",
					lastPageLinkLabel : " 尾页",
					previousPageLinkLabel : " 上一页",
					nextPageLinkLabel : " 下一页",
					template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}",
					pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}"
				}), // Enables
		// pagination
		generateRequest : requestBuilder
	};

	myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);

	var linkClickEvent = function(oArgs) {
		var a = oArgs.target;
		var oRecord = this.getRecord(a);
		var link = "/tv/search/toVideoDetail.action?vid="
				+ oRecord.getData("id");
		window
				.open(
						link,
						"影带信息",
						'height=500,width=400,top=0,left=0,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
	}
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

	myDataTable.subscribe("linkClickEvent", linkClickEvent);
	var columnSet = myDataTable.getColumnSet();
	var showHideColumn = function(e) {
		var column = columnSet.getColumn(this.value);
		if (this.checked) {
			myDataTable.hideColumn(column);
		} else {
			myDataTable.showColumn(column);
		}
	}

	var colLink = new YAHOO.widget.Button({
		type : "button",
		id : "colLink",
		label : "选项",
		container : "tableOption"
	});
	var colDiv = YAHOO.util.Dom.get("colDiv");
	colLink.on("click", function() {
		colDiv.style.display = colDiv.style.display == "block" ? "none"
				: "block";
	});
	var addColumnsName = function() {
		if (colDiv.innerHTML.length == 0) {
			for ( var i = 0; i < myColumnDefs.length; i++) {
				var column = myColumnDefs[i];
				var checkbox = document.createElement("INPUT");
				checkbox.type = "checkbox";
				checkbox.name = "colCkbox";
				checkbox.value = column.key;
				if (column.key == "avgScore" || column.key == "purchase"
						|| column.key == "award" || column.key == "audiScore"
						|| column.key == "comments") {
					checkbox.checked = true;
				} else {
					checkbox.checked = false;
				}
				colDiv.appendChild(checkbox);
				var p = document.createElement("SPAN");
				p.innerHTML = column.label;
				colDiv.appendChild(p);
				if (i % 2 == 1) {
					var br = document.createElement("BR");
					colDiv.appendChild(br);
				}

				YAHOO.util.Event.addListener(checkbox, "click", showHideColumn);
				colDiv.style.display = "none";
			}
		}
	};
	var initHideCols = function() {
		var colNames = [ "avgScore", "purchase", "award", "audiScore",
				"comments" ];
		for ( var i = 0; i < colNames.length; i++) {
			myDataTable.hideColumn(columnSet.getColumn(colNames[i]));
		}
	}
	var fireEvent = function(resetRecordOffset) {
		var iframe = YAHOO.util.Dom.get("mainContentFrame");
		iframe.style.display = "none";
		var queryDiv = YAHOO.util.Dom.get("queryDiv");
		queryDiv.style.display = "block";

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
		if (vid.length == 0 && vname.length == 0 && vcompany == -1
				&& vsubject == -1 && vtopic == -1 && vstatus == -1
				&& startDate.length == 0 && endDate.length == 0) {
			jAlert('至少要填写或选择一个选项', '提示');
			return;
		}
		request = myDataTable.get("generateRequest")(oState, myDataTable);
		myDataSource.sendRequest(request, oCallback);
		layout.getUnitByPosition('right').toggle();
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
			params += "&sc.id=" + vid + "&sc.company.id=" + vcompany
					+ "&sc.subject.id=" + vsubject + "&sc.topic.id=" + vtopic
					+ "&sc.status.id=" + vstatus + "&sc.startDate="
					+ startDate.substring(0, startDate.indexOf("T"))
					+ "&sc.endDate="
					+ endDate.substring(0, endDate.indexOf("T")) + "&sc.name="
					+ encodeURI(vname);
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

	var queryBtn = new YAHOO.widget.Button({
		type : "button",
		label : "查询",
		id : "queryBtn",
		container : "queryBtnDiv"
	});
	queryBtn.on("click", fireEvent);

	// fixTableWidthWithScrollBar("dynamicdata");
	addColumnsName();
	initHideCols();
	return {
		ds : myDataSource,
		dt : myDataTable
	};

}
function logout() {
	window.location = "/tv/logon/doLogout.action";
}
function refreshIframe(link) {
	var iframe = YAHOO.util.Dom.get("mainContentFrame");
	iframe.style.display = "";
	var queryDiv = YAHOO.util.Dom.get("queryDiv");
	queryDiv.style.display = "none";
	layout.getUnitByPosition('right').collapse();
	iframe.src = link;

	return false;
}