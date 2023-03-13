package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.UserData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class CreateDataTest extends BaseTestHelper {


	
	@Test()
	public void addUserData() {
		UserData data1 = getCreateData();
		Header tokenHeader=new Header("token", getToken());
		Response res= RestAssured
		.given()
			.contentType(ContentType.JSON)
			.body(data1)
			.header(tokenHeader)
		.when()
		 .post("/addData");
		res.then().statusCode(201);
		res.prettyPrint();
		res.then().body("status",is("success"));
	
	}

	private UserData getCreateData() {
		UserData data = new UserData();
		data.setAccountnNumber("DA-mike");
		data.setDepartmentNumber("1");
		data.setSalary("10000");
		data.setPincode("951298");
		return data;
	}

}

