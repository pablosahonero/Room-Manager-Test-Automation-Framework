package org.roommanager.pages.tablet.scheduler;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.util.TestLogger;
import org.roommanager.model.tablet.scheduler.Scheduler;

public class SchedulerPage {
	private WebDriver driver;
	private By createButtonLocator = Scheduler.CREATEBUTTON.value;
	private By removeButtonLocator = Scheduler.REMOVEBUTTON.value;
	private By organizerTextFieldLocator = Scheduler.ORGANIZERTEXTFIELD.value;
	private By subjectTextFieldLocator = Scheduler.SUBJECTTEXTFIELD.value;
	private By timeLineLocator = Scheduler.TIMELINE.value;
	private By meetingsInTimeLineLocator = Scheduler.MEETINGSINTIMELINE.value;
	private By meetingElementTextLocator = Scheduler.MEETINGELEMENTTEXT.value;
	private By divElementTextLocator = Scheduler.DIVELEMENT.value;
	
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
	
	public SchedulerPage moveTimelineBox(){
		WebElement timeLine = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(timeLineLocator)); 
		Actions action = new Actions(driver);
	    action.clickAndHold(timeLine)
	    	.moveByOffset(-1500, 100).release();
	    action.perform();
	    TestLogger.info("Timeline was moved");
		return this;
	}

	public SchedulerPage clickMeetingFromTimeLine(String meetingSubject)
	{
		WebElement meeting = getMeetingFromTimeLine(meetingSubject);
		meeting.click();
		TestLogger.info("The Meeting:<" + meetingSubject + "> from Timeline was clicked");
		return this;
	}

	public String getMeetingSubjectFromTimeLine(String meetingSubject){
		WebElement meeting = getMeetingFromTimeLine(meetingSubject);
		TestLogger.info("The Meeting Subject:<" + meetingSubject + "> was retrieved");
		return meeting.getText();
	}
	
	public boolean meetingExistsInTimeLine(String meetingSubject){
		WebElement meeting = getMeetingFromTimeLine(meetingSubject);
		return meeting == null? false:true;
	}
	
	public WebElement getMeetingFromTimeLine(String meetingSubject){
		WebElement meetings = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(meetingsInTimeLineLocator));
		List<WebElement> listOfMeetings = meetings.findElements(divElementTextLocator);
		
		for (WebElement webElement : listOfMeetings) {
			WebElement meeting = webElement.findElement(meetingElementTextLocator);
			if(meeting.getText().equals(meetingSubject)){
				TestLogger.info("Meeting with Subject: <" + meeting.getText() + "> was retrieved");
				return meeting;
			}
		}
		TestLogger.info("Meeting with Subject: <" + meetingSubject + "> was not found");
		return null;
	}
	
	public ProvideCredentialPage clickCreateButton(){
		WebElement createButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(createButtonLocator));
		createButton.click();
		TestLogger.info("Create Meeting Button was clicked");
		return new ProvideCredentialPage(driver);
	}
	
	public ProvideCredentialPage clickRemoveButton(){
		WebElement removeButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(removeButtonLocator));
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.elementToBeClickable(removeButtonLocator));
		removeButton.click();
		TestLogger.info("Remove Meeting Button was clicked");
		return new ProvideCredentialPage(driver);
	}
}
