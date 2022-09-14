package com.producttype.model;

import java.util.List;

public class ProductTypeService {

	private ProductTypeDAO_interface dao;

	public ProductTypeService() {
		dao = new ProductTypeJDBCDAO();
	}
	
	public ProductTypeVO addProductType(String pdTypeName) {

		ProductTypeVO productTypeVO = new ProductTypeVO();

		productTypeVO.setPdTypeName(pdTypeName);
		dao.insert(productTypeVO);

		return productTypeVO;
	}

	public ProductTypeVO updateProductType(Integer pdTypeID,String pdTypeName) {
		
		ProductTypeVO productTypeVO = new ProductTypeVO();
		
		productTypeVO.setPdTypeID(pdTypeID);
		productTypeVO.setPdTypeName(pdTypeName);
		dao.update(productTypeVO);

		return productTypeVO;
	}

	public void deleteProductType(Integer pdTypeID) {
		dao.delete(pdTypeID);
	}

	public ProductTypeVO getOneProducttype(Integer pdTypeID) {
		return dao.findByPrimaryKey(pdTypeID);
	}

	public List<ProductTypeVO> getAll() {
		return dao.getAll();
	}
}
