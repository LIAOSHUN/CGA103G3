package com.bookingorder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookingorder.model.BookingOrdService;
import com.bookingorder.model.BookingOrderVO;


@WebServlet("/bookingorder/bookingorder.do")
public class BookingOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

/*************************************************** 查詢"所有"訂位訂單 ******************************************************/
		// 來自AllBox.jsp的請求 查詢所有門市包廂
		if ("getAll".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************開始查詢資料 ****************************************/
			BookingOrdService bookOrdSvc = new BookingOrdService();
			List<BookingOrderVO> list = bookOrdSvc.getAll();
			
			
			try {
				
			} catch (NumberFormatException e) {
				errorMsgs.add("getAll錯誤");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/selectAll_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

			/***************************查詢完成,準備轉交(Send the Success view)*************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);    // 資料庫取出的list物件,存入session
			
			// Send the Success view
			String url = "/backend/bookingorder/allBookingOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交AllBox.jsp
			successView.forward(req, res);
		}
		
		
/*************************************************** "查詢單筆"訂位訂單 ******************************************************/	
		if (action.equals("getOne_BookingID")) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			String str = req.getParameter("BookingID").trim();
			String bookingIDReg = "^[\\d]{5}$";
			if(!str.matches(bookingIDReg)){
				errorMsgs.add("請輸入正確格式，訂單編號為5位數字");
			}

			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_AllBookingOrder.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer bookingID = Integer.valueOf(str);
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			BookingOrderVO bokOrdVO = bokOrdSvc.getOneBookingID(bookingID);
			if (bokOrdVO.getBoxID() == null) {
				errorMsgs.add("查無資料");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_AllBookingOrder.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("bokOrdVO", bokOrdVO);
			String url = "/backend/bookingorder/model_GetOneBookingOrd.jsp"; // 查詢單筆資料轉跳 updateBookingOrd.jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}


		
/*************************************************** "修改"訂位訂單(查詢單筆轉跳) ******************************************************/	
		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer bookingID = Integer.valueOf(req.getParameter("bookingID").trim());
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			BookingOrderVO bokOrdVO = bokOrdSvc.getOneBookingID(bookingID);
			
			try {
				
			} catch (NumberFormatException e) {
				errorMsgs.add("getOne_For_Update錯誤");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/selectAll_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("bokOrdVO", bokOrdVO);
			String url = "/backend/bookingorder/model_UpdateBookingOrd.jsp"; // 查詢單筆資料轉跳 updateBookingOrd.jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		
/*************************************************** "修改"訂位訂單資訊 ******************************************************/	
		if("update_BookingOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer bookingID = Integer.valueOf(req.getParameter("bookingID").trim());
			
			Integer memID = Integer.valueOf(req.getParameter("memID").trim());
			
			Integer boxID = Integer.valueOf(req.getParameter("boxID").trim());
			
			java.sql.Date bookingDate = java.sql.Date.valueOf(req.getParameter("bookingDate").trim());
			
			String bookingStart= null;
			try {
				bookingStart = req.getParameter("bookingStart").trim();
				if(bookingStart.length()==0) {
					errorMsgs.add("訂位起始時間: 未正確選擇");
				}
			} catch (Exception e) {
				errorMsgs.add("訂位起始時間: 未正確選擇");
			}
			
			String bookingEnd = null;
			try {
				bookingEnd = req.getParameter("bookingEnd").trim();
				if(bookingEnd.length()==0) {
					errorMsgs.add("訂位起始時間: 未正確選擇");
				}
			} catch (Exception e) {
				errorMsgs.add("訂位結束時間: 未正確選擇");
			}
			
			String bookingNote = req.getParameter("bookingNote").trim();
			
			Integer bookingStatus = Integer.valueOf(req.getParameter("bookingStatus").trim());
			
			BookingOrderVO bokOrdVO = new BookingOrderVO();
			bokOrdVO.setBookingID(bookingID);
			bokOrdVO.setMemID(memID);
			bokOrdVO.setBookingDate(bookingDate);
			bokOrdVO.setBookingStart(bookingStart);
			bokOrdVO.setBookingEnd(bookingEnd);
			bokOrdVO.setBoxID(boxID);
			bokOrdVO.setBookingNote(bookingNote);
			bokOrdVO.setBookingStatus(bookingStatus);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("bokOrdVO", bokOrdVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_UpdateBookingOrd.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			bokOrdVO = bokOrdSvc.updateBookingOrder(bookingDate, bookingStart, bookingEnd, boxID, bookingNote, bookingStatus, bookingID);
		
			req.setAttribute("bokOrdVO", bokOrdVO);
			String url = "/backend/bookingorder/model_AllBookingOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
/*************************************************** "修改"訂位訂單(結束訂單) ******************************************************/
		if("finish_BoookingOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer bookingID = Integer.valueOf(req.getParameter("bookingID").trim());
			
			BookingOrderVO bokOrdVO = new BookingOrderVO();
			bokOrdVO.setBookingID(bookingID);
			bokOrdVO.setBookingStatus(0);
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_AllBookingOrder.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			bokOrdVO = bokOrdSvc.cancelBooking(bookingID, 0);
			
			req.setAttribute("bokOrdVO", bokOrdVO);
			String url = "/backend/bookingorder/model_AllBookingOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
/*************************************************** "新增"訂位訂單 ******************************************************/	
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String memID = req.getParameter("memID").trim();
			String memIDReg = "^[\\d]{5}$";

			if (memID == null || memID.trim().length() == 0) {
				errorMsgs.add("請輸入會員編號");
			} else if (!memID.matches(memIDReg)){
				errorMsgs.add("請輸入正確格式，會員編號為5位數字");
			}
			
			// 因會員編號為Integer,如未輸入將提前轉跳
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_AddBookingOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			Integer boxID = Integer.valueOf(req.getParameter("boxID"));
			
			java.sql.Date bookingDate = null;
			try {
				 bookingDate = java.sql.Date.valueOf(req.getParameter("bookingDate").trim());
			} catch (IllegalArgumentException e) {
				bookingDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			String bookingStart = req.getParameter("bookingStart").trim();
			
			String bookingEnd = req.getParameter("bookingEnd").trim();
			
			//驗證起始與結束時間
			Integer checkStartTime = Integer.valueOf(bookingStart);
			Integer checkEndTime = Integer.valueOf(bookingEnd);
			if(checkEndTime < checkStartTime) {
				errorMsgs.add("訂位時間有誤，結束時間早於起始時間");
			}
			
			String bookingNote = req.getParameter("bookingNote").trim();
			if (bookingNote == null || (bookingNote.trim().length()) == 0) {
				bookingNote = null;
			}
			
			BookingOrderVO bookingOrdVO = new BookingOrderVO();
			bookingOrdVO.setMemID(Integer.valueOf(memID));
			bookingOrdVO.setBoxID(boxID);
			bookingOrdVO.setBookingDate(bookingDate);
			bookingOrdVO.setBookingStart(bookingStart);
			bookingOrdVO.setBookingEnd(bookingEnd);
			bookingOrdVO.setBookingNote(bookingNote);
			
			if (!errorMsgs.isEmpty()) {
req.setAttribute("bookingOrdVO", bookingOrdVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/bookingorder/model_AddBookingOrder.jsp");
				failureView.forward(req, res);
				return;
			}
			
			BookingOrdService bookingOrdSvc = new BookingOrdService();
			bookingOrdVO = bookingOrdSvc.addBooking(bookingOrdVO);
			
			String url = "/backend/bookingorder/model_AllBookingOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交model_AllBookingOrder.jsp
			successView.forward(req, res);
		}

		
/*************************************************** "客戶單筆"查詢 ******************************************************/	
		if ("getOne_BookingOrd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer memID = Integer.valueOf(req.getParameter("memID").trim());
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			List<BookingOrderVO> list = bokOrdSvc.getBookingOrd(memID);
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/selectAll_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("list", list);
			String url = "/frontend/booking/MemBookingOrd.jsp"; // 查詢單筆資料轉跳 getOneBookingOrd.jsp
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

/*************************************************** "會員"取消訂單 ******************************************************/	
		if("cancle_bookingOrd".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer bookingID = Integer.valueOf(req.getParameter("bookingID").trim());
			
//			Integer bookingStatus = Integer.valueOf(req.getParameter("bookingStatus").trim());
			
			BookingOrderVO bokOrdVO = new BookingOrderVO();
			bokOrdVO.setBookingID(bookingID);
			bokOrdVO.setBookingStatus(0);
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/selectAll_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			bokOrdVO = bokOrdSvc.cancelBooking(bookingID, 0);
			
			req.setAttribute("bokOrdVO", bokOrdVO);
			String url = "/backend/selectAll_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
/*************************************************** "刪除"訂位訂單 ******************************************************/
		if("delete".equals(action)) {
			Integer bookingID = Integer.valueOf(req.getParameter("bookingID").trim());
			
			BookingOrderVO bokOrdVO = new BookingOrderVO();
			bokOrdVO.setBookingID(bookingID);
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			bokOrdSvc.deleteBookingOrd(bookingID);
			
			String url = "/backend/bookingorder/model_AllBookingOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

/*************************************************** 會員"取消"訂位訂單(取消訂單) ******************************************************/
		if("cancle_BoookingOrder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer bookingID = Integer.valueOf(req.getParameter("bookingID").trim());
			
			BookingOrderVO bokOrdVO = new BookingOrderVO();
			bokOrdVO.setBookingID(bookingID);
			bokOrdVO.setBookingStatus(0);
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/index.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			BookingOrdService bokOrdSvc = new BookingOrdService();
			bokOrdVO = bokOrdSvc.cancelBooking(bookingID, 0);
			
			req.setAttribute("bokOrdVO", bokOrdVO);
			String url = "/frontend/booking/MemBookingOrd.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
	}
}
