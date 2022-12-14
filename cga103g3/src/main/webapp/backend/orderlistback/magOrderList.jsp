<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.coupontype.model.*"%>
<%@ page import="com.memcoupon.model.*"%>
<%@ page import="com.orderlist.model.*"%>
<%@ page import="com.orderdetail.model.*"%>

<jsp:useBean id="orderListSvc" scope="page" class="com.orderlist.model.OrderListService" />
<jsp:useBean id="orderDetailSvc" scope="page" class="com.orderdetail.model.OrderDetailService" />
<%
	
	List<OrderListVO> list = orderListSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default"
	data-assets-path="../backend_template/assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />
<title>訂單管理</title>
<meta name="description" content="" />
<!-- Favicon -->
<link rel="icon" type="image/x-icon"
	href="../backend_template/html/board-game (1).png" />
<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet" />
<!-- Icons. Uncomment required icon fonts -->
<link rel="stylesheet"
	href="../backend_template/assets/vendor/fonts/boxicons.css" />
<!-- Core CSS -->
<link rel="stylesheet"
	href="../backend_template/assets/vendor/css/core.css"
	class="template-customizer-core-css" />
<link rel="stylesheet"
	href="../backend_template/assets/vendor/css/theme-default.css"
	class="template-customizer-theme-css" />
<link rel="stylesheet" href="../backend_template/assets/css/demo.css" />
<!-- Vendors CSS -->
<link rel="stylesheet"
	href="../backend_template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />
<link rel="stylesheet"
	href="../backend_template/assets/vendor/libs/apex-charts/apex-charts.css" />
<!-- Page CSS -->
<!-- Helpers -->
<script src="../backend_template/assets/vendor/js/helpers.js"></script>
<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="../backend_template/assets/js/config.js"></script>
<!-- ================================================================================================ -->
<style>
  
  * {
  	box-sizing: border-box;
  }
  
    table {
	width: 950px !important;
	
	margin-top: 5px !important;
	margin-bottom: 5px !important;
  }

   th{
   text-align: center  !important;
   color: black !important;
   }
  td {
    padding: 11px ;
    text-align: center;
  }

  tr:nth-child(odd){
  	background-color: #eee
  }
  #page2{
  	padding-left: 690px;
  }
  .detail{
  	font-size: 8px;
  	padding-top: 0px;
  	padding-bottom: 0px;
  	padding-left: 0px;
  	padding-right: 0px;
  	
  }

</style>
</head>
 <body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->
        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
          <div class="app-brand demo">
            <a href="<%=request.getContextPath()%>/backend/index.jsp" class="app-brand-link">
              <span class="app-brand-logo demo">
               <img src="<%=request.getContextPath()%>/backend/backend_template/html/board-game (1).png" alt="LogoTest" width="35px">
              </span>
              <span class="app-brand-text demo menu-text fw-bolder ms-2">絆桌</span>
            </a>
            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>
          <div class="menu-inner-shadow"></div>
          <ul class="menu-inner py-1">
<!-- ======================================================================================================= -->

            <!-- Dashboard -->
            <li class="menu-item ">
              <a href="<%=request.getContextPath()%>/backend/index.jsp" class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <div data-i18n="Analytics">首頁</div>
              </a>
            </li>
<!-- ======================================================================================================= -->
            <li class="menu-header small text-uppercase">
              <span class="menu-header-text">Pages</span>
            </li>
<!-- ======================================================================================================= -->
            <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-dock-top"></i>
                <div data-i18n="Account Settings">使用者管理</div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/employee/addEmployee.jsp" class="menu-link">
                    <div data-i18n="Account">新增管理員</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/employee/listAllEmployee.jsp" class="menu-link">
                    <div data-i18n="Notifications">管理員管理</div>
                  </a>
                </li>
              </ul>
<!-- ======================================================================================================= -->
              <li class="menu-item">
                <a href="javascript:void(0);" class="menu-link menu-toggle">
                  <i class="menu-icon tf-icons bx bx-file "></i>
                  <div data-i18n="Account Settings">前台網站管理</div>
                </a>
                <ul class="menu-sub">
                  <li class="menu-item">
                    <a href="<%=request.getContextPath()%>/frontend/member/listAllMember.jsp" class="menu-link">
                      <div data-i18n="Account">會員資料管理</div>
                    </a>
                  </li>
                </ul>
              </li>
<!-- ======================================================================================================= -->
            <li class="menu-item ">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-box"></i>
                <div data-i18n="Account Settings">商品管理</div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/product/addProduct3.jsp" class="menu-link">
                    <div data-i18n="Account">新增商品</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/product/listAllProductFinal.jsp" class="menu-link">
                    <div data-i18n="Notifications">商品管理</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/orderlistback/magOrderList.jsp" class="menu-link">
                    <div data-i18n="Connections">訂單管理</div>
                  </a>
                </li>
              </ul>
            </li>
<!-- ======================================================================================================= -->
            <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-detail"></i>
                <div data-i18n="Account Settings">遊戲分類管理</div>
              </a>
               <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/producttype/addProductType2.jsp" class="menu-link">
                    <div data-i18n="Account">新增遊戲種類</div>
                  </a>
                </li>
              </ul>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/producttype/listAllProductType2.jsp" class="menu-link">
                    <div data-i18n="Account">遊戲種類管理</div>
                  </a>
                </li>
              </ul>
            </li>
<!-- ======================================================================================================= -->
            
<!-- ======================================================================================================= -->
             <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-store-alt "></i>
                <div data-i18n="Account Settings"><b>門市管理</b></div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/store/model_AllStore.jsp" class="menu-link">
                    <div data-i18n="Account">門市查詢</div>
                  </a>
                </li>
              </ul>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/store/model_AddStore.jsp" class="menu-link">
                    <div data-i18n="Account">新增門市</div>
                  </a>
                </li>
              </ul>
            </li>
<!-- == Leo ================================================================================================= -->
            <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-briefcase-alt-2 "></i>
                <div data-i18n="Account Settings"><b>包廂管理</b></div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/box/model_AllBox.jsp" class="menu-link">
                    <div data-i18n="Account">包廂查詢</div>
                  </a>
                </li>
              </ul>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/box/model_AddBox.jsp" class="menu-link">
                    <div data-i18n="Account">新增包廂</div>
                  </a>
                </li>
              </ul>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/boxtype/model_AllBoxType.jsp" class="menu-link">
                    <div data-i18n="Account">包廂類型查詢</div>
                  </a>
                </li>
              </ul>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/boxtype/model_AddBoxType.jsp" class="menu-link">
                    <div data-i18n="Account">新增包廂類型</div>
                  </a>
                </li>
              </ul>
            </li>
<!-- == Leo ================================================================================================= -->
            <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bxs-calendar-check "></i>
                <div data-i18n="Account Settings"><b>門市訂位管理</b></div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/bookingorder/model_AllBookingOrder.jsp" class="menu-link">
                    <div data-i18n="Account">所有訂位訂單</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/GetStoreBookingOrdServlet/GetStoreBookingOrdServlet.do?StoreID=51001&action=get_StoreBookingOrd" class="menu-link">
                    <div data-i18n="Account">Java店/訂單</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/GetStoreBookingOrdServlet/GetStoreBookingOrdServlet.do?StoreID=51002&action=get_StoreBookingOrd" class="menu-link">
                    <div data-i18n="Account">Servlet店/訂單</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/GetStoreBookingOrdServlet/GetStoreBookingOrdServlet.do?StoreID=51003&action=get_StoreBookingOrd" class="menu-link">
                    <div data-i18n="Account">MySQL店/訂單</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/GetStoreBookingOrdServlet/GetStoreBookingOrdServlet.do?StoreID=51004&action=get_StoreBookingOrd" class="menu-link">
                    <div data-i18n="Account">不一樣的養身店/訂單</div>
                  </a>
                </li>
              </ul>
            </li>
  <!-- ======================================================================================================= -->
            <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-mobile-alt "></i>
                <div data-i18n="Account Settings">活動管理</div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/act/addAct.jsp" class="menu-link">
                    <div data-i18n="Account">新增活動</div>
                  </a>
                </li>
              </ul>
               <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/act/listAllAct.jsp" class="menu-link">
                    <div data-i18n="Account">活動列表</div>
                  </a>
                </li>
              </ul>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/actregis/listAllActRegis.jsp" class="menu-link">
                    <div data-i18n="Account">報名列表</div>
                  </a>
                </li>
              </ul>
            </li>
<!-- ======================================================================================================= -->
            
<!-- ======================================================================================================= -->
            <li class="menu-item">
              <a href="javascript:void(0);" class="menu-link menu-toggle">
                <i class="menu-icon tf-icons bx bx-user "></i>
                <div data-i18n="Account Settings">行銷管理</div>
              </a>
              <ul class="menu-sub">
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/coupontype/addCouponType.jsp" class="menu-link">
                    <div data-i18n="Account">新增優惠券</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/coupontype/sendCoupon.jsp" class="menu-link">
                    <div data-i18n="Account">優惠券發放</div>
                  </a>
                </li>
                <li class="menu-item">
                  <a href="<%=request.getContextPath()%>/backend/coupontype/listAllCouponType.jsp" class="menu-link">
                    <div data-i18n="Account">優惠券管理</div>
                  </a>
                </li>
              </ul>
            </li>
<!-- ======================================================================================================= -->
                  
        </aside>
        <!-- Layout container -->
        <div class="layout-page">
          <!-- Navbar -->
          <nav
            class="layout-navbar    navbar-detached  "
            id="layout-navbar"
          >
            <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
              <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                <i class="bx bx-menu bx-sm"></i>
              </a>
            </div>
            <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
              <ul class="navbar-nav flex-row align-items-center ms-auto">
                <!-- User -->
                <li class="nav-item navbar-dropdown dropdown-user dropdown">
                  <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                    <div class="avatar avatar-online">
                      <img src="<%=request.getContextPath()%>/backend/backend_template/assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                    </div>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                      <a class="dropdown-item" href="#">
                        <div class="d-flex">
                          <div class="flex-shrink-0 me-3">
                            <div class="avatar avatar-online">
                              <img src="<%=request.getContextPath()%>/backend/backend_template/assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                            </div>
                          </div>
                          <div class="flex-grow-1">
                            <span class="fw-semibold d-block">John Doe</span>
                            <small class="text-muted">Admin</small>
                          </div>
                        </div>
                      </a>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bx bx-cog me-2"></i>
                        <span class="align-middle">設定</span>
                      </a>
                    </li>
                    <li>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                    </li>
                    <li>
                      <a class="dropdown-item" href="auth-login-basic.html">
                        <i class="bx bx-power-off me-2"></i>
                        <span class="align-middle">登出</span>
                      </a>
                    </li>
                  </ul>
                </li>
                <!--/ User -->
              </ul>
            </div>
          </nav>
				<!-- / Navbar -->
				<!-- Content wrapper -->
				<div class="content-wrapper">
					<!-- =============================================================================================== -->
					<!-- Content內容-->
					<div class="container-xxl flex-grow-1 container-p-y">
						<div class="card">
							<h2 class="card-header">訂單管理</h2>
								<div>
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color:red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color:red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
							</div>
					<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/backend/orderlistback/orderListServlet.do" >
						<div style="text-align: right;" class="row">
							<div class="col">
								<h6>
									<span style="color: blue">訂單查詢:</span>
								</h6>
							</div>
							<div class="col">
								<b>訂單編號:</b> <input type="number" name="ordNo" value=""
									placeholder="22001">
							</div>
						
							<div class="col" style="text-align: center;">
								<b>訂單狀態:</b><br>
								<select name="ordStatus" id="ordStatus">
									<option value="">請選擇訂單狀態</option>
									<option value="0">未出貨</option>
									<option value="1">已出貨</option>
									<option value="2">已完成</option>
									<option value="3">取消</option>
								</select>
							</div>
							<div class="col">
								<b>收件人姓名:</b> 
									<input type="text" name="recName" value="" placeholder="可填入關鍵字"> 
									<input type="submit" value="開始搜尋" class="btn btn-outline-primary"> 
									<input type="hidden" name="action" value="CompositeQuery">
							</div>
						</div>
					</FORM>
					
						<div>
						
							<%@ include file="page1.file"%>
							<c:forEach var="orderListVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
								<table class="table" style="line-height:14px;">
									
										<tr>
											<th>訂單編號</th>
											<th>會員姓名</th>
											<th>優惠券編號</th>
											<th>總價</th>
											<th>訂單狀態</th>
											<th>下單時間</th>
											<th>收件人</th>
											<th>電話</th>
											<th>地址</th>
											<th>取貨方式</th>
											<th>修改</th>
											<th>明細</th>
										</tr>
										
										<tr>
											<td>${orderListVO.ordNo}</td>
											<td>${orderListVO.memberVO.memName}</td> 
											<td><c:if test="${orderListVO.coupNo == 0 }"><span>未用</span></c:if>
												<c:if test="${orderListVO.coupNo != 0 }"><span>${orderListVO.coupNo}</span></c:if></td>
											<td>$<fmt:formatNumber value="${orderListVO.ordLastPrice}"/></td>
											<td><c:if test="${orderListVO.ordStatus == 0 }"><span class="badge bg-label-warning me-1">未出貨</span></c:if><c:if test="${orderListVO.ordStatus == 1 }"><span class="badge bg-label-info me-1">已出貨</span></c:if><c:if test="${orderListVO.ordStatus == 2 }"><span class="badge bg-label-success me-1">已完成</span></c:if><c:if test="${orderListVO.ordStatus == 3 }"><span class="badge bg-label-danger me-1">已取消</span></c:if></td>
											<td><fmt:formatDate value="${orderListVO.ordCreate}" pattern="yyyy/MM/dd  HH點mm分" /></td>
											<td>${orderListVO.recName}</td>
											<td>${orderListVO.recPhone}</td>
											<td>${orderListVO.recAddress}</td>
											<td><c:if test="${orderListVO.ordPick == 0 }"><span class="badge bg-info">店取</span></c:if><c:if test="${orderListVO.ordPick == 1 }"><span class="badge bg-success">超取</span></c:if><c:if test="${orderListVO.ordPick == 2 }"><span class="badge bg-warning">宅配</span></c:if></td>
											<td><c:if
													test="${orderListVO.ordStatus != 2 && orderListVO.ordStatus != 3}"
													var="condition">
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/backend/orderlistback/orderListServlet.do"
														style="margin-bottom: 0px;">
														<input type="submit" value="修改" type="button" class="detail btn rounded-pill btn-primary"
															style="background-color: gray;"> <input type="hidden"
															name="ordNo" value="${orderListVO.ordNo}"> <input
															type="hidden" name="action" value="getOne_For_Update">
													</FORM>
												</c:if></td>
											<td><button 
			                          				  class="detail btn rounded-pill btn-primary"
							                          type="button"
							                          data-bs-toggle="collapse"
							                          data-bs-target="#collapseExample${orderListVO.ordNo}"
							                          aria-expanded="false"
							                          aria-controls="collapseExample${orderListVO.ordNo}">展 開</button></td>											
										</tr>	
										
							
								</table>
								
<!-- 								=================以下為訂單明細================================ -->
											<div class="collapse" id="collapseExample${orderListVO.ordNo}">
						                        <div class="d-grid d-sm-flex p-3 border">
						                          
								                      <table class="table table-bordered table-hover mb-0">
														<thead class="text-700 bg-gray-200">
															<tr>
																<th class="fw-600">遊戲名稱</th>
																<th class="fw-600">數量</th>
																<th class="fw-600">小計</th>
															</tr>
														</thead>
														<c:forEach var="orderDetailVO" 
														items="${orderDetailSvc.showOneOrderDetail(orderListVO.getOrdNo())}">
															<tbody>
																<tr style="background-color: #B7EBEB;">
																	<td class="p-3">${orderDetailVO.getProductVO(orderDetailVO.getPdID()).getPdName()}</td>
																	<td class="p-3">${orderDetailVO.itemSales}</td>
																	<td class="p-3">$${orderDetailVO.price}</td>
																</tr>
															</tbody>
														</c:forEach>
													</table>
			                        			</div>
			                        		</div>
<!-- 								=================以上為訂單明細================================ -->

							</c:forEach>		
								
						</div>
						<div id="page2">
							<%@ include file="page2.file"%>
						</div>
					</div>
					<!-- / Content -->
					<!-- =============================================================================================== -->
					<div class="content-backdrop fade"></div>
				</div>
				<!-- Content wrapper -->
			</div>
			<!-- / Layout page -->
		</div>
		<!-- Overlay -->
		<div class="layout-overlay layout-menu-toggle"></div>
	</div>
	<!-- / Layout wrapper -->
	<!-- Core JS -->
	<!-- build:js assets/vendor/js/core.js -->
	<script src="../backend_template/assets/vendor/libs/jquery/jquery.js"></script>
	<script src="../backend_template/assets/vendor/libs/popper/popper.js"></script>
	<script src="../backend_template/assets/vendor/js/bootstrap.js"></script>
	<script
		src="../backend_template/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

	<script src="../backend_template/assets/vendor/js/menu.js"></script>
	<!-- endbuild -->

	<!-- Vendors JS -->
	<script
		src="../backend_template/assets/vendor/libs/apex-charts/apexcharts.js"></script>

	<!-- Main JS -->
	<script src="../backend_template/assets/js/main.js"></script>

	<!-- Page JS -->
	<script src="../backend_template/assets/js/dashboards-analytics.js"></script>

	<!-- Place this tag in your head or just before your close body tag. -->
	<script async defer src="https://buttons.github.io/buttons.js"></script>
	<input type="hidden" name="action" value="insert">


</body>
</html>