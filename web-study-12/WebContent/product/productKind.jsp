<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="sub_img.html"%>
<%@include file="sub_menu.html"%>
<article>
	<h2>Item</h2>
	<c:forEach items="${productKindList }" var="pVo">
		<div id="item">
			<a href="ShoppingServlet?command=product_detail&pseq=${pVo.pseq }">
				<img src="product_images/${pVo.image }" />
				<h3>${pVo.name }</h3>
				<p>${pVo.price2 }</p>
			</a>
		</div>
	</c:forEach>
	<div class="clear"></div>
</article>
<%@include file="../footer.jsp"%>