<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page import="java.util.*"%>
<%@ page import="com.actregis.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActRegisService actRegisSvc = new ActRegisService();
    List<ActRegisVO> list = actRegisSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有活動報名資料</title>

<style>
	h3 {
		margin: 0px 30px;
		padding-top: 30px;
	}
  table {
	width: 960px;
	background-color: white;
	margin-bottom: 5px;
	line-height: 25px;		/*表格行高固定*/
	word-break:break-all;	/*td內容過長不會被撐開*/
	position:absolute;
	margin-top: 20px;
	margin-left: 30px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
	text-align: center !important;
	white-space: nowrap;		 /*限定不可斷行*/
  }
</style>

</head>
<body bgcolor='white'>
<%@include file="/backend/bkhead.jsp"%>
<div>
	<h3 style="margin-bottom: 0px;">所有活動列表</h3>
<table>
	<tr>
		<th>會員編號</th>
		<th>活動編號</th>
		<th>報名時間</th>
		<th>報名人數</th>
		<th>報名總費用</th>
		<th>繳費狀態</th>
		<th>報名狀態</th>
	</tr>

	<c:forEach var="actRegisVO" items="${list}">
		
		<tr>
			<td>${actRegisVO.memID}</td>
			<td><a href="<%=request.getContextPath()%>/ActServlet?actID=${actRegisVO.actID}&action=getOne_For_Display" class="actInfo">
					${actRegisVO.actID}</a></td>
			<td><javatime:format value="${actRegisVO.regisTime}" pattern="yyyy-MM-dd HH:mm" /></td>
			<td>${actRegisVO.actNum} 人</td>
			<td>${actRegisVO.actFee} 元</td>
			<td><c:if test="${actRegisVO.feeStatus == 0 }">0：未繳費</c:if>
				<c:if test="${actRegisVO.feeStatus == 1 }">1：已繳費</c:if></td> 
			<td><c:if test="${actRegisVO.regisStatus == 0 }">0：取消報名</c:if>
				<c:if test="${actRegisVO.regisStatus == 1 }">1：報名</c:if></td> 
		</tr>
	</c:forEach>
</table>
</div>
<%@include file="/backend/bkfoot.jsp"%>

</body>
</html>