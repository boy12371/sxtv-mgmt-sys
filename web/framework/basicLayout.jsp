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

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />

</head>

<body class="yui-skin-sam">

<div id="topBranding" class="topBranding">
<form id="form1" name="form1" method="post" action="/tv/search/searchVideoByName.action" target="contentFrameId">
<table style="margin-top:-10px;width:100%;">
<tr>
<td><img border="0" style="margin-left:20px;" src="./images/TVlogo.png" onclick="changeLogo(this);"/></td>
<td>
	<div class="divSearch">
		<table><tr style="height: 40px;">
			<td><input type="text" class="inputSearch" id="searchinput" name="query"/><div id="searchcontainer"></div>
			</td>
			<td>
			<input type="submit" value="GO" />
			<!-- img border="0" width="33" height="33" style="margin-top:5px;" src="./images/telescope.png"/--></td>
		</tr></table>

<table><tr style="height: 40px;">
			<td>&nbsp;</td>
			<td><a id="changePassword" href="#">修改密码</a> ｜ <a>退出</a></td>
		</tr></table>
	</div>
</td>
</tr>
</table>
</form>
</div>
	
 <div id="passwordForm" style="display:none">
<h1 id="popup_title">提示</h1>
            <p><label>原 密 码:</label><input type="password" name="oldPwd" /></p>
            <p><label>新 密 码:</label><input type="password" name="newPwd" /></p>
			<p><label>确认密码:</label><input type="password" name="confirmPwd" /></p>
			<p><input type="button" name="demo1" id="yes" value="确认" />
			<input type="button" name="cancel" id="cancel" value="取消" /></p>
 </div>

<script type="text/javascript"> 

YAHOO.example.Centered = autoCompleteVideoName(); 
	
$(document).ready(function() { 
	 
    $('#changePassword').click(function() { 
        $.blockUI({ message: $('#passwordForm'), css: { width: '475px' } }); 
    }); 

    $('#yes').click(function() { 
        // update the block message 
        $.blockUI({ message: "<h1>请稍等......</h1>" });
        $.ajax({ 
            url: 'wait.php', 
            cache: false, 
            complete: function() { 
                // unblock when remote call returns 
                $.unblockUI(); 
            } 
        }); 
    }); 

    $('#cancel').click(function() { 
        $.unblockUI(); 
        return false; 
    }); 

}); 
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

	function changeLogo(self){
		if(self.src.indexOf("TVlogo") != -1){
			self.src = "./images/sxlogo.png";
		}else{
			self.src = "./images/TVlogo.png";
		}
	}
</script>
</body>
</html>
