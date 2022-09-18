<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@include file="/frontend/frontendhead.jsp"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productimg.model.*"%>
<%@ page import="com.producttype.model.*"%>

<%
ProductService productsvc = new ProductService();
List<ProductVO> list = productsvc.getUp();
ProductImgService productImgsvc = new ProductImgService();
List<ProductImgVO> list1 = productImgsvc.getAll();
pageContext.setAttribute("list", list);
pageContext.setAttribute("list1", list1);
ProductTypeService productTypeSvc = new ProductTypeService();
List<ProductTypeVO> list2 = productTypeSvc.getAll();
pageContext.setAttribute("list2", list2);
%>

<!DOCTYPE html>
<html>

<head>
<title>絆桌</title>
</head>
<body>

	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
					<button
						class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1"
						data-filter="*">所有桌遊</button>
						
					<c:forEach var="productTypeVO" items="${list2}">
						<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
							data-filter=".${productTypeVO.pdTypeName}">${productTypeVO.pdTypeName}</button>
							
					</c:forEach>
					


				</div>

				<div class="flex-w flex-c-m m-tb-10">

					<div
						class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
						<i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
						<i
							class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
						搜尋...
					</div>
				</div>

				<!-- Search product -->
				<div class="dis-none panel-search w-full p-t-10 p-b-15">
					<div class="bor8 dis-flex p-l-15">
						<button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04">
							<i class="zmdi zmdi-search"></i>
						</button>

						<input
							class="mtext-107 cl2 size-114 plh2 p-r-15 light-table-filter"
							data-table="order-table" type="text" name="search-product"
							placeholder="Search">
					</div>
				</div>





				<!-- =========================================================================================================================================== -->
				<!-- Shoping Cart -->
				<form class="bg0 p-t-75 p-b-85">
					<div class="container">
						<div>
							<div class="m-l-25 m-r--38 m-lr-0-xl">
								<table class="table-shopping-cart order-table">
									<thead>
										<tr>
											<th style="font-size: 20px; width: 15%">遊戲圖片</th>
											<th style="font-size: 20px; width: 20%">遊戲名稱</th>
											<th style="font-size: 20px; width: 15%">遊戲圖片</th>
											<th style="font-size: 20px; width: 15%">金額</th>
											<th style="font-size: 20px;">遊戲特色</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="productVO" items="${list}">

											<tr class="table_row">

												<td><c:forEach var="productImgVO" items="${list1}"
														varStatus="imgCount" begin="1" end="1">

														<div>
															<img
																src="<%=request.getContextPath()%>/ShowProductImg?pdID=${productVO.pdID}&count=${imgCount.count}"
																width=80% alt="IMG">
														</div>
													</c:forEach></td>
												<td style="font-size: 20px;"><b>${productVO.pdName}</b></td>
												<td style="font-size: 20px;"><b>${productVO.productTypeVO.pdTypeName}</b></td>
												<td style="font-size: 20px;"><b>${productVO.pdPrice}</b></td>
												<td style="width: 70%; font-size: 18px;"><b>${productVO.pdDescription}</b></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>




</body>


<script>
	(function(document) {
		'use strict';

		// 建立 LightTableFilter
		var LightTableFilter = (function(Arr) {

			var _input;

			// 資料輸入事件處理函數
			function _onInputEvent(e) {
				_input = e.target;
				var tables = document.getElementsByClassName(_input
						.getAttribute('data-table'));
				Arr.forEach.call(tables, function(table) {
					Arr.forEach.call(table.tBodies, function(tbody) {
						Arr.forEach.call(tbody.rows, _filter);
					});
				});
			}

			// 資料篩選函數，顯示包含關鍵字的列，其餘隱藏
			function _filter(row) {
				var text = row.textContent.toLowerCase(), val = _input.value
						.toLowerCase();
				row.style.display = text.indexOf(val) === -1 ? 'none'
						: 'table-row';
			}

			return {
				// 初始化函數
				init : function() {
					var inputs = document
							.getElementsByClassName('light-table-filter');
					Arr.forEach.call(inputs, function(input) {
						input.oninput = _onInputEvent;
					});
				}
			};
		})(Array.prototype);

		// 網頁載入完成後，啟動 LightTableFilter
		document.addEventListener('readystatechange', function() {
			if (document.readyState === 'complete') {
				LightTableFilter.init();
			}
		});

	})(document);
</script>





</html>
<%@include file="/frontend/frontendfoot.jsp"%>