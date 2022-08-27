package com.membergrade.model;


public class MemberGradeVO {
	private Integer gradeID;
	private  String gradeName;
	private  double gradeDiscount;
	public Integer getGradeID() {
		return gradeID;
	}
	public void setGradeID(Integer gradeID) {
		this.gradeID = gradeID;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public double getGradeDiscount() {
		return gradeDiscount;
	}
	public void setGradeDiscount(Double gradeDiscount) {
		this.gradeDiscount = gradeDiscount;
	}
	
}
