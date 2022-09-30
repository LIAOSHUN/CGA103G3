package com.actregis.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.bookingorder.model.BookingOrderVO;

public class ActRegisService {
	private ActRegisDAO_interface dao;

	public ActRegisService() {
		dao = new ActRegisDAO();
//		dao = new ActRegisJDBCDAO();
	}

	public ActRegisVO addActRegis(Integer memID, Integer actID, 
			Integer actNum, Integer actFee, Integer feeStatus, Integer regisStatus) {

		ActRegisVO actRegisVO = new ActRegisVO();

		actRegisVO.setMemID(memID);
		actRegisVO.setActID(actID);
		actRegisVO.setActNum(actNum);
		actRegisVO.setActFee(actFee);
		actRegisVO.setFeeStatus(feeStatus);
		actRegisVO.setRegisStatus(regisStatus);
		dao.insert(actRegisVO);

		return actRegisVO;
	}
	
	//for fetch api
		public ActRegisVO addActRegis(ActRegisVO actRegisVO) {
			dao.insert(actRegisVO);
			
			return actRegisVO;
		}


	public ActRegisVO updateBkActRegis(Integer memID, Integer actID, Integer actNum, 
			Integer actFee, Integer feeStatus, Integer regisStatus) {

		ActRegisVO actRegisVO = new ActRegisVO();

		actRegisVO.setMemID(memID);
		actRegisVO.setActID(actID);
		actRegisVO.setActNum(actNum);
		actRegisVO.setActFee(actFee);
		actRegisVO.setFeeStatus(feeStatus);
		actRegisVO.setRegisStatus(regisStatus);
		dao.update(actRegisVO);

		return actRegisVO;
	}
	
	public void cancelActRegis(Integer memID, Integer actID) {
		dao.changeState(memID, actID);
	}
	
	public ActRegisVO updateFrActRegis(Integer memID, Integer actID, 
			String actReview, Integer satisfaction, Date reviewDate) {
		
		ActRegisVO actRegisVO = new ActRegisVO();
		
		actRegisVO.setMemID(memID);
		actRegisVO.setActID(actID);
		actRegisVO.setActReview(actReview);
		actRegisVO.setSatisfaction(satisfaction);
		actRegisVO.setReviewDate(reviewDate);
		dao.update(actRegisVO);
		
		return actRegisVO;
	}

	public List<ActRegisVO> getActRegistered(Integer actID) {
		return dao.findByActPrimaryKey(actID);
	}
	public List<ActRegisVO> getMemRegis(Integer memID) {
		return dao.findByMemPrimaryKey(memID);
	}
	
	public ActRegisVO getOneActRegis(Integer memID, Integer actID) {
		return dao.findByPrimaryKey(memID, actID);
	}

	public List<ActRegisVO> getAll() {
		return dao.getAll();
	}
}
