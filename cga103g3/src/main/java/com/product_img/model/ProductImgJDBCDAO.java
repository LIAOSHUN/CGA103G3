package com.product_img.model;

import java.util.*;
import java.sql.*;

public class ProductImgJDBCDAO implements  ProductImgDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "871104";

	private static final String INSERT_STMT = 
			"INSERT INTO productimg (PdId,PdImg,PdImgName) VALUES (?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdImgId,PdId,PdImg,PdImgName FROM productimg order by PdImgId";
		private static final String GET_ONE_STMT = 
			"SELECT PdImgId,PdId,PdImg,PdImgName FROM productimg where PdImgId = ?";
		private static final String DELETE = 
			"DELETE FROM productimg where PdImgId = ?";
		private static final String UPDATE = 
			"UPDATE productimg set PdImg=?, PdImgName=? where PdImgId = ?";

	@Override
	public void insert(ProductImgVO productImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productImgVO.getPdImgId());
			pstmt.setInt(2, productImgVO.getPdId());
			pstmt.setBlob(3, productImgVO.getPdImg());
			pstmt.setString(4, productImgVO.getPdImgName());

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

			pstmt.setInt(1, productImgVO.getPdImgId());
			pstmt.setInt(2, productImgVO.getPdId());
			pstmt.setBlob(3, productImgVO.getPdImg());
			pstmt.setString(4, productImgVO.getPdImgName());


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
	public ProductImgVO findByPrimaryKey(Integer empno) {

		ProductImgVO productImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				productImgVO = new ProductImgVO();
//				productImgVO.setPdImgId(rs.getInt("21005"));
				productImgVO.setPdId(rs.getInt("21001"));
//				productImgVO.setPdImg(rs.getBlob(""));
				productImgVO.setPdImgName(rs.getString("87遊戲"));
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
//				productImgVO.setPdImgId(rs.getInt("21005"));
				productImgVO.setPdId(rs.getInt("21001"));
//				productImgVO.setPdImg(rs.getBlob(""));
				productImgVO.setPdImgName(rs.getString("87遊戲"));
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
		productimgVO1.setPdId(21001);
//		productimgVO1.setPdImg("");
		productimgVO1.setPdImgName("遊戲87");
		dao.insert(productimgVO1);

		// �ק�
		ProductImgVO productimgVO2 = new ProductImgVO();
		productimgVO2.setPdId(21002);
//		productimgVO2.setPdImg("");
		productimgVO2.setPdImgName("遊戲877777");
		dao.update(productimgVO2);

		// �R��
		dao.delete(7014);

		// �d��
		ProductImgVO productimgVO3 = dao.findByPrimaryKey(21001);
		System.out.print(productimgVO3.getPdImgId() + ",");
		System.out.print(productimgVO3.getPdId() + ",");
		System.out.print(productimgVO3.getPdImg() + ",");
		System.out.print(productimgVO3.getPdImgName() + ",");
		System.out.println("---------------------");

		// �d��
		List<ProductImgVO> list = dao.getAll();
		for (ProductImgVO aProductimg : list) {
			System.out.print(aProductimg.getPdImgId() + ",");
			System.out.print(aProductimg.getPdId() + ",");
			System.out.print(aProductimg.getPdImg() + ",");
			System.out.print(aProductimg.getPdImgName() + ",");
			System.out.println();
		}
	}
}