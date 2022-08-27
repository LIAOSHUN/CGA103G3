package com.cart.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.model.CartService;
import com.cart.model.CartItemVO;
import com.google.gson.Gson;
import com.product.model.ProductVO;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		
//		頁面先設一個假button作為進入網站得住發事件，須改為filter註冊到網站每個頁面
		
		if ("init".equals(action)) {

			Cookie[] cookies = req.getCookies();

			
			
			//cookie的cookiekey:shoppingCart cookieValue:sessionid
			//Redis：(cookieValue(sessionid), {"itemId": "xxx","count": "x"})
			
			// 檢視user是否已經有存放cookie
			for (int i = 0; i < cookies.length; i++) {
				Cookie userCookie = cookies[i];

				if ("shoppingCart".equals(userCookie.getName())) {
					// 讓每的頁面可以透過sessionId呼叫CartService的方法
					session.setAttribute("sessionId", userCookie.getValue());
					return;
				}

				// 若未找到shoppingCart，新增cookie，並將session作為key存入Redis
				//cookie的key:shoppingCart value:sessionid
				Cookie shoppingCart = new Cookie("shoppingCart", session.getId());
				shoppingCart.setMaxAge(3 * 24 * 60 * 60); // 存活3天，以秒為單位
//				shoppingCart.setHttpOnly(true); 

				session.setAttribute("sessionId", session.getId());
				res.addCookie(shoppingCart);

				
				//?????
				PrintWriter out = res.getWriter();
				out.write(session.getId());
				out.flush();
				out.close();
			}
		}
		
		
//		==================================================

		// 點擊購物車時
		if ("getCart".equals(action)) {
			Gson gson = new Gson();
			String sessionId = (String) req.getSession().getAttribute("sessionId");//取得session的ID
			CartService cartSvc = new CartService();
			List<CartItemVO> cartItems = null;
			cartItems = cartSvc.getCart(sessionId);

			
			
			req.setAttribute("cartItems", cartItems);
			String url = "frontend/cart/cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			//跳轉JSP
//			PrintWriter out = res.getWriter();
//			String cartItemsJson = gson.toJson(cartItems);
//			out.write(cartItemsJson);
//			out.flush();
//			out.close();
		}

		// 點擊加入購物車時
		if ("addItem".equals(action)) {
			Integer itemId = new Integer(req.getParameter("itemId"));
			Integer count = new Integer(req.getParameter("count"));
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			CartService cartSvc = new CartService();
			cartSvc.addItem(sessionId, itemId, count);
		}

		// 點擊刪除商品時
		if ("deleteItem".equals(action)) {
			Integer itemId = new Integer(req.getParameter("itemId"));
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			CartService cartSvc = new CartService();
			cartSvc.deleteItem(sessionId, itemId);
		}

		// 成功結帳欲清空購物車時
		if ("deleteCart".equals(action)) {
			String sessionId = (String) req.getSession().getAttribute("sessionId");
			CartService cartSvc = new CartService();
			cartSvc.deleteCart(sessionId);

//////				因為結完帳後會刪除redis的key，因此cookie也要殺掉，這樣他重訪其他頁面才會再拿到新的cookie值，否則原本cookie內的值在redis找不到資料

			Cookie[] cookies = req.getCookies();

			// 檢視user是否已經有存放cookie，cookie 的值以List型態存放於Redis：(cookieValue, {"itemId": "xxx",
			// "count": "x"})
			for (int i = 0; i < cookies.length; i++) {
				Cookie userCookie = cookies[i];
				if ("shoppingCart".equals(userCookie.getName())) {
					userCookie.setMaxAge(0);
					return;
				}

			}
		}

//		List<ProductVO> cartlist; // 購物車列表
//
//		if ("addCart".equals(action)) { // 來自某樣商品詳情頁面.jsp的請求
//
//			/*************************** 1.接收請求參數 ****************************************/
////			Integer coupTypeNo = Integer.valueOf(req.getParameter("coupTypeNo"));
//			Integer memID = 11001;
//			String pdName = "印加寶藏";
//			Integer pdPrice = 840;
//			Integer ccount = 2;
//
//			String pdName2 = "矮人礦坑";
//			Integer pdPrice2 = 490;
//			Integer ccount2 = 1;
//
//			/*************************** 2.開始查詢資料 ****************************************/
//			CartService cartSvc = new CartService();
//			cartlist = cartSvc.addCart(11001, "印加寶藏", 2);// 會員11001存第一個商品到購物車
//			cartlist = cartSvc.addCart(11001, "矮人礦坑", 1);// 會員11001存第二個商品到購物車
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
//
//			req.setAttribute("cartlist", cartlist);// 從商品詳情頁面取出的xxxxxxxx,存入req
//			String url = "/frontend/cart/cart.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 cart.jsp
//			successView.forward(req, res);
//		}
	}
}
