<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/marketRate/js/marketRate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<title>收视率-市场份额</title>
</head>
<body class="yui-skin-sam">


<h1>剧目收视率、市场份额</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/43.png" border="0">
<p>搜索或点击剧目名称或编号，输入收视率和市场份额</p>
<s:actionmessage />
<s:actionerror />
<s:form id="searchForm" action="searchVideoByNameOrIDForMarketRate" namespace="/vedio">
	<div align="center">
	<table>
		<tr>
			<td><label>影带编号</label></td>
			<td><input class="inputField" type="text" name="vid" id="vid" /><s:hidden name="optionName" value="marketRate" /></td>
			<td><label>剧目名称</label></td>
			<td><input class="inputField autoComplete" type="text" id="searchinput" name="vname" style="width: 200px" />
			<div id="searchcontainer"></div>
			</td>
			<td>
			<div id="goSearch"></div>
			</td>
		</tr>

	</table>
	</div>
</s:form>
<br>
<br>
<br>
<br>
<div id="dynamicdata" align="center"></div>

<script type="text/javascript">
	var goSearch = new YAHOO.widget.Button( {
		type :"submit",
		label :"搜索",
		id :"search",
		container :"goSearch"
	});
	YAHOO.example.Centered = autoCompleteVideoName();
	YAHOO.example.DynamicData = initDataTable();
</script>
</body>
</html>