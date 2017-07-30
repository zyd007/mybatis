<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购物车列表</title>
</head>
<body>
<form action="orders.do" method="post">
<table align="center" style="margin-top:10xp; " width="900" cellpadding="0" cellspacing="0" class="thin-border">
<tr>
<td>商品名称</td><td>商品数量</td><td>商品价格</td><td>操作</td>
</tr>
<tr>
<!-- 前一个是没有购物车后面是购物车为空 -->
<c:if test="${empty shopCart||shopCart.isEmpty }"><td colspan="4">购物车空空，请去<a href="product.do?method=list">商品列表</a>添加</td></c:if>
<c:if test="${not empty shopCart }">
<c:forEach items="${shopCart.shopProduct }" var="p">
<!-- 加上购物车的总价 -->
<c:set var="totalPrice" value="${p.price+totalPrice }"/>
<tr>
<c:if test="${p.number gt 0}">
<td>${p.product.name }</td><td>${p.number }</td><td>${p.price }</td>
<td><a href="orders.do?method=clearProduct&pid=${p.pid }">清空</a>&nbsp;
<a href="orders.do?method=addProductNumberInput&pid=${p.pid }">增加数量</a>&nbsp;
<a href="orders.do?method=reduceProductNumberInput&pid=${p.pid }">减少数量</a></td></c:if>
</tr>
</c:forEach>
<tr><td colspan="4">选择收货地址</td></tr>
<c:if test="${empty addresses }"><tr><td colspan="4">你的地址列表为空，
请添加<a href="address.do?method=addInput&userId=${loginUser.id }">地址</a></td></tr></c:if>
<c:forEach items="${addresses }" var="as" varStatus="vs">
<tr>
<c:if test="${vs.index eq 0}">
<td colspan="4">${as.name }[${as.phone }]
<input type="radio" name="address" checked="checked" value="${as.id }"/>
</td></c:if>
<c:if test="${vs.index ne 0}">
<td colspan="4">${as.name }[${as.phone }]
<input type="radio" name="address" value="${as.id }"/>
</td></c:if>
</tr>
</c:forEach>
<c:if test="${not empty addresses }">
<tr><td colspan="4"><a href="address.do?method=addInput&userId=${loginUser.id }">添加地址</a></td></tr>
</c:if>
<tr>
<td colspan="4"><c:if test="${!shopCart.isEmpty }"><a href="orders.do?method=clearCart">清空购物车</a></c:if>&nbsp;
<c:if test="${not empty shopCart && !shopCart.isEmpty &&not empty addresses}">
当前商品总价:${totalPrice }&nbsp;<input type="submit" value="提交订单"/></c:if>
<input type="hidden" name="price" value="${totalPrice }"/>
<input type="hidden" name="method" value="addOrders"/>
</td></tr>
</c:if>
</tr>
</table>
</form>
</body>
</html>