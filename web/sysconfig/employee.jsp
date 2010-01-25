<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<h1>Sign-up form</h1>
<p>This is the basic look of my form without table</p>

<s:form>
	<table>
		<tr>
			<td><label>员工姓名</label></td>
			<td><s:textfield name="employee.name" /></td>
			<td><label>性别</label></td>
			<td><s:textfield name="" /></td>
		</tr>
		<tr>
			<td><label>出生日期</label></td>
			<td></td>
			<td><label>入职日期</label></td>
			<td></td>
		</tr>
		<tr>
			<td><label>电话</label></td>
			<td><s:textfield name="" /></td>
			<td><label>备注</label></td>
			<td><s:textarea name=""></s:textarea></td>

		</tr>
		<tr>
			<td colspan="4" align="center"><span id="go" class="yui-button yui-push-button"> <span class="first-child">
			<button type="button">Go!</button>
			</span> </span></td>
		</tr>
	</table>

</s:form>
</body>
</html>