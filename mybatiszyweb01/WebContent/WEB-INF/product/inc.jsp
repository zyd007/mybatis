<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty loginUser }"><a href="user.do?method=loginInput">登录账户</a></c:if>
<a href="<%=request.getContextPath()%>/product.do?method=list">商品列表</a>
<c:if test="${loginUser.type eq 1}">
<a href="<%=request.getContextPath()%>/product.do?method=addInput">添加商品</a>
</c:if>