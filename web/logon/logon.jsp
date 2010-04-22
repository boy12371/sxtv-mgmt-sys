<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/logon/css/logon.css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/common/image/favicon.ico" ></link>
<link rel="icon" href="${pageContext.request.contextPath}/common/image/favicon.ico" ></link>
</head>

<body onkeydown="if(event.keyCode==13){userlogon();}">


<s:form action="doLogon" method="post" namespace="/framework" name="logonForm">

<div>
	<img border="0" style="float:left;position:absolute;margin-top:-100px;*margin-top:0px;" src="<%=request.getContextPath() %>/logon/image/cloud.png"/>
	
	<div align="center">
	<div class="sxtv_logo">
	<div>
	<img border="0" style="margin-top:-190px;*margin-top:110px;margin-left:-60px;" src="<%=request.getContextPath() %>/logon/image/title_bottom.png"/>
	<s:actionmessage/><s:actionerror/>
	<table cellspacing="0" cellpadding="0" border="0" style="margin-top:150px;*margin-top:0px;margin-left:25px;">
		<tr>
			<td class="tl"></td>
			<td class="tc"></td>
			<td class="tr"></td>
		</tr>
		<tr>
			<td class="ml"></td>
			<td style="width: 400px; height: 100px;" class="mc">
				<table cellspacing="0" cellpadding="0" border="0">
					<tr>
						<td align="left">
							<img border="0" src="<%=request.getContextPath() %>/logon/image/dssx_laugh2.png" />
						</td>
						<td class="bsep" />
						<td align="center" width="278">
							<table cellspacing="0" cellpadding="0" border="0">
								<tr valign="middle">
									<td align="right"><img border="0" src="<%=request.getContextPath() %>/logon/image/user.png"/></td>
									<td style="padding-left: 10px;">
										<input type="text" size="20" maxlength="100" name="userInfo.username" />
									</td>
								</tr>
								<tr valign="middle">
									<td align="right"><img border="0" src="<%=request.getContextPath() %>/logon/image/key.png"/></td>
									<td style="padding-left: 10px;">
										<input type="password" size="20" maxlength="50" name="userInfo.password" />
									</td>
								</tr>
								<tr valign="middle">
									<td align="center" colspan="2">
										<div class="logon_btn" onclick="userlogon();return false;">登&nbsp;&nbsp;录</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
			
			<td class="mr"></td>
		</tr>
		<tr>
			<td class="bl"></td>
			<td class="bc"></td>
			<td class="br"></td>
		</tr>
	</table>
	</div>
	</div>
	<div style="margin-top:10px"> 
		<div style="font-size:13px;color:#666666;display:inline">Copyright @ 2010-2015 All rights reserved by SXTVS</div> 
		<img border="0" style="margin-bottom:-13px;" src="<%=request.getContextPath() %>/logon/image/sxtv_logo_min.png"/>
	</div>
	</div>
</div>

</s:form>
<SCRIPT LANGUAGE="JavaScript">
function userlogon(){

	document.logonForm.submit();
}
</SCRIPT>
</body>

</html>
