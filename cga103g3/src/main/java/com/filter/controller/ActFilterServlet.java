package com.filter.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "ActFilterServlet", 
servletNames = "RegisFetch",
urlPatterns = {"/frontend/actregis/*"})
public class ActFilterServlet implements Filter {
   
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("testt");
		// 獲得在下面程式碼中要用的request,response,session物件
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String actID = (String) req.getParameter("actID");
//		String showActForRegis = (String) req.getAttribute("action");
		// 獲得使用者請求的URI
//		System.out.println(servletRequest.getRequestURI());
		
		// 從session裡取會員編號資訊
		Integer memID = (Integer) session.getAttribute("memID");
		
		// 判斷如果沒有取到會員資訊,就跳轉到登入頁面  || "".equals(memID) || (memID.trim()).length() == 0
		if (memID == null) {
		//存現在網頁到location
		session.setAttribute("location", req.getContextPath()+ "/ActServlet?actID="+actID+"&action=showActForRegis");
//		session.setAttribute("actID", actID);
//		session.setAttribute("action", showActForRegis);
		// 跳轉到登入頁面
		res.sendRedirect("/cga103g3/frontend/member/memberLogin.jsp");
		return;
		} else {
		// 已經登入,繼續此次請求
		session.setAttribute("memID", memID);
		chain.doFilter(request, response);
		}		
	}
}
