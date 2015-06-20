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
	private String username = PropertyReader.getUsername();
	private String password = PropertyReader.getUserPassword();
	private String resourceName = "ResourcePablo";
	private String resourceDisplayName = "ResourcePablo";
	private String resourceDescription = "Description ResourcePablo";
	private String resourceIcon = "";
	private String errorMessage = "The test failed because the Resource was not removed";
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@BeforeTest
	public void testSetUp(){
		HttpRequest.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	@Test
	public void verifyAResourceIsRemoved() throws Exception {

		LoginPage login = new LoginPage(driver);
		
		HomePage adminHome = login
			.enterUserName(username)
			.enterPassword(password)
			.clickSignInButton();
		
		ResourcePage resources =  adminHome
			.selectResourcesLink();
		
		RemoveResourcePage removeResource = resources
			.clickOnResourceFromTable(resourceName)
			.clickRemoveResourceButton();
		
		resources = removeResource
			.clickRemoveResourceButton();
		
		boolean resourceDoesNotExist = resources.verifyElementDoesNotExist(resourceName);
		Assert.assertTrue(resourceDoesNotExist, errorMessage);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
