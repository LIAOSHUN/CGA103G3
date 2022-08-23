package com.memcoupon.model;

import java.sql.Timestamp;

public class MemCouponVO {
	private Integer coupNo;
	private Integer memID;
	private Integer coupTypeNo;
	private Integer coupStatus;
	private Timestamp coupExpDate;
	private Timestamp coupGetDate;
	
	public MemCouponVO() {
		
	}
	
	public MemCouponVO(Integer coupNo, Integer memID, Integer coupTypeNo, Integer coupStatus, Timestamp coupExpDate,
			Timestamp coupGetDate) {
		super();
		this.coupNo = coupNo;
		this.memID = memID;
		this.coupTypeNo = coupTypeNo;
		this.coupStatus = coupStatus;
		this.coupExpDate = coupExpDate;
		this.coupGetDate = coupGetDate;
	}
	public Integer getCoupNo() {
		return coupNo;
	}
	public void setCoupNo(Integer coupNo) {
		this.coupNo = coupNo;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getCoupTypeNo() {
		return coupTypeNo;
	}
	public void setCoupTypeNo(Integer coupTypeNo) {
		this.coupTypeNo = coupTypeNo;
	}
	public Integer getCoupStatus() {
		return coupStatus;
	}
	public void setCoupStatus(Integer coupStatus) {
		this.coupStatus = coupStatus;
	}
	public Timestamp getCoupExpDate() {
		return coupExpDate;
	}
	public void setCoupExpDate(Timestamp coupExpDate) {
		this.coupExpDate = coupExpDate;
	}
	public Timestamp getCoupGetDate() {
		return coupGetDate;
	}
	public void setCoupGetDate(Timestamp coupGetDate) {
		this.coupGetDate = coupGetDate;
	}
	
	
}
