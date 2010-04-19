<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head extraLocales="UTF-8"/>
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

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/audit/js/audit.js"></script>
<title>待审剧目</title>
</head>
<body class="yui-skin-sam">

<s:actionerror />
<s:actionmessage />


<h1>查看或修改影带信息</h1>
<img class="pageImage"
	src="${pageContext.request.contextPath}/common/images/Search.png"
	border="0" />
<p>输入剧目编号或名称搜索剧目</p>
<s:form id="searchForm" action="findVideoByNameOrID" namespace="/audit">
	<div align="center">

	<table>
		<tr>
			<td><label>影带编号</label></td>
			<td><input class="inputField" type="text" name="vid" id="vid" /><s:hidden
				name="optionName" value="auditing" /></td>
			<td><label>剧目名称</label></td>
			<td><input class="inputField autoComplete" type="text"
				id="searchinput" name="vname" style="width: 200px" />
			<div id="searchcontainer"></div>
			</td>
			<td colspan="4" align="center">
			<div id="searchBtnDiv"></div>
			</td>
		</tr>

	</table>
	</div>
	<script type="text/javascript">
	YAHOO.example.Centered = autoCompleteVideoName();
	var searchBtn = new YAHOO.widget.Button( {
		label :"搜索",
		id :"searchBtn",
		type :"submit",
		container :"searchBtnDiv"
	});
</script>
</s:form>


<h1>待审剧目</h1>
<img class="pageImage"
	src="${pageContext.request.contextPath}/common/images/tape.png"
	border="0">
<p>点击下拉菜单查看待审剧目，单击剧目查看详细信息</p>

<select class="selectField" id="filter">
	<option value="0">All</option>
	<option value="2" selected="selected">待审剧目</option>
	<option value="3">通过剧目</option>
	<option value="5">待排剧目</option>
	<option value="7">淘汰剧目</option>
	<!-- option value="5">重审剧目</option-->
</select><br/>
<a href="#" id="tableOption" style="font-size:12px">选项</a>
	<div id="colDiv" style="display:none;z-index:1002;position:absolute;background-color:white;overflow:auto;border:5px solid #999999"></div>

<div id="dynamicdata" align="center"></div>

<div id="printDate" style="display: none">
<h1 class="popupWindowTitle">请选择日期</h1>
<table>
	<tr>
		<td>从:<sx:datetimepicker cssClass="inputField"
			displayFormat="yyyy-MM-dd" id="fromDate" toggleType="explode"
			value="today" toggleType="explode" language="UTF-8" weekStartsOn="0"/></td>
		<td>至:<sx:datetimepicker cssClass="inputField"
			displayFormat="yyyy-MM-dd" id="toDate" toggleType="explode"
			value="today" toggleType="explode" language="UTF-8" weekStartsOn="0"/></td>
	</tr>
	<tr>
		<td colspan="2"><input type="button" name="demo1" id="yes"
			value="确认" /> <input type="button" name="cancel" id="cancel"
			value="取消" /></td>

	</tr>

</table>
</div>

<script type="text/javascript">
	YAHOO.util.Event.addListener(window, "load", initDataTable);
</script>


</body>
</html>