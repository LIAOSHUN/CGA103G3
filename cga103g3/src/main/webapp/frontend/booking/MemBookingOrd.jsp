<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/frontend/frontendhead.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.bookingorder.model.*"%>

<%
//取得會員訂位訂單資訊
BookingOrdService boSvc = new BookingOrdService();
List<BookingOrderVO> list = boSvc.getBookingOrd((Integer)(session.getAttribute("MemID")));
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的訂位訂單</title>
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">

	<!-- Bootstrap -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css" />

	<!-- Date -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>


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
<!-- ============================================ < Content內容 > ======================================================= -->
         <%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>             
            
          <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->
            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">訂位訂單 /</span> BookingOrder Information</h4>
             
              <!-- Basic Bootstrap Table -->
              <div class="card">
                <h5 class="card-header">訂位訂單表</h5>
                <div class="table-responsive text-nowrap">
                  <table class="table">
                    <thead>
                      <tr>
						<th>訂單編號</th>
						<th>會員編號</th>
						<th>訂位日期</th>
						<th>訂位時間</th>
						<th>訂位結束時間</th>
						<th>門市編號</th>
						<th>包廂編號</th>
						<th>訂位備註</th>
						<th>訂位狀態</th>
                      </tr>
                    </thead>
                    <tbody class="table-border-bottom-0">
            <%@ include file="./file/page1.file"%>
            <c:forEach var="bookingOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                      <tr>
                        <td>${bookingOrderVO.bookingID}</td>
						<td>${bookingOrderVO.memID}</td>
						<td>${bookingOrderVO.bookingDate}</td>
						<td>${bookingOrderVO.bookingStart}</td>
						<td>${bookingOrderVO.bookingEnd}</td>
						<td>${bookingOrderVO.boxVO.storeID}</td>
						<td>${bookingOrderVO.boxID}</td>
						<td>${bookingOrderVO.bookingNote}</td>						
						<td>${(bookingOrderVO.bookingStatus == 1)? '<span class="badge bg-label-success">訂位成功</span>':'<span class="badge bg-label-dark">訂位結束</span>'}</td>
                        <td><div class="dropdown"><button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown"><i class="bx bx-dots-vertical-rounded"></i></button><div class="dropdown-menu"><a class="dropdown-item" href="<%=request.getContextPath()%>/bookingorder/bookingorder.do?bookingID=${bookingOrderVO.bookingID}&bookingStatus=0&&action=finish_BoookingOrder"><i class="bx bx-x-circle"></i>取消訂單</a></div></div></td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
               <%@ include file="./file/page2.file"%>
                </div>
              </div>
              <!--/ Basic Bootstrap Table -->
<!-- -------------------------------------------------------------------------------------------------- -->
            </div>
            <!-- / Content -->
            <!-- Footer -->
            <footer class="content-footer footer bg-footer-theme">

            </footer>
            <!-- / Footer -->

            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
          
          
          
          
          
          
<!-- ============================================ < Content內容 > ======================================================= -->

    <!-- build:js assets/vendor/js/core.js -->
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/popper/popper.js"></script>
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="<%=request.getContextPath()%>/backend/backend_template/assets/js/dashboards-analytics.js"></script>
    
</body>
</html>
<%@include file="/frontend/frontendfoot.jsp"%>