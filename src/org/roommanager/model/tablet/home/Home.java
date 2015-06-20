package org.roommanager.model.tablet.home;

import org.openqa.selenium.By;

public enum Home {
	SCHEDULER_LINK(By.xpath("//div[1]/div/div/div/div/div[2]/div/div[2]"));
	
	public By value;
	
	private Home(By locator){
		this.value = locator;
	}
}
