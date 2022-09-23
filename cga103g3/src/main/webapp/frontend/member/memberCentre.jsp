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


		<!-- Banner -->
		<div class="sec-banner bg0 p-t-95 p-b-55">
			<div class="container">




				<div class="p-b-10">
					<h3 class="ltext-103 cl5">會員中心</h3>
				</div>
				<div class="row">
					<div class="col-md-6 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img
								src="<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬03.jpg"
								alt="IMG-BANNER">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/frontend/member/listOneMember.jsp">
								<button
									class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
									<div class="block1-txt-child1 flex-col-l">
										<span class="block1-name ltext-102 trans-04 p-b-8"> <font
											color=white><strong>會員資料</strong></font>

										</span> <span class="block1-info stext-102 trans-04"> </span>
									</div>

									<div class="block1-txt-child2 p-b-4 trans-05">
										<div class="block1-link stext-101 cl0 trans-09">查看</div>
									</div>
								</button>
								<input type="hidden" name="pdTypeID" value="41006"> <input
									type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img
								src="<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬05.jpg"
								alt="IMG-BANNER">
							<FORM METHOD="post" ACTION="/cga103g3/product/ShowProductType">

								<button
									class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
									<div class="block1-txt-child1 flex-col-l">
										<span class="block1-name ltext-102 trans-04 p-b-8"> <font
											color=white><strong>我的訂單</strong></font>

										</span> <span class="block1-info stext-102 trans-04"> </span>
									</div>

									<div class="block1-txt-child2 p-b-4 trans-05">
										<div class="block1-link stext-101 cl0 trans-09">查看</div>
									</div>
								</button>
								<input type="hidden" name="pdTypeID" value="41002"> <input
									type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img
								src="<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬06.jpg"
								alt="IMG-BANNER">
							<FORM METHOD="post" ACTION="/cga103g3/product/ShowProductType">

								<button href="product.html"
									class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
									<div class="block1-txt-child1 flex-col-l">
										<span class="block1-name ltext-102 trans-04 p-b-8"> <strong>我的活動</strong>
										</span> <span class="block1-info stext-102 trans-04"> </span>
									</div>

									<div class="block1-txt-child2 p-b-4 trans-05">
										<div class="block1-link stext-101 cl0 trans-09">查看</div>
									</div>
								</button>
								<input type="hidden" name="pdTypeID" value="41004"> <input
									type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img
								src="<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬04.jpg"
								alt="IMG-BANNER">
							<FORM METHOD="post" ACTION="/cga103g3/product/ShowProductType">

								<button
									class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
									<div class="block1-txt-child1 flex-col-l">
										<span class="block1-name ltext-102 trans-04 p-b-8"> <font
											color=black><strong>我的優惠卷</strong></font>

										</span> <span class="block1-info stext-102 trans-04"> </span>
									</div>

									<div class="block1-txt-child2 p-b-4 trans-05">
										<div class="block1-link stext-101 cl0 trans-09">查看</div>
									</div>
								</button>
								<input type="hidden" name="pdTypeID" value="41008"> <input
									type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

					<div class="col-md-6 col-lg-4 p-b-30 m-lr-auto">
						<!-- Block1 -->
						<div class="block1 wrap-pic-w">
							<img
								src="<%=request.getContextPath()%>/frontend/frontend_template/images/首頁跑馬02.jpg"
								alt="IMG-BANNER">
							<FORM METHOD="post" ACTION="/cga103g3/product/ShowProductType">
								<button href="product.html"
									class="block1-txt ab-t-l s-full flex-col-l-sb p-lr-38 p-tb-34 trans-03 respon3">
									<div class="block1-txt-child1 flex-col-l">
										<span class="block1-name ltext-102 trans-04 p-b-8"> <font
											color=black><strong>活動收藏</strong></font>
										</span> <span class="block1-info stext-102 trans-04"> </span>
									</div>

									<div class="block1-txt-child2 p-b-4 trans-05">
										<div class="block1-link stext-101 cl0 trans-09">查看</div>
									</div>
								</button>
								<input type="hidden" name="pdTypeID" value="41010"> <input
									type="hidden" name="action" value="getOne_Type">
							</FORM>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
		<FORM METHOD="post" ACTION="member.do">
 		<div style="width: 100%; text-align: center;">
                <div class="mb-3">
                  <button class=" stext-101  size-116 bg3 bor14 hov-btn87 p-lr-15 trans-04 pointer" style="width:10% ;color:rgb(0, 0, 0);size: 20px;" type="submit">登出</button><input type="hidden" name="action" value="memberLogout">
                </div>
		</div>
		</FORM>




</body>






</html>
<%@include file="/frontend/frontendfoot.jsp"%>