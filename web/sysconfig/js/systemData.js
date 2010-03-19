function initStatusTable() {
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
				key : "id",
				label : "编号",
				sortable : true
				,
			}, {
				key : "status",
				label : "状态"
			}, {
				key : "comments",
				label : "说明"
			}];
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getStatuses.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "status", "comments"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=id&dir=asc&startIndex=0&results=25",
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "id",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25,
					template : YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions : [25, 50, 100]
				})
		// Enables pagination
	};
	// DataTable instance
	var myDataTable = new YAHOO.widget.DataTable("vedioStatus", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("initEvent", function() {
				parent.resizeIframe();
			});
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

function initTopicTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};

	var formatStatus = function(elCell, oRecord, oColumn, sData) {
		var st = sData == 1 ? "正常" : "禁用";
		elCell.innerHTML = st;
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
				key : "id",
				label : "编号",
				sortable : true,
				formatter : formatUrl
			}, {
				key : "topicName",
				label : "题材"
			}, {
				key : "status",
				label : "状态",
				sortable : true,
				formatter : formatStatus
			}, {
				key : "comments",
				label : "备注"
			}, {
				key : "select",
				label : "操作",
				formatter : "dropdown",
				dropdownOptions : [{
							label : "选择",
							value : "none"
						}, {
							label : "修改",
							value : "modify"
						}, {
							label : "禁用",
							value : "disable"
						}, {
							label : "启用",
							value : "enable"
						}]

			}];
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getTopices.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "topicName", "status", "comments", {
					key : "select",
					parser : "string"
				}],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=id&dir=asc&startIndex=0&results=25",
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "id",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25,
					template : YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions : [25, 50, 100]
				})
		// Enables pagination
	};
	// DataTable instance
	var myDataTable = new YAHOO.widget.DataTable("vedioTopic", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("initEvent", function() {
				parent.resizeIframe();
			});
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataTable.subscribe("dropdownChangeEvent", function(oArgs) {
		var elDropdown = oArgs.target;
		var oRecord = this.getRecord(elDropdown);
		var objName = YAHOO.util.Dom.get("objName");
		var objComments = YAHOO.util.Dom.get("objComments");
		var objID = YAHOO.util.Dom.get("objID");
		objName.value = "";
		objComments.value = "";
		objID.value = "";
		var form = document.forms[0];
		var opt = elDropdown.value;
		if (opt == "none") {
			elDropdown.selectIndex = 0;
		} else if (opt == "modify") {
			elDropdown.selectedIndex = 0;
			$.blockUI({
						message : $('#hiddenDiv'),
						css : {
							width : '475px',
							top : '45%',
							left : '25%',
							cursor : 'auto'
						}
					});

			var yesBtn = YAHOO.util.Dom.get("yes");
			YAHOO.util.Event.addListener(yesBtn, "click", function() {
						$.unblockUI();
						objName.name = "topic.topicName";
						objComments.name = "topic.comments";
						objID.name = "topic.id";
						objID.value = oRecord.getData("id");
						form.action = "/tv/sys/modifyTopic.action"
						form.submit();
					});
			var yesBtn = YAHOO.util.Dom.get("cancel");
			YAHOO.util.Event.addListener(yesBtn, "click", function() {
						$.unblockUI();
					});
		} else if (opt == "disable") {
			if (oRecord.getData("status") == 0
					|| oRecord.getData("status") == "0") {
				elDropdown.selectedIndex = 0;
				jAlert("题材已禁用", "提示");
				return;
			}
			jConfirm("真的要禁用" + oRecord.getData("topicName") + "题材吗?", '警告',
					function(r) {
						if (r) {
							window.location = "/tv/sys/doDisableEnableTopic.action?enableOperator=false&topic.id="
									+ oRecord.getData("id");
						}
					});
		} else {
			if (oRecord.getData("status") == 1
					|| oRecord.getData("status") == "1") {
				elDropdown.selectedIndex = 0;
				jAlert("题材已启用", "提示");
				return;
			}
			jConfirm('真的要启用《' + oRecord.getData("topicName") + '》题材吗?', '警告',
					function(r) {
						if (r) {
							window.location = "/tv/sys/doDisableEnableTopic.action?enableOperator=true&topic.id="
									+ oRecord.getData("id");
						}
					});
		}
	});
	return {
		ds : myDataSource,
		dt : myDataTable
	};
}

function initSubjectTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
	var formatStatus = function(elCell, oRecord, oColumn, sData) {
		var st = sData == 1 ? "正常" : "禁用";
		elCell.innerHTML = st;
	};
	// Column definitions
	var myColumnDefs = [ // sortable:true enables sorting
	{
		key : "id",
		label : "编号",
		sortable : true
			// ,formatter : formatUrl
		}, {
		key : "subjectName",
		label : "栏目"
	}, {
		key : "status",
		label : "状态",
		sortable : true,
		formatter : formatStatus
	}, {
		key : "comments",
		label : "备注"
	}, {
		key : "select",
		label : "操作",
		formatter : "dropdown",
		dropdownOptions : [{
					label : "选择",
					value : "none"
				}, {
					label : "修改",
					value : "modify"
				}, {
					label : "禁用",
					value : "disable"
				}, {
					label : "启用",
					value : "enable"
				}]

	}];
	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getSubjects.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "subjectName", "comments", "status", {
					key : "select",
					parser : "string"
				}],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};
	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=id&dir=asc&startIndex=0&results=25",
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "id",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
					rowsPerPage : 25,
					template : YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
					rowsPerPageOptions : [25, 50, 100]
				})
		// Enables pagination
	};
	// DataTable instance
	var myDataTable = new YAHOO.widget.DataTable("vedioSubject", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("initEvent", function() {
				parent.resizeIframe();
			});
	myDataTable.subscribe("dropdownChangeEvent", function(oArgs) {
		var elDropdown = oArgs.target;
		var oRecord = this.getRecord(elDropdown);
		var objName = YAHOO.util.Dom.get("objName");
		var objComments = YAHOO.util.Dom.get("objComments");
		var objID = YAHOO.util.Dom.get("objID");
		objName.value = "";
		objComments.value = "";
		objID.value = "";

		var opt = elDropdown.value;
		if (opt == "none") {
			elDropdown.selectIndex = 0;
		} else if (opt == "modify") {
			elDropdown.selectedIndex = 0;
			$.blockUI({
						message : $('#hiddenDiv'),
						css : {
							width : '475px',
							top : '45%',
							left : '25%',
							cursor : 'auto'
						}
					});

			var yesBtn = YAHOO.util.Dom.get("yes");
			YAHOO.util.Event.addListener(yesBtn, "click", function() {
						$.unblockUI();
						objName.name = "subject.subjectName";
						objComments.name = "subject.comments";
						objID.name = "subject.id";
						objID.value = oRecord.getData("id");
						var form = document.forms[0];
						form.action = "/tv/sys/modifySubject.action"
						form.submit();
					});
			var yesBtn = YAHOO.util.Dom.get("cancel");
			YAHOO.util.Event.addListener(yesBtn, "click", function() {
						$.unblockUI();
					});
		} else if (opt == "disable") {
			if (oRecord.getData("status") == 0
					|| oRecord.getData("status") == "0") {
				elDropdown.selectedIndex = 0;
				jAlert("栏目已禁用", "提示");
				return;
			}
			jConfirm('真的要禁用《' + oRecord.getData("subjectName") + '》栏目吗?', '警告',
					function(r) {
						if (r) {
							window.location = "/tv/sys/doDisableEnableSubject.action?enableOperator=false&subject.id="
									+ oRecord.getData("id");
						}
					});
		} else {
			if (oRecord.getData("status") == 1
					|| oRecord.getData("status") == "1") {
				elDropdown.selectedIndex = 0;
				jAlert("栏目已启用", "提示");
				return;
			}
			jConfirm('真的要启用《' + oRecord.getData("subjectName") + '》栏目吗?', '警告',
					function(r) {
						if (r) {
							window.location = "/tv/sys/doDisableEnableSubject.action?enableOperator=true&subject.id="
									+ oRecord.getData("id");
						}
					});
		}
	});
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
