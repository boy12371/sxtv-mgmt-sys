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
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
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
<script type="text/javascript"  2007  2008  2009  src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
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
	border-bottom-style: insert,
	text-align: center
}
</style>
<title>打印预览</title>
</head>
<body class="yui-skin-sam">
<s:actionerror />
<br /><br /><br />
<h4>
<script type="text/javascript">
var date = new Date();
document.write("打印日期: "+date.getFullYear()+"年"+(date.getMonth()+1)+"月"+ date.getDate()+"日");
</script>
</h4>
<a href="#" id="tableOption" style="font-size:12px">选项</a>
	<div id="colDiv" style="display:none;z-index:1002;position:absolute;background-color:white;overflow:auto;border:5px solid #999999"></div>
<s:hidden name="query" id="queryString"/>
<div id="excelTable" align="center" style="margin-top:-50"></div>

<script type="text/javascript">
	function initExcelTable() {
		var query = '<s:property value="query"/>';
		//query.replace(/amp;/g, "")
		var initRequest = encodeURI(encodeURI(YAHOO.util.Dom.get("queryString").value));
		if (initRequest.length == 0) {
			return;
		}
		var formatLink = function(elCell, oRecord, oColumn, sData) {
			var href = "<a href='./search/toVideoDetail.action?vid=";
			href += sData;
			href += "'>" + sData + "</a>";
			elCell.innerHTML = href;
		}
		var formattorDing = function(elCell, oRecord, oColumn, sData){
			elCell.innerHTML="<input type=checkbox name=ding />";
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
		},{
			key : "purchase",
			label : "购买意见",
			formatter : formatPurchase
		}, {
			key : "awarding",
			label : "获奖备选(是/否)",
			formatter : formatAward
		}, {
			key : "audiencescore",
			label : "观众投票(看/不看)",
			formatter : formatAudienceScore
		}, {
			key :"comments",
			label :"备注",
			formatter : formatorComments
		}, {
			key :"dingpian",
			label :"定片",
			formatter : formattorDing
		},{
			key :"remark",
			label : "说明"
			}];

		// DataSource instance
		var myDataSource = new YAHOO.util.XHRDataSource(
				"/tv/search/doPrintVideosReport.action?");
		myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
		myDataSource.responseSchema = {
			resultsList :"records",
			fields : [ "id", "vedioName", "topic", "subject", "companyID",
					"dateInput", "status", "marketShare", "audienceRating",
					"vedioscores","purchase","awarding", "audiencescore", "comments" ],
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
			},
			caption:"<h1>百家,都市审看记录统计</h1>"
		};

		var myDataTable = new YAHOO.widget.DataTable("excelTable",
				myColumnDefs, myDataSource, myConfigs);

		// Update totalRecords on the fly with value from server
		myDataTable.handleDataReturnPayload = function(oRequest, oResponse,
				oPayload) {
			oPayload.totalRecords = oResponse.meta.totalRecords;
			return oPayload;
		}
		var columnSet = myDataTable.getColumnSet();
		var showHideColumn = function(e){
			var column = columnSet. getColumn(this.value);
			if(this.checked){
				myDataTable.hideColumn(column);
			}else{
				myDataTable.showColumn(column);
			}
		}
		var colDiv = YAHOO.util.Dom.get("colDiv");
		var colLink = YAHOO.util.Dom.get("tableOption");
		YAHOO.util.Event.addListener(colLink,"click",function(){
			colDiv.style.display = colDiv.style.display=="block"?"none":"block";
		});
		addColumnsName = function() {
			if(colDiv.innerHTML.length==0){
				for (var i = 0; i < myColumnDefs.length; i++) {
					var column = myColumnDefs[i];
					var checkbox = document.createElement("INPUT");
					checkbox.type = "checkbox";
					checkbox.name = "colCkbox";
					checkbox.value = column.key;
					checkbox.checked = false;
					colDiv.appendChild(checkbox);
					var p = document.createElement("SPAN");
					p.innerHTML = column.label;
					colDiv.appendChild(p);
					if(i%2==1){
						var br = document.createElement("BR");
						colDiv.appendChild(br);
					}
					
					YAHOO.util.Event.addListener(checkbox,"click",showHideColumn);
					colDiv.style.display="none";
				}
			}
		};
		myDataTable.subscribe("renderEvent", function() {
			addColumnsName();
		});
		return {
			ds :myDataSource,
			dt :myDataTable
		};

	}
	YAHOO.util.Event.addListener(window, "load", initExcelTable());
</script>
</body>
</html>