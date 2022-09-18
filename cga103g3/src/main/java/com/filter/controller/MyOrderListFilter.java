package com.filter.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebFilter(	filterName = "MyOrderListFilter",
//servletNames = {"DcartServlet"}
urlPatterns = {"/frontend/orderlist/myOrderList.jsp", "/frontend/memcoupon/myCoupon.jsp"}
		)
public class MyOrderListFilter extends HttpFilter {

public MyOrderListFilter() {
	super();
}
	
	
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		
		//讀取的網頁路徑
//		String uri = req.getRequestURI();
//		System.out.println(uri);
		//判斷是否有登入,用session有無存入LoginSessionName判斷
//		String getSessionID =	((HttpServletRequest) request).getRequestedSessionId();
		String memAccount = (String) req.getSession().getAttribute("memAccount");
		
		HttpSession session = req.getSession();
//		測試登入狀態
//		System.out.println("SessionID : " + getSessionID);
//		System.out.println("登入狀態Session : " + LoginSessionName);
		
		//以下判斷,當結尾不是"Login.jsp" 或是 "EmpLoginServlet.do" 時 ,而且沒有取得Session登入狀態
		if( memAccount == null || (memAccount.trim()).length() == 0){
//			req.getRequestDispatcher("../../BackLogin.jsp").forward(request, response);
			//存現在網頁到location
			session.setAttribute("location", req.getRequestURI());
			//跳轉頁面至登入頁面
			res.sendRedirect("../../member/memberLogin.jsp");
			return;
		}else{
			//回傳正常頁面
			chain.doFilter(req, res); 
			return;
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}
