package com.product.model;

import java.util.*;

public interface ProductDAO_interface {
          public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(Integer PdID);
          public ProductVO findByPrimaryKey(Integer PdID);
          public List<ProductVO> getAll();
}
