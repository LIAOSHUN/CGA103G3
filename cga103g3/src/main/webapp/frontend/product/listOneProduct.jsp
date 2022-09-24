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
						<div class="ratings">
							<div class="empty_star">★★★★★</div>
							<div class=" star${productVO.pdStar} ">★★★★★</div>
						</div>
						<br> <br>  <span class="mtext-106 cl2">
							<%=productVO.getPdPrice()%>元
						</span> <br> <br> <strong class="mtext-106"
							style="font-size: 15px;"> 剩餘數量:<%=productVO.getPdAmount()%>
						</strong>

						<p class="stext-102 cl3 p-t-23"><%=productVO.getPdDescription()%></p>

						<br> <br> <br> <br> <br> <br> <br>
						<br> <br> <br> <br> <br> <br> <br>




						<!--  -->

						<div class="flex-w flex-r-m p-b-10">
							<div class="flex-m bor9 p-r-10 m-r-11">
<%-- 							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/productfavorite/ProductFavoriteServlet"  name="form1"> --%>
								<button 
									class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100"
									data-tooltip="加入收藏" id="addfav"> <i class="zmdi zmdi-favorite"></i>
								</button>
								<input type="hidden" name="pdID" value="${productVO.pdID}"> 
								<input type="hidden" name="action" value="insert">
<!-- 								</FORM> -->
								
							</div>
							<div class="size-204 flex-w flex-m respon6-next">
								<div class="wrap-num-product flex-w m-r-20 m-tb-10">
									<div
										class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
										<i class="fs-16 zmdi zmdi-minus"></i>
									</div>

									<input class="mtext-104 cl3 txt-center num-product"
										type="number" name="num-product" value="1" id="count">

									<div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
										<i class="fs-16 zmdi zmdi-plus"></i>
									</div>
								</div>

								<button id="addCart"
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


<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>


<script>

let addCart = document.querySelector('#addCart');
let count1 = document.querySelector('#count');
let cartNum = document.querySelector('#cartNum');


const pdID = <%=productVO.getPdID() %>;



addCart.addEventListener('click', function () {
	
	let newCount = count1.value;
	
	   $.ajax({
		url: "cart.do",
		type: "POST",
		
		data: {
				action: "addItem",
				count:newCount,
				pdID:pdID,
			  },
			  success: function(){
				
				   $.ajax({
						url: "initCart.do",
						type: "POST",
						
						data: {
							
						},
						success: function(data){
							console.log(data);
							console.log(data.length);
							cartNum.innerText = data.length;
							
						}
					})
				
				
				}	  

		})	
});


let addfav = document.querySelector('#addfav');

addfav.addEventListener('click', function () {
	
	
	   $.ajax({
		url: "<%=request.getContextPath()%>/frontend/productfavorite/ProductFavoriteServlet",
		type: "POST",
		data: {
				action: "insert",
				pdID:pdID,
			  },
			
		})	
	
	
		
});





	
</script>




<%@include file="/frontend/frontendfoot.jsp"%>
</html>