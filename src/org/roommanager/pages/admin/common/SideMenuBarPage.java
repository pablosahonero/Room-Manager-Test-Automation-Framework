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
	private By emailServerLink = SideMenuBar.EMAILSERVERLINK.value;
	private By impersonationLink = SideMenuBar.IMPERSONATIONLINK.value;
	private By conferenceRoomsLink = SideMenuBar.CONFERENCELINK.value;
	private By resourcesLink = SideMenuBar.RESOURCESLINK.value;
	private By issuesLink = SideMenuBar.ISSUESLINK.value;
	private By tabletsLink = SideMenuBar.TABLETSLINK.value;
	
	public SideMenuBarPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectEmailServerLink(){
		selectLink(emailServerLink, 60);
		TestLogger.info("Email Server Page Link was clicked");
	}
	
	public void selectImpersonationLink(){
		selectLink(impersonationLink, 50);
		TestLogger.info("Impersonation Page Link was clicked");
	}
	
	public void selectConferenceRoomsLink(){
		selectLink(conferenceRoomsLink, 40);
		TestLogger.info("Conference Page Rooms Link was clicked");
	}
	
	public ResourcePage selectResourcesLink(){
		selectLink(resourcesLink, 60);
		TestLogger.info("Resources Page Link was clicked");
		return new ResourcePage(driver);
	}
	
	public void selectIssuesLink(){
		selectLink(issuesLink, 30);
		TestLogger.info("Issues Page Link was clicked");
	}
	
	public void selectTabletsLink(){
		selectLink(tabletsLink, 50);
		TestLogger.info("Tablets Page Link was clicked");
	}
	
	private void selectLink(By locator, long timeOutInseconds){
		WebElement link = (new WebDriverWait(driver, timeOutInseconds))
			.until(ExpectedConditions.presenceOfElementLocated(locator));
		link.click();
	}
}
