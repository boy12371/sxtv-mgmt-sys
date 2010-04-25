<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/vediotape/js/vediotape.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />

<title>调整影带状态</title>
</head>
<body class="yui-skin-sam">


<h1>调整影带状态</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/Search.png" border="0">
<p>输入剧目编号或名称搜索剧目</p>
<s:actionerror />
<s:actionmessage />
<div align="center"><s:form id="searchForm" action="searchVideoByNameOrIDForStatusAdjust" namespace="/vedio">
	<table>
		<tr>
			<td><label>影带编号</label></td>
			<td><input class="inputField" type="text" name="vid" id="vid" /><s:hidden
				name="optionName" value="adjustStatus" /></td>

			<td><label>剧目名称</label></td>
			<td><input class="inputField autoComplete" type="text" id="searchinput" name="vname" />
			<div id="searchcontainer"></div>
			</td>
			<td align="center"><span id="searchBtn" class="yui-button yui-push-button"> <span class="first-child">
			<button type="submit">搜索</button>
			</span> </span></td>
		</tr>

	</table>


</s:form></div>
<s:if test="vedio!=null">
	<h1>影带信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/tape.png" border="0">
	<p>查看或编辑剧目信息并保存</p>
	<div align="center"><s:form id="updateForm" action="doAdjustVideoStatus" namespace="/vedio" method="post" onsubmit="return validateInput();">
		<table class="inputTable">
			<tr>
				<td><label>影带编号</label></td>
				<td><s:property value="vedio.id" /> <s:hidden name="vedio.id" id="vedioID" /></td>
			</tr>
			<tr>
				<td><label>剧目名称</label></td>
				<td><s:property value="vedio.vedioName" /></td>
			</tr>
			<tr>
				<td><label>影视公司</label></td>
				<td><s:property value="vedio.companyID.companyName" /></td>
			</tr>
			<tr>
				<td><label>收带时间</label></td>
				<td><s:date name="vedio.dateComing" format="yyyy-MM-dd" /></td>
			</tr>
			<tr>
				<td><label>栏目</label></td>
				<td><s:property value="vedio.subject.subjectName" /></td>
			</tr>
			<tr>
				<td><label>题材</label></td>
				<td><s:property value="vedio.topic.topicName" /></td>
			</tr>

			<tr>
				<td><label>状态</label></td>
				<td><s:property value="vedio.status.status" /></td>
			</tr>
			<tr>
				<td><label>备注</label></td>
				<td><s:property value="vedio.comments" /></td>
			</tr>
			
			<tr>
				<td><label>调整状态</label></td>
				<td><s:select name="vedio.status.id" list="#{'2':'待审','3':'通过','5':'待徘','7':'淘汰'}" headerKey="-1" headerValue="----" id="statusSelect" /></td>
			</tr>
			<tr>
				<td><label>调整原因</label></td>
				<td><s:textarea cssClass="inputField" name="vedio.comments" id="changeComments"></s:textarea></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<div id="submitBtnDiv"></div>
				</td>
			</tr>
		</table>
	</s:form></div>
</s:if>

<script type="text/javascript">
	YAHOO.example.Centered = autoCompleteVideoName();
	var searchBtn = new YAHOO.widget.Button("searchBtn");
	var submitBtn = new YAHOO.widget.Button( {
		type :"submit",
		label :"确定",
		id :"submitBtn",
		container :"submitBtnDiv"
	});
	function validateInput(){
		var status = YAHOO.util.Dom.get("statusSelect");
		var comments = YAHOO.util.Dom.get("changeComments");
		if(status.value == -1){
			jAlert('请选择状态', '错误');
			return false;
		}
		if(comments.value.length == 0){
			jAlert('调整原因必须填写', '错误');
			return false;
		}
		return true;
	}	
</script>
</body>
</html>