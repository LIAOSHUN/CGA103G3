package com.actimg.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actimg.model.ActImgService;
import util.pic.ImageUtil;

public class ActImgServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOneImg".equals(action)) {
			res.setContentType("image/jpg");
			ServletOutputStream out = res.getOutputStream();
			Integer actImgID = Integer.valueOf(req.getParameter("actImgID").trim());
			Integer scaleSize = null;
			try {
				ActImgService actImgSvc = new ActImgService();
				byte[] buffer = actImgSvc.getOneActImg(actImgID).getActImgFile();
				try {
					scaleSize = Integer.parseInt(req.getParameter("scaleSize").trim());
					out.write(ImageUtil.shrink(buffer, scaleSize));
				} catch (Exception e) {
					out.write(buffer);
				}
				
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/image/none2.jpg");//回傳inputStream;/斜線代表該專案路徑
				byte[] b= new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}finally {
				out.close();
			}
		}
		
		
	}
}
