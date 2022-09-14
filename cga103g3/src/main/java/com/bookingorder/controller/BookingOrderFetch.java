package com.bookingorder.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookingorder.model.BookingOrdService;
import com.bookingorder.model.BookingOrderVO;


import static com.core.utils.JSONTrans.*;

@WebServlet("/bookingorderfetch/bookingorder.do")
public class BookingOrderFetch extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}




	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("fetch come!!");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		
		BookingOrderVO bokOrdVO = json2Pojo(req, BookingOrderVO.class);
		
		
		BookingOrdService bokOrdSvc = new BookingOrdService();
		bokOrdVO = bokOrdSvc.addBooking(bokOrdVO);

		
	}

}
