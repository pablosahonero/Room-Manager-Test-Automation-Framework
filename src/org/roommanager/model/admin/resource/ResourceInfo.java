package org.roommanager.model.admin.resource;

import org.openqa.selenium.By;

public enum ResourceInfo {
	RESOURCENAMEFIELD(By.xpath("(//input[@type='text'])[3]")),
	RESOURCEDISPLAYNAMEFIELD(By.xpath("(//input[@type='text'])[4]")),
	RESOURCEDESCRIPTIONAREA(By.xpath("//textarea")),
	SAVEBUTTON(By.cssSelector("button.info"));
	
	public By value;
	
	private ResourceInfo(By locator){
		this.value = locator;
	}
}
