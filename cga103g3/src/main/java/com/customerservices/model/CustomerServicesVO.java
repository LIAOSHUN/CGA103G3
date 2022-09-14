package com.customerservices.model;

import java.sql.Timestamp;

public class CustomerServicesVO {
	private Integer csNum;
	private Integer memID;
	private Integer empID;
	private Integer csStatus;
	private String csContent;
	private byte[] csImg;
	private Timestamp csTime;

	public Integer getCsNum() {
		return csNum;
	}

	public void setCsNum(Integer csNum) {
		this.csNum = csNum;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public Integer getEmpID() {
		return empID;
	}

	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	public Integer getCsStatus() {
		return csStatus;
	}

	public void setCsStatus(Integer csStatus) {
		this.csStatus = csStatus;
	}

	public String getCsContent() {
		return csContent;
	}

	public void setCsContent(String csContent) {
		this.csContent = csContent;
	}

	public byte[] getCsImg() {
		return csImg;
	}

	public void setCsImg(byte[] csImg) {
		this.csImg = csImg;
	}

	public Timestamp getCsTime() {
		return csTime;
	}

	public void setCsTime(Timestamp csTime) {
		this.csTime = csTime;
	}
	
}
