<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM ProductType: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM ProductType: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM ProductType: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllProductType.jsp'>List</a> all ProductType.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="/cga103g3/producttype/ProductTypeServlet" >
        <b>輸入種類編號 (如41001):</b>
        <input type="text" name="pdTypeID">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="productTypeSvc" scope="page" class="com.producttype.model.ProductTypeService" />
   
  <li>
     <FORM METHOD="post" ACTION="producttype.do" >
       <b>選擇種類編號:</b>
       <select size="1" name="pdTypeID">
         <c:forEach var="productTypeVO" items="${productTypeSvc.all}" > 
          <option value="${productTypeVO.pdTypeID}">${productTypeVO.pdTypeID}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="producttype.do" >
       <b>選擇遊戲名稱:</b>
       <select size="1" name="pdTypeID">
         <c:forEach var="productTypeVO" items="${productTypeSvc.all}" > 
          <option value="${productTypeVO.pdTypeName}">${productTypeVO.pdTypeName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>種類管理</h3>

<ul>
  <li><a href='addProductType.jsp'>Add</a> a new ProductType.</li>
</ul>

</body>
</html>