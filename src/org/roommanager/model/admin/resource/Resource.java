package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum Resource {
	ADDRESOURCEBUTTON(By.xpath("//div/div/button")),
	SEARCHRESOURCETEXTFIELD(By.xpath("//input[@type='text']")),
	FIRSTRESOURCESTABLEELEMENT(By.cssSelector("div.ng-scope > span.ng-binding"));
	
	public By value;
	
	private Resource(By locator){
		this.value = locator;
	}
}
