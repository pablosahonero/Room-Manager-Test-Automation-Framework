package org.roommanager.test.admin.login;

import static org.junit.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.pages.browser.WebBrowser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class VerifyLogin {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Test(priority=0)
	public void VerifyAUserCanLogin() throws Exception {
		driver = WebBrowser.getInstance().getGoogleChromeWebDriver();
		LoginPage login = new LoginPage(driver);
		login
		.enterUserName()
		.enterPassword()
		.clickSignInButton();
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
