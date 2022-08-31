function init() {
   $.ajax({
			url: "cart.do",
			type: "POST",
			data: {
				action: "init"
			},
			success: function(){
				alert('成功');
			}
		})
}

window.onload = init;