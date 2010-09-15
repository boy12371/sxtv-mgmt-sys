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
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
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
<script type="text/javascript" 2007  2008  2009  src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/search/js/search.js"></script>

<style type="text/css">
.yui-skin-sam tr.yui-dt-odd td.yui-dt-asc,.yui-skin-sam tr.yui-dt-odd td.yui-dt-desc
	{
	background-color: #FFFFFF;
}

.yui-skin-sam tr.yui-dt-even td.yui-dt-asc,.yui-skin-sam tr.yui-dt-even td.yui-dt-desc
	{
	background-color: #FFFFFF;
}

.yui-skin-sam tr.yui-dt-odd {
	background-color: #FFFFFF;
}

.yui-skin-sam .yui-dt th {
	border-left: 1px solid buttonhighlight;
	border-top: 1px solid buttonhighlight;
	border-bottom: 1px solid buttonshadow;
	border-right: 1px solid buttonshadow;
	background-color: #ece9d8;
	background-image: none;
	cursor: pointer;
	padding: 4px;
	font-weight: bold;
}

.yui-skin-sam .yui-dt td {
	border: clear;
	border-color: #CBCBCB;
	border-style: solid;
	border-width: 1px;
	border-bottom-style: insert, text-align :         center
}
.yui-skin-sam .yui-dt-liner {
	font-size:12pt;
	white-space: nowrap;
}
.yui-skin-sam .yui-dt th, .yui-skin-sam .yui-dt th a {
	font-weight:bold;
}
</style>
<style type="text/css" media="print">
.noprint {
	display: none
}
</style>

<title>打印预览</title>
</head>
<body class="yui-skin-sam">
<s:actionerror />
<br />
<br />
<br />
<h4><script>
var date = new Date();
document.write("打印日期: " + date.getFullYear() + "年" + (date.getMonth() + 1)+ "月" + date.getDate() + "日");
</script></h4>



<script type="text/javascript">
	function printsetup(){
  　　	// 打印页面设置
  　　	wb.execwb(8,1);
  　　}
  　　function printpreview(){
	  　　//打印页面预览      　　　　
	  　　wb.execwb(7,1);
  　　}
 
  　　function printit(){
	  　　if (confirm('确定打印吗？')) {
	  　　wb.execwb(6,6);
	  　　}
  　　} 
</script>
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0"></OBJECT>
<div class="noprint">
<input type="button" id="tableOption" value="选项"/>
<input type=button name=button_show value="打印预览" onclick="javascript:printpreview();">
<input type=button name=button_fh value="关闭" onclick="javascript:window.close();"> 
</div>
<div id="colDiv" style="display: none; z-index: 1002; position: absolute; background-color: white; overflow: auto; border: 5px solid #999999"></div>
<s:hidden name="query" id="queryString" />
<div id="excelTable" align="center" style="margin-top: -50"></div>

<script type="text/javascript">
	var query = '<s:property value="query"/>';
	//query.replace(/amp;/g, "")
	var actionUrl ="/tv/search/doPrintVideosReport.action?";
	var initRequest = encodeURI(encodeURI(YAHOO.util.Dom.get("queryString").value));
	if (initRequest.length == 0) {
		initRequest="";
	}else{
		actionUrl += initRequest;
	}
	YAHOO.util.Event.addListener(window, "load", getDataSource());
</script>
</body>
</html>