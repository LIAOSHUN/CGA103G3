package com.actregis.model;

import static common_35.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActRegisJDBCDAO implements ActRegisDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = 
		"insert into actregistered (MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate) "
		+ "values (?, ?, Now(), ?, ?, ?, ?, ?, ?, Now())";
	private static final String GET_ALL_STMT = 
		"select MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate "
		+ "from actregistered order by ActID";
	private static final String GET_ONE_STMT = 
		"select MemID, ActID, RegisTime, ActNum, ActFee, FeeStatus, "
		+ "RegisStatus, ActReview, Satisfaction, ReviewDate "
		+ "from actregistered where ActID = ?";
	private static final String UPDATE = 
		"update actregistered set RegisTime=Now(), ActNum=?, ActFee=?, FeeStatus=?, "
		+ "RegisStatus=?, ActReview=?, Satisfaction=?, ReviewDate=Now() where MemID = ? and ActID = ?";
	
	
	@Override
	public void insert(ActRegisVO actRegisVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
				Class.forName(driver);
				
				pstmt.setInt(1, actRegisVO.getMemID());
				pstmt.setInt(2, actRegisVO.getActID());
				pstmt.setInt(3, actRegisVO.getActNum());
				pstmt.setInt(4, actRegisVO.getActFee());
				pstmt.setInt(5, actRegisVO.getFeeStatus());
				pstmt.setInt(6, actRegisVO.getRegisStatus());
				pstmt.setString(7, actRegisVO.getActReview());
				pstmt.setInt(8, actRegisVO.getSatisfaction());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} 		
	}

	@Override
	public void update(ActRegisVO actRegisVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
				Class.forName(driver);

				pstmt.setInt(1, actRegisVO.getActNum());
				pstmt.setInt(2, actRegisVO.getActFee());
				pstmt.setInt(3, actRegisVO.getFeeStatus());
				pstmt.setInt(4, actRegisVO.getRegisStatus());
				pstmt.setString(5, actRegisVO.getActReview());
				pstmt.setInt(6, actRegisVO.getSatisfaction());
				pstmt.setInt(7, actRegisVO.getMemID());
				pstmt.setInt(8, actRegisVO.getActID());

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			}
		
	}

	@Override
	public List<ActRegisVO> findByPrimaryKey(Integer actID) {
		List<ActRegisVO> listRegis = new ArrayList<ActRegisVO>();
		ActRegisVO actRegisVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
				) {
				Class.forName(driver);

			pstmt.setInt(1, actID);
			ResultSet rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// actRegisVO 也稱為 Domain objects
				actRegisVO = new ActRegisVO();
				actRegisVO.setMemID(rs.getInt("memID"));
				actRegisVO.setActID(rs.getInt("actID"));
				actRegisVO.setRegisTime(rs.getTimestamp("regisTime"));
				actRegisVO.setActNum(rs.getInt("actNum"));
				actRegisVO.setActFee(rs.getInt("actFee"));
				actRegisVO.setFeeStatus(rs.getInt("feeStatus"));
				actRegisVO.setRegisStatus(rs.getInt("regisStatus"));
				actRegisVO.setActReview(rs.getString("actReview"));
				actRegisVO.setSatisfaction(rs.getInt("satisfaction"));
				actRegisVO.setReviewDate(rs.getDate("reviewDate"));	
				listRegis.add(actRegisVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}
		return listRegis;
	}

	@Override
	public List<ActRegisVO> getAll() {
		List<ActRegisVO> list = new ArrayList<ActRegisVO>();
		ActRegisVO actRegisVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
				) {
				Class.forName(driver);
				ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				actRegisVO = new ActRegisVO();
				actRegisVO.setMemID(rs.getInt("memID"));
				actRegisVO.setActID(rs.getInt("actID"));
				actRegisVO.setRegisTime(rs.getTimestamp("regisTime"));
				actRegisVO.setActNum(rs.getInt("actNum"));
				actRegisVO.setActFee(rs.getInt("actFee"));
				actRegisVO.setFeeStatus(rs.getInt("feeStatus"));
				actRegisVO.setRegisStatus(rs.getInt("regisStatus"));
				actRegisVO.setActReview(rs.getString("actReview"));
				actRegisVO.setSatisfaction(rs.getInt("satisfaction"));
				actRegisVO.setReviewDate(rs.getDate("reviewDate"));
				list.add(actRegisVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}
		return list;
	}
	
	public static void main(String[] args) {

		ActRegisJDBCDAO dao = new ActRegisJDBCDAO();

		// 新增
		ActRegisVO actRegisVO1 = new ActRegisVO();
		actRegisVO1.setMemID(11001);
		actRegisVO1.setActID(61001);
		actRegisVO1.setActNum(1);
		actRegisVO1.setActFee(100);
		actRegisVO1.setFeeStatus(1);
		actRegisVO1.setRegisStatus(0);
		actRegisVO1.setActReview("很棒");
		actRegisVO1.setSatisfaction(3);
		dao.insert(actRegisVO1);

		// 修改
		ActRegisVO actRegisVO2 = new ActRegisVO();
		actRegisVO2.setActNum(2);
		actRegisVO2.setActFee(200);
		actRegisVO2.setFeeStatus(1);
		actRegisVO2.setRegisStatus(0);
		actRegisVO2.setActReview("很棒");
		actRegisVO2.setSatisfaction(3);
		actRegisVO2.setMemID(11001);
		actRegisVO2.setActID(61001);
		dao.update(actRegisVO2);

		// 查詢 報名活動的所有會員
		List<ActRegisVO> listRegis = dao.findByPrimaryKey(61001);
		for (ActRegisVO aActRegismem : listRegis) {
			System.out.print(aActRegismem.getMemID() + ", ");
			System.out.print(aActRegismem.getActID() + ", ");
			System.out.print(aActRegismem.getRegisTime() + ", ");
			System.out.print(aActRegismem.getActNum() + ", ");
			System.out.print(aActRegismem.getActFee() + ", ");
			System.out.print(aActRegismem.getFeeStatus() + ", ");
			System.out.print(aActRegismem.getRegisStatus() + ", ");
			System.out.print(aActRegismem.getActReview() + ", ");
			System.out.print(aActRegismem.getSatisfaction() + ", ");
			System.out.println(aActRegismem.getReviewDate());
			System.out.println("---------------------");
		}
		// 查詢
		List<ActRegisVO> list = dao.getAll();
		for (ActRegisVO aActRegis : list) {
			System.out.print(aActRegis.getMemID() + ", ");
			System.out.print(aActRegis.getActID() + ", ");
			System.out.print(aActRegis.getRegisTime() + ", ");
			System.out.print(aActRegis.getActNum() + ", ");
			System.out.print(aActRegis.getActFee() + ", ");
			System.out.print(aActRegis.getFeeStatus() + ", ");
			System.out.print(aActRegis.getRegisStatus() + ", ");
			System.out.print(aActRegis.getActReview() + ", ");
			System.out.print(aActRegis.getSatisfaction() + ", ");
			System.out.print(aActRegis.getReviewDate());
			System.out.println();
		}
	}
}
