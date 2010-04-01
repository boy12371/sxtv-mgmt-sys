<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/search/js/search.js"></script>

<style type="text/css">
.yui-skin-sam tr.yui-dt-odd td.yui-dt-asc, .yui-skin-sam tr.yui-dt-odd td.yui-dt-desc{
	background-color: #FFFFFF;
}
.yui-skin-sam tr.yui-dt-even td.yui-dt-asc, .yui-skin-sam tr.yui-dt-even td.yui-dt-desc{
	background-color: #FFFFFF;
}
.yui-skin-sam tr.yui-dt-odd{
	background-color: #FFFFFF;
}
.yui-skin-sam .yui-dt th{
	border-left: 1px solid buttonhighlight; 
    border-top: 1px solid buttonhighlight; 
    border-bottom: 1px solid buttonshadow; 
    border-right: 1px solid buttonshadow;
    background-color: #ece9d8;
    background-image: none;
	cursor: pointer;
	padding: 4px;
	font-weight: bold;
}
.yui-skin-sam .yui-dt td {
	border:clear;
	border-color:#CBCBCB;
	border-style:solid;
	border-width:1px;
	border-bottom-style: insert
}
</style>
<title>打印预览</title>
</head>
<body class="yui-skin-sam">
<s:actionerror />
<h1>
 <s:property value="sc.startDate"/>——<s:property value="sc.endDate"/> 
</h1>
<p>&nbsp;</p>

<div id="excelTable" align="center"></div>

<script type="text/javascript">
	function initExcelTable() {
		var query = '<s:property value="query"/>';
		var initRequest = query.replace(/amp;/g, "");
		if (initRequest.length == 0) {
			return;
		}
		var formatLink = function(elCell, oRecord, oColumn, sData) {
			var href = "<a href='./search/toVideoDetail.action?vid=";
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
		var formatScroes = function(elCell, oRecord, oColumn, sData) {
			if (sData.length == 0) {
				elCell.innerHTML = "0";
			} else {
				var avgScore = 0;
				var total = 0;
				for ( var i = 0; i < sData.length; i++) {
					total += sData[i].score;
				}
				var s = (total / sData.length).toString();
				s = s.substring(0, s.indexOf(".") + 3);
				elCell.innerHTML = s;
			}
		}
		var formatAudienceScore = function(elCell, oRecord, oColumn, sData) {
			if (sData.length == 0) {
				elCell.innerHTML = "0/0";
			} else {
				var yes = 0;
				var no = 0;
				for ( var i = 0; i < sData.length; i++) {
					if (sData[i].result == 1) {
						yes += 1;
					} else {
						no += 1;
					}
				}
				elCell.innerHTML = yes + "/" + no;
			}
		}
		// Column definitions
		var myColumnDefs = [ {
			key :"id",
			label :"编号"
		}, {
			key :"vedioName",
			label :"剧目名称"
		}, {
			key :"topic",
			label :"题材",
			formatter :formatTopic
		}, {
			key :"subject",
			label :"栏目",
			formatter :formatSubject
		}, {
			key :"companyID",
			label :"影视公司",
			formatter :formatCompany
		}, {
			key :"dateInput",
			label :"收带日期",
			formatter :formatDate
		}, {
			key :"status",
			label :"状态",
			formatter :formatStatus
		}, {
			key :"marketShare",
			label :"市场份额"
		}, {
			key :"audienceRating",
			label :"收视率"
		}, {
			key :"vedioscores",
			label :"综合平均分",
			formatter :formatScroes
		}, {
			key :"audiencescore",
			label :"观众投票(看/不看)",
			formatter :formatAudienceScore
		}, {
			key :"comments",
			label :"备注"
		} ];

		// DataSource instance
		var myDataSource = new YAHOO.util.XHRDataSource(
				"/tv/search/doPrintVideosReport.action?");
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		myDataSource.responseSchema = {
			resultsList :"records",
			fields : [ "id", "vedioName", "topic", "subject", "companyID",
					"dateInput", "status", "marketShare", "audienceRating",
					"vedioscores", "audiencescore", "comments" ],
			metaFields : {
				totalRecords :"totalRecords" // Access to value in the server
		// response
		}
		}
		// DataTable configuration
		var myConfigs = {
			initialRequest :initRequest,
			initialLoad :true,
			dynamicData :true, // Enables dynamic server-driven data
			sortedBy : {
				key :"dateInput",
				dir :YAHOO.widget.DataTable.CLASS_ASC
			}
		};

		var myDataTable = new YAHOO.widget.DataTable("excelTable",
				myColumnDefs, myDataSource, myConfigs);

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
	YAHOO.util.Event.addListener(window, "load", initExcelTable());
</script>
</body>
</html>