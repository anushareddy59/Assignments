package org.tekarch.SalesForceTests;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.tekarch.salesForceHelperTest.SalesForceHepler;
import org.tekarch.utility.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(org.tekarch.utility.TestEventListenersUtility.class)
public class SFDCLoginTests extends SalesForceHepler {

	//Testcase 1
	@Test
	public void errorLoginEmptyPassword() throws Exception {
		logger.info("inside loginto SalesForce method");
		extentreport.logTestInfo("inside loginto SalesForce method");
		PropertiesLoader propertiesloader =new PropertiesLoader();
		Properties propertyFile =propertiesloader.loadFile("inputProperties");
		String userName = propertyFile.getProperty("username");
		String password = "";

		WebElement userBox = findWebElementByID("username");
		waitUntilElementIsVisible(userBox);
		sendKeys(userBox, userName);
		WebElement passwordBox = findWebElementByID("password");
		sendKeys(passwordBox, password);
		waitUntilElementIsVisible(passwordBox);
		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		
		WebElement errorMsg = findWebElementByID("error");
		String actualMsg = errorMsg.getText();
		String expectedMsg = "Please enter your password.";
		Assert.assertEquals(expectedMsg,actualMsg );
		extentreport.logTestInfo("method ended");
	}
	
	

	//Testcase 2
	@Test
	public void login() throws Exception {
		loginIntoSF();
		
		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		String actual = profileIcon.getText();
		String expected = "Anu abcd";
		
		Assert.assertEquals(expected,actual);
		extentreport.logTestInfo("method ended");
	}



//Testcase 3
	@Test
	public void rememberUserNameCheck() throws Exception {
		logger.info("inside logintoSalesForce method");
		extentreport.logTestInfo("inside logintoSalesForce method");
		PropertiesLoader propertiesloader =new PropertiesLoader();
		Properties propertyFile =propertiesloader.loadFile("inputProperties");
		
		String userName = propertyFile.getProperty("username");
		String password = propertyFile.getProperty("password");

		//Thread.sleep(2000);
		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		Thread.sleep(1000);
		WebElement passwordBox = findWebElementByID("password");
		passwordBox.sendKeys(password);

		
		WebElement radioButton = findWebElementByXPath("//*[@id=\"rememberUn\"]");
		radioButton.click();

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		waitUntilPageLoads();
		WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
		profileIcon.click();
		
		WebElement logOutButton = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
		logOutButton.click();
		waitImplicitly();
		WebElement userBox1 = findWebElementByID("username");
		String actual = "anu@tekarch.com";
		String expected = userBox1.getAttribute("value");
		Assert.assertEquals(expected,actual);
		extentreport.logTestInfo("method ended");
	}
	
	//testcase 4 a
	@Test
	public void forgotPasswordCheck() throws Exception {
		logger.info("inside logintoSalesForce method");
		extentreport.logTestInfo("inside logintoSalesForce method");
		PropertiesLoader propertiesloader =new PropertiesLoader();
		Properties propertyFile =propertiesloader.loadFile("inputProperties");
		String userName = propertyFile.getProperty("username");
		String password = propertyFile.getProperty("password");

		WebElement userBox = findWebElementByID("username");
		userBox.sendKeys(userName);
		WebElement forgotPassword = findWebElementByXPath("//*[@id=\"forgot_password_link\"]");

		forgotPassword.click();

		WebElement userNameForgot = findWebElementByXPath("//*[@id=\"header\"]");
		String expected = "Forgot Your Password";
		String actual = userNameForgot.getText();
		Assert.assertEquals(expected,actual);
	
		extentreport.logTestInfo("method ended");
	}

	//Testcase 4 b
	@Test
	public  void errorLoginWrongInputs() throws Exception {
		logger.info("inside logintoSalesForce method");
		extentreport.logTestInfo("inside logintoSalesForce method");
		PropertiesLoader propertiesloader =new PropertiesLoader();
		Properties propertyFile =propertiesloader.loadFile("inputProperties");
	
		String userName = "123";
		String password = "22132";

		WebElement userBox = findWebElementByID("username");
		waitUntilElementIsVisible(userBox);
		sendKeys(userBox, userName);

		WebElement passwordBox = findWebElementByID("password");
		sendKeys(passwordBox, password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();

		Thread.sleep(1000);
		WebElement errorMessage = findWebElementByID("error");
		String expectedMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String actualMsg = errorMessage.getText();
		Assert.assertEquals(expectedMsg,actualMsg);
		extentreport.logTestInfo("method ended");
	
	}
}

