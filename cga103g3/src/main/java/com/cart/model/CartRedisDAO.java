package com.cart.model;

import java.util.List;

import org.json.JSONObject;

import com.product.model.ProductVO;

import redis.clients.jedis.Jedis;

public class CartRedisDAO {
	
		Jedis jedis = new Jedis("localhost", 6379);
		
		
	
		
		
		
//增加商品到購物車
		public  addCart() {
			
		}
		
		
		
		jedis.lpush("11001", )
		
		
		
		
		
//商品數量加1
//商品數量減1
//商品數量減到0，移除商品
//移除商品
//清空購物車
//共幾樣商品
//購物車存活七天
		
		
		jedis.close();
		
	
}
