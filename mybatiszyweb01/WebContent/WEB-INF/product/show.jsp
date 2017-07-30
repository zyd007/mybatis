<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品查看</title>
</head>
<body>
<jsp:include page="inc.jsp"/>

<table width="400" class="thin-border" align="center">
	<tr><td colspan="2" align="center"><img height="200" width="200" src="<%=request.getContextPath()%>/img/${p.img}"></td></tr>
	<tr>
	<td>商品名称：</td><td>${p.name }</td>
	</tr>
	<tr>
	<td>商品价格：</td><td>${p.price }</td>
	</tr>
	<tr>
	<td>商品库存：</td><td>${p.stock }</td>
	</tr>
	<tr>
	<td colspan="2"><c:if test="${p.status eq 1 }"><a href="orders.do?method=addToCart&id=${p.id }">加入购物车</a></c:if>
								<c:if test="${p.status ne 1}">已下架</c:if>
	</td>
	</tr>
	<tr>
	<td colspan="2">商品介绍：</td>
	</tr>
	<tr>
	<td colspan="2">${p.intro }</td>
	</tr>
</table>
</body>
</html>