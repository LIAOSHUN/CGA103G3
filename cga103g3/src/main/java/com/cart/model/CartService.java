package com.cart.model;

import java.util.List;

import org.json.JSONArray;

import com.product.model.ProductVO;

public class CartService {
	private CartJDBCDAO dao;
	private CartRedisDAO daoR;

	public CartService() {
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

	
	
	
	
	
	
	private Gson gson = new Gson();
	private ItemDAO_interface itemDAO = new ItemDAO();

	public ShoppingCartService() {
		
	}

	// user cookie("cart", sessionId)
	// Redis List (sessionId, {"itemId": "xxx", "count": "x"})
	// 使用者進入首頁時，檢查是否有key為shoppingCart的cookie，有的話取得sessionId，沒有則用給予cookie
	// (可以做在filter，讓使用者不管進入哪個註冊到的頁面都先做此動作?)
	// 用sessionId自redis取得購物車內的資料：jedis.lrange(key, 0, -1)，用itemId比對mySQL取得商品價格、比對庫存

	public List<ShoppingCartItemVO> getCart(String sessionId) {
		List cartItemsList = new ArrayList();

		// search cart from redis
		List<String> cartItems = JedisHandleShoppingCart.getCart(sessionId); // {"itemId": "xxx", "count": "x"}
		for (int i = 0; i < cartItems.size(); i++) {
			ShoppingCartItemVO cartItemVO = gson.fromJson(cartItems.get(i), ShoppingCartItemVO.class);
			ItemVO itemVO = itemDAO.findByPrimaryKey(cartItemVO.getItemId());
			cartItemVO.setItemName(itemVO.getItemName());
			cartItemVO.setItemPrice(itemVO.getItemPrice());

			cartItemsList.add(cartItemVO);
		}
		return cartItemsList; // 讓servlet取得後渲染於購物車頁面
	}

	public void addItem(String sessionId, Integer itemId, Integer count) {

		ShoppingCartItemVO cartItemVO = new ShoppingCartItemVO();
		cartItemVO.setItemId(itemId);
		cartItemVO.setCount(count);

		JedisHandleShoppingCart.addItem(sessionId, cartItemVO);

	}

	public void deleteItem(String sessionId, Integer itemId) {
		JedisHandleShoppingCart.deleteItem(sessionId, itemId);
	}

	public void deleteCart(String sessionId) {
		JedisHandleShoppingCart.deleteCart(sessionId);
	};
	
	
	
	// 購物車存活七天

}
