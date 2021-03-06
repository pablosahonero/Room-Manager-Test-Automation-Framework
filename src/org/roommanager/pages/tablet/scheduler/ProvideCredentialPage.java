package org.roommanager.pages.tablet.scheduler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.model.tablet.scheduler.ProvideCredential;
import org.roommanager.util.TestLogger;

public class ProvideCredentialPage {
	
	private WebDriver driver;
	private By okButtonLocator = ProvideCredential.OK_BUTTON.value;
	private By usernameTextFieldLocator = ProvideCredential.USERNAME_TEXT_FIELD.value;
	private By passwordTextFieldLocator = ProvideCredential.PASSWORD_TEXT_FIELD.value;
	
	public ProvideCredentialPage(WebDriver driver){
		this.driver = driver;
	}
	
	public ProvideCredentialPage enterUsername(String username){
		WebElement usernameTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(usernameTextFieldLocator));
		usernameTextField.clear();
		usernameTextField.sendKeys(username);
		TestLogger.info("Username: <" + username + "> was entered");
		return this;
	}
	
	public ProvideCredentialPage enterPassword(String password){
		WebElement passwordTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(passwordTextFieldLocator));
		passwordTextField.clear();
		passwordTextField.sendKeys(password);
		TestLogger.info("Password: <" + password + "> was entered");
		return this;
	}
	
	public SchedulerPage clickOkButton(){
		WebElement okButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(okButtonLocator));
		okButton.click();
		TestLogger.info("OK Button was clicked");
		new WebDriverWait(driver, 60)
			.until(ExpectedConditions.invisibilityOfElementLocated(okButtonLocator));
		return new SchedulerPage(driver);
	}
}
