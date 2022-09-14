<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.box.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
BoxVO boxVO = (BoxVO) request.getAttribute("boxVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<title>各門市包廂資料 - listOneStoreBox.jsp</title>

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
	width: 600px;
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

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有包廂資料 - listOneStoreBox.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>包廂編號</th>
			<th>門市編號</th>
			<th>包廂類型</th>
			<th>包廂人數</th>
			<th>包廂價格</th>
			<th>包廂說明</th>
			<th>包廂照片</th>
			<th>包廂預訂時間</th>
			<th>包廂預訂結束時間</th>
		</tr>
		<c:forEach var="boxVO" items="${list}" >
			<tr>
				<td>${boxVO.boxID}</td>
				<td>${boxVO.storeID}</td>
				<td>${boxVO.boxTypeID}</td>
				<td>${boxVO.boxCapcity}</td>
				<td>${boxVO.boxPrice}</td>
				<td>${boxVO.boxDescription}</td>
				<td><img src="<%=request.getContextPath()%>/BoxImg?BoxID=${boxVO.boxID}" width="50" height="50"></td>
				<td>${boxVO.boxBkStart}</td>
				<td>${boxVO.boxBkEnd}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>