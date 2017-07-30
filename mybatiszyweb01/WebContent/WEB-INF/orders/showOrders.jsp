<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
</head>
<body>
<table width="600" class="thin-border" align="center">
	<tr>
	<td colspan="5">订单状态:<c:if test="${o.status eq 1}">已下单</c:if>
						<c:if test="${o.status eq 2}">已付款</c:if>
						<c:if test="${o.status eq 3}">已送货</c:if>
						<c:if test="${o.status eq 4}">已确认收货</c:if>
	</td>
	</tr>
	<tr>
	<td colspan="5">订单日期:<fmt:formatDate value="${o.buyDate }" pattern="yyyy-MM-dd"/></td>
	</tr>
	<tr>
	<td colspan="5">购买人：${o.user.nickname }</td>
	</tr>
	<tr>
	<td colspan="5">地址:${o.address.name }&nbsp;电话:${o.address.phone }&nbsp;邮编:${o.address.postcode }</td>
	</tr>
	<tr>
	<td colspan="5">商品总价:${o.price }</td>
	</tr>
	<tr>
	<td>商品名称</td><td>数量</td><td>价格</td><td>图片</td><td>操作</td>
	</tr>
	<c:forEach items="${o.products }" var="p">
		<tr>
		<td>${p.product.name }</td><td>${p.number }</td>
		<td>${p.price }</td><td><img src="<%=request.getContextPath()%>/img/${p.product.img}" width="200" height="100"></td>
		<td><c:if test="${o.status lt 1}">没有操作</c:if><c:if test="${o.status eq 1}"><a href="orders.do?method=clearOrdersProduct&oid=${o.id }&pid=${p.id }">清空</a>&nbsp;
<a href="orders.do?method=addOrdersProductNumberInput&oid=${o.id }&pid=${p.id }">增加数量</a>&nbsp;
<a href="orders.do?method=reduceOrdersProductNumberInput&oid=${o.id }&pid=${p.id }">减少数量</a></c:if></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>