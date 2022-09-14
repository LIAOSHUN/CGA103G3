package com.authority.model;

import java.util.List;

public class AuthorityService {
	
	private AuthorityDAO_interface dao;

	public AuthorityService() {
		dao = new AuthorityJDBCDAO();
	}

	public AuthorityVO addAuthority(Integer empID, Integer funcID) {

		AuthorityVO authorityVO = new AuthorityVO();

		authorityVO.setEmpID(empID);
		authorityVO.setFuncID(funcID);
	
		dao.insert(authorityVO);

		return authorityVO;
	}

	public AuthorityVO updateAuthority(Integer empID, Integer funcID) {

		AuthorityVO authorityVO = new AuthorityVO();

		authorityVO.setEmpID(empID);
		authorityVO.setFuncID(funcID);
	
		dao.update(authorityVO);

		return authorityVO;
	}

	public void deleteAuthority(Integer empID, Integer funcID) {
		dao.delete(empID,funcID);
	}

	public AuthorityVO getOneAuthority(Integer empID) {
		return dao.findByPrimaryKey(empID);
	}

	public List<AuthorityVO> getAll() {
		return dao.getAll();
	
}
	}
