<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<!-- 同一文件不用加其他 -->
<jsp:include page="inc.jsp"/>
<table align="center" width="800" cellpadding="0" cellspacing="0" class="thin-border">
<tr>
	<td>用户名</td><td>${user.username }</td>
</tr>
<tr>
	<td>昵称</td><td>${user.nickname }</td>
</tr>
<tr>
	<td>用户类型</td><td><c:if test="${user.type eq 1 }">管理员</c:if>
<c:if test="${user.type eq 0}">普通用户</c:if>&nbsp;
</td>
</tr>
<tr><td colspan="2"><a href="orders.do?method=userList&uid=${loginUser.id }">查询订单</a></td></tr>
<tr>
<tr><td colspan="2">用户联系地址<c:if test="${user.id eq loginUser.id }">
<a href="<%=request.getContextPath()%>/address.do?method=addInput&userId=${loginUser.id}">&nbsp;添加地址</a>
</c:if></td></tr>
<c:if test="${empty user.addresses }">
<tr><td colspan="2"><span class="errorColor">还没有地址！赶紧添加吧</span></td></tr>
</c:if>
<c:if test="${not empty user.addresses }">
<c:forEach items="${user.addresses }" var="address">
<tr><td colspan="2">${address.name }&nbsp;${address.phone }&nbsp;${address.postcode }
&nbsp;<a href="<%=request.getContextPath()%>/address.do?method=updateInput&id=${address.id}">修改</a>
&nbsp;<a href="<%=request.getContextPath()%>/address.do?method=delete&id=${address.id}">删除</a></td></tr>
</c:forEach>
</c:if>
</table>
</body>
</html>