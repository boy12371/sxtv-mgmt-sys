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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/tabview/assets/skins/sam/tabview.css" />

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
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/tabview/tabview-min.js"></script> 

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vediotape/js/vediotape.js"></script>


<style type="text/css">
.yui-skin-sam .yui-dt-liner {
font-size:10pt;
white-space:nowrap;
}
.yui-skin-sam .yui-navset .yui-nav .selected a, .yui-skin-sam .yui-navset .yui-nav .selected a:focus, .yui-skin-sam .yui-navset .yui-nav .selected a:hover {
    background: url("../common/yui/build/assets/skins/sam/sprite.png") repeat-x scroll left -1400px #2647A0;
    color: #FFFFFF;
}
.yui-skin-sam .yui-navset .yui-nav a, .yui-skin-sam .yui-navset .yui-navset-top .yui-nav a {
    background: url("../common/yui/build/assets/skins/sam/sprite.png") repeat-x scroll 0 0 #D8D8D8;
    border-color: #A3A3A3;
    border-style: solid;
    border-width: 0 1px;
    color: #000000;
    position: relative;
    text-decoration: none;
}
.yui-skin-sam .yui-navset .yui-content, .yui-skin-sam .yui-navset .yui-navset-top .yui-content {
    border-bottom: 0px solid #808080;
    border-left: 0px solid #808080;
    border-right: 0px solid #808080;
    border-top: 0px solid #243356;
    padding: 0.25em 0.5em;
}
.yui-skin-sam .yui-navset .yui-content {
    background: none;
}
</style>
<title>影带信息</title>
</head>
<body class="yui-skin-sam">

<h1>搜索影带信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/audit.png" border="0">
<p>输入剧目编号或名称搜索剧目</p>
<s:actionerror />
<s:actionmessage />
<s:form id="searchForm" action="searchVideoByNameOrIDForModification" namespace="/vedio">
	<div align="center">
	<table>
		<tr>
			<td><label>影带编号</label></td>
			<td><input class="inputField" type="text" name="vid" id="vid" /><s:hidden name="optionName" value="modification" /></td>
			<td><label>剧目名称</label></td>
			<td><input class="inputField autoComplete" type="text" id="searchinput" name="vname" />
			<div id="searchcontainer"></div>
			</td>
			<td colspan="4" align="center">
			<div id="goSearch"></div>
			</td>
		</tr>

	</table>
	</div>
	<script type="text/javascript">
	YAHOO.example.Centered = autoCompleteVideoName();
	</script>
</s:form>

<s:form id="tableForm" namespace="/vedio" action="doModificationBatch" method="post">

<div id="tabs" class="yui-navset"> 
    <ul class="yui-nav"> 
        <li class="selected"><a href="#tab1"><em>已通过</em></a></li> 
        <li><a href="#tab2"><em>已入库待排</em></a></li>
    </ul>            
    <div class="yui-content"> 
        <div>
        	<a href="#" id="tableOption" style="font-size:12px">选项</a>
			<div id="colDiv" style="display:none;z-index:1002;position:absolute;background-color:white;overflow:auto;border:5px solid #999999"></div>
			<div id="makeToArrange"></div>
			<div id="submitToPreArrange"></div>
		</div> 
        <div>
	        <a href="#" id="tableOptionPass" style="font-size:12px">选项</a>
			<div id="colDivPass" style="display:none;z-index:1002;position:absolute;background-color:white;overflow:auto;border:5px solid #999999"></div>
			<div id="makeToPass"></div>
			<div id="submitToPass"></div>
        </div> 
    </div> 
</div>
</s:form>
<script type="text/javascript">
(function() {
    var tabView = new YAHOO.widget.TabView('tabs');
    YAHOO.util.Event.addListener(window, "load", initButtons);
})();
initArrangeTableDataSource("/tv/audit/filterVideos.action?filter=3&sort=dateInput&dir=asc&startIndex=-1&results=10",toArrangeTableCallBack);
initPassTableDataSource("/tv/audit/filterVideos.action?filter=5&sort=dateInput&dir=asc&startIndex=-1&results=10",toToPassTableCallBack);
	//YAHOO.util.Event.addListener(window, "load", initDataSource);
	//YAHOO.util.Event.addListener(window, "load", initToPassTable);
	
</script>

</body>
</html>