package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	//public WebDriver driver;
	
	@Test
	public void verifyLogin() {
		logger.info("*** Starting Login Test***");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail(p.getProperty("email"));
		loginPage.enterPWD(p.getProperty("password"));
		loginPage.clickLogin();
		
		MyAccountPage myaccountPage = new MyAccountPage(driver);
		boolean targetPage =myaccountPage.isMyAccountDisplayed();
		
		Assert.assertEquals(targetPage, true,"Login Passed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*** Finished Login Test***");
	}
}
