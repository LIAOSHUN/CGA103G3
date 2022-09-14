<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.box.model.*"%>

<%
BoxService bs = new BoxService();
List<BoxVO> list = bs.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Get All Box</title>

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
				<h3>所有包廂資料 - AllBox.jsp</h3>
				<h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">回首頁</a></h4>
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
			<th>包廂訂位起始時間</th>
			<th>包廂訂位結束時間</th>
			<th>包廂照片</th>
		</tr>
		<%@ include file="../../page1.file"%>
		<c:forEach var="boxVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${boxVO.boxID}</td>
				<td>${boxVO.storeID}</td>
				<td><c:choose>
				<c:when test="${boxVO.boxTypeID == 1}">小包廂</c:when>
				<c:when  test="${boxVO.boxTypeID == 2}">中包廂</c:when>
				<c:otherwise>大包廂</c:otherwise>
				</c:choose></td>
				<td>${boxVO.boxCapcity}</td>
				<td>${boxVO.boxPrice}</td>
				<td>${boxVO.boxDescription}</td>
				<td>${boxVO.boxBkStart}</td>
				<td>${boxVO.boxBkEnd}</td>
				<td><img src="<%=request.getContextPath()%>/BoxImg?BoxID=${boxVO.boxID}" width="50" height="50"></td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/box/box.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="boxID" value="${boxVO.boxID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/box/box.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="boxID" value="${boxVO.boxID}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="../../page2.file"%>
</body>
</html>