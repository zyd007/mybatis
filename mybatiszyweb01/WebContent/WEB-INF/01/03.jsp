<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border-top: 1px solid #000;
	border-left: 1px solid #000;
	
}
table td {
	border-bottom: 1px solid #000;
	border-right: 1px solid #000;
	padding: 5px;
}
</style>
</head>
<body>

${user.age}---${user.username}--${user.nickname}&nbsp;
<c:if test="${user.age gt 18}">成年了</c:if>
<c:if test="${empty aa }">aa为空</c:if>
<c:if test="${not empty aa}">aa不为空</c:if>
<!-- choose里面选择一个 -->
<c:choose>
	<c:when test="${user.age eq 15}">15</c:when>
	<c:when test="${user.age gt 18}">成年了</c:when>
	<c:otherwise>未成年</c:otherwise>
</c:choose>
<table align="center" width="600" cellpadding="0" cellspacing="0">
	<tr><td>用户名</td><td>昵称</td><td>年龄</td></tr>
	<c:forEach var="u" items="${users}" varStatus="s" 	>
		<tr <c:if test="${s.index%2==0 }">style="background:#FF0;color:#45E"</c:if>>
		<td>${u.age}</td><td>${u.username}--${s.index}</td><td>${u.nickname}</td></tr>
	</c:forEach>
</table>
</body>
</html>