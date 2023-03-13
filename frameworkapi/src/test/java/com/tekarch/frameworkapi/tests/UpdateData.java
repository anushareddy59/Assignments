package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.helper.UserDataHelper;
import com.tekarch.frameworkapi.pojos.UserData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class UpdateData extends BaseTestHelper {
	
	@Test()
	public void updateUserData() {
		UserData updateData = UserDataHelper.getUpdateData();
		Header tokenHeader=new Header("token", getToken());
		Response res= RestAssured
		.given()
			.contentType(ContentType.JSON)
			//.body("{\"accountno\":\"DA-postman\",\"departmentno\":\"9\",\"salary\":\"1000\",\"pincode\":\"242435\",\"userid\":\"zhFI4oQlHjP2cn3mW3GP\",\"id\":\"DUJsQJiQ1i2pk86naW8X\"}")
			.body(updateData)
			.header(tokenHeader)
		.when()
		 .put("/updateData");
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status",is("success"));
		
	}

}

