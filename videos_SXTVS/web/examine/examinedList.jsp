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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/examine.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/examine/js/examinedList.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
</head>
<body class="yui-skin-sam">
<%@ include file="/common/errorMsg.jsp" %>
<h1>打分信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/exam.png" border="0">
<p>查看已打分影带</p>

<s:actionmessage/>
<s:actionerror/>
<br />

<div id="searchTape" align="center">
<table class="searchTable">
	<tr>
		<td><label>影带编号</label></td>
		<td><input class="inputField" type="text" name="vid" id="vid" /></td>
		<td><label>剧目名称</label></td>
		<td>
			<input class="inputField autoComplete" type="text" id="searchinput" name="vname" style="width: 200px" />
			<div id="searchcontainer"></div>
		</td>
		<td>
			<div id="searchBtnDiv"></div>
		</td>
	</tr>
</table>
</div>

<div id="dynamicdata" align="center"></div>
<script type="text/javascript">
	YAHOO.example.Centered = autoCompleteVideoName();
	var searchBtn = new YAHOO.widget.Button({  
	label: "搜&nbsp;&nbsp;索",  
	id: "searchBtn",  
	container: "searchBtnDiv" }
	); 
	searchBtn.on("click",filterFunc);
</script>

</body>
</html>