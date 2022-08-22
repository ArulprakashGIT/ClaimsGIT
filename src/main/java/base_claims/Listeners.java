package base_claims;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resourse.ExtentReporterNG;

public class Listeners extends BaseClaims implements ITestListener{

	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal <ExtentTest>extenttest =new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestStart(result);
		test=extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSuccess(result);
		extenttest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extenttest.get().fail(result.getThrowable());
		String failmethordname =result.getMethod().getMethodName();
		try {
			driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch(Exception e){

		}
		try {
		     extenttest.get().addScreenCaptureFromPath(getScreenshotPath(failmethordname, driver), result.getMethod().getMethodName());
		     //getScreenshotPath(failmethordname, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//Listener POM XML comment
		/*
		 <listeners>
         <listener class-name="claims.Listeners"/>
         </listeners>
		 */
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		//ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//ITestListener.super.onFinish(context);
		extent.flush();
		
	}

}
