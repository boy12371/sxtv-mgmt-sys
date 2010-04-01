<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统参数</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/tabview/assets/skins/sam/tabview.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/sysconfig/js/systemData.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>

</head>
<body class="yui-skin-sam">
<s:actionmessage />
<s:actionerror />

<h1>影带状态</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/21.png" border="0">
<p>影带状态不可更改</p>

<br />
<div id="vedioStatus" align="center"></div>



<h1>影带题材</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/21.png" border="0">
<p>可添加或修改题材内容</p>
<br />
<div id="vedioTopic" align="center"></div>
<div id="topicBtnDiv" align="center"></div>

<h1>栏&nbsp;目</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/21.png" border="0">
<p>可添加或修改题材内容</p>
<br />
<div id="vedioSubject" align="center"></div>
<div id="subjectBtnDiv" align="center"></div>


<h1>评分权重</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/21.png" border="0">
<p>可添加或修改权重</p>
<br />
<div id="scoreWeight" align="center"></div>

<!-- div id="weightBtnDiv" align="center"></div-->



<h1>评分级别</h1>
<img class="pageImage" src="${pageContext.request.contextPath}/common/images/21.png" border="0">
<p>可添加或修改级别</p>
<br />
<div id="scorelevel" align="center"></div>
<div id="levelBtnDiv" align="center"></div>




<script type="text/javascript">
	YAHOO.example.DynamicData = initStatusTable();
	YAHOO.example.DynamicData = initTopicTable();
	YAHOO.example.DynamicData = initSubjectTable();
	YAHOO.example.DynamicData = initScoreWeightTable();
	YAHOO.example.DynamicData = initScoreLevelTable();
</script>
<div id="hiddenDiv" style="display: none"><s:form id="commonform" action="" namespace="" method="post">
	<h1 class="popupWindowTitle" id="headDiv"></h1>
	<input type="hidden" name="subject.id" id="objID" />
<table width="100%">
	<tr>
		<td style="text-align: right; width: 40%;"><span id="titleName">题材:</span>&nbsp;</td>
		<td style="text-align:left"><s:textfield name="subject.subjectName" id="objName" /></td>
	</tr>
	<tr>
		<td style="text-align: right; width: 40%;valign:top"><span id="titleComments">备注:</span></td>
		<td style="text-align:left"><s:textarea cols="18" rows="4" name="subject.comments" id="objComments"></s:textarea></td>
	
	</tr>
	<tr>
		<td colspan="2" style="text-align: center"><input type="button" id="yes" value="确认" /><input type="button" id="cancel" value="取消" /></td>
		
	</tr>

</table>
	
</s:form></div>




<div id="hiddenWeightDiv" style="display: none"><s:form id="weightform" action="" namespace="" method="post">
	<h1 class="popupWindowTitle">修改权重</h1>
	<input type="hidden" name="" id="weightID" />
	<p style="background: #FFFFFF; border-bottom: 0">权重系数:&nbsp;<span id="weight"></span></p>
	<p style="background: #FFFFFF; border-bottom: 0">系数:&nbsp;<s:textfield name="" id="weightValue" /></p>	
	<p style="background: #FFFFFF; border-bottom: 0"><input type="button" id="weightYes" value="确认" /> <input type="button" id="weightCancel" value="取消" /></p>
</s:form></div>



<div id="hiddenLevelDiv" style="display: none;"><s:form id="levelform" action="" namespace="" method="post">
	<h1 class="popupWindowTitle" id="levelTitle"></h1>
	<input type="hidden" name="" id="levelID" />
	<p style="background: #FFFFFF; border-bottom: 0" id="levelName">级别:&nbsp;<span id="levelLabel"></span></p>
	<p style="background: #FFFFFF; border-bottom: 0">起点排名:&nbsp;<s:textfield name="scorelevel.start" id="levelStart" /></p>
	<p style="background: #FFFFFF; border-bottom: 0">结束排名:&nbsp;<s:textfield name="scorelevel.end" id="levelEnd" /></p>
	<p style="background: #FFFFFF; border-bottom: 0">参考分值:&nbsp;<s:textfield name="scorelevel.levelScore" id="levelScore" /></p>
	<p style="background: #FFFFFF; border-bottom: 0"><input type="button" id="levelYes" value="确认" /> <input type="button" id="levelCancel" value="取消" /></p>

</s:form></div>




</body>
</html>
