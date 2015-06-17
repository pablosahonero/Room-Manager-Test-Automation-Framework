package org.roommanager.pages.tablet.scheduler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.util.TestLogger;
import org.roommanager.model.tablet.scheduler.Scheduler;


public class SchedulerPage {
	private WebDriver driver;
	private By createButtonLocator = Scheduler.CREATEBUTTON.value;
	private By organizerTextFieldLocator = Scheduler.ORGANIZERTEXTFIELD.value;
	private By subjectTextFieldLocator = Scheduler.SUBJECTTEXTFIELD.value;
	
	public SchedulerPage(WebDriver driver){
		this.driver = driver;
	}
	
	public SchedulerPage enterMeetingOrganizer(String organizer){
		WebElement organizerTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(organizerTextFieldLocator));
		organizerTextField.clear();
		organizerTextField.sendKeys(organizer);
		TestLogger.info("Organizer: <" + organizer + "> was entered");
		return this;
	}
	
	public SchedulerPage enterMeetingSubject(String subject){
		WebElement subjectTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(subjectTextFieldLocator));
		subjectTextField.clear();
		subjectTextField.sendKeys(subject);
		TestLogger.info("Subject: <" + subject + "> was entered");
		return this;
	}
	
	public ProvideCredentialPage clickCreateButton(){
		WebElement createButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(createButtonLocator));
		createButton.click();
		TestLogger.info("Create Meeting Button was clicked");
		return new ProvideCredentialPage(driver);
	}
}
