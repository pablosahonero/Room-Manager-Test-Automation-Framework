package org.roommanager.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.resource.Resource;
import org.roommanager.pages.admin.common.SideMenuBarPage;

public class ResourcePage extends SideMenuBarPage{
	private WebDriver driver;
	private By addResourceButtonLocator = Resource.ADDRESOURCEBUTTON.value;
	public ResourcePage(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public CreateResourcePage clickAddResourceButton(){
		WebElement addResourceButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(addResourceButtonLocator));
		addResourceButton.click();
		return new CreateResourcePage(driver);
	}
}
