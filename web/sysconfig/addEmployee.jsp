<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sx:head />
<title>添加员工</title>
<link rel="stylesheet" type="text/css" href="../../common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../../common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="../../common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="../../common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="../../common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="../../common/css/common.css" />
</head>
<body>

<h1>员工信息</h1>
<p>编辑并更新员工信息</p>

<s:actionerror/>
<s:form action="doAddEmployee" namespace="/sys" method="post">
	<div align="center">
	<table>
		<tr>
			<td><label>姓名：</label></td>
			<td><s:textfield name="employee.name" /></td>
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
			<td colspan="4" align="center"><span id="go" class="yui-button yui-push-button"> <span class="first-child"> <s:submit value="添加" />
			</span> </span></td>
		</tr>
	</table>
	</div>
</s:form>
</body>
</html>