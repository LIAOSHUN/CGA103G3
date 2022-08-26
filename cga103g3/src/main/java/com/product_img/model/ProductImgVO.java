package com.product_img.model;
import java.sql.Blob;
import java.sql.Date;


public class ProductImgVO implements java.io.Serializable{
	private Integer PdImgId;
	private Integer PdId;
	private Blob PdImg;
	private String PdImgName;
	
	
	public Integer getPdImgId() {
		return PdImgId;
	}
	public void setPdImgId(Integer pdImgId) {
		PdImgId = pdImgId;
	}
	public Integer getPdId() {
		return PdId;
	}
	public void setPdId(Integer pdId) {
		PdId = pdId;
	}
	public Blob getPdImg() {
		return PdImg;
	}
	public void setPdImg(Blob pdImg) {
		PdImg = pdImg;
	}
	public String getPdImgName() {
		return PdImgName;
	}
	public void setPdImgName(String pdImgName) {
		PdImgName = pdImgName;
	}
}
