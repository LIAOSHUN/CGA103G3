package com.coupontype.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponTypeJDBCDAO implements CouponTypeDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "asd12377";
	
//	-- 新增資料
	private static final String Insert=
		"insert into coupontype(CoupName, CoupDiscount, CoupQuantity, CoupDesc, CoupDuration, CoupStart, CoupEnd) "
		+ "values "
		+ "(?, ?, ?, ?, ?, ?, ?)";
//	-- 更改優惠券內容
	private static final String Update=
		"update coupontype "
		+ "set CoupName=?, CoupDiscount=?, CoupQuantity=?, CoupDesc=?, CoupDuration=?, CoupStart=?, CoupEnd=? "
		+ "where CoupTypeNo=?";
//	-- 找出某種類型的優惠券
	private static final String FindCouponTypeByType=
		"select CoupTypeNo, CoupName, CoupDiscount, CoupQuantity, CoupDesc, CoupDuration, CoupStart, CoupEnd "
		+ "from coupontype "
		+ "where CoupTypeNo=?";
//	-- 找出所有優惠券
	private static final String GetAll=
		"select CoupTypeNo, CoupName, CoupDiscount, CoupQuantity, CoupDesc, CoupDuration, CoupStart, CoupEnd "
		+ "from coupontype "
		+ "order by CoupTypeNo";
//	-- 刪除某張優惠券
	private static final String Delete=
		"delete from coupontype where CoupTypeNo = ?";
	
	@Override
	public void insert(CouponTypeVO couponTypeVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Insert)){
			ps.setString(1, couponTypeVO.getCoupName());
			ps.setInt(2, couponTypeVO.getCoupDiscount());
			ps.setInt(3, couponTypeVO.getCoupQuantity());
			ps.setString(4, couponTypeVO.getCoupDesc());
			ps.setInt(5, couponTypeVO.getCoupDuration());
			ps.setDate(6, couponTypeVO.getCoupStart());
			ps.setDate(7, couponTypeVO.getCoupEnd());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(CouponTypeVO couponTypeVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Update)){
			ps.setString(1, couponTypeVO.getCoupName());
			ps.setInt(2, couponTypeVO.getCoupDiscount());
			ps.setInt(3, couponTypeVO.getCoupQuantity());
			ps.setString(4, couponTypeVO.getCoupDesc());
			ps.setInt(5, couponTypeVO.getCoupDuration());
			ps.setDate(6, couponTypeVO.getCoupStart());
			ps.setDate(7, couponTypeVO.getCoupEnd());
			ps.setInt(8, couponTypeVO.getCoupTypeNo());
			
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public CouponTypeVO findCouponTypeByType(Integer coupTypeNo) {
		CouponTypeVO couponTypeVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(FindCouponTypeByType)){
			ps.setInt(1, coupTypeNo);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				couponTypeVO.setCoupName(rs.getString("CoupName"));
				couponTypeVO.setCoupDiscount(rs.getInt("CoupDiscount"));
				couponTypeVO.setCoupQuantity(rs.getInt("CoupQuantity"));
				couponTypeVO.setCoupDesc(rs.getString("CoupDesc"));
				couponTypeVO.setCoupDuration(rs.getInt("CoupDuration"));
				couponTypeVO.setCoupStart(rs.getDate("CoupStart"));
				couponTypeVO.setCoupEnd(rs.getDate("CoupEnd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return couponTypeVO;
	}
	@Override
	public List<CouponTypeVO> getAll() {
		List<CouponTypeVO> list = new ArrayList<CouponTypeVO>();
		CouponTypeVO couponTypeVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GetAll)){
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCoupTypeNo(rs.getInt("CoupTypeNo"));
				couponTypeVO.setCoupName(rs.getString("CoupName"));
				couponTypeVO.setCoupDiscount(rs.getInt("CoupDiscount"));
				couponTypeVO.setCoupQuantity(rs.getInt("CoupQuantity"));
				couponTypeVO.setCoupDesc(rs.getString("CoupDesc"));
				couponTypeVO.setCoupDuration(rs.getInt("CoupDuration"));
				couponTypeVO.setCoupStart(rs.getDate("CoupStart"));
				couponTypeVO.setCoupEnd(rs.getDate("CoupEnd"));
				list.add(couponTypeVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public void delete(Integer coupTypeNo) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(Delete)){
			ps.setInt(1, coupTypeNo);
		
			int rowcount = ps.executeUpdate();
			System.out.println(rowcount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		CouponTypeJDBCDAO dao = new CouponTypeJDBCDAO();
		
//		-- 新增資料
//		CouponTypeVO couponTypeVO1 = new CouponTypeVO();
//		couponTypeVO1.setCoupName("工程師節");
//		couponTypeVO1.setCoupDiscount(30);
//		couponTypeVO1.setCoupQuantity(5);
//		couponTypeVO1.setCoupDesc("宜蘭人特有");
//		couponTypeVO1.setCoupDuration(15);
//		couponTypeVO1.setCoupStart(java.sql.Date.valueOf("2022-11-01"));
//		couponTypeVO1.setCoupEnd(java.sql.Date.valueOf("2022-12-01"));
//		dao.insert(couponTypeVO1);
		
//		-- 更改優惠券內容
//		CouponTypeVO couponTypeVO2 = new CouponTypeVO();
//		couponTypeVO2.setCoupName("童玩節");
//		couponTypeVO2.setCoupDiscount(30);
//		couponTypeVO2.setCoupQuantity(5);
//		couponTypeVO2.setCoupDesc("宜蘭人");
//		couponTypeVO2.setCoupDuration(15);
//		couponTypeVO2.setCoupStart(java.sql.Date.valueOf("2022-10-01"));
//		couponTypeVO2.setCoupEnd(java.sql.Date.valueOf("2022-11-01"));
//		couponTypeVO2.setCoupTypeNo(26005);
//		dao.update(couponTypeVO2);
		
//		-- 找出某種類型的優惠券
//		CouponTypeVO CouponTypeVO3 = dao.findCouponTypeByType(26005);
//		System.out.println(CouponTypeVO3.getCoupTypeNo() + ",");
//		System.out.println(CouponTypeVO3.getCoupName() + ",");
//		System.out.println(CouponTypeVO3.getCoupDiscount() + ",");
//		System.out.println(CouponTypeVO3.getCoupQuantity() + ",");
//		System.out.println(CouponTypeVO3.getCoupDesc() + ",");
//		System.out.println(CouponTypeVO3.getCoupDuration() + ",");
//		System.out.println(CouponTypeVO3.getCoupStart() + ",");
//		System.out.println(CouponTypeVO3.getCoupEnd());
//		System.out.println();
		
		
//		-- 找出所有優惠券
//	List<CouponTypeVO> list = dao.getAll();
//		for(CouponTypeVO c : list) {
//		System.out.println(c.getCoupTypeNo() + ",");
//		System.out.println(c.getCoupName() + ",");
//		System.out.println(c.getCoupDiscount() + ",");
//		System.out.println(c.getCoupQuantity() + ",");
//		System.out.println(c.getCoupDesc() + ",");
//		System.out.println(c.getCoupDuration() + ",");
//		System.out.println(c.getCoupStart() + ",");
//		System.out.println(c.getCoupEnd());
//		System.out.println();
//		}
		
//		-- 刪除某張優惠券
//		dao.delete(26011);
//		-- 刪除某張優惠券
//		dao.delete(26011);
		
//		-- 刪除某張優惠券
//		dao.delete(26011);
	}

	

}
