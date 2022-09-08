<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/frontend/frontendhead.jsp"%>


<!DOCTYPE html>
<html>

<head>
<title>絆桌</title>

	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

	<!-- Date -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>

    <script src="https://www.itxst.com/package/jquery-3.5.1/jquery.min.js"></script>
    <script src="https://www.itxst.com/package/bootstrap-4.5.0/js/bootstrap.min.js"></script>
    <link href="https://www.itxst.com/package/bootstrap-4.5.0/css/bootstrap.css" rel="stylesheet">
    <script src="https://www.itxst.com/package/bootstrap-datepicker-1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script src="https://www.itxst.com/package/bootstrap-datepicker-1.9.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <link href="https://www.itxst.com/package/bootstrap-datepicker-1.9.0/css/bootstrap-datepicker.min.css" rel="stylesheet">
	
	
 <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/backend/backend_template/html/board-game (1).png" />
    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />
    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/fonts/boxicons.css" />
    <!-- Core CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
    <!-- Vendors CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/apex-charts/apex-charts.css" />
    <!-- Page CSS -->
    <!-- Helpers -->
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/helpers.js"></script>
    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/js/config.js"></script>
</head>
<body>
<!-- 	==================================================== 圖片 ================================================================ -->
					<div class="row g-0">
                      <div class="col-md-8">
                        <div class="card-body">
                          <h5 class="card-title"><b>訂位說明</b></h5>
                          <p id="bookingTitle" class="card-text">

                          </p>
                          <p class="card-text"><small class="text-muted"></small></p>
                        </div>
                      </div>
                      <div class="col-md-4">
                        <img class="card-img card-img-right" class="show" src="<%=request.getContextPath()%>/backend/images/tomcat.png" alt="Card image" width="200" height="250">
                      </div>
                    </div>
<!-- 	==================================================== 圖片 ================================================================ -->                  

<!-- 	==================================================== 表單 ================================================================ -->                  
			<form id="form">
				<div class="card mb-4">
                    <h5 class="card-header">門市訂位</h5>
                    <div class="card-body">
                    
                      <div class="mb-3">
                        <label for="memID" class="form-label">會員</label>
                        <input id="memID" type="text" class="form-control" placeholder="Enter Name">
                      </div>
                      
                      <div class="mb-3">
                        <label for="store" class="form-label">門市</label>
                        <select id="store" class="form-select" aria-label="Default select example" name="store">

                        </select>
                      </div>
                      
                      <div class="mb-3">
                        <label for="bookingDate" class="form-label">訂位日期</label>
                        <input id="bookingDate" type="text" class="form-control" placeholder="Booking Date" autocomplete="off">
                      </div>
                      
                      <div class="mb-3">
                        <label for="boxSize" class="form-label">包廂</label>
                        <select id="boxSize" class="form-select" aria-label="Default select example">

                        </select>
                      </div>
                      
                      <div class="mb-3">
                        <label for="bookingStart" class="form-label">訂位時間</label>
                        <select id="bookingStart" class="form-select" aria-label="Default select example">

                        </select>
                      </div>                   
                      
                      <div class="mb-3">
                        <label for="bookingEnd" class="form-label">結束時間</label>
                        <select id="bookingEnd" class="form-select" aria-label="Default select example">

                        </select>
                      </div>
                      
                      <div>
                        <label for="bookingNote" class="form-label">備註</label>
                        <textarea id="bookingNote" class="form-control" rows="3"></textarea>
                      </div>

					  <div class="mt-2">
						<button type="button" name="booking-btn" id="btn1" class="btn btn-primary me-2"
								onclick="bookingBtn()">預約訂位</button>
					  </div>
					  
                    </div>
                  </div>
			</form>
				
	


<script src="<%=request.getContextPath()%>/frontend/booking/js/bookingTest.js"></script>

<script>
$("#bookingDate").datepicker({
    minDate: 1,
    maxDate: "15D",
    dateFormat: "yy-mm-dd",
    beforeShowDay: function (date) { return [date.getDay() != 2, '']; }
});
</script>

</body>


</html>
<%@include file="/frontend/frontendfoot.jsp"%>