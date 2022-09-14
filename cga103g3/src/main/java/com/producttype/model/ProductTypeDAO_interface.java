package com.producttype.model;

import java.util.*;

public interface ProductTypeDAO_interface {
          public void insert(ProductTypeVO productTypeVO);
          public void update(ProductTypeVO productTypeVO);
          public void delete(Integer pdTypeID);
          public ProductTypeVO findByPrimaryKey(Integer pdTypeID);
          public List<ProductTypeVO> getAll();
}
