package org.roommanager.model.admin.common;

import org.openqa.selenium.By;

public enum SideMenuBar {

	RESOURCES_LINK(By.linkText("Resources"));
	
	public By value;
	
	private SideMenuBar(By locator){
		this.value = locator;
	}
}
