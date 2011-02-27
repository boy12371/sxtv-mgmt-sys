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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/tabview/assets/skins/sam/tabview.css" />
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
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sysconfig/js/employee.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>


<style type="text/css">
.yui-skin-sam .yui-dt-liner {
font-size:10pt;
white-space:nowrap;
}
.yui-skin-sam .yui-navset .yui-nav .selected a, .yui-skin-sam .yui-navset .yui-nav .selected a:focus, .yui-skin-sam .yui-navset .yui-nav .selected a:hover {
    background: url("../common/yui/build/assets/skins/sam/sprite.png") repeat-x scroll left -1400px #2647A0;
    color: #FFFFFF;
}
.yui-skin-sam .yui-navset .yui-nav a, .yui-skin-sam .yui-navset .yui-navset-top .yui-nav a {
    background: url("../common/yui/build/assets/skins/sam/sprite.png") repeat-x scroll 0 0 #D8D8D8;
    border-color: #A3A3A3;
    border-style: solid;
    border-width: 0 1px;
    color: #000000;
    position: relative;
    text-decoration: none;
}
.yui-skin-sam .yui-navset .yui-content, .yui-skin-sam .yui-navset .yui-navset-top .yui-content {
    border-bottom: 1px solid #808080;
    border-left: 1px solid #808080;
    border-right: 1px solid #808080;
    border-top: 1px solid #243356;
    padding: 0.25em 0.5em;
}
.yui-skin-sam .yui-navset .yui-content {
    background: none;
}
</style>
</head>
<body class="yui-skin-sam">

<s:actionmessage />
<s:actionerror />

<div id="tabs" class="yui-navset"> 
    <ul class="yui-nav"> 
        <li class="selected"><a href="#tab1"><em>员工</em></a></li> 
        <li><a href="#tab2"><em>用户</em></a></li>
    </ul>            
    <div class="yui-content"> 
        <div>
        	<div id="addEmp" style="margin-left:17%"></div>
        	<div id="employeeTable" align="center"></div>
		</div> 
        <div>
        	<div id="addUser" style="margin-left:17%"></div>
	        <div id="userTable" align="center"></div>

        </div> 
    </div> 
</div>

<script type="text/javascript">
	(function() {
	    var tabView = new YAHOO.widget.TabView('tabs');
	})();
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