package com.blocklist.model;

import java.util.List;

public class BlockListService {
	private BlockListDAO_interface dao;

	public BlockListService() {
		dao = new BlockListJDBCDAO();
	}

	public BlockListVO addBlockerList(Integer blockerID, Integer blockerIDBan, java.sql.Date blockerDate) {

		BlockListVO blockerlistVO = new BlockListVO();

		blockerlistVO.setBlockerID(blockerIDBan);
		blockerlistVO.setBlockerIDBan(blockerIDBan);
		blockerlistVO.setBlockerDate(blockerDate);

		dao.insert(blockerlistVO);

		return blockerlistVO;
	}

	public BlockListVO updateBlockerList(Integer blockerID, Integer blockerIDBan, java.sql.Date blockerDate) {

		BlockListVO blockerlistVO = new BlockListVO();

		blockerlistVO.setBlockerID(blockerIDBan);
		blockerlistVO.setBlockerIDBan(blockerIDBan);
		blockerlistVO.setBlockerDate(blockerDate);

		dao.update(blockerlistVO);

		return blockerlistVO;
	}

	public void deleteBlockerList(Integer blockerID, Integer blockerIDBan) {
		dao.delete(blockerID, blockerIDBan);
	}

	public BlockListVO getOneBlockerList(Integer blockerID) {
		return dao.findByPrimaryKey(blockerID);
	}

	public List<BlockListVO> getAll() {
		return dao.getAll();

	}
}
