package org.roommanager.pages.tablet.scheduler;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.util.DateGenerator;
import org.roommanager.util.TestLogger;
import org.roommanager.model.tablet.scheduler.Scheduler;

public class SchedulerPage {
	private WebDriver driver;
	private By createButtonLocator = Scheduler.CREATE_BUTTON.value;
	private By removeButtonLocator = Scheduler.REMOVE_BUTTON.value;
	private By updateButtonLocator = Scheduler.UPDATE_BUTTON.value;
	private By organizerTextFieldLocator = Scheduler.ORGANIZER_TEXTFIELD.value;
	private By subjectTextFieldLocator = Scheduler.SUBJECT_TEXT_FIELD.value;
	private By attendeesTextFieldLocator = Scheduler.ATTENDEES_TEXT_FIELD.value;
	private By attendeesListLocator = Scheduler.ATTENDEES_LIST.value;
	private By attendeeElementLocator = Scheduler.ATTENDEES_LIST_ELEMENT.value;
	private By timeLineLocator = Scheduler.TIME_LINE.value;
	private By meetingsInTimeLineLocator = Scheduler.MEETINGS_IN_TIME_LINE.value;
	private By meetingSubjectLocator = Scheduler.MEETING_ELEMENT_TEXT.value;
	private By divElementLocator = Scheduler.DIV_ELEMENT.value;
	private By spanElementTextLocator = Scheduler.SPAN_ELEMENT.value;
	
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
	
	public boolean verifyAttendeesExist(String attendees){
		List <String> attendeesList = Arrays.asList(attendees.split(","));
		return verifyAttendeesExist(attendeesList); 
	}
	
	public String getMeetingSubject(){
		WebElement subjectTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(subjectTextFieldLocator));
		String meetingSubject = subjectTextField.getAttribute("value");
		TestLogger.info("Meeting Subject: <" +meetingSubject+ "> was retrieved");
		return meetingSubject;
	}
	
	private boolean verifyAttendeesExist(List<String> attendees){
		boolean attendeesExist = true;
		WebElement attendeesList = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(attendeesListLocator)); 
		List <WebElement> attendeesItems = attendeesList.findElements(attendeeElementLocator);
		for (WebElement attendee : attendeesItems) {
			String attendeeEmail = attendee.findElement(spanElementTextLocator).getText();
			if (attendees.contains(attendeeEmail)) {
				attendeesExist &= true;
				TestLogger.info("Meeting Attendee: <" +attendeeEmail+ "> exists");
			} else {
				TestLogger.info("Meeting Attendee: <" +attendeeEmail+ "> doesn't exist");
				return false;
			}
		}
		return attendeesExist;
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
		String subject = meeting.getText();
		TestLogger.info("The Meeting Subject:<" + subject + "> was retrieved from Timeline");
		return subject;
	}
	
	public boolean meetingExistsInTimeLine(String meetingSubject){
		WebElement meeting = getMeetingFromTimeLine(meetingSubject);
		return meeting == null? false:true;
	}
	
	public WebElement getMeetingFromTimeLine(String meetingSubject){
		WebElement meetings = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(meetingsInTimeLineLocator));
		List<WebElement> listOfMeetings = meetings.findElements(divElementLocator);
		
		for (WebElement meetingElement : listOfMeetings) {
			WebElement meeting = meetingElement.findElement(meetingSubjectLocator);
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
	
	public ProvideCredentialPage clickUpdateButton(){
		WebElement updateButton = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(updateButtonLocator));
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.elementToBeClickable(updateButtonLocator));
		updateButton.click();
		TestLogger.info("Update Meeting Button was clicked");
		return new ProvideCredentialPage(driver);
	}
	
	public SchedulerPage enterAttendee(String attendee){
		WebElement attendeesTextField = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(attendeesTextFieldLocator));
		attendeesTextField.clear();
		attendeesTextField.sendKeys(attendee + ";");
		TestLogger.info("Subject: <" + attendee + "> was entered");
		return this;
	}
	
	public SchedulerPage moveTimelineBox(String date){
		int time = DateGenerator.getHourOfDay(date); 
		int pixelesToMove = 0;
		if(time <= 7){
			pixelesToMove = 1500;
		} else if(time >= 19){
			pixelesToMove = -1500;
		}
		WebElement timeLine = (new WebDriverWait(driver, 60))
			.until(ExpectedConditions.presenceOfElementLocated(timeLineLocator)); 
		Actions action = new Actions(driver);
		action.clickAndHold(timeLine);
		action.moveByOffset(pixelesToMove, 100).release();
		action.perform();
		TestLogger.info("Timeline was moved to the Meeting Position");
		return this;
	}
}
