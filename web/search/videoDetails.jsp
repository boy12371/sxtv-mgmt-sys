<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>




<title>影带信息</title>
</head>
<body class="yui-skin-sam">

<div width="65%">
<h1>影带信息</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/tape.png" border="0" />
<p>影带详细信息</p>

<s:actionerror />
<div align="center">
<table class="inputTable">
	<tr>
		<td><label>影带编号</label></td>
		<td><s:property value="video.id" />
	</tr>
	<tr>
		</td>
		<td><label>剧目名称</label></td>
		<td><s:property value="video.vedioName" /></td>
	</tr>
	<tr>
		<td><label>影视公司</label></td>
		<td><s:property value="video.companyID.id" /></td>
	</tr>
	<tr>
		<td><label>送带日期</label></td>
		<td><s:date name="video.dateComing" /></td>
	</tr>
	<tr>
		<td><label>栏目</label></td>
		<td><s:property value="video.subject.subjectName" /></td>
	</tr>
	<tr>
		<td><label>题材</label></td>
		<td><s:property value="video.topic.topicName" /></td>

	</tr>
	<tr>
		<td><label>市场份额</label></td>
		<td><s:property value="video.marketShare" /></td>
	</tr>
	<tr>
		<td><label>收视率</label></td>
		<td><s:property value="video.audienceRating" /></td>

	</tr>
	<tr>
		<td><label>状态 </label></td>
		<td><s:property value="video.status.status" /></td>
	</tr>
	<tr>
		<td><label>备注</label></td>
		<td><s:property value="video.comments" /></td>

	</tr>
	<!-- 
	<tr>
		<td><label>收录人</label></td>
		<td><s:select list="subList" listKey="id" listValue="subjectName" id="vsubject" /></td>
		<td><label>送带日期</label></td>
		<td><textarea name="textfield" id="vcomments"></textarea></td>

	</tr>
	
	<tr>
		<td><label>审核人  </label></td>
		<td><s:select list="subList" listKey="id" listValue="subjectName" id="vsubject" /></td>
		<td><label>审核日期</label></td>
		<td><textarea name="textfield" id="vcomments"></textarea></td>

	</tr>
	 -->

	<tr>
		<td colspan="4" align="center">
			<div id="btnDiv"></div>
		</td>
	</tr>
</table>
<script type="text/javascript">
	var btn = new YAHOO.widget.Button( {
		type :"button",
		label :"关闭",
		id :"submitBtn",
		container :"btnDiv"
	});
	btn.on("click", function() {
		window.close();
	});
</script></div>
</div>
</body>
</html>