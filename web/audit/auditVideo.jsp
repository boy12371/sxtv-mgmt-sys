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


<script type="text/javascript" src="${pageContext.request.contextPath}/audit/js/audit.js"></script>
<title>审核剧目</title>
</head>
<body class="yui-skin-sam">
<s:actionerror />
<h1>剧目详细信息</h1>
<p>点击下拉菜单查看待审剧目，单击剧目查看详细信息</p>
<s:form action="videoOperation" method="post" namespace="/audit">
	<table>

		<tr>
			<td><label>编号</label></td>
			<td><s:property value="vv.vedioID" /><s:hidden name="vv.vedioID" id="videoID" /></td>
			<td><label>剧目名称</label></td>
			<td><s:property value="vv.name" /></td>

		</tr>
		<tr>
			<td><label>栏目</label></td>
			<td><s:property value="vv.subject" /></td>
			<td><label>题材</label></td>
			<td><s:property value="vv.topic" /></td>

		</tr>
		<tr>
			<td><label>影视公司</label></td>
			<td><s:property value="vv.company" /></td>
			<td><label>收录日期</label></td>
			<td><s:date name="vv.dateComing" format="yyyy-MM-dd" /></td>

		</tr>
		<tr>
			<td><label>状态</label></td>
			<td><s:property value="vv.status" /></td>
			<td><label>观众评价</label></td>
			<td><s:iterator value="vv.watching" var="w">
				<s:property value="#w.key" /> : <s:property value="#w.value" />
			</s:iterator></td>

		</tr>
		<tr height="20px">
			<td colspan="4"></td>

		</tr>
		<tr>
			<td colspan="4">			
			<s:radio list="#{3:'通 过',4:'修 改',7:'退 回'}" name="operation"></s:radio>
			</td>

		</tr>

		<tr>
			<td colspan="4"><s:submit value="确定"/></td>
		</tr>

	</table>
</s:form>
<div></div>
<h1>评分信息</h1>
<p>点击下拉菜单查看待审剧目，单击剧目查看详细信息</p>


<div id="dynamicdata" align="center"></div>


<script type="text/javascript">
	YAHOO.util.Event.addListener(window, "load", initScoreDataTable());
</script>
</body>
</html>