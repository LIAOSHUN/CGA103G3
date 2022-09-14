package com.productimg.model;

import java.util.*;

public interface ProductImgDAO_interface {
          public void insert(ProductImgVO productimgVO);
          public void update(ProductImgVO productimgVO);
          public void delete(Integer PdImgId);
          public ProductImgVO findByPrimaryKey(Integer PdImgId);
          public List<ProductImgVO> getAll();
        //同時新增部門與員工 (實務上並不常用, 但,可用在訂單主檔與明細檔一次新增成功)
          public void insert2 (ProductImgVO productImgVO , java.sql.Connection con);
}
