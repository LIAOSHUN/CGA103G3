package com.actfav.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActFavVO implements Serializable{
	private Integer memID;
	private Integer actID;
	private Timestamp actFavDate;
	
	
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Timestamp getActFavDate() {
		return actFavDate;
	}
	public void setActFavDate(Timestamp actFavDate) {
		this.actFavDate = actFavDate;
	}
}
