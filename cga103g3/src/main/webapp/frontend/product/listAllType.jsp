<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@include file="/frontend/frontendhead.jsp"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productimg.model.*"%>
<%@ page import="com.producttype.model.*"%>

<%
List<ProductVO> listtype = (List<ProductVO>) request.getAttribute("listtype");
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
<script src="node_modules/tablefilter/dist/tablefilter/tablefilter.js"></script>
</head>
<body>

	<!-- Product -->
	<div class="bg0 m-t-23 p-b-140">
		<div class="container">
			<div class="flex-w flex-sb-m p-b-52">
				<div class="flex-w flex-l-m filter-tope-group m-tb-10">
				<a href="<%=request.getContextPath()%>/frontend/product/listAllUp.jsp">
					<button
						class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1"
						data-filter="*" style="font-size: 20px;">所有桌遊</button></a>

					<c:forEach var="productTypeVO" items="${list2}">
						<FORM METHOD="post" ACTION="/cga103g3/product/ShowProductType">

							<button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 "
								data-filter=".${productTypeVO.pdTypeName}"
								style="font-size: 20px;">${productTypeVO.pdTypeName}</button>
							<input type="hidden" name="pdTypeID"
								value="${productTypeVO.pdTypeID}"> <input type="hidden"
								name="action" value="getOne_Type">
						</FORM>


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
							placeholder="搜尋...">
					</div>
				</div>





				<!-- =========================================================================================================================================== -->
				<!-- Shoping Cart -->
				<div class="container ">
					<div class="m-l-25 m-r--38 m-lr-0-xl ">
						<br> <br>
						<table class="table-shopping-cart order-table ">
							<thead>
								<tr>
									<th style="font-size: 20px; width: 15%">遊戲圖片</th>
									<th style="font-size: 20px; width: 20%">遊戲名稱</th>
									<th style="font-size: 20px; width: 10%">遊戲種類</th>
									<th style="font-size: 20px; width: 10%">金額</th>
									<th style="font-size: 20px; width: 5%">遊戲特色</th>


								</tr>

							</thead>
							<c:forEach var="productVO" items="${listtype}">
								<tbody>

									<tr
										class="table_row isotope-item ${productVO.productTypeVO.pdTypeName} ">

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
										<td style="width: 30%; font-size: 18px;"><b>${productVO.pdDescription}</b></td>
										<td>&emsp;</td>
										<td>&emsp;</td>

										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/backend/product/product.do">
												<input type="hidden" name="pdID" value="${productVO.pdID}">


												<input type="hidden" name="action"
													value="getOne_For_Display">
												<button
													class="flex-c-m stext-101 cl0 size-116 bg3 bor14 hov-btn3 p-lr-15 trans-04 pointer">
													看更多</button>
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