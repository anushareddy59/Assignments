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

public class CreateOptyTests extends SalesForceHepler {

	// Testcase 15
	@Test
	public static void opportunities() throws Exception {
		loginIntoSF();
		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);
		WebElement viewDropDown = findWebElementByXPath("//*[@id=\"fcf\"]");
		viewDropDown.click();
		Thread.sleep(2000);
		Select opp1 = new Select(viewDropDown);
		if (opp1.getOptions().isEmpty()) {
			System.out.println("Testcase failed.");
		} else {
			System.out.println("Testcase passed.");
		}
		extentreport.logTestInfo("method ended");
		
	}

	// TestCase 16
	@Test
	public void createNewOpty() throws Exception {
		
		loginIntoSF();
		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		clickByXPath("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "newLink");

		WebElement optyName = findWebElementByXPath("//*[@id=\"opp3\"]");
		optyName.sendKeys("mariocart");
		WebElement accName = findWebElementByXPath("//*[@id=\"opp4\"]");
		accName.sendKeys("automation");

		WebElement leadSource = findWebElementByXPath("//*[@id=\"opp6\"]");
		Select ls1 = new Select(leadSource);
		ls1.selectByVisibleText("Web");

		WebElement closeDate = findWebElementByXPath("//*[@id=\"opp9\"]");
		closeDate.click();

		WebElement date = findWebElementByXPath(
				"/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/table/tbody/tr[4]/td[4]");
		date.click();

		WebElement stage = clickByXPath("//*[@id=\"opp11\"]", "stage");
		Select st = new Select(stage);
		st.selectByVisibleText("Prospecting");

		WebElement probability = findWebElementByXPath("//*[@id=\"opp12\"]");
		probability.clear();
		probability.sendKeys("10");

		WebElement savelink = findWebElementByXPath("//*[@id=\"bottomButtonRow\"]/input[1]");
		savelink.click();

		WebElement pageDescription = findWebElementByXPath("//*[@id=\"bodyCell\"]/div[1]/div[1]/div[1]/h2");
		String actual = "mariocart";
		String expected = pageDescription.getText();
		Assert.assertEquals(actual, expected);
		
		extentreport.logTestInfo("method ended");
	}

	// Testcase 17
	@Test
	public  void pipeLineReport() throws Exception {
		loginIntoSF();
		String expected = "Opportunity Pipeline";
		

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		WebElement optyPipeline = findWebElementByXPath(
				"//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a");
		optyPipeline.click();

		WebElement textElement = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String actual = getTextFromWebElement(textElement, "Opportunity Pipeline");
		Assert.assertEquals(actual, expected);
		
		extentreport.logTestInfo("method ended");
	}

	// Testcase 18
	@Test
	public  void stuckOportunitiesReport() throws Exception {
		loginIntoSF();
		String expected = "Stuck Opportunities";
		

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		WebElement stuckOpportunities = findWebElementByXPath(
				"//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a");
		stuckOpportunities.click();

		WebElement txtElement = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String actual = getTextFromWebElement(txtElement, "Stuck Opportunities");
		Assert.assertEquals(actual, expected);
		
		extentreport.logTestInfo("method ended");
	}

	// Testcase19
	@Test
	public  void quarterelySummaryReport() throws Exception {
		loginIntoSF();
		String expected = "Opportunity Report";
		

		clickByID("Opportunity_Tab", "opportunity");
		Thread.sleep(2000);

		clickByID("tryLexDialogX", "No thanks");
		Thread.sleep(2000);

		WebElement interval = findWebElementByXPath("//*[@id=\"quarter_q\"]");
		interval.click();
		Select val = new Select(interval);
		val.selectByVisibleText("Next FQ");

		WebElement include = findWebElementByXPath("//*[@id=\"open\"]");
		include.click();
		Select val1 = new Select(include);
		val1.selectByVisibleText("Open Opportunities");
		Thread.sleep(2000);
		WebElement runReport = findWebElementByXPath("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input");
		runReport.click();

		WebElement txtElement = driver
				.findElement(By.xpath("//*[@id=\"noTableContainer\"]/div/div[1]/div[1]/div[1]/h1"));
		String actual = getTextFromWebElement(txtElement, "Opportunity Report");
		Assert.assertEquals(actual, expected);
		
		extentreport.logTestInfo("method ended");
	}

}
