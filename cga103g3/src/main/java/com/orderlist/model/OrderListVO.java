package com.orderlist.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.member.model.MemberVO;
import com.cart.model.MemberJDBCDAO_cart;
import com.cart.model.MemberVO_cart;
import com.member.model.MemberService;

public class OrderListVO {
	private Integer ordNo;
	private Integer memID;
	private Integer coupNo;
	private Double ordOriPrice;
	private Double ordLastPrice;
	private Integer ordFee;
	private Integer ordStatus;
	private Timestamp ordCreate;
	private String recName;
	private String recAddress;
	private String recPhone;
	private Integer ordPick;
	
	public OrderListVO() {
		
	}
	
	public OrderListVO(Integer ordNo, Integer memID, Integer coupNo, Double ordOriPrice, Double ordLastPrice,
			Integer ordFee, Integer ordStatus, Timestamp ordCreate, String recName, String recAddress, String recPhone,
			Integer ordPick) {
		super();
		this.ordNo = ordNo;
		this.memID = memID;
		this.coupNo = coupNo;
		this.ordOriPrice = ordOriPrice;
		this.ordLastPrice = ordLastPrice;
		this.ordFee = ordFee;
		this.ordStatus = ordStatus;
		this.ordCreate = ordCreate;
		this.recName = recName;
		this.recAddress = recAddress;
		this.recPhone = recPhone;
		this.ordPick = ordPick;
	}
	public Integer getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getCoupNo() {
		return coupNo;
	}
	public void setCoupNo(Integer coupNo) {
		this.coupNo = coupNo;
	}
	public Double getOrdOriPrice() {
		return ordOriPrice;
	}
	public void setOrdOriPrice(Double ordOriPrice) {
		this.ordOriPrice = ordOriPrice;
	}
	public Double getOrdLastPrice() {
		return ordLastPrice;
	}
	public void setOrdLastPrice(Double ordLastPrice) {
		this.ordLastPrice = ordLastPrice;
	}
	public Integer getOrdFee() {
		return ordFee;
	}
	public void setOrdFee(Integer ordFee) {
		this.ordFee = ordFee;
	}
	public Integer getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(Integer ordStatus) {
		this.ordStatus = ordStatus;
	}
	public Timestamp getOrdCreate() {
		return ordCreate;
	}
	public void setOrdCreate(Timestamp ordCreate) {
		this.ordCreate = ordCreate;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getRecAddress() {
		return recAddress;
	}
	public void setRecAddress(String recAddress) {
		this.recAddress = recAddress;
	}
	public String getRecPhone() {
		return recPhone;
	}
	public void setRecPhone(String recPhone) {
		this.recPhone = recPhone;
	}
	public Integer getOrdPick() {
		return ordPick;
	}
	public void setOrdPick(Integer ordPick) {
		this.ordPick = ordPick;
	}
	
	// for join memName  from memID
//	public MemberVO getMemberVO(){
//		MemberService memberSvc = new MemberService();
//		MemberVO memberVo = memberSvc.getOneMember(memID);
//		return memberVo;
//	}
	// for join memName  from memID
	public MemberVO_cart getMemberVO(){
		MemberJDBCDAO_cart dao = new MemberJDBCDAO_cart();
		MemberVO_cart memberVO = dao.findByPrimaryKey(memID);
		return memberVO;
	}
}
