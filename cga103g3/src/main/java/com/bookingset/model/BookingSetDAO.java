package com.bookingset.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BookingSetDAO implements BookingSet_interface {

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

	// 查詢所有包廂資訊 + 門市店休日
	private static final String GET_ALL_BOX_STORE = 
			"select b.BoxID, b.BoxBkStart, b.BoxBkEnd, b.BoxDescription, b.BoxPrice, s.StoreID, s.StoreOff "
			+ "from box b join store s on b.storeID = s.storeID";
	
	// 查詢所有包廂 + 訂位訂單
	private static final String GET_BOX_AND_BOOKING = 
			"select b.BoxID, b.BoxBkStart, b.BoxBkEnd, bok.BookingDate, bok.BookingStart, bok.BookingEnd, bok.BookingStatus "
			+ "from box b left join bookingorder bok on b.boxID = bok.boxID "
			+ "where b.BoxID = ?";
	
	// 查詢所有包廂資訊
	private static final String GET_ALL_BOX = 
			"select BoxID, BoxBkStart, BoxBkEnd from box";

	// 查詢未結束訂位訂單時間
	private static final String GET_BOOKING_STATUS = "select BookingDate, BookingStart, BookingEnd, BoxID "
			+ "from bookingorder " + "where BookingStatus = ? " + "order by BookingDate";
		
	//管理員可以查詢門市訂位訂單
	private static final String GET_STORE_BOOKING_ORD = 
			"select bok.BookingID, bok.MemID, bok.BookingDate, bok.BookingStart, bok.BookingEnd, b.StoreID, bok.BoxID, bok.BookingNote, bok.BookingStatus "
			+ "from bookingorder bok join box b on bok.BoxID = b.BoxID "
			+ "where b.StoreID = ? and bok.BookingStatus = 1";
	
	private static final String GET_BOOKING_INFO = 
			"select b.BoxID, b.BoxBkStart, b.BoxBkEnd, bok.BookingDate, bok.BookingStart, bok.BookingEnd "
			+ "from box b left join bookingorder bok on b.boxID = bok.boxID "
			+ "order by BoxID;";

	@Override
	public List<BookingSetVO> getAllBoxAndStore() {
		List<BookingSetVO> boxList = new ArrayList<BookingSetVO>();
		BookingSetVO bokSetVO = null;

		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_BOX_STORE);
				ResultSet rs = ps.executeQuery();
			) {
			
			while (rs.next()) {
				bokSetVO = new BookingSetVO();
				bokSetVO.setBoxID(rs.getInt("BoxID"));
				bokSetVO.setBoxBkStart(rs.getString("BoxBkStart"));
				bokSetVO.setBoxBkEnd(rs.getString("BoxBkEnd"));
				bokSetVO.setBoxDescription(rs.getString("BoxDescription"));
				bokSetVO.setBoxPrice(rs.getInt("BoxPrice"));
				bokSetVO.setStoreID(rs.getInt("StoreID"));
				bokSetVO.setStoreOff(rs.getString("StoreOff"));

				boxList.add(bokSetVO);
			}
			return boxList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public List<BookingSetVO> getBoxAndBooking(Integer boxID) {
		List<BookingSetVO> boxList = new ArrayList<BookingSetVO>();
		BookingSetVO bokSetVO = null;
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_BOX_AND_BOOKING);
			) {
			
			ps.setInt(1, boxID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bokSetVO = new BookingSetVO();
				bokSetVO.setBoxID(rs.getInt("BoxID"));
				bokSetVO.setBoxBkStart(rs.getString("BoxBkStart"));
				bokSetVO.setBoxBkEnd(rs.getString("BoxBkEnd"));
				bokSetVO.setBookingDate(rs.getDate("BookingDate"));
				bokSetVO.setBookingStart(rs.getString("BookingStart"));
				bokSetVO.setBookingEnd(rs.getString("BookingEnd"));
				bokSetVO.setBookingStatus(rs.getInt("BookingStatus"));
				boxList.add(bokSetVO);
			}
			return boxList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public List<BookingSetVO> getBoxBktime(Integer boxID, Date bookingDate) {
		List<BookingSetVO> boxList = new ArrayList<BookingSetVO>();
		BookingSetVO bokSetVO = null;
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_BOX_AND_BOOKING);
			) {
			
			ps.setInt(1, boxID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bokSetVO = new BookingSetVO();
				bokSetVO.setBoxID(rs.getInt("BoxID"));
				bokSetVO.setBoxBkStart(rs.getString("BoxBkStart"));
				bokSetVO.setBoxBkEnd(rs.getString("BoxBkEnd"));
				bokSetVO.setBookingDate(rs.getDate("BookingDate"));
				bokSetVO.setBookingStart(rs.getString("BookingStart"));
				bokSetVO.setBookingEnd(rs.getString("BookingEnd"));
				bokSetVO.setBookingStatus(rs.getInt("BookingStatus"));
				boxList.add(bokSetVO);
			}
			return boxList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public List<BookingSetVO> getAllBox() {
		List<BookingSetVO> boxList = new ArrayList<BookingSetVO>();
		BookingSetVO bokSetVO = null;
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_ALL_BOX);
				ResultSet rs = ps.executeQuery();
			) {
			
			while (rs.next()) {
				bokSetVO = new BookingSetVO();
				bokSetVO.setBoxNO(rs.getInt("BoxID"));
				bokSetVO.setBoxBkStart(rs.getString("BoxBkStart"));
				bokSetVO.setBoxBkEnd(rs.getString("BoxBkEnd"));

				boxList.add(bokSetVO);
			}
			return boxList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BookingSetVO> getAllBooking() {
		List<BookingSetVO> bookingList = new ArrayList<BookingSetVO>();
		BookingSetVO bokSetVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try 
//		(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//				PreparedStatement ps = connection.prepareStatement(GET_BOOKING_STATUS);
////				ResultSet rs = ps.executeQuery();
//		) 
		{

			con = ds.getConnection();
			ps = con.prepareStatement(GET_BOOKING_STATUS);

			ps.setInt(1, 1);
			rs = ps.executeQuery();
//			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bokSetVO = new BookingSetVO();
				bokSetVO.setBookingDate(rs.getDate("BookingDate"));
				bokSetVO.setBookingStart(rs.getString("BookingStart"));
				bokSetVO.setBookingEnd(rs.getString("BookingEnd"));
				bokSetVO.setBoxID(rs.getInt("BoxID"));

				bookingList.add(bokSetVO);
			}
			return bookingList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	@Override
	public List<BookingSetVO> getBookingTime() {
		
		List<BookingSetVO> bookingTimeList = new ArrayList<BookingSetVO>();
		BookingSetVO bokSetVO = null;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			ps = con.prepareStatement(GET_BOOKING_INFO);
			rs = ps.executeQuery();

			while (rs.next()) {
				bokSetVO = new BookingSetVO();
				bokSetVO.setBoxID(rs.getInt("BoxID"));
//				bokSetVO.setBoxBkStart(rs.getString("BoxBkStart"));
//				bokSetVO.setBoxBkEnd(rs.getString("BoxBkEnd"));
				bokSetVO.setBookingDate(rs.getDate("BookingDate"));
				bokSetVO.setBookingStart(rs.getString("BookingStart"));
				bokSetVO.setBookingEnd(rs.getString("BookingEnd"));

				bookingTimeList.add(bokSetVO);
			}
			return bookingTimeList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<BookingSetVO> getStoreBookingOrder(Integer storeID) {
		List<BookingSetVO> list = new ArrayList<>();
		
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_STORE_BOOKING_ORD);
			){
			
			ps.setInt(1, storeID);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				BookingSetVO bokSetVO = new BookingSetVO();
				bokSetVO.setBookingID(rs.getInt("BookingID"));
				bokSetVO.setMemID(rs.getInt("MemID"));
				bokSetVO.setBookingDate(rs.getDate("BookingDate"));
				int start = Integer.valueOf(rs.getString("BookingStart").trim());
				for(int i = 0;i <= start; i++) {
					if(i == start) {
						start = i;
					}
				}
				String bookingStart = ((start < 10)? ("0"+ start) : start) + ":00";
				bokSetVO.setBookingStart(bookingStart);
				
				int end = Integer.valueOf(rs.getString("BookingEnd"));
				for(int i = 0;i <= end; i++) {
					if(i == end) {
						end = i;
					}
				}
				String bookingEnd = ((end < 10)? ("0"+ end) : end) + ":00";
				bokSetVO.setBookingEnd(bookingEnd);
				bokSetVO.setStoreID(rs.getInt("storeID"));
				bokSetVO.setBoxID(rs.getInt("boxID"));
				bokSetVO.setBookingNote(rs.getString("BookingNote"));
				bokSetVO.setBookingStatus(rs.getInt("BookingStatus"));
				list.add(bokSetVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}







}
