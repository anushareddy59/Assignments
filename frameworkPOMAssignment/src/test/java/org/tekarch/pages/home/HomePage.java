package org.tekarch.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.tekarch.pages.salesForceHelperTest.SalesForceHelperPage;

public class HomePage extends SalesForceHelperPage {
	
	@FindBy(xpath = "//*[@id=\"phHeaderLogoImage\"]") WebElement salesForce;
	
	@FindBy(xpath = "//*[@id=\"userNavLabel\"]") WebElement profileIcon;
	
	@FindBy(xpath = "//*[@id=\"userNav-menuItems\"]/a[5]") WebElement logOutButton;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getTextFromHomePage() {
		WaitUntilElementIsVisible(salesForce, "salesForcetest element");
		return getTextFromWebElement(salesForce, "salesForcetest element");
	}

	public boolean isHomeLogoDispalyed() {
		return salesForce.isDisplayed();
	}

	public void clickProfileIcon() {
		clickElement(profileIcon, "profile icon element");
	}

	public void clickLogOutButton() {
		clickElement(logOutButton, "logOutButton element");
	}

}
