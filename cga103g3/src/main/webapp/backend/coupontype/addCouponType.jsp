<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.coupontype.model.*"%>


<%
	CouponTypeVO couponTypeVO = (CouponTypeVO)request.getAttribute("couponTypeVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>優惠券資料新增</title>


<style>
  table#table-1 {
	background-color: lightgreen;
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


<body>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>優惠券新增</h3></td><td>
		 <h4><a href="couponType_select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>優惠券新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="coupontype.do" name="form1">
<table>

	<tr>
		<td>優惠券名稱:</td>
		<td><input type="TEXT" name="coupName" size="45"
			 value="<%= (couponTypeVO==null)? "" : couponTypeVO.getCoupName()%>" /></td>
	</tr>
	<tr>
		<td>折價價格:</td>
		<td><input type="TEXT" name="coupDiscount" size="45"
			 value="<%= (couponTypeVO==null)? "" : couponTypeVO.getCoupDiscount()%>" /></td>
	</tr>
	<tr>
		<td>數量:</td>
		<td><input type="TEXT" name="coupQuantity" size="45"
			 value="<%= (couponTypeVO==null)? "" : couponTypeVO.getCoupQuantity()%>" /></td>
	</tr>
	
	<tr>
		<td>使用期間:</td>
		<td>
			<select size="1" name="coupDuration">
				<option value= 15 ${(couponTypeVO.coupDuration==15)? 'selected':'' }>15天
				<option value= 30 ${(couponTypeVO.coupDuration==30)? 'selected':'' }>30天
			</select>
		</td>
	</tr>
	

	<tr>
		<td>開始發放時間:</td>
		<td><input name="coupStart" id="start_date" type="text"></td>
	</tr>
	<tr>
		<td>結束發放時間:</td>
		<td><input name="coupEnd" id="end_date" type="text"></td>
	</tr>	
	
	<tr>
		<td>描述:</td>
		<td>
			<textarea style="width:300px;height:100px;" name="coupDesc" 
				value="<%= (couponTypeVO==null)? "" : couponTypeVO.getCoupDesc()%>" /></textarea>
		</td>
	</tr>
	


	

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<!-- 新增:第一次沒有值，所以會走這段 -->
<% 
  java.sql.Date coupStart = null;
  try {
	  coupStart = couponTypeVO.getCoupStart();
   } catch (Exception e) {
	   coupStart = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date coupEnd = null;
  try {
	  coupEnd = couponTypeVO.getCoupStart();
   } catch (Exception e) {
	   coupEnd = new java.sql.Date(System.currentTimeMillis());
   }
  

%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  value: '<%=coupStart%>',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  value: '<%=coupEnd%>',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false
	 });
});

</script>
</html>