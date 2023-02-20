package org.tekarch.pages.salesForceHelperTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.tekarch.utility.Constants;
import org.tekarch.utility.ExtentReportsUtility;



public class SalesForceHelperPage {
	protected static WebDriver driver = null;
	protected Logger logger = LogManager.getLogger(SalesForceHelperPage.class.getName());
	protected ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	protected static WebDriverWait wait = null;
	
	public SalesForceHelperPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	
	}
	
	public  void enterText(WebElement element, String text, String name) {
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

	
	public  void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			logger.info("pass:" + objName + "  element cleared");
			extentreport.logTestInfo("pass:" + objName + "  element cleared");


		} else {
			logger.info("fail:" + objName + " element not displayed");
			extentreport.logTestInfo("fail:" + objName + " element not displayed");
		}
	}

	public  void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			logger.info("pass:" + objName + " element clicked");
			extentreport.logTestInfo("pass:" + objName + " element clicked");
		} else {
			logger.info("fail:" + objName + "  element not displayed");
			extentreport.logTestInfo("fail:" + objName + "  element not displayed");

		}
	}
	
	public static void refreshPage() {
		driver.navigate().refresh();

	}
	
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	
	
	public static String getTextFromWebElement(WebElement element, String name) {
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			System.out.println(name + " web element is not displayed");
			return null;
		}

	}

	public static void moveTOElementAction(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element " + objName);
	}

	public static void ContextClickAction(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web element " + objName);
	}

	public static void WaitUntilElementIsVisible(WebElement ele, String objName) {
		System.out.println("waiting for an web element " + objName + " for its visibility");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public static void waitUntilAlertIsPresent() {
		System.out.println("waiting for aleet to be present");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for an web element " + objName + " to be clickable");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitFluentForVisibility(WebElement ele, String objName) {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static Alert switchToAlert() {
		// TODO Auto-generated method stub
		waitUntilAlertIsPresent();
		Alert alert = driver.switchTo().alert();
		System.out.println("switched to alert");
		return alert;
	}

	

	public static void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();

	}

	public static String getAlertText(Alert alert) {
		System.out.println("etracting text in the alert");
		return alert.getText();

	}

	public static void selectByTextData(WebElement cityDropdown, String city, String string) {
		Select ob = new Select(cityDropdown);
		ob.selectByVisibleText(city);

	}

	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");

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
	

}



