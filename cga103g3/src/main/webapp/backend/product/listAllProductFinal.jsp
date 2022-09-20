<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.productimg.model.*"%>
<%
ProductService productsvc = new ProductService();
List<ProductVO> list = productsvc.getAll();
ProductImgService productImgsvc = new ProductImgService();
List<ProductImgVO> list1 = productImgsvc.getAll();
pageContext.setAttribute("list", list);
pageContext.setAttribute("list1", list1);

%>
<!DOCTYPE html>
<%@include file="/backend/bkhead.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>

	  <div class="content-wrapper">
          <br>
          <div class="navbar-nav align-items-center">
            <div class="nav-item d-flex align-items-center">
              <i class="bx bx-search fs-4 lh-0"></i>
              <input type="text" class="form-control border-0 shadow-none light-table-filter " placeholder="搜尋..."
                aria-label="Search..." data-table="order-table" />
            </div>
          </div>
          <div class="container-xxl flex-grow-1 container-p-y" style="width:12000px">

          <!-- =============================================================================================== -->
          <!-- Content內容-->
          
          <div class="card" style="width:105%">
            <h5 class="card-header"><strong>所有遊戲</strong></h5>
            <div class="card-body">
              <div class="table-responsive text-nowrap">
                <table class="table table-bordered order-table">
                  <thead>
                  
                    <tr>
                      <th>封面圖片</th>
                      <th>圖片(一)</th>
                      <th>圖片(二)</th>
                      <th>遊戲名稱</th>
                      <th>遊戲種類</th>
                      <th>遊戲金額</th>
                      <th>遊戲數量</th>
                      <th>遊戲資訊</th>
                      <th>遊戲推薦度</th>
                      <th>上架狀態</th>
                      <th>最後修改時間</th>
                      <th>修改</th>

                    </tr>
                  </thead>
                  <tbody>
                                    		<c:forEach var="productVO" items="${list}" >
                  
                    <tr>
                    				<c:forEach var="productImgVO" items="${list1}" varStatus="imgCount" begin="1" end="3">
                    
                      <td >
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <img
						src="<%=request.getContextPath()%>/ShowProductImg?pdID=${productVO.pdID}&count=${imgCount.count}"
						width=150px height=100px>
                      </td>
                      				</c:forEach>
                      
                      <td>${productVO.pdName}</td>
                      <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.productTypeVO.pdTypeName}</strong>
                      </td>
                      <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.pdPrice}</strong>
                      </td>
                      <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.pdAmount}</strong>
                      </td>
                      <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.pdDescription}</strong>
                      </td>
                      <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.pdStar}</strong>
                      </td>
                       <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.pdStatus}</strong>
                      </td>
                      <td>
                        <i class="fab fa-angular fa-lg text-danger me-3"></i> <strong>${productVO.pdUpdate}</strong>
                      </td>
                      <td>
					<FORM METHOD="post" ACTION="product.do" style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="pdID" value="${productVO.pdID}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
                    </tr>
                    				</c:forEach>
                    
                    
                    
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          </div>
          </div>
           <script>
      (function (document) {
        'use strict';

        // 建立 LightTableFilter
        var LightTableFilter = (function (Arr) {

          var _input;

          // 資料輸入事件處理函數
          function _onInputEvent(e) {
            _input = e.target;
            var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
            Arr.forEach.call(tables, function (table) {
              Arr.forEach.call(table.tBodies, function (tbody) {
                Arr.forEach.call(tbody.rows, _filter);
              });
            });
          }

          // 資料篩選函數，顯示包含關鍵字的列，其餘隱藏
          function _filter(row) {
            var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
            row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
          }

          return {
            // 初始化函數
            init: function () {
              var inputs = document.getElementsByClassName('light-table-filter');
              Arr.forEach.call(inputs, function (input) {
                input.oninput = _onInputEvent;
              });
            }
          };
        })(Array.prototype);

        // 網頁載入完成後，啟動 LightTableFilter
        document.addEventListener('readystatechange', function () {
          if (document.readyState === 'complete') {
            LightTableFilter.init();
          }
        });

      })(document);
    </script>

</body>
</html>
<%@include file="/backend/bkfoot.jsp"%>

