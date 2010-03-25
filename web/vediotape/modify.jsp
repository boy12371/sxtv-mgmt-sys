<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/menu/assets/skins/sam/menu.css" />

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/container/container_core-min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>


<title>Insert title here</title>
</head>


<body class="yui-skin-sam">

<h1>影带信息</h1>
<p>确定并点击按钮，影带将被重新审核</p>
<s:actionerror />
<s:form action="doModification" method="post" namespace="/vedio">
<div align="center">

	<table class="inputTable">
		<tr>
			<td><label>编号</label></td>
			<td><s:property value="vv.id" /><s:hidden name="vv.id" /></td>
</tr><tr>
			<td><label>剧目名称</label></td>
			<td><s:property value="vv.vedioName" /></td>

		</tr>
		<tr>
			<td><label>栏目</label></td>
			<td><s:property value="vv.subject" /></td>
			</tr><tr><td><label>题材</label></td>
			<td><s:property value="vv.topic" /></td>

		</tr>
		<tr>
			<td><label>影视公司</label></td>
			<td><s:property value="vv.companyID" /></td>
			</tr><tr><td><label>收录日期</label></td>
			<td><s:date name="vv.dateComing" format="yyyy-MM-dd" /></td>

		</tr>
		<tr>
			<td><label>状态</label></td>
			<td><s:property value="vv.status" /></td>
			</tr><tr><td><label>观众评价</label></td>
			<td><s:iterator value="vv.watching" var="w">
				<s:property value="#w.key" /> : <s:property value="#w.value" />
			</s:iterator></td>

		</tr>
		

		<tr>
			<td colspan="2" height="20px">
			<s:if test="vedio.status.id==3">
				<s:hidden name="vv.status" value="5" />
				<div id="submitDiv" align="center"></div>
				<script type="text/javascript">
				var submitToPass = new YAHOO.widget.Button({  
					type: "submit",  
					label: "修改完成",  
					id: "finishModify",  
					container: "submitDiv" }
					);
				</script>
			</s:if> 
			<s:else>
				<s:hidden name="vv.status" value="3" />
				<div id="submitDiv" align="center"></div>

				<script type="text/javascript">
				var submitToPass = new YAHOO.widget.Button({  
					type: "submit",  
					label: "撤销编排",  
					id: "cancelArrange",  
					container: "submitDiv" }
					);
				</script>
			</s:else></td>
		</tr>

	</table>
</div>
</s:form>
</body>
</html>