<%@page import="com.actregis.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ActVO actVO = (ActVO) request.getAttribute("actVO"); //ActServlet.java(Concroller), 存入req的actVO物件
	ActRegisService actRegisSvc = new ActRegisService();
	List<ActRegisVO> list = actRegisSvc.getActRegistered(actVO.getActID());
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>活動資訊及報名會員</title>

<style>
h3 {
	margin: 0px 30px;
}
table {
	width: 945px;
	background-color: white;
	margin: 0px 30px 50px;
	line-height: 25px;		/*表格行高固定*/
/* 		table-layout:fixed;		/*表格寬度固定*/ */
	text-align: center;
}

table, th, td {
	border: 1px solid #CCCCFF;
	text-align: center;
}

th, td {
	padding: 5px;
	text-align: center !important;	
	white-space: nowrap;
}
</style>

</head>
<body>
<%@include file="/backend/bkhead.jsp"%>
	<table style="margin-top: 50px">
		<tr>
				<th>活動編號</th>
				<th>店面</th>
				<th>活動標題</th>
				<th>活動日期</th>
				<th>活動場次</th>
				<th>人數上限</th>
				<th>報名人數</th>
				<th>活動狀態</th>
				<th>修改</th>
			</tr>
		<tr>
					<td>${actVO.actID}</td>
					<td>${actVO.storeVO.storeName}</td>
					<td>
						<a href="<%=request.getContextPath()%>/frontend/act/activity.jsp" class="actInfo" data-actID="${actVO.actID}">${actVO.actTitle}</a>
					</td>
					<td><javatime:format value="${actVO.actDate}" pattern="yyyy-MM-dd" /></td>
					<td><c:if test="${actVO.dateNum == '14' }">下午場<br>(14:00~17:00)</c:if>
						<c:if test="${actVO.dateNum == '18' }">晚場<br>(18:00~21:00)</c:if>
					</td>
					<td>${actVO.regisMax} 人</td>
					<td>${actVO.actRegistration} 人</td>
					<td><c:if test="${actVO.actStatus == '0' }">0：活動中止</c:if>
						<c:if test="${actVO.actStatus == '1' }">1：報名中</c:if>
						<c:if test="${actVO.actStatus == '2' }">2：額滿截止</c:if>
					</td>
			<td>
			  <a href="">
					<button class="update btn" data-actID="${actVO.actID}">修改</button>
 				</a>
			</td>
		</tr>
	</table>
	<h3>報名列表</h3>
	<table style="padding-top: 30px;">
		<tr>
				<th>編號</th>
				<th>會員帳號</th>
				<th>會員姓名</th>
				<th>會員電話</th>
				<th>報名人數</th>
				<th>總費用</th>
		</tr>
		<c:forEach var="actRegisVO" items="${list}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td>${actRegisVO.memberVO.memAccount}</td>
				<td>${actRegisVO.memberVO.memName}</td>
				<td>${actRegisVO.memberVO.memPh}</td>
				<td>${actRegisVO.actNum} 人</td>
				<td>${actRegisVO.actFee} 元</td>
			</tr>
		</c:forEach>
	</table>
<!-- 	<h3>評價列表</h3> -->
<!-- 	<table style="padding-top: 30px;"> -->
<!-- 		<tr> -->
<!-- 				<th>編號</th> -->
<!-- 				<th>會員帳號</th> -->
<!-- 				<th>會員姓名</th> -->
<!-- 				<th>活動評價內容</th> -->
<!-- 				<th>滿意度</th> -->
<!-- 				<th>評價日期</th> -->
<!-- 		</tr> -->
<%-- 		<c:if test="${actRegisVO.actReview > 0 }"> --%>
<%-- 		<c:forEach var="actRegisVO" items="${list}" varStatus="revStatus"> --%>
<!-- 			<tr> -->
<%-- 				<td>${revStatus.count}</td> --%>
<%-- 				<td>${actRegisVO.memberVO.memAccount}</td> --%>
<%-- 				<td>${actRegisVO.memberVO.memName}</td> --%>
<%-- 				<td>${actRegisVO.actReview}</td> --%>
<%-- 				<td>${actRegisVO.satisfaction}</td> --%>
<%-- 				<td>${actRegisVO.reviewDate}</td> --%>
<!-- 			</tr> -->
<%-- 		</c:forEach> --%>
<%-- 		</c:if> --%>
<!-- 	</table> -->

	<script>
	$(document).ready(function(){
	    $(".update").mouseup(function (e) {
	        e.preventDefault();
	        let actID = $(this).attr("data-actID");
	        let url = "<%=request.getContextPath()%>/ActServlet?action=getOne_For_Update&actID=" + actID;
	        $("a").attr("href", url);
		});			
	})
	</script>
	<%@include file="/backend/bkfoot.jsp"%>
</body>
</html>