package org.roommanager.test.admin.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyUserLogin {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = WebBrowser.getInstance().getGoogleChromeWebDriver();
	}
	
	@Test(priority=0)
	public void VerifyAUserCanLogin() throws Exception {
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login
								.enterUserName()
								.enterPassword()
								.clickSignInButton();
		assertEquals("Room Manager", adminHome.getHomePageLinkText());
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	}
}
