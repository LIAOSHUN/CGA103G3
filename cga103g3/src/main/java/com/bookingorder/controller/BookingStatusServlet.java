package com.bookingorder.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.bookingorder.model.*;

@WebServlet("/BookingStatus/BookingStatus.do")
public class BookingStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BookingOrdService bokOrdSvc = new BookingOrdService();
		List<BookingOrderVO> bookingStatuslist = bokOrdSvc.getBookingStautsInfo(1);
		res.getWriter().append(gson.toJson(bookingStatuslist));
		
		
		
	}

}
