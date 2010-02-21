<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<sx:head />
<title>添加员工</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
</head>
<body class="yui-skin-sam">

<h1>员工信息</h1>
<p>编辑并更新员工信息</p>

<s:actionerror/>
<s:form action="doAddEmployee" namespace="/sys" method="post">
	<div>
	<table class="inputTable">
		<tr>
			<td><label>姓名：</label></td>
			<td><s:textfield cssClass="inputField" name="employee.name" />
				<s:hidden name="employee.status" value="1"/>	
			</td>
			<td><label>性别：</label></td>
			<td><s:select cssClass="selectField" name="employee.gender" list="#{'1':'男','0':'女'}" /></td>
		</tr>
		<tr>
			<td><label>出生日期：</label></td>
			<td><sx:datetimepicker cssClass="inputField" name="employee.birthday" displayFormat="yyyy-MM-dd" /></td>
			<td><label>电话：</label></td>
			<td><s:textfield cssClass="inputField" name="employee.tel" /></td>
		</tr>
		
		<tr>
			<td><label>入职日期：</label></td>
			<td><sx:datetimepicker cssClass="inputField" name="employee.contractDate" displayFormat="yyyy-MM-dd" /></td>

			<td><label>备注：</label></td>
			<td><s:textarea cssClass="inputField" name="employee.comments" cols="20" rows="2"></s:textarea></td>
		</tr>

		<tr>
			<td colspan="4" align="center">
				<div id="go"></div>
			</td>
		</tr>
	</table>
	</div>
</s:form>

<script language="JavaScript">
var goBtn = new YAHOO.widget.Button({  
					type: "submit",  
					label: "添&nbsp;&nbsp;加",  
					id: "goBtn",  
					container: "go" }
					); 
</script>

</body>
</html>