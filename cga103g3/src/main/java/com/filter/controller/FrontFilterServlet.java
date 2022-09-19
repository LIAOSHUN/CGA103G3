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
@WebFilter(	filterName = "FrontFilterServlet",
servletNames = {"DcartServlet"}
//urlPatterns = {"/frontend/cart/checkout.jsp"}
		)
public class FrontFilterServlet extends HttpFilter {


	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		//讀取的網頁路徑
//		String uri = req.getRequestURI();
//		System.out.println(uri);
		String memAccount = (String) req.getSession().getAttribute("memAccount");
		
		HttpSession session = req.getSession();
		
		//以下判斷有無登入
		if( memAccount == null || (memAccount.trim()).length() == 0){
			//存現在網頁到location
//			session.setAttribute("location", req.getContextPath() + "/frontend/cart/testpro.jsp");
			session.setAttribute("location", req.getContextPath() + "/frontend/cart/getCart.do");
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
