package org.roommanager.pages.admin.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.home.Home;
import org.roommanager.pages.admin.common.SideMenuBarPage;

public class HomePage extends SideMenuBarPage{
	private WebDriver driver;
	private By roomManagerLinkLocator = Home.ROOMMANAGERTEXT.value;
	
	public HomePage(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public String getHomePageLinkText(){
		WebElement roomManagerLink = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(roomManagerLinkLocator));
		return roomManagerLink.getText();
	}
}
