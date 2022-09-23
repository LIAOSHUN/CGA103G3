<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@include file="/frontend/frontendhead.jsp"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productimg.model.*"%>
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
ProductImgService productImgsvc = new ProductImgService();
List<ProductImgVO> list1 = productImgsvc.getAll();
pageContext.setAttribute("list1", list1);
%>


<!DOCTYPE html>
<html>

<head>
<title>絆桌</title>
<style>
.hov-btn666:hover {
	border-color: #72c2bd;
	background-color: #72c2bd;
	color: #fff;
}
</style>
</head>
<body>

	<section class="sec-product-detail bg0 p-t-65 p-b-60">
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-7 p-b-30">
					<div class="p-l-25 p-r-30 p-lr-0-lg">
						<div class="wrap-slick3 flex-sb flex-w">
							<div class="wrap-slick3-dots"></div>
							<div class="wrap-slick3-arrows flex-sb-m flex-w"></div>




							<div class="slick3 gallery-lb">
								<c:forEach var="productImgVO" items="${list1}"
									varStatus="imgCount" begin="1" end="3">
									<div class="item-slick3"
										data-thumb="<%=request.getContextPath()%>/ShowProductImg?pdID=${productVO.pdID}&count=${imgCount.count}">
										<div class="wrap-pic-w pos-relative">
											<img
												src="<%=request.getContextPath()%>/ShowProductImg?pdID=${productVO.pdID}&count=${imgCount.count}"
												alt="IMG-PRODUCT"> <a
												class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
												href="<%=request.getContextPath()%>/ShowProductImg?pdID=${productVO.pdID}&count=${imgCount.count}">
												<i class="fa fa-expand"></i>
											</a>
										</div>
									</div>
								</c:forEach>


							</div>


						</div>
					</div>
				</div>

				<div class="col-md-6 col-lg-5 p-b-30">
					<div class="p-r-50 p-t-5 p-lr-0-lg">
						<h4 class="mtext-105 cl2 js-name-detail p-b-14"><%=productVO.getPdName()%></h4>

						<span class="mtext-106 cl2"> $58.79 </span> <br> <br> <strong
							class="mtext-106" style="font-size: 15px;"> 剩餘數量:<%=productVO.getPdPrice()%>
						</strong>

						<p class="stext-102 cl3 p-t-23"><%=productVO.getPdDescription()%></p>

						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br> <br> <br> <br> <br>




						<!--  -->

						<div class="flex-w flex-r-m p-b-10">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/productfavorite/ProductFavoriteServlet"  name="form1">
							<div class="flex-m bor9 p-r-10 m-r-11">
								<button
									class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100"
									data-tooltip="加入收藏"> <i class="zmdi zmdi-favorite"></i>
								</button>
								<input type="hidden" name="action" value="insert">
								<input type="submit"value="送出新增">
								
							</div>
							</FORM>
							<div class="size-204 flex-w flex-m respon6-next">
								<div class="wrap-num-product flex-w m-r-20 m-tb-10">
									<div
										class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
										<i class="fs-16 zmdi zmdi-minus"></i>
									</div>

									<input class="mtext-104 cl3 txt-center num-product"
										type="number" name="num-product" value="1">

									<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
										<i class="fs-16 zmdi zmdi-plus"></i>
									</div>
								</div>

								<button
									class="flex-c-m stext-101  size-101 bg1 bor1 hov-btn666 p-lr-15 trans-04 js-addcart-detail"
									style="color: #222;">加入購物車</button>
							</div>
						</div>
					</div>

					<!--  -->

				</div>
			</div>
		</div>

		<div class="bor10 m-t-50 p-t-43 p-b-40">
			<!-- Tab01 -->
			<div class="tab01">
				<!-- Tab panes -->
				<div class="tab-content p-t-43">
					<!-- - -->

					<!-- - -->
				</div>
			</div>
	</section>




</body>






</html>
<%@include file="/frontend/frontendfoot.jsp"%>