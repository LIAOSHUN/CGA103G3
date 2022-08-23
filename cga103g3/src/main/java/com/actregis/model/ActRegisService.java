package com.actregis.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ActRegisService {
	private ActRegisDAO_interface dao;

	public ActRegisService() {
		dao = new ActRegisJDBCDAO();
	}

	public ActRegisVO addActRegis(Integer memID, Integer actID, Timestamp regisTime, 
			Integer actNum, Integer actFee, Integer feeStatus, Integer regisStatus, 
			String actReview, Integer satisfaction, Date reviewDate) {

		ActRegisVO actRegisVO = new ActRegisVO();

		actRegisVO.setMemID(memID);
		actRegisVO.setActID(actID);
		actRegisVO.setRegisTime(regisTime);
		actRegisVO.setActNum(actNum);
		actRegisVO.setActFee(actFee);
		actRegisVO.setFeeStatus(feeStatus);
		actRegisVO.setRegisStatus(regisStatus);
		actRegisVO.setActReview(actReview);
		actRegisVO.setSatisfaction(satisfaction);
		actRegisVO.setReviewDate(reviewDate);
		dao.insert(actRegisVO);

		return actRegisVO;
	}

	public ActRegisVO updateActRegis(Integer memID, Integer actID, Timestamp regisTime, 
			Integer actNum, Integer actFee, Integer feeStatus, Integer regisStatus, 
			String actReview, Integer satisfaction, Date reviewDate) {

		ActRegisVO actRegisVO = new ActRegisVO();

		actRegisVO.setMemID(memID);
		actRegisVO.setActID(actID);
		actRegisVO.setRegisTime(regisTime);
		actRegisVO.setActNum(actNum);
		actRegisVO.setActFee(actFee);
		actRegisVO.setFeeStatus(feeStatus);
		actRegisVO.setRegisStatus(regisStatus);
		actRegisVO.setActReview(actReview);
		actRegisVO.setSatisfaction(satisfaction);
		actRegisVO.setReviewDate(reviewDate);
		dao.update(actRegisVO);

		return actRegisVO;
	}

	public List<ActRegisVO> getOneActRegis(Integer actID) {
		return dao.findByPrimaryKey(actID);
	}

	public List<ActRegisVO> getAll() {
		return dao.getAll();
	}
}
