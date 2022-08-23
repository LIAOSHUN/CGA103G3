<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ActService actSvc = new ActService();
    List<ActVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有活動資訊 - listAllAct.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有活動資訊 - listAllAct.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>活動編號</th>
		<th>店面編號</th>
		<th>活動標題</th>
		<th>活動敘述</th>
		<th>報名開始日期</th>
		<th>報名截止日期</th>
		<th>活動日期</th>
		<th>報名人數上限</th>
		<th>報名費用</th>
		<th>目前報名人數</th>
		<th>活動狀態</th>
		<th>修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actVO.actID}</td>
			<td>${actVO.storeID}</td>
			<td>${actVO.actTitle}</td>
			<td>${actVO.actDescription}</td>
			<td>${actVO.actTimeStart}</td>
			<td>${actVO.actTimeEnd}</td>
			<td>${actVO.actDate}</td>
			<td>${actVO.regisMax}</td>
			<td>${actVO.actFee}</td> 
			<td>${actVO.actRegistration}</td>
			<td>${actVO.actStatus}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/act/act.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="actID"  value="${actVO.actID}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>