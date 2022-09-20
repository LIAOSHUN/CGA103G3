package com.employee.model;

import java.util.List;

import com.member.model.MemberVO;


public class EmployeeService {
	private EmployeeDAO_interface dao;

	public EmployeeService() {
		dao = new EmployeeJDBCDAO();
	}

	public EmployeeVO addEmployee(String empName, String empPhone, byte[] empAvatar, String empAccount, String empPassWord,
			java.sql.Date empHireDate, Integer empStatus) {

		EmployeeVO employeeVO = new EmployeeVO();

		employeeVO.setEmpName(empName);
		employeeVO.setEmpPhone(empPhone);
		employeeVO.setEmpAvatar(empAvatar);
		employeeVO.setEmpAccount(empAccount);
		employeeVO.setEmpPassWord(empPassWord);
		employeeVO.setEmpHireDate(empHireDate);
		employeeVO.setEmpStatus(empStatus);

		dao.insert(employeeVO);

		return employeeVO;
	}

	public EmployeeVO updateEmployee(Integer empID,String empName, String empPhone, byte[] empAvatar, String empAccount, String empPassWord,
			java.sql.Date empHireDate, Integer empStatus) {

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmpID(empID);
		employeeVO.setEmpName(empName);
		employeeVO.setEmpPhone(empPhone);
		employeeVO.setEmpAvatar(empAvatar);
		employeeVO.setEmpAccount(empAccount);
		employeeVO.setEmpPassWord(empPassWord);
		employeeVO.setEmpHireDate(empHireDate);
		employeeVO.setEmpStatus(empStatus);

		dao.update(employeeVO);

		return employeeVO;
	}

	public void deleteEmployee(Integer empID) {
		dao.delete(empID);
	}

	public EmployeeVO getOneEmployee(Integer empID) {
		return dao.findByPrimaryKey(empID);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();

	}
	public EmployeeVO EmployeeLogin(String empAccount,String empPassWord) {
		return dao.EmployeeLogin(empAccount, empPassWord);
	}
	public EmployeeVO EmployeeFindempID(String empAccount) {
		return dao.EmployeeFindempID(empAccount);
	}
}
