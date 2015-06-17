package org.roommanager.pages.tablet.setting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.tablet.setting.Setting;
import org.roommanager.pages.tablet.home.HomePage;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.TestLogger;

public class SettingPage {
	private WebDriver driver;
	private By searchTextFieldLocator = Setting.SEARCHTEXTFIELD.value;
	private By roomsTableFirstElementLocator= Setting.ROOMSTABLEFIRSTELEMENT.value;
	private By accepButtonLocator = Setting.ACCEPTBUTTON.value;
	//private By noItemMessageLocator = Setting.NOITEMMESSAGE.value;
	
	public SettingPage(WebDriver driver){
		this.driver = driver;
		driver.get(PropertyReader.getTabletUrl());
		//driver.navigate().refresh();
	}
	
	public SettingPage waitForSettingpageToLoad(){
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.presenceOfElementLocated(searchTextFieldLocator));
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(roomsTableFirstElementLocator));
		return this;
	}
	
	public SettingPage searchRoomByName(String roomName){
		WebElement searchTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(searchTextFieldLocator));
		searchTextField.clear();
		searchTextField.sendKeys(roomName);
		TestLogger.info("Room Name: <" + roomName + "> was entered in the Search Room Text Field");
		return this;
	}
	
	public SettingPage clickFirstRoomTableElement(){
		WebElement roomsTableFirstElement = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(roomsTableFirstElementLocator));
		roomsTableFirstElement.click();
		TestLogger.info("First Element from Rooms Table was clicked");
		return this;
	}
	
	public HomePage clickAcceptButton(){
		WebElement accepButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(accepButtonLocator));
		accepButton.click();
		TestLogger.info("Accept Button was clicked");
		return new HomePage(driver);
	}
	
}
