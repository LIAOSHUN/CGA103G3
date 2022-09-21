package com.bookingorder.model;


public class BookingOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private Integer bookingID;
	private Integer memID;
	private java.sql.Date bookingDate;
	private String bookingStart;
	private String bookingEnd;
	private Integer boxID;
	private String bookingNote;
	private Integer bookingStatus;
	private String bookingStartValue;
	private String bookingEndValue;
	// 頁面顯示
//	private String bookingStatusShow;
	
	public BookingOrderVO() {
		
	}

	public BookingOrderVO(Integer bookingID, Integer memID, java.sql.Date bookingDate, String bookingStart, String bookingEnd,
			Integer boxID, String bookingNote, Integer bookingStatus) {
		super();
		this.bookingID = bookingID;
		this.memID = memID;
		this.bookingDate = bookingDate;
		this.bookingStart = bookingStart;
		this.bookingEnd = bookingEnd;
		this.boxID = boxID;
		this.bookingNote = bookingNote;
		this.bookingStatus = bookingStatus;
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

	public String getBookingStartValue() {
		return bookingStartValue;
	}

	public void setBookingStartValue(String bookingStartValue) {
		this.bookingStartValue = bookingStartValue;
	}

	public String getBookingEndValue() {
		return bookingEndValue;
	}

	public void setBookingEndValue(String bookingEndValue) {
		this.bookingEndValue = bookingEndValue;
	}

	public com.box.model.BoxVO getBoxVO() {
		com.box.model.BoxService boxSvc = new com.box.model.BoxService();
		com.box.model.BoxVO boxVO = boxSvc.getOneBox(boxID);
		return boxVO;
	}
	
//	// test for fetch api
//	public List<com.box.model.BoxVO> getAllBox() {
//		com.box.model.BoxService boxSvc = new com.box.model.BoxService();
//		List<com.box.model.BoxVO> listAllBox = boxSvc.getAllInfo();
//		return listAllBox;
//	}

}
