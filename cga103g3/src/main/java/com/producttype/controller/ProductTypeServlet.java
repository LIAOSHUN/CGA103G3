package com.producttype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.product.model.*;
import com.producttype.model.ProductTypeService;
import com.producttype.model.ProductTypeVO;
@WebServlet("/producttype/ProductTypeServlet")
public class ProductTypeServlet extends HttpServlet {

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
				String str = req.getParameter("pdTypeID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入遊戲種類編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/producttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer pdTypeID = null;
				try {
					pdTypeID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("遊戲種類編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/producttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductTypeService productTypeSvc = new ProductTypeService();
				ProductTypeVO productTypeVO = productTypeSvc.getOneProducttype(pdTypeID);
				if (pdTypeID == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/producttype/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productTypeVO", productTypeVO); // 資料庫取出的empVO物件,存入req
				String url = "/producttype/listOneProductType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer pdTypeID = Integer.valueOf(req.getParameter("pdTypeID"));
				
				/***************************2.開始查詢資料****************************************/
				ProductTypeService productTypeSvc = new ProductTypeService();
				ProductTypeVO productTypeVO = productTypeSvc.getOneProducttype(pdTypeID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productTypeVO", productTypeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/producttype/update_producttype_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				PDTYPEID
				Integer pdTypeID = Integer.valueOf(req.getParameter("pdTypeID").trim());
//				PDTYPENAME
				String pdTypeName = req.getParameter("pdTypeName").trim();
				if (pdTypeName == null || pdTypeName.trim().length() == 0) {
					errorMsgs.add("種類名稱請勿空白");
				}	

				ProductTypeVO productTypeVO = new ProductTypeVO();
				productTypeVO.setPdTypeID(pdTypeID);
				productTypeVO.setPdTypeName(pdTypeName);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productTypeVO", productTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/producttype/update_producttype_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductTypeService productTypeSvc = new ProductTypeService();
				productTypeVO = productTypeSvc.updateProductType(pdTypeID, pdTypeName);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productTypeVO", productTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/producttype/listOneProductType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//			PDTYPENAME
			String pdTypeName = null;
			try {
				pdTypeName = req.getParameter("pdTypeName").trim();
			} catch (Exception e1) {
				errorMsgs.add("種類名稱請勿空白");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	

			ProductTypeVO productTypeVO = new ProductTypeVO();
			productTypeVO.setPdTypeName(pdTypeName);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productTypeVO", productTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/producttype/update_producttype_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
				
				/***************************2.開始新增資料***************************************/
			ProductTypeService productTypeSvc = new ProductTypeService();
			productTypeVO = productTypeSvc.addProductType(pdTypeName);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/producttype/listAllProductType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer pdTypeID = Integer.valueOf(req.getParameter("pdTypeID"));
				
				/***************************2.開始刪除資料***************************************/
				ProductTypeService productTypeSvc = new ProductTypeService();
				productTypeSvc.deleteProductType(pdTypeID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/producttype/listAllProductType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
