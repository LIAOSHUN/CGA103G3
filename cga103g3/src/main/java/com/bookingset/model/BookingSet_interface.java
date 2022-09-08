package com.bookingset.model;

import java.util.List;

public interface BookingSet_interface {
	
	//查詢所有包廂資訊 + 門市店休日
	public List<BookingSetVO> getAllBoxAndStore();
	
	// 查詢所有包廂 + 訂位訂單
	public List<BookingSetVO> getBoxAndBooking(Integer boxID);
	
	// 查詢訂位日期 + 包廂編號
	public List<BookingSetVO> getBoxBktime(Integer boxID, java.sql.Date bookingDate);
	
	//查詢所有包廂資訊
	public List<BookingSetVO> getAllBox();
	
	//查詢所有包廂狀態
	public List<BookingSetVO> getAllBooking();
	
	//查詢門市訂位訂單包廂
	public List<BookingSetVO> getStoreBookingOrder(Integer storeID);
	
	public List<BookingSetVO> getBookingTime();

}
