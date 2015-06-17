package org.roommanager.model.tablet.home;

import org.openqa.selenium.By;

public enum Home {
	SCHEDULERLINK(By.cssSelector("div.tile-button-schedule"));
	
	public By value;
	
	private Home(By locator){
		this.value = locator;
	}
}
