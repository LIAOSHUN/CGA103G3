
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登入介面</title>
</head>
<body>
	<div align="center">
		<font size="10px" >登入(後台)</font>
		
		<form action="employee.do" method="post">

			<table border="1">
				<tr>
					<th>帳號</th>
					<td><input type="text" name="empAccount" /></td>
				</tr>
				<tr>
					<th>密碼</th>
					<td><input type="password" name="empPassWord" /></td>
				</tr>
				<tr>
				
					<td colspan="2" align="center"><input type="submit" value="登入" />
						<input type="reset" /></td>

				</tr>
			</table>
			<input type="hidden" name="action" value="employeeLogin">
		</form>
	
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


	</div>
</body>
</html>
