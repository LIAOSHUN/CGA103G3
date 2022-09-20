package com.productfavorite.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductFavoriteVO implements java.io.Serializable {
	private Integer pdID;
	private Integer memID;
	private Timestamp pdFavDate;
	
	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Timestamp getPdFavDate() {
		return pdFavDate;
	}
	public void setPdFavDate(Timestamp pdFavDate) {
		this.pdFavDate = pdFavDate;
	}
	
	}


