package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class AccountRegistrationTest extends BaseClass {

//	public WebDriver driver ;
	public HomePage home;
	public RegisterPage register;
	
	
	
	@Test
	public void verifyAccountRegistration() {
		logger.info("****Starting Testcase AccountRegistration****");
		try {
		home = new HomePage(driver);
		home.clickMyAccount();
		logger.info("clicked on my account link");
		home.clickRegisterLink();
		logger.info("clicked on my register link");
		register = new RegisterPage(driver);
		logger.info("providing customer details");
		register.setFirstName("ravindra");
		register.setLastName("murge");
		register.setEmail(randomString()+"@gmail.com"); // create random email
		register.setTelephone("2345678910");
		register.setPassword("abc@123");
		register.setConfirmPassword("abc@123");
		register.checkPrivacyPolicy();
		register.clickContinue();
		
		logger.info("Validating expected msg");
		String confrimMsg = register.getConfirmationMsg();
		
		Assert.assertEquals(confrimMsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("TestFailed...");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		logger.info("****Finished Testcase AccountRegistration****");
	}
	
	public String randomString() {
	String genString =	RandomStringUtils.randomAlphabetic(8);
	
	return genString;
	}
}
