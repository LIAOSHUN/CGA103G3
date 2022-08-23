package com.orderdetail.model;

import java.io.Serializable;

public class OrderDetailVO implements Serializable {
 	private Integer ordNo;
 	private Integer pdID;
 	private Integer itemSales;
 	private Integer price;
 	
 	public OrderDetailVO() {
 		
 	}

	public OrderDetailVO(Integer ordNo, Integer pdID, Integer itemSales, Integer price) {
		super();
		this.ordNo = ordNo;
		this.pdID = pdID;
		this.itemSales = itemSales;
		this.price = price;
	}
	public Integer getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(Integer ordNo) {
		this.ordNo = ordNo;
	}
	public Integer getPdID() {
		return pdID;
	}
	public void setPdID(Integer pdID) {
		this.pdID = pdID;
	}
	public Integer getItemSales() {
		return itemSales;
	}
	public void setItemSales(Integer itemSales) {
		this.itemSales = itemSales;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
 	
 	
 	
}
