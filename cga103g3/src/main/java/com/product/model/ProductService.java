package com.product.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.productimg.model.ProductImgVO;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}
//	Integer pdID, 
	public ProductVO addProduct(String pdName, Integer pdPrice,
			Integer pdAmount, String pdDescription, Integer pdStatus,
			Integer pdStar) {

		ProductVO productVO = new ProductVO();

//		productVO.setPdID(pdID);
		productVO.setPdName(pdName);
		productVO.setPdPrice(pdPrice);
		productVO.setPdAmount(pdAmount);
		productVO.setPdDescription(pdDescription);
		productVO.setPdStatus(pdStatus);
		productVO.setPdStar(pdStar);
//		productVO.setPdUpdate(pdUpdate);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer pdID, String pdName, Integer pdPrice,
			Integer pdAmount, String pdDescription, Integer pdStatus,
			Integer pdStar) {
//		,Timestamp pdUpdate
		
		ProductVO productVO = new ProductVO();
		
		productVO.setPdID(pdID);
		productVO.setPdName(pdName);
		productVO.setPdPrice(pdPrice);
		productVO.setPdAmount(pdAmount);
		productVO.setPdDescription(pdDescription);
		productVO.setPdStatus(pdStatus);
		productVO.setPdStar(pdStar);
//		productVO.setPdUpdate(pdUpdate);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer pdID) {
		dao.delete(pdID);
	}

	public ProductVO getOneProduct(Integer pdID) {
		return dao.findByPrimaryKey(pdID);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
	
	
	public ProductVO addProductWithProductImg(String pdName, Integer pdTypeID,Integer pdPrice,
			Integer pdAmount,
			String pdDescription,
			Integer pdStatus,
			Integer pdStar,
			byte[] pdImgCover,String pdImgCoverName,
			byte[] pdImg01,String pdImg01Name,
			byte[] pdImg02,String pdImg02Name

			) {

		ProductVO productVO = new ProductVO();
		ProductImgVO productImgVO = new ProductImgVO();
		ProductImgVO productImgXX = new ProductImgVO();
		ProductImgVO productImgYY = new ProductImgVO();


		productVO.setPdName(pdName);
		productVO.setPdTypeID(pdTypeID);
		productVO.setPdPrice(pdPrice);
		productVO.setPdAmount(pdAmount);
		productVO.setPdDescription(pdDescription);
		productVO.setPdStatus(pdStatus);
		productVO.setPdStar(pdStar);
		
		productImgVO.setPdImg(pdImgCover);
		productImgVO.setPdImgName(pdImgCoverName);
		
		productImgXX.setPdImg(pdImg01);
		productImgXX.setPdImgName(pdImg01Name);
		
		productImgYY.setPdImg(pdImg02);
		productImgYY.setPdImgName(pdImg02Name);

		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		list.add(productImgVO);
		list.add(productImgXX);
		list.add(productImgYY);

		
		
		
		dao.insertWithProductImg(productVO, list);

		return productVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
