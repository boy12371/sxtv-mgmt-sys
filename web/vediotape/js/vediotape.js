var isBuild = false;
var companies = [];
var topices = [];
var subjects = [];
function buildArray(array, selectId) {
	var obj = YAHOO.util.Dom.get(selectId);
	var ops = obj.options;
	for ( var i = 0; i < ops.length; i++) {
		array.push( {
			label : ops[i].innerHTML,
			value : ops[i].value
		});
	}
}
function buildCompaniesDataSource(ods) {
	var obj = YAHOO.util.Dom.get("vcompany");
	var ops = obj.options;
	for ( var i = 0; i < ops.length; i++) {
		ods.push( {
			comName : ops[i].innerHTML,
			comID : ops[i].value
		});
	}
}
function initDataTable() {
	if (isBuild == false) {
		buildArray(companies, "vcompany");
		buildArray(topices, "vtopic");
		buildArray(subjects, "vsubject");
		isBuild = true;
	}
	var formatorRemarks = function(elCell, oRecord, oColumn, oData) {
		if (oData.length > 35) {
			elCell.innerHTML = oData.substring(0, 10) + ".....";
			elCell.title = oData;
		} else {
			elCell.innerHTML = oData;
		}
	}
	var formatCompany = function(elCell, oRecord, oColumn, oData) {
		if (typeof (oData) != "object") {
			var _id = parseInt(oData);
			for ( var i = 0; i < companies.length; i++) {
				if (_id == companies[i].value) {
					oData = {
						id : companies[i].value,
						companyName : companies[i].label
					}
				}
			}
		}
		elCell.innerHTML = oData["companyName"];
	}
	var formatTopic = function(elCell, oRecord, oColumn, oData) {

		if (typeof (oData) != "object") {
			var _id = parseInt(oData);
			for ( var i = 0; i < topices.length; i++) {
				if (_id == topices[i].value) {
					oData = {
						id : topices[i].value,
						topic : topices[i].label
					}
				}
			}
		}

		elCell.innerHTML = oData["topic"];
		// elCell.innerHTML = oData;
	}
	var formatSubject = function(elCell, oRecord, oColumn, oData) {
		if (typeof (oData) != "object") {
			var _id = parseInt(oData);
			for ( var i = 0; i < subjects.length; i++) {
				if (_id == subjects[i].value) {
					oData = {
						id : subjects[i].value,
						subject : subjects[i].label
					}
				}
			}
		}
		elCell.innerHTML = oData["subject"];
	}
	// var subjectSel = ;
	var myColumnDefs = [ {
		key : "vid",
		label : "编号",
		sortable : true
	}, {
		key : "vname",
		label : "剧目名称",
		editor : new YAHOO.widget.TextboxCellEditor( {
			disableBtns : true
		})
	}, {
		key : "vcompany",
		label : "影视公司",
		sortable : true,
		formatter : formatCompany,
		editor : new YAHOO.widget.DropdownCellEditor( {
			dropdownOptions : companies,
			disableBtns : true
		})
	}, {
		key : "vtopic",
		label : "题材",
		sortable : true,
		formatter : formatTopic,
		editor : new YAHOO.widget.DropdownCellEditor( {
			dropdownOptions : topices,
			disableBtns : true
		})
	}, {
		key : "vsubject",
		label : "栏目",
		sortable : true,
		formatter : formatSubject,
		editor : new YAHOO.widget.DropdownCellEditor( {
			dropdownOptions : subjects,
			disableBtns : true
		})
	}, {
		key : "vcomments",
		label : "备注",
		formatter : formatorRemarks,
		editor : new YAHOO.widget.TextareaCellEditor( {
			disableBtns : false
		})
	} ];

	var myDataSource = new YAHOO.util.DataSource( []);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields : [ "vid", "vname", "vcompany", "vtopic", "vsubject",
				"vcomments" ]
	};

	var myDataTable = new YAHOO.widget.DataTable("cellediting", myColumnDefs,
			myDataSource, {});

	// Set up editing flow
	var highlightEditableCell = function(oArgs) {
		var elCell = oArgs.target;
		if (YAHOO.util.Dom.hasClass(elCell, "yui-dt-editable")) {
			this.highlightCell(elCell);
		}
	};
	myDataTable.subscribe("cellMouseoverEvent", highlightEditableCell);
	myDataTable.subscribe("cellMouseoutEvent",
			myDataTable.onEventUnhighlightCell);
	myDataTable.subscribe("cellClickEvent", myDataTable.onEventShowCellEditor);
	myDataTable.subscribe("rowAddEvent", function(record) {
		parent.resizeIframe();
	});
	getData = function() {
		var _vid = YAHOO.util.Dom.get("vid").value;
		var _vname = YAHOO.util.Dom.get("vname").value;
		var _vcompany = YAHOO.util.Dom.get("comName");
		// var _vc = {
		// id :_vcompany.options[_vcompany.selectedIndex].value,
		// companyName :_vcompany.options[_vcompany.selectedIndex].text
		// };
		var _vc = null;
		var comExsits = false;
		for ( var i = 0; i < coms.length; i++) {
			if (_vcompany.value == coms[i].comName) {
				_vc = {
					id : coms[i].comID,
					companyName : coms[i].comName
				}
				comExsits = true;
				break;
			}
		}
		if (comExsits == false) {
			jAlert("公司名称有误！", '提示');
			return;
		}
		var _vtopic = YAHOO.util.Dom.get("vtopic");
		var _vt = {
			id : _vtopic.options[_vtopic.selectedIndex].value,
			topic : _vtopic.options[_vtopic.selectedIndex].text
		};
		var _vsubject = YAHOO.util.Dom.get("vsubject");
		var _vs = {
			id : _vsubject.options[_vsubject.selectedIndex].value,
			subject : _vsubject.options[_vsubject.selectedIndex].text
		};

		var _vcomments = YAHOO.util.Dom.get("vcomments").value;
		return {
			vid : _vid,
			vname : _vname,
			vcompany : _vc,
			vtopic : _vt,
			vsubject : _vs,
			vcomments : _vcomments
		};

	};

	isVedioNameValid = function() {
		var callbacks = {
			success : function(o) {
				YAHOO.log("RAW JSON DATA: " + o.responseText);
				// Process the JSON data returned from the server
			var obj = o.responseText;
			if (obj.indexOf("SUCCESS") != -1) {
				myDataTable.addRow(getData(), 0);
			} else {
				jAlert(obj, '提示');
				return;
			}
		},
		failure : function(o) {
			if (!YAHOO.util.Connect.isCallInProgress(o)) {
				jAlert('Async call failed!', '提示');
			}
		},
		timeout : 3000
		}

		var videoName = YAHOO.util.Dom.get("vname").value;
		var videoId = YAHOO.util.Dom.get("vid").value;
		if (videoId.length == 0 || videoName.length == 0) {
			jAlert("信息不完整", '错误');
			return false;
		}

		var url = encodeURI("/tv/vedio/isVediotapeExsits.action?vedioName="
				+ videoName);
		YAHOO.util.Connect.asyncRequest('GET', encodeURI(url), callbacks);
	}

	var handleClick = function() {
		var dataSet = myDataTable.getRecordSet().getRecords();
		if (dataSet.length == 0) {
			isVedioNameValid();
		} else {
			var videoName = YAHOO.util.Dom.get("vname").value;
			for ( var i = 0; i < dataSet.length; i++) {
				var record = dataSet[i];
				if (record.getData("vname") == videoName) {
					jAlert('列表中已有此剧目，请检查剧目名称。', '错误');
					return;
				}
			}
			isVedioNameValid();
		}
	}

	var btn = new YAHOO.widget.Button("go");
	btn.on("click", handleClick);

	getRealData = function(dataObj, fieldName) {
		var realValue = "";
		if (typeof dataObj != "object") {
			realValue = dataObj;
		} else {
			realValue = dataObj.id;
		}
		return realValue;
	}
	var handleSubmit = function() {

		var records = myDataTable.getRecordSet().getRecords();
		var len = records.length;
		if (len != 0) {
			var data = YAHOO.util.Dom.get("jasonDataString");

			var form = document.forms[0];
			var jasonString = "[";
			for ( var i = 0; i < len; i++) {
				var oData = records[i];
				if (i != 0 && i < len) {
					jasonString += ",";
				}
				var vc = getRealData(oData.getData("vcompany"), "company");
				var vt = getRealData(oData.getData("vtopic"), "topic");
				var vs = getRealData(oData.getData("vsubject"), "subject");

				jasonString += "{id:\"" + oData.getData("vid")
						+ "\",vedioName:\"" + oData.getData("vname")
						+ "\",companyID:" + vc + ",topic:" + vt + ",subject:"
						+ vs + ",comments:\"" + oData.getData("vcomments")
						+ "\"}";
			}
			jasonString += "]";
			data.value = jasonString;
			form.submit();
		} else {
			jAlert('尚未添加任何影带信息', "提示");
			return;
		}

	}
	var submitBtn = new YAHOO.widget.Button("submit");
	submitBtn.on("click", handleSubmit);

	var onContextMenuClick = function(p_sType, p_aArgs, p_myDataTable) {
		var task = p_aArgs[1];
		if (task) {
			// Extract which TR element triggered the context menu
			var elRow = this.contextEventTarget;
			elRow = p_myDataTable.getTrEl(elRow);

			if (elRow) {
				switch (task.index) {
				case 0: // Delete row upon confirmation
					var oRecord = p_myDataTable.getRecord(elRow);
					jConfirm("您却定要删除影带 " + oRecord.getData("vid") + " ("
							+ oRecord.getData("vname") + ")?", "提示",
							function(r) {
								if (r) {
									p_myDataTable.deleteRow(elRow);
								}
							})
				}
			}
		}
	};

	var myContextMenu = new YAHOO.widget.ContextMenu("mycontextmenu", {
		trigger : myDataTable.getTbodyEl()
	});
	myContextMenu.addItem("删除");
	// Render the ContextMenu instance to the parent container of the DataTable
	myContextMenu.render("cellediting");
	myContextMenu.clickEvent.subscribe(onContextMenuClick, myDataTable);
	return {
		oDS : myDataSource,
		oDT : myDataTable
	};
}

var toArrangeTableCallBack = {
	success : function(o) {
		// Process the JSON data returned from the server
	var records = "";
	try {
		records = YAHOO.lang.JSON.parse(o.responseText);
		initToArrangeTable(records);
	} catch (x) {
		alert("JSON Parse failed!");
		return;
	}
},

failure : function(o) {
	if (!YAHOO.util.Connect.isCallInProgress(o)) {
		alert("Async call failed!");
	}
},
timeout : 3000

};

function initArrangeTableDataSource(url, callBack) {
	YAHOO.util.Connect.asyncRequest('GET', url, callBack);
}

function initToArrangeTable(ds) {

	var OnHeaderCheckboxClicked = function() {
		var myHeaderCheckbox = YAHOO.util.Dom.get("Aheader_checkbox");
		var headerChecked = myHeaderCheckbox.checked;
		var myDataRecords = myDataTable.getRecordSet();
		var checkBoxIds2Check = new Array();
		for ( var i = 0; i < myDataRecords.getLength(); i++) {
			checkBoxIds2Check.push(myDataRecords.getRecord(i).getData("id"));
		}

		var id2Check;
		for (id in checkBoxIds2Check) {
			id2Check = document.getElementById("row_" + checkBoxIds2Check[id]);
			id2Check.checked = headerChecked;
		}

	}
	// Helps in drawing the checkbox for all rows !
	var FormatCheckboxCell = function(elCell, oRecord, oColumn, oData) {
		// Create checkbox
		var checkboxId = 'row_' + oRecord.getData()["id"];
		var checkbox = YAHOO.util.Dom.get(checkboxId);
		if (checkbox == null) {
			checkbox = document.createElement('input');
			checkbox.setAttribute('type', 'checkbox');
			checkbox.setAttribute('id', checkboxId);
			checkbox.setAttribute('class',
					YAHOO.widget.DataTable.CLASS_CHECKBOX);
			checkbox.setAttribute('name', 'toApproved');
			checkbox.setAttribute('value', oRecord.getData()["id"]);
			elCell.innerHTML = "";
			elCell.appendChild(checkbox);
		}
	}
	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='/tv/vedio/searchVideoByNameOrIDForModification.action?optionName=modification&vid=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	}

	// Column definitions
	var myColumnDefs = [
			{
				key : "checkbox",
				label : "<input id='Aheader_checkbox' class='yui-dt-checkbox' type='checkbox'>",
				sortable : false,
				resizeable : false,
				formatter : FormatCheckboxCell
			}, {
				key : "id",
				label : "编号",
				sortable : true,
				formatter : formatLink
			}, {
				key : "vedioName",
				label : "剧目名称",
				sortable : true
			}, {
				key : "topic",
				label : "题材",
				sortable : true
//				formatter : formatTopic,
//				sortOptions : {
//					sortFunction : sortTopic
//				}
			}, {
				key : "subject",
				label : "栏目",
				sortable : true
//				formatter : formatSubject,
//				sortOptions : {
//					sortFunction : sortSubject
//				}
			}, {
				key : "companyID",
				label : "影视公司",
				sortable : true
//				formatter : formatCompany,
//				sortOptions : {
//					sortFunction : sortCompany
//				}
			}, {
				key : "dateComing",
				label : "收带日期",
				sortable : true,
				formatter : formatDate
//			}, {
//				key : "status",
//				label : "状态",
//				formatter : formatStatus
			}, {
				key : "avgScore",
				label : "综合平均分",
//				formatter : formatScroes,
				sortable : true
//				sortOptions : {
//					sortFunction : sortScores
//				}
			}, {
				key : "audiScore",
				label : "观众投票(看/不看)"
//				formatter : formatAudienceScore
			}, {
				key : "award",
				label : "获奖备选(是/否)"
//				formatter : formatAward
			}, {
				key : "comments",
				label : "备注",
				formatter : formatorComments
			} ];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource(ds);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : [ "id", "vedioName", "topic", "subject", "companyID",
				"dateComing", "status", "avgScore", "award",
				"audiScore", "comments" ],
		metaFields : {
			totalRecords : "totalRecords"
		}
	};

	// DataTable configuration
	var myConfigs = {
		// initialRequest :"sort=dateInput&dir=asc&startIndex=-1&results=25",
		// dynamicData :true,
		sortedBy : {
			key : "dateComing",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		},
		paginator : new YAHOO.widget.Paginator(
				{
					rowsPerPage : 25,
					firstPageLinkLabel : "首页",
					lastPageLinkLabel : " 尾页",
					previousPageLinkLabel : " 上一页",
					nextPageLinkLabel : " 下一页",
					template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
					pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
					rowsPerPageOptions : [ 25, 50, 100 ]
				}),
		width : "auto"
	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.ScrollingDataTable("makeToArrange",
			myColumnDefs, myDataSource, myConfigs);
	myDataTable.subscribe("postRenderEvent", function() {
		$.unblockUI();
		addColumnsName();
		resizeScrollTable("makeToArrange");
		parent.resizeIframe();
	});
	myDataSource.subscribe("requestEvent", function() {
		$.blockUI( {
			message : "<h1>数据加载中......</h1>"
		});
	});
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	var headerCheckbox = YAHOO.util.Dom.get("Aheader_checkbox");
	YAHOO.util.Event.addListener(headerCheckbox, "click",
			OnHeaderCheckboxClicked);
	// column select option
	var columnSet = myDataTable.getColumnSet();
	var showHideColumn = function(e) {
		var container = document.getElementById("makeToArrange");
		if (typeof (container.originalWidth) == "undefined"
				|| container.originalWidth == null) {
			container.originalWidth = container.offsetWidth;
		}

		var column = columnSet.getColumn(this.value);
		if (this.checked) {
			myDataTable.hideColumn(column);
		} else {
			myDataTable.showColumn(column);
		}
		
		resizeScrollTable("makeToArrange");
		parent.resizeIframe();
	}
	var colDiv = YAHOO.util.Dom.get("colDiv");
	var colLink = YAHOO.util.Dom.get("tableOption");
	YAHOO.util.Event.addListener(colLink, "click", function() {
		colDiv.style.display = colDiv.style.display == "block" ? "none"
				: "block";
		return false;
	});
	var addColumnsName = function() {
		if (colDiv.innerHTML.length == 0) {
			for ( var i = 0; i < myColumnDefs.length; i++) {
				var column = myColumnDefs[i];
				if (column.key == "checkbox")
					continue;
				var checkbox = document.createElement("INPUT");
				checkbox.type = "checkbox";
				checkbox.name = "colCkbox";
				checkbox.value = column.key;
				checkbox.checked = false;
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

	return {
		ds : myDataSource,
		dt : myDataTable
	};

}

function initPassTableDataSource(url, callBack) {
	YAHOO.util.Connect.asyncRequest('GET', url, callBack);
}
var toToPassTableCallBack = {
	success : function(o) {
		YAHOO.log("RAW JSON DATA: " + o.responseText);
		// Process the JSON data returned from the server
	var records = "";
	try {
		records = YAHOO.lang.JSON.parse(o.responseText);
		initToPassTable(records);
	} catch (x) {
		alert("JSON Parse failed!");
		return;
	}
},

failure : function(o) {
	if (!YAHOO.util.Connect.isCallInProgress(o)) {
		alert("Async call failed!");
	}
},
timeout : 3000

};
var myDataTablePass = null;
function initToPassTable(ds) {

	var OnHeaderCheckboxClicked = function() {
		var myHeaderCheckbox = YAHOO.util.Dom.get("Pheader_checkbox");
		var headerChecked = myHeaderCheckbox.checked;
		var myDataRecords = myDataTablePass.getRecordSet();
		var checkBoxIds2Check = new Array();
		for ( var i = 0; i < myDataRecords.getLength(); i++) {
			checkBoxIds2Check.push(myDataRecords.getRecord(i).getData("id"));
		}

		var id2Check;
		for (id in checkBoxIds2Check) {
			id2Check = document.getElementById("row_" + checkBoxIds2Check[id]);
			id2Check.checked = headerChecked;
		}

	};
	// Helps in drawing the checkbox for all rows !
	var FormatCheckboxCell = function(elCell, oRecord, oColumn, oData) {
		// Create checkbox
		var checkboxId = 'row_' + oRecord.getData()["id"];
		var checkbox = YAHOO.util.Dom.get(checkboxId);
		if (checkbox == null) {
			checkbox = document.createElement('input');
			checkbox.setAttribute('type', 'checkbox');
			checkbox.setAttribute('id', checkboxId);
			checkbox.setAttribute('class',
					YAHOO.widget.DataTable.CLASS_CHECKBOX);
			checkbox.setAttribute('name', 'toPassed');
			checkbox.setAttribute('value', oRecord.getData()["id"]);
			elCell.innerHTML = "";
			elCell.appendChild(checkbox);
		}
	}
	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='/tv/vedio/searchVideoByNameOrIDForModification.action?optionName=modification&vid=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	}

	// Column definitions
	var myColumnDefs = [
			{
				key : "checkbox",
				label : "<input id='Pheader_checkbox' class='yui-dt-checkbox' type='checkbox'>",
				sortable : false,
				resizeable : false,
				formatter : FormatCheckboxCell
			}, {
				key : "id",
				label : "编号",
				sortable : true,
				formatter : formatLink
			}, {
				key : "vedioName",
				label : "剧目名称",
				sortable : true
			}, {
				key : "topic",
				label : "题材",
				sortable : true
//				formatter : formatTopic,
//				sortOptions : {
//					sortFunction : sortTopic
//				}
			}, {
				key : "subject",
				label : "栏目",
				sortable : true
//				formatter : formatSubject
//				sortOptions : {
//					sortFunction : sortSubject
//				}
			}, {
				key : "companyID",
				label : "影视公司",
				sortable : true
//				formatter : formatCompany,
//				sortOptions : {
//					sortFunction : sortCompany
//				}
			}, {
				key : "dateComing",
				label : "收带日期",
				sortable : true,
				formatter : formatDate
//			}, {
//				key : "status",
//				label : "状态",
//				formatter : formatStatus
			}, {
				key : "avgScore",
				label : "综合平均分",
//				formatter : formatScroes,
				sortable : true
//				sortOptions : {
//					sortFunction : sortScores
//				}
			}, {
				key : "audiScore",
				label : "观众投票(看/不看)"
//				formatter : formatAudienceScore
			}, {
				key : "award",
				label : "获奖备选(是/否)"
//				formatter : formatAward
			}, {
				key : "comments",
				label : "备注",
				formatter : formatorComments
			} ];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource(ds)
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : [ "id", "vedioName", "topic", "subject", "companyID",
				"dateComing", "status", "avgScore", "audiScore", "award",
				"comments" ],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
	// response
	}
	};

	// DataTable configuration
	var myConfigs = {
		sortedBy : {
			key : "dateComing",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		},
		paginator : new YAHOO.widget.Paginator(
				{
					rowsPerPage : 25,
					firstPageLinkLabel : "首页",
					lastPageLinkLabel : " 尾页",
					previousPageLinkLabel : " 上一页",
					nextPageLinkLabel : " 下一页",
					template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
					pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
					rowsPerPageOptions : [ 25, 50, 100 ]
				}),
		width : "auto"
	};

	// DataTable instance

	var myDataTablePass = new YAHOO.widget.ScrollingDataTable("makeToPass",
			myColumnDefs, myDataSource, myConfigs);

	myDataTablePass.subscribe("postRenderEvent", function() {
		$.unblockUI();
		addColumnsNamePass();
		resizeScrollTable("makeToPass");
		parent.resizeIframe();
	});
	myDataTablePass.subscribe("requestEvent", function() {
		$.blockUI( {
			message : "<h1>数据加载中......</h1>"
		});
	});
	myDataTablePass.subscribe("rowMouseoverEvent",
			myDataTablePass.onEventHighlightRow);
	myDataTablePass.subscribe("rowMouseoutEvent",
			myDataTablePass.onEventUnhighlightRow);
	// Update totalRecords on the fly with value from server
	myDataTablePass.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	var headerCheckbox = YAHOO.util.Dom.get("Pheader_checkbox");
	YAHOO.util.Event.addListener(headerCheckbox, "click",
			OnHeaderCheckboxClicked);

	// column select option
	var columnSet = myDataTablePass.getColumnSet();
	var showHideColumnPass = function(e) {
		var container = document.getElementById("makeToPass");
		if (typeof (container.originalWidth) == "undefined"
				|| container.originalWidth == null) {
			container.originalWidth = container.offsetWidth;
		}

		var column = columnSet.getColumn(this.value);
		if (this.checked) {
			myDataTablePass.hideColumn(column);
		} else {
			myDataTablePass.showColumn(column);
		}

		resizeScrollTable("makeToPass");
		parent.resizeIframe();
	}
	var colDiv = YAHOO.util.Dom.get("colDivPass");
	var colLink = YAHOO.util.Dom.get("tableOptionPass");
	YAHOO.util.Event.addListener(colLink, "click", function() {
		colDiv.style.display = colDiv.style.display == "block" ? "none"
				: "block";
		return false;
	});
	var addColumnsNamePass = function() {
		if (colDiv.innerHTML.length == 0) {
			for ( var i = 0; i < myColumnDefs.length; i++) {
				var column = myColumnDefs[i];
				if (column.key == "checkbox")
					continue;
				var checkbox = document.createElement("INPUT");
				checkbox.type = "checkbox";
				checkbox.name = "colCkbox";
				checkbox.value = column.key;
				checkbox.checked = false;
				colDiv.appendChild(checkbox);
				var p = document.createElement("SPAN");
				p.innerHTML = column.label;
				colDiv.appendChild(p);
				if (i % 2 == 1) {
					var br = document.createElement("BR");
					colDiv.appendChild(br);
				}

				YAHOO.util.Event.addListener(checkbox, "click",
						showHideColumnPass);
				colDiv.style.display = "none";
			}
		}
	};
	return {
		ds : myDataSource,
		dt : myDataTablePass
	};

}
function initButtons() {
	var submitToPreArrange = new YAHOO.widget.Button( {
		type : "button",
		label : "批为编排",
		id : "toArrageBtn",
		container : "submitToPreArrange"
	});

	submitToPreArrange.on("click", function() {
		var toPassed = document.getElementsByName("toPassed");
		if (toPassed != null && toPassed.length > 0) {
			for ( var i = 0; i < toPassed.length; i++) {
				if (toPassed[i].type == "checkbox") {
					if (toPassed[i].checked == true
							|| toPassed[i].checked == "checked") {
						toPassed[i].checked = false;
					}
				}
			}
		}
		var form = document.getElementById("tableForm");
		form.submit();

	});

	var submitToPass = new YAHOO.widget.Button( {
		type : "button",
		label : "撤销编排",
		id : "toPassBtn",
		container : "submitToPass"
	});
	submitToPass.on("click", function() {
		var toApproved = document.getElementsByName("toApproved");
		if (toApproved != null && toApproved.length > 0) {
			for ( var i = 0; i < toApproved.length; i++) {
				if (toApproved[i].type == "checkbox") {
					if (toApproved[i].checked == true
							|| toApproved[i].checked == "checked") {
						toApproved[i].checked = false;
					}
				}
			}
		}
		var form = document.getElementById("tableForm");
		form.submit();

	});

	var searchBtn = new YAHOO.widget.Button( {
		type : "submit",
		label : "搜索",
		id : "search",
		container : "goSearch"
	});
}