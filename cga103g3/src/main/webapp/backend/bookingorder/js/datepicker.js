	//指定每周固定日
	$("#bookingDate").datepicker({
		minDate: 1,
		maxDate: "15D",
		dateFormat: "yy-mm-dd"
		// beforeShowDay: nationalDays
		// beforeShowDay: function (date1) { return [date1.getDay() != 2, '']; }
	});