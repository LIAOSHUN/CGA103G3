
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>登入</title>
<!-- 登入 style end-->
<%if ("wrong".equals(request.getAttribute("errorMessage"))) {%>
<script type="text/javascript">
	alert("用戶名或者密碼錯誤！");
</script>
<%} else {%>
<%}%>
</head>

<body style="text-align: center">
	<form name="myform" action="EmpLoginServlet.do" method="POST">
		<div id="wrapper">
			<div id="wrappertop"></div>

			<div id="wrappermiddle">
				<h2>登入系統</h2>

				<div id="username_input">

					<div id="username_inputleft"></div>

					<div id="username_inputmiddle">
						<input type="text" name="empAccount" id="url" value="員工帳號">
					</div>

					<div id="username_inputright"></div>

				</div>

				<div id="password_input">

					<div id="password_inputleft"></div>
 
					<div id="password_inputmiddle">
						<input type="password" name="empPassword" id="url"
							value="Password">
					</div>

					<div id="password_inputright"></div>

				</div>
				<div id="submit">
					<input type="image"
						id="submit1" value="Sign In" onclick="return checkForm()">
				
				</div>
			</div> 

			<div id="wrapperbottom"></div>
		</div>
	</form>
	<script type="text/javascript" src="./back-assets/js/back.login.js"></script>
</body>
</html>

