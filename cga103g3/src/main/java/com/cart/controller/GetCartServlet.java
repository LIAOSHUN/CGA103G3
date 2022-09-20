package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartItemVO;
import com.cart.model.CartService;

@WebServlet("/GetCartServlet")
public class GetCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		String action = req.getParameter("action");
		
//		if ("init".equals(action)) {
//
//			Cookie[] cookies = req.getCookies();
//			
//			for (int i = 0; i < cookies.length; i++) {
//				Cookie userCookie = cookies[i];
//
//				if ("shoppingCart".equals(userCookie.getName())) {
//					
//					System.out.println(userCookie.getValue());
//					System.out.println("舊車");
//					
//					session.setAttribute("sessionId", userCookie.getValue());
//					
//				Cookie shoppingCart = new Cookie("shoppingCart", session.getId());
//				shoppingCart.setMaxAge(3 * 24 * 60 * 60); // 存活3天，以秒為單位
//				shoppingCart.setHttpOnly(true); 
//
//				session.setAttribute("sessionId", session.getId());
//				res.addCookie(shoppingCart);
//				System.out.println("初始化新車");
//			}
//		}
		
		
		
		
		String sessionId = (String) req.getSession().getAttribute("sessionId");//取得session的ID
		CartService cartSvc = new CartService();
		List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
		cartItems = cartSvc.getCart(sessionId);
		
		req.setAttribute("cartItems", cartItems);
		String url = "/frontend/cart/cart.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
		
		
		
	}

}
