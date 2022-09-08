package com.bookingset.model;


public class BookingSetVO {
	// BookingOrderVO
	private Integer bookingID;
	private Integer memID;
	private java.sql.Date bookingDate;
	private String bookingStart;
	private String bookingEnd;
	private Integer boxID;
	private String bookingNote;
	private Integer bookingStatus;

	// BoxVO
	private Integer boxNO;
//	private Integer storeID;
	private Integer boxTypeID;
	private Integer boxCapcity;
	private Integer boxPrice;
	private String boxDescription;
	private String boxBkStart;
	private String boxBkEnd;
	private byte[] boxImg;
	
	// StoreVO
	private Integer storeID;
	private String storeName;
	private String storeOff;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreOff() {
		return storeOff;
	}

	public void setStoreOff(String storeOff) {
		this.storeOff = storeOff;
	}

	public Integer getBookingID() {
		return bookingID;
	}

	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}

	public Integer getMemID() {
		return memID;
	}

	public void setMemID(Integer memID) {
		this.memID = memID;
	}

	public java.sql.Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(java.sql.Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getBookingStart() {
		return bookingStart;
	}

	public void setBookingStart(String bookingStart) {
		this.bookingStart = bookingStart;
	}

	public String getBookingEnd() {
		return bookingEnd;
	}

	public void setBookingEnd(String bookingEnd) {
		this.bookingEnd = bookingEnd;
	}

	public Integer getBoxID() {
		return boxID;
	}

	public void setBoxID(Integer boxID) {
		this.boxID = boxID;
	}

	public String getBookingNote() {
		return bookingNote;
	}

	public void setBookingNote(String bookingNote) {
		this.bookingNote = bookingNote;
	}

	public Integer getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(Integer bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Integer getBoxNO() {
		return boxNO;
	}

	public void setBoxNO(Integer boxNO) {
		this.boxNO = boxNO;
	}

	public Integer getStoreID() {
		return storeID;
	}

	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}

	public Integer getBoxTypeID() {
		return boxTypeID;
	}

	public void setBoxTypeID(Integer boxTypeID) {
		this.boxTypeID = boxTypeID;
	}

	public Integer getBoxCapcity() {
		return boxCapcity;
	}

	public void setBoxCapcity(Integer boxCapcity) {
		this.boxCapcity = boxCapcity;
	}

	public Integer getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(Integer boxPrice) {
		this.boxPrice = boxPrice;
	}

	public String getBoxDescription() {
		return boxDescription;
	}

	public void setBoxDescription(String boxDescription) {
		this.boxDescription = boxDescription;
	}

	public String getBoxBkStart() {
		return boxBkStart;
	}

	public void setBoxBkStart(String boxBkStart) {
		this.boxBkStart = boxBkStart;
	}

	public String getBoxBkEnd() {
		return boxBkEnd;
	}

	public void setBoxBkEnd(String boxBkEnd) {
		this.boxBkEnd = boxBkEnd;
	}

	public byte[] getBoxImg() {
		return boxImg;
	}

	public void setBoxImg(byte[] boxImg) {
		this.boxImg = boxImg;
	}

	public BookingSetVO() {

	}

	public BookingSetVO(Integer bookingID, Integer memID, java.sql.Date bookingDate, String bookingStart, String bookingEnd,
			Integer boxID, String bookingNote, Integer bookingStatus, Integer boxNO, Integer storeID, Integer boxTypeID,
			Integer boxCapcity, Integer boxPrice, String boxDescription, String boxBkStart, String boxBkEnd,
			byte[] boxImg) {
		super();
		this.bookingID = bookingID;
		this.memID = memID;
		this.bookingDate = bookingDate;
		this.bookingStart = bookingStart;
		this.bookingEnd = bookingEnd;
		this.boxID = boxID;
		this.bookingNote = bookingNote;
		this.bookingStatus = bookingStatus;
		this.boxNO = boxNO;
		this.storeID = storeID;
		this.boxTypeID = boxTypeID;
		this.boxCapcity = boxCapcity;
		this.boxPrice = boxPrice;
		this.boxDescription = boxDescription;
		this.boxBkStart = boxBkStart;
		this.boxBkEnd = boxBkEnd;
		this.boxImg = boxImg;
	}

}
