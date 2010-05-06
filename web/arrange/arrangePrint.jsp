<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/json; charset=UTF-8">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/arrange/js/arrangePrint.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>

<style type="text/css">
.yui-skin-sam tr.yui-dt-odd td.yui-dt-asc, .yui-skin-sam tr.yui-dt-odd td.yui-dt-desc{
	background-color: #FFFFFF;
}
.yui-skin-sam tr.yui-dt-even td.yui-dt-asc, .yui-skin-sam tr.yui-dt-even td.yui-dt-desc{
	background-color: #FFFFFF;
}
.yui-skin-sam tr.yui-dt-odd{
	background-color: #FFFFFF;
}
.yui-skin-sam .yui-dt th{
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
	border:clear;
	border-color:#CBCBCB;
	border-style:solid;
	border-width:1px;
	text-align: center;
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
<br/><br/><br/>
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="wb" name="wb" width="0"></OBJECT>
<div class="noprint" align="center">
<div style="margin-left:-620px;">
<div id="printDiv" style="display:inline"></div>
<div id="closeDiv" style="display:inline"></div>
</div>
</div>
<div id="date" align="center" style="margin-bottom:20px;"><h1 id="title"></h1></div>
<div id="historyDiv" align="center"></div>
<s:form action="" namespace="/arrange" >
</s:form>
<script type="text/javascript">
var printBtn = new YAHOO.widget.Button({  
	label: "打印预览",  
	id: "printBtn",  
	container: "printDiv" }
	); 
printBtn.on("click",printAction);
function printAction(){
	wb.execwb(7,1);
}

var closeBtn = new YAHOO.widget.Button({  
	label: "关&nbsp;&nbsp;闭",  
	id: "closeBtn",  
	container: "closeDiv" }
	); 
closeBtn.on("click",closeAction);
function closeAction(){
	window.close();
}

	var selMonth="${param.selMonth}";
	if("" != selMonth){
		var date = selMonth.split("-");
		var hObj = document.getElementById("title");
		hObj.innerHTML= date[0]+"年"+date[1]+"月节目编排单";
	}
	initPrintTable();
</script>

</body>
</html>