package com.coupontype.model;

import java.util.List;

public interface CouponTypeDAO_interface {
//	-- 新增資料
	public void insert(CouponTypeVO couponTypeVO);
//	-- 更改優惠券內容
	public void update(CouponTypeVO couponTypeVO);
//	-- 找出某種類型的優惠券
	public CouponTypeVO findCouponTypeByType(Integer coupTypeNo);
//	-- 找出所有優惠券
	public List<CouponTypeVO> getAll();
//	-- 刪除某張優惠券
	public void delete(Integer coupTypeNo);
	
}
