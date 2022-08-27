<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart.jsp</title>
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
<body>
<br>
   <% 
   List<CartItemVO> cartItems = (List<CartItemVO>) request.getAttribute("cartItems");%>
<%if (cartItems != null && (cartItems.size() > 0)) {%>

<font size="+1">購物車明細</font>

<table id="table-1">
    <tr> 
      <th width="200">遊戲名稱</th>
      <th width="100">單價</th>
      <th width="100">數量</th>
      <th width="100">價格</th>
      <th width="120">移除購買項目</th>
    </tr>
</table>

<table>

	<%
	 for (int index = 0; index < cartItems.size(); index++) {
		 CartItemVO CartItem = cartItems.get(index);
	%>
	<tr>
		<td width="200"><%=CartItem.getPdName()%>   </td>
		<td width="100"><%=CartItem.getPdPrice()%>  元</td>
		<td width="100"><%=CartItem.getCount()%>	</td>
		<td width="100"><%=CartItem.getPdPrice() * CartItem.getCount()%>   元 </td>
		
        <td width="120">
          <form name="deleteForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="deleteItem">
              <input type="hidden" name="pdID"  value="<%=CartItem.getPdID()%>">
              <input type="submit" value="刪 除" class="button">
          </form>
         </td>
	</tr>
	<%}%>
</table>
<p>
          <form name="deleteCartForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="deleteCart"> 
              <input type="submit" value="付款結帳" class="button">
          </form>
<%}%>
</body>
</html>