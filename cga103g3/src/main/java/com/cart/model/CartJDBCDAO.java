package com.cart.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductVO;

public class CartJDBCDAO implements CartDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "asd12377";
	
//	-- 找出某樣商品資訊
	private static final String GetOne = 
			"SELECT PdID,PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where PdID = ?";
//	-- 更改某樣商品資訊
	private static final String Update = 
			"UPDATE product set PdAmount=? where PdID = ?";
	
	
	
	
	@Override
	public ProductVO getOne(Integer pdID) {
		ProductVO productVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GetOne)){
			ps.setInt(1, pdID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("PdID"));
				productVO.setPdName(rs.getString("PdName"));
				productVO.setPdPrice(rs.getInt("PdPrice"));
				productVO.setPdAmount(rs.getInt("PdAmount"));
				productVO.setPdDescription(rs.getString("PdDescription"));
				productVO.setPdStatus(rs.getInt("PdStatus"));
				productVO.setPdStar(rs.getInt("PdStar"));
				productVO.setPdUpdate(rs.getTimestamp("PdUpdate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productVO;
	}

	@Override
	public void update(ProductVO productVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Update)){
			ps.setInt(1, productVO.getPdAmount());
			ps.setInt(2, productVO.getPdID());
			
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		CartJDBCDAO dao = new CartJDBCDAO();
		
//		-- 找出某樣商品資訊
		ProductVO productVO3= dao.getOne(21001);
		System.out.print(productVO3.getPdID() + ",");
		System.out.print(productVO3.getPdName() +",");
		System.out.print(productVO3.getPdPrice() + ",");
		System.out.print(productVO3.getPdAmount() + ",");
		System.out.print(productVO3.getPdDescription() + ",");
		System.out.print(productVO3.getPdStatus() + ",");
		System.out.println(productVO3.getPdStar() + ",");
		System.out.println("---------------------");
		
//		-- 更改某樣商品資訊
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setPdAmount(100);
//		productVO2.setPdID(21005);
//		
//		dao.update(productVO2);

	}



}
