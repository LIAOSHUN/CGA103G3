package com.actimg.model;

import java.util.List;

public interface ActImgDAO_interface {
	public void insert(ActImgVO actImgVO);
    public void update(ActImgVO actImgVO);
    public void delete(Integer actImgID);
    public ActImgVO findByPrimaryKey(Integer actImgID);
    public List<ActImgVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
