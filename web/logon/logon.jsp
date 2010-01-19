<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="css/logon.css" />
</head>

<body>
<s:form action="doLogon" namespace="/logon" method="post">

	<table cellspacing="0" cellpadding="0" border="0">
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
							<img border="0" src="image/dssx_laugh2.png" />
						</td>
						<td class="bsep" />
						<td align="center" width="278">
							<table cellspacing="0" cellpadding="0" border="0">
								<tr valign="middle">
									<td align="right"><label>用户名</label></td>
									<td style="padding-left: 15px;">
										<input type="text" size="20" maxlength="100" name="txtuserid" />
									</td>
								</tr>
								<tr valign="middle">
									<td align="right"><label>密&nbsp;&nbsp;码</label></td>
									<td style="padding-left: 15px;">
										<input type="password" size="20" maxlength="50" name="txtpwd" />
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

</s:form>
</body>

</html>