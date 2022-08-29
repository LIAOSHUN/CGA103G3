<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
ActVO actVO = (ActVO) request.getAttribute("actVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>活動新增 - addAct.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>活動新增 - addAct.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="act.do" name="form1">
		<table>
			<tr>
				<td>店名:</td>
				<td><select size="1" name="storeID">
						<option value="51001"
							<c:if test="${actVO.storeID == '51001' }">selected</c:if>>絆桌-Java店</option>
						<option value="51002"
							<c:if test="${actVO.storeID == '51002' }">selected</c:if>>絆桌-Servlet店</option>
				</select></td>
			</tr>
			<tr>
				<td>活動標題:</td>
				<td><input type="TEXT" name="actTitle" size="45"
					value="<%= (actVO==null)? "" : actVO.getActTitle()%>" /></td>
			</tr>
			<tr>
				<td>活動敘述:</td>
				<td><textarea rows="10" cols="43" maxlength="300"
						name="actDescription"><%= (actVO==null)? "" : actVO.getActDescription()%></textarea></td>
			</tr>
			<tr>
				<td>報名開始日期:</td>
				<td><input name="actTimeStart" id="act_date1" type="text"></td>
			</tr>
			<tr>
				<td>報名截止日期:</td>
				<td><input name="actTimeEnd" id="act_date2" type="text"></td>
			</tr>
			<tr>
				<td>活動日期:</td>
				<td><input name="actDate" id="act_date3" type="text"></td>
			</tr>
			<tr>
				<td>人數上限:<font color=red><b>*</b></font></td>
				<td><select size="1" name="regisMax">
						<c:forEach var="regisCount" begin="1" end="12">
							<option value="${regisCount}"
								${(actVO.regisMax==regisCount)?'selected':'' }>${regisCount}
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>報名費用:</td>
				<td><input type="TEXT" name="actFee" size="45"
					value="<%= (actVO==null)? "100" : actVO.getActFee()%>" /></td>
			</tr>
			<tr>
				<td>目前報名人數:</td>
				<td><input type="TEXT" size="45" disabled
					value="<%= (actVO==null)? "0" : actVO.getActRegistration()%>" /></td>
			</tr>
			<tr>
				<td>活動狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="actStatus">
						<option value="0"
							<c:if test="${actVO.actStatus == '0' }">selected</c:if>>0：活動中止</option>
						<option value="1"
							<c:if test="${actVO.actStatus == '1' }">selected</c:if>>1：報名中</option>
						<option value="2"
							<c:if test="${actVO.actStatus == '2' }">selected</c:if>>2：額滿截止</option>
				</select></td>
			</tr>
		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		<input type="hidden" name="actRegistration"	value="<%= (actVO==null)? "0" : actVO.getActRegistration()%>" />
		<input type="submit" value="送出新增">
	</FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
Timestamp actTimeStart = null;
try {
	actTimeStart = actVO.getActTimeStart();
} catch (Exception e) {
	actTimeStart = null;
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#act_date1').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#act_date2').val()?$('#act_date2').val():false
	   })
	  },
	  timepicker:false,
	  step: 60,
	  value: '<%=actTimeStart%>'
	 });
	 
	 $('#act_date2').datetimepicker({
	  format:'Y-m-d H:i',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#act_date1').val()?$('#act_date1').val():false,
	    maxDate:$('#act_date3').val()?$('#act_date3').val():false
	   })
	  },
	  timepicker:false,
	  step: 60,
	 });
	 
	 $('#act_date3').datetimepicker({
		  format:'Y-m-d H:i',
		  onShow:function(){
		   this.setOptions({
// 		    maxDate:$('#D_date4').val()?$('#D_date4').val():false,
		    minDate:$('#act_date2').val()?$('#act_date2').val():false
		   })
		  },
		  timepicker:false,
		  step: 60,
		});

		// 	 $('#D_date4').datetimepicker({
		// 		  format:'Y-m-d',
		// 		  onShow:function(){
		// 		   this.setOptions({
		// 		    minDate:$('#C_date3').val()?$('#C_date3').val():false
		// 		   })
		// 		  },
		// 		  timepicker:false
		// 	 });
	});
</script>
</html>