package com.actregis.model;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class ActRegisVO implements Serializable{
	private Integer memID;
	private Integer actID;
	private LocalDateTime regisTime;
	private Integer actNum;
	private Integer actFee;
	private Integer feeStatus;
	private Integer regisStatus;
	private String actReview;
	private Integer satisfaction;
	private Date reviewDate;
	
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
	public LocalDateTime getRegisTime() {
		return regisTime;
	}
	public void setRegisTime(LocalDateTime regisTime) {
		this.regisTime = regisTime;
	}
	public Integer getActNum() {
		return actNum;
	}
	public void setActNum(Integer actNum) {
		this.actNum = actNum;
	}
	public Integer getActFee() {
		return actFee;
	}
	public void setActFee(Integer actFee) {
		this.actFee = actFee;
	}
	public Integer getFeeStatus() {
		return feeStatus;
	}
	public void setFeeStatus(Integer feeStatus) {
		this.feeStatus = feeStatus;
	}
	public Integer getRegisStatus() {
		return regisStatus;
	}
	public void setRegisStatus(Integer regisStatus) {
		this.regisStatus = regisStatus;
	}
	public String getActReview() {
		return actReview;
	}
	public void setActReview(String actReview) {
		this.actReview = actReview;
	}
	public Integer getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	// for join actTitle from actID
	public ActVO getActVO() {
		ActService actSvc = new ActService();
		ActVO actVO = actSvc.getOneAct(actID);
		return actVO;
	}
	
	// for join memAccount and memName from memID
	public MemberVO getMemberVO() {
		MemberService memSvc = new MemberService();
		MemberVO memberVO = memSvc.getOneMember(memID);
		return memberVO;
	}
}
