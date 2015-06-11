package org.roommanager.model.admin.login;

import org.openqa.selenium.By;

public enum Login {
	SIGNINBUTTON(By.xpath("//button")),
	USERNAMETEXTFIELD(By.cssSelector("input[type='text']")),
	PASSWORDTEXTFIELD(By.cssSelector("input[type='password']"));
	
	public By value;
	
	private Login(By locator){
		this.value = locator;
	}
}
