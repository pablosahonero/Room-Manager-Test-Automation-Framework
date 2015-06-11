package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum Resource {
	ADDRESOURCEBUTTON(By.xpath("//div/div/button"));
	
	public By value;
	
	private Resource(By locator){
		this.value = locator;
	}
}
