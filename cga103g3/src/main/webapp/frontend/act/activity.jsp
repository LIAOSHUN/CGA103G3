<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@page import="java.util.Set"%>
<%@page import="com.actimg.model.*"%>
<%@page import="com.act.model.*"%>
<%
	ActVO actVO = (ActVO) request.getAttribute("actVO");
	ActService actSvc = new ActService();
// 	Set<ActImgVO> actImgVo = actSvc.getImgsByAct(actVO.getActID());
	Set<ActImgVO> actImgVO = actSvc.getImgsByAct(61004);
 	pageContext.setAttribute("actImgVO", actImgVO);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
<title>${actVO.actTitle}</title>
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
	}
	.container-main::after {
		content: "";
		display: block;
		clear: both;
	}
	h1 {
		line-height: 2.5 !important;
		text-shadow: 2px 2px 5px #72c2bd;
	}
	div.col-md {
		float: right;
		right: 50px;
		max-width: 480px;
		max-height: 450px;
		margin-top: 20px;
	}
	div.textInfo {
		padding-left: 55px;
		font-size: 22px;
	}
	.btn-info {
        color: #666666 !important;
        background-color: #40e0d0 !important;
        border-color: #46b8da;
		letter-spacing: 3px !important;
		padding: 6px 25px !important;
		border-radius: 12px !important;
    }
    .btn {
        display: inline-block !important;       
        margin-bottom: 0 !important;
        font-size: 30px !important;
        font-weight: 400 !important;
        cursor: pointer !important;    
        touch-action: manipulation !important;	
    }
	div.forBtn {
		padding-top: 60px;
	}
	div.description {
		padding: 150px 65px 50px 55px;
		font-size: 18px;
	}

</style>
</head>
<body>
<%@ include file="../frontendhead.jsp" %>

<main>
	<div class="container-main">
		<!-- Bootstrap carousel -->
		<div class="col-md">
			<div id="carouselExample" class="carousel slide" data-bs-ride="carousel">
			  <ol class="carousel-indicators">
				<li data-bs-target="#carouselExample" data-bs-slide-to="0" class="active"></li>
				<li data-bs-target="#carouselExample" data-bs-slide-to="1"></li>
				<li data-bs-target="#carouselExample" data-bs-slide-to="2"></li>
			  </ol>
			  <div class="carousel-inner">
				<div class="carousel-item active">
				  <img class="d-block w-100" src="<%=request.getContextPath()%>/backend/backend_template/assets/img/elements/13.jpg" alt="First slide" />
				  <div class="carousel-caption d-none d-md-block">
				  </div>
				</div>
				<div class="carousel-item">
				  <img class="d-block w-100" src="<%=request.getContextPath()%>/backend/backend_template/assets/img/elements/2.jpg" alt="Second slide" />
				  <div class="carousel-caption d-none d-md-block">
				  </div>
				</div>
				<div class="carousel-item">
				  <img class="d-block w-100" src="<%=request.getContextPath()%>/backend/backend_template/assets/img/elements/18.jpg" alt="Third slide" />
				  <div class="carousel-caption d-none d-md-block">
				  </div>
				</div>
			  </div>
			  <a class="carousel-control-prev" href="#carouselExample" role="button" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExample" role="button" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			  </a>
			</div>
		</div>
		<div class="textInfo">
			<div class="actTitle">
				<h1>${actVO.actTitle}</h1>
			</div>
			<div>
				<div class="storeName">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
						<path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
						<path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
					</svg>
					<b>${actVO.storeVO.storeName}</b>
				</div>
				<div>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clock" viewBox="0 0 16 16">
						<path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71V3.5z"/>
						<path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0z"/>
					</svg>
					<b>活動日期：</b>
						<javatime:format value="${actVO.actDate}" pattern="yyyy-MM-dd" />
						<c:if test="${actVO.dateNum == '14' }">下午場(14:00~17:00)</c:if>
						<c:if test="${actVO.dateNum == '18' }">晚場(18:00~21:00)</c:if>					
				</div>
				<div>
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clock" viewBox="0 0 16 16">
						<path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71V3.5z"/>
						<path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0z"/>
					</svg>
					<b>報名截止日期：</b>
					<javatime:format value="${actVO.actTimeEnd}" pattern="yyyy-MM-dd　HH:mm" />
				</div>
				<div class="forBtn">
					<div class="btnToRegis">
						<a href="../actregis/addRegis.jsp">
							<button class="btnn btn-info" style="margin-left: 50px;">
								<b>活動報名</b>
							</button>
						</a>
						<a href="../actfav/listFav.jsp">
							<button class="btnn btn-info" style="margin-left: 35px;">
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
									<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
								</svg>
								<b>收藏</b>
							</button>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="description">
			<div>
				<b>活動介紹：</b>
			</div>
			<div style="padding-left: 90px;">
				${actVO.actDescription}
			</div>
		</div>
		
	</div>
</main>

<%@ include file="../frontendfoot.jsp" %>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/jquery/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/popper/popper.js"></script>
<script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/bootstrap.js"></script>
</body>
</html>