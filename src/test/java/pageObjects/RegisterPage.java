package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement inputFirstName;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement inputLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement inputEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement inputTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement inputPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement inputConfirmPwd;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement checkboxPrivacyPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement BtnContinue;
	
	@FindBy(xpath = "//div[@id='content']/h1[text()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String firstname) {
		inputFirstName.sendKeys(firstname);
		
	}
	
	public void setLastName(String lastname) {
		inputLastName.sendKeys(lastname);
	}
	
	public void setEmail(String email) {
		inputEmail.sendKeys(email);
	}
	
	public void setTelephone(String telephone) {
		inputTelephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd) {
		inputPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String ConfirmPwd) {
		inputConfirmPwd.sendKeys(ConfirmPwd);
	}
	
	public void checkPrivacyPolicy() {
		checkboxPrivacyPolicy.click();
	}
	
	public void clickContinue() {
//		BtnContinue.click();
//		
//		//sol2
//		BtnContinue.submit();
//		
//		//sol3
//		Actions action = new Actions(driver);
//		action.moveToElement(BtnContinue).click().perform();
//		
//		//sol4
//		BtnContinue.sendKeys(Keys.RETURN);
		
		//sol5
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(BtnContinue)).click();
	}
	
	public String getConfirmationMsg() {
		try {
			return msgConfirmation.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
}
