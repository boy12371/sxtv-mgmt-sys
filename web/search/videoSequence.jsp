<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sx:head extraLocales="UTF-8"/>
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

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />


<title>查询影带信息</title>
</head>
<body class="yui-skin-sam">
<h1>影带排名</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/Search.png" border="0">
<p>填写或选择相关信息，点击查询，搜索影带信息及排名</p>
<div align="center">
<table>	
	<tr>
		<td><label>影视公司</label></td>
		<td><s:select cssClass="selectField" list="comList" listKey="id" listValue="companyName" id="vcompany" name="video.companyID.id" /></td>
		<td><label>题材</label></td>
		<td><s:select cssClass="selectField" list="topList" listKey="id" listValue="topicName" id="vtopic" name="video.topic.id" /></td>
	</tr>
	<tr>
		<td><label>栏目</label></td>
		<td><s:select cssClass="selectField" list="subList" listKey="id" listValue="subjectName" id="vsubject" name="video.subject.id" /></td>
		<td><label>状态</label></td>
		<td> 已播 </td>

	</tr>
	<tr>
		<td><label>从</label></td>
		<td><sx:datetimepicker name="startDate" toggleType="explode" displayFormat="yyyy-MM-dd" id="startDate" value="%{'today'}" language="UTF-8" weekStartsOn="0"/></td>
		<td><label>至</label></td>
		<td><sx:datetimepicker name="endDate" toggleType="explode" displayFormat="yyyy-MM-dd" id="endDate" value="%{'today'}" language="UTF-8" weekStartsOn="0"/></td>

	</tr>
	<tr>
		<td colspan="4" align="center">
		<span id="go" class="yui-button yui-push-button"> <span class="first-child">
		<button type="button">查 询</button>
		</span> </span></td>
	</tr>
</table>
</div>
<h1>影带排名列表</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/tape.png" border="0">
<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
<a href="#" id="tableOption" style="font-size:12px">选项</a>
	<div id="colDiv" style="display:none;z-index:1002;position:absolute;background-color:white;overflow:auto;border:5px solid #999999"></div>
<div id="printBtn" align="right"></div>
<div id="dynamicdata" align="center"></div>

<script type="text/javascript">
	YAHOO.util.Event.addListener(window, "load", initOrderDataTable());
</script>
</body>
</html>