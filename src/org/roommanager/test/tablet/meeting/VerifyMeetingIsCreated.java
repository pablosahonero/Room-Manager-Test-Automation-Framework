package org.roommanager.test.tablet.meeting;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.tablet.home.HomePage;
import org.roommanager.pages.tablet.scheduler.ProvideCredentialPage;
import org.roommanager.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.pages.tablet.setting.SettingPage;
import org.roommanager.util.HttpRequest;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.WebBrowser;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VerifyMeetingIsCreated {
	private WebDriver driver;
	
	@BeforeSuite
	public void suiteSetUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@Test
	public void verifyAMeetingIsCreated() throws Exception {
		String username = PropertyReader.getUsername();
		String password = PropertyReader.getUserPassword();
		String roomName = "RoomgGC-01";
		String meetingSubject = "Subject Meeting Pablo";
		String errorMessage = "The Test failed because the created could not be found in the Scheduler";
		
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
			.enterMeetingOrganizer(username)
			.enterMeetingSubject(meetingSubject)
			.clickCreateButton();
		
		scheduler = credentials
			.enterUsername(username)
			.enterPassword(password)
			.clickOkButton();
		
		Assert.assertEquals(meetingSubject, scheduler.getMeetingSubjectFromTimeLine(meetingSubject), errorMessage);
	}
	
	@AfterTest
	public void testTearDown() throws Exception {
		String roomName = "RoomgGC-01";
		String meetingSubject = "Subject Meeting Pablo";
		HttpRequest.deleteMeetingBySubjectName(roomName, meetingSubject);
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}	
		
}
