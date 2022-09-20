function init() {
   $.ajax({
			url: "initCart.do",
//			url: "cart.do",
			type: "POST",
			data: {
				action: "init"
			},
			success: function(){
				
			}
		})
//   $.ajax({
//			url: "getCartHumber.do",
//			type: "POST",
//			data: {
//				
//			},
//			success: function(){
//				
//			}
//		})
		
		
}

window.onload = init;