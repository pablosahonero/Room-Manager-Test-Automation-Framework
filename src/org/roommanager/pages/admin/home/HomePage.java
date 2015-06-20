package org.roommanager.pages.admin.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.home.Home;
import org.roommanager.pages.admin.common.SideMenuBarPage;
import org.roommanager.util.TestLogger;

public class HomePage extends SideMenuBarPage{
	private WebDriver driver;
	private By roomManagerLinkLocator = Home.ROOM_MANAGER_TEXT.value;
	
	public HomePage(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public String getHomePageLinkText(){
		WebElement roomManagerLink = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(roomManagerLinkLocator));
		TestLogger.info("Home Page Link Text: <" + roomManagerLink.getText() + "> was retrieved");
		return roomManagerLink.getText();
	}
}
