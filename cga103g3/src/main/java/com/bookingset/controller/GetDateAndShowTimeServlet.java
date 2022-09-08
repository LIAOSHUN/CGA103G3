package com.bookingset.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookingset.model.BookingSetService;
import com.bookingset.model.BookingSetVO;

import static com.core.utils.JSONTrans.*;
import static com.core.utils.Constants.*;

@WebServlet("/Booking/GetDateAndShowTime.do")
public class GetDateAndShowTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("fetch come (ShowBookingStartTimeServlet)!!");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		
		BookingSetVO bookingSetVO = json2Pojo(req, BookingSetVO.class);
		Integer boxID = bookingSetVO.getBoxID();
		java.sql.Date bookingDate = bookingSetVO.getBookingDate();
		BookingSetService bookingSerSvc = new BookingSetService();
		List<BookingSetVO> boxAndBooking = new ArrayList<BookingSetVO>();
		boxAndBooking = bookingSerSvc.showBookingStartTime(boxID, bookingDate);
		
		res.getWriter().append(GSON.toJson(boxAndBooking));
		
	}

}
