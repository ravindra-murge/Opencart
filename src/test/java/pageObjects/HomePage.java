package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myAccountDrpDown;
	
	@FindBy(xpath = "//ul/li/a[text()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath = "//ul/li/a[text()='Login']")
	WebElement loginLink;
	
	public void clickMyAccount() {
		myAccountDrpDown.click();
	}
	
	public void clickRegisterLink() {
		registerLink.click();
	}
	
	public void clickLoginLink() {
		loginLink.click();
	}
}
