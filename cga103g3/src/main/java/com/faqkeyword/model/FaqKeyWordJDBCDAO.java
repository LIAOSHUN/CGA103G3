package com.faqkeyword.model;

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

public class FaqKeyWordJDBCDAO implements FaqKeyWordDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO faqkeyword (kwContent,kwReply,kwStatus) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT kwID,kwContent,kwReply,kwStatus FROM faqkeyword order by kwID";
	private static final String GET_ONE_STMT = "SELECT kwID,kwContent,kwReply,kwStatus FROM faqkeyword where kwID = ?";
	private static final String DELETE = "DELETE FROM faqkeyword where kwID = ?";
	private static final String UPDATE = "UPDATE kwContent=?,kwReply=?,kwStatus=? where kwID = ?";

	@Override
	public void insert(FaqKeyWordVO faqkeywordVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, faqkeywordVO.getKwContent());
			pstmt.setString(2, faqkeywordVO.getKwReply());
			pstmt.setInt(3, faqkeywordVO.getKwStatus());
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
	public void update(FaqKeyWordVO faqkeywordVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, faqkeywordVO.getKwContent());
			pstmt.setString(2, faqkeywordVO.getKwReply());
			pstmt.setInt(3, faqkeywordVO.getKwStatus());

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
	public void delete(Integer kwID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, kwID);

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
	public FaqKeyWordVO findByPrimaryKey(Integer kwID) {
		// TODO Auto-generated method stub
		FaqKeyWordVO faqkeywordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, kwID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				faqkeywordVO = new FaqKeyWordVO();
				faqkeywordVO.setKwID(rs.getInt("KwID"));
				faqkeywordVO.setKwContent(rs.getString("KwContent"));
				faqkeywordVO.setKwReply(rs.getString("KwReply"));
				faqkeywordVO.setKwStatus(rs.getInt("KwStatus"));

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
		return faqkeywordVO;
	}

	@Override
	public List<FaqKeyWordVO> getAll() {
		// TODO Auto-generated method stub
		List<FaqKeyWordVO> list = new ArrayList<FaqKeyWordVO>();
		FaqKeyWordVO faqkeywordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				faqkeywordVO = new FaqKeyWordVO();
				faqkeywordVO.setKwID(rs.getInt("KwID"));
				faqkeywordVO.setKwContent(rs.getString("KwContent"));
				faqkeywordVO.setKwReply(rs.getString("KwReply"));
				faqkeywordVO.setKwStatus(rs.getInt("KwStatus"));

				list.add(faqkeywordVO); // Store the row in the list
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

}
