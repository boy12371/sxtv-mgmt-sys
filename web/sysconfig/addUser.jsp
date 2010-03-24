<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>添加用户</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
</head>
<body class="yui-skin-sam">

<h1>员工用户</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/user.png" border="0">
<p>编辑并添加用户信息</p>
<s:actionerror />
<s:form action="doAddUser" namespace="/sys" method="post">
	<div align="center">
	<table class="inputTable">
		<tr>
			<td><label>用户名：</label></td>
			<td><s:textfield cssClass="inputField" name="user.userName" /></td>
			</tr>
		<tr><td><label>员工：</label></td>
			<td><s:select cssClass="selectField" name="user.employee.id" list="empList" listKey="id" listValue="name" /></td>
		</tr>
		<tr>
			<td><label>密码：</label></td>
			<td><s:password cssClass="inputField" name="user.userPass" /></td>
			</tr>		
		<tr>
			<td colspan="1"><label>权限：</label></td>
			<td colspan="3"><label>&nbsp;</label></td>
		</tr>

		<tr>
			<td colspan="2"><s:iterator value="roleList" status="stat" id="role">
				<s:checkbox name="roleIDs" value="false" fieldValue="%{#role.id}" />
				<s:property value="name" />
				<br />
			</s:iterator></td>
		</tr>
		<tr>
			<td colspan="2"><div id="go"></div></td>
		</tr>
	</table>
	</div>
</s:form>
<script language="JavaScript">
var goBtn = new YAHOO.widget.Button({  
					type: "submit",  
					label: "提&nbsp;&nbsp;交",  
					id: "goBtn",  
					container: "go" }
					); 
</script>
</body>
</html>