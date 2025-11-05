package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	// demo@opencartmart.com
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement textEmailId;
	
	// 123456
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement textPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnSubmit;

	public void enterEmail(String email) {
		textEmailId.clear();
		textEmailId.sendKeys(email);
	}
	
	public void enterPWD(String pwd) {
		textPassword.clear();
		textPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnSubmit.click();
	}
}
