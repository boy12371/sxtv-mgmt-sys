<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/marketRate/js/marketRate.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/jquery/jqueryAlerts/jquery.alerts.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="yui-skin-sam">


<h1>影带信息</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/tape.png" border="0"/>
<p>影带相关信息</p>

<div align="center">
<table class="inputTable">

	<tr>
		<td><label>影带编号</label></td>
		<td><s:property value="vv.id" /></td>
</tr><tr>
		<td><label>剧目名称</label></td>
		<td><s:property value="vv.vedioName" /></td>
	</tr>
	<tr>
		<td><label>影视公司</label></td>
		<td><s:property value="vv.companyID" /></td>
		</tr><tr><td><label>收带时间</label></td>
		<td><s:date name="vv.dateComing" format="yyyy-MM-dd" /></td>
	</tr>
	<tr>
		<td><label>栏目</label></td>
		<td><s:property value="vv.topic" /></td>
		</tr><tr><td><label>题材</label></td>
		<td><s:property value="vv.subject" /></td>
	</tr>
	<tr>
		<td><label>状态</label></td>
		<td><s:property value="vv.status" /></td>
		</tr><tr><td><label>备注</label></td>
		<td><s:property value="vv.comments" /></td>
	</tr>

</table>
</div>
<h1>收视率/市场份额</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/44.png" border="0">
<p>录入剧目收视率及市场份额</p>
<s:form action="updateMarketRate" namespace="/vedio" method="post">
<div align="center">
	<table>
		<tr>
			<td><label>收视率</label></td>
			<td><s:textfield cssClass="inputField" name="vedio.audienceRating" id="audienceRate" /> <s:hidden name="vv.id" /></td>
			<td><label>市场份额</label></td>
			<td><s:textfield cssClass="inputField" name="vedio.marketShare" id="marketShare" /></td>
		</tr>
		<tr>
			<td colspan="4" align="right">
			<div id="submitBtn" align="center"></div>
			</td> 
		</tr>
	</table>
</s:form>
</div>
<script type="text/javascript">
	var submitBtn = new YAHOO.widget.Button( {
		type :"button",
		label :"确 定",
		id :"submitBtn",
		container :"submitBtn"
	});
	submitBtn.on("click",function(){
			if(checkNumeric()){
				var form = document.forms[0];
				form.submit();
			}
			return;
		});
</script>
</body>
</html>