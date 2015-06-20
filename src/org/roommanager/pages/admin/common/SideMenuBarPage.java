package org.roommanager.pages.admin.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.common.SideMenuBar;
import org.roommanager.pages.admin.resource.ResourcePage;
import org.roommanager.util.TestLogger;

public class SideMenuBarPage {
	private WebDriver driver;

	private By resourcesLink = SideMenuBar.RESOURCES_LINK.value;
	
	public SideMenuBarPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public ResourcePage selectResourcesLink(){
		selectLink(resourcesLink, 60);
		TestLogger.info("Resources Page Link was clicked");
		return new ResourcePage(driver);
	}
	
	private void selectLink(By locator, long timeOutInseconds){
		WebElement link = (new WebDriverWait(driver, timeOutInseconds))
			.until(ExpectedConditions.presenceOfElementLocated(locator));
		link.click();
	}
}
