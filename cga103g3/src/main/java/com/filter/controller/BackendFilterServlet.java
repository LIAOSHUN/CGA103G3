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
//@WebFilter(	filterName = "BackendFilterServlet",
//urlPatterns = {"/backend/*" ,"/frontend/member/listAllMember.jsp"}
//		)
public class BackendFilterServlet extends HttpFilter {


	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		Integer empID = (Integer) req.getSession().getAttribute("empID");
		
		HttpSession session = req.getSession();
		
		//以下判斷有無登入
		if( empID == null ){
			//存現在網頁到location
			
//			session.setAttribute("location", req.getContextPath() + "/backend/index.jsp");
			//跳轉頁面至登入頁面
			System.out.println("111");
			res.sendRedirect( req.getContextPath() + "/backend/employee/employeeLogin.jsp");
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
