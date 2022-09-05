package com.authority.model;

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

public class AuthorityJDBCDAO implements AuthorityDAO_interface {
	private static final String INSERT_STMT = "INSERT INTO authority (empID,funcID) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT empID,funcID FROM authority order by empID";
	private static final String GET_ONE_STMT = "SELECT empID,funcID FROM authority where empID = ?";
	private static final String DELETE = "DELETE FROM authority where empID  = ? and funcID=?";
	private static final String UPDATE = "UPDATE membergrade set empID=?,funcName=? where empID = ?";

	@Override
	public void insert(AuthorityVO authorityVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, authorityVO.getEmpID());
			pstmt.setInt(2, authorityVO.getFuncID());

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
	public void update(AuthorityVO authorityVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, authorityVO.getEmpID());
			pstmt.setInt(2, authorityVO.getFuncID());

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
	public void delete(Integer empID, Integer funcID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empID);
			pstmt.setInt(2, funcID);

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
	public AuthorityVO findByPrimaryKey(Integer empID) {
		// TODO Auto-generated method stub
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setEmpID(rs.getInt("EmpID"));
				authorityVO.setFuncID(rs.getInt("FuncID"));

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
		return authorityVO;
	}

	@Override
	public List<AuthorityVO> getAll() {
		// TODO Auto-generated method stub
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setEmpID(rs.getInt("empID"));
				authorityVO.setFuncID(rs.getInt("funcID"));

				list.add(authorityVO); // Store the row in the list
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

//		AuthorictyJDBCDAO dao1 = new AuthorictyJDBCDAO();

		// 新增
//		AuthorityVO authorityVO1 = new AuthorityVO();
//		authorityVO1.setEmpID(91001);
//		authorityVO1.setFuncID(92001);
//
//		dao1.insert(authorityVO1);

////		// 修改
//	AuthorityVO authorityVO2 = new AuthorityVO();
//		authorityVO2.setEmpID(91001);
//		authorityVO2.setFuncID(92004);
//		
//		dao1.update(authorityVO2);
//
//		// 刪除
//		dao1.delete(91004);
//
//		// 查詢
//		EmployeeVO employeeVO3 = dao.findByPrimaryKey(91001);
//		System.out.print(employeeVO3.getEmpName()+ ",");
//		System.out.print(employeeVO3.getEmpPhone() + ",");
//		System.out.print(employeeVO3.getEmpAvatar() + ",");
//		System.out.print(employeeVO3.getEmpAccount() + ",");
//		System.out.print(employeeVO3.getEmpHireDate() + ",");
//		System.out.print(employeeVO3.getEmpStatus());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO employeeVO4 : list) {
//			System.out.print(employeeVO4.getEmpID()+",");
//			System.out.print(employeeVO4.getEmpName()+ ",");
//			System.out.print(employeeVO4.getEmpPhone() + ",");
//			System.out.print(employeeVO4.getEmpAvatar() + ",");
//			System.out.print(employeeVO4.getEmpAccount() + ",");
//			System.out.print(employeeVO4.getEmpHireDate() + ",");
//			System.out.print(employeeVO4.getEmpStatus());
//			System.out.println();
//		}
//	
	}
}
