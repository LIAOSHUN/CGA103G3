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
import com.coupontype.model.CouponTypeService;
import com.orderdetail.model.OrderDetailVO;
import com.orderlist.model.OrderListService;
import com.orderlist.model.OrderListVO;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String sessionId = null;
	

			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
			
			Integer memID = Integer.valueOf(req.getParameter("memID"));
			String receiverName = req.getParameter("receiverName");
			String receiverPhone = req.getParameter("receiverPhone");
			String address = req.getParameter("address");
			
			

			//供給錯誤處理用
			OrderListVO orderListVO = new OrderListVO();
			orderListVO.setRecName(receiverName);
			orderListVO.setRecPhone(receiverPhone);
			
			
			
			/***********************2.更新mysql庫存量及redis購物車，順便利用迭代時，建立訂單名細的list，用來insert到資料庫*************************/
			
			String values[] = req.getParameterValues("pdID");
			String valuesi[] = req.getParameterValues("itemSales");
			String valuesp[] = req.getParameterValues("price");
			List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
			
			Integer pdID = 0;
			Integer itemSales = 0;
			Integer price = 0;
			if(values != null) {
				
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
					
					//更新mysql庫存量及去除redis購物車被選擇的商品
					sessionId = (String) req.getSession().getAttribute("sessionId");
					CartService cartSvc = new CartService();
					
					cartSvc.updatePdAmount(sessionId, pdID);
					cartSvc.deleteItemChecked(sessionId, pdID);
				}
			}
			
			
			
			
			
			/***************************3.開始新增訂單，及訂單明細***************************************/
			OrderListService orderListService = new OrderListService();
			
			orderListVO = orderListService.addOrderList2(memID, 27001, 100.0, 100.0, 100, 0, receiverName, address, receiverPhone, 0, list);
			
		
			/***************************4.新增完成,準備轉交到我的訂單***********/
			String url = "/frontend/orderlist/myOrderList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
	}

}
