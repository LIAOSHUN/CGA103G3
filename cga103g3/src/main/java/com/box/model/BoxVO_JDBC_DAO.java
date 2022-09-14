package com.box.model;

import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoxVO_JDBC_DAO implements Box_interface{
	
		//管理員可以新增包廂
		private static final String INSERT_STMT = 
				"insert into box (StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription, BoxImg) "
				+ "value (?, ?, ?, ?, ?, ?);";
		
		//管理員可以修改包廂資料
		private static final String UPDATE_STMT = 
				"update box set BoxTypeID = ?, BoxCapcity = ?, BoxPrice = ?, BoxDescription = ?, BoxImg = ? "
				+ "where BoxID = ?";
		
		//管理員可以指定門市包廂
		private static final String GET_ONE_STMT = 
				"select BoxID, StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription from box "
				+ "where StoreID = ?";
		
		//管理員可以查看所有包廂
		private static final String GET_ALL_STMT = 
				"select * from box";
		
		private static final String GET_ONE_BOX = 
				"select BoxID, StoreID, BoxTypeID, BoxCapcity, BoxPrice, BoxDescription from box "
				+ "where boxID = ?";

		@Override
		public void insert(BoxVO boxVO) {
			try (
					Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement ps = con.prepareStatement(INSERT_STMT);
				){
				ps.setInt(1, boxVO.getStoreID());
				ps.setInt(2, boxVO.getBoxTypeID());
				ps.setInt(3, boxVO.getBoxCapcity());
				ps.setInt(4, boxVO.getBoxPrice());
				ps.setString(5, boxVO.getBoxDescription());
				ps.setBytes(6, boxVO.getBoxImg());
				
				ps.execute();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void update(BoxVO boxVO) {
			try (
					Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement ps = con.prepareStatement(UPDATE_STMT)
				){
				
				ps.setInt(1, boxVO.getBoxTypeID());
				ps.setInt(2, boxVO.getBoxCapcity());
				ps.setInt(3, boxVO.getBoxPrice());
				ps.setString(4, boxVO.getBoxDescription());
				ps.setBytes(5, boxVO.getBoxImg());
				ps.setInt(6, boxVO.getBoxID());
				
				ps.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void delete(Integer boxVO) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<BoxVO> findPK(Integer boxVO) {
			List<BoxVO> list = new ArrayList<>();
			try (
					Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement ps = con.prepareStatement(GET_ONE_STMT);
				){
				
				ps.setInt(1, boxVO);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					BoxVO bv = new BoxVO();
					bv.setBoxID(rs.getInt("BoxID"));
					bv.setStoreID(rs.getInt("StoreID"));
					bv.setBoxTypeID(rs.getInt("BoxTypeID"));
					bv.setBoxCapcity(rs.getInt("BoxCapcity"));
					bv.setBoxPrice(rs.getInt("BoxPrice"));
					bv.setBoxDescription(rs.getString("BoxDescription"));
					list.add(bv);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		@Override
		public List<BoxVO> getAll() {
			List<BoxVO> list = new ArrayList<>();
			
			try (
					Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement ps = con.prepareStatement(GET_ALL_STMT);
					ResultSet rs = ps.executeQuery();
				){
				
				while (rs.next()) {
					BoxVO bto = new BoxVO();
					bto.setBoxID(rs.getInt(1));
					bto.setStoreID(rs.getInt(2));
					bto.setBoxTypeID(rs.getInt(3));
					bto.setBoxCapcity(rs.getInt(4));
					bto.setBoxPrice(rs.getInt(5));
					bto.setBoxDescription(rs.getString(6));
					
					list.add(bto);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		@Override
		public BoxVO getOneBox(Integer boxID) {
			
			PreparedStatement ps = null;
			BoxVO boxVO = null;
			ResultSet rs = null;
			
			try {
				
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				ps = con.prepareStatement(GET_ONE_BOX);
				
				ps.setInt(1, boxID);
				
				rs = ps.executeQuery();
				
				while (rs.next()) {
					boxVO = new BoxVO();
					boxVO.setBoxID(rs.getInt("BoxID"));
					boxVO.setStoreID(rs.getInt("StoreID"));
					boxVO.setBoxTypeID(rs.getInt("BoxTypeID"));
					boxVO.setBoxCapcity(rs.getInt("BoxCapcity"));
					boxVO.setBoxPrice(rs.getInt("BoxPrice"));
					boxVO.setBoxDescription(rs.getString("BoxDescription"));
				} 
			}catch (Exception e) {
					e.printStackTrace(System.err);
				}
			return boxVO;
			}

		public static void main(String[] args) {
			BoxVO_JDBC_DAO dao = new BoxVO_JDBC_DAO();
			BoxVO bv = new BoxVO();
			
			//insert
			bv.setStoreID(51001);
			bv.setBoxTypeID(0);
			bv.setBoxCapcity(8);
			bv.setBoxPrice(1200);
			bv.setBoxDescription("測試");
			byte[] img;
			try {
				img = getImg("image01.jpg");
				bv.setBoxImg(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			dao.insert(bv);
			
			//update
//			bv.setBoxTypeID(1);
//			bv.setBoxCapcity(8);
//			bv.setBoxPrice(900);
//			bv.setBoxDescription("容納8人以下的中包廂");
//			bv.setBoxImg(null);
//			bv.setBoxID(53005);
//			dao.update(bv);
			
			//find pk
//			List<BoxVO> bv1 = dao.findPK(51001);
//			for (BoxVO bto1 : bv1) {
//			System.out.println("BoxID: " + bto1.getBoxID());
//			System.out.println("StoreID: " + bto1.getStoreID());
//			System.out.println("BoxTypeID: " + bto1.getBoxTypeID());
//			System.out.println("BoxCapcity: " + bto1.getBoxCapcity());
//			System.out.println("BoxPrice: " + bto1.getBoxPrice());
//			System.out.println("BoxDescription: " + bto1.getBoxDescription());
//			System.out.println("=======================================");
			
			//getOneBox
//			BoxVO bvo = dao.getOneBox(53015);
//			System.out.println("BoxID: " + bvo.getBoxID());
//			System.out.println("StoreID: " + bvo.getStoreID());
//			System.out.println("BoxTypeID: " + bvo.getBoxTypeID());
//			System.out.println("BoxCapcity: " + bvo.getBoxCapcity());
//			System.out.println("BoxPrice: " + bvo.getBoxPrice());
//			System.out.println("BoxDescription: " + bvo.getBoxDescription());
//			System.out.println("=======================================");
			
			//getall
//			for (BoxVO bto1 : dao.getAll()) {
//				System.out.println("BoxID: " + bto1.getBoxID());
//				System.out.println("StoreID: " + bto1.getStoreID());
//				System.out.println("BoxTypeID: " + bto1.getBoxTypeID());
//				System.out.println("BoxCapcity: " + bto1.getBoxCapcity());
//				System.out.println("BoxPrice: " + bto1.getBoxPrice());
//				System.out.println("BoxDescription: " + bto1.getBoxDescription());
//				System.out.println("==========================================");
			}
		public static byte[] getImg(String path) throws IOException {
			FileInputStream fis = new FileInputStream(path);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			return buffer;
		}
		}


