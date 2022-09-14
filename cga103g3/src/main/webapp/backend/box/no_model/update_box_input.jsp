<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.box.model.*"%>
<%@ page import="com.box.controller.*"%>

<%
BoxVO boxVO = (BoxVO) request.getAttribute("boxVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%= boxVO==null %>--<%= boxVO.getBoxImg() %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>員工資料修改 - update_emp_input.jsp</title>

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
  .pic {
	width: 250px;
	height: 250px;
	display: flex;
	justify-content: center;
	align-items: center;
}

img {
	max-width: 100%;
	max-height: 100%;
}
  
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>包廂資料修改 - update_box_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp"><img src="<%=request.getContextPath()%>/backend/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/box/box.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>包廂編號:<font color=red><b>*</b></font></td>
		<td><%=boxVO.getBoxID()%></td>
	</tr>
	<tr>
		<td>門市編號:<font color=red><b>*</b></font></td>
		<td><%=boxVO.getStoreID()%></td>
	</tr>
	<tr>
		<td>包廂人數:</td>
		<td>
			<select size="1" style="width: 100px" name="boxCapcity">
				<option value="<%=boxVO.getBoxCapcity()%>" selected disabled hidden><%=boxVO.getBoxCapcity()%> 人以下
				<option value="4" >4 人以下
				<option value="8" >8 人以下
				<option value="12" >12 人以下
			</select>
	</tr>
	<tr>
		<td>包廂類型:</td>
		<td>
		<select size="1" style="width: 100px" name="boxTypeID">
				<option value="<%=boxVO.getBoxTypeID() %>" selected disabled hidden><%=boxVO.getBoxTypeVO().getBoxName() %>
				<option value="1" >小包廂
				<option value="2" >中包廂
				<option value="3" >大包廂
		</select>
	</td>
	<tr>
		<td>包廂價格:</td>
		<td><input type="text" name="boxPrice" size="9" value="<%=boxVO.getBoxPrice()%>"></td>
	</tr>
	<tr>
		<td>包廂說明:</td>
		<td><textarea  name="boxDescription" rows="2" cols="15"><%=boxVO.getBoxDescription()%></textarea></td>
	</tr>
	<tr>
		<td>包廂照片:</td>
		<td><input type="file" id="the_file" name="boxImg" value="<%=request.getContextPath()%>/BoxImg?BoxID=${boxVO.boxID}"></td>
	</tr>
	
	<tr>
     	<td>包廂預約起始時間:</td>
     	<td><select name="boxBkStart">
				<option value="0">00:00</option>
				<option value="1">01:00</option>
				<option value="2">02:00</option>
				<option value="3">03:00</option>
				<option value="4">04:00</option>
				<option value="5">05:00</option>
				<option value="6">06:00</option>
				<option value="7">07:00</option>
				<option value="8">08:00</option>
				<option value="9">09:00</option>
				<option value="10">10:00</option>
				<option value="11">11:00</option>
				<option value="12">12:00</option>
				<option value="13">13:00</option>
				<option value="14">14:00</option>
				<option value="15">15:00</option>
				<option value="16">16:00</option>
				<option value="17">17:00</option>
				<option value="18">18:00</option>
				<option value="19">19:00</option>
				<option value="20">20:00</option>
				<option value="21">21:00</option>
				<option value="22">22:00</option>
				<option value="23">23:00</option></select></td>
     </tr>
     
     <tr>
     	<td>包廂預約結束時間:</td>
     	<td><select name="boxBkEnd">
				<option value="0">00:00</option>
				<option value="1">01:00</option>
				<option value="2">02:00</option>
				<option value="3">03:00</option>
				<option value="4">04:00</option>
				<option value="5">05:00</option>
				<option value="6">06:00</option>
				<option value="7">07:00</option>
				<option value="8">08:00</option>
				<option value="9">09:00</option>
				<option value="10">10:00</option>
				<option value="11">11:00</option>
				<option value="12">12:00</option>
				<option value="13">13:00</option>
				<option value="14">14:00</option>
				<option value="15">15:00</option>
				<option value="16">16:00</option>
				<option value="17">17:00</option>
				<option value="18">18:00</option>
				<option value="19">19:00</option>
				<option value="20">20:00</option>
				<option value="21">21:00</option>
				<option value="22">22:00</option>
				<option value="23">23:00</option></select></td>
     </tr>



</table>
<br>
<div class="pic" >
		<img class="show" src="<%=request.getContextPath()%>/BoxImg?BoxID=${boxVO.boxID}" />
</div>

<input type="hidden" name="action" value="updateBox">
<input type="hidden" name="boxID" value="<%=boxVO.getBoxID()%>">
<input type="hidden" name="storeID" value="<%=boxVO.getStoreID()%>">
<input type="submit" value="送出修改"></FORM>

<script src="<%=request.getContextPath()%>/backend/store/js/fileImg.js"> </script>
</body>

</html>