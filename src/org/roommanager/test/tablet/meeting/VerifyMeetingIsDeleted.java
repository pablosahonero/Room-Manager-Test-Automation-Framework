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
	
	@BeforeSuite
	public void suiteSetUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@BeforeTest
	public void testSetUp() throws Exception {
		String organizer = PropertyReader.getUsername();
		String startTime = DateGenerator.getStartDate();
		String endTime = DateGenerator.getEndDate(60);
		String roomName = "RoomgGC-01";
		String meetingSubject = "Subject Meeting Pablo";
		String attendees = "\"admin@admin.com\"";
		HttpRequest.createMeeting(organizer, meetingSubject, startTime, endTime, roomName, attendees);
	}
	
	@Test
	public void verifyAMeetingIsDeleted() throws Exception {
		String password = PropertyReader.getUserPassword();
		String roomName = "RoomgGC-01";
		String meetingSubject = "Subject Meeting Pablo";
		String errorMessage = "The Test failed because the meeting couldn't be found deleted";
		
		SettingPage settings = new SettingPage(driver);
		
		HomePage tabletHome = settings
			.waitForSettingpageToLoad()
			.searchRoomByName(roomName)
			.clickFirstRoomTableElement()
			.clickAcceptButton();
		
		SchedulerPage scheduler = tabletHome
			.clickSchedulerLink()
			.moveTimelineBox();
		
		ProvideCredentialPage credentials = scheduler
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
