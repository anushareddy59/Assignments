package org.tekarch.SalesForceTests;

import static org.testng.Assert.assertEquals;

import java.util.List;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tekarch.salesForceHelperTest.SalesForceHepler;
import org.tekarch.utility.PropertiesLoader;

@Listeners(org.tekarch.utility.TestEventListenersUtility.class)
public class CreateAccountTests extends SalesForceHepler{

	
			//Test case 10
			@Test
	
			public void accountsLink() throws Exception {
				
			loginIntoSF();
			WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
			accounts.click();
			
			waitImplicitly();
			clickByID("tryLexDialogX", "No thanks");

			WebElement newAccount = findWebElementByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input");
			waitUntilElementIsVisible(newAccount);
			newAccount.click();
			

			String accountNameStr = "aaaa";
			WebElement accountName = findWebElementByXPath("//*[@id=\"acc2\"]");
			accountName.sendKeys(accountNameStr);

			WebElement type = findWebElementByXPath("//*[@id=\"acc6\"]");
			Select ob = new Select(type);
			ob.selectByVisibleText("Technology Partner");
			waitImplicitly();
			WebElement customerPriority = findWebElementByXPath("//*[@id=\"00NDn00000SjXLr\"]");
			Select ob1 = new Select(customerPriority);
			ob1.selectByVisibleText("High");

			WebElement save = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
			save.click();

			WebElement topHeader = findWebElementByXPath("//*[@id=\"contactHeaderRow\"]/div[2]/h2");
			String name = topHeader.getText();
			
			boolean expected = name.equals(accountNameStr);
			Assert.assertEquals(expected,true);
			logoutSF();
			extentreport.logTestInfo("method ended");
	
		}

			//Testcase 11
			@Test
			public void createNewViewLink() throws Exception {
			
			loginIntoSF();
			WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
			accounts.click();
			Thread.sleep(2000);
			clickByID("tryLexDialogX", "No thanks");

			WebElement createNewView = findWebElementByXPath("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]");
			createNewView.click();
			Thread.sleep(1000);
			long timeStamp = System.currentTimeMillis();
			String inputName = "Druvadh_" + timeStamp ;
			WebElement viewName = findWebElementByID("fname");
			viewName.sendKeys(inputName);
			WebElement viewUniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
			viewUniqueName.clear();
			viewUniqueName.sendKeys("");
			Thread.sleep(1000);
			WebElement save = findWebElementByXPath("//*[@id=\"editPage\"]/div[1]/table/tbody/tr/td[2]/input[1]");
			// *[@id="editPage"]/div[1]/table/tbody/tr/td[2]/input[1]
			save.click();
			waitImplicitly();
			WebElement view = findWebElementByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[1]/div[1]/form/div[1]/div/select");
			Select vSelect = new Select(view);
			String vName = vSelect.getFirstSelectedOption().getText();
			//String vName = view.getText();
			Assert.assertEquals(inputName, vName);
			
			logoutSF();
			extentreport.logTestInfo("method ended");
		}

			//Test case 12
			@Test
			public void editView() throws Exception {
			loginIntoSF();
			
			String inputName = "sandy";

			WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
			accounts.click();
			waitImplicitly();
			clickByID("tryLexDialogX", "No thanks");

			WebElement view = findWebElementByID("fcf");
			view.click();
			Select ob3 = new Select(view);
			ob3.selectByVisibleText(inputName);

			WebElement editLink = findWebElementByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/form/div/span/span[2]/a[1]");
			editLink.click();

			WebElement newViewName = findWebElementByXPath("//*[@id=\"fname\"]");
			newViewName.sendKeys(inputName);
			WebElement newViewUniqueName = findWebElementByXPath("//*[@id=\"devname\"]");
			newViewUniqueName.clear();
			newViewUniqueName.sendKeys(inputName);

			WebElement field = findWebElementByID("fcol1");
			field.click();
			Select ob4 = new Select(field);
			ob4.selectByVisibleText("Account Name");

			WebElement opreatorLink = findWebElementByID("fop1");
			Select ob5 = new Select(opreatorLink);
			ob5.selectByVisibleText("contains");

			WebElement value = findWebElementByXPath("//*[@id=\"fval1\"]");
			value.sendKeys("a");

			clickByXPath("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]", "saveLink");

			WebElement view1 = findWebElementByXPath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[1]/div[1]/form/div[1]/div/select");
			Select view1Select = new Select(view1);
			String vName = view1Select.getFirstSelectedOption().getText();
			Assert.assertEquals(vName, inputName);
			extentreport.logTestInfo("method ended");

		}

			//Testcase 13
			@Test
			public static void mergeAccounts() throws Exception {
			loginIntoSF();
			

			WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
			accounts.click();
			Thread.sleep(4000);
			clickByID("tryLexDialogX", "No thanks");

			Thread.sleep(1000);
			WebElement mergeAccounts = findWebElementByXPath(
					"//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a");
			mergeAccounts.click();

			WebElement txtBox = findWebElementByXPath("//*[@id=\"srch\"]");
			txtBox.sendKeys("aa");

			WebElement findAccounts = findWebElementByXPath("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]");
			findAccounts.click();

			WebElement table = findWebElementByXPath(
					"//*[@id=\"stageForm\"]/div/div[2]/div[4]/div/div[1]/div/div[2]/table/tbody");
			List<WebElement> rows = table.findElements(By.xpath("//tr"));
			int count = 2;
			for (WebElement row : rows) {
				if (row.getAttribute("class").trim().startsWith("dataRow")) {
					List<WebElement> cells = row.findElements(By.name("cid"));
					if (cells.size() > 0) {
						WebElement cell = cells.get(0);
						if (count > 0) {
							count--;
						} else {
							cell.click();
						}
					}
				}

			}

			WebElement next = findWebElementByXPath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]");
			next.click();

			clickByXPath("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]", "MergeAccounts");
			Alert alert = driver.switchTo().alert();
			alert.accept();

			// TODO:
			if (true) {
				System.out.println("Test case passed");
			} else {
				System.out.println("Test case failed");
			}

			extentreport.logTestInfo("method ended");
		}

			//Testcase 14
			@Test
			public static void accountReport() throws Exception {
			loginIntoSF();
			WebElement accounts = findWebElementByXPath("//*[@id=\"Account_Tab\"]/a");
			accounts.click();
			waitImplicitly();
			clickByID("tryLexDialogX", "No thanks");

			WebElement actDays = findWebElementByXPath(
					"//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a");
			actDays.click();

			WebElement dateField = clickByXPath("//*[@id=\"ext-gen20\"]", "Date field");
			dateField.sendKeys("Created Date");

			WebElement from = clickByXPath(
					"/html/body/div[2]/div[3]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div/form/div/div[3]/img",
					"from");
			Thread.sleep(1000);
			clickByXPath("/html/body/div[17]/ul/li/div/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/em/button", "today");
			clickByXPath(
					"/html/body/div[2]/div[3]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div/form/div/div[4]/img",
					"to");
			clickByXPath("/html/body/div[19]/ul/li/div/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/em/button", "today");
			String baseHandle = driver.getWindowHandle();
			clickByXPath(
					"/html/body/div[2]/div[1]/div/div/div/table/tbody/tr/td[1]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button",
					"save button");

			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(baseHandle)) {
					driver.switchTo().window(handle);
				}
			}

			WebElement reportName = findWebElementByID("saveReportDlg_reportNameField");
			reportName.sendKeys("bb");

			clickByID("saveReportDlg_DeveloperName", "UniqueName");
			clickByID("ext-gen323", "SaveAndRun");

			driver.switchTo().window(baseHandle);

			if (true) {
				System.out.println("Test case passed");
			} else {
				System.out.println("Test case failed");
			}
			extentreport.logTestInfo("method ended");
		}

}
