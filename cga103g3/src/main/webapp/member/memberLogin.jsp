
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
		<font size="10px" >登入(測試)</font>
		
		<form action="member.do" method="post">

			<table border="2">
				<tr>
					<th>帳號</th>
					<td><input type="text" name="memAccount" /></td>
				</tr>
				<tr>
					<th>密碼</th>
					<td><input type="password" name="memPassWord" /></td>
				</tr>
				<tr>
				
					<td colspan="2" align="center"><input type="submit" value="登入" />
						<input type="reset" /></td>

				</tr>
			</table>
			<input type="hidden" name="action" value="memberLogin">
		</form>
		<div>
			<a href="register.jsp" style="text-align: left">註冊</a>
		</div>
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