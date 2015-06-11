package org.roommanager.test.admin.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.pages.admin.resource.ResourcePage;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyResourceIsCreated {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = WebBrowser.getInstance().getGoogleChromeWebDriver();
	}
	
	@Test(priority=1)
	public void VerifyAResourceIsCreated() throws Exception {
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login
			.enterUserName()
			.enterPassword()
			.clickSignInButton();
		assertEquals("Room Manager", adminHome.getHomePageLinkText());
		
		ResourcePage resources =  adminHome
			.selectResourcesLink()
			.clickAddResourceButton()
			.enterResourceName("Resource759822661")
			.enterResourceDisplayName("Resource759822661")
			.enterResourceDescription("Description Resource759822661")
			.clickSaveResourceButton();
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
