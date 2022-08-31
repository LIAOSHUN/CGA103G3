package com.orderlist.model;

import java.sql.Timestamp;
import java.util.List;

import com.orderdetail.model.OrderDetailVO;

public class OrderListService {
	private OrderListDAO_interface dao;
	
	public OrderListService() {
		dao = new OrderListJDBCDAO();
	}
	
//	-- 新增訂單資料-使用優惠券
	public OrderListVO addOrderList2(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick, List<OrderDetailVO> list) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setCoupNo(coupNo);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.insertWithOrderDetails(orderListVO, list);
		return orderListVO;
		
	}
	
	
//	-- 新增訂單資料-沒使用優惠券
	public OrderListVO addOrderListNc2(Integer ordNo, Integer memID, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick, List<OrderDetailVO> list) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.insertWithOrderDetailsNoCoupon(orderListVO, list);
		return orderListVO;
		
	}
	
//	-- 新增訂單資料-使用優惠券
	public OrderListVO addOrderList(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setCoupNo(coupNo);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.insert(orderListVO);
		return orderListVO;
		
	}
//	-- 新增訂單資料-沒使用優惠券
	public OrderListVO addOrderListNc(Integer ordNo, Integer memID, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.insertNoCoupon(orderListVO);
		return orderListVO;
		
	}
//	-- 更改訂單內容
	public OrderListVO updateOrderList(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		OrderListVO orderListVO = new OrderListVO();
		
		orderListVO.setOrdNo(ordNo);
		orderListVO.setMemID(memID);
		orderListVO.setCoupNo(coupNo);
		orderListVO.setOrdOriPrice(ordOriPrice);
		orderListVO.setOrdLastPrice(ordLastPrice);
		orderListVO.setOrdFee(ordFee);
		orderListVO.setOrdStatus(ordStatus);
		orderListVO.setOrdCreate(ordCreate);
		orderListVO.setRecName(recName);
		orderListVO.setRecAddress(recAddress);
		orderListVO.setRecPhone(recPhone);
		orderListVO.setOrdPick(ordPick);
		dao.update(orderListVO);
		return orderListVO;
		
	}
//  -- 秀出某一筆訂單的所有資料
	public OrderListVO showOneOrder(Integer ordNo) {
		return dao.findOneOrder(ordNo);
	}
//  -- 秀出某種出貨狀態的訂單
	public List<OrderListVO> showOrderByStatus(Integer ordStatus){
		return dao.findOrderByStatus(ordStatus);
	}
//  -- 秀出所有訂單
	public List<OrderListVO> getAll(){
		return dao.getAll();
	}
	
}
