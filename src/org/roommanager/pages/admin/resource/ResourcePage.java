package org.roommanager.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.resource.Resource;
import org.roommanager.pages.admin.common.SideMenuBarPage;
import org.roommanager.util.TestLogger;

public class ResourcePage extends SideMenuBarPage{
	private WebDriver driver;
	private By addResourceButtonLocator = Resource.ADDRESOURCEBUTTON.value;
	private By searchResourceTextFieldLocator = Resource.SEARCHRESOURCETEXTFIELD.value;
	private By firstResourcesTableElementLocator = Resource.FIRSTRESOURCESTABLEELEMENT.value;
	
	public ResourcePage(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public CreateResourcePage clickAddResourceButton(){
		WebElement addResourceButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(addResourceButtonLocator));
		addResourceButton.click();
		TestLogger.info("Add Resource button was clicked");
		return new CreateResourcePage(driver);
	}
	
	public ResourcePage searchResourceByName(String name){
		WebElement searchResourceTextField = (new WebDriverWait(driver, 160))
			.until(ExpectedConditions.presenceOfElementLocated(searchResourceTextFieldLocator));
		searchResourceTextField.clear();
		searchResourceTextField.sendKeys(name);
		TestLogger.info("Resource Name: <" + name + "> was entered in the Search Resource Text Field");
		return this;
	}
	
	public String getFirstTableElementName(){
		WebElement firstResourceTableElement = (new WebDriverWait(driver, 160))
			.until(ExpectedConditions.presenceOfElementLocated(firstResourcesTableElementLocator));
		TestLogger.info("First Resource Table Element Name: <" + firstResourceTableElement.getText() + "> was retrieved");
		return firstResourceTableElement.getText();
	}
}
