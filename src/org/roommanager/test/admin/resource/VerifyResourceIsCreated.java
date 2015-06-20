package org.roommanager.test.admin.resource;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.pages.admin.resource.CreateResourcePage;
import org.roommanager.pages.admin.resource.ResourcePage;
import org.roommanager.util.HttpRequest;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VerifyResourceIsCreated {
	private WebDriver driver;
	private String username = PropertyReader.getUsername();
	private String password = PropertyReader.getUserPassword();
	private String resourceName = "ResourcePablo";
	private String resourceDisplayName = "ResourcePablo";
	private String resourceDescription = "Description ResourcePablo";
	private String errorMessage = "The test failed because the created Resource was not found";
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@Test
	public void verifyAResourceIsCreated() throws Exception {
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		
		ResourcePage resources =  adminHome
			.selectResourcesLink();
		
		CreateResourcePage createResource = resources
			.clickAddResourceButton()
			.enterResourceName(resourceName)
			.enterResourceDisplayName(resourceDisplayName)
			.enterResourceDescription(resourceDescription);
		
		resources = createResource
			.clickSaveResourceButton();
		
		String actualResourceName = resources.getResourceNameInTable(resourceName);
		assertEquals(errorMessage, actualResourceName, resourceName);
	}
	
	@AfterTest
	public void testTearDown(){
		HttpRequest.deleteResourceByName(resourceName);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
