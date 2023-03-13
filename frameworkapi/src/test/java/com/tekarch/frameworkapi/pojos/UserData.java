package com.tekarch.frameworkapi.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserData {

	public String getAccountnNumber() {
		return accountnNumber;
	}

	public void setAccountnNumber(String accountnNumber) {
		this.accountnNumber = accountnNumber;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("accountno")
	private String accountnNumber;
	
	@JsonProperty("departmentno")
	private String departmentNumber;
	
	@JsonProperty("salary")
	private String salary;
	
	@JsonProperty("pincode")
	private String pincode;
	
	@JsonProperty("userid")
	private String userID;
	
	@JsonProperty("id")
	private String id;
	
	
	
	
}
