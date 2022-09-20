package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;
@WebServlet("/product/ShowProductType")

public class ShowProductType extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_Type".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			

			/*************************** 1.接收請求參數 ****************************************/
			Integer pdTypeID = Integer.valueOf(req.getParameter("pdTypeID"));
			List<ProductVO> listtype = new ArrayList<ProductVO>();
			

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService productSvc = new ProductService();
			listtype = productSvc.getType(pdTypeID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("listtype", listtype); 
			String url = "/frontend/product/listAllType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}
	
}
