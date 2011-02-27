<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="./css/examine.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/examine/js/audienceExamine.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
<body class="yui-skin-sam">

<h1>观众评价信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/exam.png" border="0">

<s:actionmessage/>
<s:actionerror/>
<p>输入影带新评价</p>
<div align="center">

<table>
	<tr>
	<td valign="top">
		<table class="inputTable">
			<tr>
				<td><label>影带编号：</label></td>
				<td id="vedioID"><s:property value="tape.id"/></td>
			</tr>
			<tr>
				<td><label>影带名称：</label></td>
				<td id="vedioName"><s:property value="tape.vedioName"/></td>
			</tr>
			<tr>
				<td><label>输入观众名：</label></td>
				<td>
		    		<input id="audienceName" class="inputField autoComplete" type="text"> 
		    		<div id="container"></div>  
				</td>
			</tr>
			<tr>
				<td><label>选择评价：</label></td>
				<td>
					<table><tr>
						<td><input type="radio" class="radioSel" id="look" name="result" value="1"/>看</td>
						<td><input type="radio" class="radioSel" id="unlook" name="result" value="0"/>不看</td>
					</tr></table>
				</td>
			</tr>		
		</table>
		<div style="margin-top:10px;" id="addBtnDiv"></div></td>
	<td valign="top">
		<div id="cellediting" align="center" class="cellediting"></div>
		<div align="center">
			<div style="margin-top:30px;" id="submitBtnDiv"></div>
		</div></td>
	</tr>

</table>
	
</div>		

<s:form action="doAudienceExamine" namespace="/examine" >
	<s:hidden name="newResult" id="newResult"/>
	<s:hidden name="delResult" id="delResult"/>
	<s:hidden name="tape.id"/>
</s:form>
<script type="text/javascript">
	//get all audience name to javascript array for autocomplete.
	var audience = new Array();
	<s:iterator value="audience" status="st">
		var name = "<s:property value='name' escape='false'/>";
		audience[audience.length] = name;
	</s:iterator>

	YAHOO.example.BasicLocal = function() { 
		// Use a LocalDataSource 
		var oDS = new YAHOO.util.LocalDataSource(audience); 
		// Instantiate the AutoComplete 
		var oAC = new YAHOO.widget.AutoComplete("audienceName", "container", oDS); 
		oAC.prehighlightClassName = "yui-ac-prehighlight"; 
		oAC.useShadow = true;   
		return { 
			oDS: oDS, 
			oAC: oAC 
		}; 
	}(); 
	//button to add audience result to data table.
	var addBtn = new YAHOO.widget.Button({  
		label: "确&nbsp;&nbsp;定",  
		id: "addBtn",  
		container: "addBtnDiv" }
		); 
	addBtn.on("click",addAction);
	//button to submit new data
	var submitBtn = new YAHOO.widget.Button({  
		label: "提&nbsp;&nbsp;交",  
		id: "submitBtn",  
		container: "submitBtnDiv" }
		); 
	submitBtn.on("click",submitAction);
	//audience result datatable.
	YAHOO.example.DynamicData = initDataTable();
</script>

</body>
</html>