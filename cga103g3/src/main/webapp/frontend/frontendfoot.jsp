<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>絆桌</title>
	<meta charset="UTF-8">
</head>
<body >
<!-- Footer -->
	<footer class="bg3 p-t-75 p-b-32">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						探索絆桌
					</h4>
					<ul>
						<li class="p-b-10">
							<a href="<%=request.getContextPath()%>/frontend/index.jsp" class="stext-107 cl7 hov-cl1 trans-04">
								首頁
							</a>
						</li>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								桌遊百科
							</a>
						</li>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								門市資訊
							</a>
						</li>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								服務項目
							</a>
						</li>
					</ul>
				</div>
				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						線上服務
					</h4>

					<ul>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								加入會員
							</a>
						</li>

						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								帳號中心
							</a>
						</li>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								活動報名
							</a>
						</li>
						<li class="p-b-10">
							<a href="#" class="stext-107 cl7 hov-cl1 trans-04">
								線上訂位
							</a>
						</li>
					</ul>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">
					<h4 class="stext-301 cl0 p-b-30">
						客服中心
					</h4>

					<p class="stext-107 cl7 size-201">
						線上客服
					</p>
				</div>

				<div class="col-sm-6 col-lg-3 p-b-50">




					<h5 class="stext-301 cl0 p-b-30">
						客服中心
					</h5>

					<p class="stext-107 cl7 size-201">
						線上客服
					</p>
					<!-- <form>
						<div class="wrap-input1 w-full p-b-4">
							<input class="input1 bg-none plh1 stext-107 cl7" type="text" name="email"
								placeholder="email@example.com">
							<div class="focus-input1 trans-04"></div>
						</div>
						<div class="p-t-18">
							<button class="flex-c-m stext-101 cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04">
								訂閱
							</button>
						</div>
					</form> -->
				</div>
			</div>
			<div class="p-t-40">
				<p class="stext-107 cl6 txt-center">
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					Copyright &copy;
					<script>document.write(new Date().getFullYear());</script> All rights reserved | Made with <i
						class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com"
						target="_blank">Colorlib</a> &amp; distributed by <a href="https://themewagon.com"
						target="_blank">ThemeWagon</a>
					<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->

				</p>
			</div>
		</div>
	</footer>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/bootstrap/js/popper.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/select2/select2.min.js"></script>
	<script>
		$(".js-select2").each(function () {
			$(this).select2({
				minimumResultsForSearch: 20,
				dropdownParent: $(this).next('.dropDownSelect2')
			});
		})
	</script>

	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/daterangepicker/moment.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/slick/slick.min.js"></script>
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/js/slick-custom.js"></script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/parallax100/parallax100.js"></script>
	<script>
		$('.parallax100').parallax100();
	</script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
	<script>
		$('.gallery-lb').each(function () { // the containers for all your galleries
			$(this).magnificPopup({
				delegate: 'a', // the selector for gallery item
				type: 'image',
				gallery: {
					enabled: true
				},
				mainClass: 'mfp-fade'
			});
		});
	</script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/isotope/isotope.pkgd.min.js"></script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/sweetalert/sweetalert.min.js"></script>
	<script>
		$('.js-addwish-b2').on('click', function (e) {
			e.preventDefault();
		});

		$('.js-addwish-b2').each(function () {
			var nameProduct = $(this).parent().parent().find('.js-name-b2').html();
			$(this).on('click', function () {
				swal(nameProduct, "is added to wishlist !", "success");

				$(this).addClass('js-addedwish-b2');
				$(this).off('click');
			});
		});

		$('.js-addwish-detail').each(function(){
			var nameProduct = $(this).parent().parent().parent().find('.js-name-detail').html();

			$(this).on('click', function(){
				if ($(this).hasClass('js-addedwish-detail')) {
					$(this).removeClass('js-addedwish-detail')
				} else {



				swal(nameProduct, "已加入收藏 !", "success");

				$(this).addClass('js-addedwish-detail');
				}
			});
		});

		/*---------------------------------------------*/

		$('.js-addcart-detail').each(function () {
			var nameProduct = $(this).parent().parent().parent().parent().find('.js-name-detail').html();
			$(this).on('click', function () {
				swal(nameProduct, "is added to cart !", "success");
			});
		});
	</script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function () {
			$(this).css('position', 'relative');
			$(this).css('overflow', 'hidden');
			var ps = new PerfectScrollbar(this, {
				wheelSpeed: 1,
				scrollingThreshold: 1000,
				wheelPropagation: false,
			});

			$(window).on('resize', function () {
				ps.update();
			})
		});
	</script>
	<!--===============================================================================================-->
	<script src="<%=request.getContextPath()%>/frontend/frontend_template/js/main.js"></script>
	<script src="<%= request.getContextPath() %>/frontend/cart/cartInit.js"></script>


	

</body>

</html>