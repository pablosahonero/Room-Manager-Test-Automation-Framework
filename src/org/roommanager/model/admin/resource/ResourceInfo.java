package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum ResourceInfo {
	RESOURCE_NAME_FIELD(By.xpath("(//input[@type='text'])[3]")),
	RESOURCE_DISPLAY_NAME_FIELD(By.xpath("(//input[@type='text'])[4]")),
	RESOURCE_DESCRIPTION_AREA(By.xpath("//textarea")),
	SAVE_BUTTON(By.cssSelector("button.info"));
	
	public By value;
	
	private ResourceInfo(By locator){
		this.value = locator;
	}
}
