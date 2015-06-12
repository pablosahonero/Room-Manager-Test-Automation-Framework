package org.roommanager.test.admin.login;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.TestLogger;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VerifyUserLogin {
	private WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@Test(priority=1)
	public void verifyAUserCanLogin() throws Exception {
		String username = PropertyReader.getUsername();
		String password = PropertyReader.getUserPassword();
		String homePageExpectedLinkText = "Room Manager";
		
		TestLogger.debug ("Start of Test: Verify a User can Login"); 
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		assertEquals(homePageExpectedLinkText, adminHome.getHomePageLinkText());
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
