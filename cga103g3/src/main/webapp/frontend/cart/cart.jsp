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
   List<CartItemVO> cartItems = (List<CartItemVO>) request.getAttribute("cartItems");
   %>
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



	<%
	 for (int index = 0; index < cartItems.size(); index++) {
		 CartItemVO CartItem = cartItems.get(index);
	%>
	<table>
	<tr>
		<td width="200"><%=CartItem.getPdName()%>   </td>
		<td width="100" >
			$<span class='price'><%=CartItem.getPdPrice()%></span>  </td>
		<td width="100">
			<form action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
				<input type="hidden" name="action"  value="changeItemCount">
				<input class="pdID" type="hidden" name="pdID"  value="<%=CartItem.getPdID()%>">
				<input class="plus" type="submit" value="+" style="background-color:lightgreen;outline-style: none ;border: 1px solid #ccc;border-radius: 3px;box-shadow: 0 0 35px 15px gray outset;">
				<input class="count" type="text" readonly  style="background-color:lightgray;width:22px;height:23px;outline-style: none ;border: 1px solid #ccc;border-radius: 3px;text-align:center;padding-left:2px;" name="count"  value="<%=CartItem.getCount()%>" />
				<input class="sub" type="submit" value="-" style="background-color:lightgreen;outline-style: none ;border: 1px solid #ccc;border-radius: 3px;box-shadow: 0 0 35px 15px gray outset;">
			</form>
		</td>
		<td width="100">
			$<span class='smallPrice'><%=CartItem.getPdPrice() * CartItem.getCount()%></span></td>
		
        <td width="120">
          <form name="deleteForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="deleteItem">
              <input type="hidden" name="pdID"  value="<%=CartItem.getPdID()%>">
              <input type="submit" value="刪 除" class="button">
          </form>
         </td>
	</tr>
	</table>	
	<%}%>
	<div>
		<form name="checkoutForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="checkout"> 
              <input type="submit" value="前往結帳" class="button">
        </form>
    </div>
<%}else{%>
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
			<tr>
				<td width="620">目前尚無商品</td>
			</tr>
		</table>
	<%}%>
	
	<div>
		<a href="testpro.jsp"><font size="+1" color="green"> 繼 續 購 物</font></a>
	</div>
</body>

<script>


	let counts = document.getElementsByName('count');
	let plus = document.querySelectorAll('.plus');
	let sub = document.querySelectorAll('.sub');
	let price = document.querySelectorAll('.price');
	let smallPrice = document.querySelectorAll('.smallPrice');
	let pdIDs = document.querySelectorAll('.pdID');
	
		for(let index = 0; index < <%=cartItems.size()%>;index++){
			
			plus[index].addEventListener('click', function () {
    			let newCount = parseInt(counts[index].value) + 1;
    			counts[index].value = newCount;
    			
    			smallPrice[index].innerText = newCount * price[index].innerText;
			});
		

			sub[index].addEventListener('click', function () {
				
				
				if(parseInt(counts[index].value)>1){
					let newCount = parseInt(counts[index].value) - 1;
			
    			counts[index].value = newCount;
    			smallPrice[index].innerText = newCount * price[index].innerText;
				}else{
					
				};
				
				
    			
    			
			});
			
			
			pdIDs[index].value
			counts[index].value
				
		}
	

	
	
</script>
</html>