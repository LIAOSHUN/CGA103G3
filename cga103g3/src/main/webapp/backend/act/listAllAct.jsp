<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	line-height: 25px;		/*表格行高固定*/
	table-layout:fixed;		/*表格寬度固定*/
	word-break:break-all;	/*td內容過長不會被撐開*/
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;

    white-space: nowrap;		 /*限定不可斷行*/
    overflow: hidden;    		 /*元素超出部分隱藏*/
    text-overflow: ellipsis; 	 /*文字超出部分顯示...*/
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
		<th width="60px">活動編號</th>
		<th width="70px">店面</th>
		<th width="120px">活動標題</th>
<!-- 		<th width="15%">活動敘述</th> -->
		<th width="100px">報名開始日期</th>
		<th width="120px">報名截止日期</th>
		<th width="90px">活動日期</th>
		<th width="70px">活動場次</th>
		<th width="60px">人數上限</th>
<!-- 		<th width="35px">費用</th> -->
		<th width="90px">目前報名人數</th>
		<th width="85px">活動狀態</th>
		<th width="50px">修改</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actVO.actID}</td>
			<td>${actVO.storeVO.storeName}</td>
			<td>${actVO.actTitle}</td>
<%-- 			<td>${actVO.actDescription}</td> --%>
			<td><fmt:formatDate value="${actVO.actTimeStart}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td><fmt:formatDate value="${actVO.actTimeEnd}" pattern="yyyy-MM-dd HH:mm"/></td>
			<td><fmt:formatDate value="${actVO.actDate}" pattern="yyyy-MM-dd"/></td>
			<td><fmt:formatDate value="${actVO.actDate}" pattern="HH:mm"/></td>
			<td>${actVO.regisMax}</td>
<%-- 			<td>${actVO.actFee}</td>  --%>
			<td>${actVO.actRegistration}</td>
			<td><c:if test="${actVO.actStatus == '0' }">0：活動中止</c:if>
				<c:if test="${actVO.actStatus == '1' }">1：報名中</c:if>
				<c:if test="${actVO.actStatus == '2' }">2：額滿截止</c:if>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/act/act.do" style="margin-bottom: 0px;">
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