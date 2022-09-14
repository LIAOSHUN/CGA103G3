package com.blocklist.model;

import static basic.util.JdbcConstants.PASSWORD;
import static basic.util.JdbcConstants.URL;
import static basic.util.JdbcConstants.USERNAME;
import static basic.util.JdbcConstants.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BlockListJDBCDAO implements BlockListDAO_interface {


	private static final String INSERT_STMT = "INSERT INTO blockList (blockerID,blockerIDBan,blockerDate) VALUES (?, ?, now())";
	private static final String GET_ALL_STMT = "SELECT blockerID,blockerIDBan,blockerDate FROM blockList order by blockerID";
	private static final String GET_ONE_STMT = "SELECT blockerID,blockerIDBan,blockerDate FROM blockList where blockerID = ?";
	private static final String DELETE = "DELETE FROM blockList where blockerID = ? and blockerIDBan=?";
	private static final String UPDATE = "UPDATE blockerID=?,blockerIDBan=? where blockerID = ?";

	
	
	
	@Override
	public void insert(BlockListVO blockerListVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, blockerListVO.getBlockerID());
			pstmt.setInt(2, blockerListVO.getBlockerIDBan());
	
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public void update(BlockListVO blockerListVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, blockerListVO.getBlockerID());
			pstmt.setInt(2, blockerListVO.getBlockerIDBan());
			pstmt.setDate(3, blockerListVO.getBlockerDate());
	

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public void delete(Integer blockerID, Integer blockerIDBan) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, blockerID);
			pstmt.setInt(2, blockerIDBan);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public BlockListVO findByPrimaryKey(Integer blockerID) {
		// TODO Auto-generated method stub
		BlockListVO blockerListVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, blockerID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				blockerListVO = new BlockListVO();
				blockerListVO.setBlockerID(rs.getInt("BlockerID"));
				blockerListVO.setBlockerIDBan(rs.getInt("BlockerIDBan"));
				blockerListVO.setBlockerDate(rs.getDate("BlockerDate"));

	}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return blockerListVO;
	}

	@Override
	public List<BlockListVO> getAll() {
		// TODO Auto-generated method stub
		List<BlockListVO> list = new ArrayList<BlockListVO>();
		BlockListVO blockerListVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				blockerListVO = new BlockListVO();
				blockerListVO.setBlockerID(rs.getInt("BlockerID"));
				blockerListVO.setBlockerIDBan(rs.getInt("BlockerIDBan"));
				blockerListVO.setBlockerDate(rs.getDate("BlockerDate"));


			
				list.add(blockerListVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return list;
	
	}
	public static void main(String[] args) {

		BlockListJDBCDAO dao = new BlockListJDBCDAO();
//
//		// 新增
//		BlockListVO blockerlistVO1 = new BlockListVO();
//		blockerlistVO1.setBlockerID(11001);
//		blockerlistVO1.setBlockerIDBan(11002);
//	
//		dao.insert(blockerlistVO1);
//
//
//		// 刪除
//		dao.delete(11001,11002);
//
//		// 查詢
//		BlockListVO blockerlistVO2 = dao.findByPrimaryKey(11001);
//		System.out.print(blockerlistVO2.getBlockerID() + ",");
//		System.out.println(blockerlistVO2.getBlockerIDBan());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<BlockListVO> list = dao.getAll();
//		for (BlockListVO blockerlistVO3 : list) {
//			System.out.print(blockerlistVO3.getBlockerID() + ",");
//			System.out.println(blockerlistVO3.getBlockerIDBan());
//
//			System.out.println();
//		}
//	
	}

}
