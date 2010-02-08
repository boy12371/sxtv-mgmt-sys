

function initSelectionsData() {
	var callbacks = {
		success : function(o) {
			YAHOO.log("RAW JSON DATA: " + o.responseText);
			// Process the JSON data returned from the server
			try {
				var obj = YAHOO.lang.JSON.parse(o.responseText);
			} catch (x) {
				alert("JSON Parse failed!");
				return;
			}
			// YAHOO.log("PARSED DATA: " + YAHOO.lang.dump(companies));
			var com_section = YAHOO.util.Dom.get('vcompany');
			// The returned data was parsed into an array of objects.
			// Add a P element for each received message
			for (var i = 0; i < obj.length; i++) {
				var c = obj[i];
				var _text = c.companyName;
				var _value = c.id;
				com_section.options.add(new Option(_text, _value));

			}

		},

		failure : function(o) {
			if (!YAHOO.util.Connect.isCallInProgress(o)) {
				alert("Async call failed!");
			}
		},

		timeout : 3000
	}
	YAHOO.util.Connect.asyncRequest('GET', "/tv/vedio/getCompanies.action",
			callbacks);
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
				key : "vremarks",
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
				"vremarks"]
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

		var _vremarks = YAHOO.util.Dom.get("vremarks").value;
		return {
			vid : _vid,
			vname : _vname,
			vcompany : _vc,
			vtopic : _vt,
			vsubject : _vs,
			vdate : _vdate,
			vremarks : _vremarks
		};

	};

	var handleClick = function() {
		myDataTable.addRow(getData(), 0);
	}

	var btn = new YAHOO.widget.Button("go");
	btn.on("click", handleClick);
	
	
	var handleSubmit = function(){
		var d = myDataTable.getRecordSet().getRecords();	
		alert(d[0].getData["vid"]);
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