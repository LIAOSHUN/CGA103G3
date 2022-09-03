package com.orderlist.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderlist.model.OrderListService;
import com.orderlist.model.OrderListVO;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	if("CompositeQuery".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

			
		/***************************錯誤驗證**********************************/ 
		
		
		
		
		
		
		
		
			/***************************1.將輸入資料轉為Map**********************************/ 
			//採用Map<String,String[]> getParameterMap()的方法 
			//注意:an immutable java.util.Map 
			Map<String, String[]> map = req.getParameterMap();
			
			/***************************2.開始複合查詢***************************************/
			OrderListService orderListSvc = new OrderListService();
			List<OrderListVO> list  = orderListSvc.getAll(map);
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("CompositeQuery", list); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/backend/orderlistback/CqOrderList.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
	}
	
	if("CompositeQuery2".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		
		/***************************錯誤驗證**********************************/ 
		
		
		
		
		
		
		
		
		/***************************1.將輸入資料轉為Map**********************************/ 
		//採用Map<String,String[]> getParameterMap()的方法 
		//注意:an immutable java.util.Map 
		Map<String, String[]> map = req.getParameterMap();
		
		/***************************2.開始複合查詢***************************************/
		OrderListService orderListSvc = new OrderListService();
		List<OrderListVO> list  = orderListSvc.getAll(map);
		
		/***************************3.查詢完成,準備轉交(Send the Success view)************/
		req.setAttribute("CompositeQuery", list); // 資料庫取出的list物件,存入request
		RequestDispatcher successView = req.getRequestDispatcher("/backend/orderlistback/CqOrderList.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
		successView.forward(req, res);
	}
	
	
	
	}

}
