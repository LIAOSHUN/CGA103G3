package com.blocklist.model;

import java.sql.Date;

public class BlockListVO {
	private Integer blockerID;
	private Integer blockerIDBan;
	private Date blockerDate;

	public Integer getBlockerID() {
		return blockerID;
	}

	public void setBlockerID(Integer blockerID) {
		this.blockerID = blockerID;
	}

	public Integer getBlockerIDBan() {
		return blockerIDBan;
	}

	public void setBlockerIDBan(Integer blockerIDBan) {
		this.blockerIDBan = blockerIDBan;
	}

	public Date getBlockerDate() {
		return blockerDate;
	}

	public void setBlockerDate(Date blockerDate) {
		this.blockerDate = blockerDate;
	}
}
