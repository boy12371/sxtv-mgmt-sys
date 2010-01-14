<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>
<s:form action="doLogon" namespace="/logon" method="post">

<h3>logon</h3>
<table>
	<tr>
		<td>用户名:</td>
		<td><s:textfield name="userInfo.username" /></td>
	</tr>
	<tr>
		<td>密码:</td>
		<td><s:textfield name="userInfo.password" /></td>
	</tr>
</table>

<input type="submit" value="submit"/>
</s:form>
</body>

</html>