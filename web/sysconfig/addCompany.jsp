<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影视公司管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
</head>
<body class="yui-skin-sam">


<h1>公司信息</h1>
<p>编辑并添加公司信息</p>
<s:actionerror />

<s:form action="doAddCompany" namespace="/sys" method="post">
	<div align="center">
	<table>
		<tr>
			<td><label>公司名称：</label></td>
			<td><s:textfield name="company.companyName" /><s:hidden name="company.id" /></td>
			<td><label>注册号：</label></td>
			<td><s:textfield name="company.registrationNo" /></td>
		</tr>
		<tr>
			<td><label>联系人：</label></td>
			<td><s:textfield name="company.contactPerson" /></td>
			<td><label>电话：</label></td>
			<td><s:textfield name="company.phone" /></td>
		</tr>
		<tr>
			<td><label>备注：</label></td>
			<td><s:textarea name="company.comments" cols="20" rows="2"></s:textarea></td>

			<td><label></label></td>
			<td></td>
		</tr>

		<tr>
			<td colspan="4" align="center"><span id="go" class="yui-button yui-push-button"> <span
				class="first-child"> <s:submit value="添加" /> </span> </span></td>
		</tr>
	</table>
	</div>

</s:form>
</body>
</html>