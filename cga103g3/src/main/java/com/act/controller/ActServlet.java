package com.act.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.model.ActService;
import com.act.model.ActVO;

public class ActServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("actID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer actID = null;
				try {
					actID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actID);
				if (actVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫取出的actVO物件,存入req
				String url = "/backend/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAct.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllAct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
			Integer actID = Integer.valueOf(req.getParameter("actID"));
				
				/***************************2.開始查詢資料****************************************/
				ActService actSvc = new ActService();
				ActVO actVO = actSvc.getOneAct(actID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actVO", actVO);         // 資料庫取出的actVO物件,存入req
				String url = "/backend/act/update_act_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_act_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_act_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actID = Integer.valueOf(req.getParameter("actID").trim());
				Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
				
				String actTitle = req.getParameter("actTitle").trim();
				if (actTitle == null || actTitle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String actDescription = req.getParameter("actDescription").trim();
				if (actDescription == null || actDescription.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				Timestamp actTimeStart = null;
				try {
					actTimeStart = Timestamp.valueOf(req.getParameter("actTimeStart").trim()+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}
				Timestamp actTimeEnd = null;
				try {
					actTimeEnd = Timestamp.valueOf(req.getParameter("actTimeEnd").trim()+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}
				Timestamp actDate = null;
				try {
					actDate = Timestamp.valueOf(req.getParameter("actDate").trim()+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}
				
				Integer regisMax = Integer.valueOf(req.getParameter("regisMax").trim());
				Integer actFee = null;
				try {
					actFee = Integer.valueOf(req.getParameter("actFee").trim());
					if(actFee <= 0){
						errorMsgs.add("金額不得小於0");
					}
				} catch (Exception e) {
					errorMsgs.add("請輸入金額");
				}
				
				Integer actRegistration = Integer.valueOf(req.getParameter("actRegistration").trim());
				Integer actStatus = Integer.valueOf(req.getParameter("actStatus").trim());
				
				
				
				ActVO actVO = new ActVO();
				actVO.setActID(actID);
				actVO.setStoreID(storeID);
				actVO.setActTitle(actTitle);
				actVO.setActDescription(actDescription);
				actVO.setActTimeStart(actTimeStart);
				actVO.setActTimeEnd(actTimeEnd);
				actVO.setActDate(actDate);
				actVO.setRegisMax(regisMax);
				actVO.setActFee(actFee);
				actVO.setActRegistration(actRegistration);
				actVO.setActStatus(actStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的actVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/update_act_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ActService actSvc = new ActService();
				actVO = actSvc.updateAct(actID, storeID, actTitle, actDescription, actTimeStart, actTimeEnd, 
										actDate, regisMax, actFee, actRegistration, actStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的actVO物件,存入req
				String url = "/backend/act/listOneAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAct.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addAct.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
				
				String actTitle = req.getParameter("actTitle").trim();
				if (actTitle == null || actTitle.trim().length() == 0) {
					errorMsgs.add("標題請勿空白");
				}
				String actDescription = req.getParameter("actDescription").trim();
				if (actDescription == null || actDescription.trim().length() == 0) {
					errorMsgs.add("內文請勿空白");
				}
				
				Timestamp actTimeStart = null;
				try {
					actTimeStart = Timestamp.valueOf(req.getParameter("actTimeStart").trim()+":00");
				} catch (IllegalArgumentException e) {
					actTimeStart=new Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Timestamp actTimeEnd = null;
				try {
					actTimeEnd = Timestamp.valueOf(req.getParameter("actTimeEnd").trim()+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}
				Timestamp actDate = null;
				try {
					actDate = Timestamp.valueOf(req.getParameter("actDate").trim()+":00");
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入日期!");
				}

				Integer regisMax = Integer.valueOf(req.getParameter("regisMax").trim());
				Integer actFee = null;
				try {
					actFee = Integer.valueOf(req.getParameter("actFee").trim());
					if(actFee <= 0){
						errorMsgs.add("金額不得小於0");
					}
				} catch (Exception e) {
					actFee = 100;
					errorMsgs.add("請輸入金額");
				}
				
				Integer actRegistration = Integer.valueOf(req.getParameter("actRegistration").trim());
				Integer actStatus = Integer.valueOf(req.getParameter("actStatus").trim());
				
				
				ActVO actVO = new ActVO();
				actVO.setStoreID(storeID);
				actVO.setActTitle(actTitle);
				actVO.setActDescription(actDescription);
				actVO.setActTimeStart(actTimeStart);
				actVO.setActTimeEnd(actTimeEnd);
				actVO.setActDate(actDate);
				actVO.setRegisMax(regisMax);
				actVO.setActFee(actFee);
				actVO.setActRegistration(actRegistration);
				actVO.setActStatus(actStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的actVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ActService actSvc = new ActService();
				actVO = actSvc.addAct(storeID, actTitle, actDescription, actTimeStart, actTimeEnd, 
						actDate, regisMax, actFee, actRegistration, actStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllAct.jsp
				successView.forward(req, res);				
		}
	}
}
