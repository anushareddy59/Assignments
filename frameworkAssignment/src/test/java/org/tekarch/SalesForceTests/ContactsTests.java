package org.tekarch.SalesForceTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.tekarch.salesForceHelperTest.SalesForceHepler;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tekarch.utility.PropertiesLoader;

@Listeners(org.tekarch.utility.TestEventListenersUtility.class)
class ContactsTests extends SalesForceHepler {

	// Testcase 25
	@Test
	public void createContact() throws Exception {
		String expected = "Renu";
		loginIntoSF();
		waitUntilPageLoads();
		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();

		clickByID("tryLexDialogX", "No thanks");

		WebElement newButton = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
		newButton.click();
		WebElement lastName = findWebElementByXPath("//*[@id=\"name_lastcon2\"]");
		waitUntilElementIsVisible(lastName);
		lastName.sendKeys("Renu");
		WebElement accName = findWebElementByXPath("//*[@id=\"con4\"]");
		accName.sendKeys("automation");
		WebElement save = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		save.click();
		WebElement act = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
		String actual = act.getText();
		Assert.assertEquals(actual, expected);

		extentreport.logTestInfo("method ended");

	}

	// Testcase 26
	@Test
	public void createNewView() throws Exception {
		long currentTime = System.currentTimeMillis();
		String expected = "Riya_"+currentTime;
		loginIntoSF();

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();
		clickByID("tryLexDialogX", "No thanks");
		WebElement newView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		newView.click();
		WebElement viewName = findWebElementByXPath("//*[@id=\"fname\"]");
		viewName.sendKeys(expected);
		WebElement uniName = findWebElementByXPath("//*[@id=\"devname\"]");
		uniName.sendKeys("");
		WebElement save = findWebElementByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]");
		waitUntilElementIsVisible(save);
		save.click();
		WebElement act = driver.findElement(By.name("fcf"));
		Select ob = new Select(act);
		String actual = ob.getFirstSelectedOption().getText();
		// String actual = act.getText();
		Assert.assertEquals(actual, expected);
		logoutSF();
		extentreport.logTestInfo("method ended");

	}

	// Testcase 27
	@Test
	public void recentlyCreatedContact() throws Exception {
		String expected = "Recent Contacts";
		loginIntoSF();
		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();
		clickByID("tryLexDialogX", "No thanks");

		WebElement recentlyCreated = findWebElementByXPath("//*[@id=\"hotlist_mode\"]");
		recentlyCreated.click();
		Select ob = new Select(recentlyCreated);
		ob.selectByVisibleText("Recently Created");
		WebElement act = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[1]/h3");
		String actual = act.getText();
		Assert.assertEquals(actual,expected);
		logoutSF();
		extentreport.logTestInfo("method ended");
	
	}

	// Testcase 28
	@Test
	public void myContacts() throws Exception {
		String expected = "My Contacts";
		loginIntoSF();
		
		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();

		clickByID("tryLexDialogX", "No thanks");

		WebElement view = findWebElementByXPath("//*[@id=\"fcf\"]");
		view.click();
		Select ob = new Select(view);
		ob.selectByVisibleText("My Contacts");
		Thread.sleep(1000);
		// WebElement act =
		// findWebElementByXPath("//*[@id=\"00BDn00000Im6Gx_listSelect\"]");
		WebElement act = driver.findElement(By.name("fcf"));
		Select ob1 = new Select(act);

		String actual = ob1.getFirstSelectedOption().getText();
		Assert.assertEquals(actual, expected);
		logoutSF();
		extentreport.logTestInfo("method ended");
	}

	// Testcase 29
	@Test
	public void contactPage() throws Exception {
		String expected = "Renu";
		loginIntoSF();
		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();
		clickByID("tryLexDialogX", "No thanks");

		WebElement name = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a");
		name.click();
		WebElement act = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
		String actual = act.getText();
		Assert.assertEquals(expected, actual);
		logoutSF();
		extentreport.logTestInfo("method ended");
	}

	// Testcase 30
	@Test
	public void errorViewContact() throws Exception {
		String expected = "Error: You must enter a value";
		loginIntoSF();
		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();

		clickByID("tryLexDialogX", "No thanks");

		WebElement newView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		newView.click();
		WebElement uniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
		uniqueName.sendKeys("EFGH");
		WebElement save = findWebElementByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]");
		save.click();

		WebElement act = findWebElementByXPath(
				"//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]");
		String actual = act.getText();
		Assert.assertEquals(actual, expected);
		logoutSF();
		extentreport.logTestInfo("method ended");
	}

	// Testcase 31
	@Test
	public void cancelButtonCreateNewView() throws Exception {
		String expected = "Home";
		loginIntoSF();

		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();
		clickByID("tryLexDialogX", "No thanks");

		WebElement newView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
		newView.click();
		WebElement name = findWebElementByXPath("//*[@id=\"fname\"]");
		name.sendKeys("ABCD");
		WebElement uniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
		uniqueName.sendKeys("EFGH");
		WebElement cancel = findWebElementByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[2]");
		cancel.click();

		WebElement act = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2");
		String actual = act.getText();
		Assert.assertEquals(actual,expected);
		logoutSF();
		extentreport.logTestInfo("method ended");
	}

	// Testcase 32
	@Test
	public void saveNewButtonContact() throws Exception {
		String expected = "New Contact";
		loginIntoSF();
		WebElement contactTab = findWebElementByXPath("//*[@id=\"Contact_Tab\"]/a");
		contactTab.click();
		waitImplicitly();
		clickByID("tryLexDialogX", "No thanks");

		WebElement newButton = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
		newButton.click();
		WebElement lastName = findWebElementByXPath("//*[@id=\"name_lastcon2\"]");
		waitUntilElementIsVisible(lastName);
		lastName.sendKeys("Indian");
		WebElement accName = findWebElementByXPath("//*[@id=\"con4\"]");
		accName.sendKeys("Global Media");
		WebElement saveAndNew = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[2]");
		saveAndNew.click();
		WebElement act = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2");
		String actual = act.getText();
		Assert.assertEquals(expected, actual);
		logoutSF();
		extentreport.logTestInfo("method ended");
	}

}
