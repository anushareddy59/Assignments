package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.constants.ConfigConstants;
import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.GetEmployeeData;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;



public class GetEmployeeDetails extends BaseTestHelper {
//testcase 5
	@Test
	public void getUserData() {
		int id = 2;
		Response res= RestAssured
		.given()
		.when()
		 .get("/employee/"+ id);
		res.prettyPrint();		
		res.then().statusCode(200);
		res.then().body("status",is("success"));
		GetEmployeeData d =	res.body().jsonPath().getObject("data",GetEmployeeData.class );
		
		assertEquals(d.getEmployeeAge().toString(), "63");
		assertEquals(d.getEmployeeName().toString(), "Garrett Winters");
		assertEquals(d.getEmployeeSalary().toString(), "170750");

	}

}
