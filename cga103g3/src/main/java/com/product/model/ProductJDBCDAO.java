package com.product.model;

import java.util.*;

import com.productimg.model.ProductImgJDBCDAO;
import com.productimg.model.ProductImgVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/boardgame?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "CGA103g3";

	private static final String INSERT_STMT = 
			"INSERT INTO product (PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar) VALUES (?, ?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdID,PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product order by PdUpdate desc";
		private static final String GET_ONE_STMT = 
			"SELECT PdID,PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where PdID = ?";
		private static final String DELETE = 
			"DELETE FROM product where PdID = ?";
		private static final String UPDATE = 
			"UPDATE product set PdName=?,pdTypeID=?, PdPrice=?, PdAmount=?, PdDescription=?, PdStatus=?, PdStar=? where PdID = ?";
		private static final String GET_ALL_UP = 
				"SELECT PdID,PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where PdStatus=1 order by PdUpdate desc";
		private static final String GET_TYPE = 
				"SELECT PdID,PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where PdStatus=1 and pdTypeID=? order by PdUpdate desc";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			"INSERT INTO product (PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar) VALUES (?, ?, ?, ?, ?, ?,?)";
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdTypeID());
			pstmt.setInt(3, productVO.getPdPrice());
			pstmt.setInt(4, productVO.getPdAmount());
			pstmt.setString(5, productVO.getPdDescription());
			pstmt.setInt(6, productVO.getPdStatus());
			pstmt.setInt(7, productVO.getPdStar());
			
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
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			"UPDATE product set PdName=?,pdTypeID=?, PdPrice=?, PdAmount=?, PdDescription=?, PdStatus=?, PdStar=? where PdID = ?";
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdTypeID());
			pstmt.setInt(3, productVO.getPdPrice());
			pstmt.setInt(4, productVO.getPdAmount());
			pstmt.setString(5, productVO.getPdDescription());
			pstmt.setInt(6, productVO.getPdStatus());
			pstmt.setInt(7, productVO.getPdStar());
			pstmt.setInt(8, productVO.getPdID());

			
			
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
	public void delete(Integer pdId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pdId);

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
	public ProductVO findByPrimaryKey(Integer pdId) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pdId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo ???]???????? Domain objects
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("pdID"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdTypeID(rs.getInt("pdTypeID"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdAmount(rs.getInt("pdAmount"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdStar(rs.getInt("pdStar"));
				productVO.setPdUpdate(rs.getTimestamp("pdUpdate"));
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ???]???????? Domain objects
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("pdID"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdTypeID(rs.getInt("pdTypeID"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdAmount(rs.getInt("pdAmount"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdStar(rs.getInt("pdStar"));
				productVO.setPdUpdate(rs.getTimestamp("pdUpdate"));
				list.add(productVO); // Store the row in the list
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
	public void insertWithProductImg(ProductVO productVO , List<ProductImgVO> list) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1???????????? pstm.executeUpdate()??????
    		con.setAutoCommit(false);
			
    		// ?????????????????????
			String cols[] = {"pdID"};
//			"INSERT INTO product (PdName,pdTypeID,PdPrice,PdAmount,PdDescription,PdStatus,PdStar) VALUES (?, ?, ?, ?, ?, ?,?)";
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdTypeID());
			pstmt.setInt(3, productVO.getPdPrice());
			pstmt.setInt(4,productVO.getPdAmount());
			pstmt.setString(5,productVO.getPdDescription());
			pstmt.setInt(6,productVO.getPdStatus());
			pstmt.setInt(7,productVO.getPdStar());
			
			Statement stmt=	con.createStatement();
			//????????????-?????????
//			stmt.executeUpdate("set auto_increment_offset=10;");
			//????????????-??????   
			stmt.executeUpdate("set auto_increment_increment=1;"); 
			pstmt.executeUpdate();
			//??????????????????????????????
			String next_PdID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_PdID = rs.getString(1);
				System.out.println("???????????????= " + next_PdID +"(??????????????????????????????)");
			} else {
				System.out.println("????????????????????????");
			}
			rs.close();
			// ?????????????????????
			ProductImgJDBCDAO dao = new ProductImgJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (ProductImgVO aProductImg : list) {
				aProductImg.setPdID(new Integer(next_PdID)) ;
				dao.insert2(aProductImg,con);
			}

			// 2???????????? pstm.executeUpdate()??????
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("??????????????????" + next_PdID + "???,????????????" + list.size()
					+ "??????????????????");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3??????????????????exception????????????catch?????????
					System.err.print("Transaction is being ");
					System.err.println("rolled back-???-dept");
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
	public List<ProductVO> getUp() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_UP);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ???]???????? Domain objects
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("pdID"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdTypeID(rs.getInt("pdTypeID"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdAmount(rs.getInt("pdAmount"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdStar(rs.getInt("pdStar"));
				productVO.setPdUpdate(rs.getTimestamp("pdUpdate"));
				list.add(productVO); // Store the row in the list
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
	public List<ProductVO> getType(Integer pdTypeID) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_TYPE);
			
			pstmt.setInt(1, pdTypeID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO ???]???????? Domain objects
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("pdID"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdTypeID(rs.getInt("pdTypeID"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdAmount(rs.getInt("pdAmount"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdStar(rs.getInt("pdStar"));
				productVO.setPdUpdate(rs.getTimestamp("pdUpdate"));
				list.add(productVO); // Store the row in the list
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

		ProductJDBCDAO dao = new ProductJDBCDAO();

//		ProductVO productVO = new ProductVO();
//		productVO.setPdName("????????????");
//		productVO.setPdTypeID(41010);
//		productVO.setPdPrice(100);
//		productVO.setPdAmount(99);
//		productVO.setPdDescription("????????????????????????");
//		productVO.setPdStatus(0);
//		productVO.setPdStar(3);
//		// ????????????????????????
//		List<ProductImgVO> testList = new ArrayList<ProductImgVO>(); 
//		ProductImgVO productImgXX = new ProductImgVO();   // ??????POJO1
//		
//		byte[] pic1 = getPictureByteArray("C:\\Users\\871104\\Desktop\\Tibame\\??????\\ProductImg\\Product (1).jpg");
//
//		productImgXX.setPdImg(pic1);
//		productImgXX.setPdImgName("???????????????");
//		
//		ProductImgVO productImgYY = new ProductImgVO();   // ??????POJO2
//		
//		byte[] pic2 = getPictureByteArray("C:\\Users\\871104\\Desktop\\Tibame\\??????\\ProductImg\\Product (1).webp");
//
//		productImgYY.setPdImg(pic2);
//		productImgYY.setPdImgName("???????????????");
//
//		testList.add(productImgXX);
//		testList.add(productImgYY);
//		
//		dao.insertWithProductImg(productVO , testList);
		
		
		// ???s???W
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setPdName("?????????8+9");
//		productVO1.setPdPrice(89);
//		productVO1.setPdAmount(89);
//		productVO1.setPdDescription("??????89");
//		productVO1.setPdStatus(0);
//		productVO1.setPdStar(3);
//		productVO1.setPdUpdate(Timestamp.valueOf(LocalDateTime.now())
//);
//		dao.insert(productVO1);

		// ????????
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setPdID(21015);
//		productVO2.setPdName("???????????????");
//		productVO2.setPdPrice(11);
//		productVO2.setPdAmount(11);
//		productVO2.setPdDescription("????????????");
//		productVO2.setPdStatus(0);
//		productVO2.setPdStar(3);
//		dao.update(productVO2);

		// ???R??????
//		dao.delete(21006);

		// ???d?????? ?????????PDUPDATE
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

		// ???d??????
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
		List<ProductVO> list = dao.getType(41001);
		for (ProductVO aproduct : list) {
			System.out.print(aproduct.getPdID() + ",");
			System.out.print(aproduct.getPdName() + ",");
			System.out.print(aproduct.getPdTypeID() + ",");
			System.out.print(aproduct.getPdPrice() + ",");
			System.out.print(aproduct.getPdAmount() + ",");
			System.out.print(aproduct.getPdDescription() + ",");
			System.out.print(aproduct.getPdStatus() + ",");
			System.out.print(aproduct.getPdStar());
			System.out.println(aproduct.getPdUpdate());
			System.out.println();
		}
	}
	
	
	
	
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}


