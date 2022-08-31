package com.cart.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DcartServlet")
public class DcartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		Enumeration en = req.getParameterNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			String values[] = req.getParameterValues(name);
			if (values != null ) {
				for (int i = 0; i < values.length; i++) {
					req.setAttribute(name, values[i]);
					
				}
			}
		}
		String url = "/frontend/cart/checkout2.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
		
	}	
}
