<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.cart.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkout</title>

<style>
  
    table {
	width: 800px;
	
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

	#deleteItemChecked{
		background-color: #e3e66c  !important;
	}
	
	#deleteCartForm_div{
		display: inline-block;
		position: fixed;
  		bottom: 0;
  		right: 0;
 		width: 200px;
	}
  
</style>

</head>
<body>
<%@ include file="../frontendhead.jsp" %>
<br>
   <% 
   List<CartItemVO> checkedlist = (List<CartItemVO>) request.getAttribute("checkedlist");
   %>
<%if (checkedlist != null && (checkedlist.size() > 0)) {%>

<font style="font-size: 1.5em;">結帳</font>

<table id="table-1">
    <tr> 
      <th width="200">遊戲名稱</th>
      <th width="100">單價</th>
      <th width="100">數量</th>
      <th width="100">價格</th>
    </tr>
</table>

<form name="deleteItemCheckedForm" id="deleteItemCheckedForm" action="<%=request.getContextPath()%>/frontend/cart/checkout.do" method="POST">
<table>
	<%
	 for (int index = 0; index < checkedlist.size(); index++) {
		 CartItemVO cartItem = checkedlist.get(index);
		 
	%>
	
	
	<tr>
		
		<td width="200"><%=cartItem.getPdName()%>   
		<input class="pdID" type="hidden" name="pdID"  value="<%=cartItem.getPdID().toString()%>">
		<input class="itemSales" type="hidden" name="itemSales"  value="<%=cartItem.getCount()%>">
		<input class="price" type="hidden" name="price"  value="<%=cartItem.getPdPrice() * cartItem.getCount()%>">
		</td>
		<td width="100"><%=cartItem.getPdPrice()%>  元</td>
		<td width="100"><%=cartItem.getCount()%>	</td>
		<td width="100" ><span class=smallPrice><%=cartItem.getPdPrice() * cartItem.getCount()%></span>   元 </td>
	</tr>
		
	<%}%>
</table>



<!-- 會員ID先寫死 -->
<input type="hidden" name="memID" value="11001">
			
                    
<!-- =================================訂購資訊============================================== -->
                        <div>
                        <label style="font-size: 1.5em;">請填寫訂購資料</label>
                        
                        	<table>
								<tr>
									<td width="100">
										<div>
                                    		<label for="receiverName">收件人姓名</label>
                                    		<input type="text" id="receiverName" required name="receiverName">
                                		</div>
									</td>
									<td width="100">
										<div>
	                                    	<label for="receiverPhone" >聯絡手機</label>
	                                    	<input type="number" id="receiverPhone" required name="receiverPhone">
	                                	</div>
									</td>
								
									<td width="100">
										<div>
		                                    <label for="address" >地址</label>
		                                    <input type="text" id="address" required name="address">
		                                </div>
	                                </td>
								</tr>
							</table>
                        
                           </div>
                                
                                
                               
                                
                                
 <!-- =================================取貨方式============================================== -->              
                                
                             <div>
                                <label style="font-size: 1.5em;">取貨資料</label>
                                    
                                    
	                              <table>
									<tr>
										<td width="150">
											<div>
												<label >取貨方式</label>
												<select id="ordPick" required name="ordPick">
													<option value="-1" selected disabled>--請選擇取貨方式--</option>
		                                        	<option value="0">店面取貨</option>
		                                        	<option value="1">超商取貨</option>
		                                        	<option value="2">宅配取貨</option>
		                                    	</select>
	                                    	</div>
										</td>
										
										
										<td width="150">
											<div>
			                                    <label >付款方式</label>
			                                    <select required name="payMethod">
			                                    	<option value="-1" selected disabled>--請選擇--</option>
			                                        <option value="0">信用卡付款</option>
			                                        <option value="1">線上匯款</option>
			                                    </select>
			                                </div>
		                                </td>
	                                </tr>
								</table>   
                          </div>       
                          
                            
                                
                                
<!-- =================================選擇優惠券============================================== -->                              


                               
                                
 <!-- ================================金額計算============================================== -->
                                
                                
                          <div>
                                <label style="font-size: 1.5em; color:rgb(204, 207, 26);">本次訂單</label>
                                    
                                   	<table id="table-1">
									    <tr> 
									      <th class="column-5">原價</th>
									      <th class="column-5">優惠券折抵金額</th>
									      <th class="column-5">運費</th>
									      <th class="column-5">總價</th>
									    </tr>
									
									
									
									<tr class="tr table_row" >
			
										<td class="column-5" >
											$<span id="orgPrice_span"></span>
										</td>
										<td class="column-5">
										<jsp:useBean id="memCouponSvc" scope="page" class="com.memcoupon.model.MemCouponService" />
											 <select name="coupNo"  >
						                        		<option  value="0">不使用優惠券</option>
						                        <c:forEach var="memCouponVO" items="${memCouponSvc.showMemCouponByMemID(11001)}" > 
						                        	<c:if test="${memCouponVO.coupStatus == 0}">
		         									 	<option  value="${memCouponVO.coupNo}" >${memCouponVO.couponTypeVO.coupName}-$${memCouponVO.couponTypeVO.coupDiscount}
		        									</c:if>
		        								</c:forEach>   
				                    		</select> 
										</td>
										<td class="column-5">
											$<span id="ordPick_span">0</span>
										</td>
								        <td class="column-5">
											$<span >0</span>
										</td>
									</tr>
	                            </table>
                          </div>       
                                

   </form>   

<!-- ================================確認送出========================================== -->

	
	<div id='deleteCartForm_div'>
          
               
              <button type="submit" form="deleteItemCheckedForm" id="deleteItemChecked" value="送出訂單" class="flex-c-m stext-101 cl0 size-218 size-115 bg2 bor14 hov-btn3 p-lr-15 trans-04 pointer">
          		送出訂單
          	  </button>
    </div>
   
	<div>
		 <form name="backCartForm" action="<%=request.getContextPath()%>/frontend/cart/cart.do" method="POST">
              <input type="hidden" name="action"  value="getCart"> 
              <input type="submit" value="更改購物車品項" class="flex-c-m stext-101 cl0 size-102 bg2  bor14 hov-btn3 p-lr-15 trans-04 pointer">
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
		<a href="testpro.jsp"><font size="+1" color="green"> 請 繼 續 逛 商 城</font></a>
	<%}%>	
	<%@ include file="../frontendfoot.jsp" %>
</body>
<script>


	let ordPick = document.querySelector('#ordPick'); 
	let ordPick_span = document.querySelector('#ordPick_span');
	let smallPrices = document.querySelectorAll('.smallPrice');
	let orgPrice_span = document.querySelector('#orgPrice_span');
	
	
	
	//選擇取貨方式，連動運費
	ordPick.addEventListener('change',  e => {
		
		let fee = e.target.value;
		
		if(fee == 0){
			ordPick_span.innerText = 0;
		};
		
		if(fee == 1){
			ordPick_span.innerText = 60;
		};
		
		if(fee == 2){
			ordPick_span.innerText = 100;
		};
		
	});

	
	//計算訂單原價
		let orgPrice = 0;
		for(let index = 0; index < <%=checkedlist.size()%>;index++){
			
			orgPrice =  orgPrice + parseInt(smallPrices[index].innerText);
			
		}
			orgPrice_span.innerText = orgPrice;
	
	
























// 	let pdIDs = document.querySelectorAll('.pdID');
// 	let deleteItemChecked = document.getElementById('deleteItemChecked');


<%-- 	for(let index = 0; index < <%=checkedlist.size()%>;index++){ --%>
		
// 		let pdID = pdIDs[index].value;
// 		console.log(pdID);
// 		//+1
// 		deleteItemChecked.addEventListener('click', function () {
			
			
// 			$.ajax({
// 				url: "cart.do",
// 				type: "POST",
// 				data: {
// 						action: "deleteItemChecked",
// 						pdID:pdID,
// 					},
// 					success: function(){
// 						alert('成功');
						
// 					}
// 			})	
// 		});








</script>

</html>