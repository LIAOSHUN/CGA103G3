package com.employee.model;

import java.util.List;

import com.member.model.MemberVO;


public interface EmployeeDAO_interface {
	 public void insert(EmployeeVO employeeVO);
	    public void update(EmployeeVO employeeVO);
	    public void delete(Integer empID);
	    public EmployeeVO findByPrimaryKey(Integer empID);
	    public List<EmployeeVO> getAll();
		public EmployeeVO EmployeeLogin(String empAccount,String empPassWord);
		public EmployeeVO EmployeeFindempID(String empAccount);

}
