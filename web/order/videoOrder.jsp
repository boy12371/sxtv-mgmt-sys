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
<s:form action="createPlayorder" method="post" namespace="/order">
请选择编排月份：
<select id="month" name="month" onchange="initMontSelections(this);">
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
	
	<div id="dhtmlgoodies_listOfItems">
		<div>
			<p>待排剧目</p>		
		<ul id="allItems">
		<s:iterator value="videoList" var="tape">		
			<li id="<s:property value="#tape.id" />"><s:property value="#tape.vedioName"/></li>
		</s:iterator>
			
		</ul>
		</div>
	</div>	
	<div id="dhtmlgoodies_mainContainer">
		<!-- ONE <UL> for each "room" -->
		
	</div>
</div>
<div id="footer">

	
	<input type="hidden" name="orderString" id="orderString" />
	<input type="button" onclick="saveDragDropNodes()" value="Save">
	
</div>
</s:form>
<ul id="dragContent"></ul>
<div id="dragDropIndicator"><img src="images/insert.gif"></div>
<div id="saveContent"><!-- THIS ID IS ONLY NEEDED FOR THE DEMO --></div>



</body>

</html>