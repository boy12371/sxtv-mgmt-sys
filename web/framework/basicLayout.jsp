<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow:hidden;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	overflow: hidden;
	background: url(../images/body_bg.gif) 0 0 repeat-x #E8E8D0;
}
</style>
<link rel="stylesheet" type="text/css" href="../common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="../common/yui/build/tabview/assets/skins/sam/tabview.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script type="text/javascript" src="../common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="../common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="../common/yui/build/tabview/tabview-min.js"></script>
<script type="text/javascript" src="js/layout.js"></script>

</head>

<body class="yui-skin-sam">

<div id="topBranding" class="topBranding">
<div class="topSearch">
<img border="0" width="163" height="50" title="ecode" class="logo" alt="ecode" src="./images/logo.gif">
<form name="serch" action="#" method="post"><input type="text" value="输入剧名搜索" class="txtBox"><input type="submit" name="go" value="Go"
	class="go"><a href="#">高级</a>
</div>

</div>

<div id="tabView" class="yui-navset tabviewArea">
<ul class="yui-nav">
	<s:iterator value="tabs" status="st">
		<li><s:if test="subTabs.size() == 0">
			<a href="<s:property value='url'/>" id="<s:property value='id'/>" onclick="refreshIframe(this);return false;">
		</s:if> <s:else>
			<a href="#tab<s:property value='#st.index'/>" id="<s:property value='id'/>" onclick="showDefaultSubtab(this);return false";>
		</s:else> <em><s:property value="name" /></em></a></li>
	</s:iterator>
</ul>

<div class="yui-content subTabview" id="subTabview"><s:iterator value="tabs" status="st">
	<div id="tab<s:property value='#st.index'/>">
	<ul class="subTabUL">
		<s:iterator value="subTabs">
			<li><a href="<s:property value='url'/>" target="contentFrame" id="<s:property value='id'/>"
				onclick="highLightSubtab(this);refreshIframe(this);return false;"> <s:property value="name" /> </a></li>
		</s:iterator>
	</ul>
	</div>
</s:iterator></div>
</div>

<div style="overflow-y: auto; overflow-x: hidden" id="contentDiv">
<div class="bodyTop"></div>
<div align="center"><iframe id="contentFrameId" name="contentFrame" class="contentIframe" frameborder='0' scrolling='no' style="height: 650px"
	src="" onload="resizeIframe();return false;"> </iframe></div>
<div style="margin-top: 30px;" /></div>
<script language="JavaScript">
	( function() {
		var tabView = new YAHOO.widget.TabView('tabView');
	})();
	resizeContentDiv();
	resizeTabview();
	window.onresize = function() {
		resizeContentDiv();
	}
</script>
</body>
</html>
