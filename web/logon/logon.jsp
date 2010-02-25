<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
++<%=request.getContextPath() %>
<br/>
==${pageContext.request.contextPath}
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/logon/css/logon.css" />
</head>

<body>


<s:form action="doLogon" method="post" namespace="/framework" name="logonForm">
<div style="text-align:center;">
<s:actionmessage/>
<s:actionerror/>
	<div class="sxtv_logo">
	<table cellspacing="0" cellpadding="0" border="0" style="margin-top:100px;">
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

</s:form>
<SCRIPT LANGUAGE="JavaScript">
function userlogon(){

	document.logonForm.submit();
}
</SCRIPT>
</body>

</html>
