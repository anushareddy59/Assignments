package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.UserData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class DeleteDataTest extends BaseTestHelper {

	
	@Test()
	public void deleteUserData() {
		UserData data = getDeleteData();
		Header tokenHeader=new Header("token", getToken());
		Response res= RestAssured
		.given()
			.contentType(ContentType.JSON)
			.body(data)
			.header(tokenHeader)
		.when()
		 .delete("/deleteData");
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status",is("success"));
		
	}
	
	private UserData getDeleteData() {
		UserData data = new UserData();
		data.setId("NXOhSAeyfYxrDgJjOXVk");
		data.setUserID("d0EKEpsgBHDEjnOtkFRp");
		return data;
	}

}

