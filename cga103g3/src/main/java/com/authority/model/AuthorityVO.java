package com.authority.model;

import java.io.Serializable;

public class AuthorityVO implements Serializable {
	private Integer empID;
	private Integer funcID;

	public Integer getEmpID() {
		return empID;
	}

	public void setEmpID(Integer empID) {
		this.empID = empID;
	}

	public Integer getFuncID() {
		return funcID;
	}

	public void setFuncID(Integer funcID) {
		this.funcID = funcID;
	}

}
