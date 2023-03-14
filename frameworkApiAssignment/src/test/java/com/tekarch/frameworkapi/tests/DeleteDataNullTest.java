package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteDataNullTest extends BaseTestHelper {
//testcsae 4
	
	@Test()
	public void deleteUserData() {
		Response res= RestAssured
		.given()
			.contentType(ContentType.JSON)
		.when()
		 .delete("/delete/"+0);
		res.then().statusCode(400);
		res.prettyPrint();
		res.then().body("status",is("error"));
		
		//fetch the message data and print it to console
		String data = res.body().jsonPath().get("data");
		System.out.println("deleted the data for ID: " + data);
	}

}

