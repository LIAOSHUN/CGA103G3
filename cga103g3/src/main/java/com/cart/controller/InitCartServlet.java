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


public class InitCartServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
	}
		
		
		//cookie的cookiekey:shoppingCart cookieValue:sessionid
		//Redis：(cookieValue(sessionid), {"pdID": "xxx","count": "x"})
		
		public void service(HttpServletRequest req,  HttpServletResponse res)  throws ServletException, IOException{
					
			HttpSession session = req.getSession();
			Cookie[] cookies = req.getCookies();
			// 檢視user是否已經有存放cookie
			for (int i = 0; i < cookies.length; i++) {
				Cookie userCookie = cookies[i];
				
				if ("shoppingCart".equals(userCookie.getName())) {
					// 讓每的頁面可以透過sessionId呼叫CartService的方法
					
					System.out.println(userCookie.getValue());
					System.out.println("舊車");
					
					
					//可以讓getcart找車
					session.setAttribute("sessionId", userCookie.getValue());
					
					return;
				}
			}
		}	
		
			public void init(HttpServletRequest req,  HttpServletResponse res) throws ServletException {
				
				HttpSession session = req.getSession();
				// 若未找到shoppingCart，新增cookie，並將session作為key存入Redis
				//cookie的key:shoppingCart value:sessionid
				Cookie shoppingCart = new Cookie("shoppingCart", session.getId());
				shoppingCart.setMaxAge(3 * 24 * 60 * 60); // 存活3天，以秒為單位
				shoppingCart.setHttpOnly(true); 
				
				session.setAttribute("sessionId", session.getId());
				res.addCookie(shoppingCart);
				System.out.println("初始化新車");
			}

		
			
			
		
		
}


