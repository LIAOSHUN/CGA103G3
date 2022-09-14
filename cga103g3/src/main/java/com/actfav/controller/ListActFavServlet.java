package com.actfav.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.actfav.model.ActFavService;
import com.google.gson.Gson;
import com.member.model.MemberVO;


public class ListActFavServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html ; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		/*----在session取會員資訊----*/
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		Integer memID = memberVO.getMemID();
		
		/*-----查詢-----*/
		ActFavService actFavSvc = new ActFavService();
		List<Object> list = actFavSvc.getByActJoinList(memID);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
	}
}
