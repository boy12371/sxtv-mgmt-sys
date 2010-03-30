<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/search/js/search.js"></script>
<title>打印预览</title>
</head>
<body>
<s:actionerror />
<p id="query"><s:property value="query" /></p>

<table cellpadding="4" cellspacing="0" border="1" class="winUI"
	width="90%">
	<thead>
		<td>编号</td>
		<td>剧目名称</td>
		<td>题材</td>
		<td>栏目</td>
		<td>影视公司</td>
		<td>状态</td>
		<td>综合平均分</td>
		<td>观众投票(看/不看)</td>
		<td>备注</td>
	</thead>
	<tbody>
		<s:iterator value="videos" var="v">

			<tr>
				<td><s:property value="#v.id" /></td>
				<td><s:property value="#v.vedioName" /></td>
				<td><s:property value="#v.topic.topicName" /></td>
				<td><s:property value="#v.subject.subjectName" /></td>
				<td><s:property value="#v.companyID.companyName" /></td>
				<td><s:property value="#v.status.status" /></td>
				<td>
					<s:set var="avg" value="0"/>
					<s:iterator value="v.vedioscores" var="s">
						<s:set var="avg" value="%{#+=s.score}"/>
					</s:iterator>
					
				</td>
				<td><s:property value="#v.id" /></td>
				<td><s:property value="#v.id" /></td>
			</tr>
		</s:iterator>
	</tbody>
	<tfoot>
		<td colspan="5" style="text-align: right">共 5 行</td>
	</tfoot>
</table>
<div id="excelTable"></div>

<script type="text/javascript">
	function initExcelTable() {
		var initRequest = YAHOO.util.Dom.get("query").innerHTML;
		alert(initRequest);
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
			key :"marketShare",
			label :"市场份额",
			sortable :true
		}, {
			key :"audienceRating",
			label :"收视率",
			sortable :true
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
				"http://localhost:8080/tv/search/doPrintVideosReport.action?");
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
			initialRequest :"sort=dateInput&dir=asc&startIndex=-1&results=0&sc.status.id=0&sc.startDate=2010-03-30&sc.endDate=2010-03-30",
			initialLoad :false,
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

		myDataTable.subscribe("rowMouseoverEvent",
				myDataTable.onEventHighlightRow);
		myDataTable.subscribe("rowMouseoutEvent",
				myDataTable.onEventUnhighlightRow);

		return {
			ds :myDataSource,
			dt :myDataTable
		};

	}

	YAHOO.util.Event.addListener(window, "load", initExcelTable());
</script>
</body>
</html>