<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单列表</title>
</head>
<body>
<table align="center" style="margin-top:10xp; " width="900" cellpadding="0" cellspacing="0" class="thin-border">
<tr>
<td colspan="5">
<form action="orders.do?method=userList&uid=${loginUser.id }" method="post">
<select size="1" name="status">
<option value="0">请选择订单状态</option>
<option  value="1" <c:if test="${param.status eq 1 }">selected</c:if>>下订单</option>
<option value="2" <c:if test="${param.status eq 2 }">selected</c:if>>已付款</option>
<option value="3" <c:if test="${param.status eq 3 }">selected</c:if>>送货</option>
<option value="4" <c:if test="${param.status eq 4 }">selected</c:if>>确认</option> 
</select>
<input type="submit" value="筛选"/>
</form>
</td>
</tr>
<tr>
	<td>订单编号</td><td>购买日期</td><td>订单状态</td><td>订单用户</td>
	<td>操作</td>
</tr>
<c:forEach items="${orders.datas }" var="o" >
<tr>
<td>${o.id }&nbsp;<a href="orders.do?method=showOrders&oid=${o.id }">查看订单详情</a></td>
<td><fmt:formatDate value="${o.buyDate }"  pattern="yyyy-MM-dd"/></td>
<td><c:if test="${o.status eq 1 }">已下单</c:if>
<c:if test="${o.status eq 2 }">已付款</c:if>
<c:if test="${o.status eq 3 }">已发送</c:if>
<c:if test="${o.status eq 4 }">确认</c:if>
</td>
<td>${o.user.nickname }</td>
<td><c:if test="${o.status eq 1 }"><a href="orders.do?method=deleteOrders&oid=${o.id }">删除</a>&nbsp;
<c:if test="${o.user.id eq loginUser.id }"><a href="orders.do?method=payPrice&oid=${o.id }">付款</a></c:if></c:if>
<c:if test="${o.status eq 3  }">
<a href="orders.do?method=confirm&oid=${o.id }">确认收货</a>&nbsp;</c:if></td>
</tr>
</c:forEach>
<tr><td colspan="5">
<jsp:include page="/inc/pager.jsp">
<jsp:param value="${orders.totalRecord }" name="items"/>
<jsp:param value="orders.do" name="url"/>
<jsp:param value="status,method,uid" name="params"/>
</jsp:include>
</td></tr>
</table>
</body>
</html>