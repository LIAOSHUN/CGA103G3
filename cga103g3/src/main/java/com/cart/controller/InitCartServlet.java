package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.Gson;


public class InitCartServlet extends HttpServlet {
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		
		String sessionId = (String) req.getSession().getAttribute("sessionId");//取得session的ID
		CartService cartSvc = new CartService();
		List<CartItemVO> cartItems = new ArrayList<CartItemVO>();
		cartItems = cartSvc.getCart(sessionId);
		
		res.setContentType("application/json; charset = UTF-8");
		PrintWriter out = res.getWriter();
		String cartItemsJson = gson.toJson(cartItems);
		out.write(cartItemsJson);
		out.flush();
		out.close();
		
		
		
		
	}
		
		

		
			
			
		
		
}


