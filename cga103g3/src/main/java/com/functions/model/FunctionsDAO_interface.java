package com.functions.model;

import java.util.List;


public interface FunctionsDAO_interface {
	 public void insert(FunctionsVO functionsVO);
	    public void update(FunctionsVO functionsVO);
	    public void delete(Integer funcID);
	    public FunctionsVO findByPrimaryKey(Integer funcID);
	    public List<FunctionsVO> getAll();
}
