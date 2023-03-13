package com.tekarch.frameworkapi.helper;

import org.testng.annotations.BeforeClass;

import com.tekarch.frameworkapi.pojos.LoginCredentials;
import com.tekarch.frameworkapi.utils.PropertiesUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTestHelper {
	
	@BeforeClass
	public void init() {
		PropertiesUtil.initProperties();
		RestAssured.baseURI=PropertiesUtil.getProperty("url");
	}
	
	public String getToken() {
		String userName = PropertiesUtil.getProperty("username");
		String password = PropertiesUtil.getProperty("password");
		LoginCredentials credPojo = new LoginCredentials(userName, password); 
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
