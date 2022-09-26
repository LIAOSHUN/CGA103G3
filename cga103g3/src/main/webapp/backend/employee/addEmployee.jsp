<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.employee.model.*"%>
<%@include file="/backend/bkhead.jsp"%>

<%
  EmployeeVO employeeVO = (EmployeeVO) request.getAttribute("employeeVO");
%>
<!DOCTYPE html>
<html>

<head>
<title>絆桌</title>

<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/booking/css/bootstrap.min.css" />

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/booking/css/style.css" />

<!-- Date -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>


<!-- Favicon -->
<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/backend/backend_template/html/board-game (1).png" />
<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet" />
<!-- Icons. Uncomment required icon fonts -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/fonts/boxicons.css" />
<!-- Core CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/core.css"
	class="template-customizer-core-css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/css/theme-default.css"
	class="template-customizer-theme-css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/backend_template/assets/css/demo.css" />
<!-- Vendors CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/apex-charts/apex-charts.css" />
<!-- Page CSS -->
<!-- Helpers -->
<script
	src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/helpers.js"></script>
<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script
	src="<%=request.getContextPath()%>/backend/backend_template/assets/js/config.js"></script>
</head>
<body>
	<!-- 	==================================================== 圖片 ================================================================ -->

	<!-- 	==================================================== 圖片 ================================================================ -->





	<!-- 	==================================================== 表單 ================================================================ -->
	<form id="form" METHOD="post" ACTION="employee.do" name="form1"
		enctype="multipart/form-data">

		<div class="card mb-4" style="width: 1000px; margin: 0 auto;">
			<h5 class="card-header">會員註冊</h5>
			<div class="card-body">
				<%-- 錯誤表列 --%>
				<div style="width: 1000px; margin: 0 auto;">
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

				</div>
				<div class="mb-3">
					<label for="empName" class="form-label">姓名:</label> <input
						id="empName" name="empName" type="text" class="form-control"
						autocomplete="off"
						 value="<%= (employeeVO==null)? "吳永志" : employeeVO.getEmpName()%>" >
				</div>

				<div class="mb-3">
					<label for="empPhone" class="form-label">電話:</label> <input
						id="empPhone" name="empPhone" type="text" class="form-control"
						autocomplete="off"
						value="<%= (employeeVO==null)? "09" : employeeVO.getEmpPhone()%>">
				</div>

				<div class="mb-3">
					<label for="empAvatar" class="form-label">頭貼:</label> <input
						id="empAvatar" name="empAvatar" type="file"
						class="form-control" autocomplete="off">
				</div>

			<div class="mb-3">
					<label for="empAccount" class="form-label">帳號：</label> <input
						id="empAccount" name="empAccount" type="text" class="form-control"
						autocomplete="off"
						 value="<%= (employeeVO==null)? "user" : employeeVO.getEmpAccount()%>">
				</div>


			<div class="mb-3">
					<label for="empPassWord" class="form-label">密碼：</label> <input
						id="empPassWord" name="empPassWord" type="password" class="form-control"
						autocomplete="off"
						 value="<%= (employeeVO==null)? "" : employeeVO.getEmpPassWord()%>">
				</div>


				<div class="mb-3">
					<label for="empHireDate" class="form-label">生日:</label> <input
						id="fdate1" name="empHireDate" type="text" class="form-control"
						autocomplete="off">
				</div>


			<div class="mb-3">
					<label for="empStatus" class="form-label"></label> <input
						id="empStatus" name="empStatus" type="hidden" class="form-control"
						autocomplete="off"
						 value="<%= (employeeVO==null)? "1" : employeeVO.getEmpStatus()%>">
				</div>







				<input type="hidden" name="action" value="insert"> <input
					class="mybtn" type="submit" value="送出">


			</div>
		</div>
	</form>







</body>
<%
java.sql.Date empHireDate = null;
try {
	empHireDate = employeeVO.getEmpHireDate();
} catch (Exception e) {
	empHireDate = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
}

.mybtn {
	border-radius: 5px;
	background-color: #242c6d;
	border: 1px solid #242c6d;
	color: #fff;
	border-radius: 3px;
	font-size: 14px;
	cursor: pointer;
	vertical-align: middle;
	padding: 5px 12px;
}

.gender {
	display: inline-block;
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#fdate1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=empHireDate%>'
		   ,
	// value:   new Date(),
	});
</script>


</html>

<%@include file="/backend/bkfoot.jsp"%>
