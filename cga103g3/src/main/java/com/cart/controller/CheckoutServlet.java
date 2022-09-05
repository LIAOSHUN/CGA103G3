package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartService;
import com.cart.model.CheckoutService;
import com.coupontype.model.CouponTypeService;
import com.orderdetail.model.OrderDetailVO;
import com.orderlist.model.OrderListService;
import com.orderlist.model.OrderListVO;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String sessionId = null;
	

			
			/***********************一.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			
			Integer memID = Integer.valueOf(req.getParameter("memID"));
			String receiverName = req.getParameter("receiverName");
			String receiverPhone = req.getParameter("receiverPhone");
			String address = req.getParameter("address");
			
			

			//供給錯誤處理用
			OrderListVO orderListVO = new OrderListVO();
			orderListVO.setRecName(receiverName);
			orderListVO.setRecPhone(receiverPhone);
			
			
			
			/***********************二.利用迭代時，建立訂單名細的list，用來insert到資料庫*************************/
			
			String values[] = req.getParameterValues("pdID");
			String valuesi[] = req.getParameterValues("itemSales");
			String valuesp[] = req.getParameterValues("price");
			List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
			
			Integer pdID = 0;
			Integer itemSales = 0;
			Integer price = 0;
			 
				
				for (int i = 0; i < values.length; i++) {
					//順便利用迭代時，建立訂單名細的list，用來insert到資料庫
					OrderDetailVO orderDetailVO = new OrderDetailVO();
					
					pdID = Integer.valueOf(values[i]);
					itemSales = Integer.valueOf(valuesi[i]);
					price = Integer.valueOf(valuesp[i]);
					
					orderDetailVO.setPdID(pdID);
					orderDetailVO.setItemSales(itemSales);
					orderDetailVO.setPrice(price);
					list.add(orderDetailVO);
				}
			
			
			/***************************三.1.新增訂單，及訂單明細，2.更新mysql庫存量3.更新優惠券庫存量及4.redis購物車調整數量***************************************/
			CheckoutService checkoutService = new CheckoutService();
			sessionId = (String) req.getSession().getAttribute("sessionId");
			checkoutService.allJobs(memID, 27001, 100.0, 100.0, 100, 0, receiverName, address, receiverPhone, 0, list, sessionId);
			
			
//			以上交易都確定成功，才會去對購物進行處理，避免上面步驟出現rollback的話，會造成購物車內容已被刪除，無法找回的情形
//			去除redis購物車被選擇的商品
			for (int i = 0; i < values.length; i++) {
				
				pdID = Integer.valueOf(values[i]);
				CartService cartSvc = new CartService();
				cartSvc.deleteItemChecked(sessionId, pdID);
			}
			/***************************4.新增完成,準備轉交到我的訂單***********/
			String url = "/frontend/orderlist/myOrderList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
	}

}
