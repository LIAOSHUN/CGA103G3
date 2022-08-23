package com.act.model;

import java.sql.Timestamp;

public class ActVO implements java.io.Serializable {
	private Integer actID;
	private Integer storeID;
	private String actTitle;
	private String actDescription;
	private Timestamp actDate;
	private Integer actStatus;
	private Integer actFee;
	private Integer actRegistration;
	private Timestamp actTimeStart;
	private Timestamp actTimeEnd;
	private Integer regisMax;
	
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getStoreID() {
		return storeID;
	}
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	public String getActTitle() {
		return actTitle;
	}
	public void setActTitle(String actTitle) {
		this.actTitle = actTitle;
	}
	public String getActDescription() {
		return actDescription;
	}
	public void setActDescription(String actDescription) {
		this.actDescription = actDescription;
	}
	public Timestamp getActDate() {
		return actDate;
	}
	public void setActDate(Timestamp actDate) {
		this.actDate = actDate;
	}
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getActRegistration() {
		return actRegistration;
	}
	public void setActRegistration(Integer actRegistration) {
		this.actRegistration = actRegistration;
	}
	public Timestamp getActTimeStart() {
		return actTimeStart;
	}
	public void setActTimeStart(Timestamp actTimeStart) {
		this.actTimeStart = actTimeStart;
	}
	public Timestamp getActTimeEnd() {
		return actTimeEnd;
	}
	public void setActTimeEnd(Timestamp actTimeEnd) {
		this.actTimeEnd = actTimeEnd;
	}
	public Integer getRegisMax() {
		return regisMax;
	}
	public void setRegisMax(Integer regisMax) {
		this.regisMax = regisMax;
	}
}

