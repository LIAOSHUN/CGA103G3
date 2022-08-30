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
  
    table {
	width: 800px;
	
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid gray;
  }
  th, td {
    padding: 15px;
    text-align: center;
  }
</style>

<style>
	.count{
		display: inline-block;
	}
	#checkout{
		background-color: #39ac7e  !important;
	}

  .plus, .sub{
  	width: 10px;
  	
  	justify-content: center;
	-ms-align-items: center;
	align-items: center;
	font-family: Poppins-Medium;
	
    font-size: 15px;
  	line-height: 1.466667;
  	text-transform: uppercase;
  
  	color: black;
  	font-family: fantasy;
  	font-weight: bold;
  
  	background-color: #7ed3cd;
  
  	border-radius: 25px;
  
  	padding-left: 15px;
  	padding-right: 15px;
    
  	-webkit-transition: all 0.4s;
  	-o-transition: all 0.4s;
  	-moz-transition: all 0.4s;
  	transition: all 0.4s;
  
  	display: inline-block;
  }
  
  .plus:hover {
  border-color: #fff;
  background-color: gray;
  color: white;
	}

  .plus:hover i {
  	color: #fff;
  }
  .sub:hover {
  	border-color: #fff;
  	background-color: gray;
  	color: white;
  }

  .sub:hover i {
  	color: #fff;
  }
  
  .table_head{
  }
  
 

</style>
</head>
<body>
<%@ include file="../frontendhead.jsp" %>
<br>
   <% 
   List<CartItemVO> cartItems = (List<CartItemVO>) request.getAttribute("cartItems");
   %>
<%if (cartItems != null && (cartItems.size() > 0)) {%>

<font size="+1">購物車明細</font>


<table class="wrap-table-shopping-cart">
    <tr class="table_head"> 
      <th class="column-1">遊戲名稱</th>
      <th class="column-3">單價</th>
      <th class="column-4">數量</th>
      <th class="column-5">價格</th>
      <th width="20">移除商品</th>
    </tr>

	<%
	 for (int index = 0; index < cartItems.size(); index++) {
		 CartItemVO CartItem = cartItems.get(index);
	%>
	
	<tr class="tr table_row" >
		<td class="column-1"><%=CartItem.getPdName()%>   </td>
		<td class="column-3" >
			$<span class='price'><%=CartItem.getPdPrice()%></span>  </td>
		<td class="column-4">
			<form class='form' action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
				<input type="hidden" name="action"  value="changeItemCount">
				<input class="pdID" type="hidden" name="pdID"  value="<%=CartItem.getPdID()%>">
				<input class="plus" type="button" value="+" >
				<span class="count"><%=CartItem.getCount()%></span>
				<input class="sub" type="button" value="-" >
			</form>
		</td>
		<td class="column-5">
			$<span class='smallPrice'><%=CartItem.getPdPrice() * CartItem.getCount()%></span></td>
		
        <td width="20">
          <form class="deleteForm" name="deleteForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="deleteItem">
              <input type="hidden" name="pdID"  value="<%=CartItem.getPdID()%>">
              <input type="button" id='delete' value="刪除" class="flex-c-m stext-101 cl0 size-202 bg3 bor7 hov-btn3 p-lr-15 trans-04 pointer">
          </form>
         </td>
	</tr>
	
	<%}%>
</table>
	
	<div>
		<form name="checkoutForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="checkout"> 
              <input type="submit" id='checkout' value="前往結帳" class="flex-c-m stext-101 cl0 size-101 bg2 bor14 hov-btn3 p-lr-15 trans-04 pointer">
        </form>
    </div>
<%}else{%>
		<font size="+1">購物車明細</font>

		<table class="wrap-table-shopping-cart">
    		<tr class="table_head"> 
     			<th class="column-1">遊戲名稱</th>
      			<th class="column-3">單價</th>
      			<th class="column-4">數量</th>
      			<th class="column-5">價格</th>
      			<th width="20">移除商品</th>
   			 </tr>
		</table>

		<table>
			<tr class="table_row">
				<td width="620">目前尚無商品</td>
			</tr>
		</table>
	<%}%>
	
	<div>
		<a href="testpro.jsp" class="flex-c-m stext-101 cl0 size-101 bg2  hov-btn3 p-lr-15 trans-04 pointer"> 繼 續 購 物</a>
	</div>
	
	<%@ include file="../frontendfoot.jsp" %>
</body>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>


	let counts = document.querySelectorAll('.count');
	let plus = document.querySelectorAll('.plus');
	let sub = document.querySelectorAll('.sub');
	let price = document.querySelectorAll('.price');
	let smallPrice = document.querySelectorAll('.smallPrice');
	let pdIDs = document.querySelectorAll('.pdID');
	let forms = document.querySelectorAll('.form');
	let trs = document.getElementsByClassName('tr');
	let deleteForms = document.getElementsByClassName('deleteForm');
	
<%-- 		for(let index = 0; index < <%=cartItems.size()%>;index++){ --%>
			
// 			plus[index].addEventListener('click', function () {
//     			let newCount = parseInt(counts[index].value) + 1;
//     			counts[index].value = newCount;
    			
//     			smallPrice[index].innerText = newCount * price[index].innerText;
// 			});
		

// 			sub[index].addEventListener('click', function () {
				
				
// 				if(parseInt(counts[index].value) > 1){
// 					let newCount = parseInt(counts[index].value) - 1;
			
//     				counts[index].value = newCount;
//     				smallPrice[index].innerText = newCount * price[index].innerText;
// 				}else if(parseInt(counts[index].value) === 1){
// 					var yes = confirm('你確定要刪除嗎？');
// 					if(yes){
// 						let newCount = parseInt(counts[index].value) - 1;
// 						counts[index].value = newCount;
// 		    			smallPrice[index].innerText = newCount * price[index].innerText;
// 					}else{
					    
// 					};
// 				 };	
// 			 }); 
// 		 }	 
// 					==============================================
		
		for(let index = 0; index < <%=cartItems.size()%>;index++){
			
			let pdID = parseInt(pdIDs[index].value);
			
			deleteForms[index].addEventListener('click', function () {
				$.ajax({
					url: "cart.do",
					type: "POST",
					data: {
							action: "deleteItem",
							pdID:pdID,
						},
						success: function(){
//								alert('隱形');
							trs[index].setAttribute('style', 'display: none');
						}
				})	
			});
		}
	
	
	

		for(let index = 0; index < <%=cartItems.size()%>;index++){
			
			let pdID = parseInt(pdIDs[index].value);
			
			plus[index].addEventListener('click', function () {
    			let newCount = parseInt(counts[index].innerText) + 1;
    			counts[index].innerText = newCount;
    			smallPrice[index].innerText = newCount * price[index].innerText;
    			
    			$.ajax({
					url: "cart.do",
					type: "POST",
					data: {
							action: "changeItemCount",
							count:newCount,
							pdID:pdID,
						},
				})	
			});
		

			sub[index].addEventListener('click', function () {
				
				if(parseInt(counts[index].innerText) > 1){
					let newCount = parseInt(counts[index].innerText) - 1;
			
    				counts[index].innerText = newCount;
    				smallPrice[index].innerText = newCount * price[index].innerText;
    				
    				$.ajax({
						url: "cart.do",
						type: "POST",
						data: {
								action: "changeItemCount",
								count:newCount,
								pdID:pdID,
							},
					})	
				}else if(parseInt(counts[index].innerText) === 1){
					var yes = confirm('您要移除此商品嗎？');
					if(yes){
						let newCount = parseInt(counts[index].innerText) - 1;
	    				
	    				$.ajax({
							url: "cart.do",
							type: "POST",
							data: {
									action: "changeItemCount",
									count:newCount,
									pdID:pdID,
								},
							success: function(){
// 								alert('隱形');
								trs[index].setAttribute('style', 'display: none');
							}
						})	
					}
				}
				
			 }); 
		 }						
					
					
					
					
// 					if (yes) {
// 						$.ajax({
// 			           	 	type:"POST",
// 			            	url:"cart.do",
// 			            	data:$('.'+'forms'+'['+ index+ ']').serialize(),  //直接傳表單裡的資料
// 			        	});      
// 					} else {
// 					    alert('你按了取消按鈕');
// 					}
					
					
					
// 					$.ajax({
// 						url: "cart.do",
// 						type: "POST",
// 						data: {
// 							action: "changeItemCount",
// 							count:1
// 						},
// 						success: function(){
							
// 						}
// 					})
					
					
// 					============================
				
			
		
	


	
	
</script>
</html>