package com.tekarch.frameworkapi.helper;

import org.testng.annotations.BeforeClass;

import com.tekarch.frameworkapi.utils.PropertiesUtil;

import io.restassured.RestAssured;

public class BaseTestHelper {
	
	protected static Integer userIDCreated;
	
	
	@BeforeClass
	public void init() {
		PropertiesUtil.initProperties();
		RestAssured.baseURI=PropertiesUtil.getProperty("url");
	}
	
	
}
