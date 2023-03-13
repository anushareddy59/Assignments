package com.tekarch.frameworkapi.tests;


import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.constants.ConfigConstants;
import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.UserData;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;


public class GetDataTest extends BaseTestHelper  {

	@Test
	public void getUserData() {
		Header tokenHeader=new Header("token", getToken());
		Response res= RestAssured
		.given()
			.header(tokenHeader)
		.when()
		 .get("/getdata");
		res.prettyPrint();		
		res.then().body(JsonSchemaValidator.matchesJsonSchema(new File(ConfigConstants.SCHEMA_FILE)));
		List<UserData> userList = res.body().jsonPath().getList("");
		System.out.println("number of records="+userList.size());
		res.then().statusCode(200);
	}

}
