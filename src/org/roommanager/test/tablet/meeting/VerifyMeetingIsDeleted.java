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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyMeetingIsDeleted {
	private WebDriver driver;
	private String organizer = PropertyReader.getUsername();
	private String startTime = DateGenerator.getStartDate();
	private String endTime = DateGenerator.getEndDate(60);
	private String roomName = "SM-Room9";
	private String meetingSubject = "Subject Meeting Pablo";
	private String attendees = "\"admin@admin.com\"";
	String password = PropertyReader.getUserPassword();
	String errorMessage = "The Test failed because the meeting couldn't be found deleted";
	
	@BeforeSuite
	public void suiteSetUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@BeforeTest
	public void testSetUp() throws Exception {
		
		HttpRequest.createMeeting(organizer, meetingSubject, startTime, endTime, roomName, attendees);
	}
	
	@Test
	public void verifyAMeetingIsDeleted() throws Exception {
		
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
			.clickRemoveButton();
		
		scheduler = credentials
			.enterPassword(password)
			.clickOkButton();
		Assert.assertFalse(scheduler.meetingExistsInTimeLine(meetingSubject), errorMessage);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}
}
