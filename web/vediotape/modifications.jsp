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

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/vediotape/js/vediotape.js"></script>

<title>影带信息</title>
</head>
<body class="yui-skin-sam">

<s:actionerror />
<s:actionmessage />


<h1>查看或修改影带信息</h1>
<p>输入剧目编号或名称搜索剧目</p>
<s:form id="searchForm" action="searchVideoByNameOrIDForModification" namespace="/vedio">
	<div align="center">
	<table>
		<tr>
			<td><label>影带编号</label></td>
			<td><input class="inputField" type="text" name="vid" id="vid" /><s:hidden name="optionName" value="modification" /></td>
			<td><label>剧目名称</label></td>
			<td><input class="inputField autoComplete" type="text" id="searchinput" name="vname" />
			<div id="searchcontainer"></div>
			</td>
			<td colspan="4" align="center">
			<div id="goSearch"></div>
			</td>
		</tr>

	</table>
	</div>
	<script type="text/javascript">
	YAHOO.example.Centered = autoCompleteVideoName();
	</script>
</s:form>

<s:form id="tableForm" namespace="/vedio" action="doModificationBatch" method="post">
	<h1>已通过影带列表</h1>
	<p>选择并点击按钮，使影带进入待编排状态</p>
	<div id="makeToArrange" align="center"></div>
	<div align="center">
	<div id="submitToPreArrange"></div>
	</div>


	<h1>待排影带列表</h1>
	<p>选择并点击按钮，使影带进入通过状态</p>
	<div id="makeToPass" align="center"></div>
	<div align="center">
	<div id="submitToPass"></div>
	</div>
	<script type="text/javascript">
	YAHOO.util.Event.addListener(window, "load", initButtons);
	</script>
</s:form>
<script type="text/javascript">
	YAHOO.util.Event.addListener(window, "load", initToArrangeTable);
	YAHOO.util.Event.addListener(window, "load", initToPassTable);
	
</script>

</body>
</html>