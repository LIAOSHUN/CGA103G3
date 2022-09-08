package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


import com.google.gson.Gson;
import com.memcoupon.model.MemCouponDAO;
import com.memcoupon.model.MemCouponDAO_interface;
import com.orderdetail.model.OrderDetailVO;
import com.orderlist.model.OrderListDAO;
import com.orderlist.model.OrderListVO;

public class CheckoutService {
	private Gson gson = new Gson();
	
	private CartDAO_interface dao; 
	private CartRedisDAO daoR; 
	private OrderListDAO daoL; 
	private MemCouponDAO_interface daoMC; 

	public CheckoutService() {
		dao = new CartJDBCDAO();
		daoR = new CartRedisDAO();
		daoL = new OrderListDAO();
		daoMC = new MemCouponDAO();
	}
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "asd12377";
	
	//邏輯必須寫在這層才對，專門處理結帳所有需要的動作，並針對結帳進行交易控制
	//所有動作，必須用同一連線
	//呼叫不同dao，各自完成任務
	//底層的處理，必須把例外全拋出，給service層來進行處理，因為此層在做交易控制，不然會無法rollback
	public boolean allJobs(Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, String recName, String recAddress, String recPhone,
			Integer ordPick, List<OrderDetailVO> list, String sessionId) {
		
		Connection con = null;
		Boolean transa = false;
		

		
		try{
			con = DriverManager.getConnection(url, userid, passwd);
			//交易開始，若一個動作失敗，則全部回復
			con.setAutoCommit(false);
			
			
			//1.新增訂單同時新增訂單明細
			
			//傳入的參數若為0則代表未使用優惠券
			if(coupNo.equals(0)) {
				System.out.println("未使用優惠券");
				OrderListVO orderListVO = new OrderListVO();
				
				orderListVO.setMemID(memID);
				orderListVO.setOrdOriPrice(ordOriPrice);
				orderListVO.setOrdLastPrice(ordLastPrice);
				orderListVO.setOrdFee(ordFee);
				orderListVO.setOrdStatus(ordStatus);
				orderListVO.setRecName(recName);
				orderListVO.setRecAddress(recAddress);
				orderListVO.setRecPhone(recPhone);
				orderListVO.setOrdPick(ordPick);
				daoL.insertWithOrderDetailsNoCoupon(con, orderListVO, list);
			}else {
				//有使用優惠券
				OrderListVO orderListVO = new OrderListVO();
				
				orderListVO.setMemID(memID);
				orderListVO.setCoupNo(coupNo);
				orderListVO.setOrdOriPrice(ordOriPrice);
				orderListVO.setOrdLastPrice(ordLastPrice);
				orderListVO.setOrdFee(ordFee);
				orderListVO.setOrdStatus(ordStatus);
				orderListVO.setRecName(recName);
				orderListVO.setRecAddress(recAddress);
				orderListVO.setRecPhone(recPhone);
				orderListVO.setOrdPick(ordPick);
				daoL.insertWithOrderDetails(con, orderListVO, list);
			}
			
			
			//2.更改某樣商品庫存
			List<OrderDetailVO> listForUpdate = list;
			
			for (int index = 0; index < listForUpdate.size(); index++) {
				
				OrderDetailVO orderDetailVO = listForUpdate.get(index);
				Integer pdID = orderDetailVO.getPdID();
				
				List<String> cartItems = CartRedisDAO.getCart(sessionId);//先把他的車叫出來
				
				Integer rediscount = 0;
				Integer pdAmount = 0;
				for (int i = 0; i < cartItems.size(); i++) {
					CartItemVO orgItem = gson.fromJson(cartItems.get(i), CartItemVO.class);
					Integer checkedPdID = pdID;
					Integer orgItemId = orgItem.getPdID();
					// 找出選了哪個商品ID
					if (pdID.equals(orgItemId)) {
						rediscount = orgItem.getCount();
						pdAmount = dao.getOne(orgItemId).getPdAmount();
					}
				};
				dao.update((pdAmount - rediscount), pdID, con);
			};
			
			
			//3.更改會員優惠券狀態
			if(!coupNo.equals(0)) {
				
				daoMC.updateStatus(coupNo, 1);
				System.out.println("更改優惠券狀態0變成1");
			}
			
			
			
			
			
			
			//以上動作成功的話，才送出交易
			con.commit();
			con.setAutoCommit(true);
			transa = true;
		}catch (Exception e) {
			try {
				con.rollback();
				System.err.println("交易失敗");
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.err.println("交易失敗");
			}
		}finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return transa;
		
	}
	
	
}
