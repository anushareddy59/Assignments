package org.tekarch.SalesForceTests;

import java.util.Arrays;
import java.util.HashSet;
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
public class LeadsTests extends SalesForceHepler{

		//Testcase20
		@Test
		public void leadsTabLink() throws Exception {
			loginIntoSF();
			
			String expected = "Leads";
			WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
			leadsTab.click();
			Thread.sleep(3000);

			clickByID("tryLexDialogX", "No thanks");
			WebElement leadsHeader = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h1");
			//leads title = actual
			String actual = leadsHeader.getText();
			Assert.assertEquals(expected,actual);
			logoutSF();
			extentreport.logTestInfo("method ended");
			//driver.quit();
		}
			//Testcase 21
			@Test
			public void leadsView() throws Exception {
			loginIntoSF();
			
			WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
			leadsTab.click();
			waitImplicitly();
			clickByID("tryLexDialogX", "No thanks");

			WebElement leadsDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
			Select select = new Select(leadsDropDown);

			List<WebElement> options = select.getOptions();
			Set<String> actualOptions = new HashSet();
			for (WebElement option : options) {
				actualOptions.add(option.getText());
			}

			List<String> expectionOptions = Arrays.asList("All Open Leads", "My Unread Leads", "Recently Viewed Leads",
					"Today's Leads");
			boolean actual = actualOptions.containsAll(expectionOptions);
			Boolean expected = Boolean.TRUE;
			Assert.assertEquals(actual, expected);
			
			logoutSF();
			extentreport.logTestInfo("method ended");
		}
			//Testcase 22
			@Test
			public void goButton() throws Exception {
			loginIntoSF();
			WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
			leadsTab.click();
			waitImplicitly();

			clickByID("tryLexDialogX", "No thanks");
			WebElement leadsDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
			leadsDropDown.click();
			String expected = "My Unread Leads";
			Select ld = new Select(leadsDropDown);
			ld.selectByVisibleText(expected);
			logoutSF();
			loginIntoSF();
			String leadsXPath = "//*[@id=\"Lead_Tab\"]/a";
			WebElement leadsTab1 = findWebElementByXPath(leadsXPath);
			waitUntilElementToBeClickable(By.xpath(leadsXPath));
			leadsTab1.click();
			WebElement leadsDrop = findWebElementByXPath("//*[@id=\"fcf\"]");
			Select select = new Select(leadsDrop);

			String selectedOptionStr = null;
			List<WebElement> selectedOptions = select.getAllSelectedOptions();
			for (WebElement selectedOption : selectedOptions) {
				selectedOptionStr = selectedOption.getText();
			}

			WebElement go = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[1]/input");
			go.click();
			Assert.assertEquals(expected, selectedOptionStr);
			
			extentreport.logTestInfo("method ended");
		}

			//Testcase 23	
			@Test
			public void todaysLeads() throws Exception {

			loginIntoSF();
			WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
			leadsTab.click();
			waitImplicitly();
			Thread.sleep(2000);
			clickByID("tryLexDialogX", "No thanks");
			String inputName = "Today's Leads";
			WebElement leadsDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
			leadsDropDown.click();
			Select ld = new Select(leadsDropDown);
			ld.selectByVisibleText(inputName);
			WebElement view1 = findWebElementByID("fcf");
			Select sd = new Select(view1);
			String actual = sd.getFirstSelectedOption().getText();
			
			Assert.assertEquals(inputName, actual);

			logoutSF();
			extentreport.logTestInfo("method ended");
		}

			//Testcase 24
			@Test
			public void newLeadsButton() throws Exception {
			loginIntoSF();
			String expected = "ABCD";
			
			WebElement leadsTab = findWebElementByXPath("//*[@id=\"Lead_Tab\"]/a");
			leadsTab.click();
			Thread.sleep(2000);
			clickByID("tryLexDialogX", "No thanks");
			WebElement newButton = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
			newButton.click();
			WebElement lastName = findWebElementByXPath("//*[@id=\"name_lastlea2\"]");
			clearElement(lastName,"lastName");
			lastName.sendKeys("ABCD");
			waitImplicitly();
			WebElement company = findWebElementByXPath("//*[@id=\"lea3\"]");
			clearElement(company,"company");
			company.sendKeys("ABCD");
			WebElement leadStatus = findWebElementByXPath("//*[@id=\"lea13\"]");
			leadStatus.click();
			Select ls = new Select(leadStatus);
			ls.selectByVisibleText("Open - Not Contacted");
			WebElement saveButton = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
			saveButton.click();

			WebElement act = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
			String actual = act.getText();
			Assert.assertEquals(expected, actual);
		
			logoutSF();
			extentreport.logTestInfo("method ended");
		}
}
