package com.actfav.model;

import java.util.List;

public interface ActFavDAO_interface {
	public void insert(ActFavVO actFavVO);
    public void update(ActFavVO actFavVO);
    public void delete(Integer memID, Integer actID);
    public List<ActFavVO> findByPrimaryKey(Integer memID);
    public List<ActFavVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
