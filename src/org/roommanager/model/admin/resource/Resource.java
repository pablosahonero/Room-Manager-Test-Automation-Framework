package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum Resource {
	ADDRESOURCEBUTTON(By.xpath("//div/div/button")),
	REMOVERESOURCEBUTTON(By.id("btnRemove")),
	SEARCHRESOURCETEXTFIELD(By.xpath("//input[@type='text']")),
	FIRSTRESOURCESTABLEELEMENT(By.cssSelector("div.ng-scope > span.ng-binding")),
	FIRSTRESOURCETABLEELEMENTCHECKBOX(By.cssSelector("input.ngSelectionCheckbox")),
	RESOURCETABLE(By.cssSelector("div.ngCanvas"));

	public By value;
	
	private Resource(By locator){
		this.value = locator;
	}
}
