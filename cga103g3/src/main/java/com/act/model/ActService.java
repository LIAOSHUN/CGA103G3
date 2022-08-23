package com.act.model;

import java.sql.Timestamp;
import java.util.List;

public class ActService {
	private ActDAO_interface dao;

	public ActService() {
		dao = new ActJDBCDAO();
	}

	public ActVO addAct(Integer storeID, String actTitle, String actDescription,
			Timestamp actDate, Integer actStatus, Integer actFee, Integer actRegistration, 
			Timestamp actTimeStart, Timestamp actTimeEnd, Integer regisMax) {

		ActVO actVO = new ActVO();

		actVO.setStoreID(storeID);
		actVO.setActTitle(actTitle);
		actVO.setActDescription(actDescription);
		actVO.setActDate(actDate);
		actVO.setActStatus(actStatus);
		actVO.setActFee(actFee);
		actVO.setActRegistration(actRegistration);
		actVO.setActTimeStart(actTimeStart);
		actVO.setActTimeEnd(actTimeEnd);
		actVO.setRegisMax(regisMax);
		dao.insert(actVO);

		return actVO;
	}

	public ActVO updateAct(Integer actID, Integer storeID, String actTitle, String actDescription,
			Timestamp actDate, Integer actStatus, Integer actFee, Integer actRegistration, 
			Timestamp actTimeStart, Timestamp actTimeEnd, Integer regisMax) {

		ActVO actVO = new ActVO();
		
		actVO.setActID(actID);
		actVO.setStoreID(storeID);
		actVO.setActTitle(actTitle);
		actVO.setActDescription(actDescription);
		actVO.setActDate(actDate);
		actVO.setActStatus(actStatus);
		actVO.setActFee(actFee);
		actVO.setActRegistration(actRegistration);
		actVO.setActTimeStart(actTimeStart);
		actVO.setActTimeEnd(actTimeEnd);
		actVO.setRegisMax(regisMax);
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
