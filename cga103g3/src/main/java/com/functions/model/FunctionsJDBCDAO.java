package com.functions.model;

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

public class FunctionsJDBCDAO implements FunctionsDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO functions (funcName,funcDescription) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT funcID,funcName,funcDescription FROM functions order by funcID";
	private static final String GET_ONE_STMT = "SELECT funcID,funcName,funcDescription FROM functions where funcID = ?";
	private static final String DELETE = "DELETE FROM functions where funcID = ?";
	private static final String UPDATE = "UPDATE functions funcName=?,funcDescription=? where funcID = ?";

	@Override
	public void insert(FunctionsVO functionsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, functionsVO.getFuncName());
			pstmt.setString(2, functionsVO.getFuncDescription());

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
	public void update(FunctionsVO functionsVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, functionsVO.getFuncName());
			pstmt.setString(2, functionsVO.getFuncDescription());
			pstmt.setInt(3, functionsVO.getFuncID());
			
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
	public void delete(Integer funcID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, funcID);

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
	public FunctionsVO findByPrimaryKey(Integer funcID) {
		// TODO Auto-generated method stub
		FunctionsVO functionsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, funcID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				functionsVO = new FunctionsVO();
				functionsVO.setFuncID(rs.getInt("FuncID"));
				functionsVO.setFuncName(rs.getString("FuncName"));
				functionsVO.setFuncDescription(rs.getString("FuncDescription"));

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
		return functionsVO;
	}

	@Override
	public List<FunctionsVO> getAll() {
		// TODO Auto-generated method stub
		List<FunctionsVO> list = new ArrayList<FunctionsVO>();
		FunctionsVO functionsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				functionsVO = new FunctionsVO();
				functionsVO.setFuncID(rs.getInt("FuncID"));
				functionsVO.setFuncName(rs.getString("FuncName"));
				functionsVO.setFuncDescription(rs.getString("FuncDescription"));

				list.add(functionsVO); // Store the row in the list
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

		FunctionsJDBCDAO dao = new FunctionsJDBCDAO();
//
////		 新增
//		FunctionsVO functionsVO1 = new FunctionsVO();
//		functionsVO1.setFuncName("後台管理");
//		functionsVO1.setFuncDescription("wer");
//		dao.insert(functionsVO1);
//
////		// 修改(未成功,語法錯誤)
//		FunctionsVO functionsVO2 = new FunctionsVO();
//		functionsVO2.setFuncName("後台管理");
//		functionsVO2.setFuncDescription("123");
//		functionsVO2.setFuncID(92002);
//		dao.update(functionsVO2);
//
//		// 刪除
//		dao.delete(92003);
//
//		// 查詢
//		FunctionsVO functionsVO3 = dao.findByPrimaryKey(92002);
//		System.out.print(functionsVO3.getFuncID()+ ",");
//		System.out.print(functionsVO3.getFuncName() + ",");
//		System.out.println(functionsVO3.getFuncDescription());
//		System.out.println("---------------------");
//		
//		// 查詢
//		List<FunctionsVO> list = dao.getAll();
//		for (FunctionsVO functionsVO4 : list) {
//			System.out.print(functionsVO4.getFuncID()+ ",");
//			System.out.print(functionsVO4.getFuncName() + ",");
//			System.out.println(functionsVO4.getFuncDescription());
//			System.out.println();
//		}
//	
	}
}
