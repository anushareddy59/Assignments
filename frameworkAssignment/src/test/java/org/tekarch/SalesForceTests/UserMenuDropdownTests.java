package org.tekarch.SalesForceTests;

import java.util.Set;


import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.tekarch.SalesForceTests.SFDCLoginTests;
import org.tekarch.salesForceHelperTest.SalesForceHepler;
import org.tekarch.utility.PropertiesLoader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(org.tekarch.utility.TestEventListenersUtility.class)
public class UserMenuDropdownTests extends SalesForceHepler {

	//testcase 5
	@Test
		private void usermenuDropdown() throws Exception {
		loginIntoSF();
			
			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			profileIcon.click();

			WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
			WebElement mySettings = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[2]");
			WebElement developerConsole = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[3]");
			WebElement switchToLigthningConsole = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[4]");
			WebElement logOut = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
			
			boolean actual = myProfile.isDisplayed() && mySettings.isDisplayed() && developerConsole.isDisplayed()
					&& switchToLigthningConsole.isDisplayed() && logOut.isDisplayed();
			Boolean expected = Boolean.TRUE;
			Assert.assertEquals(actual, expected);
			
			extentreport.logTestInfo("method ended");
	}
	
	

	//testcase 6
	@Test
		public void myProfile() throws Exception {
		
			loginIntoSF();
			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			profileIcon.click();

			WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
			myProfile.click();

			Thread.sleep(3000);
			WebElement editProfile = findWebElementByXPath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img");
			editProfile.click();
			Thread.sleep(4000);
			driver.switchTo().frame("contactInfoContentId");

			Thread.sleep(1000);
			WebElement about = findWebElementByXPath("/html/body/div/div/div/div[1]/ul/li[1]/a");
			about.click();

			WebElement lastName = findWebElementByID("lastName");
			sendKeys(lastName, "a");

			WebElement saveAll = findWebElementByXPath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]");
			saveAll.click();

			Thread.sleep(1000);
			String basehandle = driver.getWindowHandle();
			WebElement post = findWebElementByXPath("//*[@id=\"publisherAttachTextPost\"]/span[1]");
			post.click();
			//frame iframe[contains(....
			// bodyframe inside frame..switch to frame
			//
			Thread.sleep(2000);

			driver.switchTo().frame(0);
			WebElement textBox = findWebElementByXPath("/html/body");
			System.out.println("Textbox: " + textBox.isDisplayed() + " and " + textBox.isEnabled());
			sendKeys(textBox, "Hello, Welcome");
			driver.switchTo().window(basehandle);

			WebElement shareButton = findWebElementByID("publishersharebutton");
			shareButton.click();
			Thread.sleep(2000);
			// *[@id="publisherAttachContentPost"]/span[1]
			// File upload
			clickByXPath("//*[@id=\"publisherAttachContentPost\"]/span[1]", "File link");
			Thread.sleep(2000);

			/*
			 * clickByXPath("//*[@id=\"chatterUploadFileAction\"]", "Upload Button");
			 * Thread.sleep(2000); WebElement choosebutton =
			 * findWebElementByID("chatterFile"); Actions action = new Actions(driver);
			 * action.moveToElement(choosebutton).build().perform(); action.click();
			 * 
			 * StringSelection stringSelection = new
			 * StringSelection("C:\\Users\\Anusha\\Documents\\data1.xlsx");
			 * Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,
			 * null);
			 * 
			 * 
			 * WebElement shareButton1 = findWebElementByID("publishersharebutton");
			 * shareButton1.click();
			 */
			// clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/li/div/div/a",
			// "AddPhoto");

			WebElement photo = findWebElementByXPath(
					"/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[1]/span[2]/img[1]");
			Actions action = new Actions(driver);
			action.moveToElement(photo).build().perform();
			clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[1]/div/a", "AddPhto");
			driver.switchTo().frame("uploadPhotoContentId");

			WebElement fileChose = findWebElementByID("j_id0:uploadFileForm:uploadInputFile");
			fileChose.sendKeys("C:\\Users\\Anusha\\Desktop\\sarah1.jpg");

			clickByID("j_id0:uploadFileForm:uploadBtn", "SaveButton");

			Thread.sleep(5000);
			driver.switchTo().defaultContent();

			clickByID("j_id0:j_id7", "CropSave");
			closeBrowser();
		}

		/**
		 * Test case 7
		 * 
		 * @throws Exception
		 */
		@Test
		public void mySettings() throws Exception {
			loginIntoSF();
			Thread.sleep(2000);
			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			profileIcon.click();
			WebElement settings = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[2]");
			settings.click();
			Thread.sleep(1000);
			WebElement personal = findWebElementByXPath("//*[@id=\"PersonalInfo\"]/a");
			personal.click();
			Thread.sleep(1000);
			WebElement loginHistory = findWebElementByID("LoginHistory_font");
			loginHistory.click();
			Thread.sleep(1000);
			WebElement downloadHistory = findWebElementByXPath("//*[@id=\"RelatedUserLoginHistoryList_body\"]/div/a");
			downloadHistory.click();

			Thread.sleep(1000);
			clickByID("DisplayAndLayout_font", "display&Layout");
			clickByID("CustomizeTabs_font", "customizeTab");
			Thread.sleep(1000);

			WebElement appDropDown = clickByID("p4", "customAppdropDown");
			Select ob = new Select(appDropDown);
			ob.selectByVisibleText("Salesforce Chatter");

			WebElement selectBox = clickByID("duel_select_0", "SelectionBox");
			Select reports = new Select(selectBox);
			reports.selectByVisibleText("Reports");

			clickByXPath("//*[@id=\"duel_select_0_right\"]/img", "AddButon");
			clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "SaveButton");
			Thread.sleep(1000);

			clickByXPath("//*[@id=\"EmailSetup\"]/a", "EmailLink");
			clickByID("EmailSettings_font", "EmailSettings");
			Thread.sleep(1000);

			WebElement emailTextBox = clickByID("sender_email", "EmailBox");
			emailTextBox.clear();
			emailTextBox.sendKeys("abcdefgh@abc.com");

			clickByXPath("//*[@id=\"auto_bcc1\"]", "SelectYesInBCC");

			String baseHandler = driver.getWindowHandle();
			clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "EmailSaveButton");
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Thread.sleep(1000);
			driver.switchTo().window(baseHandler);
			clickByXPath("//*[@id=\"CalendarAndReminders\"]/a", "Calenders&Remainders");
			clickByID("Reminders_font", "ActivityReminders");
			Thread.sleep(1000);

			clickByID("testbtn", "OpenTestReminder");
			extentreport.logTestInfo("method ended");
		}

		// testcase 8
		@Test
		public static void developersConsole() throws Exception {
			loginIntoSF();
			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			profileIcon.click();
			WebElement developerConsole = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[3]");
			developerConsole.click();

			String baseHandle = driver.getWindowHandle();

			Thread.sleep(3000);

			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(baseHandle)) {
					driver.switchTo().window(handle);
				}
				extentreport.logTestInfo("method ended");
			}
	
		}
		//Test case 9
		@Test
		public static void logout() throws Exception {
			loginIntoSF();
			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			profileIcon.click();
			Thread.sleep(1000);
			WebElement logOutButton = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[5]");
			logOutButton.click();
			waitImplicitly();
			
			WebElement userBox = findWebElementByID("username_container");
			Assert.assertNotNull(userBox, "UserName box is not found");
		

			extentreport.logTestInfo("method ended");
		}
}
