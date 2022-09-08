<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bookingorder.model.*"%>

<%
BookingOrdService boSvc = new BookingOrdService();
List<BookingOrderVO> list = boSvc.getBookingStauts(1);
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Get All BookingStatus is 1</title>

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
	width: auto;
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
				<h3>所有未結束訂位訂單 - getBookingStauts.jsp</h3>
				<h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">回首頁</a></h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>訂位日期</th>
			<th>訂位時間</th>
			<th>訂位結束時間</th>
			<th>門市編號</th>
			<th>包廂編號</th>
			<th>訂位備註</th>
			<th>訂位狀態</th>
		</tr>
		<%@ include file="../file/page1.file"%>
		<c:forEach var="BookingOrderVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${BookingOrderVO.bookingID}</td>
				<td>${BookingOrderVO.memID}</td>
				<td>${BookingOrderVO.bookingDate}</td>
				<td>${BookingOrderVO.bookingStart}</td>
				<td>${BookingOrderVO.bookingEnd}</td>
				<td>${BookingOrderVO.boxVO.storeID}</td>
				<td>${BookingOrderVO.boxID}</td>
				<td>${BookingOrderVO.bookingNote}</td>
				<td>${(BookingOrderVO.bookingStatus == 1)? '訂位成功':'訂位取消'}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/bookingorder/bookingorder.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="bookingID" value="${BookingOrderVO.bookingID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/bookingorder/bookingorder.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="bookingID" value="${BookingOrderVO.bookingID}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="../file/page2.file"%>
</body>
</html>