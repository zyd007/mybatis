<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改地址</title>
</head>
<body>
<form action="address.do?method=update" method="post">
<input type="hidden" name="id" value="${address.id }"/>
<table align="center" cellpadding="0" cellspacing="0" width="500" class="thin-border">
<tr>
<!-- span在css文件里定义了 -->

<td colspan="2">用户:${address.user.nickname}</td>
</tr>
<tr>
<td>详细地址:</td><td><input type="text" name="name" value="${address.name }"/><span class="errorColor">${errorMsg.name}</span></td>
</tr>
<tr><td>联系地址:</td><td><input type="text" name="phone" value="${address.phone }"/><span class="errorColor">${errorMsg.phone}</span></td></tr>
<tr><td>邮编:</td><td><input type="text" name="postcode"value="${address.postcode }"/></td></tr>
<tr>
<td colspan="2" align="center" ><input type="submit" style="font-size: 20px" value="更新地址"/>
<input type="reset" value="重置" style="font-size: 20px"/></td>
</tr>
</table>
</form>
</body>
</html>