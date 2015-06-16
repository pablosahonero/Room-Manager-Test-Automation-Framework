package org.roommanager.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.resource.RemoveResource;
import org.roommanager.util.TestLogger;

public class RemoveResourcePage {
	private WebDriver driver;
	private By removeResourceButtonLocator = RemoveResource.REMOVEBUTTON.value;
	
	public RemoveResourcePage(WebDriver driver){
		this.driver = driver;
	}
	
	public ResourcePage clickRemoveResourceButton(){
		WebElement removeResourceButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(removeResourceButtonLocator));
		removeResourceButton.click();
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.invisibilityOfElementLocated(removeResourceButtonLocator));
		TestLogger.info("Remove Resource button from Remove Resource Page was clicked");
		return new ResourcePage(driver);
	}
}
