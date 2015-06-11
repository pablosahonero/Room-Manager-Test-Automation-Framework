package org.roommanager.pages.admin.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.login.Login;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.util.PropertyReader;

public class LoginPage {
	private WebDriver driver;
	private By signInButtonLocator = Login.SIGNINBUTTON.value;
	private By usernameTextFieldLocator = Login.USERNAMETEXTFIELD.value;
	private By passwordTextFieldLocator = Login.PASSWORDTEXTFIELD.value;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		driver.get(PropertyReader.getLoginUrl());
	}
	
	public HomePage clickSignInButton(){
		WebElement signInButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(signInButtonLocator));
		signInButton.click();
		return new HomePage(driver);
	}
	
	public LoginPage enterUserName(){
		WebElement usernameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(usernameTextFieldLocator));
		usernameTextField.clear();
		usernameTextField.sendKeys(PropertyReader.getUsername());
		return this;
	}
	
	public LoginPage enterPassword(){
		WebElement passwordTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(passwordTextFieldLocator));
		passwordTextField.clear();
		passwordTextField.sendKeys(PropertyReader.getUserPassword());
		return this;
	}
}
