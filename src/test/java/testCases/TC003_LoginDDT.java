package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
	public void verifyLoginDDT(String email, String pwd, String expected) {
		
		logger.info("***Starting TC003_LoginDDT Test validation");
		try {
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLoginLink();
		
		LoginPage lp = new LoginPage(driver);
		
		lp.enterEmail(email);
		lp.enterPWD(pwd);
		lp.clickLogin();
		
		MyAccountPage accpage = new MyAccountPage(driver);
		boolean targetpage = accpage.isMyAccountDisplayed();
		
		if(expected.equalsIgnoreCase("valid")) {
			if(targetpage == true) {
				
				Assert.assertTrue(true);
				logger.info("LoginPassed...");
				accpage.clickLogout();
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		if(expected.equalsIgnoreCase("invalid")) {
			if(targetpage == true) {
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		logger.info("*** TC003_LoginDDTTest Finished***");
		}
		catch(Exception e) {
			Assert.fail();
		}
	}

}
