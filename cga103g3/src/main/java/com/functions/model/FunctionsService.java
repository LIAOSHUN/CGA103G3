package com.functions.model;

import java.util.List;

public class FunctionsService {

	private FunctionsDAO_interface dao;

	public FunctionsService() {
		dao = new FunctionsJDBCDAO();
	}

	public FunctionsVO addFunctions(String funcName, String funcDescription) {

		FunctionsVO functionsVO = new FunctionsVO();
		functionsVO.setFuncName(funcName);
		functionsVO.setFuncDescription(funcDescription);
		dao.insert(functionsVO);

		return functionsVO;
	}

	public FunctionsVO updateFunctions(Integer funcID, String funcName, String funcDescription) {

		FunctionsVO functionsVO = new FunctionsVO();

		dao.update(functionsVO);

		return functionsVO;
	}

	public void deleteFunctions(Integer funcID) {
		dao.delete(funcID);
	}

	public FunctionsVO getOneFunctions(Integer funcID) {
		return dao.findByPrimaryKey(funcID);
	}

	public List<FunctionsVO> getAll() {
		return dao.getAll();

	}
}
