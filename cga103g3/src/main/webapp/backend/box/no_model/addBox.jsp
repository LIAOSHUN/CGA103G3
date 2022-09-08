<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.box.model.*"%>

<%
  BoxVO boxVO = (BoxVO) request.getAttribute("list");
%>
<%= boxVO==null %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>包廂資料新增 - addBox.jsp</title>

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
		 <h3>包廂資料新增 - addBox.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/backend/selectAll_page.jsp"><img src="../images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

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

	<jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
	<tr>
		<td>門市名稱:</td>
		<td><select size="1" name="storeID">
			<c:forEach var="storeVO" items="${storeSvc.all}"> 
				<option value="${storeVO.storeID}" >${storeVO.storeName}
			</c:forEach>
		</select></td>
	</tr>
	
	<jsp:useBean id="boxTypeSvc" scope="page" class="com.boxtype.model.BoxTypeService" />
	<tr>
		<td>包廂類型:</td>
		<td>
		<select size="1" name="boxTypeID">
			<c:forEach var="boxTypeVO" items="${boxTypeSvc.all}"> 
				<option value="${boxTypeVO.boxTypeID}" >${boxTypeVO.boxName}
			</c:forEach>
		</select>
	</td>
	</tr>

	<tr>
		<td>包廂人數:<font color=red><b>*</b></font></td>
		<td>		
		<select size="1" name="boxCapcity">
				<option value="4" >4 人以下
				<option value="8" >5 - 8 人
				<option value="12" >9 - 12 人
		</select>
		</td>
	</tr>
	
	<tr>
		<td>包廂價格:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="boxPrice" size="10"
			 value="<%= (boxVO==null)? "" : boxVO.getBoxPrice()%>" /></td>
	</tr>
	<tr>
		<td>包廂說明:</td>
		<td>  
			<textarea  name="boxDescription" rows="1.5" cols="15"> </textarea>
		</td>
	</tr>
	
     <tr>
     <td>
      <label>包廂照片:</label> 
      <input type="file" id="the_file" name="boxImg" />
      </td>
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
		<img class="show" />
</div>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

<script src="<%=request.getContextPath()%>/backend/store/js/fileImg.js"> </script>
</body>

</html>