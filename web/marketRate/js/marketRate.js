function initDataTable() {

	var formatLink = function(elCell, oRecord, oColumn, sData) {
		var href = "<a href='/tv/vedio/searchVideoByNameOrIDForMarketRate.action?optionName=marketRate&vid=";
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
	},/*** {
		key : "vedioscores",
		label : "综合平均分",
		formatter : formatScroes
	}, {
		key : "audiencescore",
		label : "观众投票(看/不看)",
		formatter : formatAudienceScore
	}, {
		key : "vedioscores",
		label : "获奖备选(是/否)",
		formatter : formatAward
	}, **/{
		key : "comments",
		label : "备注",
		formatter : formatorComments
	}];

	// DataSource instance
	var myDataSource = new YAHOO.util.XHRDataSource(
			"/tv/audit/filterVideos.action?filter=8&");
	myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
	myDataSource.connXhrMode = "queueRequests";
	myDataSource.responseSchema = {
		resultsList :"records",
		fields : [ "id", "vedioName", "topic", "subject", "companyID",
					"dateInput", "status", "vedioscores", "vedioscores",
					"audiencescore", "comments" ],
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
		paginator :new YAHOO.widget.Paginator({
			rowsPerPage :25,
			firstPageLinkLabel :"首页",
			lastPageLinkLabel :" 尾页",
			previousPageLinkLabel :" 上一页",
			nextPageLinkLabel :" 下一页",
			template :"{FirstPageLink}{PreviousPageLink}{PageLinks}{NextPageLink}{LastPageLink}{RowsPerPageDropdown}",
			pageReportTemplate :"Showing items {startIndex} - {endIndex} of {totalRecords}",
			rowsPerPageOptions : [25, 50,100 ]
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
	myDataSource.subscribe("requestEvent", function() { 
		$.blockUI({ message: "<h1>数据加载中......</h1>" });
	});
	myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
	myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);
	myDataTable.subscribe("renderEvent", function() {
		$.unblockUI();
		parent.resizeIframe();
	});
	
	return {
		ds :myDataSource,
		dt :myDataTable
	};
}

function checkNumeric(){
	var rate = YAHOO.util.Dom.get("audienceRate").value;
	var share = YAHOO.util.Dom.get("marketShare").value;
	
	if(isNaN(rate) || isNaN(share)){
		jAlert("输入错误，请输入数字", '提示');
		return false;
	}
	if(parseInt(rate)<0 || parseInt(rate) >100){
		jAlert("收视率应在0-100之间", '提示');
		return false;	
	}
	if(parseInt(share)<0 || parseInt(share) >100){
		jAlert("收视率应在0-100之间", '提示');
		return false;	
	}
	return true
}