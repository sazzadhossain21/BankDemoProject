package com.demobanking.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.demobanking.base.BaseClass;
import com.demobanking.reuseable.utils.Util_Class;

public class Demobanking_Listener_Reporting extends BaseClass implements ITestListener {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext result) {
		System.out.println("Test start");
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "extent-report-" + timestamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/target/" + repName);
		// htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/src/main/resources/extent-config.xml");//not
		// needed
		htmlReporter.config().setDocumentTitle("Automation Extent Report");
		htmlReporter.config().setReportName("Funtional testing Reports");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		// Attach the htmlReporter in ExtentReports
		extent.attachReporter(htmlReporter);
		// Set the user and environment information
		extent.setSystemInfo("Tester Name", "Md Sazzad Hossain");
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Environment", "QA");
	}

	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName());// create new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.GREEN));
	}

	public void onTestFailure(ITestResult result) {
		logger = extent.createTest(result.getName());// create new entry in the report
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

		// capture the screenshot if test fail
//		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+result.getName()+".png";
//		
//		File f = new File(screenshotPath); 
//		
//		if(f.exists())
//		{
//		try {
//			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
//			} 
//		catch (IOException e) 
//				{
//				e.printStackTrace();
//				}
//		}
		if (ITestResult.FAILURE == result.getStatus()) {
			try {

				String path = System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png";// screenshot
																											// store
																											// location
				Util_Class.captureScreenshot(driver, path);// method for capture screenshot
				logger.fail("Screenshot is below: " + logger.addScreenCaptureFromPath(path));
				// logger.addScreenCaptureFromPath(path);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Test failure");
	}

	public void onTestSkipped(ITestResult result) {
		logger = extent.createTest(result.getName());// create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
