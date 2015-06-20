package org.roommanager.pages.tablet.setting;

import java.util.List;

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
	private By searchTextFieldLocator = Setting.SEARCH_TEXT_FIELD.value;
	private By roomsTableFirstElementLocator= Setting.ROOMS_TABLE_FIRST_ELEMENT.value;
	private By accepButtonLocator = Setting.ACCEPT_BUTTON.value;
	private By roomsListLocator = Setting.ROOMS_LIST.value;
	private By roomElementNameLocator = Setting.ROOM_ELEMENT_NAME.value;
	private By divElementLocator = Setting.DIV_ELEMENT.value;
	
	public SettingPage(WebDriver driver){
		this.driver = driver;
		driver.get(PropertyReader.getTabletUrl());
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
	
	public SettingPage clickRoomItem(String roomName){
		WebElement room = getRoomItem(roomName);
		room.click();
		return this;
	}
	
	private WebElement getRoomItem(String roomName){
		WebElement rooms = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(roomsListLocator));
		List <WebElement> roomsList = rooms.findElements(divElementLocator);
		
		for (WebElement room : roomsList) {
			String roomItemName = room.findElement(roomElementNameLocator).getText();
			if(roomItemName.equals(roomName)){
				TestLogger.info("Room: <"+ roomItemName +"> was found on the Available Rooms List");
				return room;
			}
		}
		TestLogger.info("Room: <"+ roomName +"> wasn't found on the Available Rooms List");
		return null;
	}
	
}
