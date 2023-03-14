package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteDataTest extends BaseTestHelper {
//testcsae 3
	
	@Test()
	public void deleteUserData() {
		Response res= RestAssured
		.given()
			.contentType(ContentType.JSON)
		.when()
		 .delete("/delete/"+userIDCreated);
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status",is("success"));
		res.then().body("data", is(userIDCreated.toString()));
		String data = res.body().jsonPath().get("data");
		System.out.println("deleted the data for ID: " + data);
	}

}

