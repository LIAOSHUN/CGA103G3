package com.productimg.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/ShowProductImg")
public class ShowProductImg extends HttpServlet {

	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String pdID = req.getParameter("pdID").trim();
			String countString = req.getParameter("count");
			int countNo = 0;
			if (countString != null) {
				countNo = Integer.parseInt(countString);
			}
			ResultSet rs = stmt.executeQuery(
//				"SELECT IMAGE FROM PICTURES WHERE PID = " + req.getParameter("PID"));
				"SELECT pdImg FROM productimg WHERE pdID = "+pdID);
			
//			while (rs.next()) {
				rs.absolute(countNo);
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pdImg"));
				byte[] buf = in.readAllBytes();
				out.write(buf);
				in.close();
				
//			} 
//			else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
//				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
//				byte[] b = new byte[in.available()];
//				in.read(b);
//				out.write(b);
//				in.close();
//			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/boardgame");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}