package org.tekarch.salesForceHelperTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tekarch.utility.Constants;
import org.tekarch.utility.ExtentReportsUtility;
import org.tekarch.utility.PropertiesLoader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceHepler {

	protected static WebDriver driver;
	protected static WebDriverWait wait = null;
	protected static Logger logger = null;
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	@BeforeTest
	public void setUpBeforeTest() {
		logger = LogManager.getLogger(SalesForceHepler.class.getName());
		
	}

	@BeforeMethod
	@Parameters("browsername")
	public void setupBeforeTestMethod(@Optional("chrome") String browsername, Method method) {
		logger.info("started testscript name" + method.getName());
		// PropertiesLoader propertiesloader = new PropertiesLoader();
		// propertiesloader.loadFile("inputProperties");
		// Properties propertyFile = propertiesloader.loadFile("inputProperties");
		PropertiesLoader propertiesloader = new PropertiesLoader();
		Properties propertyFile = propertiesloader.loadFile("inputProperties");
		String url = propertyFile.getProperty("url");
		initDriverInstance("chrome");
		gotoURL(url);
	}

	@AfterMethod
	public void teardownAfterTestMethod() {
		driver.close();
	}

	public void enterText(WebElement element, String text, String name) {
		if (element.isDisplayed()) {
			clearElement(element, name);
			element.sendKeys(text);
			logger.info("text entered in " + name + "field");
			extentreport.logTestInfo("text entered in " + name + "field");
		} else {
			logger.info("fail: " + name + " element not displayed");
			extentreport.logTestInfo("fail: " + name + " element not displayed");
		}
		driver.getTitle();
	}

	@AfterTest
	public void teardownAfterTest() {
		extentreport.endReport();
	}

	public void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			logger.info("pass:" + objName + "  element cleared");
			extentreport.logTestInfo("pass:" + objName + "  element cleared");

		} else {
			logger.info("fail:" + objName + " element not displayed");
			extentreport.logTestInfo("fail:" + objName + " element not displayed");
		}
	}

	public static void initDriverInstance(String browserName) {
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

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("Invalid Browsername");
		}

	}

	public static void gotoURL(String url) {
		logger.info("going to the url = " + url);
		driver.get(url);
	}

	public static WebElement findWebElementByID(String inputID) {
		WebElement element = driver.findElement(By.id(inputID));
		return element;
	}

	public static WebElement findWebElementByXPath(String xPath) {
		WebElement element = driver.findElement(By.xpath(xPath));
		return element;
	}

	public static void sendKeys(WebElement element, String data) {
		element.sendKeys(data);
	}

	public static Alert switchToAlert() {
		waitUntilAlertIsPresent();
		Alert alert = driver.switchTo().alert();
		System.out.println("switched to alert");
		return alert;
	}

	public static void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();
	}

	public static void moveTOElementAction(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element " + objName);
	}

	public static void waitUntilElementIsVisible(WebElement ele) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public static WebElement clickByID(String id, String name) {
		WebElement element = findWebElementByID(id);
		element.click();
		return element;
	}

	public static WebElement clickByXPath(String xpath, String name) {
		WebElement element = findWebElementByXPath(xpath);
		element.click();
		return element;
	}

	public static void selectByTextData(WebElement cityDropdown, String city, String string) {
		Select ob = new Select(cityDropdown);
		ob.selectByVisibleText(city);

	}

	public static WebElement selectFromList(List<WebElement> allElements, String name) {
		WebElement result = null;
		for (WebElement element : allElements) {
			if (element.getText().equals(name)) {
				result = element;
				break;
			}
		}
		return result;
	}

	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitImplicitly() {
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	public static void closeBrowser() {
		logger.info("closing the browser");
		driver.close();
	}

	/*
	 * public static String getTextFromWebElement(WebElement element, String name) {
	 * if (element.isDisplayed()) { return element.getText(); } else {
	 * System.out.println(name + " web element is not displayed"); return null; }
	 */

	

	public static String getScreenshotofThePage() {
		// random value + date()+testcasen name --->file name
		String date = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File imgFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH + date + ".png");
		try {
			FileHandler.copy(imgFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();
	}
	
	public static void loginIntoSF() {
		PropertiesLoader propertiesloader = new PropertiesLoader();
		Properties propertyFile = propertiesloader.loadFile("inputProperties");
		String userName = propertyFile.getProperty("username");
		String password = propertyFile.getProperty("password");

		WebElement userBox = findWebElementByID("username");
		WebElement passwordBox = findWebElementByID("password");
		waitUntilElementIsVisible(passwordBox);
		userBox.sendKeys(userName);
		passwordBox.sendKeys(password);

		WebElement loginButton = findWebElementByID("Login");
		loginButton.click();
		waitUntilPageLoads();
	}
	public static void logoutSF(){
	WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
	profileIcon.click();


	WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
	logOut.click();
	}
	
	public String getTextFromWebElement(WebElement txtElement, String description) {
		return txtElement.getText();
	}

	public static String getScreenshotofThePage(WebDriver driver) {
		// random value + date()+testcasen name --->file name
		String date = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		String img = screenShot.getScreenshotAs(OutputType.BASE64);
		return img;
		/*
		 * File imgFile = screenShot.getScreenshotAs(OutputType.FILE); File destFile =
		 * new File(Constants.SCREENSHOTS_DIRECTORY_PATH + date + ".png"); try {
		 * FileHandler.copy(imgFile, destFile); } catch (IOException e) {
		 * e.printStackTrace(); } return destFile.getAbsolutePath();
		 */
	}

}
