package com.cart.model;

import com.product.model.ProductVO;

public class CartService {
	private CartJDBCDAO dao;
	private CartRedisDAO daoR;
	
	public  CartService(){
		dao = new CartJDBCDAO();
		daoR = new CartRedisDAO();
	}

	
//	-- 找出某樣商品資訊
	public ProductVO getOne(Integer pdID) {
		return dao.getOne(pdID);
	}
	
//	-- 更改某樣商品資訊
	public void updatePdAmount(ProductVO productVO) {
		dao.update(productVO);
	}
	
	
	//增加商品到購物車
	//商品數量加1
	//商品數量減1
	//商品數量減到0，移除商品
	//移除商品
	//清空購物車
	//共幾樣商品
	//購物車存活七天
	
	
	
}
