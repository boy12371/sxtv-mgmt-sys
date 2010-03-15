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
		<td><label>开始日期</label></td>
		<td><sx:datetimepicker cssClass="inputField" displayFormat="yyyy-MM-dd" name="startDateStr" toggleType="explode" value="startDateStr"/></td>
		<td><label>结束日期</label></td>
		<td>
		<sx:datetimepicker cssClass="inputField" displayFormat="yyyy-MM-dd" name="endDateStr" value="endDateStr" toggleType="explode" /></td>

<!-- 	<td><label>姓名</label></td>
		<td><input class="inputField" type="text" /></td>  -->
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