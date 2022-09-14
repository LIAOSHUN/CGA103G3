package com.faqkeyword.model;

import java.util.List;


public class FaqKeyWordService {

	private FaqKeyWordDAO_interface dao;

	public FaqKeyWordService() {
		dao = new FaqKeyWordJDBCDAO();
	}

	public FaqKeyWordVO addFaqKeyWord(String kwContent, String kwReply, Integer kwStatus) {

		FaqKeyWordVO faqkeywordVO = new FaqKeyWordVO();

		faqkeywordVO.setKwContent(kwContent);
		faqkeywordVO.setKwReply(kwReply);
		faqkeywordVO.setKwStatus(kwStatus);

		dao.insert(faqkeywordVO);

		return faqkeywordVO;
	}

	public FaqKeyWordVO updateFaqKeyWord(Integer kwID,String kwContent, String kwReply, Integer kwStatus) {

		FaqKeyWordVO faqkeywordVO = new FaqKeyWordVO();
		faqkeywordVO.setKwID(kwID);
		faqkeywordVO.setKwContent(kwContent);
		faqkeywordVO.setKwReply(kwReply);
		faqkeywordVO.setKwStatus(kwStatus);



		dao.update(faqkeywordVO);

		return faqkeywordVO;
	}

	public void deleteFaqKeyWord(Integer kwID) {
		dao.delete(kwID);
	}

	public FaqKeyWordVO getOneFaqKeyWord(Integer kwID) {
		return dao.findByPrimaryKey(kwID);
	}

	public List<FaqKeyWordVO> getAll() {
		return dao.getAll();

	}
}
