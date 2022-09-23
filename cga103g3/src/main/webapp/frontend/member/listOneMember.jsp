<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@include file="/frontend/frontendhead.jsp"%>

<%
MemberService memSVC = new MemberService();
MemberVO memberVO = memSVC.getOneMember((Integer) (session.getAttribute("memID")));

// MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
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

		<div class="card mb-4" style="width: 1000px; margin: 0 auto;">
			<h5 class="card-header">會員資料</h5>
			<div class="card-body">
				<%-- 錯誤表列 --%>
				<div class="mb-3">
					<label for="memName" class="form-label">姓名:</label> <input
						id="memName" name="memName" type="text" class="form-control"
						autocomplete="off"
						value="<%=memberVO.getMemName()%>" readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="memAccount" class="form-label">帳號:</label> <input
						id="memAccount" name="memAccount" type="text" class="form-control"
						autocomplete="off"
						value="<%=memberVO.getMemAccount()%>" readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="memPassWord" class="form-label">密碼:</label> <input
						id="memPassWord" name="memPassWord" type="text"
						class="form-control" autocomplete="off"
						value="<%=memberVO.getMemPassWord()%>" readonly="readonly">
				</div>


				<div class="mb-3">
					<label for="memGender" class="form-label">性別:</label> <input id="memGender"
						name="memGender" type="text" class="form-control" autocomplete="off"
						value="<%= memberVO.getMemGender()%>"
						maxlength="10" readonly="readonly">
				</div>



				<div class="mb-3">
					<label for="memPh" class="form-label">電話:</label> <input id="memPh"
						name="memPh" type="text" class="form-control" autocomplete="off"
						value="<%= memberVO.getMemPh()%>"
						maxlength="10" readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="memEmail" class="form-label">信箱:</label> <input
						id="memEmail" name="memEmail" type="text" class="form-control"
						autocomplete="off"
						value="<%=memberVO.getMemEmail()%>" readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="memAddress" class="form-label">地址:</label> <input
						id="memAddress" name="memAddress" type="text" class="form-control"
						autocomplete="off"
						value="<%=memberVO.getMemAddress()%>" readonly="readonly">
				</div>

				<div class="mb-3">
					<label for="memBirthday" class="form-label">生日:</label> <input
						id="memBirthday" name="memBirthday" type="text" class="form-control"
						autocomplete="off"
						value="<%=memberVO.getMemBirthday()%>" readonly="readonly">
				</div>



			<div>
				<FORM METHOD="post" name="form1"
					ACTION="<%=request.getContextPath()%>/frontend/member/member.do"
					style="margin-bottom: 0px;">
					 <input type="hidden"
						name="memID" value="<%=memberVO.getMemID()%>"> <input
						type="hidden" name="action" value="getOne_For_Update">
						<input class="mybtn" type="submit" value="修改資料">
				</FORM>

			</div>


			</div>
		</div>







</body>
<%
java.sql.Date memBirthday = null;
try {
	memBirthday = memberVO.getMemBirthday();
} catch (Exception e) {
	memBirthday = new java.sql.Date(System.currentTimeMillis());
}
%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/frontend/member/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/frontend/member/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/frontend/member/datetimepicker/jquery.datetimepicker.full.js"></script>

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
		   value: '<%=memBirthday%>'
	,
	// value:   new Date(),
	});
</script>


</html>
<%@include file="/frontend/frontendfoot.jsp"%>