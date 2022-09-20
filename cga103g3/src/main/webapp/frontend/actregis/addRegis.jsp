<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@page import="com.act.model.*"%>
<jsp:useBean id="actVO" scope="request" class="com.act.model.ActVO" />
<jsp:useBean id="memVO" scope="request" class="com.member.model.MemberVO" />
<jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />
<%
	actVO = (ActVO) request.getAttribute("actVO");
	Integer memID = (Integer)session.getAttribute("memID");
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
		margin-left: 50px;
		margin-bottom: 45px;
		display: inline; 
		width: 40%;
		position:relative; 
		float:left;
	}
	tr {
		line-height: 3;
	}
	td {
		font-size: 18px;
		white-space: nowrap;
		padding-right: 20px;
	}
	.container-main {
		position: relative;
		box-shadow: 0 2px 8px rgb(0 0 0 / 10%);
    	padding: 10px;
    	border-radius: 16px;
	}
	.container-main::after {
		content: "";
		display: block;
		clear: both;
	}
	.btn-info {
        color: #666666 !important;
        background-color: #40e0d0 !important;
        border-color: #46b8da;
		letter-spacing: 3px !important;
		padding: 6px 25px !important;
		border-radius: 12px !important;
		margin-top: 20px;
		margin-bottom: 25px;
    }
</style>
</head>
<body>
<%@ include file="../frontendhead.jsp" %>
	<main>
		<div class="container-main">
			<table style="left: 50px;">
				<tr>
					<td><b>活動名稱： </b></td>
					<td><span name="actTitle">${actVO.actTitle}</span></td>
				</tr>
				<tr>
					<td><b>活動日期： </b></td>
					<td>
						<javatime:format value="${actVO.actDate}" pattern="yyyy-MM-dd" />
						<c:if test="${actVO.dateNum == '14' }">下午場(14:00~17:00)</c:if>
						<c:if test="${actVO.dateNum == '18' }">晚場(18:00~21:00)</c:if>
					</td>
				</tr>
				<tr>
					<td><b>報名人數：</b></td>
					<td><select size="1" id="actNum" name="actNum" class="form-select">
							<c:forEach var="regisMax" begin="1" end="${actVO.regisMax/2}" varStatus="status">
								<option value="${status.index}"
									${(actVO.regisMax==status.index)?'selected':'' }>${status.index}
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td><b>報名總費用：</b></td>
					<td><span name="actFee" id="fee"></span></td>
				</tr>
			</table>
			<table style="left: 150px;">
				<tr style="line-height: 2.7;">
					<td>
						
							<button class="btnn btn-info btnToConfirm" onclick="onRegisClick()">確 認 報 名</button>
						
					</td>					
				</tr>
				<tr style="line-height: 2.5;">
					<td>
						<a href="/cga103g3/frontend/act/listAllActF.jsp">
							<button class="btnn btn-info btnBack">前往活動列表</button>
						</a>
					</td>					
				</tr>
			</table>
		</div>
	</main>

<%@ include file="../frontendfoot.jsp" %>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/popper/popper.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/bootstrap.js"></script>
<script>
	window.onload = function() {
		const actFee = document.querySelector('#fee');
		actFee.innerHTML = ${actVO.actFee} +' 元';		
	}
	document.querySelector('#actNum').onchange = function() {
		document.querySelector('#fee').innerHTML = document.querySelector('#actNum').value * ${actVO.actFee} + ' 元';
	}
	function onRegisClick(){
		if(${memID} == "" || ${memID} == null) {
			window.location.href = "/cga103g3/frontend/member/memberLogin.jsp";
			return;
		} 
		console.log('test');
	    let actNum = document.querySelector('#actNum');
	    let actFee = document.querySelector('#fee');
		console.log(actNum.value);

	    fetch('/cga103g3/RegisFetch', {
	        method: 'POST',
	        headers: { 'Content-Type': 'application/json' },
	        body: JSON.stringify({
	        	memID: ${memID},
	        	actID: ${actVO.actID},
	            actNum: actNum.value,
	            actFee: actFee.textContent
	        })
	    })
// 	    .then(res => res.json())
// 	    .then(body => {
// 	        const { successful, message } = body;
// 	            if (successful) {
// 	                alert('報名成功!');
// 	            } else {
// 	                alert(message ?? '報名失敗');
// 	            }
// 	    });
// 	    window.location.href = "/cga103g3/frontend/actregis/listMemRegis.jsp"
	}
</script>
</body>
</html>