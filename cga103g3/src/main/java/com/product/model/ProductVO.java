package com.product.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductVO implements java.io.Serializable {
	private Integer PdID;
	private String PdName;
	private Integer PdPrice;
	private Integer PdAmount;
	private String PdDescription;
	private Integer PdStatus;
	private Integer PdStar;
	private Timestamp PdUpdate;
	public Integer getPdID() {
		return PdID;
	}
	public void setPdID(Integer pdID) {
		PdID = pdID;
	}
	public String getPdName() {
		return PdName;
	}
	public void setPdName(String pdName) {
		PdName = pdName;
	}
	public Integer getPdPrice() {
		return PdPrice;
	}
	public void setPdPrice(Integer pdPrice) {
		PdPrice = pdPrice;
	}
	public Integer getPdAmount() {
		return PdAmount;
	}
	public void setPdAmount(Integer pdAmount) {
		PdAmount = pdAmount;
	}
	public String getPdDescription() {
		return PdDescription;
	}
	public void setPdDescription(String pdDescription) {
		PdDescription = pdDescription;
	}
	public Integer getPdStatus() {
		return PdStatus;
	}
	public void setPdStatus(Integer pdStatus) {
		PdStatus = pdStatus;
	}
	public Integer getPdStar() {
		return PdStar;
	}
	public void setPdStar(Integer pdStar) {
		PdStar = pdStar;
	}
	public Timestamp getPdUpdate() {
		return PdUpdate;
	}
	public void setPdUpdate(Timestamp pdUpdate) {
		PdUpdate = pdUpdate;
	}

	}


