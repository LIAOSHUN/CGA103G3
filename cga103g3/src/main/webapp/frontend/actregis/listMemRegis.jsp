<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.util.*"%>
<%@ page import="com.actregis.model.*"%>
<jsp:useBean id="memberVO" scope="request" class="com.member.model.MemberVO" />

<%
    ActRegisService actRegisSvc = new ActRegisService();
    List<ActRegisVO> list = actRegisSvc.getMemRegis((Integer)(session.getAttribute("memID")));   // ("會員登入後擷取會員ID"));
//     List<ActRegisVO> list = actRegisSvc.getMemRegis(11001);
    pageContext.setAttribute("list",list);
//     session.setAttribute("memID", memID);
%>


<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
<title>我的活動資料</title>

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
<h3 style="padding-top: 30px; padding-left: 50px; text-align:left;">我的活動</h3>
<table>
	<tr>
		<th>活動名稱</th>
		<th>活動日期</th>
		<th>報名人數</th>
		<th>報名總費用</th>
		<th>取消報名</th>
	</tr>
	<c:forEach var="actRegisVO" items="${list}">		
		<tr>
			<td>${actRegisVO.actVO.actTitle}</td>
			<td><javatime:format value="${actRegisVO.actVO.actDate}" pattern="yyyy-MM-dd" />
				<c:if test="${actRegisVO.actVO.dateNum == '14' }">下午場(14:00~17:00)</c:if>
				<c:if test="${actRegisVO.actVO.dateNum == '18' }">晚場(18:00~21:00)</c:if></td>
			<td>${actRegisVO.actNum}</td>
			<td>${actRegisVO.actFee}</td>
			<td>
				<c:if test="${actRegisVO.regisStatus == '1' }">
					<a href="<%=request.getContextPath()%>/ActRegisServlet?actID=${actRegisVO.actID}&action=cancel">
						<button onclick="onCancel()">
							取消報名
						</button>
					</a>
				</c:if>
				<c:if test="${actRegisVO.regisStatus == '0' }">
					<button disabled>已取消	</button>
				</c:if>
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
function onCancel() {
	if (!confirm('是否確定取消報名?')) {
    return;
	}
	let url = `/cga103g3/ActRegisServlet`;
	fetch(url, {
		method: 'POST',
		headers: { 'Content-Type': 'application/json' },
		body: JSON.stringify({
			memID: ${memID},
			action: cancel,
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