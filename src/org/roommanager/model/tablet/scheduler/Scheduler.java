package org.roommanager.model.tablet.scheduler;

import org.openqa.selenium.By;

public enum Scheduler {
	ORGANIZERTEXTFIELD(By.id("txtOrganizer")),
	SUBJECTTEXTFIELD(By.id("txtSubject")),
	CREATEBUTTON(By.xpath("//button[4]")),
	REMOVEBUTTON(By.xpath("//button[2]")),
	TIMELINE(By.xpath("//div[@id='timelinePanel']")),
	MEETINGSINTIMELINE(By.xpath("//div[@id='timelinePanel']/rm-vis/div/div[4]/div/div/div[2]/div")),
	MEETINGELEMENTTEXT(By.xpath("div/span")),
	DIVELEMENT(By.xpath("div"));
	
	public By value;
	
	private Scheduler(By locator){
		this.value = locator;
	}
}
