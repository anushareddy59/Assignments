package com.tekarch.frameworkapi.helper;

import com.tekarch.frameworkapi.pojos.UserData;

public class UserDataHelper {

	public static UserData getDeleteData() {
		UserData data = new UserData();
		data.setId("NXOhSAeyfYxrDgJjOXVk");
		data.setUserID("d0EKEpsgBHDEjnOtkFRp");
		return data;
	}
	
	public static UserData getCreateData() {
		UserData data = new UserData();
		data.setAccountnNumber("DA-mike");
		data.setDepartmentNumber("1");
		data.setSalary("10000");
		data.setPincode("951298");
		return data;
	}
	
	
	public static UserData getUpdateData() {
		UserData data = new UserData();
		data.setAccountnNumber("DA-david");
		data.setDepartmentNumber("2");
		data.setSalary("10001");
		data.setPincode("9512987");
		data.setId("NXOhSAeyfYxrDgJjOXVk");
		data.setUserID("d0EKEpsgBHDEjnOtkFRp");
		return data;
	}
}
