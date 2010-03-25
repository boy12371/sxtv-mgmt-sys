<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/fonts/fonts-min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/yui/build/button/assets/skins/sam/button.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/yahoo-dom-event/yahoo-dom-event.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/element/element-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/yui/build/button/button-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common.js"></script>
</head>
<body class="yui-skin-sam">
<s:actionerror/>
<h1>专业人员打分</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/exam.png" border="0">
<p>打分人员<s:property value="tapeScore.examiner"/>已对该影带打分</p>
<s:form action="doExamineTape" namespace="/examine" >
	<div align="center">
		<table class="inputTable">
			<tr>
				<td><label>打分人员</label></td>
				<td><s:property value="tapeScore.examiner"/></td>
			</tr>
			<tr>
				<td><label>影带编号</label></td>
				<td><s:property value="tapeScore.vedioID"/></td>
			</tr>
			<tr>
				<td><label>影带名称</label></td>
				<td><s:property value="tapeScore.vedioName"/></td>
			</tr>
			<tr>
				<td><label>故事</label></td>
				<td><s:property value="tapeScore.storyScore"/></td>
			</tr>
			<tr>
				<td><label>技术</label></td>
				<td><s:property value="tapeScore.techScore"/></td>
			</tr>
			<tr>
				<td><label>表演</label></td>
				<td><s:property value="tapeScore.performScore"/></td>
			</tr>
			<tr>
				<td><label>创新</label></td>
				<td><s:property value="tapeScore.innovateScore"/></td>
			</tr>
			<tr>
				<td><label>购买意向</label></td>
				<td><s:property value="tapeScore.purchase"/></td>
			</tr>
			<tr>
				<td><label>获奖备选</label></td>
				<td><s:property value="tapeScore.award"/></td>
			</tr>
			<tr>
				<td><label>导向</label></td>
				<s:if test="1==tapeScore.orientation">
					<td>不合格</td>
				</s:if><s:else>
					<td>合格</td>
				</s:else>
			</tr>
		</table>
		<div style="margin-top:20px;" align="center">
			<div id="cancelBtnDiv"></div>
		</div>
	</div>
</s:form>

<script language="JavaScript">
function cancelAction(){
	window.location="/tv/examine/toUnExaminedTapes.action";
}
var cancelBtn = new YAHOO.widget.Button({  
					label: "返&nbsp;&nbsp;回",  
					id: "backBtn",  
					container: "cancelBtnDiv" }
					);
cancelBtn.on("click",cancelAction);
</script>
</body>
</html>