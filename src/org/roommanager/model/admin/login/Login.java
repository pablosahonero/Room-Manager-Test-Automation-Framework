package org.roommanager.model.admin.login;

import org.openqa.selenium.By;

public enum Login {
	SIGNIN_BUTTON(By.xpath("//button")),
	USERNAME_TEXT_FIELD(By.cssSelector("input[type='text']")),
	PASSWORD_TEXT_FIELD(By.cssSelector("input[type='password']"));
	
	public By value;
	
	private Login(By locator){
		this.value = locator;
	}
}
