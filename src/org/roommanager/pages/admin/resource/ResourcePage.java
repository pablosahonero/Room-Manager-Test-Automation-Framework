package org.roommanager.pages.admin.resource;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.resource.Resource;
import org.roommanager.pages.admin.common.SideMenuBarPage;
import org.roommanager.util.TestLogger;

public class ResourcePage extends SideMenuBarPage{
	private WebDriver driver;
	private By addResourceButtonLocator = Resource.ADD_RESOURCE_BUTTON.value;
	private By removeResourceButtonLocator = Resource.REMOVE_RESOURCE_BUTTON.value;
	private By nextPageButtonLocator = Resource.NEXT_PAGE_BUTTON.value;
	private By nextPageInputLocator = Resource.NEXT_PAGE_INPUT.value;
	private By resourceTableNumberOfPagesLocator = Resource.RESOURCES_TABLE_NUMBER_OF_PAGES.value;
	private By resourceListLocator = Resource.RESOURCES_LIST.value;
	private By resourceListItemLocator = Resource.RESOURCE_TABLE_ITEM.value;
	private By divElementLocator = Resource.DIV_ELEMENT.value;
	
	
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
	
	public RemoveResourcePage clickRemoveResourceButton(){
		WebElement removeResourceButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(removeResourceButtonLocator));
		removeResourceButton.click();
		TestLogger.info("Remove Resource button was clicked");
		return new RemoveResourcePage(driver);
	}
	
	public ResourceInfoPage doubleClickOnResourceFromTable(String resourceName){
		WebElement resource = getResourceFromAllPagesByName(resourceName, getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(resourceListItemLocator).getText();
		Actions action = new Actions(driver);
		action.doubleClick(resource);
		action.perform();
		TestLogger.info("Double Click on Resource: <"+resourceItemName+"> from Resources Table");
		return new ResourceInfoPage(driver);
	}
	
	public ResourcePage clickOnResourceFromTable(String resourceName){
		WebElement resource = getResourceFromAllPagesByName(resourceName, getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(resourceListItemLocator).getText();
		resource.click();
		TestLogger.info("Click on Resource: <"+resourceItemName+"> from Resources Table");
		return this;
	}
	
	public String getResourceNameInTable(String resourceName){
		WebElement resource = getResourceFromAllPagesByName(resourceName, getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(resourceListItemLocator).getText();
		TestLogger.info("Resource Name: <" + resourceItemName + "> was retrieved");
		return resourceItemName;
	}
	
	public boolean verifyElementDoesNotExist(String resourceName){
		WebElement resource = getResourceFromAllPagesByName(resourceName, getResourcesTableNumberOfPages());
		return resource == null? true:false;
	}
	
	private WebElement getResourceFromAllPagesByName(String resourceName, int numberOfPages){
		WebElement resource = null;
		for(int index = 1; index <= numberOfPages; index++){
			 resource = getResourceByName(resourceName);
			 if(resource != null){
				 TestLogger.info("Resource: <" +resourceName+ "> was found in page:" + index);
				 return resource;
			 }
			 clickNextPageButton(index + 1, numberOfPages);
			 TestLogger.info("Searching for resource in page: " + index);
		}
		return resource;
	}
	
	private void clickNextPageButton(int actualPage, int numberOfPages){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(nextPageButtonLocator));
		WebElement nextPageButton = driver.findElement(nextPageButtonLocator);
		nextPageButton.click();
		
		String nextPageinput = driver.findElement(nextPageInputLocator).getAttribute("value");
		while(Integer.parseInt(nextPageinput)!= actualPage && actualPage <= numberOfPages){
			nextPageinput = driver.findElement(nextPageInputLocator).getAttribute("value");
		}
		TestLogger.info("The Next Page button was clicked");
	}
	
	private int getResourcesTableNumberOfPages(){
		WebElement numberOfPages = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceTableNumberOfPagesLocator));
		String pages = numberOfPages.getText().replace("/ ", "");
		TestLogger.info("The number of Pages of the Resources Table is: " + Integer.parseInt(pages));
		return Integer.parseInt(pages);
	}
	
	private WebElement getResourceByName(String resourceName){
		WebElement resources = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(resourceListLocator));
		List<WebElement> resourcesTable = resources.findElements(divElementLocator);
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(resourceListItemLocator).getText();
			if(resourceItemName.equals(resourceName)){
				TestLogger.info("Resource: <" + resourceItemName + "> was retrieved from Resources Table");
				return resource;
			}
		}
		TestLogger.info("Resource: <" + resourceName + "> wasn't found");
		return null;
	}
}
