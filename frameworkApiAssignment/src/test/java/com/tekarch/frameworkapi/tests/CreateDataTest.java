package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.CreateEmployeeData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateDataTest extends BaseTestHelper {
//testcase 3

	
	@Test()
	public void addUserData() {
		CreateEmployeeData inputData = new CreateEmployeeData();
		inputData.setName("mike2");
		inputData.setAge("24");
		inputData.setSalary("100001");
		
		Response res= RestAssured
		.given()
			.contentType(ContentType.JSON)
			.body(inputData)
		.when()
		 .post("/create");
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status",is("success"));
		CreateEmployeeData usrData  = res.body().jsonPath().getObject("data", CreateEmployeeData.class);
		assertEquals(inputData.getAge(), usrData.getAge());
		assertEquals(inputData.getName(), usrData.getName());
		assertEquals(inputData.getSalary(), usrData.getSalary());
		System.out.println("Saved user data ID is : " + usrData.getId() +", Name:" + usrData.getName());
		userIDCreated = usrData.getId();
	
	}


}

