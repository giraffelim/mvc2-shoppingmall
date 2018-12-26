<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="sub_img.html"%>
<%@include file="sub_menu.html"%>
<article>
	<h1>Item</h1>
	<div id="itemdetail">
		<form method="post" name="formm">
			<fieldset>
				<!-- 요소들을 박스로 묶어줌 -->
				<legend>Item Detail Info</legend>
				<!-- 박스 안에 글 -->
				<a href="ShoppingServlet?command=product_detail&pseq=${pVo.pseq }">
				<span style="float: left;"> <img
					src="product_images/${pVo.image }" />
					</span>
					<h2>${pVo.name }</h2>
					</a>
					<label>가격: </label>
					<p>${pVo.price2 }원</p>
					<label>수량: </label>
					<input type="text" name="quantity" size="2" value="1"><br>
					<input type="hidden" name="pseq" value="${pVo.pseq }">
			</fieldset>
			<div class="clear"></div>
			<div id="buttons">
				<input type="button" value="장바구니에 담기" class="submit" onclick="go_cart()">
				<input type="button" value="즉시 구매" class="submit" onclick="go_order()">
				<input type="reset" value="취소" class="cancel">
			</div>
		</form>
	</div>
</article>
<%@include file="../footer.jsp"%>