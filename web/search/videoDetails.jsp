<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>影带信息</title>
</head>
<body>


<h1>影带信息</h1>
<p>影带详细信息</p>

<s:actionerror/>
<table>
	<tr>
		<td><label>影带编号</label></td>
		<td> <s:property value="video.id"/>
		</td>
		<td><label>剧目名称</label></td>
		<td><s:property value="video.vedioName"/></td>
	</tr>
	<tr>
		<td><label>影视公司</label></td>
		<td><s:property value="video.companyID.id"/></td>
		<td><label>送带日期</label></td>
		<td><s:date name="video.dateComing"/></td>
	</tr>
	<tr>
		<td><label>栏目</label></td>
		<td><s:property value="video.subject.subjectName"/></td>
		<td><label>题材</label></td>
		<td><s:property value="video.topic.topicName"/></td>

	</tr>
	<tr>
		<td><label>市场份额</label></td>
		<td><s:property value="video.marketShare"/></td>
		<td><label>收视率</label></td>
		<td><s:property value="video.audienceRating"/></td>

	</tr>
	<tr>
		<td><label>状态  </label></td>
		<td><s:property value="video.status.status"/></td>
		<td><label>备注</label></td>
		<td><s:property value="video.comments"/></td>

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
		<span id="go" class="yui-button yui-push-button">
        <span class="first-child">
            <button type="button"> 确 定 </button>
        </span>
        </span>
</td>
	</tr>
</table>


</body>
</html>