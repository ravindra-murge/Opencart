package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//h2[text()='My Account']")
	WebElement myAccountText;
	
	@FindBy(xpath = "//div/a[text()='Logout']")
	WebElement logoutLink;
	
	public void clickLogout() {
		logoutLink.click();
	}
	
	public boolean isMyAccountDisplayed() {
		try {
			return (myAccountText.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
}
