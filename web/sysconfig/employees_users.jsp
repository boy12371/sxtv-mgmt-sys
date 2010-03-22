<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
<title>员工信息</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/sysconfig/js/employee.js"></script>
</head>
<body class="yui-skin-sam">


<br />
<h1>员工信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/employee.png" border="0">
<p>查看所有员工信息</p>
<s:actionmessage />
<s:actionerror />
<!-- Employee Table-->
<div id="employeeTable" align="center"></div>
<div id="addEmp" align="center"></div>

<!-- Employee Table End-->


<!-- User Table-->

<h1>用户信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/user.png" border="0">
<p>编辑并更新用户信息</p>
<div id="userTable" align="center"></div>
<div id="addUser" align="center"></div>

<script type="text/javascript">
	var addEmpBtn = new YAHOO.widget.Button( {
		type :"link",
		id :"linkbutton6",
		label :"添加新员工",
		href :"./sys/toAddEmployee.action",
		container :"addEmp"
	});
	var addUserBtn = new YAHOO.widget.Button( {
		type :"link",
		id :"linkbutton7",
		label :"添加新用户",
		href :"./sys/toAddUser.action",
		container :"addUser"
	});
	YAHOO.example.DynamicData = initEmployeeDataTable();
	YAHOO.example.DynamicData = initUserDataTable();
</script>

<!-- User Table End-->
</body>
</html>