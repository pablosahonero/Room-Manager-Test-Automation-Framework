package org.roommanager.model.tablet.scheduler;

import org.openqa.selenium.By;

public enum ProvideCredential {
	USERNAMETEXTFIELD(By.xpath("//input[@type='text']")),
	PASSWORDTEXTFIELD(By.xpath("//input[@type='password']")),
	OKBUTTON(By.xpath("(//button[@type='button'])[2]"));
	
	public By value;
	
	private ProvideCredential(By locator){
		this.value = locator;
	}
}
