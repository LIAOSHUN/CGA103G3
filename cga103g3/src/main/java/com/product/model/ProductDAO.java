package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProductDAO implements ProductDAO_interface  {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO product (PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar) VALUES (?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT PdID,PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product order by PdID";
		private static final String GET_ONE_STMT = 
			"SELECT PdID,PdName,PdPrice,PdAmount,PdDescription,PdStatus,PdStar,PdUpdate FROM product where empno = ?";
		private static final String DELETE = 
			"DELETE FROM product where PdID = ?";
		private static final String UPDATE = 
			"UPDATE product set PdName=?, PdPrice=?, PdAmount=?, PdDescription=?, PdStatus=?, PdStar=? where PdID = ?";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, productVO.getPdID());
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdPrice());
			pstmt.setInt(3, productVO.getPdAmount());
			pstmt.setString(4, productVO.getPdDescription());
			pstmt.setInt(5, productVO.getPdStatus());
			pstmt.setInt(6, productVO.getPdStar());
//			pstmt.setTimestamp(7, productVO.getPdUpdate());

			pstmt.executeUpdate();

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setInt(1, productVO.getPdID());
			pstmt.setString(1, productVO.getPdName());
			pstmt.setInt(2, productVO.getPdPrice());
			pstmt.setInt(3, productVO.getPdAmount());
			pstmt.setString(4, productVO.getPdDescription());
			pstmt.setInt(5, productVO.getPdStatus());
			pstmt.setInt(6, productVO.getPdStar());
			pstmt.setInt(7, productVO.getPdID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer PdID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, PdID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ProductVO findByPrimaryKey(Integer PdID) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, PdID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("PdID"));
				productVO.setPdName(rs.getString("PdName"));
				productVO.setPdPrice(rs.getInt("PdPrice"));
				productVO.setPdAmount(rs.getInt("PdAmount"));
				productVO.setPdDescription(rs.getString("PdDescription"));
				productVO.setPdStatus(rs.getInt("PdStatus"));
				productVO.setPdStar(rs.getInt("PdStar"));
				productVO.setPdUpdate(rs.getTimestamp("PdUpdate"));
			}
			
			
			
			
			

			// Handle any driver errors
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ProductVO �]�٬� Domain objects
				productVO = new ProductVO();
				productVO.setPdID(rs.getInt("PdID"));
				productVO.setPdName(rs.getString("PdName"));
				productVO.setPdPrice(rs.getInt("PdPrice"));
				productVO.setPdAmount(rs.getInt("PdAmount"));
				productVO.setPdDescription(rs.getString("PdDescription"));
				productVO.setPdStatus(rs.getInt("PdStatus"));
				productVO.setPdStar(rs.getInt("PdStar"));
				productVO.setPdUpdate(rs.getTimestamp("PdUpdate"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
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
}