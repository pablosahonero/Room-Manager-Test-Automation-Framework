package org.roommanager.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.resource.CreateResource;

public class CreateResourcePage {
	private WebDriver driver;
	private By resourceNameTextFieldLocator = CreateResource.RESOURCENAMEFIELD.value;
	private By resourceDisplayNameTextFieldLocator = CreateResource.RESOURCEDISPLAYNAMEFIELD.value;
	private By resourceDescriptionAreaTextLocator = CreateResource.RESOURCEDESCRIPTIONAREA.value;
	private By saveResourceButtonLocator = CreateResource.SAVEBUTTON.value;
	
	public CreateResourcePage(WebDriver driver){
		this.driver = driver;
	}
	
	public CreateResourcePage enterResourceName(String resourceName){
		WebElement resourceNameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceNameTextFieldLocator));
		resourceNameTextField.clear();
		resourceNameTextField.sendKeys(resourceName);
		return this;
	}
	
	public CreateResourcePage enterResourceDisplayName(String resourceDisplayName){
		WebElement resourceDisplayNameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceDisplayNameTextFieldLocator));
		resourceDisplayNameTextField.clear();
		resourceDisplayNameTextField.sendKeys(resourceDisplayName);
		return this;
	}
	
	public CreateResourcePage enterResourceDescription(String resourceDescription){
		WebElement resourceDescriptionAreaText = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceDescriptionAreaTextLocator));
		resourceDescriptionAreaText.clear();
		resourceDescriptionAreaText.sendKeys(resourceDescription);
		return this;
	}
	
	public ResourcePage clickSaveResourceButton(){
		WebElement saveResourceButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(saveResourceButtonLocator));
		saveResourceButton.click();
		return new ResourcePage(driver);
	}
	
}
