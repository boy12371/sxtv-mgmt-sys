<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/sysconfig/js/employee.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
</head>
<body class="yui-skin-sam">



<h1>员工信息</h1>
<p>编辑并更新员工信息</p>
<s:actionerror />
<s:form action="doUpdateEmployee" namespace="/sys" method="post">
	<div align="center">
	<table>
		<tr>
			<td><label>姓名：</label></td>
			<td><s:textfield name="employee.name" /><s:hidden name="employee.id" /></td>
			<td><label>性别：</label></td>
			<td><s:select name="employee.gender" list="#{'1':'男','0':'女'}" /></td>
		</tr>
		<tr>
			<td><label>出生日期：</label></td>
			<td><sx:datetimepicker name="employee.birthday" displayFormat="yyyy-MM-dd" /></td>
			<td><label>电话：</label></td>
			<td><s:textfield name="employee.tel" /></td>
		</tr>
		<tr>
			<td><label>入职日期：</label></td>
			<td><sx:datetimepicker name="employee.contractDate" displayFormat="yyyy-MM-dd" /></td>

			<td><label>备注：</label></td>
			<td><s:textarea name="employee.comments" cols="20" rows="2"></s:textarea></td>
		</tr>

		<tr>
			<td colspan="4" align="center">
				<s:hidden name="operation" /> 
				<span id="go" class="yui-button yui-push-button">
					<span class="first-child"> 
					<s:submit id="updateEmp" value="更新" onclick="executOperations(this.id);" />
					<s:if test="employee.status==1">
						<s:submit id="disableEmp" value="注销" onclick="executOperations(this.id);" />
					</s:if><s:else>
						<s:submit id="enableEmp" value="启用" onclick="executOperations(this.id);" />
					</s:else> 
					
					</span> 
				</span>
			</td>
		</tr>
	</table>
	</div>
	<s:if test="!employee.users.isEmpty()">
		<h1>用户信息</h1>
		<p>员工的登录系统用户</p>
		<div align="center">
		<table>
			<s:iterator value="employee.users"></s:iterator>
			<tr>
				<td><label>用户名：</label></td>
				<td><s:property value="userName" /></td>
				<td><label>密码：</label></td>
				<td>*******</td>
			</tr>
		</table>
		</div>
	</s:if>
	<s:else>
		<p>尚未为此员工创建系统用户</p>
	</s:else>


</s:form>
</body>
</html>