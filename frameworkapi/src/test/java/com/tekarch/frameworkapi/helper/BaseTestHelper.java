package com.tekarch.frameworkapi.helper;

import org.testng.annotations.BeforeClass;

import com.tekarch.frameworkapi.pojos.LoginCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTestHelper {
	
	@BeforeClass
	public void init() {
		RestAssured.baseURI="https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	}
	
	public String getToken() {
		LoginCredentials credPojo = new LoginCredentials("anushareddy.59@gmail.com", "anushareddy123"); 
		Response res = RestAssured
				.given()
					.log().headers()
					.contentType(ContentType.JSON)
					.body(credPojo)// serialization
				.when()
					.post("/login");
		res.then().log().all();
		res.then().statusCode(201);
		// res.then().assertThat().statusCode(201);
		String token= res.body().jsonPath().getString("[0].token");
		System.out.println("token="+token);
		return token;
	}
	
}
