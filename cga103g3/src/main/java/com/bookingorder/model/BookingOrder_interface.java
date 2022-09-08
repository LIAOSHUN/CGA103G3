package com.bookingorder.model;

import java.util.List;

public interface BookingOrder_interface {
	//會員新增訂位訂單
	public void insert(BookingOrderVO bookingOrderVO);
	//會員可以取消訂位訂單
	public void cancel(BookingOrderVO bookingOrderVO);
	//管理員可以修改訂位訂單
	public void update(BookingOrderVO bookingOrderVO);
	//管理員可以查詢單筆訂位訂單
	public BookingOrderVO getOneBookingID(Integer bookingID);
	//管理員可以刪除訂單
	public void delete(Integer bookingID);

	//管理員可以查詢所有訂位訂單
	public List<BookingOrderVO> getAll();
	//管理員可以查詢成立的預約訂單
	public List<BookingOrderVO> getBookingStatus(Integer bookingStatus);
	
	public List<BookingOrderVO> getBookingStatusInfo(Integer bookingStatus);
	
	//會員可以查詢訂位訂單
	public List<BookingOrderVO> getBookingOrd(Integer memID);

}
