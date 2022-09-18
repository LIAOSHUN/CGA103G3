<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="com.act.model.*"%>

<%
    ActService actSvc = new ActService();
    List<ActVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
<title>活動專區</title>
<style>
	main{
		padding: 30px 45px;
    	background-color: #fff;
	}
	div {
		line-height: 2.2;
	}
	.container-main {
		position: relative;
		box-shadow: 0 2px 8px rgb(0 0 0 / 10%);
    	padding: 10px;
    	border-radius: 16px;
		text-align: center;
	}
	table {
		margin: 0 auto;
		text-align: center;
		width: 80%;
		font-size: 18px;
		line-height: 2.5;
		margin-top: 30px;
		margin-bottom: 45px;
	}
	th {
		text-align: center !important;
		font-size: 22px !important;		
	}
	td {		
		white-space: nowrap;		
	}
</style>
</head>
<body>
<%@ include file="../frontendhead.jsp" %>

	<main>
		<div class="container-main">
			<table>
			<tr>
				<th>店面</th>
				<th>活動標題</th>
				<th>活動日期</th>
				<th>活動場次</th>
				<th>報名期限</th>
				<th>報名</th>
			</tr>
			<c:forEach var="actVO" items="${list}">				
				<tr>
					<td>${actVO.storeVO.storeName}</td>
					<td><a href="<%=request.getContextPath()%>/ActServlet?actID=${actVO.actID}&action=showActInfo" class="actInfo">
					${actVO.actTitle}</a></td>
					<td><javatime:format value="${actVO.actDate}" pattern="yyyy-MM-dd" /></td>
					<td><c:if test="${actVO.dateNum == '14' }">下午場（14:00~17:00）</c:if>
						<c:if test="${actVO.dateNum == '18' }">晚場（18:00~21:00）</c:if>
					</td>
					<td><javatime:format value="${actVO.actTimeEnd}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td>
						<a href="<%=request.getContextPath()%>/ActServlet?actID=${actVO.actID}&action=showActForRegis">
							<button>我要報名</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</main>
	
<%@ include file="../frontendfoot.jsp" %>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/popper/popper.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/bootstrap.js"></script>
</body>
</html>