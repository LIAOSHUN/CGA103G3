package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.employee.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("empID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer empID = null;
				try {
					empID = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(empID);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer empID = Integer.valueOf(req.getParameter("empID"));
				
				/***************************2.開始查詢資料****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				EmployeeVO employeeVO = employeeSvc.getOneEmployee(empID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/employee/update_employee_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer empID = Integer.valueOf(req.getParameter("empID").trim());
				
				String empName = req.getParameter("empName");
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String empPhone = req.getParameter("empPhone").trim();
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}	
                
				
				byte[] empAvatar = req.getPart("empAvatar").getInputStream().readAllBytes();
				if(empAvatar.length==0) {
					empAvatar=null;
				}				
				String empAccount = req.getParameter("empAccount");
				String empAccountReg = "^[(a-zA-Z0-9_)]{2,13}$";
				if (empAccount == null || empAccount.trim().length() == 0) {
					errorMsgs.add("使用者姓名: 請勿空白");
				} else if(!empAccount.trim().matches(empAccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是英文字母、數字和_ , 且長度必需在2到13之間");
	            }

				
				
				String empPassWord = req.getParameter("empPassWord");
				String empPassWordReg = "^[(a-zA-Z0-9_)]{2,13}$";
				if (empPassWord == null || empPassWord.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if(!empPassWord.trim().matches(empPassWordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在2到13之間");
	            }
				
				
				java.sql.Date empHireDate = null;
				try {
					empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
				} catch (IllegalArgumentException e) {
					empHireDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer empStatus=Integer.valueOf(req.getParameter("empStatus").trim());;
				
				
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmpID(empID);
				employeeVO.setEmpName(empName);
				employeeVO.setEmpPhone(empPhone);
				employeeVO.setEmpAvatar(empAvatar);
				employeeVO.setEmpAccount(empAccount);
				employeeVO.setEmpPassWord(empPassWord);
				employeeVO.setEmpHireDate(empHireDate);
				employeeVO.setEmpStatus(empStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/update_employee_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeVO = employeeSvc.updateEmployee(empID, empName, empPhone, empAvatar, empAccount,empPassWord, empHireDate
						,empStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/employee/listOneEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

		
		
		
		
		
		
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empName = req.getParameter("empName");
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String empPhone = req.getParameter("empPhone").trim(); // 電話
				String empPhoneReg = "^[0-9]{10}$";
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				} else if (!empPhone.trim().matches(empPhoneReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話: 只能是數字");
				}
				
				byte[] empAvatar = req.getPart("empAvatar").getInputStream().readAllBytes();       //頭貼
				if(empAvatar.length==0) {
					empAvatar=null;
				}				
                          

				String empAccount = req.getParameter("empAccount"); // 使用者名稱
				String empAccountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empAccount == null || empAccount.trim().length() == 0) {
					errorMsgs.add("使用者名稱: 請勿空白");
				} else if (!empAccount.trim().matches(empAccountReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("使用者名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String empPassWord = req.getParameter("empPassWord"); // 密碼
				String empPassWordReg = "^[(a-zA-Z0-9_)]{6,20}$";
				if (empPassWord == null || empPassWord.trim().length() == 0) {
					errorMsgs.add("密碼: 請勿空白");
				} else if (!empPassWord.trim().matches(empPassWordReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("密碼: 只能是英文字母、數字和_ , 且長度必需在6到20之間");
				}

				java.sql.Date empHireDate = null; // 入值日
				try {
					empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
					System.out.println(empHireDate);
				} catch (IllegalArgumentException e) {
					empHireDate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				Integer empStatus = Integer.valueOf(req.getParameter("empStatus").trim());


				EmployeeVO employeeVO = new EmployeeVO();
				
				employeeVO.setEmpName(empName);
				employeeVO.setEmpPhone(empPhone);
				employeeVO.setEmpAvatar(empAvatar);
				employeeVO.setEmpAccount(empAccount);
				employeeVO.setEmpPassWord(empPassWord);
				employeeVO.setEmpHireDate(empHireDate);
				employeeVO.setEmpStatus(empStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.addEmployee(empName,empPhone,empAvatar, empAccount, empPassWord,
						empHireDate,empStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer empID = Integer.valueOf(req.getParameter("empID"));
				
				/***************************2.開始刪除資料***************************************/
				EmployeeService employeeSvc = new EmployeeService();
				employeeSvc.deleteEmployee(empID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/employee/listAllEmployee.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	
	
		
		
		
		/**********************************登入***********************************************************************************/
		
		if ("employeeLogin".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

//			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			
			
		    String empAccount = req.getParameter("empAccount");
		    String empPassWord = req.getParameter("empPassWord");

		    // 【檢查該帳號 , 密碼是否有效】
			if (empAccount == null || (empAccount.trim()).length() == 0) {
				errorMsgs.add("請輸入會員帳號");
			}
			if (empPassWord == null || (empPassWord.trim()).length() == 0) {
				errorMsgs.add("請輸入會員密碼");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/employee/employeeLogin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			/***************************2.開始查詢資料*****************************************/
			EmployeeService employeeSvc = new EmployeeService();
//			Mem_VO memVO = memSvc.getOneMem(mem_no);
		   EmployeeVO admin = employeeSvc.EmployeeLogin(empAccount, empPassWord);

			if (admin == null ) {
				errorMsgs.add("帳號或密碼輸入錯誤");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/employee/employeeLogin.jsp");
				failureView.forward(req, res);
				
				return;//程式中斷
			}
			/*******************************************************************************************/
			EmployeeVO admin1 = employeeSvc.EmployeeFindempID(empAccount);
			req.setAttribute("employeeVO", admin); // 資料庫取出的empVO物件,存入req

			
			
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			session.setAttribute("empID", admin1.getEmpID());

			
			
			System.out.println(req.getSession().getAttribute("empID"));      //測試Session

//			String location=(String)session.getAttribute("location");
//			res.sendRedirect(location);
			
			String url = "/backend/employee/listOneEmployee.jsp"; 
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);

		}
		/**********************************************************************************************************************/

		

		
		/**********************************登出*******************************************************************************/
		
		if ("employeeLogout".equals(action)) {
			session.removeAttribute("admin");
			String url = "/backend/employee/employeeLogin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		
		/**********************************************************************************************************************/

		
		
		
		
		
		
		
	
	
	}
}
