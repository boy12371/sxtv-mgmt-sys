<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />



<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/examine/js/examineTapeByInputer.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

</head>
<body class="yui-skin-sam">

<h1>专业人员打分</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/exam.png" border="0">
<s:actionmessage/>
<s:actionerror/>
<p>输入影带分值</p>
<s:form action="doExamineTape" namespace="/examine" >
	<s:hidden name="tapeScore.vedioID"/>
	<s:hidden name="tapeScore.id"/>
	<s:hidden name="uid"/>
	<s:hidden name="perform"/>
	<div align="center">
		<table class="inputTable">
			<tr>
				<td><label>输入打分人员</label></td>
				<td>
					<input id="examiners" class="inputField autoComplete" type="text" name="examinerByInputer"/> 
	    			<div id="container"></div>  
	    		</td>
			</tr>
			<tr>
				<td><label>影带编号</label></td>
				<td><s:property value="tapeScore.vedioID"/></td>
			</tr>
			<tr>
				<td><label>影带名称</label></td>
				<td><s:property value="tapeScore.vedioName"/></td>
			</tr>
			<tr>
				<td><label>故事</label></td>
				<td><s:textfield cssClass="inputField" name="tapeScore.storyScore" id="storyScore" value=""/></td>
			</tr>
			<tr>
				<td><label>技术</label></td>
				<td><s:textfield cssClass="inputField" name="tapeScore.techScore" id="techScore" value=""/></td>
			</tr>
			<tr>
				<td><label>表演</label></td>
				<td><s:textfield cssClass="inputField" name="tapeScore.performScore" id="performScore" value=""/></td>
			</tr>
			<tr>
				<td><label>创新</label></td>
				<td><s:textfield cssClass="inputField" name="tapeScore.innovateScore" id="innovateScore" value=""/></td>
			</tr>
			<tr>
				<td><label>购买意向</label></td>
				<td>
					<table><tr>
						<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="1" id="purchase"/>购买</td>
						<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="0" checked="checked"/>不购买</td>
					</tr></table>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>获奖备选</label></td>
				<td><input type="checkbox" class="radioSel" name="tapeScore.award" value="1" id="award"/>推荐</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>导向</label></td>
				<td><input type="checkbox" class="radioSel" name="tapeScore.orientation" value="1" id="orientation"/>不合格</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<div style="margin-top:20px;">
			<div id="okBtnDiv" style="display:inline;margin-right:50px;"></div>
		</div>
	</div>
	
	<p>已打分值</p>
	<div id="dynamicdata" align="center"></div>
</s:form>

<script language="JavaScript">
var examiners = new Array();
<s:iterator value="examiners" status="st">
	var name = "<s:property value='employee.name' escape='false'/>";
	examiners[examiners.length] = name;
</s:iterator>

YAHOO.example.BasicLocal = function() { 
	// Use a LocalDataSource 
	var oDS = new YAHOO.util.LocalDataSource(examiners); 
	// Instantiate the AutoComplete 
	var oAC = new YAHOO.widget.AutoComplete("examiners", "container", oDS); 
	oAC.prehighlightClassName = "yui-ac-prehighlight"; 
	oAC.useShadow = true;   
	return { 
		oDS: oDS, 
		oAC: oAC 
	}; 
}(); 

function cancelAction(){
	window.location="/tv/examine/toUnExaminedTapes.action";
}
function okAction(){
	var storyScore = document.getElementById("storyScore").value;
	var techScore = document.getElementById("techScore").value;
	var performScore = document.getElementById("performScore").value;
	var innovateScore = document.getElementById("innovateScore").value;
	var exp=/^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	var msg="";
	if(!exp.test(storyScore) || parseFloat(storyScore, 10)>100){
		msg+="故事得分必须是一个介于0和100之间的数字！<br>";
	}
	if(!exp.test(techScore) || parseFloat(techScore, 10)>100){
		msg+="技术得分必须是一个介于0和100之间的数字！<br>";
	}
	if(!exp.test(performScore) || parseFloat(performScore, 10)>100){
		msg+="表演得分必须是一个介于0和100之间的数字！<br>";
	}
	if(!exp.test(innovateScore) || parseFloat(innovateScore, 10)>100){
		msg+="创新得分必须是一个介于0和100之间的数字！<br>";
	}
	if(""!=msg){
		jAlert(msg, "输入错误");
		return;
	}
	document.forms[0].submit();
}
var okBtn = new YAHOO.widget.Button({  
					label: "确&nbsp;&nbsp;定",  
					id: "okBtn",  
					container: "okBtnDiv" }
					); 
okBtn.on("click",addData);

initDataTable();
</script>
</body>
</html>