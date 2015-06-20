package org.roommanager.test.admin.resource;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.pages.admin.resource.ResourceInfoPage;
import org.roommanager.pages.admin.resource.ResourcePage;
import org.roommanager.util.HttpRequest;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyResourceIsUpdated {
	private WebDriver driver;
	private String resourceName = "ResourcePablo";
	private String resourceDisplayName = "ResourcePablo";
	private String resourceDescription = "Description ResourcePablo";
	private String resourceIcon = "";
	private String username = PropertyReader.getUsername();
	private String password = PropertyReader.getUserPassword();
	private String resourceNameUpdated = "ResourcePabloUpdated";
	private String resourceDisplayNameUpdated = "ResourcePabloDisplayNameUpdated";
	private String errorMessage = "The test failed because the Resource was not updated";
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@BeforeTest
	public void testSetUp(){
		
		HttpRequest.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	@Test
	public void verifyAResourceIsUpdated() throws Exception {
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		
		ResourcePage resources =  adminHome
			.selectResourcesLink();
		
		ResourceInfoPage resourceInfo = resources
			.doubleClickOnResourceFromTable(resourceName);
		
		resources = resourceInfo
			.enterResourceName(resourceNameUpdated)
			.enterResourceDisplayName(resourceDisplayNameUpdated)
			.clickSaveResourceButton();
		
		String actualResourceName = resources.getResourceNameInTable(resourceNameUpdated);
		assertEquals(errorMessage, actualResourceName, resourceNameUpdated);
	}
	
	@AfterTest
	public void testTearDown(){
		HttpRequest.deleteResourceByName(resourceNameUpdated);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
