<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productimg.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
ProductService productsvc = new ProductService();
List<ProductVO> list = productsvc.getAll();
ProductImgService productImgsvc = new ProductImgService();
List<ProductImgVO> list1 = productImgsvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有遊戲資料 - listAllProduct.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有遊戲資料 - listAllProduct.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>遊戲編號</th>
			<th>遊戲名稱</th>
			<th>遊戲種類</th>
			<th>遊戲金額</th>
			<th>遊戲數量</th>
			<th>遊戲描述</th>
			<th>上架狀態</th>
			<th>遊戲推薦度</th>
			<th>上架時間</th>
			<th>封面圖片</th>
			<th>圖片(一)</th>
			<th>圖片(二)</th>


			<th>修改</th>
			<th>刪除</th>
		</tr>
<%-- 		<%@ include file="page1.file"%> --%>
		<c:forEach var="productVO" items="${list}" >

			<tr>
				<td>${productVO.pdID}</td>
				<td>${productVO.pdName}</td>
				<td>${productVO.productTypeVO.pdTypeName}</td>
				<td>${productVO.pdPrice}</td>
				<td>${productVO.pdAmount}</td>
				<td>${productVO.pdDescription}</td>
				<td>${productVO.pdStatus}</td>
				<td>${productVO.pdStar}</td>
				<td>${productVO.pdUpdate}</td>

				<c:forEach var="productImgVO" items="${list}" varStatus="imgCount" step="4"
					>

					<td><img
						src="<%=request.getContextPath()%>/ShowProductImg?pdID=${productVO.pdID}&count=${imgCount.count}"
						width=150px height=100px></td>

				</c:forEach>

				<td>
					<FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="pdID" value="${productVO.pdID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="pdID" value="${productVO.pdID}"> <input
							type="hidden" name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
<%-- 	<%@ include file="page2.file"%> --%>

</body>
</html>