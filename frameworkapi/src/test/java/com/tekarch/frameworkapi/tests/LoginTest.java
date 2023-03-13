package com.tekarch.frameworkapi.tests;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.LoginCredentials;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LoginTest  extends BaseTestHelper {
	
	@Test
	public void logintoApi() {
		
		LoginCredentials credPojo = new LoginCredentials("anushareddy.59@gmail.com", "anushareddy123"); 

		Response res = RestAssured
				.given()
					.log().headers()
					.contentType(ContentType.JSON)
					//.body(map)// serialization
					.body(credPojo)
				.when()
					.post("/login");
		res.then().log().all();
		res.then().statusCode(201);
		res.then().assertThat().statusCode(201);
		String token= res.body().jsonPath().getString("[0].token");
		System.out.println("token="+token);
			
	}

}
