package org.roommanager.pages.tablet.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.tablet.home.Home;
import org.roommanager.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.util.TestLogger;

public class HomePage {
	private WebDriver driver;
	private By schedulerLinkLocator = Home.SCHEDULERLINK.value;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
	}
	
	public SchedulerPage clickSchedulerLink(){
		WebElement schedulerLink = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(schedulerLinkLocator));
		schedulerLink.click();
		TestLogger.info("Scheduler Link was clicked");
		return new SchedulerPage(driver);
	}
}
