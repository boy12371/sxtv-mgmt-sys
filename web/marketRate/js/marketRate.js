function initDataTable() {

	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='/tv/vedio/searchVideoByNameOrIDForMarketRate?optionName=marketRate&vid=";
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
	var myColumnDefs = [ {
		key :"id",
		label :"编号",
		sortable :true,
		formatter :formatLink
	}, {
		key :"vedioName",
		label :"剧目名称"
	}, {
		key :"topic",
		label :"题材",
		sortable :true,
		formatter :formatTopic
	}, {
		key :"subject",
		label :"栏目",
		sortable :true,
		formatter :formatSubject
	}, {
		key :"companyID",
		label :"影视公司",
		sortable :true,
		formatter :formatCompany
	}, {
		key :"dateInput",
		label :"收带日期",
		sortable :true,
		formatter :formatDate
	}, {
		key :"status",
		label :"状态",
		sortable :true,
		formatter :formatStatus
	}, {
		key :"comments",
		label :"备注"
	} ];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource(
			"/tv/audit/filterVideos.action?filter=8&");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.connXhrMode = "queueRequests";
	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "topic", "subject", "companyID",
				"dateInput", "status", "comments" ],
		metaFields : {
			totalRecords :"totalRecords" // Access to value in the server
	// response
		}
	};
	
	// DataTable configuration
	var myConfigs = {
		initialRequest :"sort=dateInput&dir=asc&startIndex=0&results=25",
		dynamicData :true,
		sortedBy : {
			key :"dateInput",
			dir :YAHOO.widget.DataTable.CLASS_ASC
		},
		paginator :new YAHOO.widget.Paginator( {
			rowsPerPage :25,
			template :YAHOO.widget.Paginator.TEMPLATE_ROWS_PER_PAGE,
			rowsPerPageOptions : [ 25, 50, 100 ]
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
		ds :myDataSource,
		dt :myDataTable
	};
}

