<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品列表</title>
</head>
<body>
<!-- 同一文件不用加其他 -->
<jsp:include page="inc.jsp"/>
<table align="center" style="margin-top:10xp; " width="900" cellpadding="0" cellspacing="0" class="thin-border">
<tr>
	<td align="center">商品图片</td><td>商品名称</td><td>商品价格</td><td>商品的库存</td>
	<td>商品状态</td><td>介绍</td><td>操作</td>
</tr>

<c:forEach items="${products.datas }" var="p" >
<tr>
<td align="center" width="120"><img align="middle" width="100"  height="50" src="<%=request.getContextPath()%>/img/${p.img}"></td>
<td><a href="product.do?method=show&id=${p.id }">${p.name }</a></td><td>${p.price }</td>
<td>${p.stock }<c:if test="${loginUser.type eq 1 }"><a href="product.do?method=addStockInput&id=${p.id }">添加</a></c:if></td>

<td><c:if test="${p.status eq 1 }">可购买</c:if>
<c:if test="${p.status eq 0}">未知&nbsp;</c:if>
<c:if test="${p.status eq -1}"><span style="color:#832">已下架</span></c:if>&nbsp;
<c:if test="${loginUser.type eq 1}"><a href="product.do?method=changeStatus&id=${p.id }">变更</a>
</c:if></td>
<td width="200">${p.intro }</td>
<c:if test="${loginUser.type eq 1}">
<td><a href="product.do?method=updateInput&id=${p.id }">修改</a> 
 <a href="product.do?method=delete&id=${p.id}">删除</a>
 <c:if test="${p.status eq 1}">
 <a href="orders.do?method=addToCart&id=${p.id }">加入购物车</a></c:if>
 <c:if test="${p.status eq -1}"> </c:if>
 </td></c:if>
 <c:if test="${loginUser.type eq 0 }">
 <td><c:if test="${p.status eq 1}">
 <a href="orders.do?method=addToCart&id=${p.id }">加入购物车</a></c:if>
 <c:if test="${p.status eq -1}"></c:if>
 </td></c:if>
 <c:if test="${empty loginUser }"><td>无操作</td></c:if>
</tr>
</c:forEach>
<tr><td colspan="7">
<jsp:include page="/inc/pager.jsp">
<jsp:param value="${products.totalRecord }" name="items"/>
<jsp:param value="product.do" name="url"/>
<jsp:param value="method,name,status,cid" name="params"/>
</jsp:include>
</td></tr>
</table>
</body>
</html>