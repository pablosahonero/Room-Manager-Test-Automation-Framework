package org.roommanager.pages.admin.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.common.SideMenuBar;
import org.roommanager.pages.admin.resource.ResourcePage;

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
		selectLink(emailServerLink);
	}
	
	public void selectImpersonationLink(){
		selectLink(impersonationLink);
	}
	
	public void selectConferenceRoomsLink(){
		selectLink(conferenceRoomsLink);
	}
	
	public ResourcePage selectResourcesLink(){
		selectLink(resourcesLink);
		return new ResourcePage(driver);
	}
	
	public void selectIssuesLink(){
		selectLink(issuesLink);
	}
	
	public void selectTabletsLink(){
		selectLink(tabletsLink);
	}
	
	private void selectLink(By locator){
		WebElement link = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(locator));
		link.click();
	}
}
