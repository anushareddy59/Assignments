package com.tekarch.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.tekarch.pages.forgotPassword.ForgotPasswordPage;
import org.tekarch.pages.home.HomePage;
import org.tekarch.pages.login.LoginPage;
import org.tekarch.utility.ExtentReportsUtility;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefinationForSalesForce {

	private WebDriver driver;
	private LoginPage loginPage;
	private HomePage homePage;
	private ForgotPasswordPage forgotPasswordPage;
	protected static ExtentReportsUtility extentreport;

	@Before
	public void initDriver() {
		GetDriverInstance("chrome");
		extentreport = ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();
		extentreport.startSingleTestReport("mytest");
	}

	@After
	public void closeBrowser() {
		extentreport.endReport();
		driver.close();
	}

	@Given("Open SalesForce Application")
	public void open_sales_force_application() {
		String url = "https://login.salesforce.com";
		goToUrl(url);
	}

	@When("user on {string}")
	public void user_on(String pageName) {
		if (pageName.equalsIgnoreCase("LoginPage")) {
			loginPage = new LoginPage(driver);
		} else if (pageName.equalsIgnoreCase("HomePage")) {
			homePage = new HomePage(driver);
		} else if (pageName.equalsIgnoreCase("ForgotPasswordPage")) {
			forgotPasswordPage = new ForgotPasswordPage(driver);

		} else {
			System.out.println("Page name not supported");

		}
	}

	@When("user enters username as {string}")
	public void user_enters_username_as(String userName) {
		loginPage.enterUsername(userName);
	}

	@When("user enters password as {string}")
	public void user_enters_password_as(String password) {
		loginPage.enterPassword(password);
	}

	@When("user clicks on login button")
	public void user_clicks_on_login_button() {
		loginPage.clickLogin();
	}

	@Then("homepage logo should be displayed")
	public void homepage_logo_should_be_displayed() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		Assert.assertEquals(true, homePage.isHomeLogoDispalyed());
	}

	@Then("empty password error message displayed")
	public void empty_password_error_message_displayed() {
		String expected = "Please enter your password.";
		String actual = loginPage.getErrorMessage();
		Assert.assertEquals(actual, expected);
	}

	public void GetDriverInstance(String browserName) {
		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("not entered proper browsername");
		}
	}

	public void goToUrl(String url) {
		driver.get(url);
	}

	@When("user clicks remember me checkbox")
	public void user_clicks_remember_me_checkbox() {
		driver.findElement(By.xpath("//*[@id=\"rememberUn\"]")).click();
	}

	@When("user clicks on profile icon")
	public void user_clicks_on_profile_icon() {
		driver.findElement(By.xpath("//*[@id=\"userNavLabel\"]")).click();
	}

	@When("user clicks on logout")
	public void user_clicks_on_logout() {
		driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]")).click();
	}

	@Then("username field contains {string}")
	public void username_field_contains(String userName) throws InterruptedException {
		String expected = userName;
		Thread.sleep(5000);
		String actual = loginPage.getAttributeFromUserNameField();
		Assert.assertEquals(expected,actual);
	}

	@When("user clicks forgot your password")
	public void user_clicks_forgot_your_password() {
		driver.findElement(By.xpath("//*[@id=\"forgot_password_link\"]")).click();
	}

	@When("user enters forgot username as {string}")
	public void user_enters_forgot_username_as(String userName) {
		forgotPasswordPage.enterforgotUsername(userName);
	}

	@When("user clicks continue button")
	public void user_clicks_continue_button() {
		forgotPasswordPage.clickContinue();
	}

	@Then("check your email message is displayed")
	public void check_your_email_message_is_displayed() {
		String expected = "Check Your Email";
		String actual = forgotPasswordPage.getEmailMessage();
		Assert.assertEquals(expected, actual);
	}

	@Then("invalid credentials message is displayed")
	public void invalid_credentials_message_is_displayed() {
		String expectedMsg = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String actualMsg = loginPage.getTextFromWrongInput();
		Assert.assertEquals(expectedMsg,actualMsg);
	}
	
	


	
}
