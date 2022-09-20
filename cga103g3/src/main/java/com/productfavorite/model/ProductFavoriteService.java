package com.productfavorite.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ProductFavoriteService {

	private ProductFavoriteDAO_interface dao;

	public ProductFavoriteService() {
		dao = new ProductFavoriteJDBCDAO();
	}
//	Integer pdID, 
	public ProductFavoriteVO addProductFavorite(Integer memID,Integer pdID) {

		ProductFavoriteVO productFavoriteVO = new ProductFavoriteVO();

		productFavoriteVO.setMemID(memID);
		productFavoriteVO.setPdID(pdID);
		dao.insert(productFavoriteVO);

		return productFavoriteVO;
	}


	public void deleteProductFavorite(Integer memID,Integer pdID) {
		dao.delete(memID,pdID);
	}


	public List<ProductFavoriteVO> getAll() {
		return dao.getAll();
	}
}
