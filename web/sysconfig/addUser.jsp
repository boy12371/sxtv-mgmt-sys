<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sx:head />
<title>添加用户</title>
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
<body>

<h1>员工用户</h1>
<p>编辑并添加用户信息</p>
<s:actionerror />
<s:form action="doAddUser" namespace="/sys" method="post">
	<div align="center">
	<table>
		<tr>
			<td><label>用户名：</label></td>
			<td><s:textfield name="user.userName" /></td>
			<td><label>员工：</label></td>
			<td><s:select name="user.employee.id" list="empList" listKey="id" listValue="name" /></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td><s:password name="user.userPass" /></td>
			<td><label>重复密码：</label></td>
			<td><s:password name="pwdRepeat" /></td>
		</tr>
		<tr>
			<td colspan="1"><label>权限：</label></td>
			<td colspan="3"><label>&nbsp;</label></td>
		</tr>

		<tr>
			<td colspan="4">
			 
<s:iterator value="roleList" status="stat" id="role" >
<s:checkbox name="user.roles[%{#stat.index}].id" value="false" fieldValue="%{#role.id}" /><s:property value="name"/><br />
</s:iterator>
</td>
		</tr>
		<tr>
			<td colspan="4">
				<s:submit value="提交" />
			 </td>
		</tr>
	</table>
	</div>
</s:form>
</body>
</html>