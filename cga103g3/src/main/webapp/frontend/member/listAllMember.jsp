<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@include file="/backend/bkhead.jsp"%>

<%
MemberService memberSvc = new MemberService();
List<MemberVO> list = memberSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

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
	width: 100%;
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

	<table id="table-1">
		<tr>
			<td>
				<h3>所有會員資料 - listAllMember.jsp</h3>
				<h4>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>會員編號</th>
			<th>等級</th>
			<th>姓名</th>
			<th>使用者名稱</th>
			<th>密碼</th>
			<th>性別</th>
			<th>電話</th>
			<th>Email</th>
			<th>地址</th>
			<th>生日</th>
			<th>違規記點</th>
			<th>狀態</th>
			<th>修改</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${memberVO.memID}</td>
				<td>${memberVO.gradeID}</td>
				<td>${memberVO.memName}</td>
				<td>${memberVO.memAccount}</td>
				<td>${memberVO.memPassWord}</td>
				<td>${memberVO.memGender}</td>
				<td>${memberVO.memPh}</td>
				<td>${memberVO.memEmail}</td>
				<td>${memberVO.memAddress}</td>
				<td>${memberVO.memBirthday}</td>
				<td>${memberVO.memVio}</td>
				<td>${memberVO.memStatus}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/frontend/member/member.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="memID" value="${memberVO.memID}"> <input
							type="hidden" name="action" value="getOne_For_Update1">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
<%@include file="/backend/bkfoot.jsp"%>