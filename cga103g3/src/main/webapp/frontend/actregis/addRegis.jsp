<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.act.model.*"%>
<jsp:useBean id="actVO" scope="request" class="com.act.model.ActVO" />
<jsp:useBean id="memVO" scope="request" class="com.member.model.MemberVO" />
<jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
<%
	actVO = (ActVO) request.getAttribute("actVO");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
<meta charset="UTF-8">
<title>活動報名</title>
<style>
	main{
		padding: 30px 45px;
    	background-color: #fff;
	}
	table {
		margin-top: 10px;
		margin-left: 30px;
	}
	tr {
		line-height: 2.2;
	}
	td {
		font-size: 15px;
	}
	.container-main {
		position: relative;
		box-shadow: 0 2px 8px rgb(0 0 0 / 10%);
    	padding: 10px;
    	border-radius: 16px;
	}
</style>
</head>
<body>
<%@ include file="../frontendhead.jsp" %>
	<main>
		<div class="container-main">
			<table>
				<tr>
					<td><b>活動名稱： </b></td>
					<td><input type="text" name="actTitle" value="${actVO.actTitle}" class="form-control" readonly /></td>
				</tr>
			</table>
		</div>
	</main>

<%@ include file="../frontendfoot.jsp" %>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/popper/popper.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/bootstrap.js"></script>
</body>
</html>