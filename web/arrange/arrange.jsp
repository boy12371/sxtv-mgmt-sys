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
<script type="text/javascript" src="${pageContext.request.contextPath}/arrange/js/arrange.js"></script>
</head>
<body class="yui-skin-sam">

<h1>编排信息</h1>
<s:actionmessage/>
<s:actionerror/>

<p>未编排影带</p>
<br />
<div id="unArrangeTableDiv" align="center"></div>

<p>已编排影带</p>
<br />
<div id="selectMonthDiv" style="width: 800px; height: 30px; margin-left: 68px;">
	<s:select list="monthList" listKey="key" listValue="value" 
		id="selectMonth" 
		onchange="selectMonthFunc(this)"
		cssStyle="float:right;width:100px;"
	/>
</div>
<div id="arrangeTableDiv" align="center"></div>
<div align="center">
	<div style="margin-top:30px;" id="submitBtnDiv"></div>
</div>
<s:form action="doArrange" namespace="/arrange" >
	<s:hidden name="newResult" id="newResult"/>
</s:form>
<script type="text/javascript">
	initUnArrangeTable();

	var submitBtn = new YAHOO.widget.Button({  
		label: "提&nbsp;&nbsp;交",  
		id: "submitBtn",  
		container: "submitBtnDiv" }
		); 
	submitBtn.on("click",submitAction);

	YAHOO.util.Event.addListener(window, "load", initArrangeReorderEvent);
</script>

</body>
</html>