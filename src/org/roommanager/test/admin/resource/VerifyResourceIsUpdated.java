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
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@BeforeTest
	public void testSetUp(){
		String resourceName = "ResourcePablo";
		String resourceDisplayName = "ResourcePablo";
		String resourceDescription = "Description ResourcePablo";
		String resourceIcon = "";
		HttpRequest.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	@Test
	public void verifyAResourceIsUpdated() throws Exception {
		String username = PropertyReader.getUsername();
		String password = PropertyReader.getUserPassword();
		String resourceName = "ResourcePablo";
		String resourceNameUpdated = "ResourcePabloUpdated";
		String resourceDisplayNameUpdated = "ResourcePabloDisplayNameUpdated";
		String errorMessage = "The test failed because the Resource was not updated";
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		
		ResourcePage resources =  adminHome
			.selectResourcesLink();
		
		ResourceInfoPage resourceInfo = resources
			.searchResourceByName(resourceName)
			.doubleClickFirstTableElement();
		
		resources = resourceInfo
			.enterResourceName(resourceNameUpdated)
			.enterResourceDisplayName(resourceDisplayNameUpdated)
			.clickSaveResourceButton()
			.searchResourceByName(resourceNameUpdated);
		
		assertEquals(errorMessage, resources.getFirstTableElementName(), resourceNameUpdated);
		assertEquals(errorMessage, resources.getFirstTableElementDisplayName(), resourceDisplayNameUpdated);
			
	}
	
	@AfterTest
	public void testTearDown(){
		String resourceName = "ResourcePabloUpdated";
		HttpRequest.deleteResourceByName(resourceName);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
