<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart.jsp</title>
</head>
<body>
<br>
   <% 
   List<CartItemVO> cartItems = (List<CartItemVO>) request.getAttribute("cartItems");%>
<%if (buylist != null && (buylist.size() > 0)) {%>

<font size="+1">目前購物車明細</font>

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
	 for (int index = 0; index < buylist.size(); index++) {
		BOOK order = buylist.get(index);
	%>
	<tr>
		<td width="200"><%=order.getName()%>     </td>
		<td width="100"><%=order.getAuthor()%>   </td>
		<td width="100"><%=order.getPublisher()%></td>
		<td width="100"><%=order.getPrice()%>    </td>
		
        <td width="120">
          <form name="deleteForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action"  value="DELETE">
              <input type="hidden" name="del" value="<%= index %>">
              <input type="submit" value="刪 除" class="button">
          </form>
         </td>
	</tr>
	<%}%>
</table>
<p>
          <form name="checkoutForm" action="Shopping.html" method="POST">
              <input type="hidden" name="action"  value="CHECKOUT"> 
              <input type="submit" value="付款結帳" class="button">
          </form>
<%}%>
</body>
</html>