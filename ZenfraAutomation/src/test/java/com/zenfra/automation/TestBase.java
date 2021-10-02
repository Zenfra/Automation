 package com.zenfra.automation;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Guice;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.service.ExtentTestManager;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import com.google.inject.Inject;
import com.zenfra.automation.pagefactory.PageFactoryManager;
import com.zenfra.automation.setup.DriverModule;
import com.zenfra.automation.utils.ExcelDataUtils;

@Listeners({ ExtentITestListenerClassAdapter.class })
@Guice(modules = { DriverModule.class })
public class TestBase {

	@Inject
	Logger logger;

	@Inject
	PageFactoryManager pageFactoryManager;

	ExcelDataUtils excelDataUtils;
	DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

	@BeforeTest
	public void inalizeUtils() {
		excelDataUtils = new ExcelDataUtils("TestResults" + System.currentTimeMillis() + ".xls");
		excelDataUtils.createExcelFile();
	}

	@AfterMethod
	public void getResult(ITestResult result) {

		switch (result.getStatus()) {
		case ITestResult.FAILURE:
			logger.info("FAIL: " + result.getName());
			logger.log(Level.SEVERE, "" + result.getThrowable());
			captureScreenShotForTest();
			break;

		case ITestResult.SKIP:
			logger.info("SKIP: " + result.getName());
			captureScreenShotForTest();
			break;

		default:
			logger.info("PASS: " + result.getName());
			//captureScreenShotForTest();
			break;
		}
		excelDataUtils.insertResult(result.getMethod().getDescription(), result.getStatus(),
				simple.format(new Date(result.getStartMillis())), simple.format(new Date(result.getEndMillis())),
				result.getEndMillis() - result.getStartMillis());
	}

	@AfterTest
	//public void testTearDown() {
	//	pageFactoryManager.closeBrowser();
	//}

	public void captureScreenShotForTest() {
		File scrFile = ((TakesScreenshot) pageFactoryManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
		File screenshotName = new File("screenshot\\" + System.currentTimeMillis() + ".png");
		try {
			FileUtils.copyFile(scrFile, screenshotName);
			ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotName.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//ExtentTestManager.getTest().log(Status.INFO, "Page screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName.getAbsolutePath()));
		//Reporter.log("<a href='"+ screenshotName.getAbsolutePath() + "'> <img src=" + screenshotName.getAbsolutePath() + " height='100' width='100'/> </a>");
	}
}
