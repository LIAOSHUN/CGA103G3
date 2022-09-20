package com.productfavorite.model;

import java.util.*;

import com.productimg.model.ProductImgVO;

public interface ProductFavoriteDAO_interface {
          public void insert(ProductFavoriteVO productFavoriteVO);
          public void delete(Integer memID,Integer pdID);
//          public ProductFavoriteVO findByPrimaryKey(Integer memID);
          public List<ProductFavoriteVO> getAll();
        //查詢某部門的員工(一對多)(回傳 Set)
//	      public Set<ProductVO> getProductByPdID(Integer pdID);
	    //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)


}
