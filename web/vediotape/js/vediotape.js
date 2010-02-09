

function isVedioNameValid(inputEl) {
	var callbacks = {
		success : function(o) {
			YAHOO.log("RAW JSON DATA: " + o.responseText);
			// Process the JSON data returned from the server
			var obj = o.responseText;
			
			if (obj.indexOf("SUCCESS")== -1) {
				var msg = "<font color='red'>" + obj + "</font>";
				alert(obj);
				// YAHOO.util.Dom.insertAfter(msg,inputEl);
			}

		},

		failure : function(o) {
			if (!YAHOO.util.Connect.isCallInProgress(o)) {
				alert("Async call failed!");
			}
		},

		timeout : 3000
	}
	
	var vedioName = inputEl.value;
	var url = "/tv/vedio/isVediotapeExsits.action?vedioName=" + vedioName;
	YAHOO.util.Connect.asyncRequest('GET', url, callbacks);
}

var isBuild = false;
var companies = [];
var topices = [];
var subjects = []
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

		elCell.innerHTML = oData["companyName"];
	}
	var formatTopic = function(elCell, oRecord, oColumn, oData) {
		elCell.innerHTML = oData["topic"];
	}
	var formatSubject = function(elCell, oRecord, oColumn, oData) {
		elCell.innerHTML = oData["subject"];
	}
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
				key : "vdate",
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
		fields : ["vid", "vname", "vcompany", "vtopic", "vsubject", "vdate",
				"vcomments"]
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

		var _vdate = YAHOO.util.Date.format(new Date(), {
					format : "%Y-%m-%d"
				});

		var _vcomments = YAHOO.util.Dom.get("vcomments").value;
		return {
			vid : _vid,
			vname : _vname,
			vcompany : _vc,
			vtopic : _vt,
			vsubject : _vs,
			vdate : _vdate,
			vcomments : _vcomments
		};

	};

	var handleClick = function() {
		myDataTable.addRow(getData(), 0);
	}

	var btn = new YAHOO.widget.Button("go");
	btn.on("click", handleClick);

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
				jasonString += "{id:" + oData.getData("vid") + ",vedioName:\""
						+ oData.getData("vname") + "\",companyID:"
						+ oData.getData("vcompany").id + ",topic:"
						+ oData.getData("vtopic").id + ",subject:"
						+ oData.getData("vsubject").id + ",comments:\""
						+ oData.getData("vcomments") + "\"}";
			}
			jasonString += "]";

			data.value = jasonString;
			// alert(data.value);
			form.submit();
		} else {
			alert("尚未添加任何影带信息");
		}

	}
	var submitBtn = new YAHOO.widget.Button("submit");
	submitBtn.on("click", handleSubmit);

	var checkVedioName = function() {
		
		var vName = YAHOO.util.Dom.get("vname");
		var records = myDataTable.getRecordSet().getRecords();
		var len = records.length;
		var exists = false;
		if (len != 0) {
			for (var i = 0; i < len; i++) {
				var oData = records[i];
				var _vname = oData.getData("vname");
				if (vName.value == _vname) {
					alert("影带已存在，请检查影带名称");
					exists = true;
					break;
				}
			}		
			if(exists ==false){
				isVedioNameValid(vName);
			}
		}
	}
	
	var vedioName = YAHOO.util.Dom.get("vname");
	YAHOO.util.Event.addListener(vedioName,"blur",checkVedioName);
	//vedioName.on("blur", checkVedioName);
	
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
						if (confirm("您却定要删除影带 " + oRecord.getData("vid") + " ("
								+ oRecord.getData("vname") + ")?")) {
							p_myDataTable.deleteRow(elRow);
						}
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
