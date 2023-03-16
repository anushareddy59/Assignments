package com.tekarch.frameworkapi.tests;

import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.testng.annotations.Test;

import com.tekarch.frameworkapi.constants.ConfigConstants;
import com.tekarch.frameworkapi.helper.BaseTestHelper;
import com.tekarch.frameworkapi.pojos.CreateEmployeeData;
import com.tekarch.frameworkapi.pojos.GetEmployeeData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class EndToEndApiTest extends BaseTestHelper {

	// testcase 1
	@Test(priority = 0)
	public void getAllEmployees() {
		Response res = RestAssured.given().when().get("/employees");
		res.prettyPrint();
		res.then().body(JsonSchemaValidator.matchesJsonSchema(new File(ConfigConstants.SCHEMA_FILE)));
		res.then().statusCode(200);
		res.then().body("status", is("success"));
		/*
		 * String status = res.body().jsonPath().getString("status");
		 * assertEquals(status, "success");
		 */

		// data array in the response object is converted into the java array list.
		// response in pojo
		List<GetEmployeeData> emplist = res.body().jsonPath().getList("data");
		System.out.println("Number of employees: " + emplist.size());

	}

	// testcsae 2

	@Test(priority = 1)
	public void addUserData() {
		CreateEmployeeData inputData = new CreateEmployeeData();
		inputData.setName("mike2");
		inputData.setAge("24");
		inputData.setSalary("100001");

		Response res = RestAssured.given().contentType(ContentType.JSON).body(inputData).when().post("/create");
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status", is("success"));
		CreateEmployeeData usrData = res.body().jsonPath().getObject("data", CreateEmployeeData.class);
		assertEquals(inputData.getAge(), usrData.getAge());
		assertEquals(inputData.getName(), usrData.getName());
		assertEquals(inputData.getSalary(), usrData.getSalary());
		System.out.println("Saved user data ID is : " + usrData.getId() + ", Name:" + usrData.getName());
		userIDCreated = usrData.getId();

	}
	
	@Test(priority = 2)
	public void deleteUserData() {
		Response res = RestAssured.given().contentType(ContentType.JSON).when().delete("/delete/" + userIDCreated);
		res.then().statusCode(200);
		res.prettyPrint();
		res.then().body("status", is("success"));
		res.then().body("data", is(userIDCreated.toString()));
		String data = res.body().jsonPath().get("data");
		System.out.println("deleted the data for ID: " + data);
	}
	

	@Test(priority = 3)
	public void deleteNullUserData() {
		Response res = RestAssured.
				given().contentType(ContentType.JSON)
				.when().delete("/delete/" + 0);
		res.then().statusCode(400);
		res.prettyPrint();
		res.then().body("status", is("error"));

		// fetch the message data and print it to console
		String data = res.body().jsonPath().get("data");
		System.out.println("deleted the data for ID: " + data);
	}

//testcase 5
	@Test(priority = 4)
	public void getEmployeeDataByID() {
		int id = 2;
		Response res = RestAssured.given().when().get("/employee/" + id);
		res.prettyPrint();
		res.then().statusCode(200);
		res.then().body("status", is("success"));
		GetEmployeeData d = res.body().jsonPath().getObject("data", GetEmployeeData.class);

		assertEquals(d.getEmployeeAge().toString(), "63");
		assertEquals(d.getEmployeeName().toString(), "Garrett Winters");
		assertEquals(d.getEmployeeSalary().toString(), "170750");

	}

}
