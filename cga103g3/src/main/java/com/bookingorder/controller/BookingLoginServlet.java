package com.bookingorder.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookingorder.model.BookingOrdService;
import com.bookingorder.model.BookingOrderVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

@WebServlet("/BookingLogin")
public class BookingLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String action = req.getParameter("action");
		
		// filter攔截的路徑
		HttpSession session = req.getSession();
		String location= (String) session.getAttribute("location");
		
/**********************************登入***********************************************************************************/
		
		if ("memberLogin".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
		    String memAccount = req.getParameter("memAccount");
		    String memPassWord = req.getParameter("memPassWord");  
		    

		    // 【檢查該帳號 , 密碼是否有效】
			if (memAccount == null || (memAccount.trim()).length() == 0) {
				errorMsgs.add("請輸入會員帳號");
			}
			if (memPassWord == null || (memPassWord.trim()).length() == 0) {
				errorMsgs.add("請輸入會員密碼");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("memberLogin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			/***************************2.開始查詢資料*****************************************/
			MemberService memberSvc = new MemberService();
			MemberVO user = memberSvc.MemberLogin(memAccount, memPassWord);

			if (user == null ) {
				errorMsgs.add("帳號或密碼輸入錯誤");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("memberLogin.jsp");
				failureView.forward(req, res);
				
				return;//程式中斷
			}
			/*******************************************************************************************/
			MemberVO memberVO = memberSvc.MemberFindmemID(memAccount);
			req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			session.setAttribute("memAccount",user.getMemAccount());
			session.setAttribute("memID", memberVO.getMemID());
			
			res.sendRedirect(location);
			
//			String url = "/frontend/booking/bookingFinal.jsp"; 
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//			successView.forward(req, res);

		}

		
	}

}
