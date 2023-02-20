package org.tekarch.pages.login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.tekarch.pages.salesForceHelperTest.SalesForceHelperPage;

public class LoginPage extends SalesForceHelperPage {
	@FindBy(xpath = "//*[@id=\"username\"]") WebElement userName;
	@FindBy(xpath = "//*[@id=\"password\"]") WebElement password;
	@FindBy(xpath = "//*[@id=\"Login\"]") WebElement loginButton;
	@FindBy(xpath = "//*[@id=\"error\"]") WebElement errorMsg;
	@FindBy(xpath = "//*[@id=\"forgot_password_link\"]") WebElement forgotPassword;
	
	@FindBy(xpath = "//*[@id=\"login_form\"]/div[3]/label") WebElement radioButton;
	@FindBy(id = "error") WebElement wrongInputMsg;
	public LoginPage(WebDriver driver) {
		//use super keyword to use basePage constructor here
		super(driver);
	}
	
	public void enterUsername(String data) {
	  WaitUntilElementIsVisible(userName,"username element");
	  enterText(userName,data,"username element");
	}
	
	public void enterPassword(String data) {
		 enterText(password,data,"password element");
	}
	public WebDriver clickLogin() {
		clickElement(loginButton,"login button element");
		return driver;
	}
	
	public String getErrorMessage() {
		return errorMsg.getText();
	}
	
	public void clickRadioButton() {
		clickElement(radioButton, "radio button element");
	}
	
	public String getTextFromAlert() {
		Alert alert = switchToAlert();
		return getAlertText(alert);
	}
	
	public String getAttributeFromUserNameField() {
		return userName.getAttribute("value");
	}
	
	public void clickForgotPassword() {
		clickElement(forgotPassword, "forgotPassword element");
	}
	
	
	public String getTextFromWrongInput() {
	return wrongInputMsg.getText();
}

}

