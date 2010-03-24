<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>影视公司管理</title>
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


<h1>公司信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/company.png" border="0">
<p>编辑并添加公司信息</p>
<s:actionerror />

<s:form action="doAddCompany" namespace="/sys" method="post">
	<div align="center">
	<table class="inputTable">
		<tr>
			<td><label>公司名称：</label></td>
			<td><s:textfield cssClass="inputField" name="company.companyName" /><s:fielderror fieldName="company.companyName"/></td>
		</tr>
		<tr>
			<td><label>注册号：</label></td>
			<td><s:textfield cssClass="inputField" name="company.registrationNo" /><s:fielderror fieldName="company.registrationNo"/></td>
		</tr>
		<tr>
			<td><label>联系人：</label></td>
			<td><s:textfield cssClass="inputField" name="company.contactPerson" /><s:fielderror fieldName="company.contactPerson"/></td>
		</tr>
		<tr>
			<td><label>电话：</label></td>
			<td><s:textfield cssClass="inputField" name="company.phone" /><s:fielderror fieldName="company.phone"/></td>
		</tr>
		<tr>
			<td><label>备注：</label></td>
			<td><s:textarea cssClass="inputField" name="company.comments" cols="20" rows="2"><s:fielderror fieldName="company.comments"/></s:textarea></td>

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