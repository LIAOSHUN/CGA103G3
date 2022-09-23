package com.actfav.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actfav.model.ActFavService;
import com.member.model.MemberVO;

public class DelActFavServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
//		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		Integer memID = (Integer) session.getAttribute("memID");
		Integer actID = Integer.valueOf(req.getParameter("actID"));
		
		ActFavService actFavSvc = new ActFavService();
		actFavSvc.deleteActFav(memID, actID);
		res.sendRedirect("/cga103g3/frontend/actfav/listFav.jsp");
	}
}
