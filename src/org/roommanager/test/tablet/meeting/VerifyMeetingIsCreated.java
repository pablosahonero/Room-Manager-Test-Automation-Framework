package org.roommanager.test.tablet.meeting;

import org.openqa.selenium.WebDriver;
import org.roommanager.pages.tablet.home.HomePage;
import org.roommanager.pages.tablet.scheduler.ProvideCredentialPage;
import org.roommanager.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.pages.tablet.setting.SettingPage;
import org.roommanager.util.PropertyReader;
import org.roommanager.util.WebBrowser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.roommanager.util.ScreenShot.class)
public class VerifyMeetingIsCreated {
	private WebDriver driver;
	
	@BeforeSuite
	public void setUp() throws Exception {
		driver = WebBrowser.getGoogleChromeWebDriver();
	}
	
	@Test
	public void verifyAUserCanLogin() throws Exception {
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
			.clickSchedulerLink();
		
		ProvideCredentialPage credentials = scheduler
			.enterMeetingOrganizer(username)
			.enterMeetingSubject(meetingSubject)
			.clickCreateButton();
		
		scheduler = credentials
			.enterUsername(username)
			.enterPassword(password)
			.clickCreateButton();
	}
	
	@AfterSuite
	public void suiteTearDown(){
		driver.quit();
	}	
		
}
