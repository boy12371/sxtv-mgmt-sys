<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/dragdrop/dragdrop-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/arrange/js/arrangedHistory.js"></script>
</head>
<body class="yui-skin-sam">

<h1>编排信息</h1>
<s:actionmessage/>
<s:actionerror/>

<p>编排影带历史</p>
<br />

<div id="selectDateDiv" style="width: 800px; height: 30px; margin-left: 68px;">
	<table><tr>
	<td><label>选择查询年月：</label></td>
	<td><select id="selectYear" class="selectField" onchange="selectYearFunc(this)" style="width:100px;margin:0px;"></select></td>
	<td><select id="selectMonth" class="selectField" onchange="selectMonthFunc(this)" style="width:100px;margin:0px;"></select></td>
	</tr></table>
</div>
<div id="historyDiv" align="center"></div>
<s:form action="" namespace="/arrange" >
	<s:hidden name="month" id="month"/>
</s:form>
<script type="text/javascript">
	var firstArrangedDate = "<s:property value='firstArrangedDate'/>";
	var nowDate = "<s:property value='nowDate'/>";
	initSelectElements();
</script>

</body>
</html>