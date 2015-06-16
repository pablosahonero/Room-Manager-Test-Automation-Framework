package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum RemoveResource {
	REMOVEBUTTON(By.cssSelector("button.info"));
	
	public By value;
	
	private RemoveResource(By locator){
		this.value = locator;
	}
}
