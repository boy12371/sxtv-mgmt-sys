<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<title>Insert title here</title>
</head>


<body class="yui-skin-sam">
<s:actionerror />
<h1>影带信息</h1>
<p>确定并点击按钮，影带将被重新审核</p>
<s:form action="modificationFinish" method="post" namespace="/vedio">
	<table class="inputTable">
		<tr>
			<td><label>编号</label></td>
			<td><s:property value="vv.vedioID" /><s:hidden name="vv.vedioID" /></td>
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
				<s:if test="vv.status==3">
					<s:hidden name="vv.vedioID" />
					<s:submit value="修改完成"/>
				</s:if>				
				<s:else>
					<s:hidden name="vv.vedioID" />
					<s:submit value="撤销编排"/>
				</s:else>
				

			</td>
		</tr>

	</table>
</s:form>
</body>
</html>