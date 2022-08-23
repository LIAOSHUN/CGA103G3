package com.store.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static common.Common.*;

public class Store_JDBC_DAO implements StoreVO_interface {
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
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

	public static void main(String[] args) throws IOException {

		Store_JDBC_DAO dao = new Store_JDBC_DAO();

		// insert
//		StoreVO stVO1 = new StoreVO();
//		stVO1.setStoreName("絆桌-SQL店");
//		stVO1.setStoreAdd("桃園市中壢區復興路46號9樓之905教室");
//		stVO1.setStorePhone1("03-xxxxxxx");
//		stVO1.setStorePhone2("03-xxxxxxx");
//		stVO1.setStoreEmail("CGA103@tibame.com");
//		stVO1.setStoreImg(null);
//		stVO1.setStoreOpen(java.sql.Time.valueOf("10:00:00"));
//		stVO1.setStoreClose(java.sql.Time.valueOf("21:00:00"));
//		stVO1.setStoreOff("0010000");
//		stVO1.setEmpID(91001);
//		stVO1.setStoreBokSet("000000000011111111111100");
//		stVO1.setStoreStatus(1);
//		
//		dao.insert(stVO1);

		// update
//		StoreVO stVO3 = new StoreVO();
//		stVO3.setStoreName("絆桌-Java店");
//		stVO3.setStoreAdd("桃園市中壢區復興路46號9樓");
//		stVO3.setStorePhone1("03-4251108");
//		stVO3.setStorePhone2("03-4251108");
//		stVO3.setStoreEmail("CGA103G5@tibame.com");
//		byte[] img = getImg("storeimg.jpg");
//		stVO3.setStoreImg(img);
//		stVO3.setStoreOpen(java.sql.Time.valueOf("10:00:00"));
//		stVO3.setStoreClose(java.sql.Time.valueOf("22:00:00"));
//		stVO3.setStoreOff("0100000");
//		stVO3.setEmpID(91001);
//		stVO3.setStoreBokSet("000000000011111111111100");
//		stVO3.setStoreStatus(1);
//		stVO3.setStoreID(51001);
//
//		dao.update(stVO3);

		// 單一查詢
		StoreVO stVO2 = dao.findPK(51002);
		System.out.print(stVO2.getStoreID() + ", ");
		System.out.print(stVO2.getStoreName() + ", ");
		System.out.print(stVO2.getStoreAdd() + ", ");
		System.out.print(stVO2.getStorePhone1() + ", ");
		System.out.print(stVO2.getStorePhone2() + ", ");
		System.out.print(stVO2.getStoreEmail() + ", ");
		System.out.print(stVO2.getStoreImg() + ", ");
		System.out.print(stVO2.getStoreOpen() + ", ");
		System.out.print(stVO2.getStoreClose() + ", ");
		System.out.print(stVO2.getStoreOff() + ", ");
		System.out.print(stVO2.getEmpID() + ", ");
		System.out.print(stVO2.getStoreBokSet() + ", ");
		System.out.println(stVO2.getStoreStatus() + ", ");
		System.out.println("==================================");

		// gatall查詢
		for (StoreVO siv : dao.getAll()) {
			System.out.println("店面編號: " + siv.getStoreID());
			System.out.println("店面店名: " + siv.getStoreName());
			System.out.println("店面地址: " + siv.getStoreAdd());
			System.out.println("店面電話1: " + siv.getStorePhone1());
			System.out.println("店面電話2: " + siv.getStorePhone2());
			System.out.println("店面e-mail: " + siv.getStoreEmail());
			System.out.println("店面照片: " + siv.getStoreImg());
			System.out.println("店面營業起始時間: " + siv.getStoreOpen());
			System.out.println("店面營業結束時間: " + siv.getStoreClose());
			System.out.println("店面公休日期: " + siv.getStoreOff());
			System.out.println("管理者編號: " + siv.getEmpID());
			System.out.println("訂位時段: " + siv.getStoreBokSet());
			System.out.println("店面狀態: " + siv.getStoreStatus());
			System.out.println("==========================================================");
		}
	}

	public static byte[] getImg(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
