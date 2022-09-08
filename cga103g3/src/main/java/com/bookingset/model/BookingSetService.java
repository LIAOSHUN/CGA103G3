package com.bookingset.model;

import java.util.ArrayList;
import java.util.List;

import javax.mail.search.IntegerComparisonTerm;

public class BookingSetService {

	private BookingSet_interface dao;

	public BookingSetService() {
		dao = new BookingSetDAO();
	}
	
	//查詢所有包廂資訊 + 門市店休日
	public List<BookingSetVO> getBoxAndStore(){
		return dao.getAllBoxAndStore();
	}
	
	// 查詢所有包廂 + 訂位訂單
	public List<BookingSetVO> getBoxAndBooking(Integer boxID){
		return dao.getBoxAndBooking(boxID);
	};
	
	// 查詢訂位日期 + 包廂編號
	public List<BookingSetVO> showBookingStartTime(Integer boxID, java.sql.Date bookingDate){
		return dao.getBoxBktime(boxID, bookingDate);
	}

	public List<BookingSetVO> getAllBox() {
		return dao.getAllBox();
	}

	public List<BookingSetVO> getAllBooking() {
		return dao.getAllBooking();
	}

	public List<BookingSetVO> getStroeBookingOrd(Integer StoreID) {
		return dao.getStoreBookingOrder(StoreID);
	}

	public List<BookingSetVO> getBookingTimeInfo() {
		return dao.getBookingTime();
	}

	
	public static void main(String[] args) {
		BookingSetService boksvc = new BookingSetService();
		List<BookingSetVO> list = boksvc.getBookingTimeInfo();

		for (BookingSetVO bookingSetVO : list) {
			System.out.println("BoxID= " + bookingSetVO.getBoxID());
			System.out.println("BoxBkStart= " + bookingSetVO.getBoxBkStart());
			System.out.println("BoxBkEnd= " + bookingSetVO.getBoxBkEnd());
			System.out.println("BookingDate= " + bookingSetVO.getBookingDate());
			System.out.println("BookingStart= " + bookingSetVO.getBookingStart());
			System.out.println("BookingEnd= " + bookingSetVO.getBookingEnd());
			System.out.println("====================================");
		}
	}

}
