<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
<s:form action="doUpdateUser" namespace="/sys" method="post">
	<div align="center">
	<table>

		<tr>
			<td><label>用户名：</label></td>
			<td><label><s:property value="user.userName" /></label></td>
			<td><label>密码：</label></td>
			<td><label>********</label></td>
		</tr>
		<tr>
			<td colspan="1"><label>权限：</label></td>
			<td colspan="3"><label>&nbsp;</label></td>
		</tr>
		<tr>
			<td colspan="4">
			<s:iterator value="roleList" var="role" status="st">
				<s:iterator value="user.roles" var="r">
					<s:if test="%[#role.id==r.id]">
						==
										
					</s:if>
					<s:else>
						**
					</s:else>
						
			</s:iterator>
				

			</s:iterator></td>


		</tr>
		<tr>
			<td colspan="4"><s:iterator value="user.roles" var="r">
				<s:property value="#r.id" />
			</s:iterator></td>
		</tr>
		<tr>
			<td colspan="4"><s:hidden name="operation" /><s:hidden name="user.id" /> <s:submit value="更新"
				id="updateUserRole" onclick="executOperations(this.id)" /><s:if test="user.status==1">
				<s:submit value="禁用" id="disableUser" onclick="executOperations(this.id)" />
			</s:if><s:else>
				<s:submit value="启用" id="enableUser" onclick="executOperations(this.id)" />
			</s:else> <s:submit value="重置密码" id="resetPwd" onclick="executOperations(this.id)" /></td>
		</tr>
	</table>
	</div>
</s:form>
</body>
</html>