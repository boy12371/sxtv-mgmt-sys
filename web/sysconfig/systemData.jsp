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
<script type="text/javascript">
	YAHOO.example.DynamicData = initStatusTable();
	//YAHOO.example.DynamicData = initTopicTable();
	//YAHOO.example.DynamicData = initSubjectTable();
</script>
<div id="hiddenDiv" style="display: none"><s:form id="aform" action="" namespace="/sys" method="post">
	<h1 class="popupWindow" id="headDiv"></h1>
	<input type="hidden" name="subject.id" id="objID" />
	<p style="background: #FFFFFF; border-bottom: 0">题&nbsp;材:&nbsp;<s:textfield name="subject.subjectName" id="objName" /></p>
	<p style="background: #FFFFFF; border-bottom: 0">备&nbsp;注:&nbsp;<s:textarea cols="18" rows="4" name="subject.comments" id="objComments"></s:textarea></p>
	<p style="background: #FFFFFF; border-bottom: 0"><input type="button" id="yes" value="确认" /> <input type="button" id="cancel" value="取消" /></p>

</s:form></div>
</body>
</html>
