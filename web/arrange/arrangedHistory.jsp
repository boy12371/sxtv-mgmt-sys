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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/dragdrop/dragdrop-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/arrange/js/arrangedHistory.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
<body class="yui-skin-sam">
<%@ include file="/common/errorMsg.jsp" %>
<h1>编排信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/clock.png" border="0">
<s:actionmessage/>
<s:actionerror/>

<p>编排影带历史</p>
<div id="subjectDiv" style="margin-left:73px;">
<table>
	<tr>
		<td>选择栏目：</td>
		<s:iterator value="subjects" status='st' id='s'>
			<td>
			<s:if test="#s.id == subject">
				<input type="radio" onclick="selectSubject(this)" checked="checked" name="sub" value='<s:property value="id"/>' /> 
			</s:if><s:else>
				<input type="radio" onclick="selectSubject(this)" name="sub" value='<s:property value="id"/>' /> 
			</s:else>
				<span id="subjectName<s:property value="id"/>"><s:property value="subjectName"/></span>
			</td>
		</s:iterator>
	</tr>
</table>
</div>

<div id="selectDateDiv" style="width: 800px; height: 30px; margin-left:68px;margin-bottom:10px;">
	<table style="width:100%"><tr>
		<td>
			<table><tr>
			<td><label>选择查询年月：</label></td>
			<td><select id="selectYear" class="selectField" onchange="selectYearFunc(this)" style="width:100px;margin:0px;"></select></td>
			<td><select id="selectMonth" class="selectField" onchange="selectMonthFunc(this)" style="width:100px;margin:0px;"></select></td>
			</tr></table>
		</td>
		<td>
			<div id="printDiv" style="float:right;"></div>
		</td>
	</tr></table>
</div>
<div id="historyDiv" align="center"></div>
<s:form action="toArrangedHistory" namespace="/arrange" >
	<s:hidden name="month" id="month"/>
</s:form>
<script type="text/javascript">
	var firstArrangedDate = "<s:property value='firstArrangedDate'/>";
	var nowDate = "<s:property value='nowDate'/>";
	getSubjectValue();
	var printBtn = new YAHOO.widget.Button({  
		label: "打&nbsp;&nbsp;印",  
		id: "printBtn",  
		container: "printDiv" }
		); 
	printBtn.on("click",printAction);
	function printAction(){
		var year = YAHOO.util.Dom.get("selectYear").value;
		var month = YAHOO.util.Dom.get("selectMonth").value;
		selMonth = year + "-" + month; 	
		var sname = document.getElementById("subjectName"+selSubject).innerHTML;
		var url="/tv/arrange/toArrangePrint.action?selMonth=" + selMonth+"&subject="+selSubject;
		window.open(url, "打印预览");
	}
	
	initSelectElements();
</script>

</body>
</html>