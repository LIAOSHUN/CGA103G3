<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.boxtype.model.*"%>

<%
BoxTypeService btSvc = new BoxTypeService();
List<BoxTypeVO> list = btSvc.getAll();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Get All BoxType</title>

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
	<h4>包廂類型:</h4>
	
	<table id="table-1">
		<tr>
			<td>
				<h3>所有包廂類型 - AllBoxType.jsp</h3>
				<h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp">回首頁</a></h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>包廂類型編號</th>
			<th>包廂類型名稱</th>
		</tr>
		<%@ include file="../../page1.file"%>
		<c:forEach var="boxTypeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${boxTypeVO.boxTypeID}</td>
				<td>${boxTypeVO.boxName}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/boxtype/boxtype.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> 
						<input type="hidden" name="boxTypeID" value="${boxTypeVO.boxTypeID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/boxtype/boxtype.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="boxTypeID" value="${boxTypeVO.boxTypeID}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="../../page2.file"%>
</body>
</html>