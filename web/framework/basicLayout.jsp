<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
body {
	margin:0;
	padding:0;
	background:url(../images/body_bg.gif) 0 0 repeat-x #E8E8D0;
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
	
</div>

<div id="tabView" class="yui-navset tabviewArea">
<ul class="yui-nav">
	<s:iterator value="tabs" status="st">
	<li><a href="#tab<s:property value='#st.index'/>"><em><s:property value="name"/></em></a></li>
	</s:iterator>
</ul>

<div class="yui-content subTabview">
<s:iterator value="tabs" status="st">
<div id="tab<s:property value='#st.index'/>">
<ul>
	<s:iterator value="subTabs">
	<li><a href="<s:property value='URL'/>" target="contentFrame" onclick="resizeIframe(this);return false;"><s:property value="name"/></a></li>
	</s:iterator>
</ul>
</div>
</s:iterator>
</div>
</div>

<div class="bodyTop">
</div>

<iframe id="contentFrameId" name="contentFrame" style="width:100%;height:500px" frameborder='0' scrolling='no' 
	src=""></iframe>

<script>
	( function() {
		var tabView = new YAHOO.widget.TabView('tabView');
	})();
</script>


</body>
</html>
