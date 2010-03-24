<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
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

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>


<script type="text/javascript" src="${pageContext.request.contextPath}/audit/js/audit.js"></script>
<title>审核剧目</title>
</head>
<body class="yui-skin-sam">

<h1>剧目详细信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/tape.png" border="0">
<p>审核并点击相应按钮，确认审核操作</p>
<s:actionerror />
<s:form action="videoOperation" method="post" namespace="/audit">
	<div align="center">

	<table class="inputTable">
		<tr>
			<td><label>编号</label></td>
			<td><s:property value="vv.id" /><s:hidden name="vv.id" id="videoID" /></td>
		</tr>
		<tr>
			<td><label>剧目名称</label></td>
			<td><s:property value="vv.vedioName" /></td>

		</tr>
		<tr>
			<td><label>栏目</label></td>
			<td><s:property value="vv.subject" /></td>
		</tr>
		<tr>
			<td><label>题材</label></td>
			<td><s:property value="vv.topic" /></td>

		</tr>
		<tr>
			<td><label>影视公司</label></td>
			<td><s:property value="vv.companyID" /></td>
		</tr>
		<tr>
			<td><label>收录日期</label></td>
			<td><s:date name="vv.dateComing" format="yyyy-MM-dd" /></td>

		</tr>
		<tr>
			<td><label>状态</label></td>
			<td><s:property value="vv.status" /></td>
		</tr>
		<tr>
			<td><label>观众评价</label></td>
			<td><s:iterator value="vv.watching" var="w">
				<s:property value="#w.key" /> : <s:property value="#w.value" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</s:iterator></td>

		</tr>
		
	</table>
		<input type="hidden" name="operation">
		<div style="margin-top: 20px;">
				<s:if test="vedio.status.id == 3">
					<div id="rejectDiv"></div>				
					<script type="text/javascript">
						var rejectBtn = new YAHOO.widget.Button( {
							type :"button",
							label :"淘汰",
							id :"submitBtn",
							container :"rejectDiv"
						});
						rejectBtn.on("click",function(){
							var form = document.forms[0];
							form.operation.value="7";
							form.submit();
		
							});
					</script>
				</s:if>
				<s:elseif test="vedio.status.id == 2">
					<span id="passDiv"></span>
					<span id="rejectDiv"></span>
					<script type="text/javascript">
						var passBtn = new YAHOO.widget.Button( {
							type :"button",
							label :"通 过",
							id :"passBtn",
							container :"passDiv"
						});
						passBtn.on("click",function(){
							var form = document.forms[0];
							form.operation.value="3";
							form.submit();
						
							});
						
						var rejectBtn = new YAHOO.widget.Button( {
							type :"button",
							label :"淘 汰",
							id :"rejectBtn",
							container :"rejectDiv"
						});
						rejectBtn.on("click",function(){
							var form = document.forms[0];
							form.operation.value="7";
							form.submit();
						
							});
					</script>
				</s:elseif>
				<s:else>
					<div id="passDiv"></div>
					<script type="text/javascript">
						var passBtn = new YAHOO.widget.Button( {
							type :"button",
							label :"通过",
							id :"passBtn",
							container :"passDiv"
						});
						passBtn.on("click",function(){
							var form = document.forms[0];
							form.operation.value="3";
							form.submit();
						
							});
					</script>
				</s:else>
		</div>
	</div>
</s:form>
<div></div>
<h1>评分信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/score.png" border="0">
<p>评分人员对此剧目的评分情况</p>

<div id="dynamicdata" align="center"></div>

<script type="text/javascript">
	YAHOO.util.Event.addListener(window, "load", initScoreDataTable());
	var submitToPreArrange = new YAHOO.widget.Button( {
		type :"submit",
		label :"确定",
		id :"submitBtn",
		container :"submit"
	});
</script>
</body>
</html>