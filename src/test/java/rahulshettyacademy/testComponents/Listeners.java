package rahulshettyacademy.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends Basetest implements ITestListener {
	ExtentTest test;
	 ExtentReports extent=ExtentReporterNG.getReportObject();
	 ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();
	 
	 
	@Override
	public void onTestStart(ITestResult result) {
		
	test=extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	
//	@Override
//	public void onTestSuccesss(ITestResult result)	{
//		test.log(Status.PASS, "Test Passed");
//	}
	
   @Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	String filepath=null;
	try {
		filepath = getScreenshot(result.getMethod().getMethodName(),driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	
   
   
   @Override
   public void onFinish(ITestContext context)
   {
	   extent.flush();
   }
}
