<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bookingorder.model.*"%>

<%
BookingOrderVO bokOrdVO = (BookingOrderVO) request.getAttribute("bokOrdVO");
%>
<%= bokOrdVO==null %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>updateBookingOrd.jsp</title>

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
	
<form>
	<table id="table-1">
		<tr>
			<td>
				<h3>修改訂位訂單 - updateBookingOrd.jsp</h3>
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
			<th>訂位狀態更改</th>
		</tr>
			<tr>
				<td><%=bokOrdVO.getBookingID() %></td>
				<td><%=bokOrdVO.getMemID() %></td>
				<td><%=bokOrdVO.getBookingDate() %></td>
				<td><%=bokOrdVO.getBookingStart() %></td>
				<td><%=bokOrdVO.getBookingEnd() %></td>
				<td><%=bokOrdVO.getBoxVO().getStoreID() %></td>
				<td><%=bokOrdVO.getBoxID() %></td>
				<td><%=bokOrdVO.getBookingNote() %></td>
				<td><%=(bokOrdVO.getBookingStatus() == 1)? "訂位成功":"訂位取消" %></td>
				<td><select name="bookingStatus">
						<option value="0">結束訂單</option>
						<option value="1" selected="selected">訂單成立</option>
				</select></td>
			</tr>
	</table>
	<br><input type="hidden" name="action" value="updateBoookingOrder">
<input type="hidden" name="bookingID" value="<%=bokOrdVO.getBookingID()%>">
<input type="submit" value="送出修改"></form>
</body>
</html>