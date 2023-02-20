package org.tekarch.SalesForceTests;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.tekarch.salesForceHelperTest.SalesForceHepler;
import org.tekarch.utility.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tekarch.pages.forgotPassword.ForgotPasswordPage;
import org.tekarch.pages.home.HomePage;
import org.tekarch.pages.login.LoginPage;
import org.tekarch.pages.salesForceHelperTest.SalesForceHelperPage;

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
		Thread.sleep(2000);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(userName);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		Thread.sleep(3000);
		String expected = "Please enter your password.";
		String actual = loginpage.getErrorMessage();
		Assert.assertEquals(actual, expected);

		
		/*WebElement errorMsg = findWebElementByID("error");
		String actualMsg = errorMsg.getText();
		String expectedMsg = "Please enter your password.";
		Assert.assertEquals(expectedMsg,actualMsg );*/
		extentreport.logTestInfo("method ended");
	}
	
	

	//Testcase 2
	@Test
	public void login() throws Exception {
		logger.info("inside loginto SalesForce method");
		extentreport.logTestInfo("inside loginto SalesForce method");
		PropertiesLoader propertiesloader =new PropertiesLoader();
		Properties propertyFile =propertiesloader.loadFile("inputProperties");
		String userName = propertyFile.getProperty("username");
		String password = propertyFile.getProperty("password");
		Thread.sleep(4000);		//String expected = "Anu abcd";
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(userName);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		Thread.sleep(3000);
		HomePage homepage = new HomePage (driver);
		Boolean expected = Boolean.TRUE; 
		boolean actual = homepage.isHomeLogoDispalyed();
		Assert.assertEquals(actual, expected);
		
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
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUsername(userName);
		loginPage.enterPassword(password);
		loginPage.clickRadioButton();
		driver = loginPage.clickLogin();
		SalesForceHelperPage.waitUntilPageLoads();
		HomePage homepage = new HomePage (driver);
	
		homepage.clickProfileIcon();
		homepage.clickLogOutButton();
		Thread.sleep(3000);
		SalesForceHelperPage.waitUntilPageLoads();
		LoginPage loginPage1 = new LoginPage(driver);
		
		String expected = userName;
		String actual = loginPage1.getAttributeFromUserNameField();
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
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(userName);
		loginpage.clickForgotPassword();
		Thread.sleep(3000);
		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
		forgotPasswordPage.enterforgotUsername(userName);
		forgotPasswordPage.clickContinue();
		String expected = "Check Your Email";
		String actual = forgotPasswordPage.getEmailMessage();
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
		String userName = propertyFile.getProperty("wrongUsername");
		String password = propertyFile.getProperty("wrongPassword");
	
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUsername(userName);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		
		String expectedMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String actualMsg = loginpage.getTextFromWrongInput();
		Assert.assertEquals(expectedMsg,actualMsg);
		extentreport.logTestInfo("method ended");
		
		
	
	}
}

