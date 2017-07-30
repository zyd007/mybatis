<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- url是实际地址XXXX.do -->
<pg:pager maxPageItems="15" items="${param.items }" export="number"
	url="${param.url }">
	<!-- 遍历条件 param.params遍历method和name...最后得到其value值 -->
	<c:forEach items="${param.params }" var="p">
		<pg:param name="${p}" />
	</c:forEach>

	<pg:last>
	共有${pageNumber }页&nbsp;
	</pg:last>
	当前为${number }页
	<pg:first>
		<a href="${pageUrl }">首页</a>
	</pg:first>
	<pg:prev>
		<a href="${pageUrl }">上一页</a>
	</pg:prev>
	<pg:pages>
	<c:if test="${number eq pageNumber }">[${pageNumber }]</c:if>
	<c:if test="${number ne pageNumber }"><a href="${pageUrl }">${pageNumber }</a></c:if>	
	</pg:pages>
	<pg:next>
		<a href="${pageUrl }">下一页</a>
	</pg:next>
	<pg:last>
		<a href="${pageUrl }">尾页</a>
	</pg:last>
</pg:pager>