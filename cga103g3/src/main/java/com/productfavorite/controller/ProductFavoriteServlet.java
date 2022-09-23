package com.productfavorite.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.*;
import com.productfavorite.model.ProductFavoriteService;
import com.productfavorite.model.ProductFavoriteVO;
import com.productimg.model.ProductImgVO;
import com.member.model.*;

@WebServlet("/frontend/productfavorite/ProductFavoriteServlet")
public class ProductFavoriteServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		MemberVO memVO = (MemberVO) (session.getAttribute("memVO"));
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			MEMID
			Integer memID = 11004;
//			memID = Integer.valueOf(req.getParameter("memID").trim());
//			PDID
			Integer pdID = null;
			pdID = Integer.valueOf(req.getParameter("pdID").trim());

			ProductFavoriteVO productFavoriteVO = new ProductFavoriteVO();
			productFavoriteVO.setMemID(memID);
			productFavoriteVO.setPdID(pdID);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productFavoriteVO", productFavoriteVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/productfavorite/update_product_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductFavoriteService productFavoriteService = new ProductFavoriteService();
			productFavoriteVO = productFavoriteService.addProductFavorite(memID, pdID);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//			String url = "/frontend/product/listAllFavorite.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer memID = Integer.valueOf(req.getParameter("memID"));

			Integer pdID = Integer.valueOf(req.getParameter("pdID"));

			/*************************** 2.開始刪除資料 ***************************************/
			ProductFavoriteService productFavoriteService = new ProductFavoriteService();
			productFavoriteService.deleteProductFavorite(memID, pdID);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/frontend/product/listAllFavorite.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
