package org.tekarch.pages.forgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.tekarch.pages.salesForceHelperTest.SalesForceHelperPage;

public class ForgotPasswordPage extends SalesForceHelperPage  {
	@FindBy(xpath = "//*[@id=\"un\"]") WebElement userName;
	@FindBy(xpath = "//*[@id=\"header\"]") WebElement checkMailMsg;
	@FindBy(xpath = "//*[@id=\"continue\"]") WebElement continueButton;
	
	
	public  ForgotPasswordPage(WebDriver driver) {
		
		super(driver);
	}
	
	public void enterforgotUsername(String data) {
		WaitUntilElementIsVisible(userName, "forgot username element");
		enterText(userName, data, "forgotUsername element");
	}
	
	public void clickContinue() {
		clickElement(continueButton, "Continue Button");
	}
	
	public String getEmailMessage() {
		return checkMailMsg.getText();
	}
}
