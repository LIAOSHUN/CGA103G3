package com.authority.model;

import java.util.List;


public interface AuthorityDAO_interface {
	public void insert(AuthorityVO authorityVO);

	public void update(AuthorityVO authorityVO);

	public void delete(Integer empID, Integer funcID);

	public AuthorityVO findByPrimaryKey(Integer empID);

	public List<AuthorityVO> getAll();
}
