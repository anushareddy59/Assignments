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


public class GetEmployeeList extends BaseTestHelper  {
//testcase 1
	@Test
	public void getUserData() {
		Response res= RestAssured
		.given()
		.when()
		 .get("/employees");
		res.prettyPrint();		
		res.then().body(JsonSchemaValidator.matchesJsonSchema(new File(ConfigConstants.SCHEMA_FILE)));
		res.then().statusCode(200);
		res.then().body("status",is("success"));
		/*
		 * String status = res.body().jsonPath().getString("status");
		 * assertEquals(status, "success");
		 */
		
		//data array in the response object is converted into the java array list.
		List<GetEmployeeData> emplist = res.body().jsonPath().getList("data");
		System.out.println("Number of employees: " + emplist.size());

	}

}
