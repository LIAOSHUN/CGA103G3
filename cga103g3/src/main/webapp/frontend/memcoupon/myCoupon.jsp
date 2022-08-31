<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="com.memcoupon.model.*"%>

<jsp:useBean id="memCouponSvc" scope="page" class="com.memcoupon.model.MemCouponService" />
<%
	Integer memID =   Integer.parseInt(request.getParameter("memID"));
	
	List<MemCouponVO> list = memCouponSvc.showMemCouponByMemID(memID);
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myCoupon</title>
<style>
  
    table {
	width: 950px;
	
	margin-top: 5px;
	margin-bottom: 5px;
  }
   table, th{ 
    border: 1px solid gray; 
   } 
   th{
   background-color: #39ac7e  !important;
   text-align: center  !important;
   color: black !important;
   }
  th, td {
    padding: 15px;
    text-align: center;
  }
  
  tr:nth-child(odd){
  	background-color: #eee
  }
</style>

</head>
<body>
<%@ include file="../frontendhead.jsp" %>


<table id="table-1">
	<tr><td>
		 <h3>我的優惠券</h3>
		 <h4><a href="myCoupon_home.jsp">回會員中心</a></h4>
	</td></tr>
</table>


<table>
	<tr>
		<th>會員優惠券編號</th>
		<th>名稱</th>
		<th>折價價格</th>
		<th>到期日</th>
		<th>使用狀態</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memCouponVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memCouponVO.coupNo}</td>
<%-- 			<td>${memCouponVO.coupName}</td> --%>
<%-- 			<td>${memCouponVO.}</td> --%>
<%-- 			<td>${memCouponVO.}</td> --%>
			<td>${memCouponVO.coupStatus}</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<%@ include file="../frontendfoot.jsp" %>
</body>
</html>