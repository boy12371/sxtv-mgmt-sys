function initEmployeeDataTable() {

	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateEmployee.action?employee.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};
	var formatGender = function(elCell, oRecord, oColumn, sData) {
		var sex = "<span>女</span>";
		if (sData == 1) {
			sex = "<span>男</span>";
		}
		elCell.innerHTML = sex;
	};
	var formatDate = function(elCell, oRecord, oColumn, sData) {
		var idx = sData.indexOf("T");
		if (idx != -1) {
			elCell.innerHTML = sData.substring(0, idx);
		} else {
			elCell.innerHTML = sData;
		}
	};
	var formatComments = function(elCell, oRecord, oColumn, sData) {
		sData = sData.substring(0, 13);
		elCell.innerHTML = sData;
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
				key : "name",
				label : "姓名",
				sortable : true
			}, {
				key : "gender",
				label : "性别",
				sortable : true,
				formatter : formatGender
			}, {
				key : "contractDate",
				label : "入职日期",
				sortable : true,
				formatter : formatDate
			}, {
				key : "birthday",
				label : "生日",
				sortable : true,
				formatter : formatDate
			}, {
				key : "tel",
				label : "电话"
			}, {
				key : "status",
				label : "状态",
				sortable : true,
				formatter : formatStatus
			}, {
				key : "comments",
				label : "备注",
				formatter : formatComments
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getEmployees.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "name", "gender", "contractDate", "birthday", "tel",
				"status", "comments"],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=id&dir=asc&startIndex=0&results=20", // Initial
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "id",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage : 20,
			firstPageLinkLabel : "首页",
			lastPageLinkLabel : " 尾页",
			previousPageLinkLabel : " 上一页",
			nextPageLinkLabel : " 下一页",
			template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}",
			pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
		})
		// Enables pagination
	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.DataTable("employeeTable", myColumnDefs,
			myDataSource, myConfigs);
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
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

function initUserDataTable() {
	var formatUrl = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='./sys/toUpdateUser.action?user.id=";
		href += sData;
		href += "'>" + sData + "</a>";
		elCell.innerHTML = href;
	};

	var formatStatus = function(elCell, oRecord, oColumn, sData) {
		if (sData == 0) {
			elCell.innerHTML = "<span>禁用</span>";
		} else {
			elCell.innerHTML = "<span>正常</span>";
		}

	}
	// Column definitions
	
	var formatRoles = function(elCell, oRecord, oColumn, sData) {
		if (sData.length == 0) {
			elCell.innerHTML="";
		}else{
			var roles ="";
			for (var i = 0; i < sData.length; i++) {
				role = sData[i];
				roles += role.name+" ";
			}
			elCell.innerHTML =roles;
		}
		
	}
	var myColumnDefs = [ // sortable:true enables sorting
	{
				key : "id",
				label : "编号",
				sortable : true,
				formatter : formatUrl
			}, {
				key : "userName",
				label : "用户名",
				sortable : true
			}, {
				key : "roles",
				label : "角色",
				formatter : formatRoles
			}, {
				key : "status",
				label : "状态",
				sortable : true,
				formatter : formatStatus
			}, {
				key : "employee.name",
				label : "员工"
			}];

	// DataSource instance
	var myDataSource = new YAHOO.util.DataSource("/tv/sys/getUsers.action?");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;

	myDataSource.responseSchema = {
		resultsList : "records",
		fields : ["id", "userName", "roles", "status", {
					key : "employee.name"
				}],
		metaFields : {
			totalRecords : "totalRecords" // Access to value in the server
			// response
		}
	};

	// DataTable configuration
	var myConfigs = {
		initialRequest : "sort=id&dir=asc&startIndex=0&results=20", // Initial
		dynamicData : true, // Enables dynamic server-driven data
		sortedBy : {
			key : "id",
			dir : YAHOO.widget.DataTable.CLASS_ASC
		}, // Sets UI initial sort arrow
		paginator : new YAHOO.widget.Paginator({
			rowsPerPage : 20,
			firstPageLinkLabel : "首页",
			lastPageLinkLabel : " 尾页",
			previousPageLinkLabel : " 上一页",
			nextPageLinkLabel : " 下一页",
			template : "{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}",
			pageReportTemplate : "Showing items {startIndex} - {endIndex} of {totalRecords}",
		})
		// Enables pagination
	};

	// DataTable instance

	var myDataTable = new YAHOO.widget.DataTable("userTable", myColumnDefs,
			myDataSource, myConfigs);
	// Update totalRecords on the fly with value from server
	myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
			oPayload) {
		oPayload.totalRecords = oResponse.meta.totalRecords;
		return oPayload;
	}
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable
			.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	return {
		ds : myDataSource,
		dt : myDataTable
	};
}

function executOperations(obj) {
	var form = document.forms[0];
	form.operation.value = obj;
	if (obj == "disableEmp") {
		jConfirm('注销此员工后将同时禁用其系统用户，确定吗？', '警告', function(r) {
					if (r) {
						form.submit();
					}
				});
	} else {
		form.submit();
	}

}