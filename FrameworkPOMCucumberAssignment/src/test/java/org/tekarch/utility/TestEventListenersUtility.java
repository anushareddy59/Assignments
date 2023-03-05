package org.tekarch.utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import org.tekarch.salesForceHelperTest.SalesForceBaseTest;

public class TestEventListenersUtility implements ITestListener{
	protected static ExtentReportsUtility extentreport=null;
	protected WebDriver driver;
	//protected WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
		
		extentreport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestPassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentreport.logTestFailed(result.getMethod().getMethodName());
		SalesForceBaseTest ob = new SalesForceBaseTest();
		driver = ob.returnDriverInstance();
		String path = ob.getScreenshotBase64(driver);
		extentreport.logTestFailedWithException(result.getThrowable());
		extentreport.logTestScreenshot(path);
		
		}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport=ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport.endReport();
	}

}
