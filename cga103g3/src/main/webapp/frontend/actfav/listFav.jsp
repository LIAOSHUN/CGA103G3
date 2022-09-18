<%@page import="com.actfav.model.ActFavService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.util.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="com.actfav.model.*"%>
<jsp:useBean id="actVO" scope="request" class="com.act.model.ActVO" />
<%
    ActFavService actFavSvc = new ActFavService();
//ActFavVO actFavVO = (ActFavVO) session.getAttribute("member");("會員登入後擷取會員ID"));
//List<ActFavVO> list = actFavSvc.getByMem(member.getMemID());("會員登入後擷取會員ID"));
    List<ActFavVO> list = actFavSvc.getByMem(11002);
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
<title>活動收藏</title>
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
					<th>移除</th>
				</tr>
				<c:forEach var="actFavVO" items="${list}">
					<tr>
						<td>${actFavVO.actVO.storeVO.storeName}</td>
					<td><a href="<%=request.getContextPath()%>/ActServlet?actID=${actFavVO.actVO.actID}&action=showActInfo" class="actInfo">
					${actFavVO.actVO.actTitle}</a></td>
					<td><javatime:format value="${actFavVO.actVO.actDate}" pattern="yyyy-MM-dd" /></td>
					<td><c:if test="${actFavVO.actVO.dateNum == '14' }">下午場（14:00~17:00）</c:if>
						<c:if test="${actFavVO.actVO.dateNum == '18' }">晚場（18:00~21:00）</c:if>
					</td>
					<td><javatime:format value="${actFavVO.actVO.actTimeEnd}" pattern="yyyy-MM-dd HH:mm" /></td>
					<td>
						<a href="<%=request.getContextPath()%>/ActServlet?actID=${actFavVO.actVO.actID}&action=showActForRegis">
							<button>我要報名</button>
						</a>
					</td>
						<td>
							<button onclick="onDelFav(${actVO.actID})">移除</button>
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
<script>
	function onDelFav(actID) {
		if (!confirm('確定刪除?')) {
        return;
    	}
		let url = `/cga103g3/DelActFavServlet?actID=${actVO.actID}`;
		fetch(url, {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify({
				actID: `${actVO.actID}`
			})
		})
			.then(res => res.json())
			.then(body => {
				if (body.successful) {
					location.reload();
				}
			});
	}
</script>
</body>
</html>