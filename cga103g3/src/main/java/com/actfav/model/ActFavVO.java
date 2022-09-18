package com.actfav.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

public class ActFavVO implements Serializable{
	private Integer memID;
	private Integer actID;
	private LocalDateTime actFavDate;
	
	
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
	public LocalDateTime getActFavDate() {
		return actFavDate;
	}
	public void setActFavDate(LocalDateTime actFavDate) {
		this.actFavDate = actFavDate;
	}
	
	// for join actInfo from actID
	public ActVO getActVO() {
		ActService actSvc = new ActService();
		ActVO actVO = actSvc.getOneAct(actID);
		return actVO;
	}
}
