<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/order/css/order.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/order/js/order.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css" media="print">
	div#dhtmlgoodies_listOfItems{
		display:none;
	}
	body{
		background-color:#FFF;
	}
	img{
		display:none;
	}
	#dhtmlgoodies_dragDropContainer{
		border:0px;
		width:100%;
	}	
	</style>	
<title>編排據目</title>	

</head>
<body>
请选择编排月份：
<select id="month" onchange="initMontSelections(this);">
	<option value="1">1月</option>
	<option value="2">2月</option>
	<option value="3">3月</option>
	<option value="4">4月</option>
	<option value="5">5月</option>
	<option value="6">6月</option>
	<option value="7">7月</option>
	<option value="8">8月</option>
	<option value="9">9月</option>
	<option value="10">10月</option>
	<option value="11">11月</option>
	<option value="12">12月</option>
</select>
<div id="dhtmlgoodies_dragDropContainer">
	<div id="topBar">
		<img src="http://www.sharejs.com/images/logo.gif">
	</div>
	<div id="dhtmlgoodies_listOfItems">
		<div>
			<p>Available students</p>		
		<ul id="allItems">
			<li id="node1">Student A</li>
			<li id="node2">Student B</li>
			<li id="node3">Student C</li>
			<li id="node4">Student D</li>
			<li id="node5">Student E</li>
			<li id="node6">Student F</li>
			<li id="node7">Student G</li>
			<li id="node8">Student H</li>
			<li id="node9">Student I</li>
			<li id="node10">Student J</li>
			<li id="node11">Student K</li>
			<li id="node12">Student L</li>
			<li id="node13">Student M</li>
			<li id="node14">Student N</li>
			<li id="node15">Student O</li>
		</ul>
		</div>
	</div>	
	<div id="dhtmlgoodies_mainContainer">
		<!-- ONE <UL> for each "room" -->
		
	</div>
</div>
<div id="footer">

	<form action="aPage.html" method="post"><input type="button" onclick="saveDragDropNodes()" value="Save"></form>
</div>
<ul id="dragContent"></ul>
<div id="dragDropIndicator"><img src="images/insert.gif"></div>
<div id="saveContent"><!-- THIS ID IS ONLY NEEDED FOR THE DEMO --></div>



</body>

</html>