package com.bookingset.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookingset.model.BookingSetService;
import com.bookingset.model.BookingSetVO;

@WebServlet("/GetStoreBookingOrd/GetStoreBookingOrd.do")
public class GetStoreBookingOrdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		/*************************************************** "查詢"門市訂位訂單 ******************************************************/	
		if("get_StoreBookingOrd".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String str = req.getParameter("StoreID");
			if(str == null || str.equals("0")) {
				errorMsgs.add("未選擇門市");
			}
			
			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("bokSetVO", bokSetVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_AllBookingOrder.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer storeID = Integer.valueOf(str);
			
			BookingSetVO bokSetVO = new BookingSetVO();
			bokSetVO.setStoreID(storeID);
			
			BookingSetService bookingSetSvc = new BookingSetService();
			List<BookingSetVO> list = bookingSetSvc.getStroeBookingOrd(storeID);
			
			req.setAttribute("list", list);
			String url = "/backend/bookingorder/model_GetStoreBookingOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		
	}

}
