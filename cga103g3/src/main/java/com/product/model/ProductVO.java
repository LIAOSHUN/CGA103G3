package com.product.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable {
	private Integer pdID;
	private String pdName;
	private Integer pdTypeID;
	private Integer pdPrice;
	private Integer pdAmount;
	private String pdDescription;
	private Integer pdStatus;
	private Integer pdStar;
	private Timestamp pdUpdate;
	
	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getPdTypeID() {
		return pdTypeID;
	}
	public void setPdTypeID(Integer pdTypeID) {
		this.pdTypeID = pdTypeID;
	}
	public Integer getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(Integer pdPrice) {
		this.pdPrice = pdPrice;
	}
	public Integer getPdAmount() {
		return pdAmount;
	}
	public void setPdAmount(Integer pdAmount) {
		this.pdAmount = pdAmount;
	}
	public String getPdDescription() {
		return pdDescription;
	}
	public void setPdDescription(String pdDescription) {
		this.pdDescription = pdDescription;
	}
	public Integer getPdStatus() {
		return pdStatus;
	}
	public void setPdStatus(Integer pdStatus) {
		this.pdStatus = pdStatus;
	}
	public Integer getPdStar() {
		return pdStar;
	}
	public void setPdStar(Integer pdStar) {
		this.pdStar = pdStar;
	}
	public Timestamp getPdUpdate() {
		return pdUpdate;
	}
	public void setPdUpdate(Timestamp pdUpdate) {
		this.pdUpdate = pdUpdate;
	}
	public com.producttype.model.ProductTypeVO getProductTypeVO() {
	    com.producttype.model.ProductTypeService productTypeSvc = new com.producttype.model.ProductTypeService();
	    com.producttype.model.ProductTypeVO productTypeVO = productTypeSvc.getOneProducttype(pdTypeID);
	    return productTypeVO;
    }
	}


