package pageObjects;

import org.openqa.selenium.By;

import reusableComponents.PropertiesOperations;
import testBase.DriverFactory;
import testBase.TestBase;

public class LoginPage extends TestBase{
	
	By email = By.id("un");
	By pwd = By.id("pw");
	By signIn = By.xpath("//input[@type = 'submit']");
	
	public void login(String username, String password) {
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(email), "LoginEmailFIeld", username);
		sendKeys_custom(DriverFactory.getInstance().getDriver().findElement(pwd), "LoginPasswordFIeld", password);
		click_custom(DriverFactory.getInstance().getDriver().findElement(signIn), "LoginButton");
		
	}
	
	public void hrManagerLogin() {
		login(PropertiesOperations.getPropertyValueByKey("username"),PropertiesOperations.getPropertyValueByKey("password") );
	}
}
