<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<sx:head extraLocales="UTF-8"/>
<link rel="shortcuticon"
	href="${pageContext.request.contextPath}/common/logo/sxtvLogo.ico"></link>
<link rel="icon"
	href="${pageContext.request.contextPath}/common/logo/sxtvLogo.ico"></link>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/reset-fonts-grids/reset-fonts-grids.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/resize/assets/skins/sam/resize.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/layout/assets/skins/sam/layout.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/autocomplete/assets/skins/sam/autocomplete.css" />
 
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />


 

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/calendar/calendar-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/connection/connection-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/json/json-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/utilities/utilities.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/menu/menu-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/resize/resize-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/layout/layout-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datasource/datasource-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/datatable/datatable-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/paginator/paginator-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/animation/animation-min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/autocomplete/autocomplete-min.js"></script>



<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/framework/js/basicLayout.js"></script>

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: url('./images/bg.jpg') repeat scroll center center #000000;
}

#productsandservices2 {
	position: static;
}

#productsandservices2 .yuimenuitemlabel {
	_zoom: 1;
}

#productsandservices2 .yuimenu .yuimenuitemlabel {
	_zoom: normal;
}


.yui-skin-sam .yui-layout .yui-layout-unit-top div.yui-layout-bd {
	border: none;
}
#yui-gen8{
	opacity:0.8;
	filter:Alpha(opacity=80);
}
#yui-gen3{
	opacity:0.8;
	filter:Alpha(opacity=80);
}
#yui-gen1{
	opacity:0.9;
	/*filter:Alpha(opacity=90);*/
}
.yui-skin-sam .yuimenu .bd {
	background-color: #F2F2F2;
}

#productsandservices2 .bd {
	border: none;
}

#productsandservices2 .bd .first-of-type .bd {
	border: 1px solid #808080;
}
.yui-skin-sam .yui-layout .yui-layout-unit div.yui-layout-bd {
    border:0;
}
.yui-skin-sam .yui-ac-input {
    position: relative;
    width: 120px;
}
</style>

</head>

<body class="yui-skin-sam">
<div class="logoStyle">
<%@include file="./sxtv.jsp" %>
</div>
<div id="top1">
    <div id="productsandservices" class="yuimenubar yuimenubarnav"> 
        <div class="bd"> 
        <div style="float:right;margin-right:20px;width:400px">
        <div style="float:left">
        <form style="width:200px" id="searchForm" onsubmit="beforeSearchSubmit(this);" method="post" action="/tv/search/searchVideoByName.action" target="mainContentFrame" >
		<input type="text" style="float:left" id="searchinput" name="query"/><div id="searchcontainer"></div><input type="submit" id="goBtnDiv" value="Go" />
		</form>
		</div>
		<span style="float:right">
		用户:&nbsp;<s:property value="#session.SessionUserInfo.username"/>&nbsp;&nbsp;
		<a href="#">修改密码</a>&nbsp;&nbsp;<a href="#" onclick="logout();return false">退出</a>
		</span>
		</div>
            <ul class="first-of-type">
			<s:iterator value="#session.NAV_TABS" status="st" var="ftab">
				<li class="yuimenubaritem">
					<s:if test="#ftab.subTabs.size() == 0">
						<a class="yuimenubaritemlabel" href="#" onclick="refreshIframe('<s:property value='url'/>');return false;" id="<s:property value='id'/>"><s:property value="name" /></a>
					</s:if> 
					<s:else>
						<a class="yuimenubaritemlabel" href="#tab<s:property value='#ftab.id'/>" id="<s:property value='id'/>"><s:property value="name" /></a>
							<div id="tab<s:property value='#ftab.id'/>" class="yuimenu"> 
						      <div class="bd"> 
						          <ul class="first-of-type">
							          <s:iterator value="#ftab.subTabs" status="xt" var="stab">
						          		<li class="yuimenuitem">
						                  <a class=yuimenuitemlabel onclick="refreshIframe('<s:property value='#stab.url'/>');return false;" href="<s:property value='#stab.url'/>" id="<s:property value='#stab.id'/>"><s:property value="#stab.name" /></a>
						                </li>
								      </s:iterator>
						          </ul> 
						      </div> 
						    </div>
					</s:else>
				</li>
			</s:iterator>
			</ul>         
                       
        </div> 
    </div>
</div>

<div id="bottom1">
<p>温馨提示：使用Firefox或Google Chrome浏览器，将会得到更快的浏览速度及更好的显示效果.</p><br/>

</div>
<div id="right1">
<s:form action="searchVideos" namespace="/search" target="mainContentFrame">
<div align="center" style="margin-top:20px">
<table>
	<tr>
		<td><label>影带编号&nbsp;&nbsp;</label></td>
		<td><input class="inputField" type="text" name="video.id" id="vid" /></td>
	</tr>
	<tr>
		<td><label>剧目名称&nbsp;&nbsp;</label></td>
		<td><input class="inputField" type="text" name="video.vedioName" id="vname" /></td>
	</tr>
	<tr>
		<td><label>影视公司&nbsp;&nbsp;</label></td>
		<td><s:select cssClass="selectField" list="comList" listKey="id" listValue="companyName" id="vcompany" name="video.companyID.id" headerKey="0" headerValue="" /></td>
	</tr>
	<tr>
		<td><label>题材&nbsp;&nbsp;</label></td>
		<td><s:select cssClass="selectField" list="topList" listKey="id" listValue="topicName" id="vtopic" name="video.topic.id" headerKey="0" headerValue=""/></td>
	</tr>
	<tr>
		<td><label>栏目&nbsp;&nbsp;</label></td>
		<td><s:select cssClass="selectField" list="subList" listKey="id" listValue="subjectName" id="vsubject" name="video.subject.id" headerKey="0" headerValue=""/></td>
	</tr>
	<tr>
		<td><label>状态&nbsp;&nbsp;</label></td>
		<td><s:select cssClass="selectField" list="statusList" listKey="id" listValue="status" id="vstatus" name="video.status.id" headerKey="0" headerValue=""/></td>

	</tr>
	<tr>
		<td><label>从&nbsp;&nbsp;</label></td>
		<td><sx:datetimepicker name="startDate" displayFormat="yyyy-MM-dd" toggleType="explode" id="startDate" value="%{'today'}" language="UTF-8" weekStartsOn="0"/></td>
	</tr>
	<tr>
		<td><label>至&nbsp;&nbsp;</label></td>
		<td><sx:datetimepicker name="endDate" displayFormat="yyyy-MM-dd" toggleType="explode" id="endDate" value="%{'today'}" language="UTF-8"  weekStartsOn="0"/></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
		<div id="queryBtnDiv"></div>
		</td>
	</tr>
</table>
</div>
</s:form>
</div>

<div id="center1" style="height:100%;overflow-x:auto;overflow-y:hidden;">

<div align="center" id="queryDiv" style="display:none; overflow: auto; margin-top: 20px; height:100%;" >
	<div style="float:left;">
	<div id="tableOption"></div>
	<div id="printBtn" align="right"></div>
    <div id="colDiv" style="display:none;z-index:1002;position:absolute;background-color:white;overflow:auto;border:5px solid #999999"></div>
	</div>
	<div id="dynamicdata"></div>
</div>
<div style="height:100%;overflow-x:auto;overflow-y:hidden;">
<iframe id="mainContentFrame" style="height:100%;width:100%;border:0" src="/tv/framework/sxtv.jsp">

</iframe>
</div>
</div>

<script type="text/javascript">
var userInfo = {
		userId:${userInfo.userId},
		username:"${userInfo.username}",
		password:"${userInfo.password}",
		strRoles:"${userInfo.strRoles}"
	};
YAHOO.util.Event.addListener(window, "load", initPageElements);
	
</script>
</body>
</html>
