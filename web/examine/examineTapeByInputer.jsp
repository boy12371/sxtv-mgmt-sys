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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/container/assets/skins/sam/container.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />
<link rel="stylesheet" type="text/css" href="./css/examine.css" />


<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>

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
<s:form action="doExamineTapeByInputer" namespace="/examine" >
	<s:hidden name="vid"/>
	<s:hidden name="newResult" id="newResult"/>
	<div align="center">
	
	<table>
		<tr>
			<td valign="top">
				<table class="inputTable">
				<tr>
					<td><label>选择打分人员</label></td>
					<td>
						<s:select cssStyle="width:200px;" id="examiners" list="examiners" listKey="employee.name" listValue="employee.name" headerKey="" headerValue="请选择"></s:select>
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
					<div id="okBtnDiv" align="center"></div>
				</div>
			</td>
			<td valign="top">
				<div id="dynamicdata" align="center"></div>
				<div id="submitBtnDiv" style="margin-top:20px;"></div>
			</td>
		</tr>
	
	</table>
		
	</div>
</s:form>

<script language="JavaScript">
function cancelAction(){
	window.location="/tv/examine/toUnExaminedTapes.action";
}
function submitAction(){
	submitData();
}

var okBtn = new YAHOO.widget.Button({  
					label: "确&nbsp;&nbsp;定",  
					id: "okBtn",  
					container: "okBtnDiv" }
					); 
okBtn.on("click",addData);

var submitBtn = new YAHOO.widget.Button({  
	label: "提&nbsp;&nbsp;交",  
	id: "submitBtn",  
	container: "submitBtnDiv" }
	); 
submitBtn.on("click",submitAction);

initDataTable();
</script>
</body>
</html>