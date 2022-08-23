package com.actregis.model;

import java.util.List;

public interface ActRegisDAO_interface {
	public void insert(ActRegisVO actRegisVO);
    public void update(ActRegisVO actRegisVO);
    public List<ActRegisVO> findByPrimaryKey(Integer actID);
    public List<ActRegisVO> getAll();
}
