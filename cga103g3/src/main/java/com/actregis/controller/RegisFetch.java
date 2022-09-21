package com.actregis.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actregis.model.ActRegisService;
import com.actregis.model.ActRegisVO;

import static com.core.utils.JSONTransTime.*;

@WebServlet("/RegisFetch")
public class RegisFetch extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("fetch come!!");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		
		HttpSession session = req.getSession();
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
		Integer memID = (Integer) (session.getAttribute("memID"));
		Integer actID = Integer.valueOf(req.getParameter("actID").trim());
		Integer actNum = Integer.valueOf(req.getParameter("actNum").trim());
		
		Integer actFee = null;
		try {
			actFee = Integer.valueOf(req.getParameter("actFee").trim());
		} catch (NumberFormatException e) {
			errorMsgs.put("actFee","請填數字");
		}
		Integer feeStatus = Integer.valueOf(req.getParameter("feeStatus").trim());
		if (feeStatus == null) {
			feeStatus = 0;
		}
		Integer regisStatus = Integer.valueOf(req.getParameter("regisStatus").trim());
		if (regisStatus == null) {
			regisStatus = 1;
		}
		
		// 更改activity表格的報名人數
		Integer actRegistration = Integer.valueOf(req.getParameter("actNum").trim());

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/actregis/addActRegis.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			ActRegisService actRegisSvc = new ActRegisService();
			actRegisSvc.addActRegis(memID, actID, actNum, actFee, feeStatus, regisStatus);
			ActService actSvc = new ActService();
			ActVO actVO = actSvc.updateActRegistration(actRegistration);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			req.setAttribute("actVO", actVO);
			String url = "/frontend/act/listAllActF.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllActRegis.jsp
			successView.forward(req, res);
		
	}

}
