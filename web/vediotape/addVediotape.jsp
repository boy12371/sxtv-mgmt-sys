<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

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


<script type="text/javascript" src="${pageContext.request.contextPath}/vediotape/js/vediotape.js"></script>



 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" /> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script> 


<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
</head>

<body class="yui-skin-sam">

<s:form id="form1" name="form1" method="post" action="doAddingVedio" namespace="/vedio">
<s:actionerror/>
<s:actionmessage/>
<h1><img src="${pageContext.request.contextPath}/common/images/2.png" border="0">输入影带信息</h1>
<p>调描条形码并填写或选择其他相关信息</p>

<div align="center">
<table class="inputTable">
	<tr>
		<td><label>影带编号</label></td>
		<td><input class="inputField" type="text" name="textfield" id="vid" /></td>
</tr>
			<tr>
		<td><label>剧目名称</label></td>
		<td><input class="inputField" type="text" name="textfield" id="vname"/></td>
	</tr>
	<tr>
		<td><label>影视公司</label></td>
		<td><s:select cssClass="selectField" list="comList" listKey="id" listValue="companyName" id="vcompany" /></td>
		</tr>
			<tr><td><label>题材</label></td>
		<td><s:select cssClass="selectField" list="topList" listKey="id" listValue="topicName" id="vtopic" /></td>
	</tr>
	<tr>
		<td><label>栏目</label></td>
		<td><s:select cssClass="selectField" list="subList" listKey="id" listValue="subjectName" id="vsubject" /></td>
		</tr>
			<tr><td><label>收带日期</label></td>
		<td><sx:datetimepicker cssClass="inputField" displayFormat="yyyy-MM-dd" id="vdateComing" toggleType="explode" value="today"/></td>

	</tr>
	<tr>
		<td><label>备注</label></td>
		<td><textarea class="inputField" name="textfield" id="vcomments"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<span id="go" class="yui-button yui-push-button">
        <span class="first-child">
            <button type="button"> 确 定 </button>
        </span>
        </span>
</td>
	</tr>
</table>
</div>
<s:hidden name="jasonDataString" id="jasonDataString"/>
<h1><img src="${pageContext.request.contextPath}/common/images/tape.png" border="0">影带信息</h1>
<p>单击表格可进行编辑，单击右键可删除</p>
<div id="cellediting" align="center"></div>
<br />
<div align="center">
<span align="center" id="submit" class="yui-button yui-push-button">
        <span class="first-child">
            <button type="button"> 添加 </button>
        </span>
</span>
</div>
</s:form>

<script type="text/javascript">

YAHOO.util.Event.addListener(window, "load", initDataTable());
</script>

</body>

</html>