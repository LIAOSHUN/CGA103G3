<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupontype.model.*"%>


<jsp:useBean id="couponTypeSvc" scope="page" class="com.coupontype.model.CouponTypeService" />
<%
	List<CouponTypeVO> list = couponTypeSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAllCouponType</title>

<style>
  table#table-1 {
	background-color: lightgreen;
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
<body>



<table id="table-1">
	<tr><td>
		 <h3>所有優惠券</h3>
		 <h4><a href="couponType_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<ul>

  <li>
     <FORM METHOD="post" ACTION="coupontype.do" >
       <b>選擇優惠券類型編號:</b>
       <select size="1" name="coupTypeNo">
         <c:forEach var="couponTypeVO" items="${couponTypeSvc.all}" > 
          <option value="${couponTypeVO.coupTypeNo}">${couponTypeVO.coupTypeNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="coupontype.do" >
       <b>選擇優惠券類型名稱:</b>
       <select size="1" name="coupTypeNo">
         <c:forEach var="couponTypeVO" items="${couponTypeSvc.all}" > 
          <option value="${couponTypeVO.coupTypeNo}">${couponTypeVO.coupName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
</ul>

<table>
	<tr>
		<th>類型編號</th>
		<th>名稱</th>
		<th>折價價格</th>
		<th>數量</th>
		<th>描述</th>
		<th>使用期間(天)</th>
		<th>開始發放日</th>
		<th>結束發放日</th>
		<th>修改</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="couponTypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${couponTypeVO.coupTypeNo}</td>
			<td>${couponTypeVO.coupName}</td>
			<td>${couponTypeVO.coupDiscount}</td>
			<td>${couponTypeVO.coupQuantity}</td>
			<td>${couponTypeVO.coupDesc}</td>
			<td>${couponTypeVO.coupDuration}</td>
			<td>${couponTypeVO.coupStart}</td>
			<td>${couponTypeVO.coupEnd}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/coupontype/coupontype.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="coupTypeNo"  value="${couponTypeVO.coupTypeNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/coupontype/coupontype.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="coupTypeNo"  value="${couponTypeVO.coupTypeNo}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>