package com.orderdetail.model;

import java.util.List;

public class OrderDetailService {

	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
//	-- 新增一筆訂單明細
	public OrderDetailVO addOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdNo(ordNo);
		orderDetailVO.setPdID(pdID);
		orderDetailVO.setItemSales(itemSales);
		orderDetailVO.setPrice(price);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
//	-- 更新某一筆訂單明細的內容(ItemSales,Price)
	public OrderDetailVO updateOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdNo(ordNo);
		orderDetailVO.setPdID(pdID);
		orderDetailVO.setItemSales(itemSales);
		orderDetailVO.setPrice(price);
		dao.update(orderDetailVO);
		
		return orderDetailVO;
	}
//	-- 作廢某一筆訂單明細的內容(ItemSales,Price)
	public OrderDetailVO clearOrderDetail(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdNo(ordNo);
		orderDetailVO.setPdID(pdID);
		orderDetailVO.setItemSales(itemSales);
		orderDetailVO.setPrice(price);
		dao.clear(orderDetailVO);
		
		return orderDetailVO;
	}
	
//	-- 秀出某一筆訂單明細中的每個遊戲
	public List<OrderDetailVO> showOneOrderDetail(Integer ordNo){
		return dao.findOneOrderDetail(ordNo);
	}
	
//	-- 秀出某一筆訂單明細中的某一個遊戲
	public OrderDetailVO showOneOrderDetailPd(Integer ordNo, Integer pdID) {
		return dao.findOneOrderDetailPd(ordNo, pdID);
	}
//	-- 秀出所有訂單明細
	public List<OrderDetailVO> getAll(){
		return dao.getAll();
	}
	
}
