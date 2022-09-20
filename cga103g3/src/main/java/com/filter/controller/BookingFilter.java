package com.filter.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "BookingFilter", 
servletNames = "BookingFilter",
urlPatterns = {"/frontend/booking/bookingFinal.jsp", "/frontend/booking/MemBookingOrd.jsp"})
public class BookingFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		// 獲得在下面程式碼中要用的request,response,session物件
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		
		// 獲得使用者請求的URI
//		System.out.println(servletRequest.getRequestURI());
		
		// 從session裡取會員編號資訊
//		String memID = (String) session.getAttribute("MemID");
		Integer memID = (Integer) session.getAttribute("MemID");
		
		// 判斷如果沒有取到會員資訊,就跳轉到登陸頁面  || "".equals(memID) || (memID.trim()).length() == 0
		if (memID == null) {
		//存現在網頁到location
		session.setAttribute("location", servletRequest.getRequestURI());
		// 跳轉到登陸頁面
		servletResponse.sendRedirect("./BookingLogin.jsp");
		} else {
		// 已經登陸,繼續此次請求
		session.setAttribute("MemID", memID);
		chain.doFilter(request, response);
		}
		
	}
}
