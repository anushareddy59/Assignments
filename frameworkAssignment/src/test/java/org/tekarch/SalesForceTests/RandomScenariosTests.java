package org.tekarch.SalesForceTests;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.tekarch.salesForceHelperTest.SalesForceHepler;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tekarch.utility.PropertiesLoader;
@Listeners(org.tekarch.utility.TestEventListenersUtility.class)
public class RandomScenariosTests extends SalesForceHepler {

		//Testcase 33		
		@Test  
		public void firstLastName() throws Exception {
			String expected = "Anu abcd";

			loginIntoSF();
			WebElement homeTab = findWebElementByXPath("//*[@id=\"home_Tab\"]/a");
			homeTab.click();
			waitImplicitly();
			//clickByID("tryLexDialogX", "No thanks");

			WebElement act = findWebElementByXPath("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a");
			String actHref = act.getAttribute("href");

			String actual = act.getText();
			Assert.assertEquals(expected, actual);

			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			waitUntilElementIsVisible(profileIcon);
			profileIcon.click();
			
			// clickByID("tryLexDialogX", "No thanks");
			WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
			String myProfileHref = myProfile.getAttribute("href");
			Assert.assertEquals(actHref, myProfileHref);
			extentreport.logTestInfo("method ended");
		}

			// Testcaes 34
			@Test
			public void firstNameLastNameVerify() throws Exception {
			loginIntoSF();

			WebElement profileIcon = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			profileIcon.click();

			WebElement myProfile = findWebElementByXPath("//*[@id=\"userNav-menuItems\"]/a[1]");
			myProfile.click();
			waitImplicitly();
			WebElement editProfile = findWebElementByXPath("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img");
			editProfile.click();
			driver.switchTo().frame("contactInfoContentId");
			waitImplicitly();
			WebElement about = findWebElementByXPath("/html/body/div/div/div/div[1]/ul/li[1]/a");
			about.click();
			String lName = "abcd";
			WebElement lastName = findWebElementByID("lastName");
			lastName.clear();
			sendKeys(lastName, lName);

			WebElement saveAll = findWebElementByXPath("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]");
			saveAll.click();
			waitImplicitly();
			WebElement displayedName = findWebElementByID("tailBreadcrumbNode");
			String name = displayedName.getText();

			WebElement profileIcon1 = findWebElementByXPath("//*[@id=\"userNavLabel\"]");
			String profilename = profileIcon1.getText();
			System.out.print("Names: " + profilename + " .." + name);
			if (name.trim().endsWith(lName) && profilename.trim().endsWith(lName)) {
				System.out.println("Test case passed");
			} else {
				System.out.println("Testcase failed");
			}

			logoutSF();
			extentreport.logTestInfo("method ended");
		}

			//Testcase 35
			@Test 
			public void verifyTabCustomization() throws Exception {
			loginIntoSF();

			WebElement plusButton = findWebElementByXPath("//*[@id=\"AllTab_Tab\"]/a/img");
			waitUntilElementIsVisible(plusButton);
			plusButton.click();
			waitImplicitly();

			/*
			 * WebElement customizeButton = findWebElementByXPath(
			 * "//*[@id=\"bodyCell\"]/div[3]/div[1]/table/tbody/tr/td[2]/input");
			 * customizeButton.click();
			 */
			clickByXPath("/html/body/div/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/table/tbody/tr/td[2]/input", "CustomizeButton");

			WebElement selectBox = clickByID("duel_select_1", "SelectionBox");
			Select reports = new Select(selectBox);
			String tabToRemove = "Reports";
			reports.selectByVisibleText(tabToRemove);

			clickByXPath("//*[@id=\"duel_select_0_left\"]/img", "RemoveButton");
			clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "SaveButton");

			logoutSF();
			loginIntoSF();
			boolean isTabRemoved = true;
			WebElement tabBar = findWebElementByID("tabBar");
			List<WebElement> elements = tabBar.findElements(By.tagName("a"));
			for (WebElement element : elements) {
				System.out.println(element.getText());
				if (element.getText().equalsIgnoreCase(tabToRemove)) {
					isTabRemoved = false;
				}
			}
		
			Assert.assertEquals(isTabRemoved, Boolean.TRUE);
			logoutSF();
			extentreport.logTestInfo("method ended");

		}

			//Testcase 36
			@Test 
			public void eventBlock() throws Exception {
			String expected = "Anu aaaaaaa";

			loginIntoSF();

			WebElement homeTab = findWebElementByXPath("//*[@id=\"home_Tab\"]/a");
			homeTab.click();
			Thread.sleep(3000);
			clickByID("tryLexDialogX", "No thanks");

			WebElement dateLink = findWebElementByXPath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a");
			dateLink.click();
			waitImplicitly();
			WebElement pmLink = findWebElementByXPath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]");
			pmLink.click();
			

			String baseHandle = driver.getWindowHandle();

			WebElement subjectCombo = findWebElementByXPath(
					"//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img");
			subjectCombo.click();

			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(baseHandle)) {
					driver.switchTo().window(handle);
				}
			}

			WebElement other = findWebElementByXPath("/html/body/div[2]/ul/li[5]/a");
			other.click();

			driver.switchTo().window(baseHandle);

			WebElement endTime = findWebElementByXPath("//*[@id=\"EndDateTime_time\"]");
			sendKeys(endTime, "9:00 PM");
			/*
			 * endTime.click(); Select ob = new Select(endTime);
			 * ob.selectByVisibleText("9:00 PM");
			 */

			WebElement save = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
			save.click();

			WebElement div8PM = findWebElementByXPath(
					"//*[@id=\"p:f:j_id25:j_id69:28:j_id71:0:j_id72:calendarEvent:i\"]/div");
			List<WebElement> list = div8PM.findElements(By.tagName("a"));
			if (list.size() > 0) {
				System.out.println("Test case passed");
			} else {
				System.out.println("Test case failed");
			}
			logoutSF();
			extentreport.logTestInfo("method ended");
		}

			//Testcase 37
			@Test  
			public void eventBlockWeeklyRecurrence() throws Exception {
			String expected = "Anu aaaaaaa";
			loginIntoSF();

			WebElement homeTab = findWebElementByXPath("//*[@id=\"home_Tab\"]/a");
			homeTab.click();
			Thread.sleep(3000);
			clickByID("tryLexDialogX", "No thanks");

			WebElement dateLink = findWebElementByXPath("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a");
			dateLink.click();
			Thread.sleep(3000);
			WebElement pmLink = findWebElementByXPath("//*[@id=\"p:f:j_id25:j_id61:28:j_id64\"]");
			pmLink.click();
			Thread.sleep(2000);
			String baseHandle = driver.getWindowHandle();
			WebElement subjectCombo = findWebElementByXPath(
					"//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img");
			subjectCombo.click();

			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(baseHandle)) {
					driver.switchTo().window(handle);
				}
			}

			waitImplicitly();
			WebElement other = findWebElementByXPath("/html/body/div[2]/ul/li[5]/a");
			other.click();

			driver.switchTo().window(baseHandle);
			WebElement endTime = findWebElementByXPath("//*[@id=\"EndDateTime_time\"]");
			endTime.clear();
			sendKeys(endTime, "7:00 PM");
			/*
			 * endTime.click(); Select ob = new Select(endTime);
			 * ob.selectByVisibleText("7:00 PM");
			 */
			clickByXPath("//*[@id=\"IsRecurrence\"]", "recurrence");
			clickByXPath("//*[@id=\"rectypeftw\"]", "weekly");

			clickByID("EndDateTime", "EndDate");
			/*
			 * String startDate = findWebElementByID("StartDateTime").getText();
			 * SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			 */
			WebElement endDate = findWebElementByXPath(
					"/html/body/div/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/table/tbody/tr[5]/td[7]");
			endDate.click();

			clickByXPath("//*[@id=\"bottomButtonRow\"]/input[1]", "save");
			waitImplicitly();
			//clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/span[2]/div/div/div[2]/span[2]/a[3]/img", "Month view");
			clickByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[1]/div/div[2]/span[2]/a[3]/img", "Month View");

			/*
			 * WebElement table = findWebElementByXPath(
			 * "//*[@id=\"bodyCell\"]/div[2]/div[1]/div[1]/table/tbody"); List<WebElement>
			 * cells = table.findElements(By.className("calActive")); for(WebElement cell :
			 * cells) { List<WebElement> links = cell.findElements(By.tagName("a")); String
			 * date = null; boolean otherPresent = false; for(WebElement link : links) {
			 * String title = link.getAttribute("title"); if(title.startsWith("Day View")) {
			 * date = link.getText(); } if(link.getText() != null &&
			 * link.getText().equals("Other")) { otherPresent = true; } }
			 * 
			 * }
			 */
			logoutSF();
			extentreport.logTestInfo("method ended");
		}

	}


