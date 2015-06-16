package org.roommanager.test.admin.resource;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.pages.admin.login.LoginPage;
import org.roommanager.pages.admin.resource.RemoveResourcePage;
import org.roommanager.pages.admin.resource.ResourcePage;
import org.roommanager.util.HttpRequest;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.WebBrowser;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyResourceIsRemoved {
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
	public void verifyAResourceIsRemoved() throws Exception {
		String username = PropertyReader.getUsername();
		String password = PropertyReader.getUserPassword();
		String resourceName = "ResourcePablo";
		String errorMessage = "The test failed because the Resource was not removed";
		
		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		
		ResourcePage resources =  adminHome
			.selectResourcesLink();
		
		RemoveResourcePage removeResource = resources
			.searchResourceByName(resourceName)
			.clickFirstTableElementCheckBox()
			.clickRemoveResourceButton();
		
		resources = removeResource
			.clickRemoveResourceButton()
			.searchResourceByName(resourceName);
		
		boolean hasResources = resources.hasResourceTableElements();
		Assert.assertTrue(hasResources, errorMessage);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
