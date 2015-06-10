package org.roommanager.pages.admin.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.properties.PropertyReader;

public class LoginPage {
	private WebDriver driver;
	private PropertyReader properties;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		properties = new PropertyReader();
		driver.get(properties.getLoginUrl());
	}
	
	public LoginPage clickSignInButton(){
		WebElement usernameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button")));
		usernameTextField.click();
		return this;
	}
	
	public LoginPage enterUserName(){
		WebElement usernameTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='text']")));
		usernameTextField.clear();
		usernameTextField.sendKeys(properties.getUsername());
		return this;
	}
	
	public LoginPage enterPassword(){
		WebElement passwordTextField = (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='password']")));
		passwordTextField.clear();
		passwordTextField.sendKeys(properties.getUserPassword());
		return this;
	}
}
