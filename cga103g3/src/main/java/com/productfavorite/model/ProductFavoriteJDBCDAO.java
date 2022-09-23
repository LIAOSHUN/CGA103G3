package com.productfavorite.model;

import java.util.*;

import com.product.model.ProductVO;
import com.productimg.model.ProductImgJDBCDAO;
import com.productimg.model.ProductImgVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class ProductFavoriteJDBCDAO implements ProductFavoriteDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";

	private static final String INSERT_STMT = 
			"INSERT INTO productfavorites (memID,pdID) VALUES (?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT memID,pdID,pdfavdate FROM productfavorites where memID=11004 order by pdfavdate desc";
		private static final String DELETE = 
			"DELETE FROM productfavorites where memID = ? and pdID= ? " ;

	@Override
	public void insert(ProductFavoriteVO productFavoriteVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, productFavoriteVO.getMemID());
			pstmt.setInt(2, productFavoriteVO.getPdID());
			
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
	public void delete(Integer memID,Integer pdID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memID);
			pstmt.setInt(2, pdID);


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
	public List<ProductFavoriteVO> getAll() {
		List<ProductFavoriteVO> list = new ArrayList<ProductFavoriteVO>();
		ProductFavoriteVO productFavoriteVO = null;

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
				productFavoriteVO = new ProductFavoriteVO();
				productFavoriteVO.setMemID(rs.getInt("memID"));
				productFavoriteVO.setPdID(rs.getInt("pdID"));
				productFavoriteVO.setPdFavDate(rs.getTimestamp("pdFavDate"));
				list.add(productFavoriteVO); // Store the row in the list
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

	public static void main(String[] args) throws IOException {

		ProductFavoriteJDBCDAO dao = new ProductFavoriteJDBCDAO();

		
		// �s�W
		ProductFavoriteVO productFavoriteVO1 = new ProductFavoriteVO();
		productFavoriteVO1.setMemID(11001);
		productFavoriteVO1.setPdID(21009);

		dao.insert(productFavoriteVO1);

		// �ק�
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setPdID(21015);
//		productVO2.setPdName("吃你媽的飯");
//		productVO2.setPdPrice(11);
//		productVO2.setPdAmount(11);
//		productVO2.setPdDescription("這是你媽");
//		productVO2.setPdStatus(0);
//		productVO2.setPdStar(3);
//		dao.update(productVO2);

		// �R��
//		dao.delete(11001,21018);

		// �d�� 沒有打PDUPDATE
//		ProductVO productVO3= dao.findByPrimaryKey(21001);
//		System.out.print(productVO3.getPdID() + ",");
//		System.out.print(productVO3.getPdName() +",");
//		System.out.print(productVO3.getPdPrice() + ",");
//		System.out.print(productVO3.getPdAmount() + ",");
//		System.out.print(productVO3.getPdDescription() + ",");
//		System.out.print(productVO3.getPdStatus() + ",");
//		System.out.println(productVO3.getPdStar() + ",");
//		System.out.println(productVO3.getPdUpdate() + ",");
//		System.out.println("---------------------");

		// �d��
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aproduct : list) {
//			System.out.print(aproduct.getPdID() + ",");
//			System.out.print(aproduct.getPdName() + ",");
//			System.out.print(aproduct.getPdTypeID() + ",");
//			System.out.print(aproduct.getPdPrice() + ",");
//			System.out.print(aproduct.getPdAmount() + ",");
//			System.out.print(aproduct.getPdDescription() + ",");
//			System.out.print(aproduct.getPdStatus() + ",");
//			System.out.print(aproduct.getPdStar());
//			System.out.println(aproduct.getPdUpdate());
//			System.out.println();
//		}
//		List<ProductVO> list = dao.getUp();
//		for (ProductVO aproduct : list) {
//			System.out.print(aproduct.getPdID() + ",");
//			System.out.print(aproduct.getPdName() + ",");
//			System.out.print(aproduct.getPdTypeID() + ",");
//			System.out.print(aproduct.getPdPrice() + ",");
//			System.out.print(aproduct.getPdAmount() + ",");
//			System.out.print(aproduct.getPdDescription() + ",");
//			System.out.print(aproduct.getPdStatus() + ",");
//			System.out.print(aproduct.getPdStar());
//			System.out.println(aproduct.getPdUpdate());
//			System.out.println();
//		}
	}
	
}


