<%@page import="com.producttype.model.ProductTypeService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productfavorite.model.*"%>
<%@ page import="com.productimg.model.*"%>
<%@include file="/frontend/frontendhead.jsp"%>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ProductFavoriteService productFavoriteService = new ProductFavoriteService();
List<ProductFavoriteVO> list = productFavoriteService.getAll();
pageContext.setAttribute("list", list);
ProductImgService productImgsvc = new ProductImgService();
List<ProductImgVO> list1 = productImgsvc.getAll();
pageContext.setAttribute("list1", list1);
%>


<html>
<head>
<title>我的收藏</title>
<style>
.ratings {
	position: relative;
	vertical-align: middle;
	display: inline-block;
	color: #ddd; /*背景星星顏色*/
	overflow: hidden;
	font-size: 20px; /*調整字體大小可放大縮小星星*/
	text-shadow: 0px 1px 0 #999;
}

.star5 {
	width: 100%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #D56A16; /*前景星星顏色*/
}

.star1 {
	width: 20%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #D56A16; /*前景星星顏色*/
}

.star2 {
	width: 40%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #D56A16; /*前景星星顏色*/
}

.star3 {
	width: 60%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #D56A16; /*前景星星顏色*/
}

.star4 {
	width: 80%; /*調整寬度可變更星等*/
	position: absolute;
	left: 0;
	top: 0;
	white-space: nowrap;
	overflow: hidden;
	color: #D56A16; /*前景星星顏色*/
}
</style>
</head>
<body>
	<div class="bg0 m-t-23 p-b-140">
		<div class="container ">
			<h1>
				<strong>我的收藏</strong>
			</h1>
			<br>
			<div class="m-l-25 m-r--38 m-lr-0-xl ">
				<div class="flex-w flex-sb-m p-b-52">

					<table class="table-shopping-cart">
						<thead>
							<tr>
								<th style="font-size: 20px; width: 15%">遊戲圖片</th>
								<th style="font-size: 20px; width: 20%">遊戲名稱</th>
								<th style="font-size: 20px; width: 10%">遊戲種類</th>
								<th style="font-size: 20px; width: 10%">金額</th>
								<th style="font-size: 20px; width: 5%">遊戲特色</th>
							</tr>
						</thead>

						<c:forEach var="productFavoriteVO" items="${list}">
							<tbody>

								<tr class="table_row isotope-item">
									<td><c:forEach var="productImgVO" items="${list1}"
											varStatus="imgCount" begin="1" end="1">

											<div>
												<img
													src="<%=request.getContextPath()%>/ShowProductImg?pdID=${productFavoriteVO.pdID}&count=${imgCount.count}"
													width=80% alt="IMG">
											</div>
										</c:forEach></td>
									<td style="font-size: 20px;"><b>${productFavoriteVO.productVO.pdName}</b><br>
									<div class="ratings">
											<div class="empty_star">★★★★★</div>
											<div class=" star${productFavoriteVO.productVO.pdStar} ">★★★★★</div>
										</div></td>
									<td style="font-size: 20px;">${productFavoriteVO.productVO.productTypeVO.pdTypeName}</td>
									<td style="font-size: 20px;">${productFavoriteVO.productVO.pdPrice}</td>
									<td style="width: 30%; font-size: 18px;">${productFavoriteVO.productVO.pdDescription}</td>
									<td>&emsp;</td>
									<td>&emsp;</td>



									<td>
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/frontend/product/ProductServlet">
											<input type="hidden" name="pdID"
												value="${productFavoriteVO.productVO.pdID}"> <input
												type="hidden" name="action" value="getOne_For_Display">
											<button
												class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
												看更多</button>
										</FORM> <br>






										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/frontend/productfavorite/ProductFavoriteServlet"
											style="margin-bottom: 0px;">
											<button
												class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">刪除</button>
											<input type="hidden" name="memID"
												value="${productFavoriteVO.memID}"> <input
												type="hidden" name="pdID" value="${productFavoriteVO.pdID}">
											<input type="hidden" name="action" value="delete">
										</FORM>
									</td>
								</tr>
							</tbody>

						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@include file="/frontend/frontendfoot.jsp"%>