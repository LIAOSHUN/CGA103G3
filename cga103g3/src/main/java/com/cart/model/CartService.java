package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.google.gson.Gson;
import com.product.model.ProductDAO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductVO;

//ShoppingCartItemVO cartItemVO = cartItemVO
//ProductDAO_interface = CartDAO_interface
//itemDAO = cartJDBCDAO
//Item = product
//ItemVO = ProductVO
//JedisHandleShoppingCart = CartRedisDAO

// user cookie("cart", sessionId)
// Redis List (sessionId, {"itemId": "xxx", "count": "x"})
// 使用者進入首頁時，檢查是否有key為shoppingCart的cookie，有的話取得sessionId，沒有則用給予cookie
// (可以做在filter，讓使用者不管進入哪個註冊到的頁面都先做此動作?)
// 用sessionId自redis取得購物車內的資料：jedis.lrange(key, 0, -1)，用itemId比對mySQL取得商品價格、比對庫存
public class CartService {
	private Gson gson = new Gson();
	
	private CartDAO_interface dao; 
	private CartRedisDAO daoR; 

	public CartService() {
		dao = new CartJDBCDAO();
		daoR = new CartRedisDAO();
	}
//=========================================================================================================
//	-- 找出某樣商品資訊
	public ProductVO getOne(Integer pdID) {
		return dao.getOne(pdID);
	}

//	-- 更改某樣商品資訊
	public void updatePdAmount(ProductVO productVO) {
		dao.update(productVO);
	}
	
//	===============================================================================================================
	
	//點擊購物車的按鈕，進入購物車，要先找這個人的車，去redis裡找購物車
	public List<CartItemVO> getCart(String sessionId) {
		List cartItemsList = new ArrayList();

		// search cart from redis
		List<String> cartItems = CartRedisDAO.getCart(sessionId); // {"itemId": "xxx", "count": "x"}
		for (int i = 0; i < cartItems.size(); i++) {
			CartItemVO cartItemVO = gson.fromJson(cartItems.get(i), CartItemVO.class);
			ProductVO productVO = dao.getOne(cartItemVO.getPdID());//從資料庫找出該商品名字及價錢，並設值到購物車的商品上
			
			cartItemVO.setPdName(productVO.getPdName());
			cartItemVO.setPdPrice(productVO.getPdPrice());

			cartItemsList.add(cartItemVO);
		}
		return cartItemsList; // 讓servlet取得後渲染於購物車頁面
	}

	public void addItem(String sessionId, Integer pdID, Integer count) {

		CartItemVO cartItemVO = new CartItemVO();
		cartItemVO.setPdID(pdID);
		cartItemVO.setCount(count);

		daoR.addItem(sessionId, cartItemVO);

	}

	public void deleteItem(String sessionId, Integer itemId) {
		daoR.deleteItem(sessionId, itemId);
	}

	public void deleteCart(String sessionId) {
		daoR.deleteCart(sessionId);
	};
	
	
	
	// 購物車存活七天

}
