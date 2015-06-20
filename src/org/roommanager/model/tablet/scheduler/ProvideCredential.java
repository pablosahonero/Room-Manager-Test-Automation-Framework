package org.roommanager.model.tablet.scheduler;

import org.openqa.selenium.By;

public enum ProvideCredential {
	USERNAME_TEXT_FIELD(By.xpath("//input[@type='text']")),
	PASSWORD_TEXT_FIELD(By.xpath("//input[@type='password']")),
	OK_BUTTON(By.xpath("(//button[@type='button'])[2]"));
	
	public By value;
	
	private ProvideCredential(By locator){
		this.value = locator;
	}
}
