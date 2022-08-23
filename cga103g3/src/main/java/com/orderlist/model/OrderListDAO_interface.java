package com.orderlist.model;

import java.util.List;

public interface OrderListDAO_interface {
	
//	-- 新增訂單資料-使用優惠券
	public void insert(OrderListVO orderListVO);
//	-- 新增訂單資料-沒使用優惠券
	public void insertNoCoupon(OrderListVO orderListVO);
//	-- 更改訂單內容
    public void update(OrderListVO orderListVO);
//  -- 找出某一筆訂單的所有資料
    public OrderListVO findOneOrder(Integer ordNo);
//  -- 找出某種出貨狀態的訂單
    public List<OrderListVO> findOrderByStatus(Integer ordStatus);
//  -- 找出所有訂單
    public List<OrderListVO> getAll();
}
