package utilities;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timestamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser Name", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed...");
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onTestFinish(ITestResult result) {
		
		extent.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		// send email functionality
//		try {
//			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
//			
//			//create the email  message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googleemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("ravindra@gamil.com", "password"));
//			email.setSSL(true);
//			email.setFrom("ravindra@gmail.com");  //sender
//			email.setSubject("Test Results");
//			email.setMsg("Please find attached reports");
//			email.addTo("swati@gmail.com");   // receiver
//			email.attach(url,"extent report","please check report...");
//			email.send();
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
	}
}
