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

<title>查看或修改影带信息</title>
</head>
<body>

<s:actionerror/>
<s:actionmessage/>
<h1>查看或修改影带信息</h1>
<p>输入剧目编号或名称搜索剧目</p>
<s:form id="searchForm" action="searchVideoByNameOrID" namespace="/vedio">
<table>
	<tr>
		<td><label>影带编号</label></td>
		<td><input type="text" name="vid" id="vid" /></td>
		<td><label>剧目名称</label></td>
		<td><input type="text" id="searchinput" name="vname" style="width: 200px" />
		<div id="searchcontainer">
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
		<span id="go" class="yui-button yui-push-button"> <span class="first-child">
		<input type="submit" value="搜索" />
		</span> </span></td>
	</tr>
</table>

<script type="text/javascript">
	YAHOO.example.Centered = autoCompleteVideoName();
</script>
</s:form>

<s:if test="vedio!=null">
<h1>影带信息</h1>
<p>查看或编辑剧目信息并保存</p>
<s:form id="updateForm" action="updateVideoInfo" namespace="/vedio" method="post">
	<table>
		<tr>
			<td><label>影带编号</label></td>
			<td><s:property value="vedio.id" /> <s:hidden name="vedio.id" /></td>
			<td><label>剧目名称</label></td>
			<td><s:textfield name="vedio.vedioName" /></td>
		</tr>
		<tr>
			<td><label>影视公司</label></td>
			<td><s:select list="comList" listKey="id" listValue="companyName" name="vedio.companyID.id" /></td>
			<td><label>收带时间</label></td>
			<td><s:date name="vedio.dateComing" format="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td><label>栏目</label></td>
			<td><s:select list="subList" listKey="id" listValue="subjectName" id="vsubject" name="vedio.subject.id" /></td>
			<td><label>题材</label></td>
			<td><s:select list="topList" listKey="id" listValue="topicName" id="vtopic" name="vedio.topic.id" /></td>
		</tr>
		<s:if test="vedio.audienceRating != null && vedio.audienceRating !=0">
			<tr>
				<td><label>收视率</label></td>
				<td><s:textfield name="vedio.audienceRating" /></td>
				<td><label>市场份额</label></td>
				<td><s:textfield name="vedio.marketShare" /></td>
			</tr>
		</s:if>
		<tr>
			<td><label>状态</label></td>
			<td><s:property value="vedio.status.status" /></td>
			<td><label>备注</label></td>
			<td><s:textarea name="vedio.comments"></s:textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><span id="go" class="yui-button yui-push-button"> <span class="first-child">
			<input type="submit" value="确定">
			</span> </span></td>
		</tr>
	</table>
</s:form>
</s:if>
</body>
</html>