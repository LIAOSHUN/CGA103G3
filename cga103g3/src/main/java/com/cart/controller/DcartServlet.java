package com.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.model.CartItemVO;
import com.cart.model.CartService;

@WebServlet("/DcartServlet")
public class DcartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String sessionId = null;
		
		
		Enumeration<String> en = req.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String values[] = req.getParameterValues(name);
			
			List<CartItemVO> checkedlist = new ArrayList<CartItemVO>();
			Integer pdID = 0;
			if (values != null ) {
				for (int i = 0; i < values.length; i++) {
					sessionId = (String) req.getSession().getAttribute("sessionId");
					CartService cartSvc = new CartService();
					pdID = Integer.valueOf(values[i]);
					CartItemVO itemchecked = cartSvc.getOneChecked(sessionId, pdID);
					
					checkedlist.add(itemchecked);
				}
				
				
				req.setAttribute("checkedlist", checkedlist);
				String url = "/frontend/cart/checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				
			}else {
				
			}
			
		}
		
		
	}	
}
