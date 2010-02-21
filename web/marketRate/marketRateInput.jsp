<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>影带信息</h1>
<p>影带相关信息</p>
<table class="inputTable">

	<tr>
		<td><label>影带编号</label></td>
		<td><s:property value="video.id" /></td>
		<td><label>剧目名称</label></td>
		<td><s:property value="video.vedioName" /></td>
	</tr>
	<tr>
		<td><label>影视公司</label></td>
		<td><s:property value="video.companyID.companyName" /></td>
		<td><label>收带时间</label></td>
		<td><s:date name="video.dateComing" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td><label>栏目</label></td>
		<td><s:property value="video.topic.topicName" /></td>
		<td><label>题材</label></td>
		<td><s:property value="video.subject.subjectName" /></td>
	</tr>
	<tr>
		<td><label>状态</label></td>
		<td><s:property value="video.status.status" /></td>
		<td><label>备注</label></td>
		<td><s:property value="video.comments" /></td>
	</tr>

</table>
<h1>收视率/市场份额</h1>
<p>录入剧目收视率及市场份额</p>
<s:form action="" namespace="" method="post">
	<table>
		<tr>
			<td><label>收视率</label></td>
			<td><s:textfield cssClass="inputField" name="video.audienceRating" /> <s:hidden name="video.id" /></td>
			<td><label>市场份额</label></td>
			<td><s:textfield cssClass="inputField" name="video.marketShare" /></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><span id="go"> <span>
			<button type="button">确 定</button>
			</span> </span></td>
		</tr>
	</table>
</s:form>
</body>
</html>