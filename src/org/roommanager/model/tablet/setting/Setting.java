package org.roommanager.model.tablet.setting;

import org.openqa.selenium.By;

public enum Setting {
	SEARCHTEXTFIELD(By.id("txtSearch")),
	ROOMSTABLEFIRSTELEMENT(By.cssSelector("small.block.ng-binding")),
	ACCEPTBUTTON(By.xpath("//button")),
	NOITEMMESSAGE(By.cssSelector("small"));
	
	public By value;
	
	private Setting(By locator){
		this.value = locator;
	}
}
