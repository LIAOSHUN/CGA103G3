package com.blocklist.model;

import java.util.List;

public interface BlockListDAO_interface {

	 public void insert(BlockListVO blockerListVO);
	    public void update(BlockListVO blockerListVO);
	    public void delete(Integer blockerID,Integer blockerIDBan);
	    public BlockListVO findByPrimaryKey(Integer blockerID);
	    public List<BlockListVO> getAll();
	}
