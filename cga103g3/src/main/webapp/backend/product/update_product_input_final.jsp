<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.producttype.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
ProductTypeVO productTypeVO = (ProductTypeVO) request.getAttribute("productTypeVO");
%>
<!DOCTYPE html>
<%@include file="/backend/bkhead.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>
	<jsp:useBean id="producttypeSvc" scope="page"
		class="com.producttype.model.ProductTypeService" />

	<FORM METHOD="post" ACTION="product.do" name="form1"
		enctype="multipart/form-data">
		<div class="content-wrapper">
			<div class="container-xxl flex-grow-1 container-p-y">
				<div class="card mb-4">
					<h5 class="card-header" style="font-weight: bold">修改</h5>
					<div class="card-body demo-vertical-spacing demo-only-element">
						<div class="input-group">
							<span class="input-group-text" id="basic-addon11">遊戲名稱</span> <input
								type="text" class="form-control" placeholder="Username"
								aria-label="Username" aria-describedby="basic-addon11"
								name="pdName"
								value="<%=(productVO == null) ? " 阿瓦隆" : productVO.getPdName()%>" />
						</div>
						<div class="input-group">
							<span class="input-group-text" id="basic-addon11">遊戲種類</span> <select
								class="form-select" id="exampleFormControlSelect1"
								aria-label="Default select example" name="pdTypeID">
								<c:forEach var="producttypeVO" items="${producttypeSvc.all}">
									<option value="${producttypeVO.pdTypeID}">${producttypeVO.pdTypeName}
								</c:forEach>
							</select>
						</div>
						<div class="input-group">
							<span class="input-group-text" id="basic-addon11">遊戲金額</span> <input
								type="text" class="form-control" placeholder="Username"
								aria-label="Username" aria-describedby="basic-addon11"
								name="pdPrice"
								value="<%=(productVO == null) ? "500 " : productVO.getPdPrice()%>" />
						</div>
						<div class="input-group">
							<span class="input-group-text" id="basic-addon11">遊戲數量</span> <input
								type="text" class="form-control" placeholder="Username"
								aria-label="Username" aria-describedby="basic-addon11"
								name="pdAmount"
								value="<%=(productVO == null) ? "50 " : productVO.getPdAmount()%>" />
						</div>
						<div class="input-group">
							<span class="input-group-text">遊戲資訊</span>
							<textarea class="form-control" aria-label="With textarea"
								placeholder=" " name="pdDescription">很好玩</textarea>
						</div>
						<div class="input-group">
							<span class="input-group-text" id="basic-addon11">遊戲狀態</span> <select
								class="form-select" id="exampleFormControlSelect1"
								aria-label="Default select example" name="pdStatus">
								<option selected></option>
								<option value="0"
									<c:if test="${productVO.pdStatus == '0' }">selected</c:if>>未上架</option>
								<option value="1"
									<c:if test="${productVO.pdStatus == '1' }">selected</c:if>>已上架</option>
							</select>
						</div>
						<div class="input-group">
							<span class="input-group-text" id="basic-addon11">遊戲推薦度</span> <select
								class="form-select" id="exampleFormControlSelect1"
								aria-label="Default select example" name="pdStar">
								<option selected></option>
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
							</select>
						</div>

						<br> <input type="hidden" name="action" value="update">
							<input type="hidden" name="pdID" value="<%=productVO.getPdID()%>">

						<button type="submit" class="btn btn-primary" >修改</button>

					</div>

				</div>

			</div>

		</div>


	</FORM>



</body>
</html>
<%@include file="/backend/bkfoot.jsp"%>

