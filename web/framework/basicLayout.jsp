<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" style="overflow:hidden;">
<head>
<sx:head />

<link rel="shortcuticon" href="${pageContext.request.contextPath}/common/logo/sxtvLogo.ico" ></link>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	overflow: hidden;
	background: url(../images/body_bg.gif) 0 0 repeat-x #E8E8D0;
}
</style>



<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/tabview/assets/skins/sam/tabview.css" />
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/tabview/tabview-min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/framework/js/layout.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" /> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script> 

<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>

<body class="yui-skin-sam">
<div id="topBranding" class="topBranding">
<form id="form1" name="form1" method="post" action="/tv/search/searchVideoByName.action" target="contentFrameId">
<table style="margin-top:-10px;width:100%;">
<tr>
<td style="width:80px;"><img border="0" style="margin-left:20px;" src="./images/TVlogo.png"/></td>
<td><img border="0" width="500" src="./images/titleText.png"/></td>
<td>
	<div class="divSearch">
		<table><tr style="height: 40px;">
			<td>
				<img border="0" width="33" height="33" style="margin-top:5px;" src="./images/telescope.png"/>
			</td>
			<td>
				<input type="text" class="inputSearch" id="searchinput" name="query"/><div id="searchcontainer"></div>
			</td>
			<td><div id="goBtnDiv" class="goBtn"></div></td>
		</tr></table>

		<table><tr style="height: 40px;">
			<td>&nbsp;</td>
			<td>
				<a id="changePassword" href="#" class="globalLink">修改密码</a> 
				<span style="color:#FFFFFF">&nbsp;|&nbsp;</span>
				<a id="logout" href="#" class="globalLink" onclick="logout();return false;">退出</a>
			</td>
		</tr></table>
	</div>
</td>
</tr>
</table>
</form>
</div>
	
 <div id="passwordForm" style="display:none">
	<h1 class="passwordTitle">修改密码</h1>
    <p><label>原&nbsp;密&nbsp;码:</label><input type="password" name="oldPwd" id="oldPwd"/></p>
    <p><label>新&nbsp;密&nbsp;码:</label><input type="password" name="newPwd" id="newPwd"/></p>
	<p><label>确认密码:&nbsp;</label><input type="password" name="confirmPwd" id="confirmPwd"/></p>
	<p><input type="button" name="demo1" id="yes" value="确认" />
	<input type="button" name="cancel" id="cancel" value="取消" /></p>
 </div>

<script type="text/javascript"> 

YAHOO.example.Centered = autoCompleteVideoName(); 

</script>
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

<div class="yui-content subTabview" id="subTabview">
<s:iterator value="tabs" status="st">
	<div id="tab<s:property value='#st.index'/>">
	<ul class="subTabUL">
		<s:iterator value="subTabs" status="xt">
			<s:if test="#xt.index == 0">
			<li style="background-image:none;">
			</s:if><s:else>
			<li>
			</s:else>
				<a href="<s:property value='url'/>" target="contentFrame" id="<s:property value='id'/>" onclick="highLightSubtab(this);refreshIframe(this);return false;"> 
					<s:property value="name" /> 
				</a>
			</li>
		</s:iterator>
	</ul>
	</div>
</s:iterator>
</div>
</div>

<div style="overflow-y: auto; overflow-x: hidden" id="contentDiv">
<div class="bodyTop"></div>
<div align="center"><iframe id="contentFrameId" name="contentFrame" class="contentIframe" frameborder='0' scrolling='no' style="height: 650px"
	src="" onload="resizeIframe();return false;"> </iframe></div>
<div style="margin-top: 30px;" /></div>
<!-- 
<div class="footer" align="center">
	<ul>
	<li><a href="#">首页</a>|</li>
	<li><a href="#">About Us</a>|</li>
	<li><a href="#">Services</a>|</li>
	<li><a href="#">Solutions</a>|</li>
	<li><a href="#">Testimonials</a>|</li>
	<li><a href="#">Projects</a>|</li>
	<li><a href="#">Online Jobs</a>|</li>
	<li><a href="#">Login</a>|</li>
	<li><a href="#">Submission</a>|</li>
	<li><a href="#">Contact Us</a></li>
	</ul>
</div>
 -->
<script language="JavaScript">
	( function() {
		var tabView = new YAHOO.widget.TabView('tabView');
	})();

	var goBtn = new YAHOO.widget.Button({  
		type: "submit",  
		label: "GO",  
		id: "goBtn",  
		container: "goBtnDiv" }
		); 
	
	resizeContentDiv();
	resizeTabview();
	window.onresize = function() {
		resizeContentDiv();
	}

	var userInfo = {
		userId:${userInfo.userId},
		username:"${userInfo.username}",
		password:"${userInfo.password}",
		role:${userInfo.role}
	};
</script>
</body>
</html>
