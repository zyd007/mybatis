<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>
<jsp:include page="inc.jsp"/>
<form action="user.do?method=add" method="post">
<table align="center" cellpadding="0" cellspacing="0" width="500" class="thin-border">
<tr>
<!-- span在css文件里定义了 -->
<td>用户名</td><td><input type="text" name="username" value="${param.username }"/><span class="errorColor">${errorMsg.username}</span></td>
</tr>
<tr>
<td>密码</td><td><input type="password" name="password" value="${param.password }"/><span class="errorColor">${errorMsg.password }</span></td>
</tr>
<tr>
<td>昵称</td><td><input type="text" name="nickname" value="${param.nickname }"/></td>
</tr>
<tr>
<td colspan="2" align="center" ><input type="submit" style="font-size: 20px" value="注册"/>
<input type="reset" value="重置" style="font-size: 20px"/></td>
</tr>
</table>
</form>
</body>
</html>