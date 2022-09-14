package com.bookingorder.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookingOrdDAO implements BookingOrder_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/boardgame");
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("連線池錯誤");
		}
	}
	
	//查詢訂單狀態(比對資料)
	private static final String CHECK_BOOKING = 
			"select BookingDate, BookingStart, BookingEnd, BoxID from bookingorder where BookingStatus = 1;";
	
	//會員訂位新增
	private static final String INSERT_STMT = 
			"insert into bookingorder (memid, bookingdate, bookingstart, bookingend, boxID, bookingNote) "
			+ "value (?, ?, ?, ?, ?, ?)";
	
	//會員取消訂位
	private static final String CANCEL_STMT = 
			"update bookingorder set BookingStatus = ? "
			+ "where BookingID = ?";
	
	//管理員可以修改訂位訂單資料
	private static final String UPDATE_STMT = 
			"update bookingorder set BookingDate = ?, BookingStart = ?, BookingEnd = ?, "
			+ "boxID = ?, BookingNote = ?, BookingStatus = ?"
			+ " where bookingid = ?";
	
	//管理員可以查詢指定訂單編號
	private static final String FINDPK_STMT = 
			"select * from bookingorder where BookingID = ?";
	
	//管理員可以查詢所有訂單
	private static final String GET_ALL_STMT = 
			"select * from bookingorder";
	
	//管理員可以查詢成立的預約訂單
	private static final String GET_BOOKING_STATUS = 
			"select * from bookingorder where BookingStatus = ? order by BookingDate";
	
	//會員可以查詢訂位訂單
	private static final String GET_BOOKING_ORD = 
			"select * from bookingorder where memid = ?";
	
	//管理員可以刪除訂單
	private static final String DELETE = 
			"delete from bookingorder where BookingID = ?";
	
	@Override
	synchronized public void insert(BookingOrderVO bookingOrderVO) {
		java.sql.Date checkDate = null;
		Integer checkStart = null;
		Integer checkEnd = null;
		int checkBox;
		java.sql.Date bookingDate = bookingOrderVO.getBookingDate();
		int bookingBoxID = bookingOrderVO.getBoxID();
		int bookingStart = Integer.valueOf(bookingOrderVO.getBookingStart());
		int bookingEnd = Integer.valueOf(bookingOrderVO.getBookingEnd());
		
		MailService mailService = new MailService();
		String reback = "訂位失敗,此時段已被預訂，請重新訂位，謝謝。";
		String to = "ufo3068@gmail.com";
		String subject = "訂位通知";
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(CHECK_BOOKING);
				PreparedStatement ps2 = con.prepareStatement(INSERT_STMT);
				ResultSet rs = ps.executeQuery();
			){
			con.setAutoCommit(false);
			
			
			while (rs.next()) {
				checkDate = rs.getDate("BookingDate");
				checkStart = Integer.valueOf(rs.getString("BookingStart"));
				checkEnd = Integer.valueOf(rs.getString("BookingEnd"));
				checkBox = rs.getInt("BoxID");
			
				if(checkDate.equals(bookingDate) && checkBox == bookingBoxID){
					System.out.println("日期、包廂相同");
					if(bookingEnd > checkStart && bookingEnd <= checkEnd) {
						System.out.println("執行到第一個");
						mailService.sendMail(to, subject, reback);
						con.rollback();
						return;
					} else if(bookingStart <= checkStart && bookingEnd >= checkEnd) {
						System.out.println("執行到第二個");
						mailService.sendMail(to, subject, reback);
						con.rollback();
						return;
					} else if(bookingStart >= checkStart && bookingStart <= checkEnd){
						System.out.println("執行到第三個");
						mailService.sendMail(to, subject, reback);
						con.rollback();
						return;
					} else if (bookingStart >= checkStart && bookingEnd <= checkEnd) {
						System.out.println("執行到第四個");
						con.rollback();
						mailService.sendMail(to, subject, reback);
						return;
					}
				}
					
			}
			
			ps2.setInt(1, bookingOrderVO.getMemID());
			ps2.setDate(2, bookingOrderVO.getBookingDate());
			ps2.setString(3, bookingOrderVO.getBookingStart());
			ps2.setString(4, bookingOrderVO.getBookingEnd());
			ps2.setInt(5, bookingOrderVO.getBoxID());
			ps2.setString(6, bookingOrderVO.getBookingNote());
			
			ps2.execute();
			
			con.commit();
			con.setAutoCommit(true);

			reback = "訂位成功，請準時報到，謝謝。";
			mailService.sendMail(to, subject, reback);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
//	public void insert(BookingOrderVO bookingOrderVO) {
//		try (
//				Connection con = ds.getConnection();
//				PreparedStatement ps = con.prepareStatement(INSERT_STMT);
//			){
//			
//			ps.setInt(1, bookingOrderVO.getMemID());
//			ps.setDate(2, bookingOrderVO.getBookingDate());
//			ps.setString(3, bookingOrderVO.getBookingStart());
//			ps.setString(4, bookingOrderVO.getBookingEnd());
//			ps.setInt(5, bookingOrderVO.getBoxID());
//			ps.setString(6, bookingOrderVO.getBookingNote());
//			
//			ps.execute();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Override
	public void cancel(BookingOrderVO bookingOrderVO) {
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(CANCEL_STMT);
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
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_STMT);
			){
			
			ps.setDate(1, bookingOrderVO.getBookingDate());
			ps.setString(2, bookingOrderVO.getBookingStart());
			ps.setString(3, bookingOrderVO.getBookingEnd());
			ps.setInt(4, bookingOrderVO.getBoxID());
			ps.setString(5, bookingOrderVO.getBookingNote());
			ps.setInt(6, bookingOrderVO.getBookingStatus());
			ps.setInt(7, bookingOrderVO.getBookingID());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BookingOrderVO getOneBookingID(Integer bookingID) {
		BookingOrderVO bov = new BookingOrderVO();
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(FINDPK_STMT);
			){
			
			ps.setInt(1, bookingID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bov.setBookingID(rs.getInt("BookingID"));
				bov.setMemID(rs.getInt("MemID"));
				bov.setBookingDate(rs.getDate("BookingDate"));
//				bov.setBookingStart(rs.getString("BookingStart"));
				int start = Integer.valueOf(rs.getString("BookingStart").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String bookingStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bov.setBookingStart(bookingStart);
				
//				bov.setBookingEnd(rs.getString("BookingEnd"));
				int end = Integer.valueOf(rs.getString("BookingEnd"));
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String bookingEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bov.setBookingEnd(bookingEnd);
				bov.setBoxID(rs.getInt("boxID"));
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
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);
				ResultSet rs = ps.executeQuery();
			){
			while(rs.next()) {
				BookingOrderVO bko = new BookingOrderVO();
				bko.setBookingID(rs.getInt("BookingID"));
				bko.setMemID(rs.getInt("MemID"));
				bko.setBookingDate(rs.getDate("BookingDate"));
//				bko.setBookingStart(rs.getString(4));
				int start = Integer.valueOf(rs.getString("BookingStart").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String bookingStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bko.setBookingStart(bookingStart);
				
//				bko.setBookingEnd(rs.getString(5));
				int end = Integer.valueOf(rs.getString(5));
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String bookingEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bko.setBookingEnd(bookingEnd);
				
				bko.setBoxID(rs.getInt(6));
				bko.setBookingNote(rs.getString(7));
				bko.setBookingStatus(rs.getInt(8));
				list.add(bko);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<BookingOrderVO> getBookingStatus(Integer bookingStatus) {
		List<BookingOrderVO> list = new ArrayList<>();
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_BOOKING_STATUS);
			){
			
			
			ps.setInt(1, bookingStatus);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BookingOrderVO bko = new BookingOrderVO();
				bko.setBookingID(rs.getInt(1));
				bko.setMemID(rs.getInt(2));
				bko.setBookingDate(rs.getDate(3));
//				bko.setBookingStart(rs.getString(4));
				int start = Integer.valueOf(rs.getString(4).trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String bookingStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bko.setBookingStart(bookingStart);
				
//				bko.setBookingEnd(rs.getString(5));
				int end = Integer.valueOf(rs.getString(5));
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String bookingEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bko.setBookingEnd(bookingEnd);
				
				bko.setBoxID(rs.getInt(6));
				bko.setBookingNote(rs.getString(7));
				bko.setBookingStatus(rs.getInt(8));
				list.add(bko);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<BookingOrderVO> getBookingStatusInfo(Integer bookingStatus) {
		List<BookingOrderVO> list = new ArrayList<>();
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_BOOKING_STATUS);
			){
			
			
			ps.setInt(1, bookingStatus);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				BookingOrderVO bko = new BookingOrderVO();
				bko.setBookingID(rs.getInt(1));
				bko.setMemID(rs.getInt(2));
				bko.setBookingDate(rs.getDate(3));
				bko.setBookingStart(rs.getString(4));
				bko.setBookingEnd(rs.getString(5));
				bko.setBoxID(rs.getInt(6));
				bko.setBookingNote(rs.getString(7));
				bko.setBookingStatus(rs.getInt(8));
				list.add(bko);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@Override
	public List<BookingOrderVO> getBookingOrd(Integer memID) {
		List<BookingOrderVO> list = new ArrayList<>();
		BookingOrderVO bov = new BookingOrderVO();
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_BOOKING_ORD);
			){
			
			ps.setInt(1, memID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bov.setBookingID(rs.getInt("BookingID"));
				bov.setMemID(rs.getInt("MemID"));
				bov.setBookingDate(rs.getDate("BookingDate"));
//				bov.setBookingStart(rs.getString("BookingStart"));
				int start = Integer.valueOf(rs.getString("BookingStart").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String bookingStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bov.setBookingStart(bookingStart);
				
//				bov.setBookingEnd(rs.getString("BookingEnd"));
				int end = Integer.valueOf(rs.getString("BookingEnd"));
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String bookingEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bov.setBookingEnd(bookingEnd);
				bov.setBoxID(rs.getInt("boxID"));
				bov.setBookingNote(rs.getString("BookingNote"));
				bov.setBookingStatus(rs.getInt("BookingStatus"));
				list.add(bov);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(Integer bookingID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bookingID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	

}
