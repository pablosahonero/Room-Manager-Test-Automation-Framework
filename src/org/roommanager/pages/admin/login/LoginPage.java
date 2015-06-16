package org.roommanager.pages.admin.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.admin.login.Login;
import org.roommanager.pages.admin.home.HomePage;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.TestLogger;

public class LoginPage {
	private WebDriver driver;
	private By signInButtonLocator = Login.SIGNINBUTTON.value;
	private By usernameTextFieldLocator = Login.USERNAMETEXTFIELD.value;
	private By passwordTextFieldLocator = Login.PASSWORDTEXTFIELD.value;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		driver.get(PropertyReader.getLoginUrl());
		driver.navigate().refresh();
	}
	
	public HomePage clickSignInButton(){
		WebElement signInButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(signInButtonLocator));
		signInButton.click();
		TestLogger.info("Sign In Button was clicked");
		return new HomePage(driver);
	}
	
	public LoginPage enterUserName(String username){
		WebElement usernameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(usernameTextFieldLocator));
		usernameTextField.clear();
		usernameTextField.sendKeys(username);
		TestLogger.info("User Name: <" + username +  "> was entered");
		return this;
	}
	
	public LoginPage enterPassword(String password){
		WebElement passwordTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(passwordTextFieldLocator));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		TestLogger.info("Password: <" + password +  "> was entered");
		return this;
	}
}
