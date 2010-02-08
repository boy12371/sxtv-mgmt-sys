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
</head>
<body class="yui-skin-sam">
<s:actionerror/>
<h1>专业人员打分</h1>
<p>输入影带分值</p>
<s:form action="doExamineTape" namespace="/examine" >
	<s:hidden name="tapeScore.vedioID"/>
	<div>
		<table class="inputTable">
			<tr>
				<td><label>影带编号：</label></td>
				<td><s:property value="tapeScore.vedioID"/></td>
				<td><label>影带名称：</label></td>
				<td><s:property value="tapeScore.vedioName"/></td>
			</tr>
			<tr>
				<td><label>故事：</label></td>
				<td><s:textfield name="tapeScore.storyScore" value=""/></td>
				<td><label>技术：</label></td>
				<td><s:textfield name="tapeScore.techScore" value=""/></td>
			</tr>
			<tr>
				<td><label>表演：</label></td>
				<td><s:textfield name="tapeScore.performScore" value=""/></td>
				<td><label>创新：</label></td>
				<td><s:textfield name="tapeScore.innovateScore" value=""/></td>
			</tr>
			<tr>
				<td><label>获奖情况：</label></td>
				<td>
					<table><tr>
					<td><input type="radio" class="radioSel" name="tapeScore.award" value="1"/>获奖</td>
					<td><input type="radio" class="radioSel" name="tapeScore.award" value="0"/>不获奖</td>
					</tr></table>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>购买意向：</label></td>
				<td>
					<table><tr>
					<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="1"/>购买</td>
					<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="0"/>不购买</td>
					</tr></table>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td align="center">
					<div style="margin-top:30px;" id="okBtnDiv"></div>
				</td>
				<td align="center">
					<div style="margin-top:30px;" id="cancelBtnDiv"></div>
				</td>
				<td></td>
			</tr>
		</table>
	</div>
</s:form>

<script language="JavaScript">
function cancelAction(){
	window.location="/tv/examine/toUnExaminedTapes.action";
}
var okBtn = new YAHOO.widget.Button({  
					type: "submit",  
					label: "确&nbsp;&nbsp;定",  
					id: "okBtn",  
					container: "okBtnDiv" }
					); 

var cancelBtn = new YAHOO.widget.Button({  
					label: "取&nbsp;&nbsp;消",  
					id: "okBtn",  
					container: "cancelBtnDiv" }
					);
cancelBtn.on("click",cancelAction);
</script>
</body>
</html>