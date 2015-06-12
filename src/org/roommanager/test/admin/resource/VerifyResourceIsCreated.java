package org.roommanager.test.admin.resource;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.pages.admin.resource.CreateResourcePage;
import org.roommanager.pages.admin.resource.ResourcePage;
import org.roommanager.util.HttpRequest;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.TestLogger;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VerifyResourceIsCreated {
	private WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@Test(priority=0)
	public void verifyAResourceIsCreated() throws Exception {
		String username = PropertyReader.getUsername();
		String password = PropertyReader.getUserPassword();
		String homePageExpectedLinkText = "Room Manager";
		String resourceName = "ResourcePablo";
		String resourceDisplayName = "ResourcePablo";
		String resourceDescription = "Description ResourcePablo";
		
		TestLogger.debug ("Start of Test: Verify a Resource is created"); 
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		assertEquals(homePageExpectedLinkText, adminHome.getHomePageLinkText());
		
		ResourcePage resources =  adminHome
			.selectResourcesLink();
		
		CreateResourcePage createResource = resources
			.clickAddResourceButton()
			.enterResourceName(resourceName)
			.enterResourceDisplayName(resourceDisplayName)
			.enterResourceDescription(resourceDescription);
		
		resources = createResource
			.clickSaveResourceButton()
			.searchResourceByName(resourceName);
		assertEquals(resources.getFirstTableElementName(), resourceName);
	}
	
	@AfterTest
	public void testTearDown(){
		HttpRequest.deleteResourceByName("ResourcePablo");
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
