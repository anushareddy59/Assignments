package com.tekarch.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = { "src/test/resources/login.feature" },
		glue = { "com.tekarch.steps" },
		monochrome = true,
		//dryRun = false,
		plugin = { "pretty", "html:target/sf_login_cucumber.html", "json:target/sf_login_cucumber.json" } 
		)

public class LoginRunner extends AbstractTestNGCucumberTests {

}
