package com.productimg.model;

import java.util.*;
import java.sql.*;

public class ProductImgJDBCDAO implements  ProductImgDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";

	private static final String INSERT_STMT = 
			"INSERT INTO productimg (PdId,PdImg,PdImgName) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdImgId,PdId,PdImg,PdImgName FROM productimg order by PdImgId";
		private static final String GET_ONE_STMT = 
			"SELECT PdImgId,PdId,PdImg,PdImgName FROM productimg where PdImgId = ?";
		private static final String DELETE = 
			"DELETE FROM productimg where PdImgId = ?";
		private static final String UPDATE = 
			"UPDATE productimg set PdID=?, PdImg=?, PdImgName=? where PdImgId = ?";
		private static final String GET_ONE_IMG =
			"SELECT PdImg FROM productimg where pdID = ?";
	

		
		
		
		
	@Override
	public void insert(ProductImgVO productImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			"INSERT INTO productimg (PdId,PdImg,PdImgName) VALUES (?, ?, ?)";
			pstmt.setInt(1, productImgVO.getPdID());
			pstmt.setBytes(2, productImgVO.getPdImg());
			pstmt.setString(3, productImgVO.getPdImgName());

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
	public void update(ProductImgVO productImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			"UPDATE productimg set PdID=?, PdImg=?, PdImgName=? where PdImgId = ?";
			pstmt.setInt(1, productImgVO.getPdID());
			pstmt.setBytes(2, productImgVO.getPdImg());
			pstmt.setString(3, productImgVO.getPdImgName());
			pstmt.setInt(4, productImgVO.getPdImgID());


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
	public void delete(Integer PdImgId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PdImgId);

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
	public ProductImgVO findByPrimaryKey(Integer pdImgID) {

		ProductImgVO productImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pdImgID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productImgVO = new ProductImgVO();
				productImgVO.setPdImgID(rs.getInt("pdImgID"));
				productImgVO.setPdID(rs.getInt("pdID"));
				productImgVO.setPdImg(rs.getBytes("pdImg"));
				productImgVO.setPdImgName(rs.getString("pdImgName"));
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
		return productImgVO;
	}

	@Override
	public List<ProductImgVO> getAll() {
		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		ProductImgVO productImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productImgVO = new ProductImgVO();
				productImgVO.setPdImgID(rs.getInt("pdImgID"));
				productImgVO.setPdID(rs.getInt("pdID"));
				productImgVO.setPdImg(rs.getBytes("pdImg"));
				productImgVO.setPdImgName(rs.getString("pdImgName"));
				list.add(productImgVO); // Store the row in the list
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
	@Override
	public void insert2 (ProductImgVO productImgVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
//    		"INSERT INTO productimg (PdId,PdImg,PdImgName) VALUES (?, ?, ?)";
			pstmt.setInt(1, productImgVO.getPdID());
			pstmt.setBytes(2, productImgVO.getPdImg());
			pstmt.setString(3, productImgVO.getPdImgName());

			Statement stmt=	con.createStatement();
			//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}
	public List<ProductImgVO> getOneImg() {
		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		ProductImgVO productImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_IMG);
			rs = pstmt.executeQuery();
			
//			"SELECT PdImg FROM productimg where pdID = ?";
			pstmt.setInt(1, productImgVO.getPdID());


			while (rs.next()) {
				// empVO �]�٬� Domain objects
				productImgVO = new ProductImgVO();
				productImgVO.setPdImg(rs.getBytes("pdImg"));
				list.add(productImgVO); // Store the row in the list
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

		ProductImgJDBCDAO dao = new ProductImgJDBCDAO();

		// �s�W
		ProductImgVO productimgVO1 = new ProductImgVO();
//		productimgVO1.setPdId(21001);
//		productimgVO1.setPdImg("");
//		productimgVO1.setPdImgName("遊戲87");
//		dao.insert(productimgVO1);

		// �ק�
//		ProductImgVO productimgVO2 = new ProductImgVO();
//		productimgVO2.setPdId(21002);
//		productimgVO2.setPdImg("");
//		productimgVO2.setPdImgName("遊戲877777");
//		dao.update(productimgVO2);

		// �R��
		dao.delete(7014);

		// �d��
//		ProductImgVO productimgVO3 = dao.findByPrimaryKey(21001);
//		System.out.print(productimgVO3.getPdImgId() + ",");
//		System.out.print(productimgVO3.getPdId() + ",");
//		System.out.print(productimgVO3.getPdImg() + ",");
//		System.out.print(productimgVO3.getPdImgName() + ",");
//		System.out.println("---------------------");

		// �d��
		List<ProductImgVO> list = dao.getAll();
		for (ProductImgVO aProductimg : list) {
			System.out.print(aProductimg.getPdImgID() + ",");
			System.out.print(aProductimg.getPdID() + ",");
			System.out.print(aProductimg.getPdImg() + ",");
			System.out.print(aProductimg.getPdImgName() + ",");
			System.out.println();
		}
	}
}