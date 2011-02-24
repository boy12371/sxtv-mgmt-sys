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
<script type="text/javascript" src="${pageContext.request.contextPath}/arrange/js/arrange.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
<body class="yui-skin-sam">

<h1>编排信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/clock.png" border="0">
<s:actionmessage/>
<s:actionerror/>

<p>未编排影带列表，点击第一列图标将影带移入编排列表</p>

<div id="subjectDiv" align="center">
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
				<s:property value="subjectName"/>
			</td>
		</s:iterator>
	</tr>
</table>
</div>
<div id="unArrangeTableDiv" align="center"></div>

<p>已编排影带列表，点击第一列图标可将影带移除编排列表，拖动记录可以调节播放时间。</p>
<br />

<div id="selectMonthDiv" style="width:800px;height:30px;margin-left:68px;margin-bottom:10px;">
	<table style="width:100%"><tr>
		<td>
			<table><tr>
			<td><label>选择编排月份：</label></td>
			<td>
				<s:select list="monthList" listKey="key" listValue="value" 
					id="selectMonth" 
					onchange="selectMonthFunc(this)"
					cssStyle="float:right;width:100px;margin:0px;"
				/>
			</td>
			</tr></table>
		</td>
		<td>
			<div id="printDiv" style="float:right;"></div>
		</td>
	</tr></table>
</div>
<div id="arrangeTableDiv" align="center"></div>
<div align="center">
	<div style="margin-top:30px;" id="submitBtnDiv"></div>
</div>
<s:form action="doArrange" namespace="/arrange" >	
	<s:hidden name="subject" id="subject"/>
	<s:hidden name="newResult" id="newResult"/>
	<s:hidden name="month" id="month"/>
</s:form>
<script type="text/javascript">
	getSubjectValue();
	initUnArrangeTable();

	var printBtn = new YAHOO.widget.Button({  
		label: "打&nbsp;&nbsp;印",  
		id: "printBtn",  
		container: "printDiv" }
		); 
	printBtn.on("click",printAction);
	function printAction(){
		var selDate = YAHOO.util.Dom.get("selectMonth").value;
		var date = selDate.split("-");
		var year = parseInt(date[0],10)+1900;
		var selMonth = year + "-" + date[1]; 	
		var url="/tv/arrange/toArrangePrint.action?selMonth=" + selMonth;
		window.open(url, "打印预览");
	}

	var submitBtn = new YAHOO.widget.Button({  
		label: "提&nbsp;&nbsp;交",  
		id: "submitBtn",  
		container: "submitBtnDiv" }
		); 
	submitBtn.on("click",submitAction);

	YAHOO.util.Event.addListener(window, "load", initArrangeReorderEvent);
</script>

</body>
</html>