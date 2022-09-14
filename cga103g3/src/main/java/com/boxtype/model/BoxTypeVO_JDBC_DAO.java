package com.boxtype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.boxsetting.model.BoxSettingVO;

import static common.Common.*;

public class BoxTypeVO_JDBC_DAO implements BoxType_interface{
	//管理員可以新增包廂類型
	private static final String INSERT_STMT = 
			"insert into boxtype (boxtypeid, boxname) "
			+ "value (?, ?);";
	
	//管理員可以刪除資料列
	private static final String DELETE_STMT = 
			"delete from boxtype "
			+ "where boxtypeid = ?";
	
	//管理員可以修改包廂名稱
	private static final String UPDATE_STMT = 
			"update boxtype set boxname = ? "
			+ "where boxtypeid = ?";
	
	//管理員可以查詢指定id資料
	private static final String GET_ONE_STMT = 
			"select boxtypeid, boxname "
			+ "from boxtype "
			+ "where boxtypeid = ?";
	
	//�޲z���i�H�d�ߩҦ����
	private static final String GET_ALL_STMT = 
			"select * from boxtype";
	
	@Override
	public void insert(BoxTypeVO boxTypeVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			){
			
			pstmt.setInt(1, boxTypeVO.getBoxTypeID());
			pstmt.setString(2, boxTypeVO.getBoxName());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer boxTypeID) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE_STMT);
			){
			
			pstmt.setInt(1, boxTypeID);
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(BoxTypeVO boxTypeVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE_STMT);
			){
			
			pstmt.setString(1, boxTypeVO.getBoxName());
			pstmt.setInt(2, boxTypeVO.getBoxTypeID());
			
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BoxTypeVO findPK(Integer boxTypeID) {
		BoxTypeVO btv = null;
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
			){
			
			pstmt.setInt(1, boxTypeID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				btv = new BoxTypeVO();
				btv.setBoxTypeID(rs.getInt("BoxTypeID"));
				btv.setBoxName(rs.getString("BoxName"));
			}
			return btv;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BoxTypeVO> getAll() {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
				ResultSet rs = pstmt.executeQuery();
			){
			List<BoxTypeVO> list = new ArrayList<>();
			while (rs.next()) {
				BoxTypeVO btv = new BoxTypeVO();
				btv.setBoxTypeID(rs.getInt(1));
				btv.setBoxName(rs.getString(2));
				list.add(btv);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		BoxTypeVO_JDBC_DAO dao = new BoxTypeVO_JDBC_DAO();
		BoxTypeVO btv = new BoxTypeVO();
		
		//insert
//		btv.setBoxTypeID(3);
//		btv.setBoxName("vip包廂");
//		dao.insert(btv);
		
		//delete
//		btv.setBoxTypeID(3);
//		dao.delete(btv);
		
		//update
//		btv.setBoxName("vvip包廂");
//		btv.setBoxTypeID(3);
//		dao.update(btv);
		
		//get pk
//		BoxTypeVO btv1 = dao.findPK(0);
//		System.out.println("包廂類型編號: " + btv1.getBoxTypeID());
//		System.out.println("包廂類型名稱: " + btv1.getBoxName());
		
		//get all
		for (BoxTypeVO btv2 : dao.getAll()) {
			System.out.println("包廂類型編號: " + btv2.getBoxTypeID());
			System.out.println("包廂類型名稱: " + btv2.getBoxName());
			System.out.println("===================================");
		}
	}

}
