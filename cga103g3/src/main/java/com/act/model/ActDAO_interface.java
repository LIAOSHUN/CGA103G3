package com.act.model;

import java.util.List;

public interface ActDAO_interface {
	public void insert(ActVO actVO);
    public void update(ActVO actVO);
    public ActVO findByPrimaryKey(Integer actID);
    public List<ActVO> getAll();
}
