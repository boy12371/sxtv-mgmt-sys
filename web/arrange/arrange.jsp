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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/tabview/assets/skins/sam/tabview.css" />
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
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/tabview/tabview-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/arrange/js/arrange.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
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
<body class="yui-skin-sam">

<h1>编排信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/clock.png" border="0">
<s:actionmessage/>
<s:actionerror/>

<div id="tabs" class="yui-navset"> 
    <ul class="yui-nav"> 
        <li class="selected"><a href="#tab1"><em>待排</em></a></li> 
        <li><a href="#tab2"><em>编排</em></a></li>
    </ul>            
    <div class="yui-content"> 
        <div>
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
		</div> 
		<div>
			<p>已编排影带列表，点击第一列图标可将影带移除编排列表，拖动记录可以调节播放时间。</p>
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
				<div id="submitBtnDiv"></div>
			</div>
        </div> 
    </div> 
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
	(function() {
	    var tabView = new YAHOO.widget.TabView('tabs');
	})();
	YAHOO.util.Event.addListener(window, "load", initArrangeReorderEvent);
</script>

</body>
</html>