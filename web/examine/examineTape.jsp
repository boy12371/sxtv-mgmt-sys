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

<h1>专业人员打分</h1><img class="pageImage" src="${pageContext.request.contextPath}/common/images/exam.png" border="0">
<s:actionmessage/>
<s:actionerror/>
<p>输入影带分值</p>
<s:form action="doExamineTape" namespace="/examine" >
	<s:hidden name="tapeScore.vedioID"/>
	<s:hidden name="tapeScore.id"/>
	<s:hidden name="uid"/>
	<s:hidden name="perform"/>
	<div align="center">
		<table class="inputTable">
			<s:if test="'modify'==perform">
			<tr>
				<td><label>打分人员</label></td>
				<td><s:property value="tapeScore.examiner"/></td>
			</tr>
			</s:if>
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
				<s:if test="'modify'==perform">
					<td><s:textfield cssClass="inputField" name="tapeScore.storyScore"/></td>
				</s:if>
				<s:else>
					<td><s:textfield cssClass="inputField" name="tapeScore.storyScore" value=""/></td>
				</s:else>
			</tr>
			<tr>
				<td><label>技术</label></td>
				<s:if test="'modify'==perform">
					<td><s:textfield cssClass="inputField" name="tapeScore.techScore"/></td>
				</s:if>
				<s:else>
					<td><s:textfield cssClass="inputField" name="tapeScore.techScore" value=""/></td>
				</s:else>
			</tr>
			<tr>
				<td><label>表演</label></td>
				<s:if test="'modify'==perform">
					<td><s:textfield cssClass="inputField" name="tapeScore.performScore"/></td>
				</s:if>
				<s:else>
					<td><s:textfield cssClass="inputField" name="tapeScore.performScore" value=""/></td>
				</s:else>
			</tr>
			<tr>
				<td><label>创新</label></td>
				<s:if test="'modify'==perform">
					<td><s:textfield cssClass="inputField" name="tapeScore.innovateScore"/></td>
				</s:if><s:else>
					<td><s:textfield cssClass="inputField" name="tapeScore.innovateScore" value=""/></td>
				</s:else>
			</tr>
			<tr>
				<td><label>购买意向</label></td>
				<td>
					<table><tr>
					<s:if test="'modify'==perform && '购买'==tapeScore.purchase">
						<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="1" checked="checked"/>购买</td>
						<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="0"/>不购买</td>
					</s:if><s:else>
						<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="1"/>购买</td>
						<td><input type="radio" class="radioSel" name="tapeScore.purchase" value="0" checked="checked"/>不购买</td>
					</s:else>
					</tr></table>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>获奖备选</label></td>
				<s:if test="'modify'==perform && '推荐'==tapeScore.award">
					<td><input type="checkbox" class="radioSel" name="tapeScore.award" value="1" checked="checked"/>推荐</td>
				</s:if><s:else>
					<td><input type="checkbox" class="radioSel" name="tapeScore.award" value="1"/>推荐</td>
				</s:else>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><label>导向</label></td>
				<s:if test="'modify'==perform && 1==tapeScore.orientation">
					<td><input type="checkbox" class="radioSel" name="tapeScore.orientation" value="1" checked="checked"/>不合格</td>
				</s:if><s:else>
					<td><input type="checkbox" class="radioSel" name="tapeScore.orientation" value="1"/>不合格</td>
				</s:else>
				<td></td>
				<td></td>
			</tr>
		</table>
		<div style="margin-top:20px;">
			<div id="okBtnDiv" style="display:inline;margin-right:50px;"></div>
			<div id="cancelBtnDiv" style="display:inline;"></div>
		</div>
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