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
import com.google.gson.GsonBuilder;

@WebServlet("/BookingSet/BookingSet.do")
public class BookingSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BookingSetService boksvc = new BookingSetService();
		List<BookingSetVO> allBooking = boksvc.getBookingTimeInfo();
		res.getWriter().append(gson.toJson(allBooking));

//		doPost(req, res);

	}

//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		System.out.println("fetch come!!");
//		req.setCharacterEncoding("UTF-8");
//		res.setContentType("application/json; charset=UTF-8");
//
//		BookingSetVO fetch = json2Pojo(req, BookingSetVO.class);
//		java.sql.Date checkDate = fetch.getBookingDate();
//		Integer checkBoxID = fetch.getBoxID();
//
//		BookingSetService boksvc = new BookingSetService();
//
//		List<BookingSetVO> allBooking = new ArrayList<BookingSetVO>();
//		
//		//取得已訂位訂單
//		allBooking = boksvc.getAllBooking();
//		java.sql.Date bookingDate = ((BookingSetVO) allBooking).getBookingDate();
//		Integer bookingStart = Integer.valueOf(((BookingSetVO) allBooking).getBookingStart());
//		Integer bookingEnd = Integer.valueOf(((BookingSetVO) allBooking).getBookingEnd());
//		Integer boxID = ((BookingSetVO) allBooking).getBoxID();
//		
//		//取得包廂資訊
//		List<BookingSetVO> allBox = new ArrayList<BookingSetVO>();
//		allBox = boksvc.getAllBox();
//		Integer boxNO = ((BookingSetVO) allBox).getBoxNO();
//		Integer boxBkStart = Integer.valueOf(((BookingSetVO) allBox).getBoxBkStart());
//		Integer boxBkEnd = Integer.valueOf(((BookingSetVO) allBox).getBoxBkEnd());
//
//		Integer reStartTime, reEndTime;
//		for (BookingSetVO bookingSetVO : allBox) {
//			if(checkDate == bookingSetVO.getBookingDate() && checkBoxID == bookingSetVO.getBoxID()) {
//				reStartTime = Integer.valueOf(((BookingSetVO) allBooking).getBookingStart());
//				reEndTime = Integer.valueOf(((BookingSetVO) allBooking).getBookingEnd());
//			}
//		}
//
//		
//
//
//	}

}
