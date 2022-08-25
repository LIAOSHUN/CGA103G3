package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.product.model.ProductVO;

import redis.clients.jedis.Jedis;

public class CartRedisDAO {

	public static void main(String[] args) throws JSONException {
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.select(1);
//
//		String jsonStr = "";
//		String s = "";
//		String key = "";
//
//		String pdName = "阿瓦隆";
//		Integer ccount = 10;
//		Integer memID = 11002;
//
//		key = memID.toString();
//		jsonStr = "[{ " + pdName + ":" + ccount + "}]";
//
//		JSONArray jsonArray = new JSONArray(jsonStr);
//
//		// JSON to List
//
//		jedis.lpush(key, jsonStr);
////		jedis.lpush( "11001", "[{阿瓦隆:1}]");
//
//		jedis.close();
		
		
		
//		====================getCart=======================
//		Integer memID = 11001;
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.select(1);
//		String memIDs = "";
//		memIDs = memID.toString();
//
//		List<String> cartItems = jedis.lrange(memIDs, 0, -1);
//		jedis.close();
//		System.out.println(cartItems);
		
		
//		====================addItem=======================
		
		Integer memID = 11001;
		Integer pdID = 21001;
		CartVO cartVO = new CartVO();
		cartVO.setPdID(pdID);
		cartVO.setCount(2);
		
		
		Gson gson = new Gson();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(2);
		String memIDs = "";
		memIDs = memID.toString();
		
		List<String> cartItems = getCart(memID);

		if (cartItems != null) {
			for (int i = 0; i < cartItems.size(); i++) {
				CartVO productVOtoAdd = gson.fromJson(cartItems.get(i), CartVO.class);

				Integer cartItemId = cartVO.getPdID();
				Integer itemVOtoAddId = productVOtoAdd.getPdID();

				// 若購物車內已有該商品ID則增加數量
				if (cartItemId.equals(itemVOtoAddId)) {
					Integer count = productVOtoAdd.getCount();

					count += 1; // 購物車內無調整數量功能，由使用者觸發「add」按鈕後+1
					productVOtoAdd.setCount(count);

					String str = gson.toJson(productVOtoAdd);
					jedis.lset(memIDs, i, str);
					jedis.close();
					return;
				}
			}

			// 若沒有該商品ID則新增
			String strVO = gson.toJson(cartVO);
			jedis.rpush(memIDs, strVO);
			jedis.close();
		}
		
//		====================deleteItem====================
		
//		Integer memID = 11001;
//		Integer pdID = 21001;
//		
//		Gson gson = new Gson();
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.select(1);
//		String memIDs = "";
//		memIDs = memID.toString();
//		
//
//		List<String> cartItems = getCart(memID);
//
//		for (int i = 0; i < cartItems.size(); i++) {
//			CartVO itemVO = gson.fromJson(cartItems.get(i), CartVO.class);
//
//			if (itemVO.getPdID().equals(pdID)) {
//				jedis.lrem(memIDs, 0, cartItems.get(i)); // 刪除該商品
//				jedis.close();
//				return;
//			}
//		}
		
		
		
		
//		====================deleteCart====================
		
		
		
		
	}

//	====================================================================

	public static List<String> getCart(Integer memID) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(1);
		String memIDs = "";
		memIDs = memID.toString();

		List<String> cartItems = jedis.lrange(memIDs, 0, -1);
		jedis.close();
		return cartItems;
	}

	public static void addItem(Integer memID, CartVO cartVO) {

		Gson gson = new Gson();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(1);
		String memIDs = "";
		memIDs = memID.toString();

		List<String> cartItems = getCart(memID);

		if (cartItems != null) {
			for (int i = 0; i < cartItems.size(); i++) {
				CartVO productVOtoAdd = gson.fromJson(cartItems.get(i), CartVO.class);

				Integer cartItemId = cartVO.getPdID();
				Integer itemVOtoAddId = productVOtoAdd.getPdID();

				// 若購物車內已有該商品ID則增加數量
				if (cartItemId.equals(itemVOtoAddId)) {
					Integer count = productVOtoAdd.getCount();

					count += 1; // 購物車內無調整數量功能，由使用者觸發「add」按鈕後+1
					productVOtoAdd.setCount(count);

					String str = gson.toJson(productVOtoAdd);
					jedis.lset(memIDs, i, str);
					jedis.close();
					return;
				}
			}

			// 若沒有該商品ID則新增
			String strVO = gson.toJson(cartVO);
			jedis.rpush(memIDs, strVO);
			jedis.close();
		}

	}

	public static void deleteItem(Integer memID, Integer pdID) {
		Gson gson = new Gson();
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(1);
		String memIDs = "";
		memIDs = memID.toString();
		

		List<String> cartItems = getCart(memID);

		for (int i = 0; i < cartItems.size(); i++) {
			CartVO itemVO = gson.fromJson(cartItems.get(i), CartVO.class);

			if (itemVO.getPdID().equals(pdID)) {
				jedis.lrem(memIDs, 0, cartItems.get(i)); // 刪除該商品
				jedis.close();
				return;
			}
		}
	}

	public static void deleteCart(Integer memID) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(1);
		String memIDs = "";
		memIDs = memID.toString();
		
		jedis.del(memIDs); // 清空購物車刪除key
		jedis.close();
	}

	
	
	
	
	
	
	
	
	
	
	
//增加商品到購物車
//	public List<ProductVO> addCart(Integer memID, String pdName, Integer ccount) {
//
//		List<ProductVO> list = new ArrayList<ProductVO>();
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.select(1);// 更換資料庫
////			jedis.lpush("", "{pdName:ccount}");
////			jedis.lpush("11001", "{"阿瓦隆":2}");
//
//		jedis.lpush("11001", "[{阿瓦隆:1}]");
//
//		// JSON to List
//
//		jedis.close();
//		return list;
//	}
//		
//		
//		
//		
//		
////		商品1{"阿瓦隆":2}
////		商品2{"狼人殺":2}
//		
//		
////商品數量加1
////商品數量減1
////商品數量減到0，移除商品
////移除商品
////清空購物車
////共幾樣商品
////購物車存活七天
//		
//		
//		jedis.close();
//		
//	
}
