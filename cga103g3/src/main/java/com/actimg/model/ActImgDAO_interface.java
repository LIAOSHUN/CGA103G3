package com.actimg.model;

import java.util.List;

public interface ActImgDAO_interface {
	public void insert(ActImgVO actImgVO);
    public void update(ActImgVO actImgVO);
    public void delete(Integer actImgID);
    public ActImgVO findByPrimaryKey(Integer actImgID);
    public List<ActImgVO> getAll();
  //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
    public void insertfromAct (ActImgVO actImgVO , java.sql.Connection con);
}
