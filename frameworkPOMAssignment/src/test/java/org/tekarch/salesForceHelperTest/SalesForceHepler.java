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
protected static WebDriver driver = null;
	
	protected Logger logger = null;
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	@BeforeTest
	public void setUpBeforeTest() {
		
		logger = LogManager.getLogger(SalesForceHepler.class.getName());
		
		
	}

	@BeforeMethod
	@Parameters("browsername")
	public void setupBeforeTestMethod(@Optional("chrome") String browsername, Method method) {
		//extentreport.startSingleTestReport("testcase");  
		logger.info("started testscript name" + method.getName());
		//extentreport.logTestInfo("started testscript name" + method.getName());
		PropertiesLoader propertiesLoader = new PropertiesLoader();
		Properties propertyFile = propertiesLoader.loadFile("inputProperties");
		String url = propertyFile.getProperty("url");
		GetDriverInstance("chrome");
		goToUrl(url);
	}

	@AfterMethod
	public void teardownAftetrTestMethod() {
		driver.close();
	}


	
	@AfterTest
	public void teardownAfterTest() {
		extentreport.endReport();
	}


	public  void goToUrl(String url) {
		logger.info("going to th url = " + url);
		driver.get(url);
	}

	public  void closeBrowser() {
		logger.info("closing the browser");
		driver.close();
	}

	public static String getPageTitle() {
		return driver.getTitle();
	}
	
	public WebDriver returnDriverInstance() {
		return driver;
	}
	

	public static void GetDriverInstance(String browserName) {

		switch (browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "chrome":
			WebDriverManager.chromedriver().setup();
			// ChromeOptions option=new ChromeOptions();
			// option.addArguments("--headless");
			// option.addArguments("--incognito");
			// option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("not entered proper browsername");
		}
	}

	public static String getScreenshotBase64(WebDriver driver) {
		// random value + date()+testcasen name --->file name
		String date = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		String img = screenShot.getScreenshotAs(OutputType.BASE64);
		return img;
	
	}
	
}


	