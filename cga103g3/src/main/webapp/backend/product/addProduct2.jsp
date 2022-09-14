<%@page import="com.productimg.model.ProductImgVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productimg.model.*"%>
<%@ page import="com.producttype.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
ProductImgVO productImgVO = (ProductImgVO) request.getAttribute("productImgVO");
ProductTypeVO productTypeVO = (ProductTypeVO) request.getAttribute("productTypeVO");


%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addEmp.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>員工資料新增 - addEmp.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<jsp:useBean id="producttypeSvc" scope="page"
		class="com.producttype.model.ProductTypeService" />

	<FORM METHOD="post" ACTION="product.do" name="form1"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>遊戲名稱:</td>
				<td><input type="TEXT" name="pdName" size="45"
					value="<%=(productVO == null) ? " 阿瓦隆" : productVO.getPdName()%>" /></td>
			</tr>
			<tr>

				<td>種類名稱:</td>
				<td><select size="1" name="pdTypeID">
						<c:forEach var="producttypeVO" items="${producttypeSvc.all}">
							<option value="${producttypeVO.pdTypeID}">${producttypeVO.pdTypeName}
						</c:forEach>
				</select></td>


			</tr>
			<tr>
				<td>遊戲價錢:</td>
				<td><input type="TEXT" name="pdPrice" size="45"
					value="<%=(productVO == null) ? "500 " : productVO.getPdPrice()%>" /></td>
			</tr>
			<tr>
				<td>遊戲數量:</td>
				<td><input type="TEXT" name="pdAmount" size="45"
					value="<%=(productVO == null) ? "50 " : productVO.getPdAmount()%>" /></td>
			</tr>

			<tr>
				<td>遊戲資訊:</td>
				<td><input type="TEXT" name="pdDescription" size="45"
					value="<%=(productVO == null) ? "顧名思義 " : productVO.getPdDescription()%>" /></td>
			</tr>
			<tr>
				<td>遊戲狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="pdStatus">
						<option value="0"
							<c:if test="${productVO.pdStatus == '0' }">selected</c:if>>未上架</option>
						<option value="1"
							<c:if test="${productVO.pdStatus == '1' }">selected</c:if>>已上架</option>
				</select></td>
			</tr>
			<tr>
				<td>遊戲推薦度:<font color=yellow><b>*</b></font></td>
				<td><select size="1" name="pdStar">
						<option value="1"
							<c:if test="${productVO.pdStar == '1' }">selected</c:if>>1星</option>
						<option value="2"
							<c:if test="${productVO.pdStar == '2' }">selected</c:if>>2星</option>
						<option value="3"
							<c:if test="${productVO.pdStar == '3' }">selected</c:if>>3星</option>
						<option value="4"
							<c:if test="${productVO.pdStar == '4' }">selected</c:if>>4星</option>
						<option value="5"
							<c:if test="${productVO.pdStar == '5' }">selected</c:if>>5星</option>
				</select></td>
			</tr>
			<tr>
				<td>封面照片:</td>
				<td><input type="file" name="pdImgCover" size="45"></td>
			</tr>
			<tr>
				<td>封面照片名稱:</td>
				<td><input type="TEXT" name="pdImgCoverName" size="45"
					value="<%=(productImgVO == null) ? " 阿瓦隆封面" : productImgVO.getPdImgName()%>">
				</td>
			</tr>
			<tr>
				<td>照片(一):</td>
				<td><input type="file" name="pdImg01" size="45"></td>
			</tr>
			<tr>
				<td>照片(一)名稱:</td>
				<td><input type="TEXT" name="pdImg01Name" size="45"
					value="<%=(productImgVO == null) ? " 阿瓦隆照片(一)" : productImgVO.getPdImgName()%>">
				</td>
			</tr>
			<tr>
				<td>照片(二):</td>
				<td><input type="file" name="pdImg02" size="45"></td>
			</tr>
			<tr>
				<td>照片(二)名稱:</td>
				<td><input type="TEXT" name="pdImg02Name" size="45"
					value="<%=(productImgVO == null) ? " 阿瓦隆照片(二)" : productImgVO.getPdImgName()%>">
				</td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insertwithimg">
		<input type="submit" value="送出新增">
	</FORM>
</body>




</html>