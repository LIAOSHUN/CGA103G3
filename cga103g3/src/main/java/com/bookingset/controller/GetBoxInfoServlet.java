package com.bookingset.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookingset.model.BookingSetService;
import com.bookingset.model.BookingSetVO;
import com.google.gson.Gson;

@WebServlet("/BoxInfo/BoxInfo.do")
public class GetBoxInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		Gson gson2 = new Gson();
		
		BookingSetService bookingSetSvc = new BookingSetService();
		List<BookingSetVO> allBoxAndStore = bookingSetSvc.getBoxAndStore();
		res.getWriter().append(gson2.toJson(allBoxAndStore));
	}


}
