package org.roommanager.test.tablet.meeting;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.tablet.home.HomePage;
import org.roommanager.pages.tablet.scheduler.ProvideCredentialPage;
import org.roommanager.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.pages.tablet.setting.SettingPage;
import org.roommanager.util.DateGenerator;
import org.roommanager.util.HttpRequest;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.WebBrowser;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyMeetingIsUpdated {
	private WebDriver driver;
	private String organizer = PropertyReader.getUsername();
	private String startTime = DateGenerator.getStartDate();
	private String endTime = DateGenerator.getEndDate(60);
	private String roomName = "SM-Room9";
	private String meetingSubject = "Subject Meeting Pablo S.";
	private String attendees = "\"admin@admin.com\"";
	private String password = PropertyReader.getUserPassword();
	private String meetingSubjectUpdated = "Updated Subject Meeting Pablo S.";
	private String attendee = "admin@admin.com";
	private String attendeeUpdated = "administrator@gmail.com";
	private String errorMessage = "The Test failed because the meeting wasn't updated";
	
	@BeforeSuite
	public void suiteSetUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@BeforeTest
	public void testSetUp() throws Exception {
		HttpRequest.createMeeting(organizer, meetingSubject, startTime, endTime, roomName, attendees);
	}
	
	@Test
	public void verifyAMeetingIsUpdated() throws Exception {
		
		SettingPage settings = new SettingPage(driver);
		
		HomePage tabletHome = settings
			.waitForSettingpageToLoad()
			.clickRoomItem(roomName)
			.clickAcceptButton();
		
		SchedulerPage scheduler = tabletHome
			.clickSchedulerLink();
		
		ProvideCredentialPage credentials = scheduler
			.moveTimelineBox(startTime)
			.clickMeetingFromTimeLine(meetingSubject)
			.enterMeetingSubject(meetingSubjectUpdated)
			.enterAttendee(attendeeUpdated)
			.clickUpdateButton();
		
		scheduler = credentials
			.enterPassword(password)
			.clickOkButton();
		
		String attendees = attendee + "," + attendeeUpdated;
		
		Assert.assertTrue(scheduler.verifyAttendeesExist(attendees), errorMessage);
		Assert.assertEquals(scheduler.getMeetingSubjectFromTimeLine(meetingSubjectUpdated), meetingSubjectUpdated, errorMessage);
		Assert.assertEquals(scheduler.getMeetingSubject(), meetingSubjectUpdated, errorMessage);
	}
	
	@AfterTest
	public void testTearDown() throws Exception {
		HttpRequest.deleteMeetingBySubjectName(roomName, meetingSubjectUpdated);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
