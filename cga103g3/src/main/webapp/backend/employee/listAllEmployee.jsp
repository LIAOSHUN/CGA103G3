<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.employee.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%@include file="/backend/bkhead.jsp"%>

<%
EmployeeService employeeSvc = new EmployeeService();
List<EmployeeVO> list = employeeSvc.getAll();
pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmployee.jsp</title>

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


	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>電話</th>
			<th>帳號</th>
			<th>密碼</th>
			<th>入職時間</th>
			<th>狀態</th>
			<th>修改</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${employeeVO.empID}</td>
				<td>${employeeVO.empName}</td>
				<td>${employeeVO.empPhone}</td>
				<td>${employeeVO.empAccount}</td>
				<td>${employeeVO.empPassWord}</td>
				<td>${employeeVO.empHireDate}</td>
				<td>${employeeVO.empStatus}</td>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/backend/employee/employee.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="empID" value="${employeeVO.empID}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>
<%@include file="/backend/bkfoot.jsp"%>