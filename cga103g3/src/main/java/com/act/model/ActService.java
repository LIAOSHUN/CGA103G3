package com.act.model;

import java.sql.Timestamp;
import java.util.List;

public class ActService {
	private ActDAO_interface dao;

	public ActService() {
		dao = new ActJDBCDAO();
	}

	public ActVO addAct(Integer storeID, String actTitle, String actDescription,
			Timestamp actTimeStart, Timestamp actTimeEnd, Timestamp actDate, Integer regisMax, 
			Integer actFee, Integer actRegistration, Integer actStatus) {

		ActVO actVO = new ActVO();

		actVO.setStoreID(storeID);
		actVO.setActTitle(actTitle);
		actVO.setActDescription(actDescription);
		actVO.setActTimeStart(actTimeStart);
		actVO.setActTimeEnd(actTimeEnd);
		actVO.setActDate(actDate);
		actVO.setRegisMax(regisMax);
		actVO.setActFee(actFee);
		actVO.setActRegistration(actRegistration);
		actVO.setActStatus(actStatus);
		dao.insert(actVO);

		return actVO;
	}

	public ActVO updateAct(Integer actID, Integer storeID, String actTitle, String actDescription,
			Timestamp actTimeStart, Timestamp actTimeEnd, Timestamp actDate, Integer regisMax, 
			Integer actFee, Integer actRegistration, Integer actStatus) {

		ActVO actVO = new ActVO();
		
		actVO.setActID(actID);
		actVO.setStoreID(storeID);
		actVO.setActTitle(actTitle);
		actVO.setActDescription(actDescription);
		actVO.setActTimeStart(actTimeStart);
		actVO.setActTimeEnd(actTimeEnd);
		actVO.setActDate(actDate);
		actVO.setRegisMax(regisMax);
		actVO.setActFee(actFee);
		actVO.setActRegistration(actRegistration);
		actVO.setActStatus(actStatus);
		dao.update(actVO);

		return actVO;
	}

	public ActVO getOneAct(Integer actID) {
		return dao.findByPrimaryKey(actID);
	}

	public List<ActVO> getAll() {
		return dao.getAll();
	}
}
