<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品类别查询</title>
</head>
<body>
<jsp:include page="inc.jsp"/>
<table width="600" class="thin-border" align="center">
	<tr>
	<td>分类名称：${category.name }</td>
	</tr>
	<tr>
	<td>商品列表:<c:if test="${empty category.products}">没有商品
	<c:if test="${loginUser.type eq 1 }">,添加<a href="<%=request.getContextPath()%>/product.do?method=addInput">商品</a>吧</c:if></c:if>
	<c:forEach items="${category.products }" var="p">
	
	<span style="font-size: 20px"><a href="product.do?method=show&id=${p.id }">${p.name }</a></span>&nbsp;
	</c:forEach>
	</td>
	</tr>
	<!-- 这个if是为了消除空行 -->
	<c:if test="${loginUser.type eq 1}">
	<tr>
	<td>
	<a href="category.do?method=updateInput&id=${category.id }">修改</a>
	&nbsp;
	<c:if test="${empty category.products}"><a href="category.do?method=delete&id=${category.id }">删除</a></c:if>
	</td>
	</tr>
	</c:if>
</table>
</body>
</html>