package com.memcoupon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MemCouponServlet")
public class MyCouponServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
	
	if("getMyCoup".equals(action)) {
		
		
		
		
		req.setAttribute("couponTypeVO", couponTypeVO); // 資料庫取出的couponTypeVO物件,存入req
		String url = "/backend/coupontype/listOneCouponType.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCouponType.jsp
		successView.forward(req, res);
	}
	
	
	
	
	
	
	
	
	
	
	}
}
