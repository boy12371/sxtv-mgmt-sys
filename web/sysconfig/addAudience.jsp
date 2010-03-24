<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加观众信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/calendar/assets/skins/sam/calendar.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/datatable/assets/skins/sam/datatable.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/paginator/assets/skins/sam/paginator.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>

</head>
<body class="yui-skin-sam">


<h1>观众信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/company.png" border="0">
<p>添加观众信息</p>
<s:actionerror />
<s:fielderror />

<s:form action="/sys/addAudience.action" method="post" validate="true">
	<div align="center">
	<table class="inputTable">
		<tr>
			<td><label>姓名：</label></td>
			<td><s:textfield cssClass="inputField" name="audience.name" /></td>
		</tr>
		<tr>
			<td><label>年龄：</label></td>
			<td><s:textfield cssClass="inputField" name="audience.age" value="0"/></td>
		</tr>
		<tr>
			<td><label>性别：</label></td>
			<td><s:select cssClass="selectField" name="audience.gender" list="#{'1':'男','0':'女'}" /></td>
		</tr>
		<tr>
			<td><label>职业：</label></td>
			<td><s:textfield cssClass="inputField" name="audience.career" /></td>
		</tr>
		<tr>
			<td><label>备注：</label></td>
			<td><s:textarea cssClass="inputField" name="audience.comments" cols="20" rows="2"></s:textarea></td>

		</tr>
		
		<tr>
			<td colspan="2" align="center">
			<div id="updateBtnDiv"></div>
			</td>
			</td>
		</tr>
	</table>
	</div>
	<script type="text/javascript">
	var submitToPass = new YAHOO.widget.Button( {
		type :"submit",
		label :"确定",
		id :"submitBtn",
		container :"updateBtnDiv"
	});
</script>
</s:form>
</body>
</html>