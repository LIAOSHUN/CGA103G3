package com.product.model;

import java.util.*;

import com.productimg.model.ProductImgVO;

public interface ProductDAO_interface {
          public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(Integer pdID);
          public ProductVO findByPrimaryKey(Integer pdID);
          public List<ProductVO> getAll();
        //查詢某部門的員工(一對多)(回傳 Set)
//	      public Set<ProductVO> getProductByPdID(Integer pdID);
	    //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
	      public void insertWithProductImg(ProductVO productVO , List<ProductImgVO> list);
}
