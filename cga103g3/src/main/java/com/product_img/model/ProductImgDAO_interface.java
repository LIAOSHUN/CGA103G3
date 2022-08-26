package com.product_img.model;

import java.util.*;

public interface ProductImgDAO_interface {
          public void insert(ProductImgVO productimgVO);
          public void update(ProductImgVO productimgVO);
          public void delete(Integer PdImgId);
          public ProductImgVO findByPrimaryKey(Integer PdImgId);
          public List<ProductImgVO> getAll();
}
