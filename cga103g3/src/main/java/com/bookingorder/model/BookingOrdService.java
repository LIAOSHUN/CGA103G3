package com.bookingorder.model;

import java.util.List;

public class BookingOrdService {
	
	private BookingOrder_interface dao;
	
	public BookingOrdService() {
		dao = new BookingOrdDAO();
	}
	
	//管理員新增訂位訂單
	public BookingOrderVO addBooking(Integer memID, java.sql.Date bookingDate, String bookingStart, String bookingEnd, Integer boxID, String bookingNote) {
		BookingOrderVO bov = new BookingOrderVO();
		
		bov.setMemID(memID);
		bov.setBookingDate(bookingDate);
		bov.setBookingStart(bookingStart);
		bov.setBookingEnd(bookingEnd);
		bov.setBoxID(boxID);
		bov.setBookingNote(bookingNote);
		dao.insert(bov);
		
		return bov;
	}
	
	//管理員修改訂位訂單
	public BookingOrderVO updateBookingOrder(java.sql.Date bookingDate, String bookingStart, String bookingEnd, Integer boxID, String bookingNote, Integer bookingStatus, Integer bookingID) {
		BookingOrderVO bov = new BookingOrderVO();
		bov.setBookingDate(bookingDate);
		bov.setBookingStart(bookingStart);
		bov.setBookingEnd(bookingEnd);
		bov.setBoxID(boxID);
		bov.setBookingNote(bookingNote);
		bov.setBookingStatus(bookingStatus);
		bov.setBookingID(bookingID);
		dao.update(bov);
		
		return bov;
	}
	
	//for fetch api
	public BookingOrderVO addBooking(BookingOrderVO bookingOrderVO) {
		dao.insert(bookingOrderVO);
		
		return bookingOrderVO;
	}
	
	public BookingOrderVO cancelBooking(Integer bookingID, Integer bookingStatus) {
		BookingOrderVO bov = new BookingOrderVO();
		
		bov.setBookingStatus(bookingStatus);
		bov.setBookingID(bookingID);
		dao.cancel(bov);
		return bov;
	}
	
	public void updateBooking(Integer bookingID, java.sql.Date bookingDate, String bookingStart, String bookingEnd, Integer boxID/*, String bookingNote*/, Integer bookingStatus) {
		BookingOrderVO bov = new BookingOrderVO();
		
		bov.setBookingID(bookingID);
		bov.setBookingDate(bookingDate);
		bov.setBookingStart(bookingStart);
		bov.setBookingEnd(bookingEnd);
		bov.setBoxID(boxID);
//		bov.setBookingNote(bookingNote);
		bov.setBookingStatus(bookingStatus);
		dao.insert(bov);
	}
	
	public void deleteBookingOrd(Integer bookingID) {
		dao.delete(bookingID);
	}
	
	public BookingOrderVO getOneBookingID(Integer bookingID) {
		return dao.getOneBookingID(bookingID);
	}
	
	public List<BookingOrderVO> getAll(){
		return dao.getAll();
	}
	
	public List<BookingOrderVO> getBookingStauts(Integer bookingStatus){
		return dao.getBookingStatus(bookingStatus);
	}
	
	public List<BookingOrderVO> getBookingStautsInfo(Integer bookingStatus){
		return dao.getBookingStatusInfo(bookingStatus);
	}
	
	public List<BookingOrderVO> getBookingOrd(Integer memID) {
		return dao.getBookingOrd(memID);
	}
	
}
