package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.DriverFactory;
import testBase.TestBase;

public class LoginTest extends TestBase{
	
	LoginPage login = new LoginPage();
	@Test
	//Admin User Login
	public void AdminLogin() {
		login.login("mani@allwyncorp.com", "Ms@12345");
		String expected_URL = "https://allwyntest.appiancloud.com/suite/sites/protrack";
		String actual_URL = DriverFactory.getInstance().getDriver().getCurrentUrl();
		Assert.assertEquals(actual_URL, expected_URL, "Login failed");
		//test git repo
		
		/*
		 * <test name="LoginTests">
		<classes>
			<class name="Tests.UserLoginTests" />
		</classes>
	</test> <!-- Test -->
		<test name="DataDriven Tests">
		<classes>
		<class name="Tests.TestCase" /> 
		</classes>  
	</test> <!-- Test -->*/
	}
}
