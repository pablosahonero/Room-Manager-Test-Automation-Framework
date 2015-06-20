package org.roommanager.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.resource.CreateResource;
import org.roommanager.util.TestLogger;

public class CreateResourcePage {
	private WebDriver driver;
	private By resourceNameTextFieldLocator = CreateResource.RESOURCE_NAME_FIELD.value;
	private By resourceDisplayNameTextFieldLocator = CreateResource.RESOURCE_DISPLAY_NAME_FIELD.value;
	private By resourceDescriptionAreaTextLocator = CreateResource.RESOURCE_DESCRIPTION_AREA.value;
	private By saveResourceButtonLocator = CreateResource.SAVE_BUTTON.value;
	
	public CreateResourcePage(WebDriver driver){
		this.driver = driver;
	}
	
	public CreateResourcePage enterResourceName(String resourceName){
		WebElement resourceNameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceNameTextFieldLocator));
		resourceNameTextField.clear();
		resourceNameTextField.sendKeys(resourceName);
		TestLogger.info("Resource Name: <" + resourceName +  "> was entered");
		return this;
	}
	
	public CreateResourcePage enterResourceDisplayName(String resourceDisplayName){
		WebElement resourceDisplayNameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceDisplayNameTextFieldLocator));
		resourceDisplayNameTextField.clear();
		resourceDisplayNameTextField.sendKeys(resourceDisplayName);
		TestLogger.info("Resource Display Name: <" + resourceDisplayName +  "> was entered");
		return this;
	}
	
	public CreateResourcePage enterResourceDescription(String resourceDescription){
		WebElement resourceDescriptionAreaText = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceDescriptionAreaTextLocator));
		resourceDescriptionAreaText.clear();
		resourceDescriptionAreaText.sendKeys(resourceDescription);
		TestLogger.info("Resource Description: <" + resourceDescription +  "> was entered");
		return this;
	}
	
	public ResourcePage clickSaveResourceButton(){
		WebElement saveResourceButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(saveResourceButtonLocator));
		saveResourceButton.click();
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.invisibilityOfElementLocated(saveResourceButtonLocator));
		TestLogger.info("Save Resource button was clicked");
		return new ResourcePage(driver);
	}
	
}
