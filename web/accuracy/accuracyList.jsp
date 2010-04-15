<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/accuracy/js/accuracy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
</head>
<body class="yui-skin-sam">

<h1>准确度信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/score.png" border="0">
<s:actionmessage/>
<s:actionerror/>

<p>查看打分人员在一段时间的准确度</p>
<br />

<div id="search" align="center">
<table class="searchTable">
	<tr>
		<td><label>选择查询年月：</label></td>
		<td><s:select id="selectYear" list="years" value="defYear" cssClass="selectField" cssStyle="width:150px;margin:0px;"/></td>
		<td><s:select id="selectMonth" 
			list="#{'01':'1月','02':'2月','03':'3月','04':'4月','05':'5月','06':'6月','07':'7月','08':'8月','09':'9月','10':'10月','11':'11月','12':'12月'}"
			value="defMonth" 
			cssClass="selectField" cssStyle="width:150px;margin:0px;"/>
		</td>
		<td>
			<div id="searchBtnDiv"></div>
		</td>
	</tr>
</table>
</div>

<div id="accuracyTableDiv" align="center"></div>

<script language="JavaScript">
var searchBtn = new YAHOO.widget.Button({  
		label: "查&nbsp;&nbsp;询",  
		id: "searchBtn",  
		container: "searchBtnDiv" }
		); 

searchBtn.on("click",filterFunc);

initAccuracyTable();
</script>

</body>
</html>