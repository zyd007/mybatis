<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><decorator:title/></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/main.css"/>
<decorator:head/>
</head>
<body>
<c:if test="${not empty loginUser }">欢迎[${loginUser.nickname }]进入系统&nbsp;
<c:if test="${loginUser.type eq 1 }"><a href="user.do?method=list">用户管理&nbsp;</a>
<a href="<%=request.getContextPath()%>/category.do?method=list">商品分类管理</a>
<a href="<%=request.getContextPath()%>/orders.do?method=list">查看订单列表</a>
</c:if>
<a href="<%=request.getContextPath()%>/category.do?method=list">商品分类</a>
<a href="<%=request.getContextPath()%>/product.do?method=list">商品列表</a>
<a href="<%=request.getContextPath()%>/orders.do?method=showCart">查看购物车</a>

<a href="user.do?method=show&id=${loginUser.id }">查看个人信息</a>
<a href="user.do?method=updateSelfInput">修改个人信息 </a>
<a href="user.do?method=validate">退出系统</a><hr/></c:if>

<!-- 如果没有title的就算默认值 -->
<h2 align="center"><decorator:title default="商品管理系统"/></h2>
<decorator:body/>
<div align="center" style="width:100%;border-top:1px solid; float:left;margin-top:10px;">CopyRight@zy</div>
</body>
</html>