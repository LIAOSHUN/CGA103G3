package com.customerservices.model;

import java.sql.Timestamp;
import java.util.List;


public class CustomerServicesService {
	private CustomerServicesDAO_interface dao;

	public CustomerServicesService() {
		dao = new CustomerServicesJDBCDAO();
	}

	public CustomerServicesVO addCustomerServices(Integer memID, Integer empID, Integer csStatus, String csContent,byte[] csImg,Timestamp csTime) {

		CustomerServicesVO customerservicesVO = new CustomerServicesVO();

		customerservicesVO.setMemID(memID);
		customerservicesVO.setEmpID(empID);
		customerservicesVO.setCsStatus(csStatus);
		customerservicesVO.setCsContent(csContent);
		customerservicesVO.setCsImg(csImg);
		customerservicesVO.setCsTime(csTime);

		dao.insert(customerservicesVO);

		return customerservicesVO;
	}

	public CustomerServicesVO updateCustomerServices(Integer csNum,Integer memID, Integer empID, Integer csStatus, String csContent,byte[] csImg,Timestamp csTime) {


		CustomerServicesVO customerservicesVO = new CustomerServicesVO();
        customerservicesVO.setCsNum(csNum);
		customerservicesVO.setMemID(memID);
		customerservicesVO.setEmpID(empID);
		customerservicesVO.setCsStatus(csStatus);
		customerservicesVO.setCsContent(csContent);
		customerservicesVO.setCsImg(csImg);
		customerservicesVO.setCsTime(csTime);


		dao.update(customerservicesVO);

		return customerservicesVO;
	}

	public void deleteCustomerServices(Integer csNum) {
		dao.delete(csNum);
	}

	public CustomerServicesVO getOneCustomerServices(Integer csNum) {
		return dao.findByPrimaryKey(csNum);
	}

	public List<CustomerServicesVO> getAll() {
		return dao.getAll();

	}
}
