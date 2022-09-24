function init() {
   $.ajax({
			url: "getCart.do",
//			url: "initCart.do",
			url: "cart.do",
			type: "POST",
			data: {
				action: "init"
			},
			success: function(){
				
			}
		})
		
		
		$.ajax({
			url: "initCart.do",
			type: "POST",
						
			data: {
							
			},
			success: function(data){
//				console.log(data);
//				console.log(data.length);
				cartNum.innerText = data.length;
							
			}
		})
		
		
   $.ajax({
			url: "getCartHumber.do",
			type: "POST",
			data: {
				
			},
			success: function(){
				
			}
		})
		
		
}

window.onload = init;