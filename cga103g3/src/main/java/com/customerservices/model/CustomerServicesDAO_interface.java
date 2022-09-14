package com.customerservices.model;

import java.util.List;


public interface CustomerServicesDAO_interface {
	 public void insert(CustomerServicesVO customerServicesVO);
	    public void update(CustomerServicesVO customerServicesVO);
	    public void delete(Integer csNum);
	    public CustomerServicesVO findByPrimaryKey(Integer csNum);
	    public List<CustomerServicesVO> getAll();
}
