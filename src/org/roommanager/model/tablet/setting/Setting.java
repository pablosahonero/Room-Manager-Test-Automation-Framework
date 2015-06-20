package org.roommanager.model.tablet.setting;

import org.openqa.selenium.By;

public enum Setting {
	SEARCH_TEXT_FIELD(By.id("txtSearch")),
	ROOMS_TABLE_FIRST_ELEMENT(By.cssSelector("small.block.ng-binding")),
	ROOMS_LIST(By.xpath("//div[1]/div/div[1]/div[3]/div/div")),
	ROOM_ELEMENT_NAME(By.xpath("h4")),
	ACCEPT_BUTTON(By.xpath("//button")),
	DIV_ELEMENT(By.xpath("div"));
	
	public By value;
	
	private Setting(By locator){
		this.value = locator;
	}
}
