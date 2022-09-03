package com.cart.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartService;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String sessionId = null;
	
		
		
			String values[] = req.getParameterValues("pdID");
			
			Integer pdID = 0;
			if(values != null) {
				
				for (int i = 0; i < values.length; i++) {
					sessionId = (String) req.getSession().getAttribute("sessionId");
					CartService cartSvc = new CartService();
					pdID = Integer.valueOf(values[i]);
					cartSvc.updatePdAmount(sessionId, pdID);
					cartSvc.deleteItemChecked(sessionId, pdID);
					
				}
			}
			
			String receiverName = req.getParameter("receiverName");
			String receiverPhone = req.getParameter("receiverPhone");
			String address = req.getParameter("address");
			
			System.out.println(receiverName);
			System.out.println(receiverPhone);
			System.out.println(address);
			
	}

}
