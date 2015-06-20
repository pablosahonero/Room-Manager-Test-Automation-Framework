package org.roommanager.model.admin.home;

import org.openqa.selenium.By;

public enum Home {
	ROOM_MANAGER_TEXT(By.linkText("Room Manager"));
	
	public By value;
	
	private Home(By locator){
		this.value = locator;
	}
}
