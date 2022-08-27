package com.orderlist.model;

import java.util.List;

import com.orderdetail.model.OrderDetailVO;

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
//  訂單主檔與明細檔一次新增成功
    public void insertWithOrderDetails(OrderListVO orderListVO, List<OrderDetailVO> list );
//  訂單主檔與明細檔一次新增成功(無優惠券)
    public void insertWithOrderDetailsNoCoupon(OrderListVO orderListVO, List<OrderDetailVO> list );
}
