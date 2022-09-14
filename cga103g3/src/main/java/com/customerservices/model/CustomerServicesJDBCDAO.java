package com.customerservices.model;

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



public class CustomerServicesJDBCDAO implements CustomerServicesDAO_interface {


	private static final String INSERT_STMT = 
			"INSERT INTO customerServices (MemID,EmpID,CsStatus,CsContent,CsImg,CsTime) VALUES (?, ?, ?, ?, ?,now())";
		private static final String GET_ALL_STMT = 
			"SELECT MemID,EmpID,CsStatus,CsContent,CsImg,CsTime FROM customerServices order by CsNum";
		private static final String GET_ONE_STMT = 
			"SELECT MemID,EmpID,CsStatus,CsContent,CsImg,CsTime FROM customerServices where CsNum = ?";
		private static final String DELETE = 
			"DELETE FROM customerServices where CsNum = ?";
		private static final String UPDATE = 
			"UPDATE customerServices set MemID=?,EmpID=?,CsStatus=?,CsContent=?,CsImg=?,CsTime=? where CsNum = ?";

	
	
	
	
	
	@Override
	public void insert(CustomerServicesVO customerServicesVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, customerServicesVO.getMemID());
			pstmt.setInt(2, customerServicesVO.getEmpID());
			pstmt.setInt(3, customerServicesVO.getCsStatus());
			pstmt.setString(4, customerServicesVO.getCsContent());
			pstmt.setBytes(5, customerServicesVO.getCsImg());
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
	public void update(CustomerServicesVO customerServicesVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, customerServicesVO.getMemID());
			pstmt.setInt(2, customerServicesVO.getEmpID());
			pstmt.setInt(3, customerServicesVO.getCsStatus());
			pstmt.setString(4, customerServicesVO.getCsContent());
			pstmt.setBytes(5, customerServicesVO.getCsImg());
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
	public void delete(Integer csNum) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, csNum);

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
	public CustomerServicesVO findByPrimaryKey(Integer csNum) {
		// TODO Auto-generated method stub
		CustomerServicesVO customerServicesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, csNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				customerServicesVO = new CustomerServicesVO();
				customerServicesVO.setMemID(rs.getInt("MemID"));
				customerServicesVO.setEmpID(rs.getInt("EmpID"));
				customerServicesVO.setCsStatus(rs.getInt("CsStatus"));
				customerServicesVO.setCsContent(rs.getString("CsContent"));
				customerServicesVO.setCsImg(rs.getBytes("CsImg"));
				customerServicesVO.setCsNum(rs.getInt("CsNum"));
			
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
		return customerServicesVO;
	}

	@Override
	public List<CustomerServicesVO> getAll() {
		// TODO Auto-generated method stub
		List<CustomerServicesVO> list = new ArrayList<CustomerServicesVO>();
		CustomerServicesVO customerServicesVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				customerServicesVO = new CustomerServicesVO();
				customerServicesVO.setMemID(rs.getInt("MemID"));
				customerServicesVO.setEmpID(rs.getInt("EmpID"));
				customerServicesVO.setCsStatus(rs.getInt("CsStatus"));
				customerServicesVO.setCsContent(rs.getString("CsContent"));
				customerServicesVO.setCsImg(rs.getBytes("CsImg"));
				customerServicesVO.setCsNum(rs.getInt("CsNum"));
			
				list.add(customerServicesVO); // Store the row in the list
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
