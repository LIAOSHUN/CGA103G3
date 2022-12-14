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
	width: 950px;
	
	margin-top: 5px;
	margin-bottom: 5px;
  }
   table, th{ 
    border: 1px solid gray; 
   } 
   th{
   background-color: #39ac7e  !important;
   text-align: center  !important;
   color: black !important;
   }
  th, td {
    padding: 15px;
    text-align: center;
  }
  
  tr:nth-child(odd){
  	background-color: #eee
  }
</style>

<style>
	.count{
		display: inline-block;
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
  
 #all, #else{
 	padding: 10px 100px 30px 100px;

 }
 #else th{
 	padding-top: 0.4px;
	padding-bottom: 0.4px;
	max-height: 10px;
 }
 
 #checkoutForm{
 	padding-left: 790px;
 }
 #checkout{
	background-color: #39ac7e  !important;
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


<div id="all">
<div id="checkoutForm">
		<form name="checkoutForm" action="<%=request.getContextPath()%>/frontend/cart/dcart.do" method="POST">
<font size="+1">???????????????</font>
<table class="wrap-table-shopping-cart">
    <tr class="table_head"> 
      <th class="column-1">????????????</th>
      <th class="column-3">??????</th>
      <th class="column-4">??????</th>
      <th class="column-5">??????</th>
      <th width="20">????????????</th>
    </tr>

	<%
	 for (int index = 0; index < cartItems.size(); index++) {
		 CartItemVO CartItem = cartItems.get(index);
	%>
	
	<tr class="tr table_row" >
		<td class="column-1"><%=CartItem.getPdName()%>  
		<input class="pdID" type="checkbox" name="pdID"  value="<%=CartItem.getPdID().toString()%>">
		 </td>
		<td class="column-3" >
			$<span class='price'><%=CartItem.getPdPrice()%></span>  </td>
		<td class="column-4">
				
<!-- 				<input class="plus" type="button" value="+" > -->
				<input class="count" value="<%=CartItem.getCount()%>">
<!-- 				<input class="sub" type="button" value="-" > -->
		</td>
		<td class="column-5">
			$<span class='smallPrice'><%=CartItem.getPdPrice() * CartItem.getCount()%></span></td>
		
        <td width="20">
<!--               <input type="button" id='delete' value="??????" class="delete flex-c-m stext-101 cl0 size-202 bg3 bor7 hov-btn3 p-lr-15 trans-04 pointer"> -->
         </td>
	</tr>
	
	<%}%>
</table>

              <input type="submit" id='checkout' value="???????????????!" class="flex-c-m stext-101 cl0 size-101 bg2 bor14 hov-btn3 p-lr-15 trans-04 pointer">
        </form>
    </div>
</div>    
<%}else{%>
<div id="else">
		<font size="+1">???????????????</font>

		<table class="wrap-table-shopping-cart">
    		<tr class="table_head"> 
     			<th class="column-1">????????????</th>
      			<th class="column-3">??????</th>
      			<th class="column-4">??????</th>
      			<th class="column-5">??????</th>
      			<th width="20">????????????</th>
   			 </tr>
		</table>

		<table>
			<tr class="table_row">
				<td width="620">??????????????????</td>
			</tr>
		</table>
</div>		
	<%}%>
	
	<div>
		<a href="testpro.jsp" class="flex-c-m stext-101 cl0 size-101 bg2  hov-btn3 p-lr-15 trans-04 pointer"> ??? ??? ??? ??? ??? ??? ??? ???</a>
	</div>
	
	<%@ include file="../frontendfoot.jsp" %>
</body>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>


	let counts = document.querySelectorAll('.count');
	let plus = document.querySelectorAll('.plus');
	let sub = document.querySelectorAll('.sub');
	let price = document.querySelectorAll('.price');
	let smallPrice = document.querySelectorAll('.smallPrice');
	let pdIDs = document.querySelectorAll('.pdID');
	let trs = document.getElementsByClassName('tr');
	let deletes = document.getElementsByClassName('delete');
	
		
	

	
	
		//?????????????????????????????????
<%-- 		for(let index = 0; index < <%=cartItems.size()%>;index++){ --%>
			
// 			let pdID = parseInt(pdIDs[index].value);
			
// 			deletes[index].addEventListener('click', function () {
// 				$.ajax({
// 					url: "cart.do",
// 					type: "POST",
// 					data: {
// 							action: "deleteItem",
// 							pdID:pdID,
// 						},
// 						success: function(){
// 							//alert('??????');
// 							trs[index].setAttribute('style', 'display: none');
// 						}
// 				})
// 			});
// 		}
	

	
	
		//?????????????????????????????????
<%-- 		for(let index = 0; index < <%=cartItems.size()%>;index++){ --%>
			
// 			let pdID = parseInt(pdIDs[index].value);
			
// 			plus[index].addEventListener('click', function () {
//     			let newCount = parseInt(counts[index].innerText) + 1;
//     			counts[index].innerText = newCount;
//     			smallPrice[index].innerText = newCount * price[index].innerText;
    			
//     			$.ajax({
// 					url: "cart.do",
// 					type: "POST",
// 					data: {
// 							action: "changeItemCount",
// 							count:newCount,
// 							pdID:pdID,
// 						},
// 				})	
// 			});
		

// 			sub[index].addEventListener('click', function () {
				
// 				if(parseInt(counts[index].innerText) > 1){
// 					let newCount = parseInt(counts[index].innerText) - 1;
			
//     				counts[index].innerText = newCount;
//     				smallPrice[index].innerText = newCount * price[index].innerText;
    				
//     				$.ajax({
// 						url: "cart.do",
// 						type: "POST",
// 						data: {
// 								action: "changeItemCount",
// 								count:newCount,
// 								pdID:pdID,
// 							},
// 					})	
// 				}else if(parseInt(counts[index].innerText) === 1){
// 					/* sweetalert */
// 		 			 const swalWithBootstrapButtons = Swal.mixin({
// 		 		        customClass: {
// 		 		            confirmButton: 'btn btn-success ml-3',
// 		 		            cancelButton: 'btn btn-danger mr-3'
// 		 		        },
// 		 		        buttonsStyling: false
// 		 		    })

					
// 					=====================================
					
// 					 swalWithBootstrapButtons.fire({
//  		        		title: '?????????????????????????????????????',
//  		        		text: "????????????????????????",
//  		        		icon: 'warning',
//  		        		showCancelButton: true,
//  		        		confirmButtonText: '??????!',
//  		        		cancelButtonText: '????????????',
//  		        		reverseButtons: true
//  		    		}).then((result) => {
//  		    			if (result.isConfirmed) {
//  		 		            swalWithBootstrapButtons.fire(
//  		 		                '??????????????????????????????!',
//  		 		                '?????????????????????????????????????????????????????????',
//  		 		                'success'
//  		 		            )
 		    			
//  		 		          	let newCount = parseInt(counts[index].innerText) - 1;
 		    				
//  		    					$.ajax({
//  									url: "cart.do",
//  									type: "POST",
//  									data: {
//  											action: "changeItemCount",
//  											count:newCount,
//  											pdID:pdID,
//  										},
//  									success: function(){
//  										trs[index].setAttribute('style', 'display: none');
//  									}
//  								})	
//  		    			  }
 		    			
 		    			
// 					})
				
// 				  }
// 			 }); 
// 		 }						
					
// 					if (yes) {
// 						$.ajax({
// 			           	 	type:"POST",
// 			            	url:"cart.do",
// 			            	data:$('.'+'forms'+'['+ index+ ']').serialize(),  //???????????????????????????
// 			        	});      
// 					} else {
// 					    alert('?????????????????????');
// 					}
// 		function f1(){
<%-- 			for(let index = 0; index < <%=cartItems.size()%>;index++){ --%>
				
// 				//??????????????????(tr)???????????????(?????????????????????)????????????????????????????????????redis?????????????????????????????????????????????????????????
// 				if(trs[index].getAttribute('style') === 'display: none'){
// 					location.reload();//??????????????????
// 					return;
// 				}
// 			}
// 		};

</script>
</html>