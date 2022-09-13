package com.act.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actimg.model.ActImgVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActWithImgServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer storeID = Integer.valueOf(req.getParameter("storeID").trim());
			
			String actTitle = req.getParameter("actTitle").trim();
			if (actTitle == null || actTitle.trim().length() == 0) {
				errorMsgs.put("actTitle", "標題請勿空白");
			}
			String actDescription = req.getParameter("actDescription").trim();
			if (actDescription == null || actDescription.trim().length() == 0) {
				errorMsgs.put("actDescription", "內文請勿空白");
			}
			
			LocalDateTime actTimeStart = null;
			try {
				actTimeStart = LocalDateTime.parse(req.getParameter("actTimeStart").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			} catch (DateTimeParseException e) {
				errorMsgs.put("actTimeStart", "請輸入日期!");
			}
			LocalDateTime actTimeEnd = null;
			try {
				actTimeEnd = LocalDateTime.parse(req.getParameter("actTimeEnd").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			} catch (DateTimeParseException e) {
				errorMsgs.put("actTimeEnd", "請輸入日期!");
			}
			LocalDateTime actDate = null;
			try {
				actDate = LocalDateTime.parse(req.getParameter("actDate").trim()+":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
			} catch (DateTimeParseException e) {
				errorMsgs.put("actDate", "請輸入日期!");
			}

			Integer regisMax = Integer.valueOf(req.getParameter("regisMax").trim());
			Integer actFee = null;
			try {
				actFee = Integer.valueOf(req.getParameter("actFee").trim());
				if(actFee <= 0){
					errorMsgs.put("actFee", "金額不得小於0");
				}
			} catch (Exception e) {
				actFee = 100;
				errorMsgs.put("actFee", "請輸入金額");
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

			// 單張上傳
//			byte[] article_picture = null;
//
//			InputStream in = req.getPart("article_picture").getInputStream();
//			article_picture = new byte[in.available()];
//
//			in.read(article_picture);
//			in.close();
//
//			ArticlePictureVO articlePictureVO = new ArticlePictureVO();
//			articlePictureVO.setArticle_picture(article_picture);

//			將網誌及圖片放進資料庫
//			ArticleService articleSvc = new ArticleService();
//			String getCheck = articleSvc.insertWithPic(articleVO, articlePictureVO);
//			=========================================================================
			// 測試多張圖片
			Collection<Part> parts = req.getParts();
			List<ActImgVO> list = new ArrayList<ActImgVO>();

			byte[] actImgFile = null;
			for (Part part : parts) {
				if (part.getContentType() != null && part.getContentType().contains("image/")) {
					InputStream in = part.getInputStream();
					actImgFile = new byte[in.available()];
					in.read(actImgFile);
					in.close();
					ActImgVO actImgVO = new ActImgVO();
					actImgVO.setActImgFile(actImgFile);
					list.add(actImgVO);
				}
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("backend/act/addAct.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增訂單***************************************/
			ActService actSvc = new ActService();
			actSvc.insertWithActImgs(actVO, list);
			/***************************3.新增完成,準備轉交(Send the Success view)***********/

				// 新增成功後跳轉列表
				String url = "/backend/act/listAllAct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
	}
}
