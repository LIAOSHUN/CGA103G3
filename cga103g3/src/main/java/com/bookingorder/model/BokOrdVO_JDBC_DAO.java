package com.bookingorder.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static common.Common.*;

public class BokOrdVO_JDBC_DAO implements BookingOrder_interface{
	//會員訂位新增
	private static final String INSERT_STMT = 
			"insert into bookingorder (memid, bookingdate, bookingstart, bookingend, boxno) "
			+ "value (?, ?, ?, ?, ?)";
	
	//會員取消訂位
	private static final String DELETE_STMT = 
			"update bookingorder set BookingStatus = ? "
			+ "where BookingID = ?";
	
	//管理員可以修改訂位訂單資料
	private static final String UPDATE_STMT = 
			"update bookingorder set BookingDate = ?, BookingStart = ?, BookingEnd = ?, "
			+ "BoxNO = ?, BookingNote = ?, BookingStatus = ?"
			+ " where bookingid = ?";
	
	//管理員可以查詢指定訂單編號
	private static final String FINDPK_STMT = 
			"select * from bookingorder where BookingID = ?";
	
	//管理員可以查詢所有訂單
	private static final String GET_ALL_STMT = 
			"select * from bookingorder";

	@Override
	public void insert(BookingOrderVO bookingOrderVO) {
		try (
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(INSERT_STMT);
			){
			
			ps.setInt(1, bookingOrderVO.getMemID());
			ps.setDate(2, bookingOrderVO.getBookingDate());
			ps.setTime(3, bookingOrderVO.getBookingStart());
			ps.setTime(4, bookingOrderVO.getBookingEnd());
			ps.setInt(5, bookingOrderVO.getBoxNO());
			
			ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(BookingOrderVO bookingOrderVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement(DELETE_STMT);
			){
			
			ps.setInt(1, bookingOrderVO.getBookingStatus());
			ps.setInt(2, bookingOrderVO.getBookingID());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(BookingOrderVO bookingOrderVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement(UPDATE_STMT);
			){
			
			ps.setDate(1, bookingOrderVO.getBookingDate());
			ps.setTime(2, bookingOrderVO.getBookingStart());
			ps.setTime(3, bookingOrderVO.getBookingEnd());
			ps.setInt(4, bookingOrderVO.getBoxNO());
			ps.setString(5, bookingOrderVO.getBookingNote());
			ps.setInt(6, bookingOrderVO.getBookingStatus());
			ps.setInt(7, bookingOrderVO.getBookingID());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BookingOrderVO findBookingID(Integer bookingID) {
		BookingOrderVO bov = new BookingOrderVO();
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement(FINDPK_STMT);
			){
			
			ps.setInt(1, bookingID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bov.setBookingID(rs.getInt("BookingID"));
				bov.setMemID(rs.getInt("MemID"));
				bov.setBookingDate(rs.getDate("BookingDate"));
				bov.setBookingStart(rs.getTime("BookingStart"));
				bov.setBookingEnd(rs.getTime("BookingEnd"));
				bov.setBoxNO(rs.getInt("BoxNO"));
				bov.setBookingNote(rs.getString("BookingNote"));
				bov.setBookingStatus(rs.getInt("BookingStatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bov;
	}

	@Override
	public List<BookingOrderVO> getAll() {
		List<BookingOrderVO> list = new ArrayList<>();
		
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);
				ResultSet rs = ps.executeQuery();
			){
			while(rs.next()) {
				BookingOrderVO bko = new BookingOrderVO();
				bko.setBookingID(rs.getInt(1));
				bko.setMemID(rs.getInt(2));
				bko.setBookingDate(rs.getDate(3));
				bko.setBookingStart(rs.getTime(4));
				bko.setBookingEnd(rs.getTime(5));
				bko.setBoxNO(rs.getInt(6));
				bko.setBookingNote(rs.getString(7));
				bko.setBookingStatus(rs.getInt(8));
//				bko.setBookingStatusShow((rs.getInt(8) == 1) ? "訂位成功" : "訂位失敗");
				list.add(bko);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public static void main(String[] args){
		BokOrdVO_JDBC_DAO dao = new BokOrdVO_JDBC_DAO();
		
		//insert
//		BookingOrderVO bko = new BookingOrderVO();
//		bko.setMemID(11001);
//		bko.setBookingDate(java.sql.Date.valueOf("2022-08-06"));
//		bko.setBookingStart(java.sql.Time.valueOf("11:00:00"));
//		bko.setBookingEnd(java.sql.Time.valueOf("20:00:00"));
//		bko.setBoxNO(53003);
//		
//		dao.insert(bko);
		
		//cancel
//		BookingOrderVO bov = new BookingOrderVO();
//		bov.setBookingID(55001);
//		bov.setBookingStatus(1);
//		dao.cancel(bov);
//		
//		System.out.println("訂單狀態: " + bov.getBookingStatus());
		
		//update
//		BookingOrderVO bov1 = new BookingOrderVO();
//		bov1.setBookingDate(java.sql.Date.valueOf("2022-08-10"));
//		bov1.setBookingStart(java.sql.Time.valueOf("17:00:00"));
//		bov1.setBookingEnd(java.sql.Time.valueOf("21:00:00"));
//		bov1.setBoxNO(53005);
//		bov1.setBookingNote("天王按摩椅");
//		bov1.setBookingStatus(1);
//		bov1.setBookingID(55001);
//		dao.update(bov1);
		
		
		//find PK
//		BookingOrderVO bvo1 = dao.findPK(55001);
//		System.out.println("訂位編號: " + bvo1.getBookingID());
//		System.out.println("會員編號: " + bvo1.getMemID());
//		System.out.println("訂位日期: " + bvo1.getBookingDate());
//		System.out.println("訂位時間: " + bvo1.getBookingStart());
//		System.out.println("訂位結束時間: " + bvo1.getBookingEnd());
//		System.out.println("包廂編號: " + bvo1.getBoxNO());
//		System.out.println("訂位註記: " + bvo1.getBookingNote());
//		System.out.println("訂位狀態: " + bvo1.getBookingStatus());
//		System.out.println("==============================================");
		
		//getall
		for (BookingOrderVO gtl : dao.getAll()) {
			System.out.println("BookingID: " + gtl.getBookingID());
			System.out.println("MemID: " + gtl.getMemID());
			System.out.println("BookingDate: " + gtl.getBookingDate());
			System.out.println("BookingStart: " + gtl.getBookingStart());
			System.out.println("BookingEnd: " + gtl.getBookingEnd());
			System.out.println("BoxNO: " + gtl.getBoxNO());
			System.out.println("BookingNote: " + gtl.getBookingNote());
			System.out.println("BookingNote: " + gtl.getBookingStatusShow());
			//三元運算子
//			String show = (gtl.getBookingStatus() == 1) ? "訂位成功" : "訂位失敗";
//			//一般if
//			String show;
//			int get = gtl.getBookingStatus();
//			if(get == 1){
//				show = "訂位成功";
//			} else {
//				show = "訂位失敗";
//			}
//			System.out.println("BookingStatus: " + show);
//			System.out.println("BookingStatus: " + gtl.getBookingStatus());
			System.out.println("==================================================");
		}
	}
}
