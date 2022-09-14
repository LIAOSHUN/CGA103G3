package com.producttype.model;
import java.sql.Date;

public class ProductTypeVO implements java.io.Serializable{
	private Integer pdTypeID;
	private String pdTypeName;
	
	public Integer getPdTypeID() {
		return pdTypeID;
	}
	public void setPdTypeID(Integer pdTypeID) {
		this.pdTypeID = pdTypeID;
	}
	public String getPdTypeName() {
		return pdTypeName;
	}
	public void setPdTypeName(String pdTypeName) {
		this.pdTypeName = pdTypeName;
	}
	
	
}
