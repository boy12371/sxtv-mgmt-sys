<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head extraLocales="UTF-8"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工管理</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/sysconfig/js/employee.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
<body class="yui-skin-sam">



<h1>员工信息</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/employee.png" border="0">
<p>编辑并更新员工信息</p>
<s:actionerror />
<s:fielderror></s:fielderror>
<s:form action="doUpdateEmployee" namespace="/sys" method="post">
	<div align="center">
	<table class="inputTable">
		<tr>
			<td><label>姓名：</label></td>
			<td><s:textfield cssClass="inputField" name="employee.name" /><s:hidden name="employee.id" /></td>
		</tr>
		<tr>
			<td><label>性别：</label></td>
			<td><s:select cssClass="selectField" name="employee.gender" list="#{'1':'男','0':'女'}" /></td>
		</tr>
		<tr>
			<td><label>出生日期：</label></td>
			<td><sx:datetimepicker cssClass="inputField" name="employee.birthday" displayFormat="yyyy-MM-dd" toggleType="explode" value="%{'today'}" language="UTF-8" weekStartsOn="0"/></td>
		</tr>
		<tr>
			<td><label>电话：</label></td>
			<td><s:textfield cssClass="inputField" name="employee.tel" /></td>
		</tr>
		<tr>
			<td><label>入职日期：</label></td>
			<td><sx:datetimepicker cssClass="inputField" name="employee.contractDate" displayFormat="yyyy-MM-dd" toggleType="explode" value="%{'today'}" language="UTF-8" weekStartsOn="0"/></td>
		</tr>
		<tr>
			<td><label>状态：</label></td>
			<td><s:if test="employee.status==1">正常
			</s:if><s:else>
			禁用
			</s:else></td>
		</tr>
		<tr>
			<td><label>备注：</label></td>
			<td><s:textarea cssClass="inputField" name="employee.comments" cols="20" rows="2"></s:textarea></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><s:hidden name="operation" />
			<div id="updateDiv" align="center"></div>
			<s:submit id="updateEmp" value="更新" onclick="executOperations(this.id);" /> <s:if test="employee.status==1">

				<input type="button" id="disableEmp" value="注销" onclick="executOperations(this.id);">

			</s:if><s:else>
				<s:submit id="enableEmp" value="启用" onclick="executOperations(this.id);" />
			</s:else></td>
		</tr>
	</table>
	</div>
	<s:if test="!employee.users.isEmpty()">
		<h1>用户信息</h1>
		<p>员工的登录系统用户</p>
		<div align="center">
		<table class="inputTable">
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