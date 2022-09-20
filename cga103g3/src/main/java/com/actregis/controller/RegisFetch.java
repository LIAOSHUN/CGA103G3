package com.actregis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.actregis.model.ActRegisService;
import com.actregis.model.ActRegisVO;
import com.bookingorder.model.BookingOrdService;
import com.bookingorder.model.BookingOrderVO;


import static com.core.utils.JSONTrans.*;

@WebServlet("/RegisFetch")
public class RegisFetch extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("fetch come!!");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		
		ActRegisVO actRegisVO = json2Pojo(req, ActRegisVO.class);
		
		ActRegisService actRegisSvc = new ActRegisService();
		actRegisSvc.addActRegis(actRegisVO);
		
	}

}
