package com.cart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		if ("cdeleteItem".equals(action)) {
			Integer pdID = new Integer(req.getParameter("pdID"));
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			CartService cartSvc = new CartService();
			cartSvc.deleteItem(sessionId, pdID);
			List<CartItemVO> cartItems = null;
			cartItems = cartSvc.getCart(sessionId);

			req.setAttribute("cartItems", cartItems);
			String url = "/frontend/cart/cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
	}	
}
