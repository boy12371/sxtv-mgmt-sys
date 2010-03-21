function initDataTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateCompany.action?company.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};

	var formatComments = function(elCell, oRecord, oColumn, sData) {
		sData = sData.substring(0, 20);
		elCell.innerHTML = sData;
	}
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
				key : "companyName",
				label : "公司名称"
			}, {
				key : "registrationNo",
				label : "注册号"
			}, {
				key : "phone",
				label : "电话"
			}, {
				key : "contactPerson",
				label : "联系人"
			}, {
				key : "comments",
				label : "备注",
				formatter : formatComments
			}, {
				key : "status",
				label : "状态",
				sortable : true,
				formatter : formatStatus
			}, {
				key : "select",
				label : "操作",
				formatter : "dropdown",
				dropdownOptions : [{
							label : "选择",
							value : "none"
						}, {
							label : "禁用",
							value : "disable"
						}, {
							label : "启用",
							value : "enable"
						}]

			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getCompanies.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "companyName", "registrationNo", "phone",
				"contactPerson", "comments", "status", {
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
		initialRequest : "sort=id&dir=asc&startIndex=0&results=25", // Initial
		// request
		// for first
		// page of
		// data
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

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);

	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataTable.subscribe("initEvent", function() {
				parent.resizeIframe();
			});

	myDataTable.subscribe("columnSortEvent", function(column, dir) {
				alert(dir);
			});
	myDataTable.subscribe("dropdownChangeEvent", function(oArgs) {
		var elDropdown = oArgs.target;
		//var oRecord = this.getRecord(elDropdown);
		var rowIndex = elDropdown.parentNode.parentNode.parentNode.rowIndex;
		
		var oRecord = this.getRecord(2);
		var opt = elDropdown.value;
		if (opt == "none") {
			elDropdown.selectIndex = 0;
		} else if (opt == "disable") {
			elDropdown.selectedIndex = 0;
			if (oRecord.getData("status") == 0
					|| oRecord.getData("status") == "0") {
				jAlert("公司已禁用", "提示");
				return;
			}
			jConfirm("真的要禁用《" + oRecord.getData("companyName") + "》吗?", '警告',
					function(r) {
						if (r) {
							window.location = "/tv/sys/doDisableEnableCompany.action?enableOperator=false&company.id="
									+ oRecord.getData("id");
						}
					});
		} else {
			elDropdown.selectedIndex = 0;
			if (oRecord.getData("status") == 1
					|| oRecord.getData("status") == "1") {
				jAlert("公司已启用", "提示");
				return;
			}
			jConfirm('真的要启用《' + oRecord.getData("companyName") + '》吗?', '警告',
					function(r) {
						if (r) {
							window.location = "/tv/sys/doDisableEnableCompany.action?enableOperator=true&company.id="
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

function initAudienceDataTable() {
	var formatComments = function(elCell, oRecord, oColumn, oData) {
		oData = oData.substring(0, 20);
		elCell.innerHTML = oData;
	}
	var formatGender = function(elCell, oRecord, oColumn, oData) {
		
		elCell.innerHTML = oData==1?"男":"女";
	}
	// Column definitions
	var myColumnDefs = [{
				key : "id",
				label : "编号",
				sortable : true
			}, {
				key : "name",
				label : "姓名"
			}, {
				key : "age",
				label : "年龄",
				sortable : true
			}, {
				key : "gender",
				label : "性别",
				formatter: formatGender,
				sortable : true
			}, {
				key : "career",
				label : "职业"
			}, {
				key : "comments",
				label : "备注",
				formatter : formatComments
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getAllAudiences.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "name", "age", "gender", "career", "comments"],
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

	var myDataTable = new YAHOO.widget.DataTable("dynamicdata", myColumnDefs,
			myDataSource, myConfigs);

	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataTable.subscribe("initEvent", function() {
				parent.resizeIframe();
			});
	return {
		ds : myDataSource,
		dt : myDataTable
	};
}