<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/frontend/frontendhead.jsp"%>


<!DOCTYPE html>
<html>

<head>
<title>絆桌</title>
<style>
	.hov-btn87:hover {
  border-color: #fff;
  background-color: #72c2bd;
  color: #fff;
}
</style>
</head>
<body>

	<div class="bg0">
		<!-- Slider -->
		<!-- Slider -->
		<section class="section-slide">
			<div class="wrap-slick1 rs2-slick1">
				<div class="slick1">
					<div class="item-slick1 bg-overlay1" style="background-image: url(<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬05.jpg);"
						data-thumb="images/thumb-01.jpg" data-caption="Women’s Wear">
						<div class="container h-full">
							<div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
								<div class="layer-slick1 animated visible-false" data-appear="fadeInDown"
									data-delay="0">
									<span class="ltext-202 txt-center cl0 respon2">
									
									</span>
								</div>

								<div class="layer-slick1 animated visible-false" data-appear="fadeInUp"
									data-delay="800">
									<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
										<b>歡迎來到絆桌</b>
									</h2>
								</div>

								
							</div>
						</div>
					</div>

					<div class="item-slick1 bg-overlay1" style="background-image: url(<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬06.jpg);"
						data-thumb="images/thumb-02.jpg" data-caption="Men’s Wear">
						<div class="container h-full">
							<div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
								<div class="layer-slick1 animated visible-false" data-appear="rollIn" data-delay="0">
									<span class="ltext-202 txt-center cl0 respon2">
										
									</span>
								</div>

								<div class="layer-slick1 animated visible-false" data-appear="lightSpeedIn"
									data-delay="800">
									<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
										當月壽星享7折優惠
									</h2>
								</div>

								<div class="layer-slick1 animated visible-false" data-appear="slideInUp"
									data-delay="1600">
									<a href="<%=request.getContextPath()%>/frontend/booking/bookingFinal.jsp"
										class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn2 p-lr-15 trans-04">
										前往訂位
									</a>
								</div>
							</div>
						</div>
					</div>

					<div class="item-slick1 bg-overlay1" style="background-image: url(<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬03.jpg);">
						<div class="container h-full">
							<div class="flex-col-c-m h-full p-t-100 p-b-60 respon5">
								<div class="layer-slick1 animated visible-false" data-appear="rotateInDownLeft"
									data-delay="0">
									<span class="ltext-202 txt-center  respon2" style="color:#ffff ;">
										玩桌遊、順便認識新朋友！
									</span>
								</div>

								<div class="layer-slick1 animated visible-false" data-appear="rotateInUpRight"
									data-delay="800">
									<h2 class="ltext-104 txt-center cl0 p-t-22 p-b-40 respon1">
										桌遊聚會活動
										【週日試遊戲】
									</h2>
								</div>

								<div class="layer-slick1 animated visible-false" data-appear="rotateIn"
									data-delay="1600">
									<a href="product.html"
										class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn2 p-lr-15 trans-04">
										前往報名
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- <div class="wrap-slick1-dots p-lr-10"></div> -->
			</div>
		</section>


		<!-- Banner -->
		<div class="sec-banner bg0 p-t-95 p-b-55">
			<div class="container">
				<div class="p-b-10">
					 <h3 class="ltext-103 cl5">
						近期活動
						</h3>
				</div>


				<div class="row">

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/近期活動01.jpg" alt="IMG-BANNER">

							<a href="product.html"
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</a>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/近期活動02.jpg" alt="IMG-BANNER">

							<a href="product.html"
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</a>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/近期活動03.jpg" alt="IMG-BANNER">

							<a href="product.html"
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<div class="p-b-10">
					 <h3 class="ltext-103 cl5">
						商品
						</h3>
				</div>
				<div class="row">
					<div class="col-md-6 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/首頁分類.png" alt="IMG-BANNER">
<FORM METHOD="post" ACTION="/cga103g3/frontend/product/ShowProductType">
							<button 
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
										<strong>家庭</strong>
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</button>
							<input type="hidden" name="pdTypeID" value="41006"> 
								<input type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/合作.jpg" alt="IMG-BANNER">
<FORM METHOD="post" ACTION="/cga103g3/frontend/product/ShowProductType">

							<button 
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
										<strong>合作</strong>
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</button>
							<input type="hidden" name="pdTypeID" value="41002"> 
								<input type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/分類03.jpg" alt="IMG-BANNER">
<FORM METHOD="post" ACTION="/cga103g3/frontend/product/ShowProductType">

							<button href="product.html"
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
										<strong>派對</strong>
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</button>
							<input type="hidden" name="pdTypeID" value="41004"> 
								<input type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/分類04.jpg" alt="IMG-BANNER">
<FORM METHOD="post" ACTION="/cga103g3/frontend/product/ShowProductType">

							<button 
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
										<strong>策略</strong>
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</button>
							<input type="hidden" name="pdTypeID" value="41008"> 
								<input type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img src="<%=request.getContextPath()%>/frontend/frontend_template/images/分類05.jpg" alt="IMG-BANNER">
<FORM METHOD="post" ACTION="/cga103g3/frontend/product/ShowProductType">
							<button href="product.html"
								class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
								<div class="block1-txt-child1 flex-col-l">
									<span class="block1-name ltext-102 trans-04 p-b-8">
										<strong>兒童</strong>
									</span>

									<span class="block1-info stext-102 trans-04">

									</span>
								</div>

								<div class="block1-txt-child2 p-b-4 trans-05">
									<div class="block1-link stext-101 cl0 trans-09">
										去逛逛
									</div>
								</div>
							</button>
							<input type="hidden" name="pdTypeID" value="41010"> 
								<input type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>
					<div style="width:100%; text-align:center;">
						<a href="<%=request.getContextPath()%>/frontend/product/listAllUp.jsp">
					<button class=" stext-101  size-116 bg3 bor14 hov-btn87 p-lr-15 trans-04 pointer" style="width:10% ;color:rgb(0, 0, 0);size: 20px;">
						<strong>更多商品...</strong>
					</button>
				</a>
				</div>
				</div>
			</div>
		</div>
	</div>

	</div>
	</div>
	</section>
	



</body>






</html>
<%@include file="/frontend/frontendfoot.jsp"%>