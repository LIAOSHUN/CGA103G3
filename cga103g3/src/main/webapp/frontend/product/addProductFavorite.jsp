<%@page import="com.productfavorite.model.ProductFavoriteVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.producttype.model.*"%>

<%
ProductFavoriteVO productFavoriteVO = (ProductFavoriteVO) request.getAttribute("productFavoriteVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�C��������Ʒs�W - addProductType.jsp</title>

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
	<tr><td>
		 <h3>�C��������Ʒs�W - addProductType.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~���C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/productfavorite/ProductFavoriteServlet"  name="form1">
<table>
	<tr>
		<td>�|��ID:</td>
				
		
		<td><input type="TEXT" name="memID" size="45" 
			 value="<%= (productFavoriteVO==null)? 11004 : productFavoriteVO.getMemID()%>" /></td>
			 <td>�ӫ~ID:</td>
			 <td><input type="TEXT" name="pdID" size="45" 
			 value="<%= (productFavoriteVO==null)? 21009 : productFavoriteVO.getPdID()%>" /></td>
	</tr>

</table>

<br>

<input type="hidden" name="action" value="insert">

<input type="submit" value="�e�X�s�W"></FORM>

</body>
</html>