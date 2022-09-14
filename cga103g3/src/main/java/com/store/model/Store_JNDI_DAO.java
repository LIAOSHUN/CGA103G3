package com.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Store_JNDI_DAO implements Store_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("連線池錯誤");
		}
	}

	//管理員可以新增店面
	private static final String INSERT_STMT = 
			"INSERT INTO store (storeName, storeAdd, storePhone1, storePhone2, storeEmail, StoreImg, storeOpen, storeClose, storeOff, empID, storeBokSet, storeStatus) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	//管理員可以修改店面資訊
	private static final String UPDATE = 
			"UPDATE store set StoreName=?, StoreAdd=?, StorePhone1=?, StorePhone2=?, StoreEmail=?, StoreImg=? "
					+ ", StoreOpen=?, StoreClose=?, StoreOff=?, EmpID=?, StoreBokSet=?, StoreStatus=?"
					+ " where StoreID = ?";
	
	//管理員可以查詢所有店面資訊
	private static final String GET_ALL_STMT = 
			"SELECT * FROM store";

	//管理員可以查詢單一家店面資訊
	private static final String GET_ONE_STMT = 
			"SELECT * FROM store "
			+ "where storeID = ?";

	@Override
	public void insert(StoreVO storeVO) {
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);
		) {

			pstmt.setString(1, storeVO.getStoreName());
			pstmt.setString(2, storeVO.getStoreAdd());
			pstmt.setString(3, storeVO.getStorePhone1());
			pstmt.setString(4, storeVO.getStorePhone2());
			pstmt.setString(5, storeVO.getStoreEmail());
			pstmt.setBytes(6, storeVO.getStoreImg());
			pstmt.setTime(7, storeVO.getStoreOpen());
			pstmt.setTime(8, storeVO.getStoreClose());
			pstmt.setString(9, storeVO.getStoreOff());
			pstmt.setInt(10, storeVO.getEmpID());
			pstmt.setString(11, storeVO.getStoreBokSet());
			pstmt.setInt(12, storeVO.getStoreStatus());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(StoreVO storeVO) {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE);
			) {

			pstmt.setString(1, storeVO.getStoreName());
			pstmt.setString(2, storeVO.getStoreAdd());
			pstmt.setString(3, storeVO.getStorePhone1());
			pstmt.setString(4, storeVO.getStorePhone2());
			pstmt.setString(5, storeVO.getStoreEmail());
			pstmt.setBytes(6, storeVO.getStoreImg());
			pstmt.setTime(7, storeVO.getStoreOpen());
			pstmt.setTime(8, storeVO.getStoreClose());
			pstmt.setString(9, storeVO.getStoreOff());
			pstmt.setInt(10, storeVO.getEmpID());
			pstmt.setString(11, storeVO.getStoreBokSet());
			pstmt.setInt(12, storeVO.getStoreStatus());
			pstmt.setInt(13, storeVO.getStoreID());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public StoreVO findPK(Integer storeID) {
		StoreVO siv = null;
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT);
//				ResultSet rs = pstmt.executeQuery();
		) {
			pstmt.setInt(1, storeID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				siv = new StoreVO();
				siv.setStoreID(rs.getInt("storeID"));
				siv.setStoreName(rs.getString("storeName"));
				siv.setStoreAdd(rs.getString("storeAdd"));
				siv.setStorePhone1(rs.getString("storePhone1"));
				siv.setStorePhone2(rs.getString("storePhone2"));
				siv.setStoreEmail(rs.getString("StoreEmail"));
				siv.setStoreImg(rs.getBytes("StoreImg"));
				siv.setStoreOpen(rs.getTime("storeOpen"));
				siv.setStoreClose(rs.getTime("storeClose"));
				siv.setStoreOff(rs.getString("StoreOff"));
				siv.setEmpID(rs.getInt("EmpID"));
				siv.setStoreBokSet(rs.getString("StoreBokSet"));
				siv.setStoreStatus(rs.getInt("StoreStatus"));
			}
			return siv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<StoreVO> getAll() {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery();) {
			List<StoreVO> list = new ArrayList<>();
			while (rs.next()) {
				StoreVO siv = new StoreVO();
				siv.setStoreID(rs.getInt(1));
				siv.setStoreName(rs.getString(2));
				siv.setStoreAdd(rs.getString(3));
				siv.setStorePhone1(rs.getString(4));
				siv.setStorePhone2(rs.getString(5));
				siv.setStoreEmail(rs.getString(6));
				siv.setStoreImg(rs.getBytes(7));
				siv.setStoreOpen(rs.getTime(8));
				siv.setStoreClose(rs.getTime(9));
				siv.setStoreOff(rs.getString(10));
				siv.setEmpID(rs.getInt(11));
				siv.setStoreBokSet(rs.getString(12));
				siv.setStoreStatus(rs.getInt(13));
				list.add(siv);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}