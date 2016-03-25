<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<form action="login.action" method="post">
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="userId" id="userId"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="登录" onclick="submit()"/></td>
			</tr>
		</table>
	</form>
	<a href="registe.jsp" style="text-decoration:none">去关注</a>
</body>
</html>