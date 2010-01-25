<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="../common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/datatable/assets/skins/sam/datatable.css" />
<script type="text/javascript" src="../common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>

<script type="text/javascript" src="../common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="../common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="../common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="../common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="../common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="../common/yui/build/datatable/datatable-min.js"></script>

<script type="text/javascript" src="../sysconfig/js/employee.js"></script>
</head>
<body class="yui-skin-sam">

<h1>员工信息</h1>
<p>This is the basic look of my form without table</p>

<div id="dynamicdata" align="center"></div>


<button id="addEmp">添加新员工</button>

<script type="text/javascript">
	YAHOO.example.DynamicData = initDataTable()
</script>

</body>
</html>