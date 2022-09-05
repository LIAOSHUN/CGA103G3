package com.faqkeyword.model;

import java.util.List;


public interface FaqKeyWordDAO_interface {
	 public void insert(FaqKeyWordVO faqkeywordVO );
	    public void update(FaqKeyWordVO faqkeywordVO);
	    public void delete(Integer kwID);
	    public FaqKeyWordVO findByPrimaryKey(Integer kwID);
	    public List<FaqKeyWordVO> getAll();
}
