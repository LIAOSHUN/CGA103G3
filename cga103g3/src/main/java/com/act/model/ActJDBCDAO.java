package com.act.model;

import java.sql.*;
import java.util.*;

import com.actimg.model.ActImgVO;

import static common_35.Common.*;

public class ActJDBCDAO implements ActDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = 
		"insert into activity (StoreID, ActTitle, ActDescription, ActTimeStart, ActTimeEnd, "
		+ "ActDate, RegisMax, ActFee, ActRegistration, ActStatus) "
		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"select ActID, StoreID, ActTitle, ActDescription, ActTimeStart, ActTimeEnd, "
		+ "ActDate, RegisMax, ActFee, ActRegistration, ActStatus "
		+ "from activity order by ActID";
	private static final String GET_ONE_STMT = 
		"select ActID, StoreID, ActTitle, ActDescription, ActTimeStart, ActTimeEnd, "
		+ "ActDate, RegisMax, ActFee, ActRegistration, ActStatus "
		+ "from activity where ActID = ?";
	private static final String UPDATE = 
		"update activity set StoreID=?, ActTitle=?, ActDescription=?, ActTimeStart=?, ActTimeEnd=?, "
		+ "ActDate=?, RegisMax=?, ActFee=?, ActRegistration=?, ActStatus=? where ActID = ?";
	private static final String GET_IMGS_BYACTID_STMT = "SELECT ActImgID, ActID, ActImgFile "
			+ "FROM actimg where ActID = ? order by ActImgID ";
	
	
	@Override
	public void insert(ActVO actVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
			Class.forName(driver);
			
			pstmt.setInt(1, actVO.getStoreID());
			pstmt.setString(2, actVO.getActTitle());
			pstmt.setString(3, actVO.getActDescription());
			pstmt.setTimestamp(4, actVO.getActTimeStart());
			pstmt.setTimestamp(5, actVO.getActTimeEnd());
			pstmt.setTimestamp(6, actVO.getActDate());
			pstmt.setInt(7, actVO.getRegisMax());
			pstmt.setInt(8, actVO.getActFee());
			pstmt.setInt(9, actVO.getActRegistration());
			pstmt.setInt(10, actVO.getActStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} 		
	}

	@Override
	public void update(ActVO actVO) {
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement pstmt = con.prepareStatement(UPDATE)) {
			Class.forName(driver);

			pstmt.setInt(1, actVO.getStoreID());
			pstmt.setString(2, actVO.getActTitle());
			pstmt.setString(3, actVO.getActDescription());
			pstmt.setTimestamp(4, actVO.getActTimeStart());
			pstmt.setTimestamp(5, actVO.getActTimeEnd());
			pstmt.setTimestamp(6, actVO.getActDate());
			pstmt.setInt(7, actVO.getRegisMax());
			pstmt.setInt(8, actVO.getActFee());
			pstmt.setInt(9, actVO.getActRegistration());
			pstmt.setInt(10, actVO.getActStatus());
			pstmt.setInt(11, actVO.getActID());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
	}

	@Override
	public ActVO findByPrimaryKey(Integer actID) {
		ActVO actVO = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);
				) {
				Class.forName(driver);

			pstmt.setInt(1, actID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// actVo 也稱為 Domain objects
				actVO = new ActVO();
				actVO.setActID(rs.getInt("actID"));
				actVO.setStoreID(rs.getInt("storeID"));
				actVO.setActTitle(rs.getString("actTitle"));
				actVO.setActDescription(rs.getString("actDescription"));
				actVO.setActTimeStart(rs.getTimestamp("actTimeStart"));
				actVO.setActTimeEnd(rs.getTimestamp("actTimeEnd"));
				actVO.setActDate(rs.getTimestamp("actDate"));
				actVO.setRegisMax(rs.getInt("regisMax"));				
				actVO.setActFee(rs.getInt("actFee"));
				actVO.setActRegistration(rs.getInt("actRegistration"));
				actVO.setActStatus(rs.getInt("actStatus"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return actVO;
	}

	@Override
	public List<ActVO> getAll() {
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;

		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
				) {
				Class.forName(driver);
				ResultSet rs = pstmt.executeQuery();
			

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				actVO = new ActVO();
				actVO.setActID(rs.getInt("actID"));
				actVO.setStoreID(rs.getInt("storeID"));
				actVO.setActTitle(rs.getString("actTitle"));
				actVO.setActDescription(rs.getString("actDescription"));
				actVO.setActTimeStart(rs.getTimestamp("actTimeStart"));
				actVO.setActTimeEnd(rs.getTimestamp("actTimeEnd"));
				actVO.setActDate(rs.getTimestamp("actDate"));
				actVO.setRegisMax(rs.getInt("regisMax"));
				actVO.setActFee(rs.getInt("actFee"));
				actVO.setActRegistration(rs.getInt("actRegistration"));
				actVO.setActStatus(rs.getInt("actStatus"));
				list.add(actVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}
		return list;
	}
	
	@Override
	public Set<ActImgVO> getImgsByAct(Integer actID) {
		Set<ActImgVO> set = new HashSet<ActImgVO>();
		ActImgVO actImgVO = null;
		try (Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement pstmt = con.prepareStatement(GET_IMGS_BYACTID_STMT)) {
			Class.forName(driver);
			
			pstmt.setInt(1, actID);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				actImgVO = new ActImgVO();
				actImgVO.setActImgID(rs.getInt("actImgID"));
				actImgVO.setActID(rs.getInt("actID"));
				actImgVO.setActImgFile(rs.getBytes("actImgFile"));
				set.add(actImgVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}		
		return set;
	}

	@Override
	public void insertWithActImgs(ActVO actVO, List<ActImgVO> list) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {

		ActJDBCDAO dao = new ActJDBCDAO();

		// 新增
		ActVO actVO1 = new ActVO();
		actVO1.setStoreID(51001);
		actVO1.setActTitle("結伴玩桌遊testtt");
		actVO1.setActDescription("引入新桌遊「I.A.裝置譯述家」，邀請各位一起體驗");
		actVO1.setActTimeStart(Timestamp.valueOf("2022-10-08 10:00:00"));
		actVO1.setActTimeEnd(Timestamp.valueOf("2022-10-20 21:00:00"));
		actVO1.setActDate(Timestamp.valueOf("2022-10-22 14:00:00"));
		actVO1.setRegisMax(6);
		actVO1.setActFee(100);
		actVO1.setActRegistration(0);
		actVO1.setActStatus(1);
		dao.insert(actVO1);

		// 修改
		ActVO actVO2 = new ActVO();
		actVO2.setStoreID(51001);
		actVO2.setActTitle("結伴玩桌遊");
		actVO2.setActDescription("引入新桌遊「I.A.裝置譯述家」~~邀請各位一起體驗");
		actVO2.setActTimeStart(Timestamp.valueOf("2022-10-08 10:00:00"));
		actVO2.setActTimeEnd(Timestamp.valueOf("2022-10-20 21:00:00"));
		actVO2.setActDate(Timestamp.valueOf("2022-10-22 14:00:00"));
		actVO2.setRegisMax(6);
		actVO2.setActFee(100);
		actVO2.setActRegistration(0);
		actVO2.setActStatus(1);
		actVO2.setActID(61001);
		dao.update(actVO2);

		// 查詢 Primary Key
		ActVO actVO3 = dao.findByPrimaryKey(61001);
		System.out.print(actVO3.getActID() + ", ");
		System.out.print(actVO3.getStoreID() + ", ");
		System.out.print(actVO3.getActTitle() + ", ");
		System.out.print(actVO3.getActDescription() + ", ");
		System.out.print(actVO3.getActTimeStart() + ", ");
		System.out.print(actVO3.getActTimeEnd() + ", ");
		System.out.print(actVO3.getActDate() + ", ");
		System.out.print(actVO3.getRegisMax() + ", ");
		System.out.print(actVO3.getActFee() + ", ");
		System.out.print(actVO3.getActRegistration() + ", ");
		System.out.println(actVO3.getActStatus());
		System.out.println("---------------------");

		// 查詢
		List<ActVO> list = dao.getAll();
		for (ActVO aAct : list) {
			System.out.print(aAct.getActID() + ", ");
			System.out.print(aAct.getStoreID() + ", ");
			System.out.print(aAct.getActTitle() + ", ");
			System.out.print(aAct.getActDescription() + ", ");
			System.out.print(aAct.getActTimeStart() + ", ");
			System.out.print(aAct.getActTimeEnd() + ", ");
			System.out.print(aAct.getActDate() + ", ");
			System.out.print(aAct.getRegisMax() + ", ");
			System.out.print(aAct.getActFee() + ", ");
			System.out.print(aAct.getActRegistration() + ", ");
			System.out.print(aAct.getActStatus());
			System.out.println();
		}
	}
}
