var isBuild = false;
var companies = [];
var topices = [];
var subjects = [];
function buildArray(array, selectId) {
	var obj = YAHOO.util.Dom.get(selectId);
	var ops = obj.options;
	for (var i = 0; i < ops.length; i++) {
		array.push({
					label : ops[i].innerHTML,
					value : ops[i].value
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
			elCell.innerHTML = oData.substring(0, 35) + ".....";
		} else {
			elCell.innerHTML = oData;
		}
	}
	var formatCompany = function(elCell, oRecord, oColumn, oData) {
		if (typeof(oData) != "object") {
			var _id = parseInt(oData);
			for (var i = 0; i < companies.length; i++) {
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

		if (typeof(oData) != "object") {
			var _id = parseInt(oData);
			for (var i = 0; i < topices.length; i++) {
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
		if (typeof(oData) != "object") {
			var _id = parseInt(oData);
			for (var i = 0; i < subjects.length; i++) {
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
	var myColumnDefs = [{
				key : "vid",
				label : "编号",
				sortable : true
			}, {
				key : "vname",
				label : "剧目名称",
				editor : new YAHOO.widget.TextboxCellEditor({
							disableBtns : true
						})
			}, {
				key : "vcompany",
				label : "影视公司",
				sortable : true,
				formatter : formatCompany,
				editor : new YAHOO.widget.DropdownCellEditor({
							dropdownOptions : companies,
							disableBtns : true
						})
			}, {
				key : "vtopic",
				label : "题材",
				sortable : true,
				formatter : formatTopic,
				editor : new YAHOO.widget.DropdownCellEditor({
							dropdownOptions : topices,
							disableBtns : true
						})
			}, {
				key : "vsubject",
				label : "栏目",
				sortable : true,
				formatter : formatSubject,
				editor : new YAHOO.widget.DropdownCellEditor({
							dropdownOptions : subjects,
							disableBtns : true
						})
			}, {
				key : "vdateComing",
				label : "收带日期",
				sortable : true
			}, {
				key : "vcomments",
				label : "备注",
				formatter : formatorRemarks,
				editor : new YAHOO.widget.TextareaCellEditor({
							disableBtns : false
						})
			}];

	var myDataSource = new YAHOO.util.DataSource([]);
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
	myDataSource.responseSchema = {
		fields : ["vid", "vname", "vcompany", "vtopic", "vsubject",
				"vdateComing", "vcomments"]
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

	getData = function() {
		var _vid = YAHOO.util.Dom.get("vid").value;
		var _vname = YAHOO.util.Dom.get("vname").value;
		var _vcompany = YAHOO.util.Dom.get("vcompany");
		var _vc = {
			id : _vcompany.options[_vcompany.selectedIndex].value,
			companyName : _vcompany.options[_vcompany.selectedIndex].text
		};
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

		var _vdate = dojo.widget.byId("vdateComing").getValue();

		var _vcomments = YAHOO.util.Dom.get("vcomments").value;
		return {
			vid : _vid,
			vname : _vname,
			vcompany : _vc,
			vtopic : _vt,
			vsubject : _vs,
			vdateComing : _vdate.substring(0, _vdate.indexOf("T")),
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
		if(videoId.length==0 || videoName.length==0){
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
			for (var i = 0; i < dataSet.length; i++) {
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
			for (var i = 0; i < len; i++) {
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
						+ vs + ", dateComing: \""
						+ oData.getData("vdateComing") + "\",comments:\""
						+ oData.getData("vcomments") + "\"}";
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
					case 0 : // Delete row upon confirmation
						var oRecord = p_myDataTable.getRecord(elRow);
						jConfirm("您却定要删除影带 " + oRecord.getData("vid") + " ("
										+ oRecord.getData("vname") + ")?",
								"提示", function(r) {
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
	myContextMenu.addItem("Delete Item");
	// Render the ContextMenu instance to the parent container of the DataTable
	myContextMenu.render("cellediting");
	myContextMenu.clickEvent.subscribe(onContextMenuClick, myDataTable);
	return {
		oDS : myDataSource,
		oDT : myDataTable
	};
}

function initModificationPage() {
	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='/tv/vedio/searchVideoByNameOrIDForModification?optionName=modification&vid=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
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
				sortable : true,
				formatter : formatDate
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
	var myDataSource = new YAHOO.util.XHRDataSource("/tv/audit/filterVideos.action?filter=4&");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.connXhrMode = "queueRequests";
	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "comments"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=dateInput&dir=asc&startIndex=0&results=25",
		dynamicData : true,
		sortedBy : {
			key : "dateInput",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		},
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25,
					template : YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions : [25, 50, 100]
				})

	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}

	return {
		ds : myDataSource,
		dt : myDataTable
	};

}
