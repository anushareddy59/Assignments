package assignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceHelper {

	static WebDriver driver;
	static WebDriverWait wait=null;

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

	protected static void gotoURL(String url) {
		driver.get(url);	
	}
	
	
	public static WebElement findWebElementByID(String inputID) {
		WebElement element = driver.findElement(By.id(inputID)) ;
		return element;
	}
	
	public static WebElement findWebElementByXPath(String xPath) {
		WebElement element = driver.findElement(By.xpath(xPath)) ;
		return element;
	}

	
	public static void sendKeys(WebElement element, String data) {
		element.sendKeys(data);	
	}
	
	
	
	public static void WaitUntilElementIsVisible(WebElement ele) {
		wait=new WebDriverWait(driver,30);
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

	
	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator) {
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public static void closeBrowser() {
		driver.close();
	}
	
	
	public static String getTextFromWebElement(WebElement element, String name) {
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			System.out.println(name + " web element is not displayed");
			return null;
		}

	}
}
